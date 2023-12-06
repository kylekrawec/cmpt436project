package com.example.database.endpoint;

import cmpt436.soap_web_service.products.*;
import com.example.database.entities.ProductEntity;
import com.example.database.repositories.ProductRepository;
import com.example.database.mappers.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Endpoint
public class DatabaseEndpoint {
    private static final String NAMESPACE_URI = "http://cmpt436/soap-web-service/products";

    private final ProductRepository productRepository;

    @Autowired
    public DatabaseEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createProductRequest")
    @ResponsePayload
    public CreateProductResponse createProductResponse(@RequestPayload CreateProductRequest request) {
        CreateProductResponse response = new CreateProductResponse();
        ProductEntity productEntity = ProductMapper.jaxbToEntity(request.getProduct());
        productRepository.save(productEntity);
        response.setProduct(ProductMapper.entityToJaxb(productEntity));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProductResponse(@RequestPayload UpdateProductRequest request) {
        UpdateProductResponse response = new UpdateProductResponse();
        Optional<ProductEntity> repositorySearch = productRepository.findById(request.getId());
        if (repositorySearch.isPresent()) {
            ProductEntity productEntity = repositorySearch.get();
            NewProduct newProduct = request.getProduct();

            productEntity.setName(newProduct.getName());
            productEntity.setPrice(newProduct.getPrice());
            productEntity.setDescription(newProduct.getDescription());
            productEntity.setImageEncoding(newProduct.getImageEncoding());

            productRepository.save(productEntity);
            response.setProduct(ProductMapper.entityToJaxb(productEntity));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProductResponse(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        Optional<ProductEntity> repositorySearch = productRepository.findById(request.getId());
        if (repositorySearch.isPresent()) {
            ProductEntity productEntity = repositorySearch.get();
            Product product = ProductMapper.entityToJaxb(productEntity);
            response.setProduct(product);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductsResponse() {
        GetProductsResponse response = new GetProductsResponse();
        ProductList productList = new ProductList();

        productRepository.findAll().forEach(product -> productList.getProducts().add(ProductMapper.entityToJaxb(product)));
        response.setProducts(productList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProductResponse(@RequestPayload DeleteProductRequest request) {
        DeleteProductResponse response = new DeleteProductResponse();
        Optional<ProductEntity> repositorySearch = productRepository.findById(request.getId());
        if (repositorySearch.isPresent()) {
            ProductEntity productEntity = repositorySearch.get();
            Product product = ProductMapper.entityToJaxb(productEntity);
            response.setProduct(product);
            productRepository.delete(productEntity);
        }
        return response;
    }
}
