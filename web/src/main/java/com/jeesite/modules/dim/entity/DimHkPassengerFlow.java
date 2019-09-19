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
 * 香港客流Entity
 * @author ljw
 * @version 2019-09-17
 */
@Table(name="dim_hk_passenger_flow", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="dhpf_dd_id", attrName="dhpfDdId", label="日期年月"),
		@Column(name="dhpf_ds_id", attrName="dhpfDsId", label="门店6915", isQuery=false),
		@Column(name="dhpf_jczrs_bylj", attrName="dhpfJczrsBylj", label="机场总人数本月累计", isQuery=false),
		@Column(name="dhpf_jczrs_byys", attrName="dhpfJczrsByys", label="机场总人数本月预算", isQuery=false),
		@Column(name="dhpf_jczrs_bylj_tq", attrName="dhpfJczrsByljTq", label="机场总人数去年同期月累计", isQuery=false),
		@Column(name="dhpf_jczrs_byys_tq", attrName="dhpfJczrsByysTq", label="机场总人数去年同期月预算", isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态", isUpdate=false),
	}, orderBy="a.id DESC"
)
public class DimHkPassengerFlow extends DataEntity<DimHkPassengerFlow> {
	
	private static final long serialVersionUID = 1L;
	private String dhpfDdId;		// 日期年月
	private String dhpfDsId;		// 门店6915
	private Double dhpfJczrsBylj;		// 机场总人数本月累计
	private Double dhpfJczrsByys;		// 机场总人数本月预算
	private Double dhpfJczrsByljTq;		// 机场总人数去年同期月累计
	private Double dhpfJczrsByysTq;		// 机场总人数去年同期月预算
	
	public DimHkPassengerFlow() {
		this(null);
	}

	public DimHkPassengerFlow(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="日期年月长度不能超过 100 个字符")
	public String getDhpfDdId() {
		return dhpfDdId;
	}

	public void setDhpfDdId(String dhpfDdId) {
		this.dhpfDdId = dhpfDdId;
	}
	
	@Length(min=0, max=100, message="门店6915长度不能超过 100 个字符")
	public String getDhpfDsId() {
		return dhpfDsId;
	}

	public void setDhpfDsId(String dhpfDsId) {
		this.dhpfDsId = dhpfDsId;
	}
	
	public Double getDhpfJczrsBylj() {
		return dhpfJczrsBylj;
	}

	public void setDhpfJczrsBylj(Double dhpfJczrsBylj) {
		this.dhpfJczrsBylj = dhpfJczrsBylj;
	}
	
	public Double getDhpfJczrsByys() {
		return dhpfJczrsByys;
	}

	public void setDhpfJczrsByys(Double dhpfJczrsByys) {
		this.dhpfJczrsByys = dhpfJczrsByys;
	}
	
	public Double getDhpfJczrsByljTq() {
		return dhpfJczrsByljTq;
	}

	public void setDhpfJczrsByljTq(Double dhpfJczrsByljTq) {
		this.dhpfJczrsByljTq = dhpfJczrsByljTq;
	}
	
	public Double getDhpfJczrsByysTq() {
		return dhpfJczrsByysTq;
	}

	public void setDhpfJczrsByysTq(Double dhpfJczrsByysTq) {
		this.dhpfJczrsByysTq = dhpfJczrsByysTq;
	}
	
}