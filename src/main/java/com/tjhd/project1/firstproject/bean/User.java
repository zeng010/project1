package com.tjhd.project1.firstproject.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    private Long id;
    @NotBlank(message = "账号不能为空")
    private String userName;
    private String avatar;
    @NotBlank(message = "邮箱不能为空")
    @javax.validation.constraints.Email(message = "邮箱格式不正确")
    private String email;
    private String password;
    private Integer status;
    private Date created;
    private Date lastLogin;
}
