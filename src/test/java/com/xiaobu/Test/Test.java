package com.xiaobu.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.util.MD5Util;

public class Test {

    //过期时间
    private final static int expiresSecond = 1800000;
    public static void main(String[] args){
    	/*String password ="123456";
    	Random rand = new Random();
    	String randStr = "";
    	int count =0;
    	//生成随机数串，例：AZTR987dsds
    	*//*int index;
    	boolean[] flags =new boolean[SysMessage.letters.length];
    	for(int i=0;i<10;i++) {
    		do{
				index = rand.nextInt(SysMessage.letters.length); 
			}while(flags[index]==true);
			char c = SysMessage.letters[index];
			randStr += c;
			flags[index]=true;

    	}*//*
    	while(true) {
    		int index = rand.nextInt(SysMessage.letters.length);
    		char temp = SysMessage.letters[index];
    		randStr+=temp;
    		count++;
    		if(count==10) {
    			break;
    		}
    	}
    	//对用户表单输入密码进行首次md5加密
    	String md5Password = MD5Util.MD5(password);
    	//将加密过的password进行与随机数randStr进行一次md5加密
    	String passWordandSalt = MD5Util.MD5(md5Password+randStr);
    	String before5 = randStr.substring(0, 5);
    	String after5 = randStr.substring(5);
    	System.out.println(randStr);
    	System.out.println(before5);
    	System.out.println(after5);
    	System.out.println(passWordandSalt);
    	String user_password = before5+passWordandSalt+after5;
    	System.out.println(user_password);*/
        /*long nowMillis = System.currentTimeMillis();
        System.out.println(nowMillis);
        Date now = new Date(nowMillis);
        System.out.println(now);
        if (expiresSecond >= 0) {
            long expMillis = nowMillis + expiresSecond;
            System.out.println(expMillis);
            Date exp = new Date(expMillis);
            System.out.println(exp);
           // builder.setExpiration(exp).setNotBefore(now);
        }*/


        //System.out.println(alarmStartTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        int year = c.get(Calendar.YEAR);
        c.set(Calendar.DAY_OF_YEAR, 1);

        Timestamp alarmStartTime = Timestamp.valueOf(sdf.format(c.getTime()));
        System.out.println(alarmStartTime); // 第一天
        //System.out.println(firstday); // 第一天
        c.add(Calendar.YEAR, 1);
        c.set(Calendar.DAY_OF_YEAR, 0);
        Timestamp lastday = Timestamp.valueOf(sdf.format(c.getTime()));
        System.out.println(lastday); // 最后一天
        //System.out.println(lastday); // 最后一天
        System.out.println(year); // 年
    }
}
