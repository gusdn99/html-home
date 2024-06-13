package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
// 업데이트 요청의 요청 파라미터들을 저장하기 위한 DTO
public class CommentUpdateDto {
	private int id;
	private String ctext;
	
	public Comment toEntity() {
		return Comment.builder().id(id).ctext(ctext).build();
	}
	
}
