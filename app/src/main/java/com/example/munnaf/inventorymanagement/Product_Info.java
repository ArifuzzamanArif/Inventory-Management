package com.example.munnaf.inventorymanagement;

public class Product_Info {

    private String code;
    private String name;
    private String color;
    private String description;
    private String price;
    private String size;
    private String status;
    private String type;

    public Product_Info() {
    }

    public Product_Info(String code, String name, String color, String description, String price, String size, String status, String type) {
        this.code = code;
        this.name = name;
        this.color = color;
        this.description = description;
        this.price = price;
        this.size = size;
        this.status = status;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
