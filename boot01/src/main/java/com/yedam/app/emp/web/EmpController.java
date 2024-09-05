package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller // Route 등록. Route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => View
//@RequiredArgsConstructor
//public class EmpController {
//	// 해당 컨트롤러에서 제공하는 서비스
//	private final EmpService empService; //롬복이 가지는 annotation중 하나가 @RequiredArgsConstructor
//}
public class EmpController { //위에거 헷갈리면 그냥 이렇게.
	// 해당 컨트롤러에서 제공하는 서비스
	private final EmpService empService; 
	
	@Autowired
	EmpController(EmpService empService){
		this.empService = empService;
	}
	
	// GET : 조회, 빈 페이지
	// POST : 데이터 조작 (등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList")
	public String empList(Model model) {
							// Model = Request + Response // 두 개를 하기 귀찮으니까 내부에서 처리하는 방식. HttpRequest로 굳이 불러올 필요가 없다.
		// 1) 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); // 타임리프 할때 이 부분 주의 / 파일 열때 데이터 전달하기위한 메소드여서. //list가 아니라 "emps" 이걸로 호출. //th:block에서
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정 
		// 슬러시 붙여서 /emp/list 이렇게 하면 /templates//emp/list.html 이렇게 되기 때문에 return에서는 /붙으면 안됨.
		// prefix + return + suffix => 실제 경로 / ViewResolver
		// classpath:/templates/emp/list.html
		
	}
	// 단건조회 : Get => QueryString, employeeId(커맨드 객체 or @RequestParam)
	@GetMapping("empInfo") // empInfo?key=value //key에 employeeId가 넘어가야 함./?key=value > 쿼리스트링.
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		// HttpServletRequest.setAttribute();
		return "emp/info";
		// classpath:/templates/emp/info.html => 실제 경로
	}
	
	
	// 등록 - 페이지 : Get // 값을 입력 받아야 함. 
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : Post => form태그를 통한 submit
	//				   => QueryString (커맨드 객체)
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		
		String url = null;
		
		if(eid > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId=" + eid;
			// redirect: 가 가능한 경우 = GetMapping // post, put, delete 전부 안됨.
		}else {
			// 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	
	// 수정 - 페이지 : Get, 조건이 필요 <=> 단건조회
	@GetMapping("empUpdate") // empUpdate?employeeId=value
	public String empUpdateForm(EmpVO empVO, Model model) {
			EmpVO findVO = empService.empInfo(empVO);
			model.addAttribute("emp", findVO);
			return "emp/update";

	}
	
	
	// 수정 - 처리 : AJAX => QueryString
	//@PostMapping("empUpdate")
	@ResponseBody // AJAX
	public Map<String, Object>
		empUpdateAJAXQueryString(EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	// 수정 - 처리 : AJAX => JSON (@RequestBody)
	@PostMapping("empUpdate")
	@ResponseBody // AJAX
	public Map<String, Object>
		empUpdateAJAXJSON(@RequestBody EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	// 삭제 - 처리 : Get => Querystring( @RequestParam ) // 웬만하면 AJAX 안 씀
	@GetMapping("empDelete") //empDelete?employeeId=value
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	}
	
}
																																																	