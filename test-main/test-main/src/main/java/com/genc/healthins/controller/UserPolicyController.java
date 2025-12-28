package com.genc.healthins.controller;

import com.genc.healthins.dao.PolicyRepository;
import com.genc.healthins.entity.Policy;
import com.genc.healthins.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPolicyController {
    private final PolicyRepository policyRepository;

    public UserPolicyController(PolicyRepository policyRepository){
        this.policyRepository = policyRepository;
    }

    @GetMapping("/policies")
    public String myPolicies(HttpSession session, Model model){
        Object obj = session.getAttribute("user");
        if(obj == null) {
            return "redirect:/login.html";
        }
        User u = (User)obj;
        List<Policy> policies = policyRepository.findByUser_UserId(u.getUserId());
        model.addAttribute("policies", policies);
        return "user/policies";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        Object obj = session.getAttribute("user");
        if(obj == null) {
            return "redirect:/login.html";
        }
        User u = (User)obj;
        List<Policy> policies = policyRepository.findByUser_UserId(u.getUserId());
        model.addAttribute("policies", policies);
        model.addAttribute("user", u);
        return "user/dashboard";
    }

    // Support direct access to /user/dashboard.html (some redirects point here) by forwarding to the dashboard handler
    @GetMapping("/dashboard.html")
    public String dashboardHtml(HttpSession session, Model model){
        return dashboard(session, model);
    }
}
