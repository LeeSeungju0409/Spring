package com.yedam.app.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Service
public class BookServiceImpl implements BookService {
	private BookMapper bookMapper;
	
	@Autowired // 생성자 주입
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();
	}

	@Override
	public List<BookVO> rentList() {
		return bookMapper.selectRentAll();
	}

	@Override
	public int insertBookInfo(BookVO bookVO) {
		int result = bookMapper.insertBookInfo(bookVO);
		return result == 1? bookVO.getBookNo() : -1; //selectKey를 사용하기 때문에 getBno가 가능
	}

}
