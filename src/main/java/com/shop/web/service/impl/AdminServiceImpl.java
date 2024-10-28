package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.*;
import com.shop.web.model.Admin;
import com.shop.web.model.AdminHome;
import com.shop.web.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ShopDAO shopDAO;

    @Override
    public Result<Admin> login(String userName, String pwd) {
        Result<Admin> result = new Result<>();
        if (StringUtils.isEmpty(userName)){
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)){
            result.setMessage("密码不能为空");
            return result;
        }
        if (adminDAO.findByName(userName)==null){
            result.setMessage("用户不存在");
            return result;
        }
        if (!pwd.equals(adminDAO.findByName(userName).getPwd())){
            result.setMessage("密码错误");
        }
        result.setData(adminDAO.findByName(userName).toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<AdminHome> getAdminHome() {
        AdminHome adminHome = new AdminHome();
        adminHome.setUserNumber(userDAO.findAll().size());
        adminHome.setShopNumber(shopDAO.findAll().size());
        adminHome.setGoodsNumber(goodsDAO.findAll().size());
        adminHome.setOrderNumber(orderDAO.findAll().size());
        adminHome.setWaitAuditGoods(goodsDAO.findByGoodsStatus("待审核").size());
        Result<AdminHome> result = new Result<>();
        result.setData(adminHome);
        result.setSuccess(true);
        return result;
    }
}
