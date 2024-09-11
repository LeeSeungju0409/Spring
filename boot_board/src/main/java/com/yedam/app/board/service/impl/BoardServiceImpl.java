package com.yedam.app.board.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service // AOP 동작 => @Transactional
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	
	@Autowired // 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		return result == 1? boardVO.getBno() : -1; //selectKey를 사용하기 때문에 getBno가 가능
	}

	@Override
	public Map<String, Object> updateBoard(BoardVO boardVO) { // AJAX. 수정은 페이지 리로드 안해도 됨.
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = boardMapper.updateBoardInfo(boardVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		String updateDate = getUpdateDate();
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", boardVO);
		
		return map;
	}
	
	private String getUpdateDate() { // public이어도 사용할 수 없음. 인터페이스에 등록되어있는 메소드가 아니고 구현클래스이기때문. 내부적으로 별도의 메소드를 추가한거. 해당 클래스 밖에서 사용할 수 없다.
		LocalDate today = LocalDate.now(); // 자바가 다루는 날짜함수. 현재 지역을 기준삼아 날짜를 가져옴. / Date는 미국기준.
		//필드는(우리가 가지고있을데이터) date, 클라이언트에 돌려줄 값은 localDate가 편함.
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String updateDt = today.format(dtFormat);//날짜에대해 출력할 포맷을 결정하는 것.
		return updateDt;
		
		//return LocalDate.now().format(DateTimeFormatter.ofPattern(getUpdateDate("yyyy/MM/dd")));
	}
	
	@Override
	public int deleteBoard(int boardNo) {
		return boardMapper.deleteBoardInfo(boardNo);
	}

}
