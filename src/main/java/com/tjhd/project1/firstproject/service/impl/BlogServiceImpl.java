package com.tjhd.project1.firstproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjhd.project1.firstproject.bean.Blog;
import com.tjhd.project1.firstproject.mapper.BlogMapper;
import com.tjhd.project1.firstproject.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-14 17:08
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}
