package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.GoodsDAO;
import com.shop.web.dao.ShoppingCartDAO;
import com.shop.web.dataobject.ShoppingCartDO;
import com.shop.web.model.Goods;
import com.shop.web.model.ShoppingCart;
import com.shop.web.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    @Autowired
    private GoodsDAO goodsDAO;
    @Override
    public Result add(ShoppingCartDO shoppingCartDO) {
        Result result = new Result();
        if (shoppingCartDO.getGoodsId()==0){
            result.setMessage("商品id为空");
            return result;
        }
        if (shoppingCartDO.getUserId()==0){
            result.setMessage("用户id为空");
            return result;
        }
        List<ShoppingCartDO> list = shoppingCartDAO.getShoppingCart(shoppingCartDO.getUserId());
        for (ShoppingCartDO shoppingCartDO1:list){
            if (shoppingCartDO.getGoodsId()==shoppingCartDO1.getGoodsId()){
                result.setMessage("请勿重复添加");
                return result;
            }
        }

        shoppingCartDAO.add(shoppingCartDO);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result delete(ShoppingCartDO shoppingCartDO) {
        Result result = new Result();
        if (shoppingCartDO.getGoodsId()==0){
            result.setMessage("商品id为空");
            return result;
        }
        if (shoppingCartDO.getUserId()==0){
            result.setMessage("用户id为空");
            return result;
        }
        shoppingCartDAO.delete(shoppingCartDO);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<ShoppingCart> getUserShoppingCart(long userId) {
        Result<ShoppingCart> result = new Result<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        double price = 0;
        List<ShoppingCartDO> list = shoppingCartDAO.getShoppingCart(userId);
        List<Goods>list1 = new ArrayList<>();
        for (ShoppingCartDO shoppingCartDO:list){
            shoppingCart.setUserId(shoppingCartDO.getUserId());
            Goods goods = goodsDAO.findByGoodsId(shoppingCartDO.getGoodsId()).toModel();
            list1.add(goods);
            price=price+goods.getPrice();
        }
        shoppingCart.setGoodsList(list1);
        shoppingCart.setPrice(price);
        result.setData(shoppingCart);
        result.setSuccess(true);
        return result;
    }
}
