package com.example.salestickets.dao;

import com.example.salestickets.model.Ticket;
import com.example.salestickets.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class TicketDAO extends GeneralDAO<Ticket>{
    @PersistenceContext
    private EntityManager entityManager;

    public TicketDAO() {
        setEntityManager(entityManager);
        setTypeParameterClass(Ticket.class);
    }

    //TEST OK
    private final String ADD_TICKET_WITH_TRIP_ID_AND_STATUS =
            "INSERT INTO TICKETS (TRIP_ID, TICKET_STATUS) VALUES (?1, 'NEW')";

    //TEST OK
    private final String GET_TICKET_ID_BY_PAYMENT_ID =
            "SELECT TICKETS.ID FROM TICKETS WHERE TICKETS.TRIP_ID = " +
                    "(SELECT TRIPS.ID FROM PAYMENTS INNER JOIN TRIPS ON " +
                    "PAYMENTS.TRIP_ID = TRIPS.ID WHERE PAYMENTS.ID = ?1)";

    private String alarmMessage = TicketDAO.class.getName();

    /*
    CRUD
     */

    @Override
    public Ticket findById(Long id) throws DaoException {
        return super.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) throws DaoException {
        return super.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) throws DaoException {
        return super.update(ticket);
    }

    @Override
    public void delete(Ticket ticket) throws DaoException {
        super.delete(ticket);
    }

    @Transactional
    public void addTicketWithTripIdAndStatus(Long tripsId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(ADD_TICKET_WITH_TRIP_ID_AND_STATUS);
            query.setParameter(1, tripsId);
            query.executeUpdate();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " addTicketWithTripIdAndStatus(Long tripsId) from class " + alarmMessage);
        }
    }

    public Long getTicketIdWithPersonAndTripId(Long paymentId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_TICKET_ID_BY_PAYMENT_ID);
            query.setParameter(1, paymentId);

            return (Long) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " getTicketIdWithPersonAndTripId(Long userId, Long tripId) from class " + alarmMessage);
        }
    }
}
