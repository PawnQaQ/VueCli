package com.liu.Test;

import com.Realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 用于测试自定义的Realm 实现验证的信息是从数据库来的
 */
public class TestCustomerRealm {
    @Test
    public void test(){
        //1.创建securityManager类
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置自定义的realm 让校验的信息来自于数据库
        securityManager.setRealm(new CustomerRealm());
        //2.使用全局的工具类 加载securityManager
        SecurityUtils.setSecurityManager(securityManager);
        //3.使用工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //4.创建token
        UsernamePasswordToken token = new UsernamePasswordToken("xiaohei","123");
        //根据token进行登录验证
        try {
            subject.login(token);
            System.out.println("欢迎用户:"+token.getUsername()+"登录成功!");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("非法的用户名!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("非法的密码!");
        }
    }
}
