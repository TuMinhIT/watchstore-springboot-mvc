package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
