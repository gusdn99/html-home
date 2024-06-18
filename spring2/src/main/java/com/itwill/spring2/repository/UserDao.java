package com.itwill.spring2.repository;

import com.itwill.spring2.dto.UserSigninDto;

public interface UserDao {
	
	User selectByUserId(String userid);
	User selectByUseridAndPassword(UserSigninDto dto);
	int insertUser(User user);
	int updatePoints(String userid, Integer points);

}

