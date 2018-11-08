package com.xiaobu.common.util;

import com.xiaobu.web.system.entity.SdManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 密码加密工具类
 */
public class PasswordUtil {


    /**
     * 加密密码
     * @param password
     * @Author  Luxinli
     * @return
     */
    public static String encryptionPwd(String password) {
        //获取十位随机串
        String randStr = RandomUtil.createRandomChar(10);
        //对用户表单输入密码进行首次md5加密
        String md5Password = MD5Util.MD5(password);
        //将加密过的password进行与随机数randStr进行一次md5加密
        String passWordandSalt = MD5Util.MD5(md5Password + randStr);
        //将随机生成的字符串截取前五位与后五位
        String before5 = randStr.substring(0, 5);
        String after5 = randStr.substring(5);
        //最终用户存进数据库的密码
        String encryption_pwd = before5 + passWordandSalt + after5;

        return encryption_pwd;
    }


    /**
     * 解密密码
     * @param password
     * @param password_db
     *  @Author  Luxinli
     * @return
     */
    public static List<String> decryptPwd(String password, String password_db){
        List<String> list = new ArrayList<String>();
        //首先對用戶表單中的password進行一次MD5加密
        String MD5password = MD5Util.MD5(password);
        //获取前五位
        String before5 = password_db.substring(0, 5);
        //获取后五位
        String after5 = password_db.substring(password_db.length()-5);
        //获取剔除盐之后的password
        String pwd =  password_db.substring(5, password_db.length()-5);
        //生成注册時隨機生成的盐
        String salt = before5+after5;
        String passWordandSalt = MD5Util.MD5(MD5password+salt);
        list.add(pwd);
        list.add(passWordandSalt);
        return list;
    }

    public  static  void main (String[] args){
        String en_pwd = encryptionPwd("123");
        System.out.println("加密之后的密码："+en_pwd);
        List<String> list = decryptPwd("123",en_pwd);
        for (String str:list) {
            System.out.println("解密之后的密码："+str);
        }

    }



}
