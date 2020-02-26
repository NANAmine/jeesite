/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 新零售Entity
 * @author liangjiawei
 * @version 2020-02-26
 */
@Table(name="cdf_parent_data", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="store", attrName="store", label="门店"),
		@Column(name="time", attrName="time", label="时间"),
		@Column(name="xs_wsxs_sales", attrName="xsWsxsSales", label="线上完税销售销售额", isQuery=false),
		@Column(name="xs_wsxs_qntq_sales", attrName="xsWsxsQntqSales", label="线上完税销售去年同期销售额", isQuery=false),
		@Column(name="xs_wsxs_shoppers", attrName="xsWsxsShoppers", label="线上完税销售购物人数", isQuery=false),
		@Column(name="xs_wsxs_qntq_shoppers", attrName="xsWsxsQntqShoppers", label="线上完税销售去年同期购物人数", isQuery=false),
		@Column(name="xxxs_sales", attrName="xxxsSales", label="线下销售销售额", isQuery=false),
		@Column(name="xxxs_qntq_sales", attrName="xxxsQntqSales", label="线下销售去年同期销售额", isQuery=false),
		@Column(name="xxxs_shoppers", attrName="xxxsShoppers", label="线下销售购物人数", isQuery=false),
		@Column(name="xxxs_qntq_shoppers", attrName="xxxsQntqShoppers", label="线下销售去年同期购物人数", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class CdfParentData extends DataEntity<CdfParentData> {
	
	private static final long serialVersionUID = 1L;
	private String store;		// 门店
	private Date time;		// 时间
	private String xsWsxsSales;		// 线上完税销售销售额
	private String xsWsxsQntqSales;		// 线上完税销售去年同期销售额
	private String xsWsxsShoppers;		// 线上完税销售购物人数
	private String xsWsxsQntqShoppers;		// 线上完税销售去年同期购物人数
	private String xxxsSales;		// 线下销售销售额
	private String xxxsQntqSales;		// 线下销售去年同期销售额
	private String xxxsShoppers;		// 线下销售购物人数
	private String xxxsQntqShoppers;		// 线下销售去年同期购物人数
	private List<CdfChildData> cdfChildDataList = ListUtils.newArrayList();		// 子表列表
	
	public CdfParentData() {
		this(null);
	}

	public CdfParentData(String id){
		super(id);
	}
	
	@NotBlank(message="门店不能为空")
	@Length(min=0, max=200, message="门店长度不能超过 200 个字符")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="时间不能为空")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@NotBlank(message="线上完税销售销售额不能为空")
	@Length(min=0, max=200, message="线上完税销售销售额长度不能超过 200 个字符")
	public String getXsWsxsSales() {
		return xsWsxsSales;
	}

	public void setXsWsxsSales(String xsWsxsSales) {
		this.xsWsxsSales = xsWsxsSales;
	}
	
	@NotBlank(message="线上完税销售去年同期销售额不能为空")
	@Length(min=0, max=200, message="线上完税销售去年同期销售额长度不能超过 200 个字符")
	public String getXsWsxsQntqSales() {
		return xsWsxsQntqSales;
	}

	public void setXsWsxsQntqSales(String xsWsxsQntqSales) {
		this.xsWsxsQntqSales = xsWsxsQntqSales;
	}
	
	@NotBlank(message="线上完税销售购物人数不能为空")
	@Length(min=0, max=200, message="线上完税销售购物人数长度不能超过 200 个字符")
	public String getXsWsxsShoppers() {
		return xsWsxsShoppers;
	}

	public void setXsWsxsShoppers(String xsWsxsShoppers) {
		this.xsWsxsShoppers = xsWsxsShoppers;
	}
	
	@NotBlank(message="线上完税销售去年同期购物人数不能为空")
	@Length(min=0, max=200, message="线上完税销售去年同期购物人数长度不能超过 200 个字符")
	public String getXsWsxsQntqShoppers() {
		return xsWsxsQntqShoppers;
	}

	public void setXsWsxsQntqShoppers(String xsWsxsQntqShoppers) {
		this.xsWsxsQntqShoppers = xsWsxsQntqShoppers;
	}
	
	@NotBlank(message="线下销售销售额不能为空")
	@Length(min=0, max=200, message="线下销售销售额长度不能超过 200 个字符")
	public String getXxxsSales() {
		return xxxsSales;
	}

	public void setXxxsSales(String xxxsSales) {
		this.xxxsSales = xxxsSales;
	}
	
	@NotBlank(message="线下销售去年同期销售额不能为空")
	@Length(min=0, max=200, message="线下销售去年同期销售额长度不能超过 200 个字符")
	public String getXxxsQntqSales() {
		return xxxsQntqSales;
	}

	public void setXxxsQntqSales(String xxxsQntqSales) {
		this.xxxsQntqSales = xxxsQntqSales;
	}
	
	@NotBlank(message="线下销售购物人数不能为空")
	@Length(min=0, max=200, message="线下销售购物人数长度不能超过 200 个字符")
	public String getXxxsShoppers() {
		return xxxsShoppers;
	}

	public void setXxxsShoppers(String xxxsShoppers) {
		this.xxxsShoppers = xxxsShoppers;
	}
	
	@NotBlank(message="线下销售去年同期购物人数不能为空")
	@Length(min=0, max=200, message="线下销售去年同期购物人数长度不能超过 200 个字符")
	public String getXxxsQntqShoppers() {
		return xxxsQntqShoppers;
	}

	public void setXxxsQntqShoppers(String xxxsQntqShoppers) {
		this.xxxsQntqShoppers = xxxsQntqShoppers;
	}
	
	public List<CdfChildData> getCdfChildDataList() {
		return cdfChildDataList;
	}

	public void setCdfChildDataList(List<CdfChildData> cdfChildDataList) {
		this.cdfChildDataList = cdfChildDataList;
	}
	
}