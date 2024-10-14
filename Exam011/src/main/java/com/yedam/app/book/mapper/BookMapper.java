package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

public interface BookMapper {
	
	// 도서목록
	public List<BookVO> selectBookAll();	
	
	// 새로 등록할 도서번호 조회
	public int selectNextNo(); //selectKey 분리한거 실행할 쿼리문.

	// 도서등록
	public int insertBook(BookVO book);
	
	// 대여현황
	public List<RentVO> selectRentAll();
}
