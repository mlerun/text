package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.UserDAO;
import com.shop.web.dataobject.OrderDO;
import com.shop.web.dataobject.UserDO;
import com.shop.web.model.Order;
import com.shop.web.model.User;
import com.shop.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

@Autowired
    private UserDAO userDAO;
    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User>result = new Result<>();
        if (StringUtils.isEmpty(userName)) {

            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {

            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO != null) {

            result.setMessage("用户名已经存在");
            return result;
        }


        UserDO userDO1 = new UserDO();
        userDO1.setName(userName);
        // 初始昵称等于用户名
        userDO1.setNickName(userName);
        userDO1.setPwd(pwd);
        userDO1.setNickName(userName);
        userDO1.setStatus(true);
        userDAO.add(userDO1);

        result.setSuccess(true);

        result.setData(userDO1.toModel());


        return result;
    }

    @Override
    public Result<User> login(String userName, String pwd) {
        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {

            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {

            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {

            result.setMessage("用户名不存在");
            return result;
        }



        if (!StringUtils.equals(pwd, userDO.getPwd())) {

            result.setMessage("密码错误");
            return result;
        }
        if (!userDAO.findByUserName(userName).isStatus()){
            result.setMessage("账户异常");
            return result;
        }


        result.setSuccess(true);

        result.setData(userDO.toModel());

        return result;
    }



    @Override
    public Result<User> update(long id,  String nickName, String mobile, String address) {
        Result<User> result = new Result<>();
        UserDO userDO = userDAO.findByUserId(id);
        if (nickName==null&&mobile==null&&address==null){
            result.setMessage("请输入要修改的内容");
            return result;
        }
        userDO.setNickName(nickName);
        userDO.setAddress(address);
        userDO.setMobile(mobile);
        userDAO.update(userDO);
        result.setData(userDO.toModel());
        result.setSuccess(true);

        return result;
    }

    @Override
    public Result<List<User>> getAllUser() {
        Result<List<User>>result = new Result<>();
        List<UserDO>list = userDAO.findAll();
        List<User> list1 = new ArrayList<>();
        for (UserDO userDO:list){
            User user = userDO.toModel();
            list1.add(user);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<User> getUser(long userId) {
        User user = userDAO.findByUserId(userId).toModel();
        Result<User> result = new Result<>();
        result.setData(user);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<User> findByName(String userName) {
        Result<User>result = new Result<>();
        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO==null){
            result.setMessage("未搜索到该用户");
            return result;
        }
        result.setData(userDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<User> freezeUser(long userId) {
        UserDO userDO = userDAO.findByUserId(userId);
        userDO.setStatus(false);
        userDAO.update(userDO);
        Result<User>result = new Result<>();
        result.setData(userDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<User> unfrozenUser(long userId) {
        UserDO userDO = userDAO.findByUserId(userId);
        userDO.setStatus(true);
        userDAO.update(userDO);
        Result<User>result = new Result<>();
        result.setData(userDO.toModel());
        result.setSuccess(true);
        return result;
    }


}
