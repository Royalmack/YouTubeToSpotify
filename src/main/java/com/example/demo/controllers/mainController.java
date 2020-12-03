package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {

    @GetMapping("/test")
    public ModelAndView test(@RequestParam String name) {
        ModelAndView mv = new ModelAndView("test");
        System.out.println(name);

        return mv;
    }
    
}
