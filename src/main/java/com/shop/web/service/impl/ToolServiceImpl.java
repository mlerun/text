package com.shop.web.service.impl;

import com.shop.web.service.ToolService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ToolServiceImpl implements ToolService {

    @Override
    public String getOrderNum(long userId,long shopId,long goodsId) {
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        String randomNumeric = RandomStringUtils.randomNumeric(3);

        String sUserId = String.valueOf(userId);
        String sShopId = String.valueOf(shopId);
        String sGoodsId = String.valueOf(goodsId);
       String orderNum = localDate+randomNumeric+sUserId+sShopId+sGoodsId;

        return orderNum;
    }
}
