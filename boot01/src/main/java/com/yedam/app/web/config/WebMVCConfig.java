package com.yedam.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	@Value("${file.upload.path}") 
	private String uploadPath;

	// 리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { //registry 경로를 확장하는 것.
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addResourceHandlers(registry); // addResourceHandlers: static 폴더 말고 추가하고싶은 경우쓴다. 내가 원하는 폴더를 경로매핑하고싶을 때. 프로젝트 내부만이 아니라 프로젝트 설치된 PC까지 범위 확장. 
		registry
			.addResourceHandler("/imgs/**") // URL // 실제 물리적 위치를 addResourceLocations기반으로 등록./ 경로 매핑시 "/imgs/" 여기 까지만 하면 안됨. 여러개 하고싶으면 **(두 개) 붙여야 함. application.properties쪽처럼 로케이션에 관한 경로는 무조건 끝에 / 붙여야 함 아니면 경로 매핑 안됨..
			.addResourceLocations("file:///" + uploadPath); // 실제 경로
	}
}
