/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.external.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 对外批发Entity
 * @author ljw
 * @version 2019-09-18
 */
@Table(name="external_wholesale_report", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="ewr_dd_id", attrName="ewrDdId", label="日期"),
		@Column(name="ewr_channel_name", attrName="ewrChannelName", label="渠道名称", isQuery=false),
		@Column(name="ewr_ds_name", attrName="ewrDsName", label="门店名称", isQuery=false),
		@Column(name="ewr_category_name", attrName="ewrCategoryName", label="品类名称", queryType=QueryType.LIKE),
		@Column(name="ewr_yysr_bnlj", attrName="ewrYysrBnlj", label="营业收入本年累计", isQuery=false),
		@Column(name="ewr_yysr_qnys", attrName="ewrYysrQnys", label="营业收入全年预算", isQuery=false),
		@Column(name="ewr_yysr_sntq", attrName="ewrYysrSntq", label="营业收入上年同期", isQuery=false),
		@Column(name="ewr_yysr_snqn", attrName="ewrYysrSnqn", label="营业收入上年全年", isQuery=false),
		@Column(name="ewr_lrze_bnlj", attrName="ewrLrzeBnlj", label="利润总额本年累计", isQuery=false),
		@Column(name="ewr_lrze_qnys", attrName="ewrLrzeQnys", label="利润总额全年预算", isQuery=false),
		@Column(name="ewr_lrze_sntq", attrName="ewrLrzeSntq", label="利润总额上年同期", isQuery=false),
		@Column(name="ewr_lrze_snqn", attrName="ewrLrzeSnqn", label="利润总额上年全年", isQuery=false),
		@Column(name="ewr_yycb_bnlj", attrName="ewrYycbBnlj", label="营业成本本年累计", isQuery=false),
		@Column(name="ewr_yycb_qnys", attrName="ewrYycbQnys", label="营业成本全年预算", isQuery=false),
		@Column(name="ewr_yycb_sntq", attrName="ewrYycbSntq", label="营业成本上年同期", isQuery=false),
		@Column(name="ewr_yycb_snqn", attrName="ewrYycbSnqn", label="营业成本上年全年", isQuery=false),
		@Column(name="ewr_mle_bnlj", attrName="ewrMleBnlj", label="毛利额本年累计", isQuery=false),
		@Column(name="ewr_mle_qnys", attrName="ewrMleQnys", label="毛利额全年预算", isQuery=false),
		@Column(name="ewr_mle_sntq", attrName="ewrMleSntq", label="毛利额上年同期", isQuery=false),
		@Column(name="ewr_mle_snqn", attrName="ewrMleSnqn", label="毛利额上年全年", isQuery=false),
		@Column(name="ewr_qjfy_bnlj", attrName="ewrQjfyBnlj", label="期间费用本年累计", isQuery=false),
		@Column(name="ewr_qjfy_qnys", attrName="ewrQjfyQnys", label="期间费用全年预算", isQuery=false),
		@Column(name="ewr_qjfy_sntq", attrName="ewrQjfySntq", label="期间费用上年同期", isQuery=false),
		@Column(name="statues", attrName="statues", label="状态"),
		@Column(name="update_by", attrName="updateBy", label="最近更新用户"),
	}, orderBy="a.id DESC"
)
public class ExternalWholesaleReport extends DataEntity<ExternalWholesaleReport> {
	
	private static final long serialVersionUID = 1L;
	private String ewrDdId;		// 日期
	private String ewrChannelName;		// 渠道名称
	private String ewrDsName;		// 门店名称
	private String ewrCategoryName;		// 品类名称
	private Double ewrYysrBnlj;		// 营业收入本年累计
	private Double ewrYysrQnys;		// 营业收入全年预算
	private Double ewrYysrSntq;		// 营业收入上年同期
	private Double ewrYysrSnqn;		// 营业收入上年全年
	private Double ewrLrzeBnlj;		// 利润总额本年累计
	private Double ewrLrzeQnys;		// 利润总额全年预算
	private Double ewrLrzeSntq;		// 利润总额上年同期
	private Double ewrLrzeSnqn;		// 利润总额上年全年
	private Double ewrYycbBnlj;		// 营业成本本年累计
	private Double ewrYycbQnys;		// 营业成本全年预算
	private Double ewrYycbSntq;		// 营业成本上年同期
	private Double ewrYycbSnqn;		// 营业成本上年全年
	private Double ewrMleBnlj;		// 毛利额本年累计
	private Double ewrMleQnys;		// 毛利额全年预算
	private Double ewrMleSntq;		// 毛利额上年同期
	private Double ewrMleSnqn;		// 毛利额上年全年
	private Double ewrQjfyBnlj;		// 期间费用本年累计
	private Double ewrQjfyQnys;		// 期间费用全年预算
	private Double ewrQjfySntq;		// 期间费用上年同期
	private String statues;		// 状态
	
	public ExternalWholesaleReport() {
		this(null);
	}

	public ExternalWholesaleReport(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="日期长度不能超过 100 个字符")
	public String getEwrDdId() {
		return ewrDdId;
	}

	public void setEwrDdId(String ewrDdId) {
		this.ewrDdId = ewrDdId;
	}
	
	@Length(min=0, max=100, message="渠道名称长度不能超过 100 个字符")
	public String getEwrChannelName() {
		return ewrChannelName;
	}

