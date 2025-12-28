package com.genc.healthins.controller;

import com.genc.healthins.dao.PolicyRepository;
import com.genc.healthins.entity.Policy;
import com.genc.healthins.entity.PolicyStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/agent/policies")
public class PolicyController {

    @Autowired
    private final PolicyRepository policyRepository;

    public PolicyController(PolicyRepository policyRepository){
        this.policyRepository = policyRepository;
    }

    @GetMapping
    public String listPolicies(@RequestParam(name = "status", required = false, defaultValue = "all") String status, Model model){
        List<Policy> policies;
        if("all".equalsIgnoreCase(status)){
            policies = policyRepository.findAll();
        } else {
            PolicyStatus s;
            try{
                s = PolicyStatus.valueOf(status.toUpperCase());
                policies = policyRepository.findByPolicyStatus(s);
            } catch (IllegalArgumentException e){
                policies = policyRepository.findAll();
            }
        }
        model.addAttribute("policies", policies);
        model.addAttribute("selectedStatus", status);
        return "policies"; // Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String viewPolicy(@PathVariable("id") Integer id, Model model){
        Objects.requireNonNull(id, "id must not be null");
        Policy policy = policyRepository.findById(id).orElse(null);
        model.addAttribute("policy", policy);
        return "policy-view";
    }
}
