package com.example.democrud.service;

import com.example.democrud.model.Product;
import com.example.democrud.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    //injectarea de dependente: aceasta clasa pentru a executa operatii are nevoie de un obiect de
    // tipul repository-ului (pe care incercam sa evitam sa-l instantiem cu new, de aceea folosim
    // dependency injection)
    private final ProductRepository productRepository;

    //dependency injection pe constructor se face automat, altfel daca il facem pe field sau pe
    // setter trebuie sa folosim anotarea @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach((product) -> productList.add(product)); //ne folosim de obiectul de tipul repository pentru a apela metoda dorita
        return productList;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        Optional<Product> product = productRepository.findProductById(id);
        if (!product.isPresent()) {
            throw new IllegalArgumentException("Product not found!");
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> searchedProductById = productRepository.findProductById(product.getId());
        if (!searchedProductById.isPresent()) {
            throw new IllegalArgumentException("Product was not found!");
        }
        return productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductById(product.getId());
        if (productOptional.isPresent()) {
            throw new IllegalArgumentException("Product already exists!");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        if(!productOptional.isPresent()){
            throw new IllegalArgumentException("Product doesn't exist!");
        }
        productRepository.deleteById(id);
    }
}
