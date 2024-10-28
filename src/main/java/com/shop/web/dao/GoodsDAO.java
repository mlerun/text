package com.shop.web.dao;

import com.shop.web.dataobject.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDAO {


    GoodsDO findByGoodsId(@Param("goodsId") long goodsId);
    GoodsDO findByGoodsName(@Param("goodsName") String goodsName);
    //@Select("select * from goods\n" +
     //       "        where status = #{status}")
    List<GoodsDO> findByGoodsStatus(@Param("status")String goodsStatus);
    List<GoodsDO> findAll();
    int addGoods(GoodsDO goodsDO);
    int update(GoodsDO goodsDO);

    List<GoodsDO> find(@Param("des")String des);
    List<GoodsDO> findByShopId(@Param("shopId")long shopId);
    List<GoodsDO> findByCategoryId(@Param("categoryId")long categoryId);

}
