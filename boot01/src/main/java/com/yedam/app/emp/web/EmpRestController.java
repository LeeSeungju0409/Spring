package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@RestController // @Controller + 모든 메소드에 @ResponseBody를 적용
				// @ResponseBody : AJAX
@RequiredArgsConstructor
public class EmpRestController {
	// RestController 이 안에 있는 건 페이지를 생성하지 않음. / 모델을 사용하지 않는다라는 의미
	private final EmpService empService;
	@GetMapping("/restTest")
	public String restTest() {
		return "test/main";
	}
	
	// REST : 세 가지 어노테이션. 보내주는 데이터에 대에서만 결정. 페이지X
	
	// 전체 조회 : GET + URI(자원 => emps(사원. 여러가지가 있으니까 s붙임)) 페이지 필요없기 때문에 그냥 리턴.
	// REST => 사실상 Model 객체 사용하지 않음.
	@GetMapping("emps")
	public List<EmpVO> empList(){
		return empService.empList();
		//http://localhost:8099/emps
	}
	
	// 단건 조회 : GET + URI(자원 => emps)
	@GetMapping("emps/{employeeId}") // path variable 방식. 경로를 구성. 위치가 중요. 뒤에 /select 붙이면 http://localhost:8099/emps/101/select 이렇게 들어가야 함.
	public EmpVO empInfo(@PathVariable Integer employeeId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		return empService.empInfo(empVO);
		//http://localhost:8099/emps/101 => 경로에 값을 지정해서 넘겨주면 단건조회로 처리.
	}
	
	// 등록	  : POST + URI(자원 => emps)
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	// 수정	  : PUT + URI(자원 => emps)
	@PutMapping("emps/{employeeId}")
	public Map<String, Object>
			empUpdate(@PathVariable Integer employeeId, 
							@RequestBody EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		
		return empService.empUpdate(empVO);
	}
	
	// 삭제	  : DELETE + URI(자원 => emps)
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete
						(@PathVariable Integer employeeId){
		return empService.empDelete(employeeId);
	}
}
