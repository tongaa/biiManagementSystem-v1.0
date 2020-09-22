package com.mengxugu2.demo.controller;


import com.mengxugu2.demo.entities.User;
import com.mengxugu2.demo.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Auther: 梦学谷
 */
@Controller
public class LoginController {
    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserMapper userMapper;






    @GetMapping("/toLogin")
    public String toLogin () {
        return "main/login";

    }

    @PostMapping("/login")
    public String login (HttpSession session, String username, String password, Map<String, Object> map) {
   //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
//封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        User user = userMapper.getUserByUsername(username);
        try {
            subject.login(token);
            session.setAttribute("loginUser", user);
            return "main/index";
        }
        catch (UnknownAccountException e) {
            map.put("msg", "用户名错误");

        }
        catch (IncorrectCredentialsException e) {
            map.put("msg", "密码错误");

        }
        return "main/login";
//        if(!StringUtils.isEmpty(username)
//                && !StringUtils.isEmpty(password)) {
//
//            User user = userMapper.getUserByUsername(username);
//            if(user != null && user.getPassword().equals(password)) {
//                //登录成功
//                session.setAttribute("loginUser", user);
//                //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
//                return "redirect:/main.html";
//            }
//
//
//        }
//
//        //登录失败
//        map.put("msg", "用户名或密码错误");
//
//        return "main/login";

    }
    /**
     * 退出登录
     * @return
     */
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        //1. 清空session中的用户信息
//        session.removeAttribute("loginUser");
//        //2. 再将session进行注销
//        session.invalidate();
//        //3. 返回登录页面
//        return "redirect:/index.html";
//    }

}
