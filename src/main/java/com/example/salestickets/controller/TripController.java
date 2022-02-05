package com.example.salestickets.controller;

import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.model.Ticket;
import com.example.salestickets.model.Trip;
import com.example.salestickets.service.TripService;
import com.example.salestickets.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/trips")
@AllArgsConstructor
@Slf4j
public class TripController {
    private final TripService tripService;

    @GetMapping(path = "/findById/{tripId}")
    public ResponseEntity getPayment(HttpSession session, @RequestParam String tripId) {
        try {
            Utils.loginAndAdminValidation(session);

            log.info("Get trip with id: " + tripId);
            return ResponseEntity.ok(tripService.findById(Utils.stringToLong(tripId)));
        } catch (DaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity addPayment(HttpSession session, @RequestBody Trip trip) {
        try {
            Utils.loginAndAdminValidation(session);

            Trip addTrip = tripService.save(trip);
            log.info("Add trip with id: " + addTrip.getId());
            return new ResponseEntity<>("Trip with id: " + addTrip.getId() + " was saved", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteById/{tripId}")
    public ResponseEntity deleteById(HttpSession session, @PathVariable String tripId) {
        try {
            Utils.loginAndAdminValidation(session);

            tripService.deleteById(Utils.stringToLong(tripId));
            log.info("Delete trip with id: " + tripId);
            return new ResponseEntity<>("Trip was deleted", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity update(HttpSession session, @RequestBody Trip trip) {
        try {
            Utils.loginAndAdminValidation(session);

            Trip updateTrip = tripService.update(trip);
            log.info("Update trip with id: " + updateTrip.getId());
            return new ResponseEntity<>("Trip was updated", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
       Список рейсов:
        - Идентификатор
        - Откуда
        - Куда
        - Время отправления
        - Стоимость
        - Количество доступных билетов
  */
    @GetMapping(path = "/getTripsList")
    public ResponseEntity<List<Trip>> getTripsList(HttpSession session) {
        try {
            Utils.loginAndAdminValidation(session);

            log.info("Get all trips with free seats");
            List<Trip> tripList =  tripService.getTripsListWithDateAndQuantity();
            return new ResponseEntity<>(tripList, HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Дополнительно необходимо реализовать обработчик по расписанию, который будет проверять состояние всех билетов, у которых состояние платежа NEW.
    Если получаем статус FAIL, возвращаем количество доступных билетов на рейсе и больше не опрашиваем статус.
    Если получаем DONE, то ничего не делаем и больше не опрашиваем статус.
     */
    @GetMapping(path = "/getInfoByTripId")
    public ResponseEntity<String> getInfoByTripId(HttpSession session, @RequestParam Long tripId) {
        try {
            Utils.loginAndAdminValidation(session);

            log.info("Get info with tripId: " + tripId);
            tripService.getInfoByTripId(tripId);
            return new ResponseEntity<>("Get condition", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
