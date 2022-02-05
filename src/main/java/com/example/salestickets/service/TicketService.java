package com.example.salestickets.service;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.TicketDAO;
import com.example.salestickets.dao.TripDAO;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.Payment;
import com.example.salestickets.model.Ticket;
import com.example.salestickets.model.Trip;
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
     */
    public Long buyTicketWithPersonAndTripId(String firstName, String lastName, Long tripId)
            throws DaoException, NotFoundException {
            Ticket ticket = ticketDAO.getTicketIdWithPersonIdAndTripId(
                    userService.findUserIdByFirstAndLastName(firstName, lastName),
                    tripId);
            tripService.quantityTripsValidation(ticket.getTrip().getQuantity(), tripId);
            Payment payment = ticket.getPayment();

//            BuyingTicket buyingTicket = new BuyingTicket(TicketDAO ticketDAO, TripDAO tripDAO, PaymentDAO paymentDAO);
            ticketDAO.save(ticket);
            tripDAO.updateQuantity();
            paymentDAO.save(payment);
        return ticket.getId();
    }

    public String getInfoByTicketId(Long ticketId) throws DaoException, NotFoundException {
        ticketValidation(ticketId);
        Trip trip = tripDAO.findTripByTicketId(ticketId);

        return  "From: " + trip.getPlace_from() + " " +
                "To: "   + trip.getPlace_to() + " " +
                "Departure date: " + trip.getDeparture_date().toString() +
                "Cost: " + trip.getCost().toString() +
                "Payment status:" + ticketDAO.findStatusByTicketId(ticketId);
    }

    private void ticketValidation(Long id) throws NotFoundException {
        Ticket ticket = ticketDAO.findById(id);
        if (ticket == null) throw
                new NotFoundException("Ticket does not exist in method ticketValidation(Long id) from class " +
                        TicketService.class.getName());
    }
}
