package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
	List<Order> findAll();
	List<Order> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("orderNo") String orderNo,
			@Param("memberId") Integer memberId,
			@Param("activityId") Integer activityId
	);
	Order findById(Integer orderId);
	Long total(
			@Param("orderNo") String orderNo,
			@Param("memberId") Integer memberId,
			@Param("activityId") Integer activityId
	);
	void insert(Order order);
	void update(Order order);
	void del(Integer orderId);
}
