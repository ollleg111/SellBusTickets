package com.example.salestickets.service;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.TicketDAO;
import com.example.salestickets.dao.TripDAO;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketDAO ticketDAO;
    private final PaymentDAO paymentDAO;
    private final TripDAO tripDAO;
    private final TripService tripService;
    private final UserService userService;

    public Ticket findById(Long id) throws DaoException, NotFoundException {
        ticketValidation(id);
        return ticketDAO.findById(id);
    }

    public Ticket save(Ticket ticket) throws DaoException {
        return ticketDAO.save(ticket);
    }

    public Ticket update(Ticket ticket) throws DaoException {
        return ticketDAO.update(ticket);
    }

    public void delete(Ticket ticket) throws DaoException {
        ticketDAO.delete(ticket);
    }

    public void deleteById(Long id) throws DaoException, NotFoundException {
        ticketValidation(id);
        ticketDAO.delete(ticketDAO.findById(id));
    }

    /*
        Процесс покупки билета.
        При заказе билета необходимо сохранить билет в базу,
        вычесть билет из количества доступных билетов на рейсе и
        создать платеж в платежной системе.

        Сервис покупки билета.
        На вход:
        - ФИО
        - идентификатор рейса.
        На выходе: идентификатор билета.
     */
    public Long buyTicketWithPersonAndTripId(String firstName, String lastName, Long tripId)
            throws DaoException, NotFoundException {
        Long userId = userService.findUserIdByFirstAndLastName(firstName, lastName);

        //При заказе билета необходимо сохранить билет в базу
        Ticket ticket = ticketDAO.addTicketWithPersonIdAndTripId(userId, tripId);
        tripService.quantityTripsValidation(ticket.getTrip().getQuantity(), tripId);

        //вычесть билет из количества доступных билетов на рейсе
        tripDAO.updateQuantity();

        //создать платеж в платежной системе
        paymentDAO.addPaymentsByUserIdAndTripId(userId, tripId);
        return ticket.getId();
    }

    public String findTripAndStatusByTicketId(Long ticketId) throws DaoException, NotFoundException {
        ticketValidation(ticketId);
        return  tripDAO.findTripAndStatusByTicketId(ticketId);
    }

    private void ticketValidation(Long id) throws NotFoundException {
        Ticket ticket = ticketDAO.findById(id);
        if (ticket == null) throw
                new NotFoundException("Ticket does not exist in method ticketValidation(Long id) from class " +
                        TicketService.class.getName());
    }
}
