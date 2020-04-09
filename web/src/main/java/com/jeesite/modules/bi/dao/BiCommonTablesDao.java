/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.bi.entity.BiCommonTables;

/**
 * 通用字典表DAO接口
 * @author ljw
 * @version 2019-10-09
 */
//@MyBatisDao //开发
@MyBatisDao(dataSourceName="ds2")
public interface BiCommonTablesDao extends CrudDao<BiCommonTables> {
    int deleteAll(BiCommonTables biCommonTables);
    int upDelete(BiCommonTables biCommonTables);
}