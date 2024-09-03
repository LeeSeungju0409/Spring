package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// 전체 조회
	// 쿼리문 감싸는 형태
	public List<EmpVO> selectEmpAllList();
	
	// 단건 조회
	// 한 건만 넘어옴
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	// 등록
	// dml반환하면 정수값으로 변환되어서 void가 아니라 int로
	public int insertEmpInfo(EmpVO empVO); //한 객체에 대한 정보가 통째로 필요하니 VO
	
	// 수정
	//보통 단일로 하면 매개변수 하나. 쿼리문이 내부적으로 조인을 하거나 복잡할 때 두 개의 VO가 필요한 경우가 있음. 기본적으로 마이바티스는 하나로 처리하는데 이 때는 마이바티스쪽에서 예외사항으로 파악함. 
	//한 객체에 대한 정보가 통째로 필요하니 VO
	public int updateEmpInfo(@Param("eid")int empId, 
								@Param("emp")EmpVO empVO); 
	
	// 삭제
	public int deleteEmpInfo(int empId);
}
