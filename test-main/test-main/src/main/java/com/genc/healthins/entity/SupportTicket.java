package com.genc.healthins.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "support_ticket")
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(name = "issue_description")
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "resolved_date")
    private LocalDate resolvedDate;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<SupportResponse> responses = new java.util.ArrayList<>();

    public SupportTicket(){}

    // getters & setters
    public Integer getTicketId(){ return ticketId; }
    public void setTicketId(Integer id){ this.ticketId = id; }

    public User getUser(){ return user; }
    public void setUser(User u){ this.user = u; }

    public String getIssueDescription(){ return issueDescription; }
    public void setIssueDescription(String d){ this.issueDescription = d; }

    public TicketStatus getTicketStatus(){ return ticketStatus; }
    public void setTicketStatus(TicketStatus s){ this.ticketStatus = s; }

    public LocalDate getCreatedDate(){ return createdDate; }
    public void setCreatedDate(LocalDate d){ this.createdDate = d; }

    public LocalDate getResolvedDate(){ return resolvedDate; }
    public void setResolvedDate(LocalDate d){ this.resolvedDate = d; }

    public java.util.List<SupportResponse> getResponses() { return responses; }
    public void setResponses(java.util.List<SupportResponse> responses) { this.responses = responses; }
}
