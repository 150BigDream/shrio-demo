package com.example.shirodemo.controller;


import com.example.shirodemo.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zz
 * @since 2020-11-27
 */
@Controller
public class AccountController {
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登陆成功
            subject.login(usernamePasswordToken);
            Account account=(Account)subject.getPrincipal();
            subject.getSession().setAttribute("account",account);
            return "index";
        } catch (UnknownAccountException e) {
            //没有这个账户
            model.addAttribute("msg", "账号不存在");
            e.printStackTrace();
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码问题
            e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @GetMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "未授权，无法访问";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}

