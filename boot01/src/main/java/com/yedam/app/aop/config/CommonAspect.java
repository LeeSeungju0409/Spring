package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

import lombok.extern.slf4j.Slf4j;


@Aspect // AOP의 설정을 선언
@Component // bean으로 등록. 
@Slf4j // lombok. 로그와 연동(로그를 출력하는 bean과 연동.)
public class CommonAspect {
	
	
	
	
	
	// 포인트컷 : 조인포인트(@Service의 메소드들) 중에서
	//		   Advice(횡단관심, 부가기능)이 적용 될 메소드 조건
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
	public void empPointCut() {} // @Pointcut("within(com.yedam.app.emp.service.impl.*)")이 정보를 empPointCut()가 들고있다.
	
	
	
	
	
	
	// @Before = 이 포인트컷이 동작하기 전에 이 메소드를 실행하겠다
	// Weaving : 포인트컷 + 동작시점 + Advice
	// 메서드로 동작해야하기때문에 하나의 bean으로 등록
	@Before("empPointCut()")
	public void beforeAdvice(JoinPoint joinPoint) { // JoinPoint - 지금 내가 요청한 포인트컷에 걸린 대상. 낚아챈 것. ppt의 GetSignature() 참고.
		// Advice를 구현
		//log.debug("AOP 적용"); // @Slf4j / 별도 임포트 하지않아도 log.을 이용하여 로그를 작성할 수 있도록 함. 스프링.
		
		// Advice를 구현
		String sinagerStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs();
		log.error("####### 실행 : " + sinagerStr);
		for(Object arg : args) {
			log.error("매개변수 ", arg);
			if(arg instanceof Integer) {
				System.err.println((Integer)arg);
				
			}else if(arg instanceof EmpVO) {
				System.err.println((EmpVO)arg);
			}
		}
			
	}
	
	

	@Around("empPointCut()")
	// 프로시딩 조인포인트 - 이 포인트는 joinpoint.proceed의 .proceed를 실행하는 것. 이 메소드가 실행하는 작업시작에 대해 알 수 있다. proceed가 동작하고나면 끝난시간까지.
	public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String signaterStr 
				= joinPoint.getSignature().toString();
		System.out.println("=== 시작 : " + signaterStr);
		
		// 공통기능
		System.out.println("=== 핵심 기능 전 실행 : " 
							+ System.currentTimeMillis());
		try {
			// 비즈니스 메소드를 실행
			Object obj = joinPoint.proceed();
			
			return obj;
		}finally {
			// 공통기능
			System.out.println("=== 핵심 기능 후 실행 : " 
							+ System.currentTimeMillis());
			System.out.println("=== 끝 : " + signaterStr);
		}
		// 원래 catch 구문이 있어야 하는데 지금은 finally때문에 이렇게 적음. finally는 무조건 실행보장하는 어쩌고로 넣는거니까
	}

	

}
