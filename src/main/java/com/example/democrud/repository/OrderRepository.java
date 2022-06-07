package com.example.democrud.repository;

import com.example.democrud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();

    Optional<Order> findOrderById(Integer id);

    Optional<Order> findOrderByDate(Date date);
}
