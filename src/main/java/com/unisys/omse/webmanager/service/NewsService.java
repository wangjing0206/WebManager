package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.TblNews;

import java.util.List;

public interface NewsService {
    //insert
    public int newsInsert(TblNews tblNews);
    //delete
    public int newsDelete(int id);
    //update
    public int newsUpdate(TblNews tblNews);

    //selectCurrentNews
    public List<TblNews> newsSelectCurrentNews();
    //selectAll
    public List<TblNews> newsSelectAll(int whichNum);
    //selectById
    public TblNews newsSelectById(int id);
}
