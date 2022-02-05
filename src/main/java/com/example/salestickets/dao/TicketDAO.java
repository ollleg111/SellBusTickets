package com.example.salestickets.dao;

import com.example.salestickets.model.Ticket;
import com.example.salestickets.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TicketDAO extends GeneralDAO<Ticket>{
    @PersistenceContext
    private EntityManager entityManager;

    public TicketDAO() {
        setEntityManager(entityManager);
        setTypeParameterClass(Ticket.class);
    }

    private final String GET_ALL_TICKETS_WITH_TICKET_STATUS_NEW =
            "SELECT * FROM TICKETS WHERE TICKET_STATUS = 'NEW'";

    private final String GET_TICKET_WITH_USER_ID_AND_TRIPS_ID = "SELECT T.ID FROM TICKETS T" +
            " INNER JOIN USERS U ON U.ID = ? INNER JOIN TRIPS TR ON TR.ID = ?";

    private final String FIND_TICKET_STATUS_BY_TICKET_ID =
            "SELECT T.TICKET_STATUS FROM TICKETS T WHERE T.ID = ?";

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

    public List<Ticket> getTicketsListWithFilter() throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_ALL_TICKETS_WITH_TICKET_STATUS_NEW);
            return query.getResultList();
        } catch (DaoException e) {
            throw new HibernateException("Operation filed in method getTicketsListWithFilter()" +
                    " from class" + alarmMessage);
        }
    }

    public Ticket getTicketIdWithPersonIdAndTripId(Long userId, Long tripsId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_TICKET_WITH_USER_ID_AND_TRIPS_ID);
            query.setParameter(1, userId);
            query.setParameter(2, tripsId);

            return (Ticket) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " getTicketIdWithPersonId(Long userId) from class " + alarmMessage);
        }
    }
    public String findStatusByTicketId(Long ticketId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(FIND_TICKET_STATUS_BY_TICKET_ID);
            query.setParameter(1, ticketId);

            return (String) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " findStatusByTicketId(Long ticketId) from class " + alarmMessage);
        }
    }
}
