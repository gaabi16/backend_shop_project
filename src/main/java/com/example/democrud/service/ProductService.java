package com.example.democrud.service;


import com.example.democrud.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Integer id);

    Product updateProduct(Product product);

    Product addProduct(Product product);

    void deleteProduct(Integer id);
}
