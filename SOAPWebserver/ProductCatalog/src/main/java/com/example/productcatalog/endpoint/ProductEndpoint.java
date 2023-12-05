package com.example.productcatalog.endpoint;

import cmpt436.soap_web_service.productcatalog.*;

import com.example.productcatalog.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class ProductEndpoint {
//    private static final String NAMESPACE_URI = System.getenv("NAMESPACE_URI");
    private static final String NAMESPACE_URI = "http://cmpt436/soap-web-service/productcatalog";

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProductResponse(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productService.findById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        response.setProducts(productService.getProducts());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
    @ResponsePayload
    public AddProductResponse addProductResponse(@RequestPayload AddProductRequest request) {
        AddProductResponse response = new AddProductResponse();
        productService.addProduct(request.getProduct());
        response.setResponse("Product added successfully.");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProductResponse(@RequestPayload UpdateProductRequest request) {
        UpdateProductResponse response = new UpdateProductResponse();
        boolean updated = productService.updateProduct(request.getId(), request.getProduct());
        if (updated) response.setResponse("Product updated successfully.");
        else response.setResponse("Product failed to be updated.");
        return response;
    }
}
