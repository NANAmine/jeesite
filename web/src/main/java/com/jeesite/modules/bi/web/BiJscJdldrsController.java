/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.bi.entity.BiJscJdldrs;
import com.jeesite.modules.bi.service.BiJscJdldrsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 三亚客流Controller
 * @author ljw
 * @version 2019-09-17
 */
@Controller
@RequestMapping(value = "${adminPath}/bi/biJscJdldrs")
public class BiJscJdldrsController extends BaseController {

	@Autowired
	private BiJscJdldrsService biJscJdldrsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BiJscJdldrs get(String id, boolean isNewRecord) {
		return biJscJdldrsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("bi:biJscJdldrs:view")
	@RequestMapping(value = {"list", ""})
	public String list(BiJscJdldrs biJscJdldrs, Model model) {
		model.addAttribute("biJscJdldrs", biJscJdldrs);
		return "modules/bi/biJscJdldrsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("bi:biJscJdldrs:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BiJscJdldrs> listData(BiJscJdldrs biJscJdldrs, HttpServletRequest request, HttpServletResponse response) {
		biJscJdldrs.setPage(new Page<>(request, response));
		Page<BiJscJdldrs> page = biJscJdldrsService.findPage(biJscJdldrs);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("bi:biJscJdldrs:view")
	@RequestMapping(value = "form")
	public String form(BiJscJdldrs biJscJdldrs, Model model,String status) {
		model.addAttribute("biJscJdldrs", biJscJdldrs);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/bi/biJscJdldrsForm";
	}

	/**
	 * 保存三亚客流
	 */
	@RequiresPermissions("bi:biJscJdldrs:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BiJscJdldrs biJscJdldrs) {
		biJscJdldrsService.save(biJscJdldrs);
		return renderResult(Global.TRUE, text("保存三亚客流成功！"));
	}
	
	/**
	 * 删除三亚客流
	 */
	@RequiresPermissions("bi:biJscJdldrs:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BiJscJdldrs biJscJdldrs) {
		biJscJdldrsService.delete(biJscJdldrs);
		return renderResult(Global.TRUE, text("删除三亚客流成功！"));
	}
	
}