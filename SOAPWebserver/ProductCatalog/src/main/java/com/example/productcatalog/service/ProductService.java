package com.example.productcatalog.service;

import cmpt436.soap_web_service.productcatalog.NewProduct;
import cmpt436.soap_web_service.productcatalog.ProductList;

import cmpt436.soap_web_service.productcatalog.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private static final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Product findById(long id) {
        return products.get(id);
    }

    public ProductList getProducts() {
        ProductList productList = new ProductList();
        productList.getProduct().addAll(products.values().stream().toList());
        return productList;
    }

    public void addProduct(NewProduct newProduct) {
        long id = counter.getAndIncrement();
        Product product = this.mapNewProduct(newProduct);
        product.setId(id);
        products.put(id, product);
    }

    public boolean updateProduct(Long id, NewProduct newProduct) {
        boolean productExists = products.containsKey(id);
        if (productExists) {
            Product product = this.mapNewProduct(newProduct);
            product.setId(id);
            products.put(id, product);
        }
        return productExists;
    }

    private Product mapNewProduct(NewProduct newProduct) {
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setImageEncoding(newProduct.getImageEncoding());
        return product;
    }
}
