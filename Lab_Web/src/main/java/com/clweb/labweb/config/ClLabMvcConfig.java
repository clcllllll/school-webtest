package com.clweb.labweb.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Controller
@Configuration
@EnableAutoConfiguration
public class ClLabMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/main.html").setViewName("Main");
        registry.addViewController("/appli").setViewName("Manage/Application");
        registry.addViewController("/sosuo").setViewName("Manage/Search");
        registry.addViewController("cl").setViewName("test/testmain");
        registry.addViewController("test1").setViewName("test/bootstrap1");
    }
}
