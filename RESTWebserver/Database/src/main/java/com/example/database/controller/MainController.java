package com.example.database.controller;

import com.example.database.entities.Product;
import com.example.database.repositories.ProductRepository;

import org.hibernate.exception.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/products")
public class MainController {
    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping(path = "/create")
    public @ResponseBody ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product Saved");
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateProduct(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product.setId(id);
            productRepository.save(product);
            return "Product updated";
        } else {
            return "Product not found";
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String updateProduct(@PathVariable int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return "Product was deleted";
        } else {
            return "Product not found";
        }
    }

    @GetMapping(path = "/get")
    public @ResponseBody Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public @ResponseBody Product getProduct(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    // Local Exception Handlers
    // -------------------------------------------------------------------------------------------------------
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data access error occurred");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body("Constraint violation: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
    }
}
