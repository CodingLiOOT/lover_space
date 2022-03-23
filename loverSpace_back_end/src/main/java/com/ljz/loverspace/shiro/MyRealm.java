package com.ljz.loverspace.shiro;

import com.ljz.loverspace.common.Constant;
import com.ljz.loverspace.entity.User;
import com.ljz.loverspace.service.UserService;
import com.ljz.loverspace.utils.token.JWTUtil;
import com.ljz.loverspace.utils.token.ShiroAuthToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroAuthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.getPrimaryPrincipal().toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加角色信息
        info.addRoles(userService.getRoles(username));
        //添加权限

        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("token is empty");
        }
        String userName= JWTUtil.getUsername(token);
        User user = userService.lambdaQuery().eq(User::getUsername, userName).one();
        if (user == null) {
            throw new AuthenticationException("user not found");
        }
        if (!JWTUtil.verify(token)) {
            throw new AuthenticationException("token is invalid");
        }
        return new SimpleAuthenticationInfo(token, Constant.STRING_EMPTY, this.getName());

    }


}
