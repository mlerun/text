package com.shop.web.API;

import com.shop.web.common.JWTUtils;
import com.shop.web.common.Result;
import com.shop.web.model.Comment;
import com.shop.web.service.CommentService;
import com.shop.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/comment/")
@CrossOrigin(origins = "*")
public class CommentAPI {
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;
    @PostMapping("add")
    public Result<Comment> add(@RequestParam("goodsId")long goodsId, @RequestParam("contend")String contend, HttpServletRequest request){
        Result<Comment> result = new Result<>();
        String token = request.getHeader("token");
        if (token==null){
            result.setMessage("无法从header获取token");
            return result;
        }
        Map<String,String> map = JWTUtils.getClaims(token);
        long userId = Long.parseLong(map.get("userId"));
         result = commentService.add(contend,goodsId,userId);
        return result;
    }

    @PostMapping("goods/get")
    public Result<List<Comment>> getGoodsComment(@RequestParam("goodsId")long goodsId){
        Result<List<Comment>> result = commentService.findByGoodsId(goodsId);
        return result;
    }

    @PostMapping("delete")
    public Result delete(@RequestParam("commentId")long commentId){
        Result result = commentService.delete(commentId);
        return result;
    }



}
