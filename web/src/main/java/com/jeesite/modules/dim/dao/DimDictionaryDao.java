/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dim.entity.DimDictionary;

/**
 * 通用字典表DAO接口
 * @author ljw
 * @version 2019-06-17
 */
@MyBatisDao //开发
//@MyBatisDao(dataSourceName="ds2") //生产
public interface DimDictionaryDao extends CrudDao<DimDictionary> {
    int deleteAll(DimDictionary dimDictionary);
    int upDelete(DimDictionary dimDictionary);
}