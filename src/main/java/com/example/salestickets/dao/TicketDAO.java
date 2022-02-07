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
    private final String ADD_TICKET_WITH_USER_ID_AND_TRIPS_ID =
            "INSERT INTO TICKETS (TRIP_ID, TICKET_STATUS) VALUES (?, 'NEW')";

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
    public Ticket addTicketWithPersonIdAndTripId(Long userId, Long tripsId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(ADD_TICKET_WITH_USER_ID_AND_TRIPS_ID);
            query.setParameter(1, userId);

            return (Ticket) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " addTicketWithPersonIdAndTripId(Long userId) from class " + alarmMessage);
        }
    }
}
