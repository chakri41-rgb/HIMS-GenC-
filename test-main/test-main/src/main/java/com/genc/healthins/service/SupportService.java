package com.genc.healthins.service;

import com.genc.healthins.entity.SupportTicket;

import java.util.List;
import java.util.Optional;

public interface SupportService {
    SupportTicket createTicket(SupportTicket ticket);
    Optional<SupportTicket> getTicketById(Integer id);
    SupportTicket resolveTicket(Integer id);
    List<SupportTicket> getAllTickets();
    List<SupportTicket> getTicketsByUser(Integer userId);
}
