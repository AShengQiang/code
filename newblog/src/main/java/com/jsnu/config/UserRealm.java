package com.jsnu.config;

import com.jsnu.pojo.User;
import com.jsnu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了shiro的授权方法。。。。");
        return null;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.checkUser(userToken.getUsername());
        //用户认证
        if (null == user) {//没有
            //LoginController层抛出UnknownAccountException------>用户名不存在异常
            return null;
        }
        //自定义盐值
        ByteSource salt=ByteSource.Util.bytes(user.getUsername());

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);
        //密码认证，shiro来做
        return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());

    }

}

