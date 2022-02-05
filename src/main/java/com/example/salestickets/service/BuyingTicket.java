package com.example.salestickets.service;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.TicketDAO;
import com.example.salestickets.dao.TripDAO;

public class BuyingTicket {
    public PaymentDAO paymentDAO;
    public TicketDAO ticketDAO;
    public TripDAO tripDAO;

    public BuyingTicket(PaymentDAO paymentDAO, TicketDAO ticketDAO, TripDAO tripDAO) {
        this.paymentDAO = paymentDAO;
        this.ticketDAO = ticketDAO;
        this.tripDAO = tripDAO;
    }


    public BuyingTicket() {

    }
}
