package com.mrbonk97.springnextjsoauth2jwtserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/token")
@RestController
public class TokenController {

    @GetMapping("/refresh")
    public void refreshToken(@RequestBody String jwt, HttpServletRequest request) {
        request.getCookies();

//        JwtUtil.generateRefrshToken()
    }
}
