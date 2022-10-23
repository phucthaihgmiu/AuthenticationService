package com.example.authenticationservice.service.Impl;

import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.repository.RoleRepository;
import com.example.authenticationservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        System.out.println("RoleServiceImpl - getByName");
        return roleRepository.findByRole(name);
    }
}