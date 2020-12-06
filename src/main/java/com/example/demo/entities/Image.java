package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

public class Image {
    @Getter @Setter
    private int height;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private int width;

    public Image() {
        //Empty default
    }
}
