package com.example.demo.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.example.demo.User;
import com.example.demo.entities.ResponseEntity;
import com.example.demo.entities.UserInformation;
import com.example.demo.helpers.SpotifyHelper;
import com.example.demo.utility.HttpUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    ObjectMapper objectMapper = new ObjectMapper();
    UserInformation userInfo;

    @Autowired
    SpotifyHelper spotify;
    @Autowired
    User user;

    @GetMapping("/")
    public ModelAndView test() throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("spotifyURL", spotify.buildURL());
        return mv;
    }

    @GetMapping("/callback")
    public RedirectView callback(@RequestParam String code) throws IOException {

        user.setAuthCode(code);

        ResponseEntity response = HttpUtility.getAccessToken(spotify, user);
        userInfo = HttpUtility.getUserInformation(response);

        return new RedirectView("http://localhost:8080/convert");
    }

    @GetMapping("/convert")
    public ModelAndView userInfoPage() {
        return new ModelAndView("convert");
    }

    @PostMapping("/processPlaylist")
    public ModelAndView processList(@RequestBody String playlistID) throws InterruptedException {
        Thread.sleep(15000);
        return new ModelAndView("completed");
    }
}
