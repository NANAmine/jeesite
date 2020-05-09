/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.db.entity.TbDbCommonTables;
import com.jeesite.modules.db.dao.TbDbCommonTablesDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * tb_db_common_tablesService
 * @author liangjiawei
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly=true)
public class TbDbCommonTablesService extends CrudService<TbDbCommonTablesDao, TbDbCommonTables> {
	
	/**
	 * 获取单条数据
	 * @param tbDbCommonTables
	 * @return
	 */
	@Override
	public TbDbCommonTables get(TbDbCommonTables tbDbCommonTables) {
		return super.get(tbDbCommonTables);
	}
	
	/**
	 * 查询分页数据
	 * @param tbDbCommonTables 查询条件
	 * @param tbDbCommonTables.page 分页对象
	 * @return
	 */
	@Override
	public Page<TbDbCommonTables> findPage(TbDbCommonTables tbDbCommonTables) {
		return super.findPage(tbDbCommonTables);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param tbDbCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TbDbCommonTables tbDbCommonTables) {
		super.save(tbDbCommonTables);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(tbDbCommonTables.getId(), "tbDbCommonTables_image");
	}
	
	/**
	 * 更新状态
	 * @param tbDbCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TbDbCommonTables tbDbCommonTables) {
		super.updateStatus(tbDbCommonTables);
	}
	
	/**
	 * 删除数据
	 * @param tbDbCommonTables
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TbDbCommonTables tbDbCommonTables) {
		super.delete(tbDbCommonTables);
	}
	
}