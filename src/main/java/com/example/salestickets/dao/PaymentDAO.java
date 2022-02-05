package com.example.salestickets.dao;

import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.model.Payment;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
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

    private final String FIND_TICKET_STATUS_BY_PAYMENT_ID =
            "SELECT T.TICKET_STATUS FROM TICKETS T INNER JOIN PAYMENTS P ON P.ID = ?";

    private final String FIND_PAYMENT_ID_WITH_USER_ID_AND_COST =
            "SELECT P.PAYMENTS_STATUS FROM PAYMENTS P INNER JOIN USERS U ON U.ID = ?" +
                    "INNER JOIN ";

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
                    " findTicketStatusById(Long id) from class " + alarmMessage);
        }
    }

    public Long getPaymentIdWithFilter(Long userId, Long cost) throws DaoException{
        try {
            Query query = entityManager.createNativeQuery(FIND_PAYMENT_ID_WITH_USER_ID_AND_COST);
            query.setParameter(1, userId);
            query.setParameter(1, cost);

            return (Long) query.getSingleResult();
        } catch (DaoException e) {
            throw new HibernateException("Operation with user data was filed in method" +
                    " getPaymentIdWithFilter(Long userId, Long cost) from class " + alarmMessage);
        }
    }
}
