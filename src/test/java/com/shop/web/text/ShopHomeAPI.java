package com.shop.web.text;

import com.shop.web.model.Category;
import com.shop.web.model.Goods;
import com.shop.web.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class ShopHomeAPI {
    @GetMapping("/shop/home/api")
    @ResponseBody
    public Result show(){
        Goods goods = new Goods();
        goods.setId(1);
        goods.setName("nuc x15移动工作站");
        goods.setPrice(7000);
        goods.setDescription("intel旗下nuc系列的移动工作站");
        goods.setUrl("../assets/商品1.png");
        Result result = new Result();
        result.setData(goods);
        result.setCode("0");
        result.setSuccess(true);



        List list = new ArrayList();
        List<Goods>list1 = new ArrayList<>();
        List<Category>list2 = new ArrayList<>();
        list.add(list1);
        list.add(list2);



        return result;


    }

}
