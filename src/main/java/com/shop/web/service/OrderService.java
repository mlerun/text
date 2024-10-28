package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.dao.OrderDAO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {

    public Order makeOrder(long userId,long goodsId,long shopId);
    public Order makeOrder(OrderDO orderDO);
    public Result<Order> addOrder(Order order);
    public Result<OrderDO> updateOrderStatusToPaid(long orderId);
    public Result<OrderDO> updateOrderStatusToShipped(long orderId);
    public Result<OrderDO> updateOrderStatusToArrived(long orderId);
    public Result<OrderDO> updateOrderStatusToReceived(long orderId);
    public Result<List<OrderDO>> findByUserId(long userId);
    public Result<List<OrderDO>> findByShopId(long shopId);
    public Result<OrderDO> findByOrderId(long orderId);
    public Result<List<Order>> findAll();
    public Result<Order> findByOrderNumber(String orderNumber);
    public Result cancelOrder(long orderId);

}
