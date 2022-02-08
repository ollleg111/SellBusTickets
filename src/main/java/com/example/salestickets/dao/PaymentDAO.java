package com.example.salestickets.dao;

import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.model.Payment;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PaymentDAO extends GeneralDAO<Payment>{
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentDAO() {
        setEntityManager(entityManager);
        setTypeParameterClass(Payment.class);
    }

    //TEST OK
    private final String ADD_PAYMENT_BY_USER_ID_AND_COST_AND_PAYMENT_TIME =
            "INSERT INTO PAYMENTS (USER_ID, TRIP_ID, PAYMENT_TIME) values (" +
                    "USER_ID = ?," +
                    "(SELECT TRIPS.ID FROM TRIPS WHERE TRIPS.COST = ?)," +
                    "PAYMENT_TIME = (SELECT current_time))";

    //TEST OK
    private final String ADD_PAYMENT_BY_USER_ID_AND_TRIP_ID_AND_PAYMENT_TIME =
            "INSERT INTO PAYMENTS (USER_ID, TRIP_ID, PAYMENT_TIME) " +
                    "values (USER_ID = ?, TRIPS.ID = ?, PAYMENT_TIME = (SELECT current_time))";

    //TEST OK
    private final String FIND_TICKET_STATUS_BY_PAYMENT_ID =
            "SELECT TICKET_STATUS FROM TICKETS WHERE TICKETS.TRIP_ID =" +
                    " (SELECT TRIPS.ID FROM PAYMENTS INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID " +
                    "WHERE PAYMENTS.ID = ?)";

    //TEST OK
    private final String GET_PAYMENT_ID_BY_TRIP_ID_AND_USER_ID_AND_DATE =
            "SELECT PAYMENTS.ID FROM PAYMENTS " +
                    "INNER JOIN TRIPS ON PAYMENTS.TRIP_ID = TRIPS.ID " +
                    "INNER JOIN USERS ON PAYMENTS.USER_ID = USERS.ID " +
                    "WHERE TRIPS.ID = ? AND USERS.ID = ? AND PAYMENT_TIME = (SELECT current_time)";

    private final String alarmMessage = PaymentDAO.class.getName();

    /*
    CRUD
     */
    @Override
    public Payment findById(Long id) throws DaoException {
        return super.findById(id);
    }

    @Override
    public Payment save(Payment payment) throws DaoException {
        return super.save(payment);
    }

    @Override
    public Payment update(Payment payment) throws DaoException {
        return super.update(payment);
    }

    @Override
    public void delete(Payment payment) throws DaoException {
        super.delete(payment);
    }

    public String findTicketStatusByPaymentId(Long id) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(FIND_TICKET_STATUS_BY_PAYMENT_ID);
            query.setParameter(1, id);

            return (String) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " findTicketStatusByPaymentId(Long id) from class " + alarmMessage);
        }
    }

    public void addPaymentsByUserIdAndCost(Long userId, Long cost) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(ADD_PAYMENT_BY_USER_ID_AND_COST_AND_PAYMENT_TIME);
            query.setParameter(1, userId);
            query.setParameter(2, cost);
            query.executeUpdate();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " addPaymentsByUserIdAndCost(Long userId, Long cost) from class " + alarmMessage);
        }
    }

    @Transactional
    public void addPaymentByUserIdAndTripId(Long userId, Long tripId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(ADD_PAYMENT_BY_USER_ID_AND_TRIP_ID_AND_PAYMENT_TIME);
            query.setParameter(1, userId);
            query.setParameter(2, tripId);
            query.executeUpdate();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " addPaymentByUserIdAndTripId(Long userId, Long cost) from class " + alarmMessage);
        }
    }

    public Long getPaymentIdByUserIdAndTripId(Long tripId, Long userId) throws DaoException {
        try {
            Query query = entityManager.createNativeQuery(GET_PAYMENT_ID_BY_TRIP_ID_AND_USER_ID_AND_DATE);
            query.setParameter(1, tripId);
            query.setParameter(2, userId);

            return (Long) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " getPaymentIdByUserIdAndTripId(Long tripId, Long userId) from class " + alarmMessage);
        }
    }
}


