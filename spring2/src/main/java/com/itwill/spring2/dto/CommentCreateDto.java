package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
// 새 글 작성 요청에서 요청 파라미터들을 저장하기 위한 DTO
public class CommentCreateDto {
	private Integer postId;
	private String username;
	private String ctext;

	
	public Comment toEntity() {
		return Comment.builder().postId(postId).username(username).ctext(ctext).build();
	}
	
}

