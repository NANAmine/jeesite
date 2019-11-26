/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.bi.entity.BiJscJdldrs;

/**
 * 三亚客流DAO接口
 * @author ljw
 * @version 2019-09-17
 */
@MyBatisDao(dataSourceName="ds2")
public interface BiJscJdldrsDao extends CrudDao<BiJscJdldrs> {
	
}