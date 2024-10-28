package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.common.TokenBlacklist;
import com.shop.web.dao.ShopDAO;
import com.shop.web.model.*;
import com.shop.web.service.NoticeService;
import com.shop.web.service.ShopHomeService;
import com.shop.web.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/shop/")
@CrossOrigin(origins = "*")
public class ShopAPI {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private ShopHomeService shopHomeService;
    @Autowired
    private NoticeService noticeService;
    @GetMapping("home")
    public Result<ShopHome> enterShopHome(HttpServletRequest request){
        Result<ShopHome> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String>map = JWTUtils.getClaims(token);
        String shopId = map.get("shopId");
        ShopHome shopHome = shopHomeService.makeShopHome(Long.parseLong(shopId));
        result.setSuccess(true);
        result.setData(shopHome);
        return result;
    }
    @GetMapping("notice")
    public Result<Notice> getNotice(){
        Result<Notice> result = new Result<>();
        return result;
    }

    @PostMapping("reg")
    public Result<Shop> register(@RequestParam("shopName") String shopName,@RequestParam("pwd") String pwd,@RequestParam("mobile") String mobile,@RequestParam("address") String address,@RequestParam("owner") String owner,@RequestParam("idCard") String idCard){
        return shopService.register(shopName,pwd,mobile,address,owner,idCard);
    }

    @PostMapping("login")
    public Result<Map> login(@RequestParam("shopName") String shopName, @RequestParam("pwd") String pwd, HttpServletRequest request){
        Result<Shop>result = shopService.login(shopName,pwd);
        if (!result.isSuccess()){
            Result<Map>result1 = new Result<>();
            result1.setMessage(result.getMessage());
            return result1;
        }
        Map<String,String> map = new HashMap<>();
        map.put("shopId",String.valueOf(result.getData().getId()));
        String token = JWTUtils.getToken(map);
        map.put("token",token);
        map.put("shopName",result.getData().getName());
        Result<Map>result1 = new Result<>();
        result1.setData(map);
        result1.setSuccess(true);
        return result1;
    }

    @GetMapping("logout")
    public Result logout(HttpServletRequest request){
        Result result = new Result();
        String token=request.getHeader("token");
        if (token==null){
            result.setMessage("未找到token");
            return result;
        }
        TokenBlacklist.addToBlacklist(token);
        result.setSuccess(true);
        return result;
    }

    @GetMapping("all")
    public Result<List<Shop>> getAll(){
        Result<List<Shop>> result = new Result();
        result=shopService.findAll();
        return result;
    }
    @PostMapping("update")
    public Result<Shop> update(@RequestParam(value = "mobile", required = false)String mobile,@RequestParam(value = "pwd",required = false)String pwd,HttpServletRequest request){
        Result<Shop> result = new Result<>();

        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String>map = JWTUtils.getClaims(token);
        Shop shop = shopDAO.findByShopId(Long.parseLong(map.get("shopId"))).toModel();
        shop.setPwd(pwd);
        shop.setMobile(mobile);
        result = shopService.update(shop);
        return result;
    }
    @PostMapping("getShop")
    public Result<Shop> getShop(@RequestParam("shopId")long shopId){
        Result<Shop>result = shopService.findByShopId(shopId);
        return result;
    }

    @GetMapping("myHome")
    public Result<Shop> myHome(HttpServletRequest request){
        Result<Shop> result = new Result<>();

        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String>map = JWTUtils.getClaims(token);
        Shop shop = shopDAO.findByShopId(Long.parseLong(map.get("shopId"))).toModel();
        result.setData(shop);
        result.setSuccess(true);
        return result;
    }

}
