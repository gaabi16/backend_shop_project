package com.example.democrud.controller;

import com.example.democrud.controller.dto.ProductDto;
import com.example.democrud.model.Product;
import com.example.democrud.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products") //aici punem url-ul de baza
@Controller //ajuta Spring Boot-ul sa identifice clasa ca un Controller (gestioneaza cerintele HTTP de la client)
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productServiceImpl.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Integer id) {
        try {
            Optional<Product> product = productServiceImpl.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productServiceImpl.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // fara DTO
//    @PutMapping("/updateProduct")
//    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
//        Product updatedProduct = productServiceImpl.updateProduct(product);
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(updatedProduct);
//    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable(name = "id") Integer id) {
        Optional<Product> productFromDB = productServiceImpl.getProductById(id);
        productFromDB.get().setName(productDto.getName());
        productFromDB.get().setDescription(productDto.getDescription());
        productFromDB.get().setPrice((productDto.getPrice()));
        productServiceImpl.updateProduct(productFromDB.get());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productServiceImpl.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
