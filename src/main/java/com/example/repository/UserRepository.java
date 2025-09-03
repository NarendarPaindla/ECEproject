package com.example.repository;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    List<User> findByRole(Role role);
    List<User> findByManagerId(Long managerId);
    boolean existsByEmailIgnoreCase(String email);
}
