package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean //비밀번호 암호화
	PasswordEncoder passwordEncoder() { //PasswordEncoder: 인터페이스
		return new BCryptPasswordEncoder(); // 시큐리티는 무조건 암호화. 기본값은 거절. 원하는걸 빈으로 등록해야 함.// 모든 비밀번호는 이 인코더를 기반으로 처리한다는 뜻.
											// BCrypt: 단방향 해쉬 알고리즘. 암호화됐지만 복포화된 마킹. 복구가 안됨. // 일반적으로 가장 많이 사용하는 알고리즘의 클래스.
	}
	/*		
	// 기본적으로 해보는 테스트
	@Bean // 메모리상 인증정보 등록 => 테스트 전용
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user // user = class. 일반적 유저라고 생각하면 됨.
			= User.builder() // 디자인 패턴 중 하나인 builder 개념으로 사용. 생성자 요구 안함.
			//VO생성하는거라고 생각하면 됨. username,password,roles - 생성자 기반으로 여러가지 오버로딩. String타입. 데이터타입은 들어가지만 명확한 값은 들어가지 못함. builder패턴은 그런 부분을 해결. 돌리기 전에 매개변수가 가져야 하는 값을 정확하게 해줌.
					.username("user1")
					.password(passwordEncoder().encode("1234")) // 암호화시킨상태로.
					.roles("USER") // ROLE_USER // 롤언더바 생략하라고 뜸. 권한은 그냥 유저로 됨.
				  //.authorities("ROLE_USER") // 풀네임으로 하고싶으면 어솔리티즈. 이거 자체가 권한. 자유롭게 가능. 이걸로 하면 ROLE_USER로 됨.
					.build();
		
		
		UserDetails admin
		= User.builder() 
				.username("admin1")
				.password(passwordEncoder().encode("1234"))
				//.roles("ADMIN") // ROLE_ADMIN // 이것도 복수니까 여러개 가능.
				.authorities("ROLE_ADMIN", "ROLE_USER") // 복수이니까 한 사람이 하나만이 아닌 여러 개 권한 가지는 것 가능.
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/

	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//적용될 Security 설정
		// => URI에 적용될 권한
		http
			.authorizeHttpRequests( authrize 
					-> authrize
					.requestMatchers("/", "/all").permitAll() //permitAll: 모두 사용할 수 있도록. 인증 안받는 대상. // /** 슬러시 밑에 하위 경로 모두 적용하겠다. -> 그냥 다 무시하고 접근가능. 그래서 그냥 /만 넣었음. 이렇게 되면 밑에 있는 requestMatcher이 안돈다.
					.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")// hasRole: 특정권한 가질때만 가능하도록. hasAnyRole : 하나만.
					.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // hasAuthority: 
					.anyRequest().authenticated() //anyRequest : 위에 정의된 걸(requestMatchers) 빼고 나머지 부분에 대해 일괄 처리. / authenticated: 관리자 권한. 권한 무시
					)
			.formLogin(formlogin -> formlogin
					.defaultSuccessUrl("/all")) // 성공 시 리다이렉트 경로.
			//.logout() -> 이건 팝업?을 보면 쓰지말라고 적혀있음. 그래서 그 표시가 없는 밑에방식으로 쓴다.
			.logout(logout -> logout
					.logoutSuccessUrl("/all")
					.invalidateHttpSession(true));
		
		http.csrf(csrf -> csrf.disable());//csrf끄기
		
		return http.build(); // build를 호출하면 SecurityFilterChain이 완성.
	}
}
