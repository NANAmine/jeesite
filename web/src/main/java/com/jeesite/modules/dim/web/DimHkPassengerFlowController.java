/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimHkPassengerFlow;
import com.jeesite.modules.dim.service.DimHkPassengerFlowService;
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
 * 香港客流Controller
 * @author ljw
 * @version 2019-09-17
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimHkPassengerFlow")
public class DimHkPassengerFlowController extends BaseController {

	@Autowired
	private DimHkPassengerFlowService dimHkPassengerFlowService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimHkPassengerFlow get(String id, boolean isNewRecord) {
		return dimHkPassengerFlowService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimHkPassengerFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimHkPassengerFlow dimHkPassengerFlow, Model model) {
		model.addAttribute("dimHkPassengerFlow", dimHkPassengerFlow);
		return "modules/dim/dimHkPassengerFlowList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimHkPassengerFlow:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimHkPassengerFlow> listData(DimHkPassengerFlow dimHkPassengerFlow, HttpServletRequest request, HttpServletResponse response) {
		dimHkPassengerFlow.setPage(new Page<>(request, response));
		Page<DimHkPassengerFlow> page = dimHkPassengerFlowService.findPage(dimHkPassengerFlow);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimHkPassengerFlow:view")
	@RequestMapping(value = "form")
	public String form(DimHkPassengerFlow dimHkPassengerFlow, Model model,String status) {
		model.addAttribute("dimHkPassengerFlow", dimHkPassengerFlow);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimHkPassengerFlowForm";
	}

	/**
	 * 保存香港客流
	 */
	@RequiresPermissions("dim:dimHkPassengerFlow:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimHkPassengerFlow dimHkPassengerFlow) {
		dimHkPassengerFlowService.save(dimHkPassengerFlow);
		return renderResult(Global.TRUE, text("保存香港客流成功！"));
	}
	
	/**
	 * 删除香港客流
	 */
	@RequiresPermissions("dim:dimHkPassengerFlow:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimHkPassengerFlow dimHkPassengerFlow) {
		dimHkPassengerFlowService.delete(dimHkPassengerFlow);
		return renderResult(Global.TRUE, text("删除香港客流成功！"));
	}
	
}