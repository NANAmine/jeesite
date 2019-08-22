/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.SqCustomer;

/**
 * 顾客表DAO接口
 * @author ljw
 * @version 2019-04-29
 */
@MyBatisDao
public interface SqCustomerDao extends CrudDao<SqCustomer> {
	
}