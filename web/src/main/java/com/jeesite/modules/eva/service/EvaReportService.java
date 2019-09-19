/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.eva.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.eva.entity.EvaReport;
import com.jeesite.modules.eva.dao.EvaReportDao;

/**
 * EVAService
 * @author ljw
 * @version 2019-09-18
 */
@Service
@Transactional(readOnly=true)
public class EvaReportService extends CrudService<EvaReportDao, EvaReport> {
	
	/**
	 * 获取单条数据
	 * @param evaReport
	 * @return
	 */
	@Override
	public EvaReport get(EvaReport evaReport) {
		return super.get(evaReport);
	}
	
	/**
	 * 查询分页数据
	 * @param evaReport 查询条件
	 * @param evaReport.page 分页对象
	 * @return
	 */
	@Override
	public Page<EvaReport> findPage(EvaReport evaReport) {
		return super.findPage(evaReport);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param evaReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EvaReport evaReport) {
		super.save(evaReport);
	}
	
	/**
	 * 更新状态
	 * @param evaReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EvaReport evaReport) {
		super.updateStatus(evaReport);
	}
	
	/**
	 * 删除数据
	 * @param evaReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EvaReport evaReport) {
		super.delete(evaReport);
	}
	
}