	public void setEwrChannelName(String ewrChannelName) {
		this.ewrChannelName = ewrChannelName;
	}
	
	@Length(min=0, max=100, message="门店名称长度不能超过 100 个字符")
	public String getEwrDsName() {
		return ewrDsName;
	}

	public void setEwrDsName(String ewrDsName) {
		this.ewrDsName = ewrDsName;
	}
	
	@Length(min=0, max=100, message="品类名称长度不能超过 100 个字符")
	public String getEwrCategoryName() {
		return ewrCategoryName;
	}

	public void setEwrCategoryName(String ewrCategoryName) {
		this.ewrCategoryName = ewrCategoryName;
	}
	
	public Double getEwrYysrBnlj() {
		return ewrYysrBnlj;
	}

	public void setEwrYysrBnlj(Double ewrYysrBnlj) {
		this.ewrYysrBnlj = ewrYysrBnlj;
	}
	
	public Double getEwrYysrQnys() {
		return ewrYysrQnys;
	}

	public void setEwrYysrQnys(Double ewrYysrQnys) {
		this.ewrYysrQnys = ewrYysrQnys;
	}
	
	public Double getEwrYysrSntq() {
		return ewrYysrSntq;
	}

	public void setEwrYysrSntq(Double ewrYysrSntq) {
		this.ewrYysrSntq = ewrYysrSntq;
	}
	
	public Double getEwrYysrSnqn() {
		return ewrYysrSnqn;
	}

	public void setEwrYysrSnqn(Double ewrYysrSnqn) {
		this.ewrYysrSnqn = ewrYysrSnqn;
	}
	
	public Double getEwrLrzeBnlj() {
		return ewrLrzeBnlj;
	}

	public void setEwrLrzeBnlj(Double ewrLrzeBnlj) {
		this.ewrLrzeBnlj = ewrLrzeBnlj;
	}
	
	public Double getEwrLrzeQnys() {
		return ewrLrzeQnys;
	}

	public void setEwrLrzeQnys(Double ewrLrzeQnys) {
		this.ewrLrzeQnys = ewrLrzeQnys;
	}
	
	public Double getEwrLrzeSntq() {
		return ewrLrzeSntq;
	}

	public void setEwrLrzeSntq(Double ewrLrzeSntq) {
		this.ewrLrzeSntq = ewrLrzeSntq;
	}
	
	public Double getEwrLrzeSnqn() {
		return ewrLrzeSnqn;
	}

	public void setEwrLrzeSnqn(Double ewrLrzeSnqn) {
		this.ewrLrzeSnqn = ewrLrzeSnqn;
	}
	
	public Double getEwrYycbBnlj() {
		return ewrYycbBnlj;
	}

	public void setEwrYycbBnlj(Double ewrYycbBnlj) {
		this.ewrYycbBnlj = ewrYycbBnlj;
	}
	
	public Double getEwrYycbQnys() {
		return ewrYycbQnys;
	}

	public void setEwrYycbQnys(Double ewrYycbQnys) {
		this.ewrYycbQnys = ewrYycbQnys;
	}
	
	public Double getEwrYycbSntq() {
		return ewrYycbSntq;
	}

	public void setEwrYycbSntq(Double ewrYycbSntq) {
		this.ewrYycbSntq = ewrYycbSntq;
	}
	
	public Double getEwrYycbSnqn() {
		return ewrYycbSnqn;
	}

	public void setEwrYycbSnqn(Double ewrYycbSnqn) {
		this.ewrYycbSnqn = ewrYycbSnqn;
	}
	
	public Double getEwrMleBnlj() {
		return ewrMleBnlj;
	}

	public void setEwrMleBnlj(Double ewrMleBnlj) {
		this.ewrMleBnlj = ewrMleBnlj;
	}
	
	public Double getEwrMleQnys() {
		return ewrMleQnys;
	}

	public void setEwrMleQnys(Double ewrMleQnys) {
		this.ewrMleQnys = ewrMleQnys;
	}
	
	public Double getEwrMleSntq() {
		return ewrMleSntq;
	}

	public void setEwrMleSntq(Double ewrMleSntq) {
		this.ewrMleSntq = ewrMleSntq;
	}
	
	public Double getEwrMleSnqn() {
		return ewrMleSnqn;
	}

	public void setEwrMleSnqn(Double ewrMleSnqn) {
		this.ewrMleSnqn = ewrMleSnqn;
	}
	
	public Double getEwrQjfyBnlj() {
		return ewrQjfyBnlj;
	}

	public void setEwrQjfyBnlj(Double ewrQjfyBnlj) {
		this.ewrQjfyBnlj = ewrQjfyBnlj;
	}
	
	public Double getEwrQjfyQnys() {
		return ewrQjfyQnys;
	}

	public void setEwrQjfyQnys(Double ewrQjfyQnys) {
		this.ewrQjfyQnys = ewrQjfyQnys;
	}
	
	public Double getEwrQjfySntq() {
		return ewrQjfySntq;
	}

	public void setEwrQjfySntq(Double ewrQjfySntq) {
		this.ewrQjfySntq = ewrQjfySntq;
	}
	
	@NotBlank(message="状态不能为空")
	@Length(min=0, max=1, message="状态长度不能超过 1 个字符")
	public String getStatues() {
		return statues;
	}

	public void setStatues(String statues) {
		this.statues = statues;
	}
	
}