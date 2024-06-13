package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {
	private Integer postId;
	private String username;
	private String ctext;

	
	public Comment toEntity() {
		return Comment.builder().postId(postId).username(username).ctext(ctext).build();
	}
	
}

