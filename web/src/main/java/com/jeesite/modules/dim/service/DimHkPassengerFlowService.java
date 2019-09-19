/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.entity.DimHkPassengerFlow;
import com.jeesite.modules.dim.dao.DimHkPassengerFlowDao;

/**
 * 香港客流Service
 * @author ljw
 * @version 2019-09-17
 */
@Service
@Transactional(readOnly=true)
public class DimHkPassengerFlowService extends CrudService<DimHkPassengerFlowDao, DimHkPassengerFlow> {
	
	/**
	 * 获取单条数据
	 * @param dimHkPassengerFlow
	 * @return
	 */
	@Override
	public DimHkPassengerFlow get(DimHkPassengerFlow dimHkPassengerFlow) {
		return super.get(dimHkPassengerFlow);
	}
	
	/**
	 * 查询分页数据
	 * @param dimHkPassengerFlow 查询条件
	 * @param dimHkPassengerFlow.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimHkPassengerFlow> findPage(DimHkPassengerFlow dimHkPassengerFlow) {
		return super.findPage(dimHkPassengerFlow);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dimHkPassengerFlow
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimHkPassengerFlow dimHkPassengerFlow) {
		super.save(dimHkPassengerFlow);
	}
	
	/**
	 * 更新状态
	 * @param dimHkPassengerFlow
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimHkPassengerFlow dimHkPassengerFlow) {
		super.updateStatus(dimHkPassengerFlow);
	}
	
	/**
	 * 删除数据
	 * @param dimHkPassengerFlow
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimHkPassengerFlow dimHkPassengerFlow) {
		super.delete(dimHkPassengerFlow);
	}
	
}