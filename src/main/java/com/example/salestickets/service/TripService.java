package com.example.salestickets.service;

import com.example.salestickets.dao.TripDAO;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.TicketStatus;
import com.example.salestickets.model.Trip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private final TripDAO tripDAO;

    public Trip findById(Long id) throws DaoException, NotFoundException {
        tripValidation(id);
        return tripDAO.findById(id);
    }

    private final String alarmMessage = TripService.class.getName();

    public Trip save(Trip trip) throws DaoException {
        return tripDAO.save(trip);
    }

    public Trip update(Trip trip) throws DaoException {
        return tripDAO.update(trip);
    }

    public void delete(Trip trip) throws DaoException {
        tripDAO.delete(trip);
    }

    public void deleteById(Long id) throws DaoException, NotFoundException {
        tripValidation(id);
        tripDAO.delete(tripDAO.findById(id));
    }

    public List<Trip> getTripsListWithDateAndQuantity() throws DaoException {
        return tripDAO.getTripsListWithDateAndQuantity();
    }

    public String getInfoByTripId(Long tripId) throws DaoException, NotFoundException {
        tripValidation(tripId);
        if(!tripDAO.validationStatus(tripId, TicketStatus.NEW)) {
            if(tripDAO.validationStatus(tripId, TicketStatus.FAILED)) {
                return tripDAO.findQuantityTicketsWhenStatusFail(tripId).toString();
            }
        }
        return "DONE";
    }

    public void quantityTripsValidation(Long tripId) throws DaoException, NotFoundException {
        tripValidation(tripId);
        if(tripDAO.findById(tripId).getQuantity() > 0) throw
                new NotFoundException("Trip does not exist in method" +
                        " quantityTripsValidation(Long tripId) " + alarmMessage);
    }

    private void tripValidation(Long tripId) throws NotFoundException {
        Trip trip = tripDAO.findById(tripId);
        if (trip == null) throw
                new NotFoundException("Trip does not exist in method tripValidation(Long id) from class " +
                        alarmMessage);
    }

}

