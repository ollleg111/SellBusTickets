package com.example.salestickets.controller;

import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.model.Payment;
import com.example.salestickets.service.PaymentService;
import com.example.salestickets.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/payments")
@AllArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    /*
    Сервис оплаты. Сервис оплаты на вход принимает
        - ФИО клиента,
        - сумма
    На выходе возвращает уникальный идентификатор платежа.
     */

    @PostMapping(value = "/addPaymentPersonAndPrice")
    public ResponseEntity addPaymentPersonAndPrice(HttpSession session,
                                                   @RequestParam String firstName,
                                                   @RequestParam String lastName,
                                                   @RequestParam String cost) {
        try {
            Utils.loginValidation(session);

            Long paymentId = paymentService.addPaymentsByPersonAndCost(firstName, lastName, Utils.stringToLong(cost));
            log.info("Add payment with id: " + paymentId);
            return new ResponseEntity<>("Payment with id: " + paymentId + "was saved", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Сервис получения статуса платежа. Сервис получения статусов на вход принимает
        - уникальный идентификатор платежа
    На выходе случайным образом отдает 1 из статусов
        - NEW
        - FAILED
        - DONE
     */
    @GetMapping(path = "/findStatusByPaymentId/{paymentId}")
    public ResponseEntity<String> getTicketStatusByPaymentId(HttpSession session, @RequestParam String paymentId) {
        try {
            Utils.loginValidation(session);

            log.info("Get status with payment id: " + paymentId);
            return ResponseEntity.ok(paymentService.
                    findTicketStatusByPaymentId(Utils.stringToLong(paymentId)));
        } catch (DaoException | NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/findById/{paymentId}")
    public ResponseEntity getPayment(HttpSession session, @RequestParam String paymentId) {
        try {
            Utils.loginValidation(session);

            log.info("Get payment with id: " + paymentId);
            return ResponseEntity.ok(paymentService.findById(Utils.stringToLong(paymentId)));
        } catch (DaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity addPayment(HttpSession session, @RequestBody Payment payment) {
        try {
            Utils.loginAndAdminValidation(session);

            Payment addPayment = paymentService.save(payment);
            log.info("Add payment with id: " + addPayment.getId());
            return new ResponseEntity<>("Payment was saved", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteById/{paymentId}")
    public ResponseEntity deleteById(HttpSession session, @PathVariable String paymentId) {
        try {
            Utils.loginAndAdminValidation(session);

            paymentService.deleteById(Utils.stringToLong(paymentId));
            log.info("Delete payment with id: " + paymentId);
            return new ResponseEntity<>("Payment was deleted", HttpStatus.OK);
        } catch (DaoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity update(HttpSession session, @RequestBody Payment payment) {
        try {
            Utils.loginAndAdminValidation(session);

            Payment updatePayment = paymentService.update(payment);
            log.info("Update payment with id: " + updatePayment.getId());
            return new ResponseEntity<>("Payment was updated", HttpStatus.OK);
        } catch (DaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
