package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	private PostDao dao = PostDao.INSTANCE; // singleton 객체
	
//	@Test
	public void test() {
		// Post 타입 객체 생성 - Builder 디자인 패턴.
		Post p = Post.builder()
				.title("테스트")
				.author("관리자")
				.content("builder design pattern")
				.id(1)
				.build();
		
		// assertNotNull(arg): arg가 null이 아니면 JUnit 테스트 성공, null이면 테스트 실패.
		// assertNull(arg): arg가 null이면 단위 테스트 성공, null이 아니면 테스트 실패.
		Assertions.assertNotNull(p);
		log.debug("p = {}", p);
	}

//	@Test
	public void testSelect() {
        // PostDao.select 메서드 단위 테스트
		
		Assertions.assertNotNull(dao); // PostDao 타입 객체가 null이 아니면 단위 테스트 성공.
		log.debug("dao = {}", dao); // 17:26:40.074 DEBUG [com.itwill.lab05.repository.PostTest    ] dao = INSTANCE
		
		List<Post> result = dao.select();
		Assertions.assertEquals(3, result.size());
		// result.size(): 데이터베이스의 행의 개수. expected 값과 actual 값이 같으면 단위 테스트 성공, 다르면 실패.
		for (Post p : result) {
			log.debug(p.toString());
		}
	}
	
//	@Test
	public void testInsert() {
		// PostDao.insert 메서드 단위 테스트
		Post post = Post.builder()
				.title("today")
				.content("difficult")
				.author("hyunwoo")
				.build();
		int result = dao.insert(post); // PostDao의 insert 메서드 호출.
		Assertions.assertEquals(1, result); // insert 메서드의 리턴 값(삽입된 행의 개수)이 1이면 단위 테스트 성공.
		log.debug("insert = {}", String.valueOf(result));
	}
	
//	@Test
	public void testDelete() {
		// PostDao.delete 메서드 단위 테스트
		int result = dao.delete(4); // id(PK)가 있을 때
		Assertions.assertEquals(1, result); // delete 메서드의 리턴 값(삭제된 행의 개수)이 1이면 단위 테스트 성공.
		log.debug("delete = {}", String.valueOf(result));
		
		result = dao.delete(10); // id(PK)가 없을 때
		Assertions.assertEquals(0, result);
		
	}
	
	@Test
	public void testSelectById() {
		// PostDao.select(int id) 메서드 단위 테스트
		Post post = dao.select(3); // id(PK)가 테이블에 있을 때
		Assertions.assertNotNull(post);
		log.debug("selectById = {}", post.toString());
		
		post = dao.select(0); // id(PK)가 테이블에 없을 때
		Assertions.assertNull(post);
	}

	
}
