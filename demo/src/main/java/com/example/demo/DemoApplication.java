package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //componentscan : bean임을 받아들이는? 어쩌고 ~하는 기능
public class DemoApplication { // 건들지말길. @SpringBootApplication에서 이 클래스 쓰는거..?

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); // SpringApplication.run() : 스프링부트 실행 // DemoApplication.class: @SpringBootApplication의 어노테이션 안에있는 클래스 정보가 들어옴.(마우스오버해보기)
	}

}
