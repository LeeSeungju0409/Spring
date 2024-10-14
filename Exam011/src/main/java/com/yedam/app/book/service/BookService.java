package com.yedam.app.book.service;

import java.util.List;

public interface BookService {
	
	// 도서등록.
	public int bookInsert(BookVO bookVO);
	
	// 새로 등록할 도서번호 조회
	public int nextBookNo();
	
	// 도서목록.
	public List<BookVO> bookList();
	
	// 렌트조회.
	public List<RentVO> rentList();
	
}
