package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unisys.omse.webmanager.po.TblNews;
import com.unisys.omse.webmanager.service.NewsService;
import com.unisys.omse.webmanager.websocket.WebSocketServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class NewsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    WebSocketServer webSocketServer;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/newsInsert")
    public Object newsInert(HttpServletRequest req){
        //0定义和收值
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String currentNews = req.getParameter("currentNews");
        long currentTime = System.currentTimeMillis();
        Date createDate = new Date(currentTime);
        Date updateDate = new Date(currentTime);

        //1封装到类中
        TblNews tblNews = new TblNews(
                title,
                content,
                Integer.parseInt(currentNews),
                createDate,
                updateDate );
        int retVal = newsService.newsInsert(tblNews);

        webSocketServer.sendMessageToClients("insert");
        return retVal ;
    }

    @RequestMapping("/newsDelete")
    public Object newsDelete(HttpServletRequest req){
        String  id = req.getParameter("id");
        int retVal =newsService.newsDelete(Integer.parseInt(id));
        webSocketServer.sendMessageToClients("delete");
        return retVal;
    }

    @RequestMapping("/newsUpdate")
    public Object newsUpdate(HttpServletRequest req){
        String id = req.getParameter("id");
        TblNews tblNews = newsService.newsSelectById(Integer.parseInt(id));

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String currentNews = req.getParameter("currentNews");
        long currentTime = System.currentTimeMillis();
        Date updateDate = new Date(currentTime);

        //1封装到类中
        tblNews.setTitle(title);
        tblNews.setContent(content);
        tblNews.setCurrentNews(Integer.parseInt(currentNews));
        tblNews.setUpdateDate(updateDate);

        int retVal =newsService.newsUpdate(tblNews);
        webSocketServer.sendMessageToClients("update");
        return retVal;
    }

    @RequestMapping("/newsSelectAll")
    public Object newsSelectAll(HttpServletRequest req){
        String whichNum = req.getParameter("whichNum");
        return newsService.newsSelectAll(Integer.parseInt(whichNum));
    }

    @RequestMapping("/newsSelectCurrentNews")
    public Object newsSelectCurrentNews(HttpServletRequest req){
        return newsService.newsSelectCurrentNews();
    }

    //newsSelectAllFromFastJson
    @RequestMapping("/newsSelectAllFromFastJson")
    public Object newsSelectAllFromFastJson(HttpServletRequest req){
        //0定义和收值
        String whichNum = req.getParameter("whichNum");
        return JSON.toJSONString(newsService.newsSelectAll(Integer.parseInt(whichNum)));
    }

    @RequestMapping("/newsSelectById")
    public Object newsSelectById(HttpServletRequest req){
        String id = req.getParameter("id");
        logger.info("id:"+id);
        return newsService.newsSelectById(Integer.parseInt(id));
    }

    @RequestMapping("/newsStatistic")
    public Object newsStatistic(HttpServletRequest req){
        return newsService.newsStatistic();
    }

}
