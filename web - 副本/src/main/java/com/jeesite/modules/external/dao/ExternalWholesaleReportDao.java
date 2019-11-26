/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.external.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.external.entity.ExternalWholesaleReport;

/**
 * 对外批发DAO接口
 * @author ljw
 * @version 2019-09-18
 */
@MyBatisDao(dataSourceName="ds2")
public interface ExternalWholesaleReportDao extends CrudDao<ExternalWholesaleReport> {
	
}