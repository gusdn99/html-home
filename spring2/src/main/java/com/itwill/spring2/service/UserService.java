package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserSigninDto;
import com.itwill.spring2.dto.UserSignupDto;
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
	
	public int signup(UserSignupDto dto) {
		log.debug("signup({})", dto);
		
		int result = userDao.insertUser(dto.toEntity());
		log.debug("signUp 결과 = {}", result);
		
		return result;
	}
	
	public User signin(UserSigninDto dto) {
		log.debug("signin({})", dto);

		return userDao.selectByUseridAndPassword(dto.toEntity());
		
	}
	
	public User read(String userid) {
		log.debug("read()");
		User user = userDao.selectByUserId(userid);
		return user;
		
	}

}

