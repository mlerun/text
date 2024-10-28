package com.shop.web.API;

import com.shop.web.common.Result;
import com.shop.web.model.Notice;
import com.shop.web.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/notice/")
@CrossOrigin(origins = "*")
public class NoticeAPI {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("add")
    public Result addNotice(@RequestParam("conten")String contend){
        Result result = noticeService.add(contend);
        return result;
    }

    @GetMapping("all")
    public Result<List<Notice>> getAllNotices(){
        Result<List<Notice>> result = noticeService.getAll();
        return result;
    }

    @GetMapping("new")
    public Result<Notice> getNew(){
        Result<Notice>result = noticeService.getNew();
        return result;
    }
    @PostMapping("one")
    public Result<Notice> getOneNotice(@RequestParam("id")long id){
        Result<Notice>result = noticeService.getOne(id);
        return result;
    }
}
