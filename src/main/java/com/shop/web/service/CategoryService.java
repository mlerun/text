package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {
    public Result<List<Category>> findAll();
    public Result<Category> add(Category category);
    public Result<Category> update(Category category);
    public Result<Category> findByCategoryId(@Param("id")long id);
    public Result<Category> findByCategoryName(@Param("name")String name);
}
