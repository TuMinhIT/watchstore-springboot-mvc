package com.spring.ecomerces.service;


import com.spring.ecomerces.models.Role;

public interface RoleService {
    void saveRole(Role userRole);
    Role findByRoleName(String roleName);
}
