/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 药品管理Entity
 * @author ljw
 * @version 2019-05-09
 */
@Table(name="yp", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="ypmc", attrName="ypmc", label="药品名称", queryType=QueryType.LIKE),
		@Column(name="ypsj", attrName="ypsj", label="药品售价", queryType=QueryType.LIKE),
		@Column(name="ypkc", attrName="ypkc", label="药品库存", queryType=QueryType.LIKE),
		@Column(name="ypsccj", attrName="ypsccj", label="药品生产厂家", queryType=QueryType.LIKE),
		@Column(name="yb", attrName="yb", label="医保", comment="医保（是、否）", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class Yp extends DataEntity<Yp> {
	
	private static final long serialVersionUID = 1L;
	private String ypmc;		// 药品名称
	private String ypsj;		// 药品售价
	private String ypkc;		// 药品库存
	private String ypsccj;		// 药品生产厂家
	private String yb;		// 医保（是、否）
	
	public Yp() {
		this(null);
	}

	public Yp(String id){
		super(id);
	}
	
	@NotBlank(message="药品名称不能为空")
	@Length(min=0, max=20, message="药品名称长度不能超过 20 个字符")
	public String getYpmc() {
		return ypmc;
	}

	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	
	@NotBlank(message="药品售价不能为空")
	@Length(min=0, max=10, message="药品售价长度不能超过 10 个字符")
	public String getYpsj() {
		return ypsj;
	}

	public void setYpsj(String ypsj) {
		this.ypsj = ypsj;
	}
	
	@NotBlank(message="药品库存不能为空")
	@Length(min=0, max=10, message="药品库存长度不能超过 10 个字符")
	public String getYpkc() {
		return ypkc;
	}

	public void setYpkc(String ypkc) {
		this.ypkc = ypkc;
	}
	
	@Length(min=0, max=20, message="药品生产厂家长度不能超过 20 个字符")
	public String getYpsccj() {
		return ypsccj;
	}

	public void setYpsccj(String ypsccj) {
		this.ypsccj = ypsccj;
	}
	
	@Length(min=0, max=20, message="医保长度不能超过 20 个字符")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}
	
}