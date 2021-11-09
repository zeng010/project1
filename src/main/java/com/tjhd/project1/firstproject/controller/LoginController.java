package com.tjhd.project1.firstproject.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjhd.project1.firstproject.bean.LoginDto;
import com.tjhd.project1.firstproject.bean.User;
import com.tjhd.project1.firstproject.result.Result;
import com.tjhd.project1.firstproject.service.UserService;
import com.tjhd.project1.firstproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-21 23:05
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        log.info("----->登录账号密码为："+ loginDto.getPassword(), loginDto.getUserName());

        // 根据登录传过来的姓名查询数据库是否存在用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", loginDto.getUserName());

        User user = userService.getOne(wrapper);

        log.info("查询返回的用户为：----->"+ user);

        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }

        // 给用户生成token
        String jwt = jwtUtils.generateToke(user.getId());

        log.info("返回的jwt为："+jwt);

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expost-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUserName())
                .put("avatar", user.getAvatar())
                .put("eamil", user.getEmail())
                .map());
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){

        SecurityUtils.getSubject().logout();

        return Result.succ(null);
    }
}
