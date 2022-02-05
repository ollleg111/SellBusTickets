package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "payments")
@Table(name = "PAYMENTS")
@Getter
@Setter
@ToString
public class Payment {
    @Id
    @SequenceGenerator(
            name = "PAYMENTS_SEQ",
            sequenceName = "PAYMENTS_SEQ",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PAYMENTS_SEQ"
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;


//    @ManyToMany
//    @JoinTable(name = "TRIPS_TICKETS", joinColumns = @JoinColumn(name = "trip_id"),
//        inverseJoinColumns = @JoinColumn(name = "ticket_id"))
//    private List<Ticket> ticket;

//    @DateTimeFormat
//    @Column(name = "DUE_DATE")
//    private Date due_date;

}

/*
                          id numeric,
                          CONSTRAINT payment_pk PRIMARY KEY (id),
                          user_id numeric NOT NULL,
                          CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
                          trip_id numeric NOT NULL,
                          CONSTRAINT TRIP_FK FOREIGN KEY (TRIP_ID) REFERENCES TRIPS(ID),
                          due_date TIMESTAMP
 */