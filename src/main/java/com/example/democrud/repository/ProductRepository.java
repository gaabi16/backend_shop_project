package com.example.democrud.repository;

import com.example.democrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { //fiecare repository trebuie sa extinda JpaRepository<numeEntitateDinJava, tipPrimaryKey>
    List<Product> findAll();

    List<Product> findAllByName(String name);

    Optional<Product> findProductById(Integer id);

    //Optional<List<Product>> findProductByName(String name);
}
