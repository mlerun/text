package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Goods;

import java.util.List;

public interface GoodsService {
    public Result<List<Goods>> getGoodsToHome();

    public Result<Goods> getGoods(long id);

    public Result<Goods> add(String goodsName, long shopId, long categoryId, String description, double price, int stock, String url);

    public Result<Goods> update(long goodsId,String description,double price,int stock,String url);
    public Result audit (long goodsId);
    public Result delist(long goodsId);
    public Result<List<Goods>> find(String des);
    public Result<List<Goods>> findByShopId(long shopId);
    public Result<List<Goods>> findAll();

    public Result<List<Goods>> findByCategoryId(long categoryId);
    public Result<List<Goods>> findAllWaitAudit();
    public Result<List<Goods>> findAllAudited();

}