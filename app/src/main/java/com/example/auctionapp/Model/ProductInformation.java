package com.example.auctionapp.Model;

import java.util.Date;

public class ProductInformation {
    private int productId;
    private String name;
    private String description;
    private float starting_price;
    private float finish_price;
    private float price;
    private Date start_date;
    private Date finish_date;
    private boolean sold;
    private int id_category;
    private int seller_login;
    private int buyer_login;

    public ProductInformation(int productId, String name, String description, float price, float starting_price, float finish_price, Date start_date, Date finish_date, boolean sold, int id_category, int seller_login, int buyer_login) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price=price;
        this.starting_price = starting_price;
        this.finish_price = finish_price;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.sold = sold;
        this.id_category = id_category;
        this.seller_login = seller_login;
        this.buyer_login = buyer_login;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStarting_price() { return starting_price;}

    public void setStarting_price(float starting_price) { this.starting_price = starting_price; }

    public float getPrice() { return price;}

    public void setPrice(float price) { this.price = price; }

    public float getFinish_price() {
        return finish_price;
    }

    public void setFinish_price(float finish_price) {
        this.finish_price = finish_price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getSeller_login() {
        return seller_login;
    }

    public void setSeller_login(int seller_login) {
        this.seller_login = seller_login;
    }

    public int getBuyer_login() {
        return buyer_login;
    }

    public void setBuyer_login(int buyer_login) {
        this.buyer_login = buyer_login;
    }

}