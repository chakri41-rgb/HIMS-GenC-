package com.genc.healthins.service;

import java.util.List;
import java.util.Optional;

import com.genc.healthins.entity.Claim;

public interface ClaimService {
    Claim submitClaim(Claim claim);
    Optional<Claim> getClaimById(Integer id);
    Claim updateClaimStatus(Integer id, String status);
    List<Claim> getAllClaims();
}
