package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 첫 시작점. 건들지 말기.
@MapperScan(basePackages = "com.yedam.app.**.mapper") // 스프링은 기본적으로 기능중심. // ** => 기능을 말함. // 컴포넌트 스캔보다 범위가 적음. 둘다 결론적으로는 bean이지만 
public class Boot01Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot01Application.class, args);
	}

}
