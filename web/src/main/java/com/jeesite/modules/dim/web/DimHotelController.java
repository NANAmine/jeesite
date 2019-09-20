/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimHotel;
import com.jeesite.modules.dim.service.DimHotelService;
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
 * 三亚酒店基本信息Controller
 * @author ljw
 * @version 2019-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimHotel")
public class DimHotelController extends BaseController {

	@Autowired
	private DimHotelService dimHotelService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimHotel get(String id, boolean isNewRecord) {
		return dimHotelService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimHotel:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimHotel dimHotel, Model model) {
		model.addAttribute("dimHotel", dimHotel);
		return "modules/dim/dimHotelList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimHotel:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimHotel> listData(DimHotel dimHotel, HttpServletRequest request, HttpServletResponse response) {
		dimHotel.setPage(new Page<>(request, response));
		Page<DimHotel> page = dimHotelService.findPage(dimHotel);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimHotel:view")
	@RequestMapping(value = "form")
	public String form(DimHotel dimHotel, Model model, String status) {
		model.addAttribute("dimHotel", dimHotel);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimHotelForm";
	}

	/**
	 * 保存三亚酒店基本信息
	 */
	@RequiresPermissions("dim:dimHotel:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimHotel dimHotel) {
		dimHotelService.save(dimHotel);
		return renderResult(Global.TRUE, text("保存三亚酒店基本信息成功！"));
	}
	
	/**
	 * 删除三亚酒店基本信息
	 */
	@RequiresPermissions("dim:dimHotel:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimHotel dimHotel) {
		dimHotelService.delete(dimHotel);
		return renderResult(Global.TRUE, text("删除三亚酒店基本信息成功！"));
	}
	
}