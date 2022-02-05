CREATE SEQUENCE USERS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE PAYMENTS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE TICKETS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE TRIP_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

CREATE TABLE users (
                       id numeric,
                       CONSTRAINT user_pk PRIMARY KEY (id),
                       first_name varchar(50) NOT NULL,
                       last_name varchar(50) NOT NULL,
                       phone_number varchar(20) NOT NULL,
                       e_mail varchar(50) NOT NULL,
                       password varchar(50) NOT NULL,
                       user_status varchar(5) default 'USER',
                       check (user_status = 'ADMIN' or user_status = 'USER')
);

CREATE TABLE trips (
                       id numeric,
                       CONSTRAINT trip_pk PRIMARY KEY (id),
                       place_from varchar(50) not null,
                       place_to varchar(50) not null,
                       departure_date TIMESTAMP,
                       cost numeric not null,
                       quantity numeric
);

CREATE TABLE payments (
                          id numeric,
                          CONSTRAINT payment_pk PRIMARY KEY (id),
                          user_id numeric NOT NULL,
                          CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
                          trip_id numeric NOT NULL,
                          CONSTRAINT TRIP_FK FOREIGN KEY (TRIP_ID) REFERENCES TRIPS(ID),
                          due_date TIMESTAMP
);

CREATE TABLE tickets (
                         id numeric,
                         CONSTRAINT ticket_pk PRIMARY KEY (id),
                         trip_id numeric NOT NULL,
                         CONSTRAINT TRIP_FK FOREIGN KEY (TRIP_ID) REFERENCES TRIPS(ID),
                         payment_id numeric NOT NULL,
                         CONSTRAINT PAYMENT_FK FOREIGN KEY (PAYMENT_ID) REFERENCES PAYMENTS(ID),
                         ticket_status varchar (6),
                         check(ticket_status IN('NEW','FAILED','DONE'))
);

drop table users;
drop table payments;
drop table tickets;
drop table trips;