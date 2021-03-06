/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dim.entity.DimMapBrandLocate;

/**
 * 店铺号品牌映射DAO接口
 * @author ljw
 * @version 2019-09-19
 */
@MyBatisDao(dataSourceName="ds2")
public interface DimMapBrandLocateDao extends CrudDao<DimMapBrandLocate> {
	
}