package com.demo.identa.repos;

import com.demo.identa.models.User;
import com.demo.identa.models.enums.Authorities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void findUserByEmail_SaveAndReturnUser_returnUser() {
        String email = "blabla@gmail.com";
        User user = User.builder()
                .email(email)
                .password("1234")
                .authorities(Authorities.USER)
                .build();

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void findUserByEmail_UserNotExistsByEmail_returnNull() {
        String email = "blabla@gmail.com";

        User userFromDB = userRepository.findUserByEmail(email);

        assertThat(userFromDB).isNull();
    }

}