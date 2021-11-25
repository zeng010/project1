package com.tjhd.project1.firstproject;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjhd.project1.firstproject.bean.Blog;
import com.tjhd.project1.firstproject.bean.User;
import com.tjhd.project1.firstproject.mapper.BlogMapper;
import com.tjhd.project1.firstproject.mapper.UserMapper;
import com.tjhd.project1.firstproject.service.BlogService;
import com.tjhd.project1.firstproject.service.UserService;
import com.tjhd.project1.firstproject.service.impl.BlogServiceImpl;
import com.tjhd.project1.firstproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FirstprojectApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        System.out.println("hello world");
    }

    @Test
    void test2(){
        User user = userMapper.selectOne(null);
        System.out.println(user);
//        User one = userService.getOne(null);
//        System.out.println("---->"+one);
//        Blog one1 = blogService.getById(1);
//        System.out.println("========>>>>"+one1);
    }

    @Test
    void test3(){
        Page<Blog> page = new Page<>(2,1);
        Page<Blog> page1 = blogMapper.selectPage(page, null);
        System.out.println("总数据：--->"+page1.getTotal());
        System.out.println("记录为：--->"+page.getRecords());

        Page<Blog> page2 = blogService.page(page, null);
        System.out.println("service查询的分页数据为：--->"+page2.getRecords());
    }

    @Test
    void test4(){
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        for (String s1 : s) {
            System.out.println(s);
        }
    }
}
