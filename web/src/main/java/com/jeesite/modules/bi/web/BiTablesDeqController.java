/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.web;

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
import com.jeesite.modules.bi.entity.BiTablesDeq;
import com.jeesite.modules.bi.service.BiTablesDeqService;

/**
 * 通用字典表二Controller
 * @author ljw
 * @version 2019-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/bi/biTablesDeq")
public class BiTablesDeqController extends BaseController {

	@Autowired
	private BiTablesDeqService biTablesDeqService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BiTablesDeq get(String id, boolean isNewRecord) {
		return biTablesDeqService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("bi:biTablesDeq:view")
	@RequestMapping(value = {"list", ""})
	public String list(BiTablesDeq biTablesDeq, Model model) {
		model.addAttribute("biTablesDeq", biTablesDeq);
		return "modules/bi/biTablesDeqList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("bi:biTablesDeq:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BiTablesDeq> listData(BiTablesDeq biTablesDeq, HttpServletRequest request, HttpServletResponse response) {
		biTablesDeq.setPage(new Page<>(request, response));
		Page<BiTablesDeq> page = biTablesDeqService.findPage(biTablesDeq);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("bi:biTablesDeq:view")
	@RequestMapping(value = "form")
	public String form(BiTablesDeq biTablesDeq, Model model) {
		model.addAttribute("biTablesDeq", biTablesDeq);
		return "modules/bi/biTablesDeqForm";
	}

	/**
	 * 保存通用字典表二
	 */
	@RequiresPermissions("bi:biTablesDeq:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BiTablesDeq biTablesDeq) {
		biTablesDeqService.save(biTablesDeq);
		return renderResult(Global.TRUE, text("保存通用字典表二成功！"));
	}
	
	/**
	 * 删除通用字典表二
	 */
	@RequiresPermissions("bi:biTablesDeq:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BiTablesDeq biTablesDeq) {
		biTablesDeqService.delete(biTablesDeq);
		return renderResult(Global.TRUE, text("删除通用字典表二成功！"));
	}
	
}