package com.unisys.omse.webmanager.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class TblNews {
    private int id;
    private String title;
    private String content;
    private int currentNews;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createDate;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCurrentNews() {
        return currentNews;
    }

    public void setCurrentNews(int currentNews) {
        this.currentNews = currentNews;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public TblNews() {
    }

    public TblNews(String title, String content, int currentNews, Date createDate, Date updateDate) {
        this.title = title;
        this.content = content;
        this.currentNews = currentNews;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "TblNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", currentNews=" + currentNews +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
