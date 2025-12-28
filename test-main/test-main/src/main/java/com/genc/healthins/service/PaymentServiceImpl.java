package com.genc.healthins.service;

import com.genc.healthins.dao.PaymentRepository;
import com.genc.healthins.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(Payment payment) {
        // In real app, integrate gateway. For now, mark as SUCCESS
        payment.setPaymentStatus(payment.getPaymentStatus() == null ? 
            com.genc.healthins.entity.PaymentStatus.SUCCESS : payment.getPaymentStatus());
        return paymentRepository.save(payment);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsByPolicy(Integer policyId) {
        return paymentRepository.findByPolicy_PolicyId(policyId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
