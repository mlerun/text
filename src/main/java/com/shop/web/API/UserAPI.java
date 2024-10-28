package com.shop.web.API;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.shop.web.common.JWTUtils;

import com.shop.web.common.Result;
import com.shop.web.common.TokenBlacklist;
import com.shop.web.dao.UserDAO;
import com.shop.web.model.User;
import com.shop.web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user/")
@CrossOrigin(origins = "*")
public class UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @PostMapping("reg")
    @ResponseBody
    public Result<User> register(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd){
        Result<User> result = userService.register(userName,pwd);

        return result;
    }

    @PostMapping("login")
    public Result<Map> login(@RequestParam("userName") String userName, @RequestParam("pwd")  String pwd, HttpServletRequest request, HttpServletResponse response){
        Result<User> result = userService.login(userName, pwd);
        if (!result.isSuccess()){
            Result<Map>result1 = new Result<>();
            result1.setMessage(result.getMessage());
            return result1;
        }


        Map<String,String> map = new HashMap();
        map.put("userId",String.valueOf(result.getData().getId()));
        map.put("userName",result.getData().getName());
        map.put("pwd",result.getData().getPwd());


        String token = JWTUtils.getToken(map);

        Map<String,String>map1 = new HashMap<>();
        map1 =  JWTUtils.getClaims(token);
        map1.put("token",token);
        Result<Map> result1 = new Result<>();
        result1.setData(map1);
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
        System.out.println(token);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("update")
    public Result<User> update(@RequestParam(value = "nickName" ,required = false)  String nickName,@RequestParam(value = "address",required = false)  String address, @RequestParam(value = "mobile",required = false)  String mobile, HttpServletRequest request){
        Result<User>result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String>map = JWTUtils.getClaims(token);
        long id = Long.parseLong(map.get("userId"));

        result = userService.update(id,nickName,mobile,address);
        return result;
    }
    @GetMapping("all")
    public Result<List<User>> getAllUser(){
        Result<List<User>> result = userService.getAllUser();
        return result;
    }
    @GetMapping("home")
    public Result<User> getHome(HttpServletRequest request){

       Result<User> result = new Result<>();
       String token = request.getHeader("token");
       if (token==null){
           result.setMessage("无法从header获取token");
           return result;
       }
       Map<String,String>map = JWTUtils.getClaims(token);
       User user = userDAO.findByUserId(Long.parseLong(map.get("userId"))).toModel();
       result.setData(user);
       result.setSuccess(true);

       return result;
    }
    @PostMapping("getUser")
    public Result<User> getUser(@RequestParam("userId")long userId){
        Result<User>result = userService.getUser(userId);
        return result;
    }
    @PostMapping("get/by/name")
    public Result<User> getByName(@RequestParam("userName")String userName){
        Result<User>result = userService.findByName(userName);
        return result;
    }

}
