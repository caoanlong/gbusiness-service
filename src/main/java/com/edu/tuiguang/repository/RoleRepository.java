package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Role;

import java.util.List;

public interface RoleRepository {
	List<Role> findAll();
	List<Role> findList();
	Role findById(Integer roleId);
	Long total();
	void insert(Role role);
	void update(Role role);
	void del(Integer roleId);
}
