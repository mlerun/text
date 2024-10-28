package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.CommentDAO;
import com.shop.web.dao.UserDAO;
import com.shop.web.dataobject.CommentDO;
import com.shop.web.model.Comment;
import com.shop.web.model.User;
import com.shop.web.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<Comment> add(String contend,long goodsId,long userId) {
        Result<Comment>result = new Result<>();
        CommentDO commentDO = new CommentDO();
        if (StringUtils.isEmpty(contend)){
            result.setMessage("请输入评论内容");
            return result;
        }
        commentDO.setContent(contend);
        commentDO.setGoodsId(goodsId);
        commentDO.setUserId(userId);
        commentDAO.add(commentDO);
        Comment comment = commentDO.toModel();

        result.setData(comment);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Comment>> findAll() {
        Result<List<Comment>> result = new Result<>();
        List<CommentDO>list=commentDAO.findAll();
        List<Comment>list1 = new ArrayList<>();
        for (CommentDO commentDO:list){
            Comment comment = commentDO.toModel();
            list1.add(comment);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result delete(long commentId) {
        Result result = new Result();
        if (commentDAO.findByCommentId(commentId)==null){
            result.setMessage("评论不存在");
            return result;
        }
        commentDAO.delete(commentId);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Comment>> findByGoodsId(long goodsId) {
        Result<List<Comment>> result = new Result<>();
        List<CommentDO>list=commentDAO.findByGoodsId(goodsId);
        List<Comment>list1 = new ArrayList<>();
        for (CommentDO commentDO:list){
            Comment comment = commentDO.toModel();
            User user = userDAO.findByUserId(comment.getUserId()).toModel();
            comment.setNickName(user.getNickName());
            list1.add(comment);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }
}
