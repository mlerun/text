package com.shop.web.dao;

import com.shop.web.dataobject.UserDO;
import com.shop.web.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
   //@Select("select * from user where name = #{userName}")
    UserDO findByUserName(@Param("userName")String userName);
    //@Select("select * from user where id = #{userId}")
    UserDO findByUserId(@Param("id")long id);
   // @Select("select * from user")
    List<UserDO> findAll();

   // @Insert("insert into user(name,pwd,nickName) values (#{name},#{pwd},#{nickName})")
   // @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    int add(UserDO userDO);
   // @Update("update user set nickName=#{nickName}, mobile=#{mobile}, address=#{address} where id=#{id}")
   // @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    int update(UserDO userDO);

    int delete(@Param("id") long id);

}
