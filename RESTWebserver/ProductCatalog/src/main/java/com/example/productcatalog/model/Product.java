package com.example.productcatalog.model;

public class Product {
    private Long id;
    private String name;
    private float price;
    private String description;
    private String imageEncoding;

    public Product(String name, float price, String description, String imageEncoding) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageEncoding = imageEncoding;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageEncoding() {
        return imageEncoding;
    }

    public void setImageEncoding(String imageEncoding) {
        this.imageEncoding = imageEncoding;
    }
}
