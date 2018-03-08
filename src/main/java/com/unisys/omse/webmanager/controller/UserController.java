package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSON;
import com.unisys.omse.webmanager.po.ViewUser;
import com.unisys.omse.webmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/userInsert")
    private Object userInsert(HttpServletRequest req){
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        String tel=req.getParameter("tel");
        String num=req.getParameter("num");
        String sex=req.getParameter("sex");
        String roleId=req.getParameter("roleId");
        String groupId=req.getParameter("groupId");
        //String isDeleted=req.getParameter("isDeleted");
        //String createDate=req.getParameter("createDate");
        Date createDate=new Date();
        String remark=req.getParameter("remark");
        ViewUser viewUser=null;
        //1封装到一个类中
        viewUser=new ViewUser
                    (userName,password,tel,num,
                            Integer.parseInt(sex),
                            Integer.parseInt(roleId),
                            Integer.parseInt(groupId),
                            0,
                            createDate,
                            remark
                    );
        return userService.userInsert(viewUser);
    }
    @RequestMapping(value="/userDelete")
    private Object userDelete(HttpServletRequest req){
        String id=req.getParameter("id");
        return userService.userDelete(Integer.parseInt(id));
    }
    @RequestMapping("/userUpdate")
    private Object userUpdate(HttpServletRequest req){
        //0定义和收值
        String id=req.getParameter("id");
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        String tel=req.getParameter("tel");
        String num=req.getParameter("num");
        String sex=req.getParameter("sex");
        String roleId=req.getParameter("roleId");
        String groupId=req.getParameter("groupId");
        //String isDeleted=req.getParameter("isDeleted");
        String createDate=req.getParameter("createDate");
        String remark=req.getParameter("remark");
        ViewUser viewUser=null;
        //1封装到一个类中
        try {
            viewUser=new ViewUser
                    (userName,password,tel,num,
                            Integer.parseInt(sex),
                            Integer.parseInt(roleId),
                            Integer.parseInt(groupId),
                            0,
                            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(createDate),
                            remark
                    );
            viewUser.setId(Integer.parseInt(id));
            System.out.println(viewUser);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return userService.userUpdate(viewUser);
    }
    @RequestMapping(value="/userSelectOne")
    private Object userSelectOne(HttpServletRequest req){
        String id=req.getParameter("id");
        ViewUser viewUser=new ViewUser();
        viewUser.setId(Integer.parseInt(id));
        return userService.userSelectOne(viewUser);
    }
    @RequestMapping(value="/userSelectAll")
    private Object userSelectAll(HttpServletRequest req){
        String whichNum=req.getParameter("whichNum");
        return userService.userSelectAll(Integer.parseInt(whichNum));
    }
    @RequestMapping(value="/userSelectAllFromFastJson")
    private Object userSelectAllFromFastJson(HttpServletRequest req){
        String whichNum=req.getParameter("whichNum");
        return JSON.toJSONString(userService.userSelectAll(Integer.parseInt(whichNum)));
    }
    @RequestMapping(value="/parseJson")
    private Object parseJson(HttpServletRequest req){
        String whichNum=req.getParameter("whichNum");
        String json= JSON.toJSONString(userService.userSelectAll(Integer.parseInt(whichNum)));
        //json转换成数组，取第一个，转成String
        String json0 = JSON.parseArray(json).get(0).toString();
        String userName = JSON.parseObject(json0).get("userName").toString();
        return userName;
    }
}
