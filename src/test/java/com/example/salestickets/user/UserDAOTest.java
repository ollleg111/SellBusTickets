package com.example.salestickets.user;

import com.example.salestickets.dao.UserDAO;
import com.example.salestickets.model.User;
import com.example.salestickets.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


/*
spring.datasource.url=jdbc:h2://mem:db;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=sa
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
 */


@DataJpaTest
public class UserDAOTest {

//    @Autowired
//    private UserDAO userDAO;

    @Test
    void isWeHaveTheResult(){
/*
    (1, 'Ade', 'Flieger', '948-926-1740', 'aflieger0@scientificamerican.com', 'hDYuEKjFrshe', 'ADMIN');
    (2, 'Taryn', 'Ramalho', '267-278-7268', 'tramalho1@google.it', '9ddHnRusSS', 'USER');
    (3, 'Rubetta', 'Kares', '749-883-1389', 'rkares2@storify.com', '64n7VSRF', 'USER');
*/
        // id, first_name, last_name, phone_number, e_mail, password, user_status

        Long id = 10L;
        String firstName = "Ade";
        String last_name = "Flieger";
        String phone_number = "948-926-1740";
        String e_mail = "aflieger0@scientificamerican.com";
        String password = "hDYuEKjFrshe";



        User user1 = new User(id, firstName, last_name, phone_number, e_mail, password, UserStatus.ADMIN, null);
        //User user2 = new User(11L,"Taryn","Ramalho", "267-278-7268", "tramalho1@google.it","9ddHnRusSS", UserStatus.USER, null);
        //User user3 = new User(12L,"Rubetta","Kares", "749-883-1389", "1rkares2@storify.com","64n7VSRF", UserStatus.USER, null);

        //User uOne = userDAO.save(user1);
        //User uTwo = userDAO.save(user2);
        //User uThree = userDAO.save(user3);

        //assertThat(uOne).;
        //assertThat(uTwo).isNull();
        //assertThat(uThree).isNull();

    }
}
