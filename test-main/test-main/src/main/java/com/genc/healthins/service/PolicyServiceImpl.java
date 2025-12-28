package com.genc.healthins.service;

import com.genc.healthins.dao.PolicyRepository;
import com.genc.healthins.entity.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    @SuppressWarnings("null")
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    @SuppressWarnings("null")
    public Policy updatePolicy(Integer id, Policy policy) {
        Optional<Policy> opt = policyRepository.findById(id);
        if(opt.isEmpty()) return null;
        Policy existing = opt.get();
        existing.setPolicyNumber(policy.getPolicyNumber());
        existing.setCoverageType(policy.getCoverageType());
        existing.setCoverageAmount(policy.getCoverageAmount());
        existing.setPremiumAmount(policy.getPremiumAmount());
        existing.setStartDate(policy.getStartDate());
        existing.setEndDate(policy.getEndDate());
        existing.setPolicyStatus(policy.getPolicyStatus());
        return policyRepository.save(existing);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Policy> getPolicyById(Integer id) {
        return policyRepository.findById(id);
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public void deletePolicy(Integer id) {
        policyRepository.deleteById(id);
    }
}
