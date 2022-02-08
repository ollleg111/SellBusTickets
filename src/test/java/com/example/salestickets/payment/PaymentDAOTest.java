package com.example.salestickets.payment;

import com.example.salestickets.dao.PaymentDAO;
import com.example.salestickets.dao.TicketDAO;
import com.example.salestickets.dao.TripDAO;
import com.example.salestickets.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PaymentDAOTest {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TripDAO tripDAO;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private PaymentDAO paymentDAO;

    @Test
    void ifShouldCheckIfStudentExistsEmail() {
        /*
                String email = "fan@gmail.com";
        Student student = new Student(
                "jamm",
                email,
                Gender.FEMALE);
        studentRepository.save(student);
        //when
        boolean exists = studentRepository.selectExistsEmail(email);
        //then
        assertThat(exists).isTrue();
         */
        //give

    }
}