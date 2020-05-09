/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * tb_db_common_tablesEntity
 * @author liangjiawei
 * @version 2020-05-09
 */
@Table(name="tb_db_common_tables", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="team_name", attrName="teamName", label="团队名称", queryType=QueryType.LIKE),
		@Column(name="common_a", attrName="commonA", label="通用字段"),
		@Column(name="common_b", attrName="commonB", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_c", attrName="commonC", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_d", attrName="commonD", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_e", attrName="commonE", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_f", attrName="commonF", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_g", attrName="commonG", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_h", attrName="commonH", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_i", attrName="commonI", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_j", attrName="commonJ", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_k", attrName="commonK", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_l", attrName="commonL", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_m", attrName="commonM", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_n", attrName="commonN", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_o", attrName="commonO", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_p", attrName="commonP", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_q", attrName="commonQ", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_r", attrName="commonR", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_s", attrName="commonS", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_t", attrName="commonT", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_u", attrName="commonU", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_v", attrName="commonV", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_w", attrName="commonW", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_x", attrName="commonX", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_y", attrName="commonY", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_z", attrName="commonZ", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_aa", attrName="commonAa", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ab", attrName="commonAb", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ac", attrName="commonAc", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ad", attrName="commonAd", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ae", attrName="commonAe", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_af", attrName="commonAf", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ag", attrName="commonAg", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ah", attrName="commonAh", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ai", attrName="commonAi", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_aj", attrName="commonAj", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ak", attrName="commonAk", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_al", attrName="commonAl", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_am", attrName="commonAm", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_an", attrName="commonAn", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ao", attrName="commonAo", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ap", attrName="commonAp", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_aq", attrName="commonAq", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ar", attrName="commonAr", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_as", attrName="commonAs", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_at", attrName="commonAt", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_au", attrName="commonAu", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_av", attrName="commonAv", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_aw", attrName="commonAw", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ax", attrName="commonAx", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ay", attrName="commonAy", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_az", attrName="commonAz", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_ba", attrName="commonBa", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bb", attrName="commonBb", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bc", attrName="commonBc", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bd", attrName="commonBd", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_be", attrName="commonBe", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bf", attrName="commonBf", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bg", attrName="commonBg", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="common_bh", attrName="commonBh", label="通用字段", queryType=QueryType.LIKE),
		@Column(name="update_by", attrName="updateBy", label="最近更新人", queryType=QueryType.LIKE),
		@Column(name="update_date", attrName="updateDate", label="最近更新时间"),
		@Column(name="status", attrName="status", label="状态，1失效0有效", isUpdate=false),
	}, orderBy="a.update_date DESC"
)
public class TbDbCommonTables extends DataEntity<TbDbCommonTables> {
	
	private static final long serialVersionUID = 1L;
	private String teamName;		// 团队名称
	private Date commonA;		// 通用字段
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
	private String commonAo;		// 通用字段
	private String commonAp;		// 通用字段
	private String commonAq;		// 通用字段
	private String commonAr;		// 通用字段
	private String commonAs;		// 通用字段
	private String commonAt;		// 通用字段
	private String commonAu;		// 通用字段
	private String commonAv;		// 通用字段
	private String commonAw;		// 通用字段
	private String commonAx;		// 通用字段
	private String commonAy;		// 通用字段
	private String commonAz;		// 通用字段
	private String commonBa;		// 通用字段
	private String commonBb;		// 通用字段
	private String commonBc;		// 通用字段
	private String commonBd;		// 通用字段
	private String commonBe;		// 通用字段
	private String commonBf;		// 通用字段
	private String commonBg;		// 通用字段
	private String commonBh;		// 通用字段
	
	public TbDbCommonTables() {
		this(null);
	}

	public TbDbCommonTables(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="团队名称长度不能超过 100 个字符")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommonA() {
		return commonA;
	}

	public void setCommonA(Date commonA) {
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
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAo() {
		return commonAo;
	}

	public void setCommonAo(String commonAo) {
		this.commonAo = commonAo;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAp() {
		return commonAp;
	}

	public void setCommonAp(String commonAp) {
		this.commonAp = commonAp;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAq() {
		return commonAq;
	}

	public void setCommonAq(String commonAq) {
		this.commonAq = commonAq;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAr() {
		return commonAr;
	}

	public void setCommonAr(String commonAr) {
		this.commonAr = commonAr;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAs() {
		return commonAs;
	}

	public void setCommonAs(String commonAs) {
		this.commonAs = commonAs;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAt() {
		return commonAt;
	}

	public void setCommonAt(String commonAt) {
		this.commonAt = commonAt;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAu() {
		return commonAu;
	}

	public void setCommonAu(String commonAu) {
		this.commonAu = commonAu;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAv() {
		return commonAv;
	}

	public void setCommonAv(String commonAv) {
		this.commonAv = commonAv;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAw() {
		return commonAw;
	}

	public void setCommonAw(String commonAw) {
		this.commonAw = commonAw;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAx() {
		return commonAx;
	}

	public void setCommonAx(String commonAx) {
		this.commonAx = commonAx;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAy() {
		return commonAy;
	}

	public void setCommonAy(String commonAy) {
		this.commonAy = commonAy;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonAz() {
		return commonAz;
	}

	public void setCommonAz(String commonAz) {
		this.commonAz = commonAz;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBa() {
		return commonBa;
	}

	public void setCommonBa(String commonBa) {
		this.commonBa = commonBa;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBb() {
		return commonBb;
	}

	public void setCommonBb(String commonBb) {
		this.commonBb = commonBb;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBc() {
		return commonBc;
	}

	public void setCommonBc(String commonBc) {
		this.commonBc = commonBc;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBd() {
		return commonBd;
	}

	public void setCommonBd(String commonBd) {
		this.commonBd = commonBd;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBe() {
		return commonBe;
	}

	public void setCommonBe(String commonBe) {
		this.commonBe = commonBe;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBf() {
		return commonBf;
	}

	public void setCommonBf(String commonBf) {
		this.commonBf = commonBf;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBg() {
		return commonBg;
	}

	public void setCommonBg(String commonBg) {
		this.commonBg = commonBg;
	}
	
	@Length(min=0, max=255, message="通用字段长度不能超过 255 个字符")
	public String getCommonBh() {
		return commonBh;
	}

	public void setCommonBh(String commonBh) {
		this.commonBh = commonBh;
	}
	
	public Date getCommonA_gte() {
		return sqlMap.getWhere().getValue("common_a", QueryType.GTE);
	}

	public void setCommonA_gte(Date commonA) {
		sqlMap.getWhere().and("common_a", QueryType.GTE, commonA);
	}
	
	public Date getCommonA_lte() {
		return sqlMap.getWhere().getValue("common_a", QueryType.LTE);
	}

	public void setCommonA_lte(Date commonA) {
		sqlMap.getWhere().and("common_a", QueryType.LTE, commonA);
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