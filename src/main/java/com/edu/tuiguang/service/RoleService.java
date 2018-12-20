package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.Role;

import java.util.List;

public interface RoleService {
	List<Role> findAll();
	PageBean<List<Role>> findList(Integer pageIndex, Integer pageSize);
	Role findById(Integer roleId);
	void insert(Role role);
	void update(Role role);
	void del(Integer roleId);
}
