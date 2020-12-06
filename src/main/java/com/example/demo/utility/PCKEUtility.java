package com.example.demo.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public final class PCKEUtility {

    private PCKEUtility() {
        //Don't allow construction of class
    }

    public static String generateVerifier() {
        SecureRandom sr = new SecureRandom();
        byte[] code = new byte[43];
        sr.nextBytes(code);

        return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    public static String generateChallenge(String codeVerifier) throws NoSuchAlgorithmException {
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes, 0, bytes.length);
        byte[] digest = md.digest();

        return Base64.encodeBase64URLSafeString(digest);
    }
}
