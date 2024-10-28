package com.shop.web.model;

import java.util.List;

public class ShoppingCart {
    long userId;
    List<Goods> goodsList;
    double price;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void getTotalPrices(){
        double price = 0;
        for (Goods goods:goodsList){
            price = price+goods.getPrice();
        }
        this.price=price;
    }
}
