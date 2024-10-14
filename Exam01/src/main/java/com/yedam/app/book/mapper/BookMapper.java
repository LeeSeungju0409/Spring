package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;

public interface BookMapper {
	// 전체 조회
	public List<BookVO> selectBookAll();
	
	// 전체 조회
	public List<BookVO> selectRentAll();

	
	// 등록 : 대상 - bno, title, contents, writer, regdate, image
	public int insertBookInfo(BookVO bookVO);
}
