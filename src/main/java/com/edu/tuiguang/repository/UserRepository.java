package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
	List<User> findAll();
	List<User> findList(
			@Param("userName") String userName,
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize
	);
	User findById(Integer userId);
	Long total();
	void insert(User user);
	void update(User user);
	void del(Integer userId);

	User findByNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password
	);
}
