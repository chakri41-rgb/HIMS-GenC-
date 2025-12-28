package com.genc.healthins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.genc.healthins.entity.ClaimStatus;

public class ClaimDTO {
    private Integer claimId;
    private Integer policyId;
    private BigDecimal claimAmount;
    private LocalDate claimDate;
    private ClaimStatus claimStatus;

    public Integer getClaimId() { return claimId; }
    public void setClaimId(Integer claimId) { this.claimId = claimId; }
    public Integer getPolicyId() { return policyId; }
    public void setPolicyId(Integer policyId) { this.policyId = policyId; }
    public BigDecimal getClaimAmount() { return claimAmount; }
    public void setClaimAmount(BigDecimal claimAmount) { this.claimAmount = claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }
    public ClaimStatus getClaimStatus() { return claimStatus; }
    public void setClaimStatus(ClaimStatus claimStatus) { this.claimStatus = claimStatus; }
}
