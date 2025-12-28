package com.genc.healthins.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.healthins.entity.SupportTicket;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Integer> {
    List<SupportTicket> findByUser_UserId(Integer userId);
}
    