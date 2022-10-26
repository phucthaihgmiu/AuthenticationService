package com.example.authenticationservice.dto;

import lombok.*;

import java.util.List;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {

    private String accessToken;

    private String refreshToken;

    private String email;

    private String firstName;

    private String lastName;

//    private List<String> roles;
    private String role;
}
