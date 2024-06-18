package com.itwill.spring2.service;

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
	
	public int signup(UserSignupDto dto) {
		log.debug("signup({})", dto);
		
		int result = userDao.insertUser(dto.toEntity());
		log.debug("signUp 결과 = {}", result);
		
		return result;
	}
	
	public User signin(UserSigninDto dto) {
		log.debug("signin({})", dto);

		return userDao.selectByUseridAndPassword(dto);
		
	}
	
	public User read(String userid) {
		log.debug("read()");
		User user = userDao.selectByUserId(userid);
		return user;
		
	}
	
//	 public boolean checkUserid(String userid) {
//	        log.debug("checkUserid(userid = {})", userid);
//	        
//	        User user = userDao.selectByUserId(userid);
//	        if (user == null) { // USERS 테이블에 userid가 없는 경우 -> 회원가입에서 사용가능한 아이디 
//	            return true;
//	        } else { // USERS 테이블에 userid가 이미 있는 경우 -> 회원가입에서 사용 불가능한 아이디
//	            return false;
//	        }
//	    }

}

