/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.entity.DimHotel;
import com.jeesite.modules.dim.dao.DimHotelDao;

/**
 * 三亚酒店基本信息Service
 * @author ljw
 * @version 2019-09-19
 */
@Service
@Transactional(readOnly=true)
public class DimHotelService extends CrudService<DimHotelDao, DimHotel> {
	
	/**
	 * 获取单条数据
	 * @param dimHotel
	 * @return
	 */
	@Override
	public DimHotel get(DimHotel dimHotel) {
		return super.get(dimHotel);
	}
	
	/**
	 * 查询分页数据
	 * @param dimHotel 查询条件
	 * @param dimHotel.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimHotel> findPage(DimHotel dimHotel) {
		return super.findPage(dimHotel);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dimHotel
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimHotel dimHotel) {
		super.save(dimHotel);
	}
	
	/**
	 * 更新状态
	 * @param dimHotel
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimHotel dimHotel) {
		super.updateStatus(dimHotel);
	}
	
	/**
	 * 删除数据
	 * @param dimHotel
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimHotel dimHotel) {
		super.delete(dimHotel);
	}
	
}