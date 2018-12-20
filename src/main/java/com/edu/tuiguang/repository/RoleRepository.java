package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {
	List<Role> findAll();
	List<Role> findList(Integer pageStart, Integer pageSize);
	Role findById(Integer roleId);
	Long total();
	void insert(Role role);
	void update(Role role);
	void del(Integer roleId);
}
