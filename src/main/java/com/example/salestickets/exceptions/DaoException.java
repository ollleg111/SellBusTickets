package com.example.salestickets.exceptions;

import org.hibernate.HibernateException;

public class DaoException extends HibernateException {
    public DaoException(String message) {
        super(message);
    }
}
