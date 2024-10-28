package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.dataobject.ShoppingCartDO;
import com.shop.web.model.Goods;
import com.shop.web.model.ShoppingCart;

public interface ShoppingCartService {
    public Result add(ShoppingCartDO shoppingCartDO);
    public Result delete(ShoppingCartDO shoppingCartDO);
    public Result<ShoppingCart> getUserShoppingCart(long userId);
}
