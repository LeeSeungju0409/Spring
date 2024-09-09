package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	//로직이 없으니 구현 클래스 필요함. com.yedam.app.dept.service.impl - DeptServiceimpl
	// 전체 조회
	public List<DeptVO> deptList();
	
	// 단건 조회
	public DeptVO deptInfo(DeptVO deptVO);
	
	// 등록
	public int deptInsert(DeptVO deptVO);
	
	// 수정
	public Map<String, Object> deptUpdate(DeptVO deptVO);
	// 삭제
	public Map<String, Object> deptDelete(int deptId);
}
