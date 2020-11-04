package com.liu.Test;

import com.Realm.CustomerRealmMD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 对密码进行加密以后的测试
 * 主要是MD5+hash散列+salt
 */
public class TestMD5SaltHahRealm {
    @Test
    public void test(){
        //创建SecurityManger对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //自定义的Realm 已经将密码加密 并且返回了salt
        CustomerRealmMD5 realmMD5 = new CustomerRealmMD5();

        //设置Realm使用的hash凭证匹配器 默认的算法是equals匹配 需要自己指定使用的算法等参数
        //1.创建凭证匹配器对象
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.指定匹配器使用的算法MD5
        matcher.setHashAlgorithmName("md5");
        //3.指定散列的次数
        matcher.setHashIterations(1024);

        //在自定义的Realm类中设置凭证匹配器
        realmMD5.setCredentialsMatcher(matcher);

        //在安全管理类中注入自定义的Realm对象
        securityManager.setRealm(realmMD5);
        //全局工具类 绑定需要操作的SecurityManger对象
        SecurityUtils.setSecurityManager(securityManager);

        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建token对象
        UsernamePasswordToken token = new UsernamePasswordToken("xiaohei","123");

        //主体对象根据token进行登录校验
        try {
            subject.login(token);
            System.out.println("登录成功...");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误!!!");
        }
        //认证通过 开始授权
        //1.基于角色权限的授权
        if(subject.isAuthenticated()){//认证通过
            //基于单个角色的权限控制
            System.out.println(subject.hasRole("super"));

        }

    }

}
