package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.dataobject.UserDO;
import com.shop.web.model.Order;
import com.shop.web.model.User;

import java.util.List;

public interface UserService {
    public Result<User> register(String userName, String pwd);
    public Result<User> login(String userName,String pwd);

    public Result<User> update(long id,  String nickName, String mobile, String address);
    public Result<List<User>> getAllUser();
    public Result<User> getUser(long userId);
    public Result<User> findByName(String userName);
    public Result<User> freezeUser(long userId);
    public Result<User> unfrozenUser(long userId);


}
