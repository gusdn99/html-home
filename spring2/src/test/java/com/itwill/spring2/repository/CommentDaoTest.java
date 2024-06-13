package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)

public class CommentDaoTest {
	
	@Autowired // 스프링 컨테이너가 생성/관리하는 빈을 주입받음.
	private CommentDao commentDao;
	
//	@Test
	public void testSelect() {
		Assertions.assertNotNull(commentDao);
		
		List<Comment> list = commentDao.selectByPostId(46);
		for (Comment c : list) {
			log.debug(c.toString());
		}
	}
	
//	@Test
	public void testInsert() {
		Comment comment = Comment.builder().postId(28).username("itwill").ctext("이따가 상담 받으러 가야한다").build();
		int result = commentDao.insert(comment);
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testUpdate() {
		Comment comment = Comment.builder().id(3).ctext("댓글 업데이트 이얍").build();
		int result = commentDao.update(comment);
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testDeleteById() {
		int result = commentDao.deleteById(3);
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testDeleteByPostId() {
		int result = commentDao.deleteByPostId(28);
		// 포스트 번호 28번에 댓글이 2개 있는 경우
		Assertions.assertEquals(2, result);
		
	}
	
//	@Test
	public void testCommentCount() {
		Integer result = commentDao.selectCommentCount(2);
		// 포스트 번호 2번 테이블에 댓글이 3개 있는 경우
		Assertions.assertEquals(3, result); // Total: 1 => 행의 개수
	}
	
	@Test
	public void testSelectById() {
		// 테이블에 아이디(PK)가 있는 경우
		Comment comment1 = commentDao.selectById(5);
		Assertions.assertNotNull(comment1);
		log.debug(comment1.toString());
		
		// 테이블에 아이디(PK)가 없는 경우
		Comment comment2 = commentDao.selectById(1);
		Assertions.assertNull(comment2);
	}
	

}
