package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceimpl implements DeptService {
	// DB연결 필요.
	private DeptMapper deptMapper;
	
	@Autowired
	DeptServiceimpl(DeptMapper deptMapper){
		this.deptMapper = deptMapper;
	}
	// 전체 조회
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}
	// 단건 조회
	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		// TODO Auto-generated method stub
		return deptMapper.selectDeptInfo(deptVO);
	}
	// 등록
	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}
	
	// 수정 : Map기반(보통 AJAX에서 많이 사용.)
	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result
		= deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		
		return map;
		
	}
	// 삭제
	@Override
	public Map<String, Object> deptDelete(int deptId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안될 경우 : {}
		// 삭제가 될 경우 : { "employeeId" : 104}
		int result = deptMapper.deleteDeptInfo(deptId);
		
		if(result == 1) {
			map.put("departmentId", deptId);
		}
		return map;
	}


}
