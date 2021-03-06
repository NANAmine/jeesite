/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.service;

import com.jeesite.common.entity.DataScope;
import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.cdf.dao.CdfChildDataDao;
import com.jeesite.modules.cdf.dao.CdfParentDataDao;
import com.jeesite.modules.cdf.entity.CdfChildData;
import com.jeesite.modules.cdf.entity.CdfParentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门店预定业务销售Service
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
    /**
     * 添加数据权限过滤条件
     */
    @Override
    @Transactional(readOnly=false)
    public void addDataScopeFilter(CdfParentData cdfParentData){

        // 举例1：公司数据权限过滤，实体类@Table注解extWhereKeys="dsf"
        cdfParentData.getSqlMap().getDataScope().addFilter("dsf", "Company",
                "a.company_code", DataScope.CTRL_PERMI_HAVE);

        // 举例2：部门数据权限过滤，实体类@Table注解extWhereKeys="dsf"
        cdfParentData.getSqlMap().getDataScope().addFilter("dsf", "Office",
                "a.office_code", DataScope.CTRL_PERMI_HAVE);

        // 举例3：角色数据权限过滤，实体类@Table注解extWhereKeys="dsf"
        cdfParentData.getSqlMap().getDataScope().addFilter("dsf", "Role",
                "a.role_code", DataScope.CTRL_PERMI_HAVE);

        // 举例4：用户、员工（自己创建的）数据权限根据部门过滤，实体类@Table注解extWhereKeys="dsfOffice"
        cdfParentData.getSqlMap().getDataScope().addFilter("dsfOffice", "Office",
                "a.office_code", "a.create_by", DataScope.CTRL_PERMI_HAVE);

        // 举例5：用户、员工（自己创建的）数据权限根据公司过滤，实体类@Table注解extWhereKeys="dsfCompany"
        /*cdfParentData.getSqlMap().getDataScope().addFilter("dsfCompany", "Company",
                "a.company_code", "a.create_by", DataScope.CTRL_PERMI_HAVE);*/
    }
	
}