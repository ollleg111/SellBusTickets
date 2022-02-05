package com.example.salestickets.dao;

import com.example.salestickets.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import com.example.salestickets.model.Trip;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TripDAO extends GeneralDAO<Trip> {
    @PersistenceContext
    private EntityManager entityManager;

    public TripDAO() {
        setEntityManager(entityManager);
        setTypeParameterClass(Trip.class);
    }

    private final String alarmMessage = TripDAO.class.getName();

    private final String FIND_TRIP_BY_TICKET_ID =
            "SELECT * FROM TRIPS INNER JOIN TICKETS T ON T.ID = ?";

    private final String UPDATE_QUANTITY = "UPDATE TRIPS T SET T.QUANTITY = T.QUANTITY - 1";

    private final String GET_ALL_TRIPS_WITH_DATE_AND_QUANTITY =
            "SELECT * FROM TRIPS T WHERE (T.DEPARTURE_DATE = current_date OR T.DEPARTURE_DATE > current_date) AND " +
                    "T.QUANTITY > 0";

    /*
    CRUD
     */

    @Override
    public Trip findById(Long id) throws DaoException {
        return super.findById(id);
    }

    @Override
    public Trip save(Trip trip) throws DaoException {
        return super.save(trip);
    }

    @Override
    public Trip update(Trip trip) throws DaoException {
        return super.update(trip);
    }

    @Override
    public void delete(Trip trip) throws DaoException {
        super.delete(trip);
    }

    public Trip findTripByTicketId(Long ticketId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(FIND_TRIP_BY_TICKET_ID);
            query.setParameter(1, ticketId);

            return (Trip) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " findTripByTicketId(Long ticketId) from class " + alarmMessage);
        }
    }

    @Transactional
    public void updateQuantity() throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(UPDATE_QUANTITY);
            query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " updateQuantity() from class " + alarmMessage);
        }
    }

    public List<Trip> getTripsListWithDateAndQuantity() throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_ALL_TRIPS_WITH_DATE_AND_QUANTITY);
            return query.getResultList();
        } catch (DaoException e) {
            throw new HibernateException("Operation filed in method getTripsListWithDateAndQuantity()" +
                    " from class" + alarmMessage);
        }
    }
}
