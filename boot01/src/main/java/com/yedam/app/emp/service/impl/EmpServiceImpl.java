
package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;


@Service // => AOP이 적용 가능한 Bean
public class EmpServiceImpl implements EmpService {
	private EmpMapper empMapper;
	
	@Autowired
	EmpServiceImpl(EmpMapper empMapper){
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		// int 타입으로 return한다고 했지만 결과값으로는 return한다고는 안했음. 
		// 삼항연산자 : 반드시 변수 사용. 단독 사용 불가. 무조건 값을 돌려주는 조건문.
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	// 수정 : Map기반(보통 AJAX에서 많이 사용.)
	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result 
		= empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		/* AJAX형태
		 * {
		 * 	"result" : true,
		 * 	"target" : {
		 * 					employeeId : 100,
		 * 					lastName : "King",
		 * 					...
		 * 				}
		 * }
		 */
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안될 경우 : {}
		// 삭제가 될 경우 : { "employeeId" : 104}
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		return map;
	}

}