package com.yedam.app.web.config;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebAdvice {
	// @ControllerAdvice 역할 :
	// 예외 처리
	@ExceptionHandler(SQLException.class) //발생하는 에러 등록 ///SQLException 쓰면 내가 지정한 오류 뜸. /IllegalAccessException은 500번
	public ResponseEntity<String> invokeError(IllegalAccessException e){
		// IllegalAccessException e -> 변수 넣으면서 예외 발생하면서 객체 생성.
		return new ResponseEntity<>
			("Error Message", HttpStatus.BAD_REQUEST);
	}
	// 공통 변수 선언
	@ModelAttribute("contextPath")
	public String contextPath
			(HttpServletRequest request) {
		return request.getContextPath();
	}
}
