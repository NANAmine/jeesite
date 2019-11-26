/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.SqCustomer;
import com.jeesite.modules.test.service.SqCustomerService;

/**
 * 顾客表Controller
 * @author ljw
 * @version 2019-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/test/sqCustomer")
public class SqCustomerController extends BaseController {

	@Autowired
	private SqCustomerService sqCustomerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SqCustomer get(String id, boolean isNewRecord) {
		return sqCustomerService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:sqCustomer:view")
	@RequestMapping(value = {"list", ""})
	public String list(SqCustomer sqCustomer, Model model) {
		model.addAttribute("sqCustomer", sqCustomer);
		return "modules/test/sqCustomerList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:sqCustomer:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SqCustomer> listData(SqCustomer sqCustomer, HttpServletRequest request, HttpServletResponse response) {
		sqCustomer.setPage(new Page<>(request, response));
		Page<SqCustomer> page = sqCustomerService.findPage(sqCustomer);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:sqCustomer:view")
	@RequestMapping(value = "form")
	public String form(SqCustomer sqCustomer, Model model) {
		model.addAttribute("sqCustomer", sqCustomer);
		return "modules/test/sqCustomerForm";
	}

	/**
	 * 保存顾客表
	 */
	@RequiresPermissions("test:sqCustomer:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SqCustomer sqCustomer) {
		sqCustomerService.save(sqCustomer);
		return renderResult(Global.TRUE, text("保存顾客表成功！"));
	}
	
	/**
	 * 停用顾客表
	 */
	@RequiresPermissions("test:sqCustomer:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SqCustomer sqCustomer) {
		sqCustomer.setStatus(SqCustomer.STATUS_DISABLE);
		sqCustomerService.updateStatus(sqCustomer);
		return renderResult(Global.TRUE, text("停用顾客表成功"));
	}
	
	/**
	 * 启用顾客表
	 */
	@RequiresPermissions("test:sqCustomer:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SqCustomer sqCustomer) {
		sqCustomer.setStatus(SqCustomer.STATUS_NORMAL);
		sqCustomerService.updateStatus(sqCustomer);
		return renderResult(Global.TRUE, text("启用顾客表成功"));
	}
	
	/**
	 * 删除顾客表
	 */
	@RequiresPermissions("test:sqCustomer:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SqCustomer sqCustomer) {
		sqCustomerService.delete(sqCustomer);
		return renderResult(Global.TRUE, text("删除顾客表成功！"));
	}
	
}