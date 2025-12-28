package com.genc.healthins.service;

import com.genc.healthins.entity.Policy;
import java.util.List;
import java.util.Optional;

public interface PolicyService {
    Policy createPolicy(Policy policy);
    Policy updatePolicy(Integer id, Policy policy);
    Optional<Policy> getPolicyById(Integer id);
    List<Policy> getAllPolicies();
    void deletePolicy(Integer id);
}
