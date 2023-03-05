package org.decagon.product;

import lombok.Data;

@Data
public class Product {
    public String ID;
    public String name;
    public Double price;
    public Integer quantity;
    public Integer productQuantity;
    public Double amount;
    public Integer productOption;
    public Integer productCategory;
    private Integer totalProductQuantity;
    private Double totalProductAmount;
    public Product(String ID, String name, Double price, Integer quantity){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String ID, String name, Double price, Integer productQuantity, Double amount){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
        this.amount = amount;
    }

    public Product(){

    }
}
