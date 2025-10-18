package com.app.ECommerce.controller;

import com.app.ECommerce.dto.UserResponse;
import com.app.ECommerce.entity.User;
import com.app.ECommerce.service.UserService;
import com.app.ECommerce.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user)
    {
        User savedUser=userService.addNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable Long id){
        Optional<UserResponse> idUser= userService.getUserById(id);
        return new ResponseEntity<>(idUser, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> allUsers= userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,@RequestBody User updateUser){
        userService.updateUser(id,updateUser);
        return "Updated Successfully";
    }
}

