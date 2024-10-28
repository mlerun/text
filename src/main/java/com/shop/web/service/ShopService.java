package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Shop;

import java.util.List;

public interface ShopService {
    public Shop getShopByGoodsId(long goodsId);
    public Result<Shop> register(String shopName,String pwd,String mobile,String address,String owner,String idCard);
    public Result<Shop> login(String shopName,String pwd);
    public Result<List<Shop>> findAll();
    public Result<Shop>update(Shop shop);
    public Result<Shop> findByShopId(long shopId);

}
