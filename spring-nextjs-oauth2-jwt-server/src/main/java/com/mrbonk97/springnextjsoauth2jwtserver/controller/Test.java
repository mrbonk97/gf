package com.mrbonk97.springnextjsoauth2jwtserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/oauth2")
@RestController
public class Test {
    @GetMapping
    public String test() {
        return "Test";
    }
}
