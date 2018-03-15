package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSONObject;
import com.unisys.omse.webmanager.po.ViewUser;
import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import com.unisys.omse.webmanager.service.NewsService;
import com.unisys.omse.webmanager.service.UserRolePermissionService;
import com.unisys.omse.webmanager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class LoginController {
    @Autowired
    private UserRolePermissionService userRolePermissionService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/userAuthLogin")
    public Object userAuthLogin(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(req.getParameter("num"), req.getParameter("password"));
        try {
            System.out.println("1");
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
            ViewUser user=new ViewUser();
            user=userService.userSelectByNum(req.getParameter("num"));
            jsonObject.put("user", user);
            System.out.println("2");
        }  catch (Exception e) {
            jsonObject.put("msg", "登录失败");
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/unauth")
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "-1");
        map.put("msg", "未登录");
        return map;
    }

    @RequestMapping(value = "/getLoginUserRoles")
    public Object getLoginUserRoles(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("getLoginUserRoles:subject.getPrincipal():"+subject.getPrincipal());
        ViewUserRolePermission currentUserRole= (ViewUserRolePermission)subject.getPrincipal();
        List<ViewUserRolePermission> list0= userRolePermissionService.UserRolePermissionSelectAll(currentUserRole.getNum());
        Set roleSet=new HashSet();
        for (int i = 0; i < list0.size() ; i++) {
            roleSet.add(list0.get(i).getRoleName());
        }
        System.out.println("getLoginUserRoles:roleSet.size():"+roleSet.size());
        return roleSet;
    }
}
