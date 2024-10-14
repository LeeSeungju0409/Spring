package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;
import com.yedam.app.book.service.RentVO;

@Controller
public class BookController {
	
	BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	// 도서목록.
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("bookList", list);
		return "book/list";
	}
	
	// 등록 - 페이지
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		int bNo = bookService.nextBookNo(); // 서비스에 등록할 북넘버 조회 후 넘기기/
		model.addAttribute("no", bNo); //no로 값이 넘겨진다.
		return "book/insert";
	}
	
	// 등록 - 처리
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {		
		bookService.bookInsert(bookVO);		
		return "redirect:bookList";
	}

	
	// 목록.
	@GetMapping("rentList")
	public String rentList(Model model) {
		List<RentVO> list = bookService.rentList();
		model.addAttribute("rentList", list);
		return "book/rentList";
	}
	

}
