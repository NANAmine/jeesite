/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.bi.entity.BiTablesDeq;

/**
 * 通用字典表二DAO接口
 * @author ljw
 * @version 2019-09-24
 */
@MyBatisDao(dataSourceName="ds2")
public interface BiTablesDeqDao extends CrudDao<BiTablesDeq> {
	
}