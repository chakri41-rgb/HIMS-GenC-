package com.genc.healthins.controller;

import com.genc.healthins.dto.ClaimDTO;
import com.genc.healthins.entity.Claim;
import com.genc.healthins.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired private ClaimService claimService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> submitClaim(@RequestBody ClaimDTO dto){
        Claim claim = new Claim();
        claim.setPolicy(dto.getPolicyId() == null ? null : new com.genc.healthins.entity.Policy() {{ setPolicyId(dto.getPolicyId()); }});
        claim.setClaimAmount(dto.getClaimAmount());
        claim.setClaimDate(dto.getClaimDate());
        Claim c = claimService.submitClaim(claim);
        ClaimDTO out = new ClaimDTO();
        out.setClaimId(c.getClaimId()); out.setPolicyId(c.getPolicy().getPolicyId()); out.setClaimAmount(c.getClaimAmount()); out.setClaimDate(c.getClaimDate()); out.setClaimStatus(c.getClaimStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getClaimDetails(@PathVariable Integer id){
        Optional<Claim> opt = claimService.getClaimById(id);
        return opt.map(c -> {
            ClaimDTO out = new ClaimDTO();
            out.setClaimId(c.getClaimId()); out.setPolicyId(c.getPolicy().getPolicyId()); out.setClaimAmount(c.getClaimAmount()); out.setClaimDate(c.getClaimDate()); out.setClaimStatus(c.getClaimStatus());
            return ResponseEntity.ok(out);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/status")
    @ResponseBody
    public ResponseEntity<?> updateClaimStatus(@PathVariable Integer id, @RequestParam String status){
        Claim c = claimService.updateClaimStatus(id, status);
        if(c == null) return ResponseEntity.notFound().build();
        ClaimDTO out = new ClaimDTO();
        out.setClaimId(c.getClaimId()); out.setPolicyId(c.getPolicy().getPolicyId()); out.setClaimAmount(c.getClaimAmount()); out.setClaimDate(c.getClaimDate()); out.setClaimStatus(c.getClaimStatus());
        return ResponseEntity.ok(out);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ResponseBody
    public ResponseEntity<java.util.List<com.genc.healthins.dto.ClaimDTO>> getAllClaims(){
        java.util.List<Claim> list = claimService.getAllClaims();
        java.util.List<ClaimDTO> dto = list.stream().map(c -> {
            ClaimDTO d = new ClaimDTO(); d.setClaimId(c.getClaimId()); d.setPolicyId(c.getPolicy().getPolicyId()); d.setClaimAmount(c.getClaimAmount()); d.setClaimDate(c.getClaimDate()); d.setClaimStatus(c.getClaimStatus()); return d;
        }).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }
}
