/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.cdf.entity.CdfParentData;
import com.jeesite.modules.cdf.service.CdfParentDataService;
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
 * 新零售Controller
 * @author liangjiawei
 * @version 2020-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/cdf/cdfParentData")
public class CdfParentDataController extends BaseController {

	@Autowired
	private CdfParentDataService cdfParentDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CdfParentData get(String id, boolean isNewRecord) {
		return cdfParentDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cdf:cdfParentData:view")
	@RequestMapping(value = {"list", ""})
	public String list(CdfParentData cdfParentData, Model model) {
		model.addAttribute("cdfParentData", cdfParentData);
		return "modules/cdf/cdfParentDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cdf:cdfParentData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CdfParentData> listData(CdfParentData cdfParentData, HttpServletRequest request, HttpServletResponse response) {
		cdfParentData.setPage(new Page<>(request, response));
        cdfParentDataService.addDataScopeFilter(cdfParentData);
        Page<CdfParentData> page = cdfParentDataService.findPage(cdfParentData);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cdf:cdfParentData:view")
	@RequestMapping(value = "form")
	public String form(CdfParentData cdfParentData, Model model, String status) {
        if("1".equals(status)){
            status = "true";
        }
        model.addAttribute("status",status);
		model.addAttribute("cdfParentData", cdfParentData);
		return "modules/cdf/cdfParentDataForm";
	}

	/**
	 * 保存新零售
	 */
	@RequiresPermissions("cdf:cdfParentData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CdfParentData cdfParentData) {
		cdfParentDataService.save(cdfParentData);
		return renderResult(Global.TRUE, text("保存新零售成功！"));
	}
	
	/**
	 * 删除新零售
	 */
	@RequiresPermissions("cdf:cdfParentData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CdfParentData cdfParentData) {
		cdfParentDataService.delete(cdfParentData);
		return renderResult(Global.TRUE, text("删除新零售成功！"));
	}

}