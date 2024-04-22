package com.mrbonk97.springnextjsoauth2jwtserver.dto.auth.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    private final String email;
    private final String password;
}
