package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Activity;
import com.edu.tuiguang.entity.Order;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.ActivityRepository;
import com.edu.tuiguang.repository.OrderRepository;
import com.edu.tuiguang.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public PageBean<List<Order>> findList(
			Integer pageIndex,
			Integer pageSize,
			String orderNo,
			Integer memberId,
			Integer activityId
	) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<Order> list = orderRepository.findList(pageStart, pageSize, orderNo, memberId, activityId);
		Long total = orderRepository.total(orderNo, memberId, activityId);
		PageBean<List<Order>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public Order findById(Integer orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	@Transactional
	public void insert(Order order) {
		Integer activityId = order.getActivityId();
		if (null == activityId || StringUtils.isBlank(activityId.toString()))
			throw new CommonException(ErrorCode.ACTIVITYID_NOTNULL);
		Activity activity = activityRepository.findById(activityId);
		Float price = activity.getPrice();
		order.setPrice(price);
		order.setCreateTime(new Date());
		orderRepository.insert(order);
	}

	@Override
	public void update(Order order) {

	}

	@Override
	public void del(Integer orderId) {
		orderRepository.del(orderId);
	}
}
