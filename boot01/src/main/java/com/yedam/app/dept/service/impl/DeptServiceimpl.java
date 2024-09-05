package com.yedam.app.dept.service.impl;

import java.util.List;

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
	
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

}
