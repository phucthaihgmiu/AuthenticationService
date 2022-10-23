package com.example.authenticationservice.dto;

import lombok.*;

//@Data
@Builder
@Getter
@EqualsAndHashCode()
public class LoginRequestDto {

    private String email;

    private String password;


}
