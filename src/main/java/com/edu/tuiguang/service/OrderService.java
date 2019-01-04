package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.Order;
import com.edu.tuiguang.entity.PageBean;

import java.util.List;

public interface OrderService {
	List<Order> findAll();
	PageBean<List<Order>> findList(
			Integer pageIndex,
			Integer pageSize,
			String orderNo,
			Integer memberId,
			Integer activityId
	);
	Order findById(Integer orderId);
	void insert(Order order);
	void update(Order order);
	void del(Integer orderId);
}
