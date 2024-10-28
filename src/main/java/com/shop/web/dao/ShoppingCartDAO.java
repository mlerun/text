package com.shop.web.dao;

import com.shop.web.dataobject.ShoppingCartDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingCartDAO {
    int add(ShoppingCartDO shoppingCartDO);
    int delete(ShoppingCartDO shoppingCartDO);
    List<ShoppingCartDO> getShoppingCart(@Param("userId")long userId);
}
