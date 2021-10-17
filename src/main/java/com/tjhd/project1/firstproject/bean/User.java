package com.tjhd.project1.firstproject.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-13 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    private Integer Id;
    private String UserName;
    private String Avatar;
    private String Email;
    private String Password;
    private Integer Status;
    private Date Created;
    private Date LastLogin;
}
