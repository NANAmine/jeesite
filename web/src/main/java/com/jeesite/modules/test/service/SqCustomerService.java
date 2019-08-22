/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.SqCustomer;
import com.jeesite.modules.test.dao.SqCustomerDao;

/**
 * 顾客表Service
 * @author ljw
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly=true)
public class SqCustomerService extends CrudService<SqCustomerDao, SqCustomer> {
	
	/**
	 * 获取单条数据
	 * @param sqCustomer
	 * @return
	 */
	@Override
	public SqCustomer get(SqCustomer sqCustomer) {
		return super.get(sqCustomer);
	}
	
	/**
	 * 查询分页数据
	 * @param sqCustomer 查询条件
	 * @param sqCustomer.page 分页对象
	 * @return
	 */
	@Override
	public Page<SqCustomer> findPage(SqCustomer sqCustomer) {
		return super.findPage(sqCustomer);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sqCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SqCustomer sqCustomer) {
		super.save(sqCustomer);
	}
	
	/**
	 * 更新状态
	 * @param sqCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SqCustomer sqCustomer) {
		super.updateStatus(sqCustomer);
	}
	
	/**
	 * 删除数据
	 * @param sqCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SqCustomer sqCustomer) {
		super.delete(sqCustomer);
	}
	
}