package com.shop.web.dataobject;

import com.shop.web.model.Notice;

import java.time.LocalDateTime;

public class NoticeDO {
    long id;
    String content;
    LocalDateTime creatTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }
    public Notice toModel(){
        Notice notice = new Notice();
        notice.setId(getId());
        notice.setContent(getContent());
        notice.setCreatTime(getCreatTime());
        return notice;
    }
}
