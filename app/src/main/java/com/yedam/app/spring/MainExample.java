package com.yedam.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		 // file:src/main/resources/applicationContext.xml
		
		TV tv = (TV)ctx.getBean("tv"); //(TV) = 강제로 캐스팅 변환. // spring 기반으로 하면 new 연산자 아예 빠짐.
		tv.turnOn();
		
		
		TV subTv = (TV)ctx.getBean(TV.class); // TV.class : class가 가지고있는 정보 자체를 넘기는 것
		subTv.turnOn();
		
		
		
		// singleton
		if(tv == subTv) { // 싱글톤
			System.out.println("같은 빈입니다"); // 몇개를 해도 같은 빈으로 해서 만들어짐. 그래서 같은빈입니다 출력됨.
		} else {
			System.out.println("다른 빈입니다");
		}
		
	}

}