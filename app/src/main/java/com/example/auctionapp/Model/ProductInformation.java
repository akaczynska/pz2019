package com.example.auctionapp.Model;

public class ProductInformation {
    private String productName;
    private String productPrice;
    private String productTime;
    private String imageURL;

    public ProductInformation(String productName, String productPrice, String productTime, String imageURL) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTime = productTime;
        this.imageURL = imageURL;
    }
    public ProductInformation(){
        productName = "Empty";
        productPrice = "Empty";
        productTime = "Empty";
        imageURL = "Empty";
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
