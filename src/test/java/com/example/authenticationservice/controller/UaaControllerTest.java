package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.security.JwtHelper;
import com.example.authenticationservice.security.SecurityConfiguration;
import com.example.authenticationservice.service.UaaService;
import com.example.authenticationservice.util.TestUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.hamcrest.Matchers.is;

import java.lang.reflect.Array;
import java.util.ArrayList;

@WebMvcTest(value = UaaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UaaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UaaService uaaService;

    @MockBean
    private JwtHelper jwtHelper;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldLogin() throws Exception {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("test@gmail.com")
                .password("123")
                .build();

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDExNzE2fQ.PvXDIQ1Wl8YpK5twbxHRjGobX3BKhUjFW_YD3Y6PqYZ1grQl3Mx6HZHtG70z0Oe4NjOc_uKtHhr3fQ7msv8CbA")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDc1NDM2fQ.BICmiiTsO-UgO1GFx46zoGr096mun-pJt1-9Fcz2FK8UhmESCl4nHEbXDKXZ-NG0HmeQdcZqP8Ev-JN3MY_DqQ")
                .email("test@gmail.com")
                .firstName("first")
                .lastName("last")
                .roles(new ArrayList<>() {
                    {
                        add("USER");
                    }
                })
                .build();

        when(uaaService.login(loginRequestDto)).thenReturn(loginResponseDto);

        mockMvc.perform(
                post("/uaa")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.stringify(loginRequestDto))
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", is(loginResponseDto.getAccessToken())))
                .andExpect(jsonPath("$.refreshToken", is(loginResponseDto.getRefreshToken())))
                .andExpect(jsonPath("$.email", is(loginResponseDto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(loginResponseDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(loginResponseDto.getLastName())))
                .andExpect(jsonPath("$.roles", is(loginResponseDto.getRoles())));
    }

    @Test
    void shouldRegister() throws Exception {
        UserDto userDto = UserDto.builder()
                .email("test@gmail.com")
                .password("123")
                .firstName("first")
                .lastName("last")
                .roles(new ArrayList<>() {
                    {
                        add("USER");
                    }
                })
                .build();

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDExNzE2fQ.PvXDIQ1Wl8YpK5twbxHRjGobX3BKhUjFW_YD3Y6PqYZ1grQl3Mx6HZHtG70z0Oe4NjOc_uKtHhr3fQ7msv8CbA")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDc1NDM2fQ.BICmiiTsO-UgO1GFx46zoGr096mun-pJt1-9Fcz2FK8UhmESCl4nHEbXDKXZ-NG0HmeQdcZqP8Ev-JN3MY_DqQ")
                .email("test@gmail.com")
                .firstName("first")
                .lastName("last")
                .roles(new ArrayList<>() {
                    {
                        add("USER");
                    }
                })
                .build();

        when(uaaService.register(userDto)).thenReturn(loginResponseDto);

        mockMvc.perform(
                        post("/uaa/sign-up")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.stringify(userDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", is(loginResponseDto.getAccessToken())))
                .andExpect(jsonPath("$.refreshToken", is(loginResponseDto.getRefreshToken())))
                .andExpect(jsonPath("$.email", is(loginResponseDto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(loginResponseDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(loginResponseDto.getLastName())))
                .andExpect(jsonPath("$.roles", is(loginResponseDto.getRoles())));
    }

    @Test
    void shouldRefreshToken() throws Exception {
        RefreshTokenRequestDto refreshTokenRequestDto = RefreshTokenRequestDto.builder()
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDc1NDM2fQ.BICmiiTsO-UgO1GFx46zoGr096mun-pJt1-9Fcz2FK8UhmESCl4nHEbXDKXZ-NG0HmeQdcZqP8Ev-JN3MY_DqQ")
                .build();

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDExNzE2fQ.PvXDIQ1Wl8YpK5twbxHRjGobX3BKhUjFW_YD3Y6PqYZ1grQl3Mx6HZHtG70z0Oe4NjOc_uKtHhr3fQ7msv8CbA")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDc1NDM2fQ.BICmiiTsO-UgO1GFx46zoGr096mun-pJt1-9Fcz2FK8UhmESCl4nHEbXDKXZ-NG0HmeQdcZqP8Ev-JN3MY_DqQ")
                .email("test@gmail.com")
                .firstName("first")
                .lastName("last")
                .roles(new ArrayList<>() {
                    {
                        add("USER");
                    }
                })
                .build();

        when(uaaService.refreshToken(refreshTokenRequestDto)).thenReturn(loginResponseDto);

        mockMvc.perform(
                        post("/uaa/refresh-token")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.stringify(refreshTokenRequestDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", is(loginResponseDto.getAccessToken())))
                .andExpect(jsonPath("$.refreshToken", is(loginResponseDto.getRefreshToken())))
                .andExpect(jsonPath("$.email", is(loginResponseDto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(loginResponseDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(loginResponseDto.getLastName())))
                .andExpect(jsonPath("$.roles", is(loginResponseDto.getRoles())));
    }

    @Test
    void shouldAuthenticateToken() throws Exception {
        RefreshTokenRequestDto refreshTokenRequestDto = RefreshTokenRequestDto.builder()
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTY2NjQxMDYzNiwiZXhwIjoxNjY2NDc1NDM2fQ.BICmiiTsO-UgO1GFx46zoGr096mun-pJt1-9Fcz2FK8UhmESCl4nHEbXDKXZ-NG0HmeQdcZqP8Ev-JN3MY_DqQ")
                .build();

        AuthenticateTokenResponseDto authenticateTokenResponseDto = AuthenticateTokenResponseDto.builder()
                .authenticated(true)
                .build();

        when(uaaService.authenticateToken(refreshTokenRequestDto)).thenReturn(authenticateTokenResponseDto);

        mockMvc.perform(
                        post("/uaa/authenticate")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.stringify(refreshTokenRequestDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authenticated", is(authenticateTokenResponseDto.isAuthenticated())));
    }
}
