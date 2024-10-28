package com.shop.web.model;

public class AdminHome {
    int userNumber;
    int shopNumber;
    int orderNumber;
    int goodsNumber;
    int waitAuditGoods;
    int categoryNumber;

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(int shopNumber) {
        this.shopNumber = shopNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getWaitAuditGoods() {
        return waitAuditGoods;
    }

    public void setWaitAuditGoods(int waitAuditGoods) {
        this.waitAuditGoods = waitAuditGoods;
    }
}
