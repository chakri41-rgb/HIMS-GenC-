package com.genc.healthins.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.healthins.entity.Policy;
import com.genc.healthins.entity.PolicyStatus;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    Optional<Policy> findByPolicyNumber(String policyNumber);
    List<Policy> findByPolicyStatus(PolicyStatus status);
    List<Policy> findByUser_UserId(Integer userId);
}
