package com.example.products.service;

import cmpt436.soap_web_service.products.*;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class ProductService {
    private final WebServiceTemplate webServiceTemplate;
    private final String databaseServiceURL = "http://localhost:8080/products";
//    private final String databaseServiceURL = System.getenv("DATABASE_SERVICE_URL");

    public ProductService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Product createProduct(NewProduct newProduct) {
        CreateProductRequest request = new CreateProductRequest();
        request.setProduct(newProduct);
        CreateProductResponse response = (CreateProductResponse) webServiceTemplate.marshalSendAndReceive(databaseServiceURL, request);
        return response.getProduct();
    }

    public Product updateProduct(int id, NewProduct newProduct) {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setId(id);
        request.setProduct(newProduct);
        UpdateProductResponse response = (UpdateProductResponse) webServiceTemplate.marshalSendAndReceive(databaseServiceURL, request);
        return response.getProduct();
    }

    public Product getProductById(int id) {
        GetProductRequest request = new GetProductRequest();
        request.setId(id);
        GetProductResponse response = (GetProductResponse) webServiceTemplate.marshalSendAndReceive(databaseServiceURL, request);
        return response.getProduct();
    }

    public ProductList getProducts() {
        GetProductsRequest request = new GetProductsRequest();
        GetProductsResponse response = (GetProductsResponse) webServiceTemplate.marshalSendAndReceive(databaseServiceURL, request);
        return response.getProducts();
    }

    public Product deleteProduct(int id) {
        DeleteProductRequest request = new DeleteProductRequest();
        request.setId(id);
        DeleteProductResponse response = (DeleteProductResponse) webServiceTemplate.marshalSendAndReceive(databaseServiceURL, request);
        return response.getProduct();
    }
}
