/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.bi.entity.BiTablesDeq;
import com.jeesite.modules.bi.dao.BiTablesDeqDao;

/**
 * 通用字典表二Service
 * @author ljw
 * @version 2019-09-24
 */
@Service
@Transactional(readOnly=true)
public class BiTablesDeqService extends CrudService<BiTablesDeqDao, BiTablesDeq> {
	
	/**
	 * 获取单条数据
	 * @param biTablesDeq
	 * @return
	 */
	@Override
	public BiTablesDeq get(BiTablesDeq biTablesDeq) {
		return super.get(biTablesDeq);
	}
	
	/**
	 * 查询分页数据
	 * @param biTablesDeq 查询条件
	 * @param biTablesDeq.page 分页对象
	 * @return
	 */
	@Override
	public Page<BiTablesDeq> findPage(BiTablesDeq biTablesDeq) {
		return super.findPage(biTablesDeq);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param biTablesDeq
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BiTablesDeq biTablesDeq) {
		super.save(biTablesDeq);
	}
	
	/**
	 * 更新状态
	 * @param biTablesDeq
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BiTablesDeq biTablesDeq) {
		super.updateStatus(biTablesDeq);
	}
	
	/**
	 * 删除数据
	 * @param biTablesDeq
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BiTablesDeq biTablesDeq) {
		super.delete(biTablesDeq);
	}
	
}