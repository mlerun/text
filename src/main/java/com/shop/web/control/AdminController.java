package com.shop.web.control;

import com.shop.web.common.Result;
import com.shop.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin")

    public Result getUser(HttpResponse response){

        Result result = new Result();
        result.setCode("0");
        Map map = new HashMap<>();
        map.put("username","LeRun");
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(map);
        result.setData(arrayList);
        return result;
    }
}
