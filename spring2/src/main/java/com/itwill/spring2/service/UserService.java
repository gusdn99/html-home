package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.repository.User;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserDao userDao;

	public List<User> read() {
		log.debug("read()");
		List<User> list = userDao.selectOrderByIdDesc();

		return list;

	}
	
	public int signUp(User user) {
		log.debug("signUp()");
		int result = userDao.insertUser(user);
		return result;
		
	}
	
	public User signIn(String userid, String password) {
		log.debug("signIn()");

		User dto = User.builder().userid(userid).password(password).build();
		User user = userDao.selectByUseridAndPassword(dto);

		return user;
		
	}
	
	public User read(String userid) {
		log.debug("read()");
		User user = userDao.selectByUserId(userid);
		return user;
		
	}

}

