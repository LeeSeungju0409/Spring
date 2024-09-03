package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired //필드주입 방식. 실제로 개발할 땐 쓰지 않음. getter setter없어서 주입 못함. @Autowired는 접근 지시자 무시함. 보안 취약.
	private EmpMapper empMapper;
	
//	@Test
//	void contextLoads() {
//		assertNotNull(empMapper); // null 인지 아닌지(존재하는지 아닌지) //failure가 떠야하는데 안뜨는건 이건 notnull이기 때문에. //bean은 제대로 생성.
//	}
	
	@Test
	void empList() {
		// 전체 조회 : 입력, X -> 출력, 1건 이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty()); //isEmpty가 false로 나오는걸 확인해야 함. true는 값이 비어있는 것. !붙였으니까 true.
	}

}
