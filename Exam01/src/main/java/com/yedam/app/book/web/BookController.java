package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {
	
	private BookService bookService; // 필드 생성
	
	@Autowired // 필드 주입할 수 있도록 통로 여는 부분
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	// 전체조회 : URI - bookList / RETURN - book/bookList
	@GetMapping("bookList")
	public String bookList(Model model) {// 값을 전달 - 정보를 model에 담아서 조회
		List<BookVO> list = bookService.bookList();
		model.addAttribute("books", list);
		return "book/bookList";
	// prefix + return + suffix
	// classpath:/templates/ + book/bookList + .html
	}
	
	// 전체조회 : URI - rentList / RETURN - rent/rentList
	@GetMapping("rentList")
	public String rentList(Model model) {// 값을 전달 - 정보를 model에 담아서 조회
		List<BookVO> list = bookService.rentList();
		model.addAttribute("rents", list);
		return "book/rentList";
	// prefix + return + suffix
	// classpath:/templates/ + book/rentList + .html
	}

	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("bookInsert")
	public String boardInsertForm() {
		return "book/bookInsert";
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)-submit을 쓰겠다. 반환이 아닌 redirect.
	//             RETURN - 단건조회 다시 호출
	@PostMapping("bookInsert") // 같은 기능 다른 메소드로 썼기때문에 충돌나지않아서 페이지와 똑같이 들어감.
	public String bookInsertProcess(BookVO bookVO) { // <form/>활용한 submit 쓰겠다. 
		int bid = bookService.insertBookInfo(bookVO);

		
		return "redirect:bookInfo?bookNo=" + bid;
	}
}
