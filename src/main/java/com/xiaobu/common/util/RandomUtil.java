package com.xiaobu.common.util;

import java.util.Random;

import com.xiaobu.common.constant.SysMessage;

public class RandomUtil {

	/**
	 * 生产随机数串类
	 * @param 参数num是想要生产多少位的随机数 
	 * @return
	 */
	public static String createRandomChar(int num) {
		Random rand = new Random();
		String randStr = "";
		int count =0;
    	//生成随机数串，例：ZQEE2WXLWT
    	while(true) {
    		int index = rand.nextInt(SysMessage.letters.length);
    		char temp = SysMessage.letters[index];
    		randStr+=temp;
    		count++;
    		if(count==num) {
    			break;
    		}
    	}
    	
    	return randStr;
	}
}
