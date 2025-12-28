package com.genc.healthins.dto;

import com.genc.healthins.entity.Role;

public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private Role role;

    public UserDTO() {}

    public UserDTO(Integer id, String username, String email, Role role){
        this.userId = id; this.username = username; this.email = email; this.role = role;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
