package com.app.ECommerce.service;

import com.app.ECommerce.dto.UserResponse;
import com.app.ECommerce.entity.User;
import com.app.ECommerce.mapper.UserResponseMapper;
import com.app.ECommerce.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addNewUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepo.findById(id).map(UserResponseMapper::mapToUserResponse);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream().map(UserResponseMapper::mapToUserResponse).collect(Collectors.toList());
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        userRepo.findById(id).ifPresent(existingUser->{
            updateUserRequest(existingUser,updateUser);
            userRepo.save(existingUser);
        });

    }

    public void updateUserRequest(User user,User updateUser)
    {
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setEmail(updateUser.getEmail());
        user.setPhone(updateUser.getPhone());
    }
}

