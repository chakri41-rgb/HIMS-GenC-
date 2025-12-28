package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Integer policyId;

    @Column(name = "policy_number", nullable=false, unique=true)
    private String policyNumber;

    @Column(name = "coverage_type")
    private String coverageType;

    @Column(name = "coverage_amount")
    private BigDecimal coverageAmount;

    @Column(name = "premium_amount")
    private BigDecimal premiumAmount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<PolicyRenewal> renewals = new java.util.ArrayList<>();

    public Policy(){}

    @PrePersist
    protected void onCreate(){
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = java.time.LocalDateTime.now();
    }

    // getters & setters
    public Integer getPolicyId(){ return policyId; }
    public void setPolicyId(Integer id){ this.policyId = id; }

    public String getPolicyNumber(){ return policyNumber; }
    public void setPolicyNumber(String n){ this.policyNumber = n; }

    public String getCoverageType(){ return coverageType; }
    public void setCoverageType(String c){ this.coverageType = c; }

    public BigDecimal getCoverageAmount(){ return coverageAmount; }
    public void setCoverageAmount(BigDecimal a){ this.coverageAmount = a; }

    public BigDecimal getPremiumAmount(){ return premiumAmount; }
    public void setPremiumAmount(BigDecimal p){ this.premiumAmount = p; }

    public LocalDate getStartDate(){ return startDate; }
    public void setStartDate(LocalDate d){ this.startDate = d; }

    public LocalDate getEndDate(){ return endDate; }
    public void setEndDate(LocalDate d){ this.endDate = d; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public PolicyStatus getPolicyStatus(){ return policyStatus; }
    public void setPolicyStatus(PolicyStatus s){ this.policyStatus = s; }

    public java.util.List<PolicyRenewal> getRenewals() { return renewals; }
    public void setRenewals(java.util.List<PolicyRenewal> renewals) { this.renewals = renewals; }
}
