package com.example.salestickets.dao;

import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.model.TicketStatus;
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

    //TEST OK
    private final String FIND_TRIP_INFO_AND_TICKET_STATUS_BY_TICKET_ID =
            "SELECT TRIPS.PLACE_FROM, TRIPS.PLACE_TO, TRIPS.DEPARTURE_DATE," +
                    "TRIPS.COST, TICKETS.TICKET_STATUS FROM" +
                    " TICKETS INNER JOIN TRIPS ON TICKETS.TRIP_ID = TRIPS.ID WHERE TICKETS.ID = ?";
    //TEST OK
    private final String GET_ALL_TRIPS_WITH_DATE_AND_QUANTITY =
            "SELECT * FROM TRIPS T WHERE (" +
                    "T.DEPARTURE_DATE = (SELECT current_date) OR T.DEPARTURE_DATE > (SELECT current_date)) AND " +
                    "T.QUANTITY > 0";
    //TEST_OK
    private final String UPDATE_QUANTITY = "UPDATE TRIPS T SET T.QUANTITY = T.QUANTITY - 1";
    //TEST OK
    private final String VALIDATION_NEW_STATUS =
            "SELECT TICKET_STATUS FROM TICKETS WHERE TRIP_ID = ? AND TICKET_STATUS = ?";
    //TEST OK
    private final String QUANTITY_TICKETS_IN_THE_TRIP_WHERE_STATUS_FAIL =
            "SELECT QUANTITY FROM TRIPS WHERE TRIPS.ID = ?";

    //TEST OK
    private final String GET_TRIP_ID_BY_COST_AND_USER_ID_AND_DATE =
            "SELECT TRIPS.ID FROM TRIPS INNER JOIN " +
                    "PAYMENTS ON TRIPS.PAYMENT_ID = PAYMENTS.ID INNER JOIN " +
                    "USERS ON TRIPS.USER_ID = USERS.ID " +
                    "WHERE TRIPS.COST = ? AND USERS.ID = ? AND PAYMENTS.PAYMENT_TIME = (SELECT current_time)";
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

    public String findTripAndStatusByTicketId(Long ticketId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(FIND_TRIP_INFO_AND_TICKET_STATUS_BY_TICKET_ID);
            query.setParameter(1, ticketId);

            return (String) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " findTripAndStatusByTicketId(Long ticketId) from class " + alarmMessage);
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

    public boolean validationStatus(Long tripId, TicketStatus ticketStatus) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(VALIDATION_NEW_STATUS);
            query.setParameter(1, tripId);
            query.setParameter(2, ticketStatus.toString());

            return (boolean) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation filed in method validationStatus()" +
                    " from class" + alarmMessage);
        }
    }

    public Long findQuantityTicketsWhereStatusFail(Long tripId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(QUANTITY_TICKETS_IN_THE_TRIP_WHERE_STATUS_FAIL);
            query.setParameter(1, tripId);

            return (Long) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " findQuantityTicketsWhereStatusFail(Long ticketId) from class " + alarmMessage);
        }
    }

    public Long getTripIdByTripCostAndUserId(Long cost, Long userId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_TRIP_ID_BY_COST_AND_USER_ID_AND_DATE);
            query.setParameter(1, cost);
            query.setParameter(2, userId);

            return (Long) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with ticket data was filed in method" +
                    " getTripIdByTripCostAndUserId(Long cost) from class " + alarmMessage);
        }
    }
}
