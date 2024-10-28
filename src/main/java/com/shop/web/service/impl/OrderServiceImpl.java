package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.GoodsDAO;
import com.shop.web.dao.OrderDAO;
import com.shop.web.dao.ShopDAO;
import com.shop.web.dao.UserDAO;
import com.shop.web.dataobject.GoodsDO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.Goods;
import com.shop.web.model.Order;
import com.shop.web.model.Shop;
import com.shop.web.model.User;
import com.shop.web.service.OrderService;
import com.shop.web.service.ToolService;
import com.shop.web.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ToolService toolService;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private GoodsDAO goodsDAO;


    @Override
    public Order makeOrder( long userId, long goodsId,long shopId) {
        User user = userDAO.findByUserId(userId).toModel();
        Goods goods = goodsDAO.findByGoodsId(goodsId).toModel();
        Shop shop = shopDAO.findByShopId(shopId).toModel();


        String orderNumber = toolService.getOrderNum(userId,shopId,goodsId);
        String status = "待付款";
        Order order = new Order();
        order.setPrice(goods.getPrice());
        order.setOrderNumber(orderNumber);
        order.setUser(user);
        order.setGoods(goods);
        order.setShop(shop);
        order.setStatus(status);
        order.setUserId(userId);

        order.setGmtCreated(LocalDateTime.now());
        order.setGmtModified(LocalDateTime.now());
        order.setUserId(userId);
        System.out.println("你麻辣隔壁");
        System.out.println(order.getPrice());


        return order;
    }

    @Override
    public Order makeOrder(OrderDO orderDO) {
        Order order = orderDO.toModel();
        User user = userDAO.findByUserId(orderDO.getUserId()).toModel();
        Goods goods = goodsDAO.findByGoodsId(orderDO.getGoodsId()).toModel();
        Shop shop = shopDAO.findByShopId(orderDO.getShopId()).toModel();

        order.setUser(user);
        order.setGoods(goods);
        order.setShop(shop);

        return order;
    }

    @Override
    public Result<Order> addOrder(Order order) {
        Result<Order> result = new Result<>();
        OrderDO orderDO = new OrderDO();
        orderDO.setId(order.getId());
        orderDO.setOrderNumber(order.getOrderNumber());
        orderDO.setStatus(order.getStatus());
        orderDO.setPrice(order.getPrice());
        orderDO.setGmtCreated(order.getGmtCreated());
        orderDO.setGmtModified(order.getGmtModified());
        orderDO.setUserId(order.getUserId());
        orderDO.setShopId(order.getShop().getId());
        orderDO.setGoodsId(order.getGoods().getId());
        orderDAO.add(orderDO);
        orderDO=orderDAO.findByOrderNumber(order.getOrderNumber());
        result.setSuccess(true);
        Order order1 = makeOrder(orderDO);
        result.setData(order1);

        GoodsDO goodsDO = goodsDAO.findByGoodsId(orderDO.getGoodsId());
        goodsDO.setStock(goodsDO.getStock()-1);
        goodsDAO.update(goodsDO);


        return result;
    }

    @Override
    public Result<OrderDO> updateOrderStatusToPaid(long orderId) {
        Result<OrderDO> result = new Result<>();
        OrderDO orderDO = orderDAO.findByOrderId(orderId);
        orderDO.setStatus("已付款，待发货");
        orderDO.setGmtModified(LocalDateTime.now());
        orderDAO.update(orderDO);
        result.setSuccess(true);
        result.setData(orderDO);
        return result;
    }

    @Override
    public Result<OrderDO> updateOrderStatusToShipped(long orderId) {
        Result<OrderDO> result = new Result<>();
        OrderDO orderDO = orderDAO.findByOrderId(orderId);
        orderDO.setStatus("已发货,待收货");
        orderDO.setGmtModified(LocalDateTime.now());
        orderDAO.update(orderDO);
        result.setSuccess(true);
        result.setData(orderDO);
        return result;
    }

    @Override
    public Result<OrderDO> updateOrderStatusToArrived(long orderId) {
        Result<OrderDO> result = new Result<>();
        OrderDO orderDO = orderDAO.findByOrderId(orderId);
        orderDO.setStatus("待收货");
        orderDO.setGmtModified(LocalDateTime.now());
        orderDAO.update(orderDO);
        result.setSuccess(true);
        result.setData(orderDO);
        return result;
    }

    @Override
    public Result<OrderDO> updateOrderStatusToReceived(long orderId) {
        Result<OrderDO> result = new Result<>();
        OrderDO orderDO = orderDAO.findByOrderId(orderId);
        orderDO.setStatus("已收货");
        orderDO.setGmtModified(LocalDateTime.now());
        orderDAO.update(orderDO);
        result.setSuccess(true);
        result.setData(orderDO);
        return result;
    }

    @Override
    public Result<List<OrderDO>> findByUserId(long userId) {
        Result<List<OrderDO>> result = new Result<>();
        result.setData(orderDAO.findByUserId(userId));
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<OrderDO>> findByShopId(long shopId) {
        Result<List<OrderDO>> result = new Result<>();
        result.setData(orderDAO.findByShopId(shopId));
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<OrderDO> findByOrderId(long orderId) {
        Result<OrderDO> result = new Result<>();
        result.setData(orderDAO.findByOrderId(orderId));
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Order>> findAll() {
        Result<List<Order>> result = new Result<>();
        List<OrderDO> list = orderDAO.findAll();
        List<Order>list1 = new ArrayList<>();
        for (OrderDO orderDO:list){
            Order order = makeOrder(orderDO);
            list1.add(order);
        }
        result.setSuccess(true);
        result.setData(list1);
        return result;
    }

    @Override
    public Result<Order> findByOrderNumber(String orderNumber) {
        Result<Order> result = new Result<>();
        OrderDO orderDO = orderDAO.findByOrderNumber(orderNumber);
        if (orderDO==null){
            result.setMessage("未搜索到该订单");
            return result;
        }
        Order order = makeOrder(orderDO);
        result.setData(order);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result cancelOrder(long orderId) {
        Result result = new Result();
        orderDAO.delete(orderId);
        if (orderDAO.findByOrderId(orderId)!=null){
            result.setMessage("删除失败");
            return result;
        }
        result.setSuccess(true);
        return result;
    }


}
