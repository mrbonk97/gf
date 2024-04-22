package com.mrbonk97.springnextjsoauth2jwtserver.dto.user.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String email;
    private final String name;
    private final String imageUrl;
}
