/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.entity.DimTexchangeRate;
import com.jeesite.modules.dim.dao.DimTexchangeRateDao;

/**
 * 汇率数据Service
 * @author ljw
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly=true)
public class DimTexchangeRateService extends CrudService<DimTexchangeRateDao, DimTexchangeRate> {
	
	/**
	 * 获取单条数据
	 * @param dimTexchangeRate
	 * @return
	 */
	@Override
	public DimTexchangeRate get(DimTexchangeRate dimTexchangeRate) {
		return super.get(dimTexchangeRate);
	}
	
	/**
	 * 查询分页数据
	 * @param dimTexchangeRate 查询条件
	 * @param dimTexchangeRate.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimTexchangeRate> findPage(DimTexchangeRate dimTexchangeRate) {
		return super.findPage(dimTexchangeRate);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dimTexchangeRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimTexchangeRate dimTexchangeRate) {
		super.save(dimTexchangeRate);
	}
	
	/**
	 * 更新状态
	 * @param dimTexchangeRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimTexchangeRate dimTexchangeRate) {
		super.updateStatus(dimTexchangeRate);
	}
	
	/**
	 * 删除数据
	 * @param dimTexchangeRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimTexchangeRate dimTexchangeRate) {
		super.delete(dimTexchangeRate);
	}
	
}