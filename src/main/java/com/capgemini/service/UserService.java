package com.capgemini.service;

import java.util.List;

import com.capgemini.model.UserModel;

public interface UserService {
	public UserModel addUser(UserModel user);
	public UserModel findUserByEmail(String email);
	public List<UserModel> findAll();
}
