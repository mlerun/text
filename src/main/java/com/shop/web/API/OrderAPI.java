package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.dao.OrderDAO;
import com.shop.web.dao.UserDAO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.model.Order;
import com.shop.web.model.User;
import com.shop.web.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/order/")
@CrossOrigin(origins = "*")
public class OrderAPI {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserDAO userDAO;

    @PostMapping("submit")
    public Result<Order> submit(@RequestParam("shopId") long shopId, @RequestParam("goodsId") long goodsId, HttpServletRequest request){

        Result<Order> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        if (userDAO.findByUserId(id).getAddress()==null||userDAO.findByUserId(id).getMobile()==null){
            result.setMessage("请补全个人信息后再购买");
            return result;
        }

        Order order = orderService.makeOrder(id,goodsId,shopId);

        result=orderService.addOrder(order);

        return result;
    }
    @PostMapping("pay")
    public Result<OrderDO> pay(@RequestParam("orderId") long orderId){
        Result<OrderDO> result = orderService.updateOrderStatusToPaid(orderId);
        orderDAO.update(result.getData());
        result.setSuccess(true);
        return result;
    }

    @PostMapping("ship")
    public Result<OrderDO> ship(@RequestParam("orderId") long orderId){
        Result<OrderDO> result = orderService.updateOrderStatusToShipped(orderId);
        orderDAO.update(result.getData());
        result.setSuccess(true);
        return result;
    }
    @PostMapping("arrive")
    public Result<OrderDO> arrive(@RequestParam("orderId") long orderId){
        Result<OrderDO> result = orderService.updateOrderStatusToArrived(orderId);
        orderDAO.update(result.getData());
        result.setSuccess(true);
        return result;
    }
    @PostMapping("receive")
    public Result<OrderDO> receive(@RequestParam("orderId") long orderId){
        Result<OrderDO> result = orderService.updateOrderStatusToReceived(orderId);
        orderDAO.update(result.getData());
        result.setSuccess(true);
        return result;
    }
    @GetMapping("user/getAllOrder")
    public Result<List<Order>> userGetAllOrder(HttpServletRequest request){
        Result<List<Order>> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));
        Result<List<OrderDO>> resultDo = orderService.findByUserId(id);
        List<OrderDO> orderDOList = resultDo.getData();
        List<Order> orderList = new ArrayList<>();
        orderDOList.forEach(orderDO -> {
            Order order = orderService.makeOrder(orderDO);
            System.out.println(order.getPrice());
            orderList.add(order);
        });
        result.setData(orderList);
        result.setSuccess(true);
        return result;
    }


    @GetMapping("shop/getAllOrder")
    public Result<List<Order>> shopGetAllOrder(HttpServletRequest request){
        Result<List<Order>> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("shopId"));
        Result<List<OrderDO>> resultDo = orderService.findByShopId(id);
        List<OrderDO> orderDOList = resultDo.getData();
        List<Order> orderList = new ArrayList<>();
        orderDOList.forEach(orderDO -> {
            Order order = orderService.makeOrder(orderDO);
            orderList.add(order);
        });
        result.setData(orderList);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("getOrder")
    public Result<Order> getOrder(@RequestParam("orderId") long orderId){
        Result<Order> result = new Result<>();
        Result<OrderDO> result1 = orderService.findByOrderId(orderId);
        Order order = orderService.makeOrder(result1.getData());
        result.setData(order);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("by/orderNumber")
    public Result<Order> getByOrderNum(@RequestParam("orderNumber")String orderNumber){
        Result<Order>result = orderService.findByOrderNumber(orderNumber);
        return result;
    }

    @PostMapping("delete")
    public Result delete(@RequestParam("orderId")long orderId){
        Result result = orderService.cancelOrder(orderId);
        return result;
    }

}
