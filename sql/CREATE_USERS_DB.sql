
CREATE SEQUENCE USERS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

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
