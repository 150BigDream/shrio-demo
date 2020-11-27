package com.example.shirodemo.realm;

import com.example.shirodemo.entity.Account;
import com.example.shirodemo.mapper.AccountMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AccountRealm extends AuthorizingRealm {
    @Autowired
    AccountMapper accountMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登陆的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account=(Account)subject.getPrincipal();

        //设置角色
        Set<String> roles=new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        //设置权限
        simpleAuthorizationInfo.addStringPermission(account.getPermis());
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;//父类转子类，usernamePasswordToken是包含用户名和密码的token
        Account account = accountMapper.findByUsername(usernamePasswordToken.getUsername());
        
        if (account!=null){
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());//校验密码
        }
        return null;
    }
}
