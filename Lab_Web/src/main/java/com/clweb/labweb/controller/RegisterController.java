package com.clweb.labweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/main")
    public String backtoMain(){
        return "Main";
    }
}
