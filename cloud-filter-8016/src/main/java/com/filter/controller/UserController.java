package com.filter.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserInfo/{name}")
    public String getUserInfo(@PathVariable("name") String name) {
        return name;
    }
    @GetMapping("/create/{name}")
    public String create(@PathVariable("name") String name) {
        return "创建成功:"+name;
    }
}
