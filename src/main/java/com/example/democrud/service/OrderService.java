package com.example.democrud.service;

import com.example.democrud.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Integer id);

    Order updateOrder(Order order);

    Order addOrder(Order order);

    void deleteOrder(Integer id);

}
