package com.scaler.bms2025.bmsmay2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.bms2025.bmsmay2025.service.UserServiceImpl;
import com.scaler.bms2025.dto.UserResponse;

import jakarta.websocket.server.PathParam;

@RestController
public class UserController {
    // All the CRUD Operations for User.
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable("id") Integer id){
        return null;
    }
    // Homework --> Learn how to store passwords in SB.
    

}
