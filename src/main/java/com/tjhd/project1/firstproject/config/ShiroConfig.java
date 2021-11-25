package com.tjhd.project1.firstproject.config;

import com.tjhd.project1.firstproject.shiro.AccountRealm;
import com.tjhd.project1.firstproject.shiro.JwtFliter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Zzz_tjhd
 * @date: 2021-10-16 15:25
 */
@Configuration
@Slf4j
public class ShiroConfig {

    // 获取application.yml参数，不能加static关键字

    @Autowired
    JwtFliter jwtFilter;


    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // redis中针对不同用户缓存（此处id需要对应user实体中的id字段，用于唯一标识）
        redisCacheManager.setPrincipalIdFieldName("id");
        // 用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * @return
     */
    @Bean
    public RedisManager redisManager(){

        RedisManager redisManager = new RedisManager();

        log.info("port---------------->"+"6379");

        // 老版本分别是setHost和setPort，新版本只需要setHost
        redisManager.setHost("localhost:6379");
        if (!StringUtils.isEmpty("redisPassword") ) {
            redisManager.setPassword("zeng0987");
        }
        return redisManager;
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setSessionDAO(redisSessionDAO);

        return sessionManager;
    }

    @Bean
    public SessionsSecurityManager securityManager(AccountRealm accountRealm,
                                                   SessionManager sessionManager,
                                                   RedisCacheManager redisCacheManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);

        securityManager.setSessionManager(sessionManager);

        securityManager.setCacheManager(redisCacheManager);

        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/**", "jwt"); // 主要通过注解方式校验权限

        chainDefinition.addPathDefinitions(filterMap);

        return chainDefinition;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt",jwtFilter);
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }
}

