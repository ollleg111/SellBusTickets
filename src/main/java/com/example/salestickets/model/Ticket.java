package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_STATUS")
    private TicketStatus ticketStatus;
}

/*
                         id numeric,
                         CONSTRAINT ticket_pk PRIMARY KEY (id),
                         trip_id numeric NOT NULL,
                         CONSTRAINT TRIP_FK FOREIGN KEY (TRIP_ID) REFERENCES TRIPS(ID),
                         payment_id numeric NOT NULL,
                         CONSTRAINT PAYMENT_FK FOREIGN KEY (PAYMENT_ID) REFERENCES PAYMENTS(ID),
                         ticket_status varchar (6),
                         check(ticket_status IN('NEW','FAILED','DONE'))
 */
