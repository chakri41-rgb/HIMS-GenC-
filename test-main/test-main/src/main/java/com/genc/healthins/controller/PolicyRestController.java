package com.genc.healthins.controller;

import com.genc.healthins.dto.PolicyDTO;
import com.genc.healthins.exception.ResourceNotFoundException;
import com.genc.healthins.entity.Policy;
import com.genc.healthins.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policies")
public class PolicyRestController {

    @Autowired private PolicyService policyService;

    @GetMapping
    public List<PolicyDTO> listPolicies(){
        return policyService.getAllPolicies().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPolicy(@PathVariable Integer id){
        Policy p = policyService.getPolicyById(id).orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
        return ResponseEntity.ok(toDTO(p));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PolicyDTO dto){
        Policy p = fromDTO(dto);
        Policy created = policyService.createPolicy(p);
        return ResponseEntity.status(201).body(toDTO(created));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PolicyDTO dto){
        Policy p = fromDTO(dto);
        Policy updated = policyService.updatePolicy(id, p);
        if(updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDTO(updated));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    private PolicyDTO toDTO(Policy p){
        PolicyDTO d = new PolicyDTO();
        d.setPolicyId(p.getPolicyId());
        d.setPolicyNumber(p.getPolicyNumber());
        d.setCoverageType(p.getCoverageType());
        d.setCoverageAmount(p.getCoverageAmount());
        d.setPremiumAmount(p.getPremiumAmount());
        d.setStartDate(p.getStartDate());
        d.setEndDate(p.getEndDate());
        d.setPolicyStatus(p.getPolicyStatus());
        return d;
    }

    private Policy fromDTO(PolicyDTO d){
        Policy p = new Policy();
        p.setPolicyNumber(d.getPolicyNumber());
        p.setCoverageType(d.getCoverageType());
        p.setCoverageAmount(d.getCoverageAmount());
        p.setPremiumAmount(d.getPremiumAmount());
        p.setStartDate(d.getStartDate());
        p.setEndDate(d.getEndDate());
        p.setPolicyStatus(d.getPolicyStatus());
        return p;
    }
}
