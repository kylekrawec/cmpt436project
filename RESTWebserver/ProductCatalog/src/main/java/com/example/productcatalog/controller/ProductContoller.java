package com.example.productcatalog.controller;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductContoller {
    private final ProductService productService;

    public ProductContoller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/getproducts")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("Product successfully created.");
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        boolean updated = productService.updateProduct(product);
        if (updated) return ResponseEntity.ok("Product successfully updated.");
        else return ResponseEntity.notFound().build();
    }
}
