package com.genc.healthins.controller;

import com.genc.healthins.dto.UserDTO;
import com.genc.healthins.entity.User;
import com.genc.healthins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(HttpSession session){
        Object obj = session.getAttribute("user"); if(obj == null) return ResponseEntity.status(401).body("Not logged in");
        User u = (User)obj; u.setPassword(null); return ResponseEntity.ok(new UserDTO(u.getUserId(), u.getUsername(), u.getEmail(), u.getRole()));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(HttpSession session, @RequestBody User body){
        Object obj = session.getAttribute("user"); if(obj == null) return ResponseEntity.status(401).body("Not logged in");
        User u = (User)obj; User updated = userService.updateUser(u.getUserId(), body); updated.setPassword(null); session.setAttribute("user", updated);
        return ResponseEntity.ok(new UserDTO(updated.getUserId(), updated.getUsername(), updated.getEmail(), updated.getRole()));
    }
}
