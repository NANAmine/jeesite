/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 汇率数据Entity
 * @author ljw
 * @version 2019-10-21
 */
@Table(name="dim_texchange_rate", alias="a", columns={
		@Column(name="shijian", attrName="shijian", label="日期", isPK=true),
		@Column(name="mendian", attrName="mendain", label="门店", queryType=QueryType.LIKE),
		@Column(name="dalei", attrName="dalei", label="大类", queryType=QueryType.LIKE),
		@Column(name="huilv", attrName="huilv", label="汇率", queryType=QueryType.LIKE),
	}, orderBy="a.shijian DESC"
)
public class DimTexchangeRate extends DataEntity<DimTexchangeRate> {
	
	private static final long serialVersionUID = 1L;
	private String shijian;		// 日期
	private String mendain;		// 门店
	private String dalei;		// 大类
	private String huilv;		// 汇率
	
	public DimTexchangeRate() {
		this(null);
	}

	public DimTexchangeRate(String id){
		super(id);
	}
	
	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	
	@Length(min=0, max=255, message="门店长度不能超过 255 个字符")
	public String getMendain() {
		return mendain;
	}

	public void setMendain(String mendain) {
		this.mendain = mendain;
	}
	
	@Length(min=0, max=255, message="大类长度不能超过 255 个字符")
	public String getDalei() {
		return dalei;
	}

	public void setDalei(String dalei) {
		this.dalei = dalei;
	}
	
	@NotBlank(message="汇率不能为空")
	@Length(min=0, max=255, message="汇率长度不能超过 255 个字符")
	public String getHuilv() {
		return huilv;
	}

	public void setHuilv(String huilv) {
		this.huilv = huilv;
	}
	
}