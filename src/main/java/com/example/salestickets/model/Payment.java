package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "payments_tickets",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Payment> paymentList;
}