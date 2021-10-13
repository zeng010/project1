package com.tjhd.project1.firstproject.bean;

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
public class Blog {
    private Integer Id;
    private Integer UserId;
    private String Title;
    private String Description;
    private String Context;
    private Date Created;
    private Integer Status;
}
