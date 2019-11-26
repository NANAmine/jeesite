/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.external.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.external.entity.ExternalWholesaleReport;
import com.jeesite.modules.external.dao.ExternalWholesaleReportDao;

/**
 * 对外批发Service
 * @author ljw
 * @version 2019-09-18
 */
@Service
@Transactional(readOnly=true)
public class ExternalWholesaleReportService extends CrudService<ExternalWholesaleReportDao, ExternalWholesaleReport> {
	
	/**
	 * 获取单条数据
	 * @param externalWholesaleReport
	 * @return
	 */
	@Override
	public ExternalWholesaleReport get(ExternalWholesaleReport externalWholesaleReport) {
		return super.get(externalWholesaleReport);
	}
	
	/**
	 * 查询分页数据
	 * @param externalWholesaleReport 查询条件
	 * @param externalWholesaleReport.page 分页对象
	 * @return
	 */
	@Override
	public Page<ExternalWholesaleReport> findPage(ExternalWholesaleReport externalWholesaleReport) {
		return super.findPage(externalWholesaleReport);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param externalWholesaleReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ExternalWholesaleReport externalWholesaleReport) {
		super.save(externalWholesaleReport);
	}
	
	/**
	 * 更新状态
	 * @param externalWholesaleReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ExternalWholesaleReport externalWholesaleReport) {
		super.updateStatus(externalWholesaleReport);
	}
	
	/**
	 * 删除数据
	 * @param externalWholesaleReport
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ExternalWholesaleReport externalWholesaleReport) {
		super.delete(externalWholesaleReport);
	}
	
}