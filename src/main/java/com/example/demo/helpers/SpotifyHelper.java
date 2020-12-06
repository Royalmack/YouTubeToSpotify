package com.example.demo.helpers;

import java.security.NoSuchAlgorithmException;

import com.example.demo.utility.PCKEUtility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class SpotifyHelper {

    @Getter
    @Value("${credentials.client_id}")
    private String clientID;
    @Getter
    @Value("${credentials.response_type}")
    private String responseType;
    @Getter
    @Value("${credentials.redirect_uri}")
    private String redirectURI;
    @Value("${credentials.scopes}")
    private String scopes;
    @Value("${credentials.challenge_type}")
    private String codeChallengeMethod;
    private String codeChallenge;
    @Getter
    private String codeVerifier;
    private String baseURL = "https://accounts.spotify.com/authorize?";

    public SpotifyHelper() {
        // Empty due to Autowiring
    }

    public String buildURL() throws NoSuchAlgorithmException {
        generateVerifier();
        generateChallenge();
        return (baseURL + "client_id=" + this.clientID + "&redirect_uri=" + this.redirectURI + "&scope=" + this.scopes
                + "&response_type=" + this.responseType + "&code_challenge_method=" + this.codeChallengeMethod + "&code_challenge=" + this.codeChallenge);
    }

    private void generateVerifier() {
        codeVerifier = PCKEUtility.generateVerifier();
    }

    private void generateChallenge() throws NoSuchAlgorithmException {
       codeChallenge = PCKEUtility.generateChallenge(codeVerifier);
    }
}