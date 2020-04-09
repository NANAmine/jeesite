/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.cdf.entity.CdfParentData;

/**
 * 门店预定业务销售DAO接口
 * @author liangjiawei
 * @version 2020-02-26
 */
//@MyBatisDao //开发
@MyBatisDao(dataSourceName="ds2")
public interface CdfParentDataDao extends CrudDao<CdfParentData> {
	
}