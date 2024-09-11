package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	private BoardService boardService; // 필드 생성
	
	@Autowired // 필드 주입할 수 있도록 통로 여는 부분
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {// 값을 전달 - 정보를 model에 담아서 조회
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boards", list);
		return "board/boardList";
	// prefix + return + suffix
	// classpath:/templates/ + board/boardList + .html
	}
	
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	// QueryString
	// 1) 객체   : 커맨드 객체
	// 2) 단일값  : @RequestParam
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {// 값을 전달 - 정보를 model에 담아서 조회
		BoardVO findVO = boardService.boardInfo(boardVO); // 조건으로 들어간 매개변수와 동일한 이름으로 매개변수 선언하면 에러나서 
		model.addAttribute("board", findVO);
		
		return "board/boardInfo";
	}
	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)-submit을 쓰겠다. 반환이 아닌 redirect.
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert") // 같은 기능 다른 메소드로 썼기때문에 충돌나지않아서 페이지와 똑같이 들어감.
	public String boardInsertProcess(BoardVO boardVO) { // <form/>활용한 submit 쓰겠다. 
		int eid = boardService.insertBoard(boardVO);

		
		return "redirect:boardInfo?bno=" + eid;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// => 단건조회와 같음.
	@GetMapping("boardUpdate")
	public String deptUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProsess
							(@RequestBody BoardVO boardVO) {
		return boardService.updateBoard(boardVO);
	}
	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete") // QueryString : @RequestParam
	public String boardDelete(@RequestParam Integer no) {
		boardService.deleteBoard(no);
		return "redirect:boardList";
		
	}
}
