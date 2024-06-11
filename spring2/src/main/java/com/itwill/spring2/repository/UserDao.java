package com.itwill.spring2.repository;

import java.util.List;

public interface UserDao {
	
	List<User> selectOrderByIdDesc();
	User selectByUserId(String userid);
	User selectByUseridAndPassword(User user);
	int insertUser(User user);
	int updatePoints(String userid, Integer points);
	int deleteUser(Integer id);

}

