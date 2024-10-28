package com.shop.web.dataobject;

import com.shop.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingCartDO {

    long userId;
    long goodsId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

}
