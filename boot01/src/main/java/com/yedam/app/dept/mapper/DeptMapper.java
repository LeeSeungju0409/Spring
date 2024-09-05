package com.yedam.app.dept.mapper;

import java.util.List;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	// 전체 조회
	public List<DeptVO> selectDeptAll(); // 쿼리문 기준으로 list 구성
	// mybatis.mapper-locations=classpath:mapper/*/*.xml 기반으로 mapper 작성(mybatis 검색해서 mapper만들기)
	
	
	// 단건 조회
	
	// 등록
	
	// 수정
	
	// 삭제
}
