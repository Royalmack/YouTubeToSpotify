package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

public class Followers {

    @Getter @Setter
    private String href;
    @Getter @Setter
    private int total;

    public Followers() {
        //Empty default
    }
}