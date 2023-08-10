package com.demo.identa.services;

import com.demo.identa.models.Order;
import com.demo.identa.models.User;
import com.demo.identa.models.enums.Authorities;
import com.demo.identa.models.enums.Status;
import com.demo.identa.repos.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    void makeOrder_WithoutUser() {
        Order orderWithoutUser = Order.builder()
                .status(Status.PROCESSING)
                .build();

        orderService.makeOrder();
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();
        assertThat(capturedOrder).isEqualTo(orderWithoutUser);
    }

    @Test
    void makeOrder_WithUser() {
        User user = User.builder()
                .authorities(Authorities.ADMIN)
                .email("blabla@gmail.com")
                .password("1234")
                .build();
        Order orderWithUser = Order.builder()
                .status(Status.PROCESSING)
                .user(user)
                .build();

        orderService.makeOrder(user);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedWithUser = orderArgumentCaptor.getValue();
        assertThat(capturedWithUser).isEqualTo(orderWithUser);
    }

    @Test
    void findAll() {
        orderService.findAll();
        verify(orderRepository).findAll();
    }

    @Test
    void getById_WithExactId() {
        Order order = Order.builder()
                .id(1L)
                .build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getById(1L);
        Order notExistId = orderService.getById(2L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(notExistId).isNull();

    }

    @Test
    @Disabled
    void changeStatusToApprove() {
    }



    @Test
    @Disabled
    void changeStatusToDeclined() {
    }
}