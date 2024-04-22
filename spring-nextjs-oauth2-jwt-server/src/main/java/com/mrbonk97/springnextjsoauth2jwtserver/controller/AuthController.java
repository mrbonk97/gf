package com.mrbonk97.springnextjsoauth2jwtserver.controller;

import com.mrbonk97.springnextjsoauth2jwtserver.dto.auth.request.SignInRequest;
import com.mrbonk97.springnextjsoauth2jwtserver.dto.auth.request.SignUpRequest;
import com.mrbonk97.springnextjsoauth2jwtserver.dto.auth.response.AuthResponse;
import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import com.mrbonk97.springnextjsoauth2jwtserver.service.UserService;
import com.mrbonk97.springnextjsoauth2jwtserver.utils.CookieUtils;
import com.mrbonk97.springnextjsoauth2jwtserver.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final UserService userService;

    // send refresh_jwt = cookie, access_jwt = body
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest signUpRequest) throws URISyntaxException {
        User user  = userService.signUp(
                signUpRequest.getEmail(),
                signUpRequest.getName(),
                signUpRequest.getPassword()
        );

        String jwt =
                JwtUtil.generateRefrshToken(String.valueOf(user.getId()));

        ResponseCookie cookie = CookieUtils.generateRefreshToken(jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());


        URI uri = new URI("http://localhost:8080/api/users/" + user.getId());
        return ResponseEntity.created(uri).headers(headers).body(AuthResponse.from(user));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(SignInRequest signInRequest) {
        User user = userService.signIn(
                signInRequest.getEmail(),
                signInRequest.getPassword()
        );

        String jwt =
                JwtUtil.generateRefrshToken(String.valueOf(user.getId()));

        ResponseCookie cookie = CookieUtils.generateRefreshToken(jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());


        return ResponseEntity.ok().headers(headers).body(AuthResponse.from(user));
    }

    @GetMapping("/sign-out")
    public void signOut() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, CookieUtils.deleteRefreshToken().toString());
        ResponseEntity.ok().headers(headers).body("Refresh Jwt Successfully Deleted Cookie");
    }

}
