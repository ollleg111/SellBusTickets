package com.example.salestickets.trash;

public class Trash {
    //payments
    /*
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
*/
}
