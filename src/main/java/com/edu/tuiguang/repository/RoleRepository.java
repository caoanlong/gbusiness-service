package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {
	List<Role> findAll();
	List<Role> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("roleName") String roleName
	);
	Role findById(Integer roleId);
	Long total();
	void insert(Role role);
	void update(Role role);
	void del(Integer roleId);
}
