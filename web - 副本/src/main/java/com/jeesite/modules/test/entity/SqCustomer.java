/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 顾客表Entity
 * @author ljw
 * @version 2019-04-29
 */
@Table(name="sq_customer", alias="a", columns={
		@Column(name="id", attrName="id", label="序号", isPK=true),
		@Column(name="phone", attrName="phone", label="电话"),
		@Column(name="card", attrName="card", label="身份证号"),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="age", attrName="age", label="年龄"),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="address", attrName="address", label="住址"),
		@Column(name="status", attrName="status", label="状态", isUpdate=false),
		@Column(name="mark", attrName="mark", label="备注"),
	}, orderBy="a.id DESC"
)
public class SqCustomer extends DataEntity<SqCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String phone;		// 电话
	private String card;		// 身份证号
	private String name;		// 姓名
	private String age;		// 年龄
	private String sex;		// 性别
	private String address;		// 住址
	private String mark;		// 备注
	
	public SqCustomer() {
		this(null);
	}

	public SqCustomer(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="电话长度不能超过 50 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="身份证号长度不能超过 50 个字符")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=0, max=50, message="姓名长度不能超过 50 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="年龄长度不能超过 50 个字符")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=50, message="性别长度不能超过 50 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=50, message="住址长度不能超过 50 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="备注长度不能超过 100 个字符")
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
}