package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.User;

import java.util.List;

public interface UserService {
	List<User> findAll();
	PageBean<List<User>> findList(String userName, Integer pageIndex, Integer pageSize);
	User findById(Integer userId);
	void insert(User user);
	void update(User user);
	void del(Integer userId);

	User findByNameAndPassword(String userName, String password);
}
