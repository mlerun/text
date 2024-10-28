package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Comment;

import java.util.List;

public interface CommentService {
    public Result<Comment> add(String contend,long goodsId,long userId);
    public Result<List<Comment>> findAll();
    public Result delete(long commentId);
    public Result<List<Comment>> findByGoodsId(long goodsId);

}
