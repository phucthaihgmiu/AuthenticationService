package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.exception.MyException;
import com.example.authenticationservice.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
public class UaaController {

    private final UaaService uaaService;


    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest) {
        System.out.println("UaaController - login");
        LoginResponseDto result = uaaService.login(loginRequest);
        return result;
    }

    @PostMapping("/sign-up")
    public LoginResponseDto register(@RequestBody UserDto user){
        System.out.println("UaaController - register");
        return uaaService.register(user);
    }

    @PostMapping("/refresh-token")
    public LoginResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest){
        System.out.println("UaaController - refreshToken");
        return uaaService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/authenticate")
    public AuthenticateTokenResponseDto authenticateToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest){
        System.out.println("UaaController - authenticateToken");
        return uaaService.authenticateToken(refreshTokenRequest);
    }
}
