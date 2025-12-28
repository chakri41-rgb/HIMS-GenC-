package com.genc.healthins.controller;

import com.genc.healthins.dto.UserDTO;
import com.genc.healthins.entity.User;
import com.genc.healthins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    @Autowired private UserService userService;

    @GetMapping
    @ResponseBody
    public List<UserDTO> listUsers(){
        return userService.getAllUsers().stream().map(u -> new UserDTO(u.getUserId(), u.getUsername(), u.getEmail(), u.getRole())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        return userService.getUserById(id).map(u -> ResponseEntity.ok(new UserDTO(u.getUserId(), u.getUsername(), u.getEmail(), u.getRole())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody User user){
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(new UserDTO(created.getUserId(), created.getUsername(), created.getEmail(), created.getRole()));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user){
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(new UserDTO(updated.getUserId(), updated.getUsername(), updated.getEmail(), updated.getRole()));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
