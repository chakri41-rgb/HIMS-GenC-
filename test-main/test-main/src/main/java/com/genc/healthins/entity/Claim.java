package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "claim")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer claim_id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Column(name = "claim_amount")
    private BigDecimal claimAmount;

    @Column(name = "claim_date")
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_status")
    private ClaimStatus claimStatus;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<ClaimActionHistory> actionHistory = new java.util.ArrayList<>();

    public Claim(){}

    // getters & setters
    public Integer getClaimId(){ return claim_id; }
    public void setClaimId(Integer id){ this.claim_id = id; }

    public Policy getPolicy(){ return policy; }
    public void setPolicy(Policy p){ this.policy = p; }

    public BigDecimal getClaimAmount(){ return claimAmount; }
    public void setClaimAmount(BigDecimal a){ this.claimAmount = a; }

    public LocalDate getClaimDate(){ return claimDate; }
    public void setClaimDate(LocalDate d){ this.claimDate = d; }

    public ClaimStatus getClaimStatus(){ return claimStatus; }
    public void setClaimStatus(ClaimStatus s){ this.claimStatus = s; }

    public Integer getDoctorId(){ return doctorId; }
    public void setDoctorId(Integer d){ this.doctorId = d; }

    public java.util.List<ClaimActionHistory> getActionHistory() { return actionHistory; }
    public void setActionHistory(java.util.List<ClaimActionHistory> actionHistory) { this.actionHistory = actionHistory; }
}
