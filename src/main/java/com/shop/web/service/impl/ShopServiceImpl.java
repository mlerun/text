package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.ShopDAO;
import com.shop.web.dataobject.ShopDO;
import com.shop.web.model.Shop;
import com.shop.web.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDAO shopDAO;
    @Override
    public Shop getShopByGoodsId(long goodsId) {
        return shopDAO.findByGoodsId(goodsId).toModel();
    }

    @Override
    public Result<Shop> register(String shopName, String pwd, String mobile, String address, String owner, String idCard) {
        ShopDO shopDO = new ShopDO();
        Result<Shop> result = new Result<>();
        if (StringUtils.isEmpty(shopName)){
            result.setMessage("店铺名字不能为空");
            return result;
        }
        if (shopDAO.findByShopName(shopName)!=null){
            result.setMessage("店铺已存在");
            return result;
        }
        if (StringUtils.isEmpty(pwd)){
            result.setMessage("密码不能为空");
            return result;
        }
        if (StringUtils.isEmpty(mobile)){
            result.setMessage("店铺电话不能为空");
            return result;
        }
        if (StringUtils.isEmpty(address)){
            result.setMessage("店铺地址不能为空");
            return result;
        }
        if (StringUtils.isEmpty(owner)){
            result.setMessage("店铺实际注册人不能为空");
            return result;
        }
        if (StringUtils.isEmpty(idCard)){
            result.setMessage("注册人身份证不能为空");
            return result;
        }
        shopDO.setName(shopName);
        shopDO.setPwd(pwd);
        shopDO.setMobile(mobile);
        shopDO.setAddress(address);
        shopDO.setOwner(owner);
        shopDO.setIdCard(idCard);
        shopDAO.add(shopDO);
        result.setData(shopDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Shop> login(String shopName, String pwd) {
        Result<Shop>result = new Result<>();
        if (StringUtils.isEmpty(shopName)){
            result.setMessage("店铺名字不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)){
            result.setMessage("密码不能为空");
            return result;
        }
        if (shopDAO.findByShopName(shopName)==null){
            result.setMessage("店铺不存在");
            return result;
        }
        if (!StringUtils.equals(pwd,shopDAO.findByShopName(shopName).getPwd())){
            result.setMessage("密码错误");
            return result;
        }
        result.setData(shopDAO.findByShopName(shopName).toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Shop>> findAll() {
        Result<List<Shop>> result = new Result<>();
        List<ShopDO>list = shopDAO.findAll();
        List<Shop>list1 = new ArrayList<>();
        for (ShopDO shopDO:list){
            Shop shop = shopDO.toModel();
            list1.add(shop);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Shop> update(Shop shop) {
        Result<Shop>result = new Result<>();
        ShopDO shopDO = new ShopDO();
        shopDO.setId(shop.getId());
        shopDO.setPwd(shop.getPwd());
        shopDO.setMobile(shop.getMobile());
        if (shopDAO.update(shopDO)>0){
            result.setData(shop);
            result.setSuccess(true);
            return result;
        };
        result.setMessage("更新错误");
        return result;
    }

    @Override
    public Result<Shop> findByShopId(long shopId) {
        Result<Shop>result = new Result<>();
        ShopDO shopDO = shopDAO.findByShopId(shopId);
        result.setData(shopDO.toModel());
        result.setSuccess(true);
        return result;
    }
}
