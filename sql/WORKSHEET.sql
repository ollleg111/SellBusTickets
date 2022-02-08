
<artifactId>spring-boot-starter-data-jpa</artifactId>

drop sequence if exists users_seq
Hibernate: create sequence payments_seq start 1 increment 1
Hibernate: create sequence tickets_seq start 1 increment 1
Hibernate: create sequence trips_seq start 1 increment 1
Hibernate: create sequence users_seq start 1 increment 1
Hibernate:

create table payments (
                          id int8 not null,
                          payment_time timestamp,
                          user_id int8,
                          primary key (id)
)
    Hibernate:

create table payments_tickets (
                                  ticket_id int8 not null,
                                  payment_id int8 not null
)
    Hibernate:

create table tickets (
                         id int8 not null,
                         ticket_status varchar(255),
                         trip_id int8,
                         primary key (id)
)
    Hibernate:

create table trips (
                       id int8 not null,
                       cost int8 not null,
                       departure_date timestamp,
                       place_from TEXT not null,
                       place_to TEXT not null,
                       quantity int8 not null,
                       primary key (id)
)
    Hibernate:

create table users (
                       id int8 not null,
                       first_name TEXT not null,
                       last_name TEXT not null,
                       e_mail TEXT not null,
                       password TEXT not null,
                       phone_number TEXT not null,
                       user_status varchar(255),
                       payment_id int8,
                       primary key (id)
)
    Hibernate:

alter table if exists payments
    add constraint FKj94hgy9v5fw1munb90tar2eje
    foreign key (user_id)
    references users
    Hibernate:

alter table if exists payments_tickets
    add constraint FK786tlr4ywm96o9eirtwkvyufo
    foreign key (payment_id)
    references tickets
    Hibernate:

alter table if exists payments_tickets
    add constraint FK90sinb3fp7chl84puba8q9fuf
    foreign key (ticket_id)
    references tickets
    Hibernate:

alter table if exists tickets
    add constraint FKbcjlnu2low7r5vfimxextqab9
    foreign key (trip_id)
    references trips
    Hibernate:

alter table if exists users
    add constraint FKqats52e29m784k0qytay1qvfp
    foreign key (payment_id)
    references payments

CREATE SEQUENCE USERS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE PAYMENTS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE TICKETS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
CREATE SEQUENCE TRIP_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

drop table users;
drop table tickets;
drop table trips;
drop table payments;
drop table payments_tickets;

insert into USERS (id, first_name, last_name, phone_number, e_mail, password, user_status) values (1, 'Ade', 'Flieger', '948-926-1740', 'aflieger0@scientificamerican.com', 'hDYuEKjFrshe', 'ADMIN');
insert into USERS (id, first_name, last_name, phone_number, e_mail, password, user_status) values (2, 'Taryn', 'Ramalho', '267-278-7268', 'tramalho1@google.it', '9ddHnRusSS', 'USER');
insert into USERS (id, first_name, last_name, phone_number, e_mail, password, user_status) values (3, 'Rubetta', 'Kares', '749-883-1389', 'rkares2@storify.com', '64n7VSRF', 'USER');

insert into TRIPS (id, place_from, place_to, departure_date, cost, quantity) values (1, 'Yanjiang', 'Armopa', '2022-05-04', 94, 35);
insert into TRIPS (id, place_from, place_to, departure_date, cost, quantity) values (2, 'Alicia', 'Usevia', '2022-04-04', 5, 39);
insert into TRIPS (id, place_from, place_to, departure_date, cost, quantity) values (3, 'Cristalina', 'Myski', '2022-06-04', 14, 15);

