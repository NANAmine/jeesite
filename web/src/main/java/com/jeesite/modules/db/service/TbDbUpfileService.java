/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.db.dao.TbDbCommonTablesDao;
import com.jeesite.modules.db.dao.TbDbUpfileDao;
import com.jeesite.modules.db.entity.TbDbCommonTables;
import com.jeesite.modules.db.entity.TbDbUpfile;
import com.jeesite.modules.file.entity.FileUpload;
import com.jeesite.modules.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * tb_db_upfileService
 * @author liangjiawei
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly=true)
public class TbDbUpfileService extends CrudService<TbDbUpfileDao, TbDbUpfile> {

    @Autowired
    private TbDbCommonTablesService tbDbCommonTablesService;
    private TbDbCommonTablesDao tbDbCommonTablesDao;
	/**
	 * 获取单条数据
	 * @param tbDbUpfile
	 * @return
	 */
	@Override
	public TbDbUpfile get(TbDbUpfile tbDbUpfile) {
		return super.get(tbDbUpfile);
	}
	
	/**
	 * 查询分页数据
	 * @param tbDbUpfile 查询条件
	 * @return
	 */
	@Override
	public Page<TbDbUpfile> findPage(TbDbUpfile tbDbUpfile) {
		return super.findPage(tbDbUpfile);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param tbDbUpfile
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TbDbUpfile tbDbUpfile) {
		super.save(tbDbUpfile);
		// 保存上传图片,匹配对应的信息记录
		FileUploadUtils.saveFileUpload(tbDbUpfile.getId(), "tbDbUpfile_image");
        List<FileUpload> listfile= new ArrayList<>();
        List<TbDbCommonTables> listdb= new ArrayList<>();
        TbDbCommonTables tbDbCommonTables = new TbDbCommonTables();
        listfile = FileUploadUtils.findFileUpload(tbDbUpfile.getId(), "tbDbUpfile_image");
        for (FileUpload file:listfile
        ) {
            tbDbCommonTables.setCommonC(file.getFileName().split("[\\.]")[0]);
            tbDbCommonTables.setCommonD(file.getFileName().split("[\\.]")[0]);
            tbDbCommonTables.setCommonE(file.getFileName().split("[\\.]")[0]);
            listdb = tbDbCommonTablesService.findListByFileName(tbDbCommonTables);
            if (listdb!=null && listdb.size() != 0){
                for (TbDbCommonTables item: listdb
                ) {
                    file.setBizKey(item.getId());
                    file.setBizType("biFashionDatabase_image");
                    FileUploadUtils.getFileUploadService().save(file);
                }
            }

        }
	}
	
	/**
	 * 更新状态
	 * @param tbDbUpfile
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TbDbUpfile tbDbUpfile) {
		super.updateStatus(tbDbUpfile);
	}
	
	/**
	 * 删除数据
	 * @param tbDbUpfile
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TbDbUpfile tbDbUpfile) {
		super.delete(tbDbUpfile);
	}


}