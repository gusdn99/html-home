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

public class PostDaoTest {
	
	@Autowired // 의존성 주입(DI)
	private PostDao postDao; // 인터페이스 객체 생성
	
	
//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(postDao);
		
		List<Post> list = postDao.selectOrderByIdDesc();
		for (Post p : list) {
			System.out.println("\t" + p);
//			DEBUG [2.repository.PostDao.selectOrderByIdDesc] ==>  Preparing: select * from posts order by id desc
//			DEBUG [2.repository.PostDao.selectOrderByIdDesc] ==> Parameters:  (setString, setInt와 유사)
//			DEBUG [2.repository.PostDao.selectOrderByIdDesc] <==      Total: 18
		}
	}
	
//	@Test
	public void testSelectById() {
		Post post1 = postDao.selectById(28); // DB 테이블에 id가 있을 때
		Assertions.assertNotNull(post1);
		log.debug(post1.toString());
//		DEBUG [ll.spring2.repository.PostDao.selectById] ==>  Preparing: select * from posts where id = ?
//		DEBUG [ll.spring2.repository.PostDao.selectById] ==> Parameters: 28(Integer)
//		DEBUG [ll.spring2.repository.PostDao.selectById] <==      Total: 1
		
		Post post2 = postDao.selectById(4); // DB 테이블에 id가 없을 때
		Assertions.assertNull(post2);
//		DEBUG [ll.spring2.repository.PostDao.selectById] ==>  Preparing: select * from posts where id = ?
//		DEBUG [ll.spring2.repository.PostDao.selectById] ==> Parameters: 4(Integer)
//		DEBUG [ll.spring2.repository.PostDao.selectById] <==      Total: 0	
		
	}
	
//	@Test
	public void testInsert() {
		Post post = Post.builder().title("MyBatis 테스트").content("MyBatis-Spring insert 테스트").author("admin").build();
		int result = postDao.insertPost(post);
		Assertions.assertEquals(1, result);
//		DEBUG [ll.spring2.repository.PostDao.insertPost] ==>  Preparing: insert into posts (title, content, author) values (?, ?, ?)
//		DEBUG [ll.spring2.repository.PostDao.insertPost] ==> Parameters: MyBatis 테스트(String), MyBatis-Spring insert 테스트(String), admin(String)
//		DEBUG [ll.spring2.repository.PostDao.insertPost] <==    Updates: 1
		
	}
	
//	@Test
	public void testUpdate() {
		// 업데이트할 포스트 객체:
		Post post = Post.builder().id(2).title("6월 11일").content("곧 점심 시간!!").build();
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result);
//		DEBUG [ll.spring2.repository.PostDao.updatePost] ==>  Preparing: update posts set title = ?, content = ?, modified_time = systimestamp where id = ?
//		DEBUG [ll.spring2.repository.PostDao.updatePost] ==> Parameters: 6월 11일(String), 곧 점심 시간!!(String), 2(Integer)
//		DEBUG [ll.spring2.repository.PostDao.updatePost] <==    Updates: 1
				
	}
	
//	@Test
	public void testDelete() {
		int result1 = postDao.deletePost(26); // DB 테이블에 id가 있을 때
		Assertions.assertEquals(1, result1);
//		DEBUG [ll.spring2.repository.PostDao.deletePost] ==>  Preparing: delete from posts where id = ?
//		DEBUG [ll.spring2.repository.PostDao.deletePost] ==> Parameters: 26(Integer)
//		DEBUG [ll.spring2.repository.PostDao.deletePost] <==    Updates: 1
		
		int result2 = postDao.deletePost(30); // DB 테이블에 id가 없을 때
		Assertions.assertEquals(0, result2);
//		DEBUG [ll.spring2.repository.PostDao.deletePost] ==>  Preparing: delete from posts where id = ?
//		DEBUG [ll.spring2.repository.PostDao.deletePost] ==> Parameters: 30(Integer)
//		DEBUG [ll.spring2.repository.PostDao.deletePost] <==    Updates: 0
		
	}

}
