package com.genc.healthins.dao;

import com.genc.healthins.entity.ClaimActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimActionHistoryRepository extends JpaRepository<ClaimActionHistory, Long> {
}
