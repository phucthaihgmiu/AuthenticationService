package com.example.authenticationservice.service.Impl;

import com.example.authenticationservice.dto.UserDetailsAdapter;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.exception.MyException;
import com.example.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println("UserDetailsServiceImpl - loadUserByUsername");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MyException("Cannot find user: " + email));
        return new UserDetailsAdapter(user);
    }
}
