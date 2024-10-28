package com.shop.web.dao;

import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDAO {
    OrderDO findByOrderNumber(@Param("orderNumber")String orderNumber);
    OrderDO findByOrderId(@Param("orderId")long orderId);
    List<OrderDO>findByUserId(@Param("userId")long userId);
    List<OrderDO>findByShopId(@Param("shopId")long shopId);
    List<OrderDO> findAll();
    int add(OrderDO orderDO);
    int update(OrderDO orderDO);
    int delete(@Param("orderId")long orderId);



}
