package com.itwill.lab05.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

//서비스(비즈니스) 계층 싱글턴 객체.
public enum UserService {
	INSTANCE;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserDao userDao = UserDao.INSTANCE;

	// 전체 목록
	public List<User> read() {
		log.debug("read()");
		List<User> list = userDao.select();
		log.debug("list size = {}", list.size());

		return list;
	}

	// 회원 가입에 필요한 메서드. userDao.insert() 호출.
	public int signUp(User user) {
		log.debug("signUp(user = {})", user);

		int result = userDao.insert(user);
		log.debug("insert result = {}", result);

		return result;
	}

	public User signIn(String userid, String password) {
		log.debug("signIn(userid = {}, passowrd = {})", userid, password);
		
		User dto = User.builder().userid(userid).password(password).build();
		User user = userDao.selectByUseridAndPassword(dto);
		log.debug("로그인 결과 = {}", user);
		
		return user;
	}

}
