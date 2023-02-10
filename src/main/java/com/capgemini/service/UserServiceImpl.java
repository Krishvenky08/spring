package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.UserRepository;
import com.capgemini.model.UserModel;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserModel addUser(UserModel user) {
		return repository.save(user);
	}

	@Override
	public UserModel findUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<UserModel> findAll() {
		return repository.findAll();
	}
}
