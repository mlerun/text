package com.shop.web.dataobject;

import com.shop.web.dao.UserDAO;
import com.shop.web.model.Goods;
import com.shop.web.model.Order;
import com.shop.web.model.Shop;
import com.shop.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDO {

    private String id;
    private String orderNumber;
    private long userId;
    private long shopId;
    private long goodsId;
    private String status;
    private double price;

    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Order toModel(){
        Order order = new Order();
        order.setPrice(getPrice());
        order.setId(getId());
        order.setOrderNumber(getOrderNumber());
        order.setStatus(getStatus());
        User user = new User();
        user.setId(getUserId());
        order.setUser(user);
        Goods goods = new Goods();
        goods.setId(getGoodsId());
        order.setGoods(goods);
        Shop shop = new Shop();
        shop.setId(getShopId());
        order.setGmtCreated(getGmtCreated());
        order.setGmtModified(getGmtModified());

        return order;
    }
}
