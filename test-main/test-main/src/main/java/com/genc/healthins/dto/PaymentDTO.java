package com.genc.healthins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.genc.healthins.entity.PaymentStatus;

public class PaymentDTO {
    private Integer paymentId;
    private Integer policyId;
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private PaymentStatus paymentStatus;

    public Integer getPaymentId() { return paymentId; }
    public void setPaymentId(Integer paymentId) { this.paymentId = paymentId; }
    public Integer getPolicyId() { return policyId; }
    public void setPolicyId(Integer policyId) { this.policyId = policyId; }
    public BigDecimal getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(BigDecimal paymentAmount) { this.paymentAmount = paymentAmount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
}
