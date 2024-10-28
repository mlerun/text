package com.shop.web.model;

public class ShopHome {

    long shopId;
    double turnover;
    int waitOrder;
    int finishOrder;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public int getWaitOrder() {
        return waitOrder;
    }

    public void setWaitOrder(int waitOrder) {
        this.waitOrder = waitOrder;
    }

    public int getFinishOrder() {
        return finishOrder;
    }

    public void setFinishOrder(int finishOrder) {
        this.finishOrder = finishOrder;
    }
}
