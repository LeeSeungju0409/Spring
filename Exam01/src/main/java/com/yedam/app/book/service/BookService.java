package com.yedam.app.book.service;

import java.util.List;

public interface BookService {
	// Book조회
	public List<BookVO> bookList();
	// Rent조회
	public List<BookVO> rentList();

	// 등록
	public int insertBookInfo(BookVO bookVO);
}
