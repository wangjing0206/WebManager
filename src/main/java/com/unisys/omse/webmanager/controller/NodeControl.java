package com.unisys.omse.webmanager.controller;

import com.unisys.omse.webmanager.po.TblNode;
import com.unisys.omse.webmanager.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class NodeControl {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        TblNode tblNode=null;
        logger.debug("get nodeInsert message."+nodeid);

        if (laststarttime.contains("T")){
            laststarttime=laststarttime.replaceAll("T"," ");
        }

        if (laststoptime.contains("T")){
            laststoptime=laststoptime.replaceAll("T"," ");
        }

        try {
            tblNode = new TblNode(
                    Integer.parseInt(nodeid),nodename,
                    Integer.parseInt(configid),ipaddress,status,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststarttime),
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststoptime)
            );
            logger.debug("get nodeInsert message."+req.getQueryString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nodeService.nodeInsert(tblNode);
    }

    @RequestMapping("/nodeDelete")
    public Object nodeDelete(HttpServletRequest req) {
        String id=req.getParameter("id");
        return nodeService.nodeDelete(Integer.parseInt(id));
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

        try {
            tblNode = new TblNode(
                    0,nodename,
                    0,ipaddress,status,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststarttime),
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(laststoptime)
            );
            tblNode.setId(Integer.parseInt(id));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return nodeService.nodeUpdate(tblNode);
    }

    @RequestMapping("/nodeSelectOne")
    public Object nodeSelectOne(HttpServletRequest req) {

        logger.debug("get nodeSelectOne message."+req.getQueryString());

        String id=req.getParameter("id");
        TblNode tblNode=new TblNode();
        tblNode.setId(Integer.parseInt(id));

        return nodeService.nodeSelectOne(tblNode);
    }

    @RequestMapping("/nodeSelectAll")
    public Object nodeSelectAll(HttpServletRequest req) {

        logger.debug("get nodeSelectAll message."+req.getQueryString());

        String whichNum=req.getParameter("whichNum");
        logger.debug("whichNum=."+whichNum);
        return nodeService.nodeSelectAll(Integer.parseInt(whichNum));
    }
}
