package com.tjhd.project1.firstproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjhd.project1.firstproject.bean.User;
import com.tjhd.project1.firstproject.mapper.UserMapper;
import com.tjhd.project1.firstproject.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-13 23:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
