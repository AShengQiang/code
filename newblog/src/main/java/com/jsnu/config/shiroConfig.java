package com.jsnu.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    //配置shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro 内置过滤器
        /*
         * anno:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须拥有记住我功能才能用
         * perms:拥有对某个资源的权限
         * role:拥有某个角色权限才能访问
         * */
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/admin/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录url
        bean.setLoginUrl("/admin/login");
        return bean;
    }


    //配置DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //配置realm
    @Bean
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){

        UserRealm userRealm = new UserRealm();
        //设置密码加密方式
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    @Bean
    //告诉shiro我使用的是MD5加密方式，当后台上传数据时，shiro自动进行MD5加密
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        //加密用的方式  true 用hex；false 用base64
       hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return  hashedCredentialsMatcher;
    }

}
