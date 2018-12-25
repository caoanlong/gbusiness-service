package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.Role;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.repository.RoleRepository;
import com.edu.tuiguang.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public PageBean<List<Role>> findList(Integer pageIndex, Integer pageSize) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<Role> list = roleRepository.findList(pageStart, pageSize);
		Long total = roleRepository.total();
		PageBean<List<Role>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public Role findById(Integer roleId) {
		return roleRepository.findById(roleId);
	}

	@Override
	public void insert(Role role) {
		if (StringUtils.isBlank(role.getRoleName()))
			throw new CommonException(ErrorCode.ROLE_NOTNULL);
//		role.setCreateby(1);
//		role.setCreateTime(new Date());
//		roleRepository.insert(role);
	}

	@Override
	public void update(Role role) {
		if (null == role.getRoleId() || StringUtils.isBlank(role.getRoleId().toString()))
			throw new CommonException(ErrorCode.ROLEID_NOTNULL);
		roleRepository.update(role);
	}

	@Override
	public void del(Integer roleId) {
		roleRepository.del(roleId);
	}
}
