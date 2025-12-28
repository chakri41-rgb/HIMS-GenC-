package com.genc.healthins.service;

import com.genc.healthins.dao.ClaimRepository;
import com.genc.healthins.entity.Claim;
import com.genc.healthins.entity.ClaimStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired private ClaimRepository claimRepository;

    @Override
    public Claim submitClaim(Claim claim) {
        claim.setClaimStatus(ClaimStatus.OPEN);
        return claimRepository.save(claim);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Claim> getClaimById(Integer id) {
        return claimRepository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Claim updateClaimStatus(Integer id, String status) {
        Optional<Claim> opt = claimRepository.findById(id);
        if(opt.isEmpty()) return null;
        Claim c = opt.get();
        c.setClaimStatus(ClaimStatus.valueOf(status));
        return claimRepository.save(c);
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
