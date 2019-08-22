/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 通用字典表Entity
 * @author ljw
 * @version 2019-05-29
 */
@Table(name="dim_dictionary", alias="a", columns={
		@Column(name="ddic_id", attrName="ddicId", label="主键", isPK=true),
		@Column(name="ddic_code", attrName="ddicCode", label="编码"),
		@Column(name="ddic_name", attrName="ddicName", label="门店名称", queryType=QueryType.LIKE),
		@Column(name="ddic_ename", attrName="ddicEname", label="门店简称", queryType=QueryType.LIKE),
		@Column(name="ddic_value", attrName="ddicValue", label="门店编码", queryType=QueryType.LIKE),
		@Column(name="ddic_desc", attrName="ddicDesc", label="描述", queryType=QueryType.LIKE),
		@Column(name="ddic_pid", attrName="ddicPid", label="父级编码", isQuery=false),
		@Column(name="ddic_expand1", attrName="ddicExpand1", label="部门名称", queryType=QueryType.LIKE),
		@Column(name="ddic_expand2", attrName="ddicExpand2", label="备用1", isQuery=false),
		@Column(name="ddic_expand3", attrName="ddicExpand3", label="备用2", isQuery=false),
		@Column(name="ddic_expand4", attrName="ddicExpand4", label="备用3", isQuery=false),
		@Column(name="status", attrName="status", label="状态", isUpdate=false, queryType=QueryType.LIKE),
		@Column(name="ddic_sort", attrName="ddicSort", label="备用4", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="数据插入时间", isUpdate=false),
		@Column(name="update_date", attrName="updateDate", label="更数据最近新时间"),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class DimDictionary extends DataEntity<DimDictionary> {
	
	private static final long serialVersionUID = 1L;
	private String ddicId;		// 主键
	private String ddicCode;		// 编码
	private String ddicName;		// 门店名称
	private String ddicEname;		// 门店简称
	private String ddicValue;		// 门店编码
	private String ddicDesc;		// 描述
	private String ddicPid;		// 父级编码
	private String ddicExpand1;		// 部门名称
	private String ddicExpand2;		// 备用1
	private String ddicExpand3;		// 备用2
	private String ddicExpand4;		// 备用3
	private String ddicSort;		// 备用4
	
	public DimDictionary() {
		this(null);
	}

	public DimDictionary(String id){
		super(id);
	}
	
	public String getDdicId() {
		return ddicId;
	}

	public void setDdicId(String ddicId) {
		this.ddicId = ddicId;
	}
	
	@Length(min=0, max=50, message="编码长度不能超过 50 个字符")
	public String getDdicCode() {
		return ddicCode;
	}

	public void setDdicCode(String ddicCode) {
		this.ddicCode = ddicCode;
	}
	
	@Length(min=0, max=200, message="门店名称长度不能超过 200 个字符")
	public String getDdicName() {
		return ddicName;
	}

	public void setDdicName(String ddicName) {
		this.ddicName = ddicName;
	}
	
	@Length(min=0, max=200, message="门店简称长度不能超过 200 个字符")
	public String getDdicEname() {
		return ddicEname;
	}

	public void setDdicEname(String ddicEname) {
		this.ddicEname = ddicEname;
	}
	
	@Length(min=0, max=200, message="门店编码长度不能超过 200 个字符")
	public String getDdicValue() {
		return ddicValue;
	}

	public void setDdicValue(String ddicValue) {
		this.ddicValue = ddicValue;
	}
	
	@Length(min=0, max=1000, message="描述长度不能超过 1000 个字符")
	public String getDdicDesc() {
		return ddicDesc;
	}

	public void setDdicDesc(String ddicDesc) {
		this.ddicDesc = ddicDesc;
	}
	
	@Length(min=0, max=30, message="父级编码长度不能超过 30 个字符")
	public String getDdicPid() {
		return ddicPid;
	}

	public void setDdicPid(String ddicPid) {
		this.ddicPid = ddicPid;
	}
	
	@Length(min=0, max=200, message="部门名称长度不能超过 200 个字符")
	public String getDdicExpand1() {
		return ddicExpand1;
	}

	public void setDdicExpand1(String ddicExpand1) {
		this.ddicExpand1 = ddicExpand1;
	}
	
	@Length(min=0, max=200, message="备用1长度不能超过 200 个字符")
	public String getDdicExpand2() {
		return ddicExpand2;
	}

	public void setDdicExpand2(String ddicExpand2) {
		this.ddicExpand2 = ddicExpand2;
	}
	
	@Length(min=0, max=200, message="备用2长度不能超过 200 个字符")
	public String getDdicExpand3() {
		return ddicExpand3;
	}

	public void setDdicExpand3(String ddicExpand3) {
		this.ddicExpand3 = ddicExpand3;
	}
	
	@Length(min=0, max=200, message="备用3长度不能超过 200 个字符")
	public String getDdicExpand4() {
		return ddicExpand4;
	}

	public void setDdicExpand4(String ddicExpand4) {
		this.ddicExpand4 = ddicExpand4;
	}
	
	@Length(min=0, max=11, message="备用4长度不能超过 11 个字符")
	public String getDdicSort() {
		return ddicSort;
	}

	public void setDdicSort(String ddicSort) {
		this.ddicSort = ddicSort;
	}
	
	public Date getCreateDate_gte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.GTE);
	}

	public void setCreateDate_gte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.GTE, createDate);
	}
	
	public Date getCreateDate_lte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.LTE);
	}

	public void setCreateDate_lte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.LTE, createDate);
	}
	
	public Date getUpdateDate_gte() {
		return sqlMap.getWhere().getValue("update_date", QueryType.GTE);
	}

	public void setUpdateDate_gte(Date updateDate) {
		sqlMap.getWhere().and("update_date", QueryType.GTE, updateDate);
	}
	
	public Date getUpdateDate_lte() {
		return sqlMap.getWhere().getValue("update_date", QueryType.LTE);
	}

	public void setUpdateDate_lte(Date updateDate) {
		sqlMap.getWhere().and("update_date", QueryType.LTE, updateDate);
	}
	
}