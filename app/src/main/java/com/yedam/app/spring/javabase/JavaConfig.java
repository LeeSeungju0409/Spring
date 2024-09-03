package com.yedam.app.spring.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig { // xml을 자바방식으로.
	@Bean
	public Chef chef() { // 단일로만 할거면 이렇게만 하면 됨.
		return new Chef(); // 반환하면 spring은 바로 등록하겠다 이런거임.
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		//return new Restaurant(chef); // 생성자 주입 기반
		Restaurant res = new Restaurant();//세터 주입방식
		res.setChef(chef);
		return res;
	}
	
	

}
