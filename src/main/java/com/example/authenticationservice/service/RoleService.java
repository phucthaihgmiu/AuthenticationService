package com.example.authenticationservice.service;

import com.example.authenticationservice.entity.Role;

public interface RoleService {
    Role getByName(String name);
}
