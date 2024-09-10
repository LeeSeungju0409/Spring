package com.yedam.app.board.service;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
//@NoArgsConstructor
@Builder
public class BoardVO {
	private Integer bno;		// 번호
	private String title;		// 제목
	private String contents;	// 내용
	private String writer;		// 작성자
	private Date regdate;		// 작성일
	private Date updatedate;	// 수정일
	private String image;		// 첨부이미지
	
}