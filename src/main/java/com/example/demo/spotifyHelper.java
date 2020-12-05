package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class spotifyHelper {

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
    private String codeVerifier;
    private String baseURL = "https://accounts.spotify.com/authorize?";

    public spotifyHelper() {
        // Empty due to Autowiring
    }

    public String finalURL() throws NoSuchAlgorithmException {
        generateVerifier();
        generateChallenge();
        return buildURL();
    }

    private String buildURL() {
        return (baseURL + "client_id=" + this.clientID + "&redirect_uri=" + this.redirectURI + "&scope=" + this.scopes
                + "&response_type=" + this.responseType + "&code_challenge_method=" + this.codeChallengeMethod + "&code_challenge=" + this.codeChallenge);
    }

    private void generateVerifier() {

        SecureRandom sr = new SecureRandom();
        byte[] code = new byte[43];
        sr.nextBytes(code);

        codeVerifier = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    private void generateChallenge() throws NoSuchAlgorithmException {
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes, 0, bytes.length);
        byte[] digest = md.digest();

        codeChallenge = Base64.encodeBase64URLSafeString(digest);
    }

    public String getVerifier() {
        return this.codeVerifier;
    }
}
