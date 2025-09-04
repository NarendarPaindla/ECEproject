package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service @Transactional
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){
        this.repo=repo;
    }

    public User create(User u){
        if(repo.existsByEmailIgnoreCase(u.getEmail()))
           throw new IllegalArgumentException("Email already exists");
        if (u.getRole()==null) u.setRole(Role.EMPLOYEE);
        return repo.save(u);
    }

    public User update(Long id, User incoming){
        User u = repo.findById(id).orElseThrow();
        u.setFullName(incoming.getFullName());
        u.setEmail(incoming.getEmail());
        if (incoming.getPassword()!=null && !incoming.getPassword().isBlank()) u.setPassword(incoming.getPassword());
        if (incoming.getRole()!=null) u.setRole(incoming.getRole());
        u.setManagerId(incoming.getManagerId());
        return repo.save(u);
    }
     public void delete(Long id){ 
        repo.deleteById(id); 
    }

}
