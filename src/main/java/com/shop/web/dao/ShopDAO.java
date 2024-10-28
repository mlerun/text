package com.shop.web.dao;

import com.shop.web.dataobject.GoodsDO;
import com.shop.web.dataobject.ShopDO;
import com.shop.web.model.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopDAO {
    //@Select("select * from shop where name=#{shopName}")
    ShopDO findByShopName(@Param("shopName")String shopName);
    ShopDO findByShopId(@Param("shopId")long shopId);
   // @Insert("insert into shop(name,pwd,mobile,address,owner,idCard)" +
    //      "values (#{name},#{pwd},#{mobile},#{address},#{owner},#{idCard})")
   // @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    int add(ShopDO shopDO);
   // @Update("")
    int update(ShopDO shopDO);
    int delete(@Param("shopId")long shopId);
   // @Select("select * from shop")
    List<ShopDO> findAll();
    ShopDO findByGoodsId(@Param("goodsId")long goodsId);
}
