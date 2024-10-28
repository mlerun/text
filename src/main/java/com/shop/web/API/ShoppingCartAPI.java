package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.dao.GoodsDAO;
import com.shop.web.dao.OrderDAO;
import com.shop.web.dataobject.GoodsDO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.dataobject.ShoppingCartDO;
import com.shop.web.model.Goods;
import com.shop.web.model.Order;
import com.shop.web.model.Shop;
import com.shop.web.model.ShoppingCart;
import com.shop.web.service.GoodsService;
import com.shop.web.service.OrderService;
import com.shop.web.service.ShopService;
import com.shop.web.service.ShoppingCartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/shoppingCart/")
@CrossOrigin(origins = "*")
public class ShoppingCartAPI {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private OrderDAO orderDAO;
    @PostMapping("add")
    public Result addGoodsToCart(HttpServletRequest request, @RequestParam("goodsId")long goodsId){
        Result result = new Result();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        if (goodsId==0){
            result.setMessage("商品id有误");
            return result;
        }
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setGoodsId(goodsId);
        shoppingCartDO.setUserId(id);

        result = shoppingCartService.add(shoppingCartDO);
        return result;

    }
    @PostMapping("delete")
    public Result deleteGoodsFromCart(HttpServletRequest request,@RequestParam("goodsId")long goodsId){
        Result result = new Result();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        if (goodsId==0){
            result.setMessage("商品id有误");
            return result;
        }
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setGoodsId(goodsId);
        shoppingCartDO.setUserId(id);

        result=shoppingCartService.delete(shoppingCartDO);
        return result;
    }

    @GetMapping("home")
    public Result<ShoppingCart> getShoppingCart(HttpServletRequest request){
        Result<ShoppingCart> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        result=shoppingCartService.getUserShoppingCart(id);
        return result;
    }

    @PostMapping("orders/list")
    public Result<List<Order>> makeCartOrder(@RequestParam("goodsIdList")List<Long> list,HttpServletRequest request){
        Result<List<Order>> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));

        List<Order>list1 = new ArrayList<>();
        list.forEach(goodsId->{
            GoodsDO goodsDO = goodsDAO.findByGoodsId(goodsId);
            Order order = orderService.makeOrder(id,goodsId,goodsDO.getShopId());
            orderService.addOrder(order);
            OrderDO orderDO = orderDAO.findByOrderNumber(order.getOrderNumber());
            order=orderService.makeOrder(orderDO);
            list1.add(order);
        });
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }
    @PostMapping("pay")
    public Result<List<Order>> pay(@RequestParam("orderList")List<Long>list,HttpServletRequest request){
        Result<List<Order>> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        List<Order>list1 = new ArrayList<>();
        list.forEach(orderId->{
            Result<OrderDO>result1 = orderService.updateOrderStatusToPaid(orderId);
            orderDAO.update(result1.getData());
            Order order = orderService.makeOrder(result1.getData());
            list1.add(order);
        });
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }
}
