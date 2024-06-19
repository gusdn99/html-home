package com.itwill.spring2.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.repository.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
)

public class CommentServiceTest {
	
	@Autowired private CommentService commentService;
	
//	@Test
	public void testReadByPostId() {
		List<CommentItemDto> list = commentService.readByPostId(2);
		for (CommentItemDto dto : list) {
			log.debug(dto.toString());	
		}
	}
	
//	@Test
//	public void testCreate() {
//		Comment comment = Comment.builder().postId(28).username("itwill").ctext("이따가 상담 받으러 가야한다").build();
//		int result = commentService.create
//	}
	
//	@Test
//	public void testInsert() {
//		Comment comment = Comment.builder().postId(28).username("itwill").ctext("이따가 상담 받으러 가야한다").build();
//		int result = commentDao.insert(comment);
//		Assertions.assertEquals(1, result);
//		
//	}
	
//	public int create(CommentCreateDto dto) {
//		log.debug("create({})", dto);
//		
//		// 리포지토리 계층의 메서드를 호출해서 comments 테이블에 insert.
//		int result = commentDao.insert(dto.toEntity());
//		log.debug("insert 결과 = {}", result);
//		
//		return result;
//	}
}
