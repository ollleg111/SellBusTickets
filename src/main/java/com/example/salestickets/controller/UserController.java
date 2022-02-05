package com.example.salestickets.controller;

import com.example.salestickets.exceptions.*;
import com.example.salestickets.model.User;
import com.example.salestickets.service.UserService;
import com.example.salestickets.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            User addUser = userService.save(user);
            log.info("Registered user with id: " + addUser.getId() +
                    " and name: " + addUser.getFirstName() + " " + addUser.getLastName());
            return ResponseEntity.ok("User with id: " + addUser.getId() + " was saved");
        } catch (BadRequestException | DaoException | ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(HttpSession session,
                                        @RequestParam("mail") String mail,
                                        @RequestParam("password") String password) {
        try {
            User user = userService.login(mail, password);
            session.setAttribute("user", user);
            log.info("login complete");
            return new ResponseEntity<>("login complete", HttpStatus.OK);
        } catch (ServiceException | BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpSession session)
    {
        session.invalidate();
        log.info("logout complete");
        return new ResponseEntity<>("logout complete", HttpStatus.OK);
    }

    @GetMapping(path = "/findById/{userId}")
    public ResponseEntity getUser(HttpSession session, @RequestParam String userId) {
        try {
            Utils.loginValidation(session);
            log.info("Get user with id: " + userId);
            return ResponseEntity.ok(userService.findById(Utils.stringToLong(userId)));
        } catch (DaoException | NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteById/{userId}")
    public ResponseEntity deleteById(HttpSession session, @PathVariable String userId) {
        try {
            Utils.loginAndAdminValidation(session);
            userService.deleteById(Utils.stringToLong(userId));
            log.info("Delete user with id: " + userId);
            return new ResponseEntity<>("User was deleted", HttpStatus.OK);
        } catch (DaoException | NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity update(HttpSession session, @RequestBody User user) {
        try {
            Utils.loginAndAdminValidation(session);
            User updateUser = userService.update(user);
            log.info("Update user with id: " +  + updateUser.getId() +
                    " and name: " + updateUser.getFirstName() + " " + updateUser.getLastName());
            return ResponseEntity.ok("User updated");
        } catch (DaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}