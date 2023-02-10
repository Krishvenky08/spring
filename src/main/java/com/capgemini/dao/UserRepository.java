package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.UserModel;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	UserModel findByEmail(String email);

}
