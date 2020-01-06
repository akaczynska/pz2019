package com.example.auctionapp.Model;

public class ProductInformation {
    private int productId;
    private String productName;
    private String productPrice;
    private String productTime;
    private String productDescription;

    public ProductInformation(String productName, String productPrice, String productTime, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTime = productTime;
        this.productDescription = productDescription;
    }
    public ProductInformation(){
        productName = "Empty";
        productPrice = "Empty";
        productTime = "Empty";
        productDescription = "Empty";
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

}
