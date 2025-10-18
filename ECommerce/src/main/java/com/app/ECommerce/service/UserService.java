package com.app.ECommerce.service;

import com.app.ECommerce.dto.UserResponse;
import com.app.ECommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addNewUser(User user);

    Optional<UserResponse> getUserById(Long id);

    List<UserResponse> getAllUsers();

    void updateUser(Long id, User updateUser);
}

