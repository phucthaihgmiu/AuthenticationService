package com.example.authenticationservice.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
public class UserDto {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

//    private List<String> roles;
    private String role;
}
