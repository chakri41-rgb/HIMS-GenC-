package com.genc.healthins.controller;

import com.genc.healthins.entity.User;
import com.genc.healthins.dto.UserDTO;
import com.genc.healthins.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @Autowired private AuthService authService;
    @Autowired private AuthenticationManager authManager;

    // Display registration form
    @GetMapping("/auth/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    // Handle registration form submission
    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user, HttpSession session, RedirectAttributes ra){
        if(authService.findByUsername(user.getUsername()).isPresent()){
            ra.addFlashAttribute("error", "Username already exists");
            return "redirect:/auth/register";
        }
        try {
            User created = authService.register(user);
            created.setPassword(null);
            session.setAttribute("user", created);
            log.info("Registered new user: {}", created.getUsername());
            return "redirect:/user/dashboard.html";
        } catch (Exception ex) {
            log.error("Error registering user {}: {}", user.getUsername(), ex.getMessage());
            ra.addFlashAttribute("error", "Registration failed: " + ex.getMessage());
            return "redirect:/auth/register";
        }
    }

    // Display login form - redirect to the static login page to avoid template 404s
    @GetMapping("/auth/login")
    public String showLoginForm(Model model){
        return "redirect:/login.html";
    }

    // Handle login submission
    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes ra){
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User u = authService.findByUsername(username).orElse(null);
            if(u != null){
                u.setPassword(null);
                session.setAttribute("user", u);
                log.info("User logged in: {}", u.getUsername());
                return "redirect:/user/dashboard.html";
            } else {
                ra.addFlashAttribute("error", "Invalid credentials");
                return "redirect:/login.html";
            }
        } catch (AuthenticationException ex){
            ra.addFlashAttribute("error", "Invalid credentials");
            return "redirect:/auth/login";
        }
    }

    // Logout — invalidate session and redirect to login
    @PostMapping("/auth/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    // API: Get current profile from session (used by JS)
    @GetMapping("/api/auth/profile")
    @ResponseBody
    public ResponseEntity<?> profile(HttpSession session){
        Object obj = session.getAttribute("user");
        if(obj == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        User u = (User)obj;
        u.setPassword(null);
        UserDTO dto = new UserDTO(u.getUserId(), u.getUsername(), u.getEmail(), u.getRole());
        return ResponseEntity.ok(dto);
    }
}
