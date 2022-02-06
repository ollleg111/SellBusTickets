
CREATE TABLE payments_tickets(
                                 payment_id numeric NOT NULL,
                                 ticket_id numeric NOT NULL,
                                 PRIMARY KEY (payment_id, ticket_id),
                                 CONSTRAINT PAYMENT_ID_FK FOREIGN KEY (PAYMENT_ID) REFERENCES PAYMENTS(ID),
                                 CONSTRAINT TICKET_ID_FK FOREIGN KEY (TICKET_ID) REFERENCES TICKETS(ID)
);
