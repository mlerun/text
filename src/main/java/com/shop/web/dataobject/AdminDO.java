package com.shop.web.dataobject;

import com.shop.web.model.Admin;

public class AdminDO {
    private long id;
    private String name;
    private String pwd;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Admin toModel(){
        Admin admin=new Admin();
        admin.setId(getId());
        admin.setPwd(getPwd());
        admin.setName(getName());
        return admin;
    }
}
