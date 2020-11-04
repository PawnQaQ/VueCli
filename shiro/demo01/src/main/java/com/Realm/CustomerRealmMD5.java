package com.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 对数据进行加密以后的Realm
 */

public class CustomerRealmMD5 extends AuthorizingRealm {
    //授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //获取身份信息 也就是用户的名字
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //输出身份信息
        System.out.println("身份信息："+primaryPrincipal);
        //根据身份信息 获取
        return null;
    }

    //认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //根据token获得用户名
        String username = (String) token.getPrincipal();
       //根据用户名对数据库进行操作
        if("xiaohei".equals(username)){//匹配成功
            //返回数据库中的密码(已经salt+md5+hash散列过了) + 提前在数据库中保存的salt
            String password="a69ecc0101ac4acde6a935daffa6fb44";
            //返回salt
            String salt="a01sf";
            //将所有的数据封装到SimpleAuthenticationInfo(这是AuthenticationInfo的实现类)
            return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(salt),this.getName());
        }
        return null;
    }
}
