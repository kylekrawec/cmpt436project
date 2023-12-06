package com.example.products.endpoint;

import cmpt436.soap_web_service.products.*;

import com.example.products.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Optional;

@Endpoint
public class ProductEndpoint {
//    private static final String NAMESPACE_URI = System.getenv("NAMESPACE_URI");
    private static final String NAMESPACE_URI = "http://cmpt436/soap-web-service/products";
    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createProductRequest")
    @ResponsePayload
    public CreateProductResponse createProductResponse(@RequestPayload CreateProductRequest request) {
        CreateProductResponse response = new CreateProductResponse();
        Product product = productService.createProduct(request.getProduct());
        response.setProduct(product);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProductResponse(@RequestPayload UpdateProductRequest request) {
        UpdateProductResponse response = new UpdateProductResponse();
        Product product = productService.updateProduct(request.getId(), request.getProduct());
        response.setProduct(product);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProductResponse(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productService.getProductById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductsResponse() {
        GetProductsResponse response = new GetProductsResponse();
        response.setProducts(productService.getProducts());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProductResponse(@RequestPayload DeleteProductRequest request) {
        DeleteProductResponse response = new DeleteProductResponse();
        Product product = productService.deleteProduct(request.getId());
        response.setProduct(product);
        return response;
    }
}
