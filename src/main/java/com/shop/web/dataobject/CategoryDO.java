package com.shop.web.dataobject;

import com.shop.web.model.Category;

public class CategoryDO {
    private long id;
    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category toModel(){
        Category category= new Category();
        category.setId(getId());
        category.setName(getName());
        category.setDescription(getDescription());
        return category;
    }
}
