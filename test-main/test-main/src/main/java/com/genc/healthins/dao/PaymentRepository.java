package com.genc.healthins.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.healthins.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByPolicy_PolicyId(Integer policyId);
}
