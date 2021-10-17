package com.tjhd.project1.firstproject.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-16 16:12
 */
public class JwtToken  implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
