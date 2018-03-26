package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSON;
import com.unisys.omse.webmanager.po.TblNode;
import com.unisys.omse.webmanager.service.NodeService;
import com.unisys.omse.webmanager.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class NodeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WebSocketServer webSocketServer;

    @Autowired
    private NodeService nodeService;

    @RequestMapping("/nodeInsert")
    public Object nodeInsert(HttpServletRequest req) {

        String nodeid=req.getParameter("nodeid");
        String nodename=req.getParameter("nodename");
        String configid=req.getParameter("configid");
        String ipaddress=req.getParameter("ipaddress");
        String status=req.getParameter("status");
        String laststarttime=req.getParameter("laststarttime");
        String laststoptime=req.getParameter("laststoptime");

        logger.error("nodeInsert.nodeid"+nodeid);
        logger.error("nodeInsert.nodename"+nodename);
        logger.error("nodeInsert.configid"+configid);
        logger.error("nodeInsert.ipaddress"+ipaddress);
        logger.error("nodeInsert.status"+status);
        logger.error("nodeInsert.laststarttime"+laststarttime);
        logger.error("nodeInsert.laststoptime"+laststoptime);
        Date ltoptime=null;

        if(laststarttime.indexOf("T")>-1){
            laststarttime = laststarttime.replaceAll("T"," ");
        }
        if(laststoptime.indexOf("T")>-1){
            laststoptime = laststoptime.replaceAll("T"," ");
            try {
                ltoptime=new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(laststoptime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        TblNode tblNode=null;

        try {
            tblNode = new TblNode(
                    Integer.parseInt(nodeid),nodename,
                    Integer.parseInt(configid),ipaddress,status,
//                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststarttime),
//                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststoptime)
                    new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(laststarttime),
                    ltoptime
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result=nodeService.nodeInsert(tblNode);
        if(result==1)
            webSocketServer.sendMessageToClients("Insert Successfully");

        return result;
    }

    @RequestMapping("/nodeDelete")
    public Object nodeDelete(HttpServletRequest req) {
        String id=req.getParameter("id");
        int result=nodeService.nodeDelete(Integer.parseInt(id));
        if(result==1)
            webSocketServer.sendMessageToClients("Delete Successfully");
        return result;
    }

    @RequestMapping("/nodeUpdate")
    public Object nodeUpdate(HttpServletRequest req) {

        String id=req.getParameter("id");
        String nodeid=req.getParameter("nodeid");
        String nodename=req.getParameter("nodename");
        String configid=req.getParameter("configid");
        String ipaddress=req.getParameter("ipaddress");
        String status=req.getParameter("status");
        String laststarttime=req.getParameter("laststarttime");
        String laststoptime=req.getParameter("laststoptime");
        TblNode tblNode=null;
        Date ltoptime =null;
        if(laststarttime.indexOf("T")>-1){
            laststarttime = laststarttime.replaceAll("T"," ");
        }
        if(laststoptime.indexOf("T")>-1){
            laststoptime = laststoptime.replaceAll("T"," ");
            try {
                ltoptime=new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(laststoptime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            tblNode = new TblNode(
                    Integer.parseInt(nodeid),nodename,
                    Integer.parseInt(configid),ipaddress,status,
//                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststarttime),
//                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststoptime)
                    new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(laststarttime),
                    ltoptime
            );
            tblNode.setId(Integer.parseInt(id));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return nodeService.nodeUpdate(tblNode);
    }

    @RequestMapping("/nodeSelectOne")
    public Object nodeSelectOne(HttpServletRequest req) {

        String id=req.getParameter("id");
        TblNode tblNode=new TblNode();
        tblNode.setId(Integer.parseInt(id));

        return nodeService.nodeSelectOne(tblNode);
    }

    @RequestMapping("/nodeSelectAll")
    public Object nodeSelectAll(HttpServletRequest req) {
        String whichNum=req.getParameter("whichNum");
        return JSON.toJSONString(nodeService.nodeSelectAll(Integer.parseInt(whichNum)));
    }
}
