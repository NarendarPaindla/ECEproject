package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController @RequestMapping("/api/users")
public class UserController {
    private UserService users;
    public UserController(UserService users){
        this.users=users;
    }
 @PostMapping("/signup")
   public ResponseEntity<User> create(@Valid @RequestBody User u){ 
    return ResponseEntity.ok(users.create(u));
 }
 @PutMapping("/{id}")
 public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User u){
    return ResponseEntity.ok(users.update(id,u));
 }

 @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){ 
    users.delete(id);
     return ResponseEntity.noContent().build(); 
    }
 
}
