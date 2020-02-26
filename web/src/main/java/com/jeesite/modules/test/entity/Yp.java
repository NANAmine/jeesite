/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 药品管理Entity
 * @author ljw
 * @version 2020-02-26
 */
@Table(name="yp", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="ypmc", attrName="ypmc", label="药品名称"),
		@Column(name="ypsj", attrName="ypsj", label="药品售价"),
		@Column(name="ypkc", attrName="ypkc", label="药品库存"),
		@Column(name="ypsccj", attrName="ypsccj", label="药品生产厂家"),
		@Column(name="yb", attrName="yb", label="医保", comment="医保（是、否）"),
		@Column(name="status", attrName="status", label="状态", isUpdate=false),
		@Column(name="time", attrName="time", label="time"),
	}, orderBy="a.id DESC"
)
public class Yp extends DataEntity<Yp> {
	
	private static final long serialVersionUID = 1L;
	private String ypmc;		// 药品名称
	private String ypsj;		// 药品售价
	private String ypkc;		// 药品库存
	private String ypsccj;		// 药品生产厂家
	private String yb;		// 医保（是、否）
	private Date time;		// time
	
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
	
	@Length(min=0, max=10, message="药品售价长度不能超过 10 个字符")
	public String getYpsj() {
		return ypsj;
	}

	public void setYpsj(String ypsj) {
		this.ypsj = ypsj;
	}
	
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}