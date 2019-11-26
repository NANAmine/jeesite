/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 通用字典表Entity
 * @author ljw
 * @version 2019-09-25
 */
@Table(name="bi_common_tables", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="table_name", attrName="tableName", label="表名称", queryType=QueryType.LIKE),
		@Column(name="common_a", attrName="commonA", label="通用字段"),
		@Column(name="common_b", attrName="commonB", label="通用字段"),
		@Column(name="common_c", attrName="commonC", label="通用字段"),
		@Column(name="common_d", attrName="commonD", label="通用字段"),
		@Column(name="common_e", attrName="commonE", label="通用字段"),
		@Column(name="common_f", attrName="commonF", label="通用字段"),
		@Column(name="common_g", attrName="commonG", label="通用字段"),
		@Column(name="common_h", attrName="commonH", label="通用字段"),
		@Column(name="common_i", attrName="commonI", label="通用字段"),
		@Column(name="common_j", attrName="commonJ", label="通用字段"),
		@Column(name="common_k", attrName="commonK", label="通用字段"),
		@Column(name="common_l", attrName="commonL", label="通用字段"),
		@Column(name="common_m", attrName="commonM", label="通用字段"),
		@Column(name="common_n", attrName="commonN", label="通用字段"),
		@Column(name="common_o", attrName="commonO", label="通用字段"),
		@Column(name="common_p", attrName="commonP", label="通用字段"),
		@Column(name="common_q", attrName="commonQ", label="通用字段"),
		@Column(name="common_r", attrName="commonR", label="通用字段"),
		@Column(name="common_s", attrName="commonS", label="通用字段"),
		@Column(name="common_t", attrName="commonT", label="通用字段"),
		@Column(name="common_u", attrName="commonU", label="通用字段"),
		@Column(name="common_v", attrName="commonV", label="通用字段"),
		@Column(name="common_w", attrName="commonW", label="通用字段"),
		@Column(name="common_x", attrName="commonX", label="通用字段"),
		@Column(name="common_y", attrName="commonY", label="通用字段"),
		@Column(name="common_z", attrName="commonZ", label="通用字段"),
		@Column(name="common_aa", attrName="commonAa", label="通用字段"),
		@Column(name="common_ab", attrName="commonAb", label="通用字段"),
		@Column(name="common_ac", attrName="commonAc", label="通用字段"),
		@Column(name="common_ad", attrName="commonAd", label="通用字段"),
		@Column(name="common_ae", attrName="commonAe", label="通用字段"),
		@Column(name="common_af", attrName="commonAf", label="通用字段"),
		@Column(name="common_ag", attrName="commonAg", label="通用字段"),
		@Column(name="common_ah", attrName="commonAh", label="通用字段"),
		@Column(name="common_ai", attrName="commonAi", label="通用字段"),
		@Column(name="common_aj", attrName="commonAj", label="通用字段"),
		@Column(name="common_ak", attrName="commonAk", label="通用字段"),
		@Column(name="common_al", attrName="commonAl", label="通用字段"),
		@Column(name="common_am", attrName="commonAm", label="通用字段"),
		@Column(name="common_an", attrName="commonAn", label="通用字段"),
		@Column(name="update_by", attrName="updateBy", label="最近更新人"),
		@Column(name="update_date", attrName="updateDate", label="最近更新时间"),
		@Column(name="status", attrName="status", label="状态"),
	}, orderBy="a.update_date DESC"
)
public class BiCommonTables extends DataEntity<BiCommonTables> {
	
	private static final long serialVersionUID = 1L;
	private String tableName;		// 表名称
	private String commonA;		// 通用字段
	private String commonB;		// 通用字段
	private String commonC;		// 通用字段
	private String commonD;		// 通用字段
	private String commonE;		// 通用字段
	private String commonF;		// 通用字段
	private String commonG;		// 通用字段
	private String commonH;		// 通用字段
	private String commonI;		// 通用字段
	private String commonJ;		// 通用字段
	private String commonK;		// 通用字段
	private String commonL;		// 通用字段
	private String commonM;		// 通用字段
	private String commonN;		// 通用字段
	private String commonO;		// 通用字段
	private String commonP;		// 通用字段
	private String commonQ;		// 通用字段
	private String commonR;		// 通用字段
	private String commonS;		// 通用字段
	private String commonT;		// 通用字段
	private String commonU;		// 通用字段
	private String commonV;		// 通用字段
	private String commonW;		// 通用字段
	private String commonX;		// 通用字段
	private String commonY;		// 通用字段
	private String commonZ;		// 通用字段
	private String commonAa;		// 通用字段
	private String commonAb;		// 通用字段
	private String commonAc;		// 通用字段
	private String commonAd;		// 通用字段
	private String commonAe;		// 通用字段
	private String commonAf;		// 通用字段
	private String commonAg;		// 通用字段
	private String commonAh;		// 通用字段
	private String commonAi;		// 通用字段
	private String commonAj;		// 通用字段
	private String commonAk;		// 通用字段
	private String commonAl;		// 通用字段
	private String commonAm;		// 通用字段
	private String commonAn;		// 通用字段
	
