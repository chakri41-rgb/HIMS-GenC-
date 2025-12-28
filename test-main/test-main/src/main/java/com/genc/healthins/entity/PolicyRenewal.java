package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "policy_renewal")
public class PolicyRenewal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @Column(name = "renewal_due_date")
    private LocalDate renewalDueDate;

    @Column(name = "renewed_at")
    private LocalDateTime renewedAt;

    @Column(length = 32, nullable = false)
    private String status; // e.g. PENDING, RENEWED, OVERDUE, CANCELLED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public PolicyRenewal() {}

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }

    public LocalDate getRenewalDueDate() { return renewalDueDate; }
    public void setRenewalDueDate(LocalDate renewalDueDate) { this.renewalDueDate = renewalDueDate; }

    public LocalDateTime getRenewedAt() { return renewedAt; }
    public void setRenewedAt(LocalDateTime renewedAt) { this.renewedAt = renewedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
