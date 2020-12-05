package com.example.demo.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.spotifyHelper;
import com.example.demo.user;
import com.example.demo.helpers.parameterStringBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

    @Autowired
    spotifyHelper spotify;
    @Autowired
    user user;

    @GetMapping("/")
    public ModelAndView test() throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("spotifyURL", spotify.finalURL());
        return mv;
    }

    @GetMapping("/callback")
    public ModelAndView callback(@RequestParam String code) throws IOException {
        // Callback page where user processing will happen and then redirect to the conversion page
        user.setAuthCode(code);

        // Get access token
        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        //Set body
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", spotify.getClientID());
        parameters.put("grant_type", "authorization_code");
        parameters.put("code", user.getAuthCode());
        parameters.put("redirect_uri", spotify.getRedirectURI());
        parameters.put("code_verifier", spotify.getVerifier());

        //create string out of parameters 
        connection.setDoOutput(true);
        DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        output.writeBytes(parameterStringBuilder.getParamsString(parameters));
        output.flush();
        output.close();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = input.readLine()) != null) {
            content.append(inputLine);
        }
        input.close();

        return new ModelAndView("callback");
    }
}
