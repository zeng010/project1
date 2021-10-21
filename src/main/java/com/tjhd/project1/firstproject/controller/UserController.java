package com.tjhd.project1.firstproject.controller;

import com.tjhd.project1.firstproject.bean.User;
import com.tjhd.project1.firstproject.result.Result;
import com.tjhd.project1.firstproject.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-16 12:40
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User id = userService.getById(1);
        return Result.succ(id);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){

        return Result.succ(user);
    }
}
