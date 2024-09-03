package com.yedam.app.spring.xml;

public class JavaMainExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chef cf = new Chef();
		Restaurant res = new Restaurant(cf);
		res.run(); // 셰프에 대한 정보를 직접 넣어줘야함 자바로 하면.
		
	}

}
