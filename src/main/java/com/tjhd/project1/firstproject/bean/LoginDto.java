package com.tjhd.project1.firstproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-21 23:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
