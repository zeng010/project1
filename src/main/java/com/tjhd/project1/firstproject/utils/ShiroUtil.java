package com.tjhd.project1.firstproject.utils;

import com.tjhd.project1.firstproject.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author: Zzz_tjhd
 * @date: 2021-11-07 23:11
 */
public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
