package com.example.democrud.controller;

import com.example.democrud.model.Order;
import com.example.democrud.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderServiceImpl.getAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/orderById/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Integer id) {
        try {
            Optional<Order> order = orderServiceImpl.getOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order newOrder = orderServiceImpl.addOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        try{
            Order updateOrder = orderServiceImpl.updateOrder(order);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(updateOrder);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Integer id){
        try{
            orderServiceImpl.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}





