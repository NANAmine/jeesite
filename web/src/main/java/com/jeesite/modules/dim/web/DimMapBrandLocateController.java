/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimMapBrandLocate;
import com.jeesite.modules.dim.service.DimMapBrandLocateService;
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
 * 店铺号品牌映射Controller
 * @author ljw
 * @version 2019-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimMapBrandLocate")
public class DimMapBrandLocateController extends BaseController {

	@Autowired
	private DimMapBrandLocateService dimMapBrandLocateService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimMapBrandLocate get(String id, boolean isNewRecord) {
		return dimMapBrandLocateService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimMapBrandLocate:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimMapBrandLocate dimMapBrandLocate, Model model) {
		model.addAttribute("dimMapBrandLocate", dimMapBrandLocate);
		return "modules/dim/dimMapBrandLocateList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimMapBrandLocate:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimMapBrandLocate> listData(DimMapBrandLocate dimMapBrandLocate, HttpServletRequest request, HttpServletResponse response) {
		dimMapBrandLocate.setPage(new Page<>(request, response));
		Page<DimMapBrandLocate> page = dimMapBrandLocateService.findPage(dimMapBrandLocate);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimMapBrandLocate:view")
	@RequestMapping(value = "form")
	public String form(DimMapBrandLocate dimMapBrandLocate, Model model, String status) {
		model.addAttribute("dimMapBrandLocate", dimMapBrandLocate);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimMapBrandLocateForm";
	}

	/**
	 * 保存店铺号品牌映射
	 */
	@RequiresPermissions("dim:dimMapBrandLocate:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimMapBrandLocate dimMapBrandLocate) {
		dimMapBrandLocateService.save(dimMapBrandLocate);
		return renderResult(Global.TRUE, text("保存店铺号品牌映射成功！"));
	}
	
	/**
	 * 删除店铺号品牌映射
	 */
	@RequiresPermissions("dim:dimMapBrandLocate:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimMapBrandLocate dimMapBrandLocate) {
		dimMapBrandLocateService.delete(dimMapBrandLocate);
		return renderResult(Global.TRUE, text("删除店铺号品牌映射成功！"));
	}
	
}