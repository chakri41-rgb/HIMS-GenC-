package com.genc.healthins.config;

import com.genc.healthins.dao.UserRepository;
import com.genc.healthins.entity.Role;
import com.genc.healthins.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataLoader implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(AdminDataLoader.class);

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private com.genc.healthins.dao.PolicyRepository policyRepository;

    @Override
    public void run(ApplicationArguments args) {
        // create a default admin if none exists
        String adminUsername = "admin@hims.com";
        if(userRepository.findByUsername(adminUsername).isEmpty()){
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setEmail("admin@hims.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            log.info("Created default admin user '{}' with password 'admin123'", adminUsername);
        } else {
            log.info("Admin user '{}' already exists", adminUsername);
        }

        // Seed sample policies if none exist
        if(policyRepository.count() == 0){
            java.util.List<com.genc.healthins.entity.Policy> samples = new java.util.ArrayList<>();

            com.genc.healthins.entity.Policy p1 = new com.genc.healthins.entity.Policy();
            p1.setPolicyNumber("POL-2025-001");
            p1.setCoverageType("Health");
            p1.setCoverageAmount(new java.math.BigDecimal("50000"));
            p1.setPremiumAmount(new java.math.BigDecimal("1200"));
            p1.setStartDate(java.time.LocalDate.now().minusMonths(1));
            p1.setEndDate(java.time.LocalDate.now().plusYears(1));
            p1.setPolicyStatus(com.genc.healthins.entity.PolicyStatus.ACTIVE);
            samples.add(p1);

            com.genc.healthins.entity.Policy p2 = new com.genc.healthins.entity.Policy();
            p2.setPolicyNumber("POL-2025-002");
            p2.setCoverageType("Dental");
            p2.setCoverageAmount(new java.math.BigDecimal("10000"));
            p2.setPremiumAmount(new java.math.BigDecimal("300"));
            p2.setStartDate(java.time.LocalDate.now().minusMonths(2));
            p2.setEndDate(java.time.LocalDate.now().plusMonths(10));
            p2.setPolicyStatus(com.genc.healthins.entity.PolicyStatus.ACTIVE);
            samples.add(p2);

            com.genc.healthins.entity.Policy p3 = new com.genc.healthins.entity.Policy();
            p3.setPolicyNumber("POL-2025-003");
            p3.setCoverageType("Vision");
            p3.setCoverageAmount(new java.math.BigDecimal("5000"));
            p3.setPremiumAmount(new java.math.BigDecimal("100"));
            p3.setStartDate(java.time.LocalDate.now());
            p3.setEndDate(java.time.LocalDate.now().plusYears(1));
            p3.setPolicyStatus(com.genc.healthins.entity.PolicyStatus.ACTIVE);
            samples.add(p3);

            policyRepository.saveAll(samples);
            log.info("Seeded {} sample policies", samples.size());
        }
    }
}
