package com.example.salestickets.controller;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.Ticket;
import com.example.salestickets.service.TicketService;
import com.example.salestickets.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tickets")
@AllArgsConstructor
@Slf4j
public class TicketController {
    private final TicketService ticketService;


    /*
      Сервис покупки билета. На вход принимает
      ФИО клиента / идентефикатор рейса
      На выходе возвращает идентификатор билета.
     */
    @PostMapping(path = "/buyTicket")
    public ResponseEntity buyTicketWithPersonAndTripId(HttpSession session,
                                                       @RequestParam String firstName,
                                                       @RequestParam String lastName,
                                                       @RequestParam String tripId) {
        try {
            Utils.loginValidation(session);

            Long ticketId =  ticketService.buyTicketWithPersonAndTripId(
                    firstName,
                    lastName,
                    Utils.stringToLong(tripId));
            log.info("We buy ticket with id: " + ticketId);
            return new ResponseEntity<>("We buy ticket with id: "+ ticketId.toString(), HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
        Сервис информации по билету.
        На вход:
            - идентификатор билета.
        На выходе:
            - информация о рейсе
            - статус платежа
     */
    @GetMapping(path = "/findInfoByTicketId")
    public ResponseEntity<String> getInfoByTicketId(HttpSession session, @RequestParam String ticketId) {
        try {
            Utils.loginValidation(session);

            String info =  ticketService.findTripAndStatusByTicketId(Utils.stringToLong(ticketId));
            log.info("We found information about ticket with id: " + info);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/findById/{ticketId}")
    public ResponseEntity getTicket(HttpSession session, @RequestParam String ticketId) {
        try {
            Utils.loginValidation(session);

            log.info("Get ticket with id: " + ticketId);
            return ResponseEntity.ok(ticketService.findById(Utils.stringToLong(ticketId)));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity addTicket(HttpSession session, @RequestBody Ticket ticket) {
        try {
            Utils.loginAndAdminValidation(session);

            Ticket addTicket = ticketService.save(ticket);
            log.info("Add ticket id: " + addTicket.getId());
            return new ResponseEntity<>("Ticket with id: " + addTicket.getId() + " was saved", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteById/{ticketId}")
    public ResponseEntity deleteById(HttpSession session, @PathVariable String ticketId) {
        try {
            Utils.loginAndAdminValidation(session);

            ticketService.deleteById(Utils.stringToLong(ticketId));
            log.info("Delete ticket with id: " + ticketId);
            return new ResponseEntity<>("Ticket was deleted", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity update(HttpSession session, @RequestBody Ticket ticket) {
        try {
            Utils.loginAndAdminValidation(session);

            Ticket updateTicket = ticketService.update(ticket);
            log.info("Update ticket with id: " + updateTicket.getId());
            return new ResponseEntity<>("Ticket was saved", HttpStatus.OK);
        } catch (DaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
