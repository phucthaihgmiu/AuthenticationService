package com.example.authenticationservice.service.Impl;

import com.example.authenticationservice.dto.*;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.exception.MyException;
import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.security.JwtHelper;
import com.example.authenticationservice.service.RoleService;
import com.example.authenticationservice.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
//    private final RoleService roleService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        System.out.println("UaaServiceImpl - login");
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        } catch(BadCredentialsException e) {
        }catch(Exception e){
            throw new MyException("Exception : " + e.getMessage());
        }

        System.out.println("before findByEmail");
        final User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new MyException("User Not Found"));

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
//        final List<String> roles = user.getRolesAsString();

        LoginResponseDto loginResponse = new LoginResponseDto(
                accessToken, refreshToken,
                user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole()
//                roles
        );
        return loginResponse;
    }

    @Override
    public LoginResponseDto register(UserDto inUser) throws MyException {
        System.out.println("UaaServiceImpl - register " + inUser.toString());

        if(userRepository.findByEmail(inUser.getEmail()).isEmpty()) {
        }else{
            throw new MyException("User exists");
        }

        System.out.println("before hashed password");
        String hashedPwd = bCryptPasswordEncoder.encode(inUser.getPassword());

        User outUser = new User();
        outUser.setId(UUID.randomUUID());
        outUser.setEmail(inUser.getEmail());
        outUser.setPassword(hashedPwd);
        outUser.setFirstName(inUser.getFirstName());
        outUser.setLastName(inUser.getLastName());

        outUser.setRole(inUser.getRole());
//        if(inUser.getRoles() == null && inUser.getRoles().size() == 0) {
//            Role role = roleService.getByName("USER");
//            if(role == null) {
//                role = new Role();
//                role.setRole("USER");
//            }
//            outUser.addRole(role);
//        }else{
//            inUser.getRoles().forEach(r -> {
//                Role role = null;
//                if(roleService.getByName(r) != null) {
//                    role = roleService.getByName(r);
//                }else{
//                    role = new Role();
//                    role.setRole(r);
//                }
//
//                outUser.addRole(role);
//            });
//        }

        userRepository.save(outUser);

        final String accessToken = jwtHelper.generateToken(outUser.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(outUser.getEmail());

        LoginResponseDto loginResponse = new LoginResponseDto(
                accessToken, refreshToken,
                outUser.getEmail(), outUser.getFirstName(),
                outUser.getLastName(), outUser.getRole()
//                outUser.getRolesAsString()
        );
        return loginResponse;
    }

    @Override
    public LoginResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        System.out.println("UaaServiceImpl - refreshToken");
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String email = jwtHelper.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
            if(userRepository.findByEmail(email).isPresent()) {
                User user = userRepository.findByEmail(email).get();
                final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
                var loginResponse = new LoginResponseDto(accessToken, refreshTokenRequest.getRefreshToken(),
                        email, user.getFirstName(),
                        user.getLastName(), user.getRole()
//                        user.getRolesAsString()
                );
                return loginResponse;
            }
        }
        return new LoginResponseDto();
    }

    @Override
    public AuthenticateTokenResponseDto authenticateToken(RefreshTokenRequestDto refreshTokenRequest) {
        System.out.println("UaaServiceImpl - validateToken");
        return new AuthenticateTokenResponseDto(jwtHelper.validateToken(refreshTokenRequest.getRefreshToken()));
    }

}
