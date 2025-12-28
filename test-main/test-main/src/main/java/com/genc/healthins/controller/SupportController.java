package com.genc.healthins.controller;

import com.genc.healthins.dto.SupportTicketDTO;
import com.genc.healthins.entity.SupportTicket;
import com.genc.healthins.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.util.Optional;

@Controller
@RequestMapping("/api/tickets")
public class SupportController {

    @Autowired private SupportService supportService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createTicket(@RequestBody SupportTicketDTO dto){
        SupportTicket ticket = new SupportTicket();
        if(dto.getUserId() != null) ticket.setUser(new com.genc.healthins.entity.User() {{ setUserId(dto.getUserId()); }});
        ticket.setIssueDescription(dto.getIssueDescription());
        SupportTicket t = supportService.createTicket(ticket);
        SupportTicketDTO out = new SupportTicketDTO(); out.setTicketId(t.getTicketId()); out.setUserId(t.getUser().getUserId()); out.setIssueDescription(t.getIssueDescription()); out.setTicketStatus(t.getTicketStatus()); out.setCreatedDate(t.getCreatedDate()); out.setResolvedDate(t.getResolvedDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getTicketDetails(@PathVariable Integer id){
        Optional<SupportTicket> opt = supportService.getTicketById(id);
        return opt.map(t -> {
            SupportTicketDTO d = new SupportTicketDTO(); d.setTicketId(t.getTicketId()); d.setUserId(t.getUser().getUserId()); d.setIssueDescription(t.getIssueDescription()); d.setTicketStatus(t.getTicketStatus()); d.setCreatedDate(t.getCreatedDate()); d.setResolvedDate(t.getResolvedDate());
            return ResponseEntity.ok(d);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/resolve")
    @ResponseBody
    public ResponseEntity<?> resolveTicket(@PathVariable Integer id){
        SupportTicket t = supportService.resolveTicket(id);
        if(t == null) return ResponseEntity.notFound().build();
        SupportTicketDTO d = new SupportTicketDTO(); d.setTicketId(t.getTicketId()); d.setUserId(t.getUser().getUserId()); d.setIssueDescription(t.getIssueDescription()); d.setTicketStatus(t.getTicketStatus()); d.setCreatedDate(t.getCreatedDate()); d.setResolvedDate(t.getResolvedDate());
        return ResponseEntity.ok(d);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ResponseBody
    public ResponseEntity<java.util.List<com.genc.healthins.dto.SupportTicketDTO>> getAllTickets(){
        java.util.List<SupportTicket> list = supportService.getAllTickets();
        java.util.List<SupportTicketDTO> dto = list.stream().map(t -> { SupportTicketDTO d = new SupportTicketDTO(); d.setTicketId(t.getTicketId()); d.setUserId(t.getUser().getUserId()); d.setIssueDescription(t.getIssueDescription()); d.setTicketStatus(t.getTicketStatus()); d.setCreatedDate(t.getCreatedDate()); d.setResolvedDate(t.getResolvedDate()); return d; }).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    // tickets for current user
    @GetMapping("/mine")
    @ResponseBody
    public ResponseEntity<java.util.List<com.genc.healthins.dto.SupportTicketDTO>> myTickets(jakarta.servlet.http.HttpSession session){
        Object obj = session.getAttribute("user");
        if(obj == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        com.genc.healthins.entity.User u = (com.genc.healthins.entity.User)obj;
        java.util.List<SupportTicket> list = supportService.getTicketsByUser(u.getUserId());
        java.util.List<SupportTicketDTO> dto = list.stream().map(t -> { SupportTicketDTO d = new SupportTicketDTO(); d.setTicketId(t.getTicketId()); d.setUserId(t.getUser().getUserId()); d.setIssueDescription(t.getIssueDescription()); d.setTicketStatus(t.getTicketStatus()); d.setCreatedDate(t.getCreatedDate()); d.setResolvedDate(t.getResolvedDate()); return d; }).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }
}
