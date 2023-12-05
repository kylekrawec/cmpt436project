package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final HashMap<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public void addProduct(Product product) {
        long id = counter.getAndIncrement();
        product.setId(id);
        products.put(id, product);
    }

    public boolean updateProduct(Product product) {
        boolean productExists = products.containsKey(product.getId());
        if (productExists) {
            products.put(product.getId(), product);
        }
        return productExists;
    }
}
