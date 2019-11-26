/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimSaleTargetYyy;
import com.jeesite.modules.dim.service.DimSaleTargetYyyService;
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
 * 三亚销售目标Controller
 * @author ljw
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimSaleTargetYyy")
public class DimSaleTargetYyyController extends BaseController {

	@Autowired
	private DimSaleTargetYyyService dimSaleTargetYyyService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimSaleTargetYyy get(String id, boolean isNewRecord) {
		return dimSaleTargetYyyService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimSaleTargetYyy:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimSaleTargetYyy dimSaleTargetYyy, Model model) {
		model.addAttribute("dimSaleTargetYyy", dimSaleTargetYyy);
		return "modules/dim/dimSaleTargetYyyList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimSaleTargetYyy:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimSaleTargetYyy> listData(DimSaleTargetYyy dimSaleTargetYyy, HttpServletRequest request, HttpServletResponse response) {
		dimSaleTargetYyy.setPage(new Page<>(request, response));
		Page<DimSaleTargetYyy> page = dimSaleTargetYyyService.findPage(dimSaleTargetYyy);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimSaleTargetYyy:view")
	@RequestMapping(value = "form")
	public String form(DimSaleTargetYyy dimSaleTargetYyy, Model model,String status) {
		model.addAttribute("dimSaleTargetYyy", dimSaleTargetYyy);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimSaleTargetYyyForm";
	}

	/**
	 * 保存三亚销售目标
	 */
	@RequiresPermissions("dim:dimSaleTargetYyy:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimSaleTargetYyy dimSaleTargetYyy) {
		dimSaleTargetYyyService.save(dimSaleTargetYyy);
		return renderResult(Global.TRUE, text("保存三亚销售目标成功！"));
	}
	
	/**
	 * 删除三亚销售目标
	 */
	@RequiresPermissions("dim:dimSaleTargetYyy:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimSaleTargetYyy dimSaleTargetYyy) {
		dimSaleTargetYyyService.delete(dimSaleTargetYyy);
		return renderResult(Global.TRUE, text("删除三亚销售目标成功！"));
	}
	
}