package com.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 这个类是用于与后端的数据库进行交互的
 * 主要的作用是将认证|授权的数据的来源转换为数据库来的
 * 就不用读取配置文件中的假数据了
 */

public class CustomerRealm extends AuthorizingRealm {
    //这个是用于授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //这个是用于认证的操作
    @Override
    /**
     * AuthenticationInfo 是根据token对数据库的操作拿到的一个实现类
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //在token中获取用户的信息-->获取用户名
        String principal =(String) token.getPrincipal();
        //System.out.println(principal); 打印一下用户名
        //可以根据这个信息去操作数据库 获得对应的参数
       if("xiaohei".equals(principal)){//假设查到信息
           //找AuthenticationInfo的实现类
           //SimpleAuthenticationInfo
           //参数1:返回数据库中正确的用户名   //参数2:返回数据库中正确密码  //参数3:提供当前realm的名字 this.getName();
           SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                   "xiaohei","123",this.getName());
           return simpleAuthenticationInfo;
       }

        return null;
    }
}
