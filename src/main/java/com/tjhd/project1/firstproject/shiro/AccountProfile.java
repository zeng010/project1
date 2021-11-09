package com.tjhd.project1.firstproject.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-17 15:48
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}
