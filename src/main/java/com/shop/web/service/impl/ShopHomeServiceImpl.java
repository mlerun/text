package com.shop.web.service.impl;

import com.shop.web.dao.OrderDAO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.ShopHome;
import com.shop.web.service.ShopHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopHomeServiceImpl implements ShopHomeService {
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public ShopHome makeShopHome(long shopId) {
        ShopHome shopHome = new ShopHome();
        shopHome.setShopId(shopId);
        List<OrderDO> list = orderDAO.findByShopId(shopId);
        double turnover = 0;
        int waitOrder = 0;
        int finishOrder = 0;
        for (OrderDO orderDO:list){
            if (orderDO.getStatus().equals("已付款，待发货")){
                waitOrder++;
            }
            if (orderDO.getStatus().equals("已收货")){
                finishOrder++;
            }
            if (!orderDO.getStatus().equals("未付款")){
                turnover=turnover+orderDO.getPrice();
            }
        }
        shopHome.setTurnover(turnover);
        shopHome.setWaitOrder(waitOrder);
        shopHome.setFinishOrder(finishOrder);
        return shopHome;
    }
}
