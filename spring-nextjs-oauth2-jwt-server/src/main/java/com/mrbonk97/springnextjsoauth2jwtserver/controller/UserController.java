package com.mrbonk97.springnextjsoauth2jwtserver.controller;

import com.mrbonk97.springnextjsoauth2jwtserver.dto.user.response.UserResponse;
import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import com.mrbonk97.springnextjsoauth2jwtserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    public ResponseEntity<UserResponse> getMyInfo(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        User user = userService.loadById(userId);
        return null;
    }
}
