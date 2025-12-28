package com.genc.healthins.controller;

import com.genc.healthins.entity.Policy;
import com.genc.healthins.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // Keep security if needed
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // <--- Note: This is @Controller, NOT @RestController
@RequestMapping("/policies")
public class PolicyWebController {

    @Autowired
    private PolicyService policyService;

    // 1. Show the list of policies (Corresponds to policies.html)
    @GetMapping
    public String viewPoliciesPage() {
        // Redirect to static single-page listing which uses JS + REST API
        return "redirect:/user/policies.html";
    }

    // 2. Show the "Create Policy" Form
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String showCreateForm() {
        return "redirect:/admin/policies.html";
    }

    // 3. Handle the Form Submission (Save Policy)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String savePolicy(@ModelAttribute("policy") Policy policy) {
        policyService.createPolicy(policy);
        return "redirect:/admin/policies.html"; // Redirect back to admin UI
    }

    // 4. Delete Policy
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deletePolicy(@PathVariable Integer id) {
        policyService.deletePolicy(id);
        return "redirect:/admin/policies.html";
    }
}