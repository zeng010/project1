package com.tjhd.project1.firstproject.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjhd.project1.firstproject.bean.Blog;
import com.tjhd.project1.firstproject.result.Result;
import com.tjhd.project1.firstproject.service.BlogService;
import com.tjhd.project1.firstproject.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-16 12:40
 */

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/all")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage,5);
        Page pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam long id) {

        Blog byId = blogService.getById(id);
        Assert.notNull(byId,"该博客已经被删除！");

        return Result.succ(byId);
    }

    @PostMapping("/del")
    public Result del(@RequestParam long id){
        boolean b = blogService.removeById(id);
        if (b) {
            return Result.succ("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp = null;

        if (blog.getId() != null){
            temp = blogService.getById(blog.getId());
            log.info("根据id查的用户为："+temp);
            log.info("temp的id为："+temp.getUserId().longValue());
            log.info("shiroutil的id为："+ShiroUtil.getProfile().getId());
            // 只能编辑自己的文章   temp.getUserId() == ShiroUtil.getProfile().getId(),
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else {

            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(new Date());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");

        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }


}
