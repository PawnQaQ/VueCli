package com.liu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestAuthenticator {
   @Test
    public void test(){
       //创建securityManager
       DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
       defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
       //在全局的工具类中 设置默认安全管理器
       SecurityUtils.setSecurityManager(defaultSecurityManager);
       //获取主体对象
       Subject subject = SecurityUtils.getSubject();
       //创建token令牌
       UsernamePasswordToken token = new UsernamePasswordToken("xiaobai", "456");
       try {
           subject.login(token);//用户登录认证
           System.out.println("登录成功~~");
       } catch (UnknownAccountException e) {
           e.printStackTrace();
           System.out.println("用户名错误!!");
       }catch (IncorrectCredentialsException e){
           e.printStackTrace();
           System.out.println("密码错误!!!");

       }
    }
}
