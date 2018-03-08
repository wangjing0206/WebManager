package com.unisys.omse.webmanager.controller;

import com.unisys.omse.webmanager.po.TblNews;
import com.unisys.omse.webmanager.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class NewsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private NewsService newsService;

    @RequestMapping("/newsInsert")
    private Object newsInert(HttpServletRequest req){
        //0定义和收值
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int currentNews = 0;
        long currentTime = System.currentTimeMillis();
        Date createDate = new Date(currentTime);
        Date updateDate = new Date(currentTime);

        //1封装到类中
        TblNews tblNews = new TblNews(
                title,
                content,
                currentNews,
                createDate,
                updateDate );
        return newsService.newsInsert(tblNews);
    }

    @RequestMapping("/newsDelete")
    private Object newsDelete(HttpServletRequest req){
        String  id = req.getParameter("id");
        return newsService.newsDelete(Integer.parseInt(id));
    }

    @RequestMapping("/newsUpdate")
    private Object newsUpdate(HttpServletRequest req){
        String id = req.getParameter("id");
        TblNews tblNews = newsService.newsSelectById(Integer.parseInt(id));

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int currentNews = 0;
        long currentTime = System.currentTimeMillis();
        Date updateDate = new Date(currentTime);

        //1封装到类中
        tblNews.setTitle(title);
        tblNews.setContent(content);
        tblNews.setCurrentNews(currentNews);
        tblNews.setUpdateDate(updateDate);

        return newsService.newsUpdate(tblNews);
    }

    @RequestMapping("/newsSelectAll")
    private Object newsSelectAll(HttpServletRequest req){
        String whichNum = req.getParameter("whichNum");
        return newsService.newsSelectAll(Integer.parseInt(whichNum));
    }

    @RequestMapping("/newsSelectCurrentNews")
    private Object newsSelectCurrentNews(HttpServletRequest req){
        return newsService.newsSelectCurrentNews();
    }

    @RequestMapping("/newsSetCurrentNews")
    private Object newsSetCurrentNews(HttpServletRequest req){
        String id = req.getParameter("id");

        TblNews currentNews = newsService.newsSelectCurrentNews();
        if(currentNews!=null) {
            currentNews.setCurrentNews(0);
            newsService.newsUpdate(currentNews);
        }

        TblNews newCurrentNews = newsService.newsSelectById(Integer.parseInt(id));
        newCurrentNews.setCurrentNews(1);
        return newsService.newsUpdate(newCurrentNews);
    }

}
