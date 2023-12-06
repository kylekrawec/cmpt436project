package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Value("${dataservice.url}")
    private String dataservice_url;

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product findById(int id) {
        return restTemplate.getForObject(dataservice_url + "/products/get/" + id, Product.class);
    }

    public Iterable<Product> getProducts() {
        ResponseEntity<Iterable<Product>> response = restTemplate.exchange(
                dataservice_url + "/products/get",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public void createProduct(Product product) {
        restTemplate.postForObject(dataservice_url + "/products/create", product, String.class);
    }

    public void updateProduct(int id, Product product) {
        restTemplate.put(dataservice_url + "/products/update/" + id, product, String.class);
    }

    public void deleteProduct(int id) {
        restTemplate.delete(dataservice_url + "/products/delete/" + id, String.class);
    }
}
