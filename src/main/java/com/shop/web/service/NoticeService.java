package com.shop.web.service;

import com.shop.web.common.Result;
import com.shop.web.model.Notice;

import java.util.List;

public interface NoticeService {
    Result<Notice> add (String contend);
    Result<List<Notice>> getAll();
    Result<Notice> getNew();
    Result<Notice> getOne(long id);
}
