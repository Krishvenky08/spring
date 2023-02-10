package com.capgemini.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.dao.UserRepository;
import com.capgemini.model.UserModel;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
    	System.out.println("loadUserByUsername: "+user);
    	UserModel userModel =  repository.findByEmail(user);
    	//System.out.println(userModel);
    	if(userModel != null)
    		return new User(userModel.getEmail(), userModel.getPassword(), new ArrayList<>());
    	return null;
    }
}