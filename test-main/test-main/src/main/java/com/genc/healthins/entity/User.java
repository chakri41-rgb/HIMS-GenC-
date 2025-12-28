package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users") // 'user' is reserved in some DBs; use 'users'
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable=false, unique=true, length=50)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(length=100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private Role role;

    public User(){}

    public User(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Integer getUserId(){ return userId; }
    public void setUserId(Integer userId){ this.userId = userId; }

    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }

    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public Role getRole(){ return role; }
    public void setRole(Role role){ this.role = role; }

    @OneToMany(mappedBy = "responder")
    private java.util.List<SupportResponse> responses = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "actedBy")
    private java.util.List<ClaimActionHistory> claimActions = new java.util.ArrayList<>();

    public java.util.List<SupportResponse> getResponses() { return responses; }
    public void setResponses(java.util.List<SupportResponse> responses) { this.responses = responses; }

    public java.util.List<ClaimActionHistory> getClaimActions() { return claimActions; }
    public void setClaimActions(java.util.List<ClaimActionHistory> claimActions) { this.claimActions = claimActions; }
}
