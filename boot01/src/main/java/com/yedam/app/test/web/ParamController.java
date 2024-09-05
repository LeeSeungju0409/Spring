package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	// Content-type : application/x-www-form-urlencoded // 얘가 보내주는 데이터를 쿼리스트링이라고 함.
	// QueryString(질의문자열) : key=value&key=value&key=value&...
	// Method : 상관없음. (Get이던 Set이던지)
	
	// QueryString => 커맨드 객체 (어노테이션 X, 객체) // annotation쓰지않고 객체를 선언.
	@RequestMapping(path="comobj",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	// QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path="reqparm",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
			(@RequestParam Integer employeeId, //필수(Required true)
						   String lastName, //생략가능
			 @RequestParam(name="message",
					 	   defaultValue="No message",
					 	   required = true) String msg) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
				
	}
	
	// Content-type : application/json // 어노테이션 하나밖에 없음. 배열로 들어오는건 배열로, 객체로 들어오는건 객체로
	// JSON 포맷 : @RequestBody, 대상은 배열 or 객체 (동시에 X) // Request안의 Body를 파싱한다.
	// Method : POST, PUT //Get방식으로하면 인코딩 깨져버림
	// 객체
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList
			(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
					result += "\t employee_id : " + empVO.getEmployeeId();
					result += "\t last_name : " + empVO.getLastName();
					result += "\n";
		}
		return result;
	}
	
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값 (VO는 어려움)
	// Method는 상관없음. 데이터로 인식 안하기 때문에.
	// Content-type도 상관없음. 주로 단독보다는 다른 컨텐트타입과 결합해서 씀.		 
																	// 값 안넘기면 404		// 경로:		path/
	@RequestMapping("path/{id}") // 데이터가 들어오는 값이고 id라는 변수에 담겠다. // id = yk라는 값을 넘기고 싶다 => path/yk
	@ResponseBody
	public String pathVariable(@PathVariable String id) { // 그 아이디가 뭔지 = @PathVariable String id
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
}
