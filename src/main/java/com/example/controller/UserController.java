package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/users")
public class UserController {
    private final UserService users;
    public UserController(UserService users){this.users=users;}

    @GetMapping public List<User> all(){ return users.all(); }
    @GetMapping("/{id}") public User one(@PathVariable Long id){ return users.byId(id); }

    @PostMapping
     public ResponseEntity<User> create(@Valid @RequestBody User u){ return ResponseEntity.ok(users.create(u)); }
    @PutMapping("/{id}") public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User u){ return ResponseEntity.ok(users.update(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ users.delete(id); return ResponseEntity.noContent().build(); }

    @GetMapping("/manager/{managerId}/employees")
    public List<User> employeesOfManager(@PathVariable Long managerId){ return users.employeesOfManager(managerId); }

    // NEW: list by role (e.g. MANAGER)
    @GetMapping("/role/{role}")
    public List<User> byRole(@PathVariable Role role){ return users.byRole(role); }

    @PatchMapping("/{id}")
public ResponseEntity<User> updatePartial(@PathVariable Long id, @RequestBody User u) {
    return ResponseEntity.ok(users.updatePartial(id, u));
}

}
