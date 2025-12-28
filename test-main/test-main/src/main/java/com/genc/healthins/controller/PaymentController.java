package com.genc.healthins.controller;

import com.genc.healthins.dto.PaymentDTO;
import com.genc.healthins.entity.Payment;
import com.genc.healthins.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.util.Optional;

@Controller
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired private PaymentService paymentService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> makePayment(@RequestBody PaymentDTO dto){
        Payment payment = new Payment();
        payment.setPolicy(dto.getPolicyId() == null ? null : new com.genc.healthins.entity.Policy() {{ setPolicyId(dto.getPolicyId()); }});
        payment.setPaymentAmount(dto.getPaymentAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        Payment p = paymentService.makePayment(payment);
        PaymentDTO out = new PaymentDTO(); out.setPaymentId(p.getPaymentId()); out.setPolicyId(p.getPolicy().getPolicyId()); out.setPaymentAmount(p.getPaymentAmount()); out.setPaymentDate(p.getPaymentDate()); out.setPaymentStatus(p.getPaymentStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getPaymentDetails(@PathVariable Integer id){
        Optional<Payment> opt = paymentService.getPaymentById(id);
        return opt.map(p -> {
            PaymentDTO d = new PaymentDTO(); d.setPaymentId(p.getPaymentId()); d.setPolicyId(p.getPolicy().getPolicyId()); d.setPaymentAmount(p.getPaymentAmount()); d.setPaymentDate(p.getPaymentDate()); d.setPaymentStatus(p.getPaymentStatus());
            return ResponseEntity.ok(d);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/policy/{policyId}")
    @ResponseBody
    public ResponseEntity<java.util.List<PaymentDTO>> getPaymentsByPolicy(@PathVariable Integer policyId){
        java.util.List<Payment> list = paymentService.getPaymentsByPolicy(policyId);
        java.util.List<PaymentDTO> dto = list.stream().map(p -> { PaymentDTO d = new PaymentDTO(); d.setPaymentId(p.getPaymentId()); d.setPolicyId(p.getPolicy().getPolicyId()); d.setPaymentAmount(p.getPaymentAmount()); d.setPaymentDate(p.getPaymentDate()); d.setPaymentStatus(p.getPaymentStatus()); return d; }).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ResponseBody
    public ResponseEntity<java.util.List<PaymentDTO>> getAllPayments(){
        java.util.List<Payment> list = paymentService.getAllPayments();
        java.util.List<PaymentDTO> dto = list.stream().map(p -> { PaymentDTO d = new PaymentDTO(); d.setPaymentId(p.getPaymentId()); d.setPolicyId(p.getPolicy().getPolicyId()); d.setPaymentAmount(p.getPaymentAmount()); d.setPaymentDate(p.getPaymentDate()); d.setPaymentStatus(p.getPaymentStatus()); return d; }).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(dto);
    }
}
