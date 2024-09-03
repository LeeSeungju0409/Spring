package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	//@Test
	void empList() {
		// 전체 조회 : 입력, X -> 출력, 1건 이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty()); //isEmpty가 false로 나오는걸 확인해야 함. true는 값이 비어있는 것. !붙였으니까 true.
	}
	
	//@Test
	void empInfo() {
		// 단건조회 : 입력, 사원번호(100) -> 출력, 사원이름(King)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
	}
	
	//@Test
	void empInsert() {
		// 사원 등록 : 입력, 사원 이름, 이메일, 업무
		// 			-> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdHong@gmail.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	//@Test
	void empInsertFull() throws ParseException {
		// 사원 등록 : 입력, 사원 이름, 이메일, 입사일자, 업무, 급여
		// 			-> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Han");
		empVO.setEmail("jhHan@gmail.com");
		SimpleDateFormat sdf 
			= new SimpleDateFormat("yyyy-MM-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(today);
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	
	//@Test
	void empDelete() {
		int result = empMapper.deleteEmpInfo(207);
		assertEquals(result, 1);
		
		result = empMapper.deleteEmpInfo(208);
		assertEquals(result, 1);
	}
	
	//@Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(206);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmail("kjHong@naver.com");
		
		int result
			= empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		
		assertEquals(result, 1);
	}
	
	@Test
	void empUpdateDynamic() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(206);
		empVO.setLastName("Kim");
		empVO.setJobId("HR_REP");

		int result
			= empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		assertEquals(result, 1);
	}

}
