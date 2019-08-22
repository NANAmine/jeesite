/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dim.dao.DimDictionaryDao;
import com.jeesite.modules.dim.entity.DimDictionary;
import com.jeesite.modules.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通用字典表Service
 * @author ljw
 * @version 2019-05-08
 */
@Service
@Transactional(readOnly=true)
public class DimDictionaryService extends CrudService<DimDictionaryDao, DimDictionary> {

	@Autowired
	private DimDictionaryDao dimDictionaryDao;
	/**
	 * 获取单条数据
	 * @param dimDictionary
	 * @return
	 */
	@Override
	public DimDictionary get(DimDictionary dimDictionary) {
		return super.get(dimDictionary);
	}

	/**
	 * 查询分页数据
	 * @param dimDictionary 查询条件
	 * @param dimDictionary.page 分页对象
	 * @return
	 */
	@Override
	public Page<DimDictionary> findPage(DimDictionary dimDictionary) {
		return super.findPage(dimDictionary);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param dimDictionary
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DimDictionary dimDictionary) {
		super.save(dimDictionary);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(dimDictionary.getId(), "dimDictionary_file");
	}

	/**
	 * 更新状态
	 * @param dimDictionary
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DimDictionary dimDictionary) {
		super.updateStatus(dimDictionary);
	}

	/**
	 * 删除数据
	 * @param dimDictionary
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DimDictionary dimDictionary) {
		super.delete(dimDictionary);
	}

	/**
	 * 删除全部数据
	 * @param dimDictionary
	 */
	@Transactional(readOnly=false)
	public void deleteAll(DimDictionary dimDictionary) {
		dimDictionaryDao.deleteAll(dimDictionary);
	}

}