package com.app.ECommerce.controller;

import com.app.ECommerce.dto.UserRequest;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest userRequest)
    {
        UserResponse savedUser=userService.addNewUser(userRequest);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>("User Deleted Successfullt",HttpStatus.GONE);
    }
}

