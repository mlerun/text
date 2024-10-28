package com.shop.web.dataobject;

import com.shop.web.model.Comment;

import java.time.LocalDateTime;

public class CommentDO {
    long id;
    long goodsId;
    long userId;
    String content;
    LocalDateTime createdTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Comment toModel(){
        Comment comment = new Comment();
        comment.setContent(getContent());
        comment.setGoodsId(getGoodsId());
        comment.setId(getId());
        comment.setCreatedTime(getCreatedTime());
        comment.setUserId(getUserId());
        return comment;

    }
}
