package com.jjonami.datajpa.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger log = LogManager.getLogger(HelloController.class);

    @GetMapping("/api/v1/hello")
    public String hello(){
        return "hello";
    }

}
