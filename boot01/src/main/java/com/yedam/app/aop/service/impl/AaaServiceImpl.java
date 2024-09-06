package com.yedam.app.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {

		private AaaMapper aaaMapper;
		@Autowired
		AaaServiceImpl(AaaMapper aaaMapper){
			this.aaaMapper = aaaMapper;
		}
		@Transactional // 연속하는 쿼리문 위에 트랜젝셔널 붙임
		@Override
		public void insert() {
			aaaMapper.insert("101"); // 연속하는 쿼리문
			aaaMapper.insert("a101"); // 연속하는 쿼리문
			// AopTest
			/* 오류가 뜸.
			 * org.springframework.dao.DataIntegrityViolationException: ### Error updating
			 * database. Cause: java.sql.SQLSyntaxErrorException: ORA-01722: invalid number
			 * 
			 * ### SQL: INSERT INTO aaa VALUES (?) ### Cause:
			 * java.sql.SQLSyntaxErrorException: ORA-01722: invalid number
			 * 
			 * ; ORA-01722: invalid number
			 * 
			 * Caused by: java.sql.SQLSyntaxErrorException: ORA-01722: invalid number
			 * 
			 * 
			 * 하지만 DB를 보면 101이라는 데이터는 들어가있다.
			 * 이러면 뭐가 진짜고 가짜인지 모름.
			 * 
			 * 해결 법 두 가지
			 * -@Transactional
			 * 		: AOP기반으로 움직임. 그래서 DB를 다루는 Mapper쪽이 아닌 Service밑에서 돈다.
			 * @Transactional으로 DB의 transaction 제어하기.
			 * 에러 발생 시 롤백돼서 DB뿐만아니라 Spring에서도 반영되도록 하는게 트랜젝셔널.
			 * 기본은 메소드단위에서 진행되지만 실제로는 원한다면 메소드보다 상위인 클래스와 인터페이스에서도 설정할 수 있음.
			 * 
			 */

			
		}

}
