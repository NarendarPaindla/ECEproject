package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController @RequestMapping("/api/users")
public class UserController {
    private UserService users;
    public UserController(UserService users){
        this.users=users;
    }
    @PostMapping
   public ResponseEntity<User> create(@Valid @RequestBody User u){ 
    return ResponseEntity.ok(users.create(u));
 }
}
