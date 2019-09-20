/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.external.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.external.entity.ExternalWholesaleReport;
import com.jeesite.modules.external.service.ExternalWholesaleReportService;
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
 * 对外批发Controller
 * @author ljw
 * @version 2019-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/external/externalWholesaleReport")
public class ExternalWholesaleReportController extends BaseController {

	@Autowired
	private ExternalWholesaleReportService externalWholesaleReportService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ExternalWholesaleReport get(String id, boolean isNewRecord) {
		return externalWholesaleReportService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("external:externalWholesaleReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExternalWholesaleReport externalWholesaleReport, Model model) {
		model.addAttribute("externalWholesaleReport", externalWholesaleReport);
		return "modules/external/externalWholesaleReportList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("external:externalWholesaleReport:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ExternalWholesaleReport> listData(ExternalWholesaleReport externalWholesaleReport, HttpServletRequest request, HttpServletResponse response) {
		externalWholesaleReport.setPage(new Page<>(request, response));
		Page<ExternalWholesaleReport> page = externalWholesaleReportService.findPage(externalWholesaleReport);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("external:externalWholesaleReport:view")
	@RequestMapping(value = "form")
	public String form(ExternalWholesaleReport externalWholesaleReport, Model model, String status) {
		model.addAttribute("externalWholesaleReport", externalWholesaleReport);
		if("1".equals(status)) {
			status = "true";
		}else {
			status = "false";
		}
		String ewr = "false";
		if ("合计".equals(externalWholesaleReport.getEwrCategoryName())){
			ewr = "true";
		}
		model.addAttribute("status",status);
		model.addAttribute("ewr",ewr);
		return "modules/external/externalWholesaleReportForm";
	}

	/**
	 * 保存对外批发
	 */
	@RequiresPermissions("external:externalWholesaleReport:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ExternalWholesaleReport externalWholesaleReport) {
		externalWholesaleReportService.save(externalWholesaleReport);
		return renderResult(Global.TRUE, text("保存对外批发成功！"));
	}
	
	/**
	 * 删除对外批发
	 */
	@RequiresPermissions("external:externalWholesaleReport:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ExternalWholesaleReport externalWholesaleReport) {
		externalWholesaleReportService.delete(externalWholesaleReport);
		return renderResult(Global.TRUE, text("删除对外批发成功！"));
	}
	
}