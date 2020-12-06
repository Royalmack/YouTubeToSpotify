package com.example.demo.entities;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserInformation {

    @Setter
    private String country;
    @Setter
    private String display_name;
    @Setter
    private String email;
    @Setter
    private Map<String, String> external_urls;
    @Setter
    private Followers followers;
     @Setter
    private String href;
    @Getter @Setter
    private String id;
    @Setter
    private List<Image> images;
    @Setter
    private String type;
    @Setter
    private String uri;

    public UserInformation() {
        //Default for Jackson binding
    }
}
