package com.demo.identa.services;

import com.demo.identa.models.User;
import com.demo.identa.models.enums.Authorities;
import com.demo.identa.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void findByEmail_CheckIfUserExistsByEmail() {
        String email = "blabla@gmail.com";
        userService.findByEmail(email);
        verify(userRepository).findUserByEmail(email);
    }

    @Test
    void isAdmin_returnIfUserIsAdmin() {
        User admin = User.builder()
                .authorities(Authorities.ADMIN)
                .build();
        User user = User.builder()
                .authorities(Authorities.USER)
                .build();

        boolean isAdmin = userService.isAdmin(admin);
        boolean isNotAdmin = userService.isAdmin(user);

        assertThat(isAdmin).isTrue();
        assertThat(isNotAdmin).isFalse();
    }

    @Test
    @Disabled
    void loadUserByUsername() {
    }
}