package com.genc.healthins.service;

import com.genc.healthins.entity.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment makePayment(Payment payment);
    Optional<Payment> getPaymentById(Integer id);
    List<Payment> getPaymentsByPolicy(Integer policyId);
    List<Payment> getAllPayments();
}
