package com.itwill.spring2.dto;

import com.itwill.spring2.repository.User;

import lombok.Data;

@Data
public class UserSignupDto {
	
	private String userid;
	private String password;
	private String email;
	
	public User toEntity() {
		return User.builder().userid(userid).password(password).email(email).build();
	}
	
}
