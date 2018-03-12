package com.unisys.omse.webmanager.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

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
}
