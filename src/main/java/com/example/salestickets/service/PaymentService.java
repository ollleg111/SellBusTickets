package com.example.salestickets.service;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.TripDAO;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentDAO paymentDAO;
    private UserService userService;
    private TripDAO tripDAO;

    public Payment findById(Long id) throws DaoException, NotFoundException {
        paymentValidation(id);
        return paymentDAO.findById(id);
    }

    public Payment save(Payment payment) throws DaoException {
        return paymentDAO.save(payment);
    }

    public Payment update(Payment payment) throws DaoException {
        return paymentDAO.update(payment);
    }

    public void delete(Payment payment) throws DaoException {
        paymentDAO.delete(payment);
    }

    public void deleteById(Long id) throws DaoException, NotFoundException {
        paymentValidation(id);
        paymentDAO.delete(paymentDAO.findById(id));
    }

    public String findTicketStatusByPaymentId(Long id) throws DaoException, NotFoundException {
        paymentValidation(id);
        return paymentDAO.findTicketStatusByPaymentId(id);
    }

    public Long addPaymentsByPersonAndCost(String firstName, String lastName, Long cost) throws
            DaoException, NotFoundException {
        Long userId = userService.findUserIdByFirstAndLastName(firstName, lastName);
        paymentDAO.addPaymentsByUserIdAndCost(userId, cost);

        return paymentDAO.getPaymentIdByUserIdAndTripId(tripDAO.getTripIdByTripCost(cost), userId);
    }

    private void paymentValidation(Long id) throws NotFoundException {
        Payment payment = paymentDAO.findById(id);
        if (payment == null) throw
                new NotFoundException("Payment does not exist in method paymentValidation(Long id) from class " +
                        PaymentService.class.getName());
    }
}
