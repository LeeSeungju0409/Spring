package com.yedam.app.book.service;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {	
	private Integer bookNo;
	private String bookName;
	private String bookCoverimg;
	private Date bookDate;
	private Integer bookPrice;
	private String bookPublisher;
	private String bookInfo;
}
