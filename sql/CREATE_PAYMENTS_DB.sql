
CREATE SEQUENCE PAYMENTS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

CREATE TABLE payments (
                          id numeric,
                          CONSTRAINT payment_pk PRIMARY KEY (id),
                          user_id numeric NOT NULL,
                          CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
                          trip_id numeric NOT NULL,
                          CONSTRAINT TRIP_FK FOREIGN KEY (TRIP_ID) REFERENCES TRIPS(ID)
);

ALTER TABLE payments ADD COLUMN payment_time TIMESTAMP;