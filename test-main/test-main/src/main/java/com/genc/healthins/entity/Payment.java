package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    public Payment(){}

    // getters & setters
    public Integer getPaymentId(){ return paymentId; }
    public void setPaymentId(Integer id){ this.paymentId = id; }

    public Policy getPolicy(){ return policy; }
    public void setPolicy(Policy p){ this.policy = p; }

    public BigDecimal getPaymentAmount(){ return paymentAmount; }
    public void setPaymentAmount(BigDecimal a){ this.paymentAmount = a; }

    public LocalDate getPaymentDate(){ return paymentDate; }
    public void setPaymentDate(LocalDate d){ this.paymentDate = d; }

    public PaymentStatus getPaymentStatus(){ return paymentStatus; }
    public void setPaymentStatus(PaymentStatus s){ this.paymentStatus = s; }
}
