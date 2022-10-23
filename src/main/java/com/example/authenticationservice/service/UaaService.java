package com.example.authenticationservice.service;

import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.exception.MyException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UaaService {

    LoginResponseDto login(LoginRequestDto loginRequest);

    LoginResponseDto register(UserDto user) throws MyException;

    LoginResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest);

    AuthenticateTokenResponseDto authenticateToken(RefreshTokenRequestDto refreshTokenRequest);
}
