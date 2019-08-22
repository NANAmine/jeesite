/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.Yp;
import com.jeesite.modules.test.dao.YpDao;

/**
 * 药品管理Service
 * @author ljw
 * @version 2019-05-09
 */
@Service
@Transactional(readOnly=true)
public class YpService extends CrudService<YpDao, Yp> {
	
	/**
	 * 获取单条数据
	 * @param yp
	 * @return
	 */
	@Override
	public Yp get(Yp yp) {
		return super.get(yp);
	}
	
	/**
	 * 查询分页数据
	 * @param yp 查询条件
	 * @param yp.page 分页对象
	 * @return
	 */
	@Override
	public Page<Yp> findPage(Yp yp) {
		return super.findPage(yp);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param yp
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Yp yp) {
		super.save(yp);
	}
	
	/**
	 * 更新状态
	 * @param yp
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Yp yp) {
		super.updateStatus(yp);
	}
	
	/**
	 * 删除数据
	 * @param yp
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Yp yp) {
		super.delete(yp);
	}
	
}