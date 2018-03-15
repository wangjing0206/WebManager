package com.unisys.omse.webmanager.service.impl;

import com.unisys.omse.webmanager.dao.NewsDao;
import com.unisys.omse.webmanager.po.NewsStatistic;
import com.unisys.omse.webmanager.po.TblNews;
import com.unisys.omse.webmanager.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int newsInsert(TblNews tblNews) {
        return newsDao.newsInsert(tblNews);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int newsDelete(int id) {
        return newsDao.newsDelete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int newsUpdate(TblNews tblNews) {
        return newsDao.newsUpdate(tblNews);
    }

    @Override
    public List<TblNews> newsSelectCurrentNews() {
        return newsDao.newsSelectCurrentNews();
    }

    @Override
    public List<TblNews> newsSelectAll(int whichNum) {
        whichNum = whichNum<1?1:whichNum;
        return newsDao.newsSelectAll((whichNum-1)*10);
    }

    //selectById
    public TblNews newsSelectById(int id){
        return newsDao.newsSelectById(id);
    }

    public List<NewsStatistic> newsStatistic(){
        return newsDao.newsStatistic();
    }
}
