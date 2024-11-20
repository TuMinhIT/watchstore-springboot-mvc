package com.spring.ecomerces.dao;
import com.spring.ecomerces.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    void deleteById(int id);
    User findById(int id);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name <> 'ROLE_ADMIN'")
    List<User> findAllExcludingAdmin();
}

