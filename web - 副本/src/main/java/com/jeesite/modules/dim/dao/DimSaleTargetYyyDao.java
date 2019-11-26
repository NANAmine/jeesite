/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dim.entity.DimSaleTargetYyy;

/**
 * 三亚销售目标DAO接口
 * @author ljw
 * @version 2019-09-20
 */
@MyBatisDao(dataSourceName="ds2")
public interface DimSaleTargetYyyDao extends CrudDao<DimSaleTargetYyy> {
	
}