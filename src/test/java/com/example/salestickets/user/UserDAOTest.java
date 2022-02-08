package com.example.salestickets.user;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.UserDAO;
import com.example.salestickets.model.Payment;
import com.example.salestickets.model.User;
import com.example.salestickets.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    void isWeHaveTheResult(){
        /*
    (1, 'Ade', 'Flieger', '948-926-1740', 'aflieger0@scientificamerican.com', 'hDYuEKjFrshe', 'ADMIN');
    (2, 'Taryn', 'Ramalho', '267-278-7268', 'tramalho1@google.it', '9ddHnRusSS', 'USER');
    (3, 'Rubetta', 'Kares', '749-883-1389', 'rkares2@storify.com', '64n7VSRF', 'USER');
     */

//1,"Ade", "Flieger", "948-926-1740", "aflieger0@scientificamerican.com", "hDYuEKjFrshe", UserStatus.ADMIN

        User user1 = new User(10L,"Ade","Flieger", "948-926-1740", "aflieger0@scientificamerican.com","hDYuEKjFrshe", UserStatus.ADMIN, null);
        User user2 = new User(11L,"Taryn","Ramalho", "267-278-7268", "tramalho1@google.it","9ddHnRusSS", UserStatus.USER, null);
        User user3 = new User(12L,"Rubetta","Kares", "749-883-1389", "1rkares2@storify.com","64n7VSRF", UserStatus.USER, null);

        User uOne = userDAO.save(user1);
        User uTwo = userDAO.save(user2);
        User uThree = userDAO.save(user3);

        assertThat(uOne).isNull();
        assertThat(uTwo).isNull();
        assertThat(uThree).isNull();

    }





}
