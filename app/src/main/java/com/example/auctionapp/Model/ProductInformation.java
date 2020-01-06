package com.example.auctionapp.Model;

public class ProductInformation {
    private int productId;
    private String name;
    private String description;
    private float starting_price;
    private float finish_price;
    private boolean sold;
    private int id_category;
    private String seller_login;
    private String buyer_login;
    private String time;

    public ProductInformation(int productId, String name, String description, float starting_price, float finish_price, boolean sold, int id_category, String seller_login, String buyer_login, String time) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.starting_price = starting_price;
        this.finish_price = finish_price;
        this.sold = sold;
        this.id_category = id_category;
        this.seller_login = seller_login;
        this.buyer_login = buyer_login;
        this.time = time;
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

    public float getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(float starting_price) {
        this.starting_price = starting_price;
    }

    public float getFinish_price() {
        return finish_price;
    }

    public void setFinish_price(float finish_price) {
        this.finish_price = finish_price;
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

    public String getSeller_login() {
        return seller_login;
    }

    public void setSeller_login(String seller_login) {
        this.seller_login = seller_login;
    }

    public String getBuyer_login() {
        return buyer_login;
    }

    public void setBuyer_login(String buyer_login) {
        this.buyer_login = buyer_login;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
