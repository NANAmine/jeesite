/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.bi.entity.BiCommonTables;
import com.jeesite.modules.bi.dao.BiCommonTablesDao;

/**
 * 通用字典表Service
 * @author ljw
 * @version 2019-09-25
 */
@Service
@Transactional(readOnly=true)
public class BiCommonTablesService extends CrudService<BiCommonTablesDao, BiCommonTables> {
	
	/**
	 * 获取单条数据
	 * @param biCommonTables
	 * @return
	 */
	@Override
	public BiCommonTables get(BiCommonTables biCommonTables) {
		return super.get(biCommonTables);
	}
	
	/**
	 * 查询分页数据
	 * @param biCommonTables 查询条件
	 * @param biCommonTables.page 分页对象
	 * @return
	 */
	@Override
	public Page<BiCommonTables> findPage(BiCommonTables biCommonTables) {
		return super.findPage(biCommonTables);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param biCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BiCommonTables biCommonTables) {
		super.save(biCommonTables);
	}
	
	/**
	 * 更新状态
	 * @param biCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BiCommonTables biCommonTables) {
		super.updateStatus(biCommonTables);
	}
	
	/**
	 * 删除数据
	 * @param biCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BiCommonTables biCommonTables) {
		super.delete(biCommonTables);
	}
	
}