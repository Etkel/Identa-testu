package com.demo.identa.controllers;

import com.demo.identa.models.Order;
import com.demo.identa.models.User;
import com.demo.identa.services.OrderService;
import com.demo.identa.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private OrderService orderService;
    private UserService userService;

    public MainController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("isAdmin", userService.isAdmin(user));
        } else model.addAttribute("isAdmin", null);
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/")
    public void makeOrder(Principal principal) {
        if(principal != null) {
            orderService.makeOrder(userService.findByEmail(principal.getName()));
        } else orderService.makeOrder();
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders", orderList);
        return "admin";
    }

    @PostMapping("/admin/reject/{orderId}")
    public String rejectOrder(@PathVariable Long orderId) {
        Order order = orderService.getById(orderId);
        orderService.changeStatusToDeclined(order);
        return "redirect:/admin";
    }

    @PostMapping("/admin/approve/{orderId}")
    public String approveOrder(@PathVariable Long orderId) {
        Order order = orderService.getById(orderId);
        orderService.changeStatusToApprove(order);
        return "redirect:/admin";
    }

}
