/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.cdf.entity.CdfParentData;
import com.jeesite.modules.cdf.dao.CdfParentDataDao;
import com.jeesite.modules.cdf.entity.CdfChildData;
import com.jeesite.modules.cdf.dao.CdfChildDataDao;

/**
 * 新零售Service
 * @author liangjiawei
 * @version 2020-02-26
 */
@Service
@Transactional(readOnly=true)
public class CdfParentDataService extends CrudService<CdfParentDataDao, CdfParentData> {
	
	@Autowired
	private CdfChildDataDao cdfChildDataDao;
	
	/**
	 * 获取单条数据
	 * @param cdfParentData
	 * @return
	 */
	@Override
	public CdfParentData get(CdfParentData cdfParentData) {
		CdfParentData entity = super.get(cdfParentData);
		if (entity != null){
			CdfChildData cdfChildData = new CdfChildData(entity);
			cdfChildData.setStatus(CdfChildData.STATUS_NORMAL);
			entity.setCdfChildDataList(cdfChildDataDao.findList(cdfChildData));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param cdfParentData 查询条件
	 * @param cdfParentData.page 分页对象
	 * @return
	 */
	@Override
	public Page<CdfParentData> findPage(CdfParentData cdfParentData) {
		return super.findPage(cdfParentData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param cdfParentData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CdfParentData cdfParentData) {
		super.save(cdfParentData);
		// 保存 CdfParentData子表
		for (CdfChildData cdfChildData : cdfParentData.getCdfChildDataList()){
			if (!CdfChildData.STATUS_DELETE.equals(cdfChildData.getStatus())){
				cdfChildData.setParentDataId(cdfParentData);
				if (cdfChildData.getIsNewRecord()){
					cdfChildData.preInsert();
					cdfChildDataDao.insert(cdfChildData);
				}else{
					cdfChildData.preUpdate();
					cdfChildDataDao.update(cdfChildData);
				}
			}else{
				cdfChildDataDao.delete(cdfChildData);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param cdfParentData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CdfParentData cdfParentData) {
		super.updateStatus(cdfParentData);
	}
	
	/**
	 * 删除数据
	 * @param cdfParentData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CdfParentData cdfParentData) {
		super.delete(cdfParentData);
		CdfChildData cdfChildData = new CdfChildData();
		cdfChildData.setParentDataId(cdfParentData);
		cdfChildDataDao.deleteByEntity(cdfChildData);
	}
	
}