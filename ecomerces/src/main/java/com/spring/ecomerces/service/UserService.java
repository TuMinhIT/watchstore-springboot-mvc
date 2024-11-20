package com.spring.ecomerces.service;

import com.spring.ecomerces.dto.UserDto;
import com.spring.ecomerces.models.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    void saveUser(User user);

    User findUserByEmail(String email);

    boolean isValidCurrentPassword(String currentPassword, User user);

    void deleteUserById(int id);

    public List<User> findAllUsersExcludingAdmin();

    User userHasAccount(String email, String password);

    void deleteUser(User user);
}
