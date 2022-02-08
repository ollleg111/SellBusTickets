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

    //TODO добавить поле через Date payment_date = new Date()
    /*
    @DateTimeFormat
    @Column(name = "PAYMENT_DATE")
    private Date payment_date;
     */

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "payments_tickets",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Payment> paymentList;
}