package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里用一句话描述这个类的作用
 *
 * @author linwl
 * @date 2024/3/29 10:44
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public String test(@RequestParam("user")String user, @RequestParam("password")String password) {
        return loginService.login(user, password);
    }

    @GetMapping("/get")
    public String test() {
        return "test";
    }
}
