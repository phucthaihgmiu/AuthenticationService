package com.example.authenticationservice;

import com.example.authenticationservice.security.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

//@SpringBootTest
class AuthenticationServiceApplicationTests {

   // @Test
    void contextLoads() {
    }

    //@Bean
    public JwtHelper jwtHelper(){
        return new JwtHelper();
    }
}
