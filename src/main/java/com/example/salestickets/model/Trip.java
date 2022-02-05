package com.example.salestickets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "trips")
@Table(name = "TRIPS")
@Getter
@Setter
@ToString
public class Trip {
    @Id
    @SequenceGenerator(
            name = "TRIPS_SEQ",
            sequenceName = "TRIPS_SEQ",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TRIPS_SEQ"
    )
    private Long id;

    @NotEmpty(message = "Starting city name should not be empty")
    @Size(min = 10, max = 15, message = "Starting city name should be between 3 and 15 characters")
    @Column(name = "PLACE_FROM", nullable = false, columnDefinition = "TEXT")
    private String place_from;

    @NotEmpty(message = "Finish city name should not be empty")
    @Size(min = 10, max = 15, message = "Finish city name should be between 3 and 15 characters")
    @Column(name = "PLACE_TO", nullable = false, columnDefinition = "TEXT")
    private String place_to;

    @DateTimeFormat
    @Column(name = "DEPARTURE_DATE")
    private Date departure_date;

    @NotEmpty(message = "Cost should not be empty")
    @Min(value = 0, message = "Cost should be greater than 0")
    @Column(name = "COST", nullable = false)
    private Long cost;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;
}

/*
                       id numeric,
                       CONSTRAINT trip_pk PRIMARY KEY (id),
                       place_from varchar(50) not null,
                       place_to varchar(50) not null,
                       departure_date TIMESTAMP,
                       cost numeric not null,
                       quantity numeric
 */
