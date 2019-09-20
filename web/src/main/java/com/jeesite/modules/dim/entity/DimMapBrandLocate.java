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
 * 店铺号品牌映射Entity
 * @author ljw
 * @version 2019-09-19
 */
@Table(name="dim_map_brand_locate", alias="a", columns={
		@Column(name="id", attrName="id", label="序号", isPK=true),
		@Column(name="catname", attrName="catname", label="分类", queryType=QueryType.LIKE),
		@Column(name="shopcode", attrName="shopcode", label="店铺号", queryType=QueryType.LIKE),
		@Column(name="locationid", attrName="locationid", label="落位ID", queryType=QueryType.LIKE),
		@Column(name="square", attrName="square", label="店铺面积", queryType=QueryType.LIKE),
		@Column(name="mfcode", attrName="mfcode", label="柜组ID", queryType=QueryType.LIKE),
		@Column(name="mfcname", attrName="mfcname", label="柜组名称", queryType=QueryType.LIKE),
		@Column(name="brandcode", attrName="brandcode", label="品牌编码", queryType=QueryType.LIKE),
		@Column(name="brandname", attrName="brandname", label="品牌名称", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态"),
		@Column(name="update_by", attrName="updateBy", label="最近更新人"),
	}, orderBy="a.id DESC"
)
public class DimMapBrandLocate extends DataEntity<DimMapBrandLocate> {
	
	private static final long serialVersionUID = 1L;
	private String catname;		// 分类
	private String shopcode;		// 店铺号
	private String locationid;		// 落位ID
	private Double square;		// 店铺面积
	private String mfcode;		// 柜组ID
	private String mfcname;		// 柜组名称
	private String brandcode;		// 品牌编码
	private String brandname;		// 品牌名称
	
	public DimMapBrandLocate() {
		this(null);
	}

	public DimMapBrandLocate(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="分类长度不能超过 255 个字符")
	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}
	
	@Length(min=0, max=40, message="店铺号长度不能超过 40 个字符")
	public String getShopcode() {
		return shopcode;
	}

	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
	
	@Length(min=0, max=255, message="落位ID长度不能超过 255 个字符")
	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	
	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}
	
	@Length(min=0, max=255, message="柜组ID长度不能超过 255 个字符")
	public String getMfcode() {
		return mfcode;
	}

	public void setMfcode(String mfcode) {
		this.mfcode = mfcode;
	}
	
	@Length(min=0, max=255, message="柜组名称长度不能超过 255 个字符")
	public String getMfcname() {
		return mfcname;
	}

	public void setMfcname(String mfcname) {
		this.mfcname = mfcname;
	}
	
	@Length(min=0, max=255, message="品牌编码长度不能超过 255 个字符")
	public String getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode;
	}
	
	@Length(min=0, max=255, message="品牌名称长度不能超过 255 个字符")
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	
}