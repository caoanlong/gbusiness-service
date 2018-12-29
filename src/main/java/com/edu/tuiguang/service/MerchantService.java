package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.Merchant;
import com.edu.tuiguang.entity.PageBean;

import java.util.List;

public interface MerchantService {
	List<Merchant> findAll(String merchantName);
	PageBean<List<Merchant>> findList(Integer pageIndex, Integer pageSize, String merchantName);
	Merchant findById(Integer merchantId);
	void insert(Merchant merchant);
	void update(Merchant merchant);
	void del(Integer merchantId);
	List<Merchant> findActivities(Integer merchantId);
}
