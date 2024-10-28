package com.shop.web.dao;

import com.shop.web.dataobject.NoticeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NoticeDAO {
    int add(NoticeDO noticeDO);
    List<NoticeDO> findAll();
    NoticeDO getNew();
    NoticeDO getById(@Param("id")long id);
}
