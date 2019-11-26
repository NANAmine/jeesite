/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.entity.DimMapBrandLocate;
import com.jeesite.modules.dim.dao.DimMapBrandLocateDao;

/**
 * 店铺号品牌映射Service
 * @author ljw
 * @version 2019-09-19
 */
@Service
@Transactional(readOnly=true)
public class DimMapBrandLocateService extends CrudService<DimMapBrandLocateDao, DimMapBrandLocate> {
	
	/**
	 * 获取单条数据
	 * @param dimMapBrandLocate
	 * @return
	 */
	@Override
	public DimMapBrandLocate get(DimMapBrandLocate dimMapBrandLocate) {
		return super.get(dimMapBrandLocate);
	}
	
	/**
	 * 查询分页数据
	 * @param dimMapBrandLocate 查询条件
	 * @param dimMapBrandLocate.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimMapBrandLocate> findPage(DimMapBrandLocate dimMapBrandLocate) {
		return super.findPage(dimMapBrandLocate);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dimMapBrandLocate
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimMapBrandLocate dimMapBrandLocate) {
		super.save(dimMapBrandLocate);
	}
	
	/**
	 * 更新状态
	 * @param dimMapBrandLocate
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimMapBrandLocate dimMapBrandLocate) {
		super.updateStatus(dimMapBrandLocate);
	}
	
	/**
	 * 删除数据
	 * @param dimMapBrandLocate
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimMapBrandLocate dimMapBrandLocate) {
		super.delete(dimMapBrandLocate);
	}
	
}