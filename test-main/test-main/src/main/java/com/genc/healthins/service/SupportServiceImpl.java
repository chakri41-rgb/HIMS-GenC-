package com.genc.healthins.service;

import com.genc.healthins.dao.SupportTicketRepository;
import com.genc.healthins.entity.SupportTicket;
import com.genc.healthins.entity.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SupportServiceImpl implements SupportService {

    @Autowired private SupportTicketRepository ticketRepository;

    @Override
    public SupportTicket createTicket(SupportTicket ticket) {
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCreatedDate(LocalDate.now());
        return ticketRepository.save(ticket);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<SupportTicket> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public SupportTicket resolveTicket(Integer id) {
        Optional<SupportTicket> opt = ticketRepository.findById(id);
        if(opt.isEmpty()) return null;
        SupportTicket t = opt.get();
        t.setTicketStatus(TicketStatus.RESOLVED);
        t.setResolvedDate(LocalDate.now());
        return ticketRepository.save(t);
    }

    @Override
    public List<SupportTicket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<SupportTicket> getTicketsByUser(Integer userId) {
        return ticketRepository.findByUser_UserId(userId);
    }
}
