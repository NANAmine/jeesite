/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.db.entity.TbDbCommonTables;

/**
 * tb_db_common_tablesDAO接口
 * @author liangjiawei
 * @version 2020-05-09
 */
@MyBatisDao
public interface TbDbCommonTablesDao extends CrudDao<TbDbCommonTables> {
	
}