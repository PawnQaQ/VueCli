package com.liu.Test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestMD5 {
    @Test
    public void testMD5(){
        Md5Hash md5Hash = new Md5Hash("123");//使用构造方法
        System.out.println(md5Hash.toHex());//toHex转为16进制
        //202cb962ac59075b964b07152d234b70
    }

    @Test
    public void testMD5andSalt(){
        Md5Hash md5Hash = new Md5Hash("123","a01sf");//可以随机生成salt 这里先固定
        System.out.println(md5Hash.toHex());//toHex转为16进制
        //418fe0447e4fd894dd103b853e05b73e
    }
    @Test
    public void testMD5andSaltandHash(){
        Md5Hash md5Hash = new Md5Hash("123","a01sf",1024);//可以随机生成salt 这里先固定
        System.out.println(md5Hash.toHex());//toHex转为16进制
        //a69ecc0101ac4acde6a935daffa6fb44
    }
}
