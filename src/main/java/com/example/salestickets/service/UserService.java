package com.example.salestickets.service;

import com.example.salestickets.dao.UserDAO;
import com.example.salestickets.exceptions.BadRequestException;
import com.example.salestickets.exceptions.DaoException;
import com.example.salestickets.exceptions.NotFoundException;
import com.example.salestickets.exceptions.ServiceException;
import com.example.salestickets.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public User findById(Long id) throws DaoException, NotFoundException {
        userValidation(id);
        return userDAO.findById(id);
    }

    public User save(User user) throws ServiceException {
        if (!userDAO.validationMailAndPhoneNumber(user.getPhoneNumber(), user.getMail()))
            throw new BadRequestException("User is already exist");
        return userDAO.save(user);
    }

    public User update(User user) throws DaoException {
        return userDAO.update(user);
    }

    public void delete(User user) throws DaoException {
        userDAO.delete(user);
    }

    public void deleteById(Long id) throws DaoException, NotFoundException {
        userValidation(id);
        userDAO.delete(userDAO.findById(id));
    }

    public User login(String mail, String password) throws ServiceException {
        if (mail.isEmpty() || password.isEmpty()) throw new BadRequestException("Incorrect email or password");

        User user = userDAO.getUser(mail, password);
        if (user == null) throw new BadRequestException("User with email or password does not exist");
        return user;
    }

    public Long findUserIdByFirstAndLastName(String firstName, String lastName) throws ServiceException {
        return userDAO.findUserIdByFirstAndLastName(firstName, lastName);
    }

    private void userValidation(Long id) throws NotFoundException {
        User user = userDAO.findById(id);
        if (user == null) throw
                new NotFoundException("User does not exist in method userValidation(Long id) from class " +
                        UserService.class.getName());
    }
}

