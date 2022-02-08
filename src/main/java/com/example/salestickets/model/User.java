package com.example.salestickets.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor

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

    @OneToOne(cascade = CascadeType.REMOVE)
    private Payment payment;

    public User(String firstName, String lastName, String phoneNumber, String mail, String password, UserStatus userStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
        this.userStatus = userStatus;
    }
}