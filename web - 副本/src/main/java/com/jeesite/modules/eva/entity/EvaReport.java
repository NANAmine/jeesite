/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.eva.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

/**
 * EVAEntity
 * @author ljw
 * @version 2019-09-18
 */
@Table(name="eva_report", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="djfkt_dd_id", attrName="djfktDdId", label="日期", queryType=QueryType.LIKE),
		@Column(name="djfkt_ds_name", attrName="djfktDsName", label="门店名称"),
		@Column(name="djfkt_category", attrName="djfktCategory", label="品类名称"),
		@Column(name="djfkt_eva", attrName="djfktEva", label="本月累计"),
		@Column(name="djfkt_eva_lj", attrName="djfktEvaLj", label="本年累计"),
		@Column(name="djfkt_eva_wcl", attrName="djfktEvaWcl", label="本期完成进度"),
		@Column(name="djfkt_eva_tqwcl", attrName="djfktEvaTqwcl", label="同期完成进度"),
		@Column(name="djfkt_eva_tbzf", attrName="djfktEvaTbzf", label="同比增幅"),
		@Column(name="statues", attrName="statues", label="状态", isUpdate=false),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class EvaReport extends DataEntity<EvaReport> {
	
	private static final long serialVersionUID = 1L;
	private String djfktDdId;		// 日期
	private String djfktDsName;		// 门店名称
	private String djfktCategory;		// 品类名称
	private Double djfktEva;		// 本月累计
	private Double djfktEvaLj;		// 本年累计
	private Double djfktEvaWcl;		// 本期完成进度
	private Double djfktEvaTqwcl;		// 同期完成进度
	private Double djfktEvaTbzf;		// 同比增幅
	
	public EvaReport() {
		this(null);
	}

	public EvaReport(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="日期长度不能超过 255 个字符")
	public String getDjfktDdId() {
		return djfktDdId;
	}

	public void setDjfktDdId(String djfktDdId) {
		this.djfktDdId = djfktDdId;
	}
	
	@Length(min=0, max=255, message="门店名称长度不能超过 255 个字符")
	public String getDjfktDsName() {
		return djfktDsName;
	}

	public void setDjfktDsName(String djfktDsName) {
		this.djfktDsName = djfktDsName;
	}
	
	@Length(min=0, max=255, message="品类名称长度不能超过 255 个字符")
	public String getDjfktCategory() {
		return djfktCategory;
	}

	public void setDjfktCategory(String djfktCategory) {
		this.djfktCategory = djfktCategory;
	}
	
	public Double getDjfktEva() {
		return djfktEva;
	}

	public void setDjfktEva(Double djfktEva) {
		this.djfktEva = djfktEva;
	}
	
	public Double getDjfktEvaLj() {
		return djfktEvaLj;
	}

	public void setDjfktEvaLj(Double djfktEvaLj) {
		this.djfktEvaLj = djfktEvaLj;
	}
	
	public Double getDjfktEvaWcl() {
		return djfktEvaWcl;
	}

	public void setDjfktEvaWcl(Double djfktEvaWcl) {
		this.djfktEvaWcl = djfktEvaWcl;
	}
	
	public Double getDjfktEvaTqwcl() {
		return djfktEvaTqwcl;
	}

	public void setDjfktEvaTqwcl(Double djfktEvaTqwcl) {
		this.djfktEvaTqwcl = djfktEvaTqwcl;
	}
	
	public Double getDjfktEvaTbzf() {
		return djfktEvaTbzf;
	}

	public void setDjfktEvaTbzf(Double djfktEvaTbzf) {
		this.djfktEvaTbzf = djfktEvaTbzf;
	}

	
}