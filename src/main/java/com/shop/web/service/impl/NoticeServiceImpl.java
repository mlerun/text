package com.shop.web.service.impl;

import com.shop.web.common.Result;
import com.shop.web.dao.NoticeDAO;
import com.shop.web.dataobject.NoticeDO;
import com.shop.web.model.Notice;
import com.shop.web.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDAO noticeDAO;
    @Override
    public Result<Notice> add(String contend) {
        Result<Notice> result = new Result<>();
        if (contend==null){
            result.setMessage("评论不能为空");
            return result;
        }
        NoticeDO noticeDO = new NoticeDO();
        noticeDO.setContent(contend);
        noticeDAO.add(noticeDO);
        Notice notice = noticeDO.toModel();
        result.setData(notice);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<Notice>> getAll() {
        Result<List<Notice>>result = new Result<>();
        List<NoticeDO>list = noticeDAO.findAll();
        List<Notice>list1 = new ArrayList<>();
        for (NoticeDO noticeDO:list){
            Notice notice = noticeDO.toModel();
            list1.add(notice);
        }
        result.setData(list1);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Notice> getNew() {
        Result<Notice> result = new Result<>();
        Notice notice = noticeDAO.getNew().toModel();
        if (notice==null){
            result.setMessage("无公告");
            return result;
        }
        result.setData(notice);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Notice> getOne(long id) {
        Result<Notice>result = new Result<>();
        if (id==0){
            result.setMessage("请输入公告id");
            return result;
        }
        Notice notice = noticeDAO.getById(id).toModel();
        result.setData(notice);
        result.setSuccess(true);
        return result;
    }
}
