/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 三亚销售目标Entity
 * @author ljw
 * @version 2019-09-20
 */
@Table(name="dim_sale_target_yyy", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="dst_dd_id", attrName="dstDdId", label="日期", queryType=QueryType.LIKE),
		@Column(name="dst_ds_id", attrName="dstDsId", label="门店编码", queryType=QueryType.LIKE),
		@Column(name="dst_dm_id", attrName="dstDmId", label="店面编码", queryType=QueryType.LIKE),
		@Column(name="dst_gz_id", attrName="dstGzId", label="柜组编码", queryType=QueryType.LIKE),
		@Column(name="dst_yyyh", attrName="dstYyyh", label="营业员号", queryType=QueryType.LIKE),
		@Column(name="dst_yyy_name", attrName="dstYyyName", label="营业员名称", queryType=QueryType.LIKE),
		@Column(name="dst_xse_target", attrName="dstXseTarget", label="营业员销售目标", isQuery=false),
		@Column(name="status", attrName="status", label="状态"),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class DimSaleTargetYyy extends DataEntity<DimSaleTargetYyy> {
	
	private static final long serialVersionUID = 1L;
	private String dstDdId;		// 日期
	private String dstDsId;		// 门店编码
	private String dstDmId;		// 店面编码
	private String dstGzId;		// 柜组编码
	private String dstYyyh;		// 营业员号
	private String dstYyyName;		// 营业员名称
	private Double dstXseTarget;		// 营业员销售目标
	
	public DimSaleTargetYyy() {
		this(null);
	}

	public DimSaleTargetYyy(String id){
		super(id);
	}
	
	@NotBlank(message="日期不能为空")
	@Length(min=0, max=100, message="日期长度不能超过 100 个字符")
	public String getDstDdId() {
		return dstDdId;
	}

	public void setDstDdId(String dstDdId) {
		this.dstDdId = dstDdId;
	}
	
	@Length(min=0, max=100, message="门店编码长度不能超过 100 个字符")
	public String getDstDsId() {
		return dstDsId;
	}

	public void setDstDsId(String dstDsId) {
		this.dstDsId = dstDsId;
	}
	
	@Length(min=0, max=100, message="店面编码长度不能超过 100 个字符")
	public String getDstDmId() {
		return dstDmId;
	}

	public void setDstDmId(String dstDmId) {
		this.dstDmId = dstDmId;
	}
	
	@Length(min=0, max=100, message="柜组编码长度不能超过 100 个字符")
	public String getDstGzId() {
		return dstGzId;
	}

	public void setDstGzId(String dstGzId) {
		this.dstGzId = dstGzId;
	}
	
	@Length(min=0, max=255, message="营业员号长度不能超过 255 个字符")
	public String getDstYyyh() {
		return dstYyyh;
	}

	public void setDstYyyh(String dstYyyh) {
		this.dstYyyh = dstYyyh;
	}
	
	@Length(min=0, max=255, message="营业员名称长度不能超过 255 个字符")
	public String getDstYyyName() {
		return dstYyyName;
	}

	public void setDstYyyName(String dstYyyName) {
		this.dstYyyName = dstYyyName;
	}
	
	public Double getDstXseTarget() {
		return dstXseTarget;
	}

	public void setDstXseTarget(Double dstXseTarget) {
		this.dstXseTarget = dstXseTarget;
	}
	
}