package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity(name = "tickets")
@Table(name = "TICKETS")
@Getter
@Setter
@ToString
public class Ticket {
    @Id
    @SequenceGenerator(
            name = "TICKETS_SEQ",
            sequenceName = "TICKETS_SEQ",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TICKETS_SEQ"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "payments_tickets",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private List<Ticket> ticketList;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_STATUS")
    private TicketStatus ticketStatus;
}

