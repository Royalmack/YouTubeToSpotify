package com.example.demo.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.User;
import com.example.demo.entities.ResponseEntity;
import com.example.demo.entities.UserInformation;
import com.example.demo.helpers.ParameterStringBuilder;
import com.example.demo.helpers.SpotifyHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class HttpUtility {

    private static final String TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token";
    private static final String ME_ENDPOINT = "https://api.spotify.com/v1/me";
    private static ObjectMapper objectMapper = new ObjectMapper();

    private HttpUtility() {
    }

    public static ResponseEntity getAccessToken(SpotifyHelper spotify, User user) throws IOException {

        URL url = new URL(TOKEN_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        setHeaders(connection, "POST", "Content-Type", "application/x-www-form-urlencoded");

        generateParamsAndBody(connection, spotify, user);

        String content = getResponseContent(connection);

        return objectMapper.readValue(content, ResponseEntity.class);
    }

    public static UserInformation getUserInformation(ResponseEntity response) throws IOException {

        URL url = new URL(ME_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        setHeaders(connection, "GET", "Authorization", "Bearer "+ response.getAccess_token());

        String content = getResponseContent(connection);

        return objectMapper.readValue(content, UserInformation.class);

    }

    private static void setHeaders(HttpURLConnection connection, String method, String propKey, String propValue) throws ProtocolException {
        connection.setRequestMethod(method);
        connection.setRequestProperty(propKey, propValue);
    }

    private static void generateParamsAndBody(HttpURLConnection connection, SpotifyHelper spotify, User user) throws IOException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", spotify.getClientID());
        parameters.put("grant_type", "authorization_code");
        parameters.put("code", user.getAuthCode());
        parameters.put("redirect_uri", spotify.getRedirectURI());
        parameters.put("code_verifier", spotify.getCodeVerifier());

        addRequestBody(connection, parameters);
    }

    private static void addRequestBody(HttpURLConnection connection, Map<String, String> parameters) throws IOException {
        connection.setDoOutput(true);
        DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        output.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        output.flush();
        output.close();
    }

    private static String getResponseContent(HttpURLConnection connection) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();

        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            content.append(inputLine);
        }

        input.close();

        return content.toString();
    }
}
