package com.demo.identa.controllers;

import com.demo.identa.models.Order;
import com.demo.identa.models.User;
import com.demo.identa.services.OrderService;
import com.demo.identa.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private UserService userService;
    private MainController mainController;
    @Mock
    private Principal principal;
    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        mainController = new MainController(orderService, userService);
    }


    @Test
    void getIndexShouldReturnIndexView() {
        when(userService.findByEmail(principal.getName())).thenReturn(new User());
        String viewName = mainController.getIndex(model, principal);
        assertThat(viewName).isEqualTo("index");
        verify(model).addAttribute(eq("isAdmin"), any());
    }

    @Test
    void getLoginShouldReturnLoginView() {
        String viewName = mainController.getLogin();
        assertThat(viewName).isEqualTo("login");
    }

    @Test
    void makeOrderShouldCallOrderServiceMakeOrder() {
        mainController.makeOrder(principal);
        verify(orderService).makeOrder(any());
    }

    @Test
    void adminPageShouldReturnAdminViewAndAddOrdersAttribute() {
        List<Order> orderList = new ArrayList<>();
        when(orderService.findAll()).thenReturn(orderList);
        String viewName = mainController.adminPage(model);
        assertThat(viewName).isEqualTo("admin");
        verify(model).addAttribute(eq("orders"), eq(orderList));
    }

    @Test
    void rejectOrderShouldCallOrderServiceChangeStatusToDeclined() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderService.getById(orderId)).thenReturn(order);
        String redirectUrl = mainController.rejectOrder(orderId);
        assertThat(redirectUrl).isEqualTo("redirect:/admin");
        verify(orderService).changeStatusToDeclined(order);
    }

    @Test
    void approveOrderShouldCallOrderServiceChangeStatusToApprove() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderService.getById(orderId)).thenReturn(order);
        String redirectUrl = mainController.approveOrder(orderId);
        assertThat(redirectUrl).isEqualTo("redirect:/admin");
        verify(orderService).changeStatusToApprove(order);
    }
}