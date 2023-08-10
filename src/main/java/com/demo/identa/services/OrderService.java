package com.demo.identa.services;

import com.demo.identa.models.Order;
import com.demo.identa.models.User;
import com.demo.identa.models.enums.Status;
import com.demo.identa.repos.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void makeOrder(User user) {
        Order order = Order.builder()
                .status(Status.PROCESSING)
                .build();
        if(user != null) {
            order.setUser(user);
        }
        orderRepository.save(order);
    }

    @Transactional
    public void makeOrder() {
        Order order = Order.builder()
                .status(Status.PROCESSING)
                .user(null)
                .build();
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
       return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getById(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public void changeStatusToApprove(Order order) {
        order.setStatus(Status.ACCEPTED);
    }

    @Transactional
    public void changeStatusToDeclined(Order order) {
        order.setStatus(Status.DECLINED);
    }
}
