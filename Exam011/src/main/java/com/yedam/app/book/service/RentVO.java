package com.yedam.app.book.service;

import lombok.Data;

@Data
public class RentVO {
	private Integer bookNo;
	private String bookName;
	// 추가
	private Integer count;
	private Integer totalPrice;
}
