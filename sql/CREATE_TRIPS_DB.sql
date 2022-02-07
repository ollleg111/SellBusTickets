
CREATE SEQUENCE TRIPS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

CREATE TABLE trips (
                       id numeric,
                       CONSTRAINT trip_pk PRIMARY KEY (id),
                       place_from varchar(50) NOT NULL,
                       place_to varchar(50) NOT NULL,
                       departure_date TIMESTAMP,
                       cost numeric NOT NULL UNIQUE,
                       quantity numeric
);