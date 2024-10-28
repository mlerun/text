package com.shop.web.control;

import com.shop.web.common.Result;
import com.shop.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController

@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Result getUser(){
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
