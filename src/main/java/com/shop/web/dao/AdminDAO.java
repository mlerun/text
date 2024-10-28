package com.shop.web.dao;

import com.shop.web.common.Result;
import com.shop.web.dataobject.AdminDO;
import com.shop.web.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDAO {
    AdminDO findByName(@Param("adminName") String adminName);

}
