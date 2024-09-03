package com.yedam.app.spring.javabase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		
		Restaurant res 
			= (Restaurant) ctx.getBean(Restaurant.class); //관계기반으로 chef를 집어넣고있으니까 spring에서 처리해달라고 // 스프링에서는 셰프에 대해서만 알면 됨.
		res.run();
	}
}
