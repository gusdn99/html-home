package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
public class CommentUpdateDto {
	private int id;
	private String ctext;
	
	public Comment toEntity() {
		return Comment.builder().id(id).ctext(ctext).build();
	}
	
}
