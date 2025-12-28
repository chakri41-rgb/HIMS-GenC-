package com.genc.healthins.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.healthins.entity.PolicyRenewal;

public interface PolicyRenewalRepository extends JpaRepository<PolicyRenewal, Long> {
}