	public BiCommonTables() {
		this(null);
	}

	public BiCommonTables(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="表名称长度不能超过 100 个字符")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonA() {
		return commonA;
	}

	public void setCommonA(String commonA) {
		this.commonA = commonA;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonB() {
		return commonB;
	}

	public void setCommonB(String commonB) {
		this.commonB = commonB;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonC() {
		return commonC;
	}

	public void setCommonC(String commonC) {
		this.commonC = commonC;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonD() {
		return commonD;
	}

	public void setCommonD(String commonD) {
		this.commonD = commonD;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonE() {
		return commonE;
	}

	public void setCommonE(String commonE) {
		this.commonE = commonE;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonF() {
		return commonF;
	}

	public void setCommonF(String commonF) {
		this.commonF = commonF;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonG() {
		return commonG;
	}

	public void setCommonG(String commonG) {
		this.commonG = commonG;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonH() {
		return commonH;
	}

	public void setCommonH(String commonH) {
		this.commonH = commonH;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonI() {
		return commonI;
	}

	public void setCommonI(String commonI) {
		this.commonI = commonI;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonJ() {
		return commonJ;
	}

	public void setCommonJ(String commonJ) {
		this.commonJ = commonJ;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonK() {
		return commonK;
	}

	public void setCommonK(String commonK) {
		this.commonK = commonK;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonL() {
		return commonL;
	}

	public void setCommonL(String commonL) {
		this.commonL = commonL;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonM() {
		return commonM;
	}

	public void setCommonM(String commonM) {
		this.commonM = commonM;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonN() {
		return commonN;
	}

	public void setCommonN(String commonN) {
		this.commonN = commonN;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonO() {
		return commonO;
	}

	public void setCommonO(String commonO) {
		this.commonO = commonO;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonP() {
		return commonP;
	}

	public void setCommonP(String commonP) {
		this.commonP = commonP;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonQ() {
		return commonQ;
	}

	public void setCommonQ(String commonQ) {
		this.commonQ = commonQ;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonR() {
		return commonR;
	}

	public void setCommonR(String commonR) {
		this.commonR = commonR;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonS() {
		return commonS;
	}

	public void setCommonS(String commonS) {
		this.commonS = commonS;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonT() {
		return commonT;
	}

	public void setCommonT(String commonT) {
		this.commonT = commonT;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonU() {
		return commonU;
	}

	public void setCommonU(String commonU) {
		this.commonU = commonU;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonV() {
		return commonV;
	}

	public void setCommonV(String commonV) {
		this.commonV = commonV;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonW() {
		return commonW;
	}

	public void setCommonW(String commonW) {
		this.commonW = commonW;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonX() {
		return commonX;
	}

	public void setCommonX(String commonX) {
		this.commonX = commonX;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonY() {
		return commonY;
	}

	public void setCommonY(String commonY) {
		this.commonY = commonY;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonZ() {
		return commonZ;
	}

	public void setCommonZ(String commonZ) {
		this.commonZ = commonZ;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAa() {
		return commonAa;
	}

	public void setCommonAa(String commonAa) {
		this.commonAa = commonAa;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAb() {
		return commonAb;
	}

	public void setCommonAb(String commonAb) {
		this.commonAb = commonAb;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAc() {
		return commonAc;
	}

	public void setCommonAc(String commonAc) {
		this.commonAc = commonAc;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAd() {
		return commonAd;
	}

	public void setCommonAd(String commonAd) {
		this.commonAd = commonAd;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAe() {
		return commonAe;
	}

	public void setCommonAe(String commonAe) {
		this.commonAe = commonAe;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAf() {
		return commonAf;
	}

	public void setCommonAf(String commonAf) {
		this.commonAf = commonAf;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAg() {
		return commonAg;
	}

	public void setCommonAg(String commonAg) {
		this.commonAg = commonAg;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAh() {
		return commonAh;
	}

	public void setCommonAh(String commonAh) {
		this.commonAh = commonAh;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAi() {
		return commonAi;
	}

	public void setCommonAi(String commonAi) {
		this.commonAi = commonAi;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAj() {
		return commonAj;
	}

	public void setCommonAj(String commonAj) {
		this.commonAj = commonAj;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAk() {
		return commonAk;
	}

	public void setCommonAk(String commonAk) {
		this.commonAk = commonAk;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAl() {
		return commonAl;
	}

	public void setCommonAl(String commonAl) {
		this.commonAl = commonAl;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAm() {
		return commonAm;
	}

	public void setCommonAm(String commonAm) {
		this.commonAm = commonAm;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAn() {
		return commonAn;
	}

	public void setCommonAn(String commonAn) {
		this.commonAn = commonAn;
	}
	
}