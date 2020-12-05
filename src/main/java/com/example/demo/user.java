package com.example.demo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Component
public class user {

    @Getter @Setter
    private String authCode;

    @Getter @Setter
    private String accessToken;
}
