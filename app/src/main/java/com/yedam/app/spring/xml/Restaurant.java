package com.yedam.app.spring.xml;


public class Restaurant {
	// 생성자 injection Vs setter injection

	
	private Chef chef;
	
	// 생성자 인젝션
	Restaurant(Chef chef){
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}
	
	// 세터 인젝션
	Restaurant() {
		System.out.println("기본 생성자");
	}
	// 반드시 기본생성자가 필요. 위에 Restaurant가 있었으니까 public 안하고 그냥 썼음)
	// 생성자 없애서 하던가 아니면 세터에서 기본생성자 추가하던가.
	public void setChef(Chef chef) {
		this.chef = chef; // 이렇게 데이터를 만들어주고
		// spring에게 주입하라고 만들어주면됨(applicationContext.xml)
		// 생성자와 주입방식이 달라졌을뿐 방식은 똑같음.
		System.out.println("세터 인젝션");
		
	}
	
	public void run() {
		chef.cooking();
		
	}
}
