package com.shop.web.dao;

import com.shop.web.dataobject.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentDAO {
    int add(CommentDO commentDO);
    int delete(@Param("id") long id);
    List<CommentDO> findAll();
    List<CommentDO> findByGoodsId(@Param("goodsId")long goodsId );
    CommentDO findByCommentId(@Param("commentId")long commentId);

}
