package com.tjhd.project1.firstproject.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.javafx.beans.IDProperty;
import com.tjhd.project1.firstproject.config.LongJsonDeserializer;
import com.tjhd.project1.firstproject.config.LongJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-13 17:17
 * 解决后台long类型返回前端精度丢失问题：
 * 第一种：直接在增加注解 @JsonFormat(shape = JsonFormat.Shape.STRING)
 * 第二种：新建LongJsonSerializer和LongJsonDeserializer两个类，并在对应的字段上增加注解
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_blog")
public class Blog {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    private Integer status;
}
