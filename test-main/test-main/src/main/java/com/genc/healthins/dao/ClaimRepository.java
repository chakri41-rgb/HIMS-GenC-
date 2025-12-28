package com.genc.healthins.dao;

import com.genc.healthins.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
    List<Claim> findByPolicy_PolicyId(Integer policyId);
}
