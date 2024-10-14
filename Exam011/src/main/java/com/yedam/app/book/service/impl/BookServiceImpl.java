package com.yedam.app.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Service
public class BookServiceImpl implements BookService {

	BookMapper bookMapper;
	
	@Autowired
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();
	}
	
	@Override
	public int bookInsert(BookVO bookVO) {
		int result = bookMapper.insertBook(bookVO);
		if(result == 1) {
			return bookVO.getBookNo();
		} else {
			return -1;
		}
	}

	@Override
	public List<RentVO> rentList() {
		return bookMapper.selectRentAll();
	}


	@Override
	public int nextBookNo() {
		// TODO Auto-generated method stub
		return bookMapper.selectNextNo();
	}


}
