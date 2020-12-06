package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResponseEntity {
    
    @Getter @Setter
    private String access_token;
    @Getter @Setter
    private String token_type;
    @Getter @Setter
    private String scope;
    @Getter @Setter
    private String expires_in;
    @Getter @Setter
    private String refresh_token;

    public ResponseEntity() {
        //Lombok filling in the all args just need a default for linting
    }
}