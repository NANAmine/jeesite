/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.eva.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.eva.entity.EvaReport;
import com.jeesite.modules.eva.service.EvaReportService;
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
 * EVAController
 * @author ljw
 * @version 2019-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/eva/evaReport")
public class EvaReportController extends BaseController {

	@Autowired
	private EvaReportService evaReportService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EvaReport get(String id, boolean isNewRecord) {
		return evaReportService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("eva:evaReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(EvaReport evaReport, Model model) {
		model.addAttribute("evaReport", evaReport);
		return "modules/eva/evaReportList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("eva:evaReport:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EvaReport> listData(EvaReport evaReport, HttpServletRequest request, HttpServletResponse response) {
		evaReport.setPage(new Page<>(request, response));
		Page<EvaReport> page = evaReportService.findPage(evaReport);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("eva:evaReport:view")
	@RequestMapping(value = "form")
	public String form(EvaReport evaReport, Model model) {
		model.addAttribute("evaReport", evaReport);
		return "modules/eva/evaReportForm";
	}

	/**
	 * 保存EVA
	 */
	@RequiresPermissions("eva:evaReport:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EvaReport evaReport) {
		evaReportService.save(evaReport);
		return renderResult(Global.TRUE, text("保存EVA成功！"));
	}
	
	/**
	 * 删除EVA
	 */
	@RequiresPermissions("eva:evaReport:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EvaReport evaReport) {
		evaReportService.delete(evaReport);
		return renderResult(Global.TRUE, text("删除EVA成功！"));
	}
	
}