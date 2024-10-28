package com.shop.web.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.web.model.Category;
import com.shop.web.model.Goods;
import com.shop.web.model.Shop;

import java.time.LocalDateTime;

public class GoodsDO {
    private long id;
    private long shopId;

    private long categoryId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String url;


    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Goods toModel(){
        Goods goods = new Goods();
        goods.setId(getId());
        goods.setName(getName());
        goods.setDescription(getDescription());
        Category category = new Category();
        category.setId(getCategoryId());
        goods.setCategory(category);
        Shop shop = new Shop();
        shop.setId(getShopId());
        goods.setShop(shop);
        goods.setPrice(getPrice());
        goods.setGmtCreated(getGmtCreated());
        goods.setGmtModified(getGmtModified());
        goods.setStatus(getStatus());
        goods.setStock(getStock());
        goods.setUrl(getUrl());
        return goods;
    }
}
