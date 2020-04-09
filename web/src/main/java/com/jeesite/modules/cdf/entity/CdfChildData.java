/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 门店预定业务销售Entity
 * @author liangjiawei
 * @version 2020-02-26
 */
@Table(name="cdf_child_data", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="sort", attrName="sort", label="排序号", isQuery=false),
		@Column(name="parent_data_id", attrName="parentDataId.id", label="父表主键"),
		@Column(name="channel", attrName="channel", label="渠道"),
		@Column(name="xs_scmsyd_sales", attrName="xsScmsydSales", label="线上商城免税预定销售额", isQuery=false),
		@Column(name="xs_scmsyd_qntq_sales", attrName="xsScmsydQntqSales", label="线上商城免税预订去年同期销售额", isQuery=false),
		@Column(name="xs_scmsyd_shoppers", attrName="xsScmsydShoppers", label="线上商城免税预定购物人数", isQuery=false),
		@Column(name="xs_scmsyd_qntq_shoppers", attrName="xsScmsydQntqShoppers", label="线上商城免税预定去年同期购物人数", isQuery=false),
	}, orderBy="a.id ASC"
)
public class CdfChildData extends DataEntity<CdfChildData> {
	
	private static final long serialVersionUID = 1L;
	private Long sort;		// 排序号
	private CdfParentData parentDataId;		// 父表主键 父类
	private String channel;		// 渠道
	private String xsScmsydSales;		// 线上商城免税预定销售额
	private String xsScmsydQntqSales;		// 线上商城免税预订去年同期销售额
	private String xsScmsydShoppers;		// 线上商城免税预定购物人数
	private String xsScmsydQntqShoppers;		// 线上商城免税预定去年同期购物人数
	
	public CdfChildData() {
		this(null);
	}


	public CdfChildData(CdfParentData parentDataId){
		this.parentDataId = parentDataId;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	@NotBlank(message="父表主键不能为空")
	@Length(min=0, max=64, message="父表主键长度不能超过 64 个字符")
	public CdfParentData getParentDataId() {
		return parentDataId;
	}

	public void setParentDataId(CdfParentData parentDataId) {
		this.parentDataId = parentDataId;
	}
	
	@NotBlank(message="渠道不能为空")
	@Length(min=0, max=200, message="渠道长度不能超过 200 个字符")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@NotBlank(message="线上商城免税预定销售额不能为空")
	@Length(min=0, max=200, message="线上商城免税预定销售额长度不能超过 200 个字符")
	public String getXsScmsydSales() {
		return xsScmsydSales;
	}

	public void setXsScmsydSales(String xsScmsydSales) {
		this.xsScmsydSales = xsScmsydSales;
	}
	
	@NotBlank(message="线上商城免税预订去年同期销售额不能为空")
	@Length(min=0, max=200, message="线上商城免税预订去年同期销售额长度不能超过 200 个字符")
	public String getXsScmsydQntqSales() {
		return xsScmsydQntqSales;
	}

	public void setXsScmsydQntqSales(String xsScmsydQntqSales) {
		this.xsScmsydQntqSales = xsScmsydQntqSales;
	}
	
	@NotBlank(message="线上商城免税预定购物人数不能为空")
	@Length(min=0, max=200, message="线上商城免税预定购物人数长度不能超过 200 个字符")
	public String getXsScmsydShoppers() {
		return xsScmsydShoppers;
	}

	public void setXsScmsydShoppers(String xsScmsydShoppers) {
		this.xsScmsydShoppers = xsScmsydShoppers;
	}
	
	@NotBlank(message="线上商城免税预定去年同期购物人数不能为空")
	@Length(min=0, max=200, message="线上商城免税预定去年同期购物人数长度不能超过 200 个字符")
	public String getXsScmsydQntqShoppers() {
		return xsScmsydQntqShoppers;
	}

	public void setXsScmsydQntqShoppers(String xsScmsydQntqShoppers) {
		this.xsScmsydQntqShoppers = xsScmsydQntqShoppers;
	}
	
}