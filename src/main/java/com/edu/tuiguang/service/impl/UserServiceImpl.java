package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.User;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.exception.CommonException;
import com.edu.tuiguang.repository.UserRepository;
import com.edu.tuiguang.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public PageBean<List<User>> findList(Integer pageIndex, Integer pageSize) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<User> users = userRepository.findList(pageStart, pageSize);
		Long total = userRepository.total();
		PageBean<List<User>> pageBean = new PageBean<>();
		pageBean.setList(users);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public User findById(Integer userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void insert(User user) {
		if (StringUtils.isBlank(user.getUserName()))
			throw new CommonException(ErrorCode.USERNAME_NOTNULL);
		if (StringUtils.isBlank(user.getPassword()))
			throw new CommonException((ErrorCode.PASSWORD_NOTNULL));
		if (null == user.getRoleId() || StringUtils.isBlank(user.getRoleId().toString()))
			throw new CommonException((ErrorCode.ROLE_NOTNULL));
		user.setCreateby(1);
		user.setCreateTime(new Date());
		userRepository.insert(user);
	}

	@Override
	public void update(User user) {
		if (null == user.getUserId() || StringUtils.isBlank(user.getUserId().toString()))
			throw new CommonException(ErrorCode.USERID_NOTNULL);
		userRepository.update(user);
	}

	@Override
	public void del(Integer userId) {
		userRepository.del(userId);
	}

	@Override
	public User findByNameAndPassword(String userName, String password) {
		return userRepository.findByNameAndPassword(userName, password);
	}
}
