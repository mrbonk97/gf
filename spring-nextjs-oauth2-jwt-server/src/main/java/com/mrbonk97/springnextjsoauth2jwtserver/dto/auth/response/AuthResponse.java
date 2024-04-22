package com.mrbonk97.springnextjsoauth2jwtserver.dto.auth.response;

import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import com.mrbonk97.springnextjsoauth2jwtserver.utils.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthResponse {
    private final Long id;
    private final String email;
    private final String name;
    private final String imageUrl;
    private final String access_token;

    public static AuthResponse from(User user) {
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getImageUrl(),
                JwtUtil.generateAccessToken(String.valueOf(user.getId()))
        );
    }
}
