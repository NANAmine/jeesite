/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.entity.DimSaleTargetYyy;
import com.jeesite.modules.dim.dao.DimSaleTargetYyyDao;

/**
 * 三亚营业员销售目标Service
 * @author ljw
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly=true)
public class DimSaleTargetYyyService extends CrudService<DimSaleTargetYyyDao, DimSaleTargetYyy> {
	
	/**
	 * 获取单条数据
	 * @param dimSaleTargetYyy
	 * @return
	 */
	@Override
	public DimSaleTargetYyy get(DimSaleTargetYyy dimSaleTargetYyy) {
		return super.get(dimSaleTargetYyy);
	}
	
	/**
	 * 查询分页数据
	 * @param dimSaleTargetYyy 查询条件
	 * @param dimSaleTargetYyy.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimSaleTargetYyy> findPage(DimSaleTargetYyy dimSaleTargetYyy) {
		return super.findPage(dimSaleTargetYyy);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dimSaleTargetYyy
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimSaleTargetYyy dimSaleTargetYyy) {
		super.save(dimSaleTargetYyy);
	}
	
	/**
	 * 更新状态
	 * @param dimSaleTargetYyy
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimSaleTargetYyy dimSaleTargetYyy) {
		super.updateStatus(dimSaleTargetYyy);
	}
	
	/**
	 * 删除数据
	 * @param dimSaleTargetYyy
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimSaleTargetYyy dimSaleTargetYyy) {
		super.delete(dimSaleTargetYyy);
	}
	
}