package com.xiaobu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching
@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@EnableScheduling//启动定时器
//@MapperScan("microservice.qssj.mapper")//必须加这个，不加报错，如果不加，也可以在每个mapper上添加@Mapper注释
public class LittleBuApplication {
	
	/*@RequestMapping("/")  //默认访问index.jsp
    String home() {  
		System.out.println("进来了");	
		 return "index";
    } */
	

	public static void main(String[] args) {
		SpringApplication.run(LittleBuApplication.class, args);
	}
}
