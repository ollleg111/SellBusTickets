package com.example.salestickets.util;

import com.example.salestickets.exceptions.BadRequestException;
import com.example.salestickets.model.User;
import com.example.salestickets.model.UserStatus;

import javax.servlet.http.HttpSession;

public class Utils {
    public static Long stringToLong(String number) throws BadRequestException {
        try {
            long id = Long.parseLong(number);
            if (id <= 0) {
                throw new BadRequestException(" Wrong entered number: " + number);
            }
            return id;
        } catch (NumberFormatException e) {
            throw new BadRequestException(" Incorrect format ");
        }
    }

    public static void loginValidation(HttpSession session) throws BadRequestException {
        isUserWithLogin(session, ((User) session.getAttribute("user")).getId());
    }

    public static void loginAndAdminValidation(HttpSession session) throws BadRequestException {
        isUserWithLogin(session, ((User) session.getAttribute("user")).getId());
        isUserStatusEqualsAdmin(session);
    }

    public static void isUserWithLogin(HttpSession session, Long userId) throws BadRequestException {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId().equals(userId)) throw new BadRequestException("You have to write the login");
    }

    public static void isUserStatusEqualsAdmin(HttpSession session) throws BadRequestException {
        User user = (User) session.getAttribute("user");
        if(!(user.getUserStatus().toString()).equals(UserStatus.ADMIN.toString()))
            throw new BadRequestException("You do not have permission to change this data");
    }
}

