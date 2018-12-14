package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.User;

import java.util.List;

public interface UserRepository {
	List<User> findAll();
	List<User> findList();
	User findById(Integer userId);
	String findPassword(Integer userId);
	Long total();
	void insert(User user);
	void update(User user);
	void del(Integer userId);
}
