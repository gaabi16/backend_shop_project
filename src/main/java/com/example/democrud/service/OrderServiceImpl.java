package com.example.democrud.service;

import com.example.democrud.model.Order;
import com.example.democrud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findOrderById(id);
        if(!order.isPresent()){
            throw new IllegalArgumentException("Order not found!");
        }
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Optional<Order> searchedOrderById = orderRepository.findOrderById(order.getId());
        if(!searchedOrderById.isPresent()){
            throw new IllegalArgumentException("Order was not found");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order addOrder(Order order) {
        Optional<Order> searchedOrder = orderRepository.findOrderById(order.getId());
        if(searchedOrder.isPresent()){
            throw new IllegalArgumentException("Order already exists!");
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        Optional<Order> searchedOrder = orderRepository.findOrderById(id);
        if(!searchedOrder.isPresent()){
            throw new IllegalArgumentException("Order does not exist!");
        }
        orderRepository.deleteById(id);
    }
}
