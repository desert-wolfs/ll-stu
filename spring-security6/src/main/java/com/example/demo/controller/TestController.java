package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里用一句话描述这个类的作用
 *
 * @author linwl
 * @date 2024/3/29 10:44
 */
@RestController
@RequestMapping("/public")
public class TestController {
    @GetMapping("/get")
    public String test() {
        return "test";
    }
}
