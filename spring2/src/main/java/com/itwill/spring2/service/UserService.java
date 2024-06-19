package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserSignInDto;
import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserDao userDao;
	
	// 아이디 중복 체크 : true - 중복되지 않은 아이디(사용할 수 O), false - 중복된 아이디(사용할 수 X)
	public boolean checkUserid(String userid) {
		log.debug("checkUserid(userid = {})", userid);
		
		User user = userDao.selectByUserId(userid);
		if (user == null) { // userid가 일치하는 레코드가 없을 때(중복된 아이디가 없는 경우)
			return true;
		} else { // userid가 일치하는 레코드가 있을 때(아이디가 중복된 경우)
			return false;
		}
	}
	
	// 회원 가입 서비스
	public int create(UserCreateDto dto) {
		log.debug("create({})", dto);
		
		int result = userDao.insert(dto.toEntity());
		log.debug("create 결과 = {}", result);
		
		return result;
	}
	
    // 로그인 서비스
    public User read(UserSignInDto dto) {
        log.debug("read({})", dto);
        
        User user = userDao.selectByUseridAndPassword(dto.toEntity());
        
        return user;
    }
	
	public User read(String userid) {
		log.debug("read()");
		User user = userDao.selectByUserId(userid);
		return user;
		
	}

}
