/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimTexchangeRate;
import com.jeesite.modules.dim.service.DimTexchangeRateService;
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
 * 汇率数据Controller
 * @author ljw
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimTexchangeRate")
public class DimTexchangeRateController extends BaseController {

	@Autowired
	private DimTexchangeRateService dimTexchangeRateService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimTexchangeRate get(String shijian, boolean isNewRecord) {
		return dimTexchangeRateService.get(shijian, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimTexchangeRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimTexchangeRate dimTexchangeRate, Model model) {
		model.addAttribute("dimTexchangeRate", dimTexchangeRate);
		return "modules/dim/dimTexchangeRateList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimTexchangeRate:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimTexchangeRate> listData(DimTexchangeRate dimTexchangeRate, HttpServletRequest request, HttpServletResponse response) {
		dimTexchangeRate.setPage(new Page<>(request, response));
		Page<DimTexchangeRate> page = dimTexchangeRateService.findPage(dimTexchangeRate);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimTexchangeRate:view")
	@RequestMapping(value = "form")
	public String form(DimTexchangeRate dimTexchangeRate, Model model,String status) {
		model.addAttribute("dimTexchangeRate", dimTexchangeRate);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimTexchangeRateForm";
	}

	/**
	 * 保存汇率数据
	 */
	@RequiresPermissions("dim:dimTexchangeRate:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimTexchangeRate dimTexchangeRate) {
		dimTexchangeRateService.save(dimTexchangeRate);
		return renderResult(Global.TRUE, text("保存汇率数据成功！"));
	}
	
	/**
	 * 删除汇率数据
	 */
	@RequiresPermissions("dim:dimTexchangeRate:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimTexchangeRate dimTexchangeRate) {
		dimTexchangeRateService.delete(dimTexchangeRate);
		return renderResult(Global.TRUE, text("删除汇率数据成功！"));
	}
	
}