
CREATE SEQUENCE TICKETS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

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
