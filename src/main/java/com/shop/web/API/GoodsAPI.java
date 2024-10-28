package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.dao.CategoryDAO;
import com.shop.web.dao.GoodsDAO;
import com.shop.web.dao.ShopDAO;
import com.shop.web.dataobject.CategoryDO;
import com.shop.web.dataobject.GoodsDO;
import com.shop.web.model.Category;
import com.shop.web.model.Goods;
import com.shop.web.service.GoodsService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/goods/")
@CrossOrigin(origins = "*")
public class GoodsAPI {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @GetMapping("get/home")
    public Result<List> getGoodsToHome(){
        Result<List> result = new Result<>();
        Result<List<Goods>> result1 = goodsService.getGoodsToHome();
        List<CategoryDO> list = categoryDAO.findAll();
        List<Category>list1 = new ArrayList<>();
        for (CategoryDO categoryDO:list){
            Category category = categoryDO.toModel();
            list1.add(category);
        }
        List list2 = new ArrayList();
        list2.add(result1.getData());
        list2.add(list1);
        result.setData(list2);
        result.setSuccess(true);
        return result;
    }


    @PostMapping("add")
    public Result<Goods> add(@RequestParam("goodsName") String goodsName, @RequestParam("categoryName") String categoryName, @RequestParam("description") String description, @RequestParam("price") String price, @RequestParam("stock") String stock, @RequestParam("url") String url, HttpServletRequest request) {
        Result<Goods>result = new Result<>();
        if (StringUtils.isEmpty(goodsName)){
            result.setMessage("商品名不能为空");
            return result;
        }

        if (StringUtils.isEmpty(categoryName)){
            result.setMessage("商品类名不能为空");
            return result;
        }
        if (categoryDAO.findByCategoryName(categoryName)==null){
            result.setMessage("不存在该商品类别");
            return result;
        }
        if (StringUtils.isEmpty(description)){
            result.setMessage("商品描述不能为空");
            return result;
        }
        if (StringUtils.isEmpty(price)){
            result.setMessage("价格不能为空");
            return result;
        } if (StringUtils.isEmpty(stock)){
            result.setMessage("库存不能为空");
            return result;
        } if (StringUtils.isEmpty(url)){
            result.setMessage("图片不能为空");
            return result;
        }

        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long shopId = Long.parseLong(map.get("shopId"));
        long categoryId = categoryDAO.findByCategoryName(categoryName).getId();
        int stocks = Integer.parseInt(stock);
        double prices = Double.parseDouble(price);
        result=goodsService.add(goodsName,shopId,categoryId,description,prices,stocks,url);


        return result;
    }

    @PostMapping("update")
    public Result<Goods> update(@RequestParam("goodsId") long goodsId,@RequestParam(value = "description",required = false) String description,@RequestParam(value = "price",required = false) double price,@RequestParam(value = "stock",required = false) int stock,@RequestParam(value = "url",required = false) String url){
        Result<Goods> result = new Result<>();
        GoodsDO goodsDO = goodsDAO.findByGoodsId(goodsId);
        if (description==null&&price==0.0&&stock==0&&url==null){
            result.setMessage("请输入要更改的参数");
            return result;
        }
        if (description!=null){
            goodsDO.setDescription(description);
        }
        if (price!=0.0){
            goodsDO.setPrice(price);
        }
        if (stock!=0){
            goodsDO.setStock(stock);
        }
        if (url!=null){
            goodsDO.setUrl(url);
        }
        result=goodsService.update(goodsId,description,price,stock,url);

        return result;
    }

    @PostMapping("find")
    public Result<List<Goods>> find(@RequestParam("des")String des){
        Result<List<Goods>> result = goodsService.find(des);
        return result;
    }

    @PostMapping("delist")
    public Result delist(@RequestParam("goodsId")long goodsId){
        Result result = goodsService.delist(goodsId);
        return result;
    }
    @GetMapping("shop")
    public Result<List<Goods>> shop(HttpServletRequest request){
        Result<List<Goods>>result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long shopId = Long.parseLong(map.get("shopId"));
        result = goodsService.findByShopId(shopId);
        return result;
    }
    @PostMapping("getGoods")
    public Result<Goods> getGoods(@RequestParam("goodsId")long goodsId){

        Result<Goods>result = goodsService.getGoods(goodsId);
        return result;
    }

    @PostMapping("category")
    public Result<List<Goods>> getByCategoryId(@RequestParam("categoryId")long categoryId){
        Result<List<Goods>> result = goodsService.findByCategoryId(categoryId);
        return result;
    }

    @PostMapping("home/cate")
    public Result<List<Goods>> userCanGetGoodsByCategory(@RequestParam("categoryId")long categoryId){
        Result<List<Goods>> result = goodsService.findByCategoryId(categoryId);
        List<Goods> list = new ArrayList<>();
        for (Goods goods: result.getData()){
            if (goods.getStatus().equals("已上架")){
                list.add(goods);
            }
        }
        result.setData(list);
        return result;
    }

    @GetMapping("wait/audit/goods")
    public Result<List<Goods>> findWaitAuditGoods(){
        Result<List<Goods>>result=goodsService.findAllWaitAudit();
        return result;
    }

    @GetMapping("audited/goods")
    public Result<List<Goods>> findAuditedGoods(){
        Result<List<Goods>>result=goodsService.findAllAudited();
        return result;
    }
}
