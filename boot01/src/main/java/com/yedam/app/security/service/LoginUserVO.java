package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter // Getter를 이용해서 로그인 한 정보를 돌려받음.
public class LoginUserVO implements UserDetails { // 새터 필요없음. 생성자 기반으로 실제 값을 갖고있는 UserVO가 들어와야 함.
	private UserVO userVO;
	
	public LoginUserVO(UserVO userVO){
		this.userVO = userVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //getAuthorities가 가진 리턴타입을 정의한 것. >를 기준삼아서 ?가 들어간 걸 보면 재내릭표현. //Collection: framework(list, set, map : 공통된 인터페이스를 상속. 같은 메소드 쓴다.컬렉션을 기준삼아 이걸 구현하는 원하는 클래스를 기반으로 리턴해야 함. 여기서는 GrantedAuthority인터페이스를 상속하는 것중에 재내릭 타입을 사용 해야 함.
		List<GrantedAuthority> auths = new ArrayList<>(); //배열을 기반으로 동작할거 아니어서 심플하게 값을 담는 인스턴스를 만들었음.
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName())); // 내부에서 값을 가져올 때 끌어올 수 있도록( 인터페이스로 감싸는 구현클래스를 쓰는 이유).
		
		return auths;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정 만료 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 잠금 여부 // 정책 위배 시 계정 block할 때 쓰는.
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정 사용 여부
		// TODO Auto-generated method stub
		return true;
	}

}
