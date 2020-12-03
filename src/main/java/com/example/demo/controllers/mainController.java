package com.example.demo.controllers;

import com.example.demo.spotifyHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

    @Autowired
    spotifyHelper spotify;

    @GetMapping("/")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("test");
        System.out.println("This is now the testing page");
        System.out.println(spotify.getBaseURL());

        return mv;
    }
}
