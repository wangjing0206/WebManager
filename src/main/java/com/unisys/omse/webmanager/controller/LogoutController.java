package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {
    @RequestMapping(value = "/userLogout")
    public Object userLogout(HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            return 1;
        }  catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
