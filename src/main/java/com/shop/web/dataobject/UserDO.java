package com.shop.web.dataobject;

import com.shop.web.model.User;

public class UserDO {
    private long id;
    private String name;
    private String pwd;

    private String nickName;
    private String mobile;
    private String address;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User toModel(){
        User user = new User();
        user.setId(getId());
        user.setName(getName());
        user.setNickName(getNickName());
        user.setPwd(getPwd());
        user.setAddress(getAddress());
        user.setMobile(getMobile());
        user.setStatus(isStatus());
        return user;
    }
}
