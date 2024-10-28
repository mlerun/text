package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.model.*;
import com.shop.web.service.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/")
@CrossOrigin(origins = "*")
public class AdminAPI {
    @Autowired
    private AdminService adminService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;

    @PostMapping("login")
    public Result<Map> login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd, HttpServletRequest request){
        Result<Admin> result = adminService.login(userName,pwd);
        if (!result.isSuccess()){
            Result<Map>result1 = new Result<>();
            result1.setMessage(result.getMessage());
            return result1;
        }
        Result<Map>result1 = new Result<>();
        Map<String,String> map = new HashMap<>();
        map.put("adminId",String.valueOf(result.getData().getId()));
        String token = JWTUtils.getToken(map);
        map.put("token",token);
        map.put("adminName",result.getData().getName());
        result1.setData(map);
        result1.setSuccess(true);
        return result1;
    }

    @GetMapping("logout")
    public Result logout(HttpServletRequest request){
        Result result = new Result();
        request.getSession().removeAttribute("adminId");
        result.setSuccess(true);
        return result;
    }

    @PostMapping("audit")
    public Result<Goods> audit(@RequestParam("goodsId") long goodsId){
        Result<Goods> result = goodsService.audit(goodsId);
        return result;
    }

    @GetMapping("home")
    public Result<AdminHome> getHome(){
        Result<AdminHome> result = adminService.getAdminHome();
        return result;
    }
    @GetMapping("order")
    public Result<List<Order>> getOrders(){
        Result<List<Order>> result = orderService.findAll();
        return result;
    }
    @GetMapping("user")
    public Result<List<User>> getUsers(){
        Result<List<User>> result = userService.getAllUser();
        return result;
    }
    @GetMapping("shop")
    public Result<List<Shop>> getShops(){
        Result<List<Shop>> result = shopService.findAll();
        return result;
    }
    @GetMapping("category")
    public Result<List<Category>> getCategories(){
        Result<List<Category>>result = categoryService.findAll();
        return result;
    }
    @GetMapping("goods")
    public Result<List<Goods>> getGoods(){
        Result<List<Goods>>result = goodsService.findAll();
        return result;
    }
    @GetMapping("comment")
    public Result<List<Comment>> getComments(){
        Result<List<Comment>> result = commentService.findAll();
        return result;
    }
    @PostMapping("category/add")
    public Result<Category> addCategory(@RequestParam("categoryName")String categoryName,@RequestParam("des")String des){
        Category category = new Category();
        category.setName(categoryName);
        category.setDescription(des);
        Result<Category>result = categoryService.add(category);
        return result;
    }
    @PostMapping("category/update")
    public Result<Category> updateCategory(@RequestParam("des")String des,@RequestParam("id")long id){
        Category category = new Category();
        category.setDescription(des);
        category.setId(id);
        Result<Category>result=categoryService.update(category);
        return result;
    }

    @PostMapping("category/get")
    public Result<Category> getCategory(@RequestParam("categoryId")long categoryId){
        Result<Category>result = categoryService.findByCategoryId(categoryId);
        return result;
    }
    @GetMapping("error")
    public Result error(){
        return new Result();
    }
    @GetMapping("abnormal/account")
    public Result abnormalAccount(){
        Result result = new Result();
        result.setMessage("账号异常，已被冻结");
        return result;
    }

    @PostMapping("freeze")
    public Result<User> freezeUser(@RequestParam("userId")long userId){
        Result<User>result = new Result<>();
        result=userService.freezeUser(userId);
        return result;
    }

    @PostMapping("unfrozen")
    public Result<User> unfrozenUser(@RequestParam("userId")long userId){
        Result<User>result = new Result<>();
        result=userService.unfrozenUser(userId);
        return result;
    }
}
