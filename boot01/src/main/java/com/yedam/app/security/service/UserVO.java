package com.yedam.app.security.service;

import lombok.Data;

@Data
public class UserVO { 
	// select했던 결과 담을 수 있도록 처리
	private String loginId;
	private String password;
	private String roleName;
	
}
