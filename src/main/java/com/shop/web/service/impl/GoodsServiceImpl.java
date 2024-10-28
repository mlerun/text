package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.GoodsDAO;
import com.shop.web.dao.OrderDAO;
import com.shop.web.dao.ShopDAO;
import com.shop.web.dataobject.GoodsDO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.Goods;
import com.shop.web.model.Order;
import com.shop.web.model.Shop;
import com.shop.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ShopDAO shopDAO;

    @Override
    public Result<List<Goods>> getGoodsToHome() {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> goodsDOList = goodsDAO.findByGoodsStatus("已上架");
        List<Goods> goodsList = new ArrayList<>();
        goodsDOList.forEach(goodsDO -> {
            goodsList.add(goodsDO.toModel());
        });
        result.setData(goodsList);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Goods> getGoods(long id) {
        Result<Goods> result = new Result<>();
        result.setData(goodsDAO.findByGoodsId(id).toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Goods> add(String goodsName, long shopId, long categoryId, String description, double price, int stock, String url) {
        Result<Goods>result = new Result<>();
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setName(goodsName);
        goodsDO.setShopId(shopId);
        goodsDO.setCategoryId(categoryId);
        goodsDO.setDescription(description);
        goodsDO.setPrice(price);
        goodsDO.setStock(stock);
        goodsDO.setUrl(url);
        goodsDO.setStatus("待审核");
        goodsDAO.addGoods(goodsDO);
        result.setData(goodsDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Goods> update(long goodsId,String description, double price, int stock, String url) {
        Result<Goods>result = new Result<>();
        GoodsDO goodsDO = goodsDAO.findByGoodsId(goodsId);
        goodsDO.setDescription(description);
        goodsDO.setStock(stock);
        goodsDO.setPrice(price);
        goodsDO.setUrl(url);
        goodsDAO.update(goodsDO);
        result.setData(goodsDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Goods> audit(long goodsId) {
        Result<Goods> result = new Result();
        GoodsDO goodsDO = goodsDAO.findByGoodsId(goodsId);
        goodsDO.setStatus("已上架");
        goodsDAO.update(goodsDO);
        result.setData(goodsDO.toModel());
        result.setSuccess(true);
        return result;

    }

    @Override
    public Result<Goods> delist(long goodsId) {
        Result<Goods> result = new Result<>();
        GoodsDO goodsDO = goodsDAO.findByGoodsId(goodsId);
        goodsDO.setStatus("已下架");
        goodsDAO.update(goodsDO);
        result.setData(goodsDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> find(String des) {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.find(des);
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            list1.add(goodsDO.toModel());
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> findByShopId(long shopId) {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.findByShopId(shopId);
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            list1.add(goodsDO.toModel());
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> findAll() {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.findAll();
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            Goods goods = goodsDO.toModel();

            Shop shop = shopDAO.findByShopId(goodsDO.getShopId()).toModel();
            goods.setShop(shop);
            list1.add(goods);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> findByCategoryId(long categoryId) {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.findByCategoryId(categoryId);
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            list1.add(goodsDO.toModel());
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> findAllWaitAudit() {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.findByGoodsStatus("待审核");
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            list1.add(goodsDO.toModel());
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Goods>> findAllAudited() {
        Result<List<Goods>> result = new Result<>();
        List<GoodsDO> list = goodsDAO.findByGoodsStatus("已上架");
        List<Goods>list1 = new ArrayList<>();
        for(GoodsDO goodsDO:list){
            list1.add(goodsDO.toModel());
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }


}
