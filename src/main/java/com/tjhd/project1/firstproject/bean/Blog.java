package com.tjhd.project1.firstproject.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-13 17:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_blog")
public class Blog {
    private Long Id;
    private Long UserId;
    private String Title;
    private String Description;
    private String Content;
    private Date Created;
    private Integer Status;
}
