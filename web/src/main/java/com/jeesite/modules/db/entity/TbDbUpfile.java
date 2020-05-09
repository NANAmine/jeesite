/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * tb_db_upfileEntity
 * @author liangjiawei
 * @version 2020-05-09
 */
@Table(name="tb_db_upfile", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="team_name", attrName="teamName", label="团队", queryType=QueryType.LIKE),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
		@Column(name="update_date", attrName="updateDate", label="最近更新时间"),
		@Column(name="status", attrName="status", label="状态，1失效0有效", isUpdate=false, isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TbDbUpfile extends DataEntity<TbDbUpfile> {
	
	private static final long serialVersionUID = 1L;
	private String teamName;		// 团队
	
	public TbDbUpfile() {
		this(null);
	}

	public TbDbUpfile(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="团队长度不能超过 255 个字符")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Date getUpdateDate_gte() {
		return sqlMap.getWhere().getValue("update_date", QueryType.GTE);
	}

	public void setUpdateDate_gte(Date updateDate) {
		sqlMap.getWhere().and("update_date", QueryType.GTE, updateDate);
	}
	
	public Date getUpdateDate_lte() {
		return sqlMap.getWhere().getValue("update_date", QueryType.LTE);
	}

	public void setUpdateDate_lte(Date updateDate) {
		sqlMap.getWhere().and("update_date", QueryType.LTE, updateDate);
	}
	
}