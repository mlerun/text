package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.CategoryDAO;
import com.shop.web.dataobject.CategoryDO;
import com.shop.web.model.Category;
import com.shop.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Result<List<Category>> findAll() {
        Result<List<Category>>result = new Result<>();
        List<CategoryDO> list = categoryDAO.findAll();
        List<Category>list1 = new ArrayList<>();
        for(CategoryDO categoryDO:list){
            Category category = categoryDO.toModel();
            list1.add(category);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Category> add(Category category) {
        Result<Category>result = new Result<>();
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setDescription(category.getDescription());
        categoryDO.setName(category.getName());
        categoryDAO.add(categoryDO);
        result.setData(category);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Category> update(Category category) {
        Result<Category>result = new Result<>();
        CategoryDO categoryDO = categoryDAO.findByCategoryId(category.getId());
        categoryDO.setDescription(category.getDescription());
        categoryDAO.update(categoryDO);
        result.setData(categoryDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Category> findByCategoryId(long id) {
        Result<Category>result =new Result<>();
        Category category = categoryDAO.findByCategoryId(id).toModel();
        result.setSuccess(true);
        result.setData(category);
        return result;

    }

    @Override
    public Result<Category> findByCategoryName(String name) {
        Result<Category>result =new Result<>();
        Category category = categoryDAO.findByCategoryName(name).toModel();
        result.setSuccess(true);
        result.setData(category);
        return result;
    }
}
