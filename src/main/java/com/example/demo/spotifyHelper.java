package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class spotifyHelper {

    private String clientID;
    private String resposneType;
    private String redirectURI;
    private String scopes;
    private String baseURL = "https://accounts.spotify.com/authorize";

    public spotifyHelper() {
        this.clientID = "";
        this.resposneType = "";
        this.redirectURI = "";
        this.scopes = "";
    }

    public String getBaseURL() {
        return this.baseURL;
    }
    
}
