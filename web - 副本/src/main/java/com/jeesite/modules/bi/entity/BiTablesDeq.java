/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 通用字典表二Entity
 * @author ljw
 * @version 2019-09-24
 */
@Table(name="bi_tables_deq", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="table_id", attrName="tableId", label="维护表编号"),
		@Column(name="bsj_dd_id", attrName="bsjDdId", label="日期", comment="日期（年月）", queryType=QueryType.LIKE),
		@Column(name="bsj_jdrs", attrName="bsjJdrs", label="进店人数", queryType=QueryType.LIKE),
		@Column(name="bsj_ldrs", attrName="bsjLdrs", label="离岛人数", queryType=QueryType.LIKE),
		@Column(name="dhpf_dd_id", attrName="dhpfDdId", label="日期年月", queryType=QueryType.LIKE),
		@Column(name="dhpf_ds_id", attrName="dhpfDsId", label="门店6915", queryType=QueryType.LIKE),
		@Column(name="dhpf_jczrs_bylj", attrName="dhpfJczrsBylj", label="机场总人数本月累计", queryType=QueryType.LIKE),
		@Column(name="dhpf_jczrs_byys", attrName="dhpfJczrsByys", label="机场总人数本月预算", queryType=QueryType.LIKE),
		@Column(name="dhpf_jczrs_bylj_tq", attrName="dhpfJczrsByljTq", label="机场总人数去年同期月累计", queryType=QueryType.LIKE),
		@Column(name="dhpf_jczrs_byys_tq", attrName="dhpfJczrsByysTq", label="机场总人数去年同期月预算", queryType=QueryType.LIKE),
		@Column(name="djfkt_dd_id", attrName="djfktDdId", label="日期", queryType=QueryType.LIKE),
		@Column(name="djfkt_ds_name", attrName="djfktDsName", label="门店名称", queryType=QueryType.LIKE),
		@Column(name="djfkt_category", attrName="djfktCategory", label="djfkt_category", queryType=QueryType.LIKE),
		@Column(name="djfkt_eva", attrName="djfktEva", label="当月值", queryType=QueryType.LIKE),
		@Column(name="djfkt_eva_lj", attrName="djfktEvaLj", label="累计值", queryType=QueryType.LIKE),
		@Column(name="djfkt_eva_wcl", attrName="djfktEvaWcl", label="本期完成率", queryType=QueryType.LIKE),
		@Column(name="djfkt_eva_tqwcl", attrName="djfktEvaTqwcl", label="同期完成率", queryType=QueryType.LIKE),
		@Column(name="djfkt_eva_tbzf", attrName="djfktEvaTbzf", label="同比增幅", queryType=QueryType.LIKE),
		@Column(name="ewr_dd_id", attrName="ewrDdId", label="日期", queryType=QueryType.LIKE),
		@Column(name="ewr_channel_name", attrName="ewrChannelName", label="渠道名称", queryType=QueryType.LIKE),
		@Column(name="ewr_ds_name", attrName="ewrDsName", label="门店名称", queryType=QueryType.LIKE),
		@Column(name="ewr_category_name", attrName="ewrCategoryName", label="品类名称", queryType=QueryType.LIKE),
		@Column(name="ewr_yysr_bnlj", attrName="ewrYysrBnlj", label="营业收入本年累计", queryType=QueryType.LIKE),
		@Column(name="ewr_yysr_qnys", attrName="ewrYysrQnys", label="营业收入全年预算", queryType=QueryType.LIKE),
		@Column(name="ewr_yysr_sntq", attrName="ewrYysrSntq", label="营业收入上年同期", queryType=QueryType.LIKE),
		@Column(name="ewr_yysr_snqn", attrName="ewrYysrSnqn", label="营业收入上年全年", queryType=QueryType.LIKE),
		@Column(name="ewr_lrze_bnlj", attrName="ewrLrzeBnlj", label="利润总额本年累计", queryType=QueryType.LIKE),
		@Column(name="ewr_lrze_qnys", attrName="ewrLrzeQnys", label="利润总额全年预算", queryType=QueryType.LIKE),
		@Column(name="ewr_lrze_sntq", attrName="ewrLrzeSntq", label="利润总额上年同期", queryType=QueryType.LIKE),
		@Column(name="ewr_lrze_snqn", attrName="ewrLrzeSnqn", label="利润总额上年全年", queryType=QueryType.LIKE),
		@Column(name="ewr_yycb_bnlj", attrName="ewrYycbBnlj", label="营业成本本年累计", queryType=QueryType.LIKE),
		@Column(name="ewr_yycb_qnys", attrName="ewrYycbQnys", label="营业成本全年预算", queryType=QueryType.LIKE),
		@Column(name="ewr_yycb_sntq", attrName="ewrYycbSntq", label="营业成本上年同期", queryType=QueryType.LIKE),
		@Column(name="ewr_yycb_snqn", attrName="ewrYycbSnqn", label="营业成本上年全年", queryType=QueryType.LIKE),
		@Column(name="ewr_mle_bnlj", attrName="ewrMleBnlj", label="毛利额本年累计", queryType=QueryType.LIKE),
		@Column(name="ewr_mle_qnys", attrName="ewrMleQnys", label="毛利额全年预算", queryType=QueryType.LIKE),
		@Column(name="ewr_mle_sntq", attrName="ewrMleSntq", label="毛利额上年同期", queryType=QueryType.LIKE),
		@Column(name="ewr_mle_snqn", attrName="ewrMleSnqn", label="毛利额上年全年", queryType=QueryType.LIKE),
		@Column(name="ewr_qjfy_bnlj", attrName="ewrQjfyBnlj", label="期间费用本年累计", queryType=QueryType.LIKE),
		@Column(name="ewr_qjfy_qnys", attrName="ewrQjfyQnys", label="期间费用全年预算", queryType=QueryType.LIKE),
		@Column(name="ewr_qjfy_sntq", attrName="ewrQjfySntq", label="期间费用上年同期", queryType=QueryType.LIKE),
		@Column(name="dh_code", attrName="dhCode", label="酒店编码", queryType=QueryType.LIKE),
		@Column(name="dh_name", attrName="dhName", label="酒店名称", queryType=QueryType.LIKE),
		@Column(name="dh_location", attrName="dhLocation", label="酒店位置", queryType=QueryType.LIKE),
		@Column(name="dh_level", attrName="dhLevel", label="酒店星级", queryType=QueryType.LIKE),
		@Column(name="dh_kfs", attrName="dhKfs", label="dh_kfs", queryType=QueryType.LIKE),
		@Column(name="dh_rzl", attrName="dhRzl", label="dh_rzl", queryType=QueryType.LIKE),
		@Column(name="catname", attrName="catname", label="分类", queryType=QueryType.LIKE),
		@Column(name="shopcode", attrName="shopcode", label="店铺号", queryType=QueryType.LIKE),
		@Column(name="locationid", attrName="locationid", label="落位ID", queryType=QueryType.LIKE),
		@Column(name="square", attrName="square", label="店铺面积", queryType=QueryType.LIKE),
		@Column(name="mfcode", attrName="mfcode", label="柜组ID", queryType=QueryType.LIKE),
		@Column(name="mfcname", attrName="mfcname", label="柜组名称", queryType=QueryType.LIKE),
		@Column(name="brandcode", attrName="brandcode", label="品牌编码", queryType=QueryType.LIKE),
		@Column(name="brandname", attrName="brandname", label="品牌名称", queryType=QueryType.LIKE),
		@Column(name="dst_dd_id", attrName="dstDdId", label="日期", queryType=QueryType.LIKE),
		@Column(name="dst_ds_id", attrName="dstDsId", label="门店编码", queryType=QueryType.LIKE),
		@Column(name="dst_dm_id", attrName="dstDmId", label="店面编码", queryType=QueryType.LIKE),
		@Column(name="dst_gz_id", attrName="dstGzId", label="柜组编码", queryType=QueryType.LIKE),
		@Column(name="dst_yyyh", attrName="dstYyyh", label="营业员号", queryType=QueryType.LIKE),
		@Column(name="dst_yyy_name", attrName="dstYyyName", label="营业员名称", queryType=QueryType.LIKE),
		@Column(name="dst_xse_target", attrName="dstXseTarget", label="营业员销售目标", queryType=QueryType.LIKE),
		@Column(name="dd_id", attrName="ddId", label="日期", queryType=QueryType.LIKE),
		@Column(name="dd_lxsid", attrName="ddLxsid", label="旅行社或会展编码", queryType=QueryType.LIKE),
		@Column(name="dd_lxsmc", attrName="ddLxsmc", label="旅行社或会展名称", queryType=QueryType.LIKE),
		@Column(name="dd_yyrs", attrName="ddYyrs", label="预约人数", queryType=QueryType.LIKE),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
		@Column(name="update_date", attrName="updateDate", label="最近更新时间", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态", isUpdate=false, queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class BiTablesDeq extends DataEntity<BiTablesDeq> {
	
	private static final long serialVersionUID = 1L;
	private String tableId;		// 维护表编号
	private String bsjDdId;		// 日期（年月）
	private Long bsjJdrs;		// 进店人数
	private Long bsjLdrs;		// 离岛人数
	private String dhpfDdId;		// 日期年月
	private String dhpfDsId;		// 门店6915
	private Double dhpfJczrsBylj;		// 机场总人数本月累计
	private Double dhpfJczrsByys;		// 机场总人数本月预算
	private Double dhpfJczrsByljTq;		// 机场总人数去年同期月累计
	private Double dhpfJczrsByysTq;		// 机场总人数去年同期月预算
	private String djfktDdId;		// 日期
	private String djfktDsName;		// 门店名称
	private String djfktCategory;		// djfkt_category
	private Double djfktEva;		// 当月值
	private Double djfktEvaLj;		// 累计值
	private Double djfktEvaWcl;		// 本期完成率
	private Double djfktEvaTqwcl;		// 同期完成率
	private Double djfktEvaTbzf;		// 同比增幅
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
	private String dhCode;		// 酒店编码
	private String dhName;		// 酒店名称
	private String dhLocation;		// 酒店位置
	private String dhLevel;		// 酒店星级
	private Double dhKfs;		// dh_kfs
	private Double dhRzl;		// dh_rzl
	private String catname;		// 分类
	private String shopcode;		// 店铺号
	private String locationid;		// 落位ID
	private Double square;		// 店铺面积
	private String mfcode;		// 柜组ID
	private String mfcname;		// 柜组名称
	private String brandcode;		// 品牌编码
	private String brandname;		// 品牌名称
	private String dstDdId;		// 日期
	private String dstDsId;		// 门店编码
	private String dstDmId;		// 店面编码
	private String dstGzId;		// 柜组编码
	private String dstYyyh;		// 营业员号
	private String dstYyyName;		// 营业员名称
	private Double dstXseTarget;		// 营业员销售目标
	private String ddId;		// 日期
	private String ddLxsid;		// 旅行社或会展编码
	private String ddLxsmc;		// 旅行社或会展名称
	private Long ddYyrs;		// 预约人数
	
	public BiTablesDeq() {
		this(null);
	}

	public BiTablesDeq(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="维护表编号长度不能超过 100 个字符")
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	@Length(min=0, max=100, message="日期长度不能超过 100 个字符")
	public String getBsjDdId() {
		return bsjDdId;
	}

	public void setBsjDdId(String bsjDdId) {
		this.bsjDdId = bsjDdId;
	}
	
	public Long getBsjJdrs() {
		return bsjJdrs;
	}

	public void setBsjJdrs(Long bsjJdrs) {
		this.bsjJdrs = bsjJdrs;
	}
	
	public Long getBsjLdrs() {
		return bsjLdrs;
	}

	public void setBsjLdrs(Long bsjLdrs) {
		this.bsjLdrs = bsjLdrs;
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
	
	@Length(min=0, max=255, message="djfkt_category长度不能超过 255 个字符")
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
	
	@Length(min=0, max=255, message="酒店编码长度不能超过 255 个字符")
	public String getDhCode() {
		return dhCode;
	}

	public void setDhCode(String dhCode) {
		this.dhCode = dhCode;
	}
	
	@Length(min=0, max=255, message="酒店名称长度不能超过 255 个字符")
	public String getDhName() {
		return dhName;
	}

	public void setDhName(String dhName) {
		this.dhName = dhName;
	}
	
	@Length(min=0, max=255, message="酒店位置长度不能超过 255 个字符")
	public String getDhLocation() {
		return dhLocation;
	}

	public void setDhLocation(String dhLocation) {
		this.dhLocation = dhLocation;
	}
	
	@Length(min=0, max=255, message="酒店星级长度不能超过 255 个字符")
	public String getDhLevel() {
		return dhLevel;
	}

	public void setDhLevel(String dhLevel) {
		this.dhLevel = dhLevel;
	}
	
	public Double getDhKfs() {
		return dhKfs;
	}

	public void setDhKfs(Double dhKfs) {
		this.dhKfs = dhKfs;
	}
	
	public Double getDhRzl() {
		return dhRzl;
	}

	public void setDhRzl(Double dhRzl) {
		this.dhRzl = dhRzl;
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
	
	@Length(min=0, max=255, message="日期长度不能超过 255 个字符")
	public String getDdId() {
		return ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}
	
	@Length(min=0, max=255, message="旅行社或会展编码长度不能超过 255 个字符")
	public String getDdLxsid() {
		return ddLxsid;
	}

	public void setDdLxsid(String ddLxsid) {
		this.ddLxsid = ddLxsid;
	}
	
	@Length(min=0, max=255, message="旅行社或会展名称长度不能超过 255 个字符")
	public String getDdLxsmc() {
		return ddLxsmc;
	}

	public void setDdLxsmc(String ddLxsmc) {
		this.ddLxsmc = ddLxsmc;
	}
	
	public Long getDdYyrs() {
		return ddYyrs;
	}

	public void setDdYyrs(Long ddYyrs) {
		this.ddYyrs = ddYyrs;
	}
	
}