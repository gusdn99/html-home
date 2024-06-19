package com.itwill.spring2.repository;

public interface UserDao {
	User selectByUserId(String userid);
	int insert(User user);
	User selectByUseridAndPassword(User user);

}
