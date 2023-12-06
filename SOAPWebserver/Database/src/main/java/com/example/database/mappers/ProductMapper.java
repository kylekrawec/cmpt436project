package com.example.database.mappers;

import cmpt436.soap_web_service.products.NewProduct;
import cmpt436.soap_web_service.products.Product;
import com.example.database.entities.ProductEntity;

public class ProductMapper {

    public static ProductEntity jaxbToEntity(Product jaxbProduct) {
        ProductEntity entity = new ProductEntity();
        entity.setId(jaxbProduct.getId());
        entity.setName(jaxbProduct.getName());
        entity.setPrice(jaxbProduct.getPrice());
        entity.setDescription(jaxbProduct.getDescription());
        entity.setImageEncoding(jaxbProduct.getImageEncoding());
        return entity;
    }

    public static ProductEntity jaxbToEntity(NewProduct jaxbProduct) {
        ProductEntity entity = new ProductEntity();
        entity.setName(jaxbProduct.getName());
        entity.setPrice(jaxbProduct.getPrice());
        entity.setDescription(jaxbProduct.getDescription());
        entity.setImageEncoding(jaxbProduct.getImageEncoding());
        return entity;
    }

    public static Product entityToJaxb(ProductEntity entity) {
        Product jaxbProduct = new Product();
        jaxbProduct.setId(entity.getId());
        jaxbProduct.setName(entity.getName());
        jaxbProduct.setPrice(entity.getPrice());
        jaxbProduct.setDescription(entity.getDescription());
        jaxbProduct.setImageEncoding(entity.getImageEncoding());
        return jaxbProduct;
    }
}
