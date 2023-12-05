package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private final String DATASERVICE_URL = "http://localhost:8080";
//    private final String DATASERVICE_URL = System.getenv("SPRING_DATASOURCE_URL");

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product findById(int id) {
        return restTemplate.getForObject(DATASERVICE_URL + "/products/get/" + id, Product.class);
    }

    public Iterable<Product> getProducts() {
        ResponseEntity<Iterable<Product>> response = restTemplate.exchange(
                DATASERVICE_URL + "/products/get",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public void createProduct(Product product) {
        restTemplate.postForObject(DATASERVICE_URL + "/products/create", product, String.class);
    }

    public void updateProduct(int id, Product product) {
        restTemplate.put(DATASERVICE_URL + "/products/update/" + id, product, String.class);
    }

    public void deleteProduct(int id) {
        restTemplate.delete(DATASERVICE_URL + "/products/delete/" + id, String.class);
    }
}
