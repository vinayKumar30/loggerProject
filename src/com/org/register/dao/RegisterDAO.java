package com.org.register.dao;

import java.io.Serializable;

import com.org.register.entity.UserEntity;

public interface RegisterDAO {

	public Serializable save(UserEntity entity);

	public UserEntity fetchByEmail(String email);

	public UserEntity fetchByNameAndPassword(String userName, String password);

	public String UpdatePasswordByEmail(String email, String password);
	
	public String ChangePasswordByEmail(String email, String password);
}