insert into TICKETS (id, trip_id, ticket_status) values (1, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (2, 2, 'FAILED');
insert into TICKETS (id, trip_id, ticket_status) values (3, 1, 'DONE');
insert into TICKETS (id, trip_id, ticket_status) values (4, 1, 'FAILED');
insert into TICKETS (id, trip_id, ticket_status) values (5, 1, 'DONE');
insert into TICKETS (id, trip_id, ticket_status) values (6, 2, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (7, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (8, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (9, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (10, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (11, 1, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (12, 1, 'NEW');

insert into PAYMENTS (id, user_id, trip_id) values (1, 1, 1);
insert into PAYMENTS (id, user_id, trip_id) values (2, 1, 1);
insert into PAYMENTS (id, user_id, trip_id) values (3, 2, 2);
insert into PAYMENTS (id, user_id, trip_id) values (4, 3, 3);
insert into PAYMENTS (id, user_id, trip_id) values (5, 3, 2);
insert into PAYMENTS (id, user_id, trip_id) values (6, 3, 3);
insert into PAYMENTS (id, user_id, trip_id) values (7, 2, 1);

SELECT * FROM TRIPS T WHERE (T.DEPARTURE_DATE = current_date OR T.DEPARTURE_DATE > current_date) AND T.QUANTITY >25;
SELECT * FROM TRIPS T WHERE (T.DEPARTURE_DATE = current_date OR T.DEPARTURE_DATE > current_date) AND T.QUANTITY >0;

SELECT TRIPS FROM TICKETS INNER JOIN TRIPS ON TICKETS.TRIP_ID = TRIPS.ID WHERE TICKETS.ID = 1;
SELECT TRIPS FROM TICKETS INNER JOIN TRIPS ON TICKETS.TRIP_ID = TRIPS.ID WHERE TICKETS.ID = 4;
SELECT TRIPS FROM TICKETS INNER JOIN TRIPS ON TICKETS.TRIP_ID = TRIPS.ID WHERE TICKETS.ID = 2;

SELECT TICKET_STATUS FROM TICKETS WHERE ID = 1;
SELECT TICKET_STATUS FROM TICKETS WHERE ID = 2;

SELECT TRIPS.PLACE_FROM,TICKETS.TICKET_STATUS FROM TICKETS INNER JOIN TRIPS ON TICKETS.TRIP_ID = TRIPS.ID WHERE TICKETS.ID = 12;

SELECT PAYMENTS.ID FROM PAYMENTS WHERE USER_ID = 3 AND TRIP_ID = 2;

SELECT PAYMENTS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID WHERE USER_ID = 1 AND TRIPS.COST = 94;
SELECT PAYMENTS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID WHERE USER_ID = 1 AND TRIPS.COST = 14;

insert into PAYMENTS (id, user_id, trip_id) values (10, 1, (SELECT TRIPS.ID FROM TRIPS WHERE TRIPS.COST = 94));

SELECT TRIPS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID WHERE PAYMENTS.ID = 5;
SELECT TICKET_STATUS FROM TICKETS WHERE TICKETS.TRIP_ID = 1;

SELECT TICKET_STATUS FROM TICKETS WHERE TICKETS.TRIP_ID = (SELECT TRIPS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID WHERE PAYMENTS.ID = 5);

insert into TRIPS (id, place_from, place_to, departure_date, cost, quantity) values (25, 'Cristalina', 'Myski', '2022-06-04 20:00:00', 14, 15);
insert into TICKETS (id, trip_id, ticket_status) values (20, 25, 'DONE');
insert into TICKETS (id, trip_id, ticket_status) values (21, 25, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (22, 25, 'NEW');
insert into TICKETS (id, trip_id, ticket_status) values (23, 25, 'DONE');
insert into TICKETS (id, trip_id, ticket_status) values (24, 25, 'FAILED');

SELECT TICKET_STATUS FROM TICKETS WHERE TRIP_ID = 25 AND TICKET_STATUS ='NEW';

SELECT QUANTITY FROM TRIPS WHERE TRIPS.ID = 25;

SELECT PAYMENTS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID INNER JOIN USERS ON PAYMENTS.USER_ID = USERS.ID WHERE TRIPS.ID = 1 AND USERS.ID = 1;

SELECT TICKETS.ID FROM TICKETS WHERE TICKETS.TRIP_ID = (SELECT TRIPS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID WHERE PAYMENTS.ID = 5);

SELECT TRIPS.ID FROM TRIPS WHERE TRIPS.COST = 5;