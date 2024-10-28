package com.shop.web.dao;

import com.shop.web.dataobject.CategoryDO;
import com.shop.web.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CategoryDAO {
    CategoryDO findByCategoryId(@Param("categoryId")long categoryId);
    CategoryDO findByCategoryName(@Param("categoryName") String categoryName);
    List<CategoryDO> findAll();
    int add(CategoryDO category);
    int update(CategoryDO category);
    int delete(@Param("categoryId") long categoryId);
}
