package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_action_history")
public class ClaimActionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    @ManyToOne
    @JoinColumn(name = "acted_by_user_id")
    private User actedBy;

    @Column(length = 32, nullable = false)
    private String action; // APPROVED, REJECTED, ESCALATED, etc.

    @Lob
    private String comments;

    @Column(name = "action_timestamp")
    private LocalDateTime actionTimestamp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ClaimActionHistory() {}

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        if (this.actionTimestamp == null) this.actionTimestamp = this.createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Claim getClaim() { return claim; }
    public void setClaim(Claim claim) { this.claim = claim; }

    public User getActedBy() { return actedBy; }
    public void setActedBy(User actedBy) { this.actedBy = actedBy; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDateTime getActionTimestamp() { return actionTimestamp; }
    public void setActionTimestamp(LocalDateTime actionTimestamp) { this.actionTimestamp = actionTimestamp; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
