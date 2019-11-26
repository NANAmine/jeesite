/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dim.entity.DimTexchangeRate;

/**
 * 汇率数据DAO接口
 * @author ljw
 * @version 2019-10-21
 */
@MyBatisDao(dataSourceName="ds2")
public interface DimTexchangeRateDao extends CrudDao<DimTexchangeRate> {
	
}