package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Admin;
import com.shop.web.model.AdminHome;
import org.springframework.stereotype.Service;


public interface AdminService {
    public Result<Admin> login(String userName,String pwd);
    public Result<AdminHome> getAdminHome();

}
