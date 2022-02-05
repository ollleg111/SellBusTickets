package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Table(name = "USERS")
@Getter
@Setter
@ToString
public class User {
    @Id
    @SequenceGenerator(
            name = "USERS_SEQ",
            sequenceName = "USERS_SEQ",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USERS_SEQ"
    )
    private Long id;

    @NotEmpty(message = "First Name should not be empty")
    @Size(min = 10, max = 15, message = "First Name should be between 2 and 15 characters")
    @Column(name = "FIRST_NAME", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 10, max = 15, message = "Last Name should be between 2 and 15 characters")
    @Column(name = "LAST_NAME", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 10, max = 15, message = "Phone Number should be between 10 and 15 characters")
    @Column(name = "PHONE_NUMBER", nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @NotEmpty(message = "E_MAIL should not be empty")
    @Email
    @Column(name = "E_MAIL", nullable = false, columnDefinition = "TEXT")
    private String mail;

    @NotEmpty(message = "Password number should not be empty")
    @Size(min = 5, max = 15, message = "Password should be between 5 and 15 characters")
    @Column(name = "PASSWORD", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_STATUS")
    private UserStatus userStatus;

//    @OneToOne(cascade = CascadeType.REMOVE)
//    @Column(name = "PAYMENT_ID")
//    private Payment payment;
}

/*
                       id numeric,
                       CONSTRAINT user_pk PRIMARY KEY (id),
                       first_name varchar(50) NOT NULL,
                       last_name varchar(50) NOT NULL,
                       phone_number varchar(20) NOT NULL,
                       e_mail varchar(50) NOT NULL,
                       password varchar(50) NOT NULL,
                       user_status varchar(5) default 'USER',
                       check (user_status = 'ADMIN' or user_status = 'USER')
 */
