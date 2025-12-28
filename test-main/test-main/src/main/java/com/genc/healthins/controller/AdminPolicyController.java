package com.genc.healthins.controller;

import com.genc.healthins.dao.PolicyRepository;
import com.genc.healthins.dao.UserRepository;
import com.genc.healthins.entity.Policy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/policies")
public class AdminPolicyController {
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    public AdminPolicyController(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model) {
        List<Policy> policies = policyRepository.findAll();
        model.addAttribute("policies", policies);
        return "admin/policies";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("users", userRepository.findAll());
        return "admin/policy-form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        Objects.requireNonNull(id, "id must not be null");
        Policy policy = policyRepository.findById(id).orElse(null);
        if (policy == null) {
            ra.addFlashAttribute("error", "Policy not found");
            return "redirect:/admin/policies";
        }
        model.addAttribute("policy", policy);
        model.addAttribute("users", userRepository.findAll());
        return "admin/policy-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Policy policy, RedirectAttributes ra) {
        // check unique policy number
        policyRepository.findByPolicyNumber(policy.getPolicyNumber()).ifPresent(existing -> {
            if (policy.getPolicyId() == null || !existing.getPolicyId().equals(policy.getPolicyId())) {
                throw new IllegalArgumentException("Policy number already exists");
            }
        });

        policyRepository.save(policy);
        ra.addFlashAttribute("success", "Policy saved successfully");
        return "redirect:/admin/policies";
    }
}
