package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class BootBoardApplicationTests {

	@Autowired //필드주입 방식. 실제로 개발할 땐 쓰지 않음. getter setter없어서 주입 못함. @Autowired는 접근 지시자 무시함. 보안 취약.
	//매퍼 기반이니까 매퍼 불러옴.
	private BoardMapper boardMapper;
	
//	@Test
//	void contextLoads() {
//		assertNotNull(empMapper); // null 인지 아닌지(존재하는지 아닌지) //failure가 떠야하는데 안뜨는건 이건 notnull이기 때문에. //bean은 제대로 생성.
//	}
	
	//@Test
	void boardList() {
		// 전체 조회 : 입력, X -> 출력, 1건 이상
		List<BoardVO> list = boardMapper.selectBoardAll();
		System.out.println(list.get(0));
	}
	
	@Test
	void boardInsert() {
		//역진행. new연산자 가진 후 값을 채워넣는 형태.
//		BoardVO boardVO = new BoardVO();
//		boardVO.setTitle("Hong");
//		boardVO.setWriter("IT_PROG");
//		boardVO.setRegdate("IT_PROG");
//		int result = boardMapper.insertBoardInfo(boardVO);
//		assertEquals(result, 1);
		
		//새로운 방법
		BoardVO boardVO = BoardVO
							.builder() //@Builder
							.title("First board")
							.writer("Tester")
							.regdate(new Date())
							.build();
		System.err.println("before) " + boardVO.getBno());
		int result = boardMapper.insertBoardInfo(boardVO);
		System.err.println("after) " + boardVO.getBno());

		assertEquals(result, 1);
	}
	
	
//	
//	
//	
//	
//	
//	
//	
//	
//	//@Test
//	void boardInfo() {
//		// 단건조회 : 입력, 사원번호(100) -> 출력, 사원이름(King)
//		BoardVO boardVO = new BoardVO();
//		boardVO.setBno(1000);
//		
//		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
//		assertEquals(findVO.getTitle(), "King");
//	}
//	
//	//@Test
//	void boardInsertFull() throws ParseException {
//		// 사원 등록 : 입력, 사원 이름, 이메일, 입사일자, 업무, 급여
//		// 			-> 출력, 1
//		BoardVO boardVO = new BoardVO();
//		boardVO.set("Han");
//		boardVO.set("jhHan@gmail.com");
//		SimpleDateFormat sdf 
//			= new SimpleDateFormat("yyyy-MM-dd");
//		Date today = sdf.parse("2024-08-15");
//		boardVO.set(today);
//		boardVO.set("SA_REP");
//		boardVO.set(15000.0);
//		
//		int result = boardMapper.insertBoardInfo(boardVO);
//		assertEquals(result, 1);
//	}
//	
//	
//	//@Test
//	void boardDelete() {
//		int result = boardMapper.deleteBoardInfo(207);
//		assertEquals(result, 1);
//		
//		result = boardMapper.deleteBoardInfo(208);
//		assertEquals(result, 1);
//	}
//	
//	//@Test
//	void boardUpdate() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setEmployeeId(206);
//		
//		BoardVO findVO = boardMapper.selectEmpInfo(boardVO);
//		findVO.setEmail("kjHong@naver.com");
//		
//		int result
//			= boardMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
//		
//		assertEquals(result, 1);
//	}
//	
//	//@Test
//	void boardUpdateDynamic() {
//		BoardVO boardVO = new BoardVO();
//		boardVO.setEmployeeId(206);
//		boardVO.setLastName("Kim");
//		boardVO.setJobId("HR_REP");
//
//		int result
//			= boardMapper.updateEmpInfo(boardVO.getEmployeeId(), boardVO);
//		
//		assertEquals(result, 1);
//	}

}
