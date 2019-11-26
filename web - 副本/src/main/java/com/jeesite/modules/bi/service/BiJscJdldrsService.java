/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.bi.entity.BiJscJdldrs;
import com.jeesite.modules.bi.dao.BiJscJdldrsDao;

/**
 * 三亚客流Service
 * @author ljw
 * @version 2019-09-17
 */
@Service
@Transactional(readOnly=true)
public class BiJscJdldrsService extends CrudService<BiJscJdldrsDao, BiJscJdldrs> {
	
	/**
	 * 获取单条数据
	 * @param biJscJdldrs
	 * @return
	 */
	@Override
	public BiJscJdldrs get(BiJscJdldrs biJscJdldrs) {
		return super.get(biJscJdldrs);
	}
	
	/**
	 * 查询分页数据
	 * @param biJscJdldrs 查询条件
	 * @param biJscJdldrs.page 分页对象
	 * @return
	 */
	@Override
	public Page<BiJscJdldrs> findPage(BiJscJdldrs biJscJdldrs) {
		return super.findPage(biJscJdldrs);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param biJscJdldrs
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BiJscJdldrs biJscJdldrs) {
		super.save(biJscJdldrs);
	}
	
	/**
	 * 更新状态
	 * @param biJscJdldrs
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BiJscJdldrs biJscJdldrs) {
		super.updateStatus(biJscJdldrs);
	}
	
	/**
	 * 删除数据
	 * @param biJscJdldrs
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BiJscJdldrs biJscJdldrs) {
		super.delete(biJscJdldrs);
	}
	
}