package com.example.service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){ this.repo=repo; }

    public User create(User u){
        if (repo.existsByEmailIgnoreCase(u.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        // Default role
        if (u.getRole() == null) u.setRole(Role.EMPLOYEE);
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

    public void delete(Long id){ repo.deleteById(id); }
    public List<User> all(){ return repo.findAll(); }
    public User byId(Long id){ return repo.findById(id).orElseThrow(); }
    public List<User> employeesOfManager(Long managerId){ return repo.findByManagerId(managerId); }
    public List<User> byRole(Role role){ return repo.findByRole(role); }

    public User login(String email, String password){
        User u = repo.findByEmailIgnoreCase(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if(!u.getPassword().equals(password)) throw new IllegalArgumentException("Invalid email or password");
        return u;
    }
    public User updatePartial(Long id, User incoming) {
    User u = repo.findById(id).orElseThrow();

    if (incoming.getFullName() != null && !incoming.getFullName().isBlank()) {
        u.setFullName(incoming.getFullName());
    }
    if (incoming.getEmail() != null && !incoming.getEmail().isBlank()) {
        // check if new email already exists
        if (repo.existsByEmailIgnoreCase(incoming.getEmail()) && !u.getEmail().equalsIgnoreCase(incoming.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        u.setEmail(incoming.getEmail());
    }
    if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {
        u.setPassword(incoming.getPassword());
    }
    if (incoming.getRole() != null) {
        u.setRole(incoming.getRole());
    }
    if (incoming.getManagerId() != null) {
        u.setManagerId(incoming.getManagerId());
    }

    return repo.save(u);
}

}
