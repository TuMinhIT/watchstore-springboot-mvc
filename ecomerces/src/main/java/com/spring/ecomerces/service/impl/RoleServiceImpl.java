package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.RoleDAO;
import com.spring.ecomerces.models.Role;
import com.spring.ecomerces.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void saveRole(Role userRole) {
        roleDAO.save(userRole);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleDAO.findByName(roleName);
    }
}
