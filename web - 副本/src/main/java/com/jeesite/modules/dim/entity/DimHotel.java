/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 三亚酒店基本信息Entity
 * @author ljw
 * @version 2019-09-19
 */
@Table(name="dim_hotel", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="dh_code", attrName="dhCode", label="酒店编码", queryType=QueryType.LIKE),
		@Column(name="dh_name", attrName="dhName", label="酒店名称", queryType=QueryType.LIKE),
		@Column(name="dh_location", attrName="dhLocation", label="酒店位置", queryType=QueryType.LIKE),
		@Column(name="dh_level", attrName="dhLevel", label="酒店星级", queryType=QueryType.LIKE),
		@Column(name="dh_kfs", attrName="dhKfs", label="dh_kfs", isQuery=false),
		@Column(name="dh_rzl", attrName="dhRzl", label="dh_rzl", isQuery=false),
		@Column(name="status", attrName="status", label="状态"),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class DimHotel extends DataEntity<DimHotel> {
	
	private static final long serialVersionUID = 1L;
	private String dhCode;		// 酒店编码
	private String dhName;		// 酒店名称
	private String dhLocation;		// 酒店位置
	private String dhLevel;		// 酒店星级
	private Double dhKfs;		// dh_kfs
	private Double dhRzl;		// dh_rzl
	
	public DimHotel() {
		this(null);
	}

	public DimHotel(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="酒店编码长度不能超过 255 个字符")
	public String getDhCode() {
		return dhCode;
	}

	public void setDhCode(String dhCode) {
		this.dhCode = dhCode;
	}
	
	@Length(min=0, max=255, message="酒店名称长度不能超过 255 个字符")
	public String getDhName() {
		return dhName;
	}

	public void setDhName(String dhName) {
		this.dhName = dhName;
	}
	
	@Length(min=0, max=255, message="酒店位置长度不能超过 255 个字符")
	public String getDhLocation() {
		return dhLocation;
	}

	public void setDhLocation(String dhLocation) {
		this.dhLocation = dhLocation;
	}
	
	@Length(min=0, max=255, message="酒店星级长度不能超过 255 个字符")
	public String getDhLevel() {
		return dhLevel;
	}

	public void setDhLevel(String dhLevel) {
		this.dhLevel = dhLevel;
	}
	
	public Double getDhKfs() {
		return dhKfs;
	}

	public void setDhKfs(Double dhKfs) {
		this.dhKfs = dhKfs;
	}
	
	public Double getDhRzl() {
		return dhRzl;
	}

	public void setDhRzl(Double dhRzl) {
		this.dhRzl = dhRzl;
	}
	
}