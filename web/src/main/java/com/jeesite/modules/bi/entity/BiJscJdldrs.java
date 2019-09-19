/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 三亚客流Entity
 * @author ljw
 * @version 2019-09-17
 */
@Table(name="bi_jsc_jdldrs", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="bsj_dd_id", attrName="bsjDdId", label="日期", comment="日期（年月）"),
		@Column(name="bsj_jdrs", attrName="bsjJdrs", label="进店人数", isQuery=false),
		@Column(name="bsj_ldrs", attrName="bsjLdrs", label="离岛人数", isQuery=false),
		@Column(name="status", attrName="status", label="状态", isUpdate=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class BiJscJdldrs extends DataEntity<BiJscJdldrs> {
	
	private static final long serialVersionUID = 1L;
	private String bsjDdId;		// 日期（年月）
	private Long bsjJdrs;		// 进店人数
	private Long bsjLdrs;		// 离岛人数
	
	public BiJscJdldrs() {
		this(null);
	}

	public BiJscJdldrs(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="日期长度不能超过 100 个字符")
	public String getBsjDdId() {
		return bsjDdId;
	}

	public void setBsjDdId(String bsjDdId) {
		this.bsjDdId = bsjDdId;
	}
	
	public Long getBsjJdrs() {
		return bsjJdrs;
	}

	public void setBsjJdrs(Long bsjJdrs) {
		this.bsjJdrs = bsjJdrs;
	}
	
	public Long getBsjLdrs() {
		return bsjLdrs;
	}

	public void setBsjLdrs(Long bsjLdrs) {
		this.bsjLdrs = bsjLdrs;
	}
	
}