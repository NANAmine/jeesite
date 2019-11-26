/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

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
import com.jeesite.modules.test.entity.Yp;
import com.jeesite.modules.test.service.YpService;

/**
 * 药品管理Controller
 * @author ljw
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/test/yp")
public class YpController extends BaseController {

	@Autowired
	private YpService ypService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Yp get(String id, boolean isNewRecord) {
		return ypService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:yp:view")
	@RequestMapping(value = {"list", ""})
	public String list(Yp yp, Model model) {
		model.addAttribute("yp", yp);
		return "modules/test/ypList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:yp:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Yp> listData(Yp yp, HttpServletRequest request, HttpServletResponse response) {
		yp.setPage(new Page<>(request, response));
		Page<Yp> page = ypService.findPage(yp);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:yp:view")
	@RequestMapping(value = "form")
	public String form(Yp yp, Model model) {
		model.addAttribute("yp", yp);
		return "modules/test/ypForm";
	}

	/**
	 * 保存药品
	 */
	@RequiresPermissions("test:yp:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Yp yp) {
		ypService.save(yp);
		return renderResult(Global.TRUE, text("保存药品成功！"));
	}
	
	/**
	 * 停用药品
	 */
	@RequiresPermissions("test:yp:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Yp yp) {
		yp.setStatus(Yp.STATUS_DISABLE);
		ypService.updateStatus(yp);
		return renderResult(Global.TRUE, text("停用药品成功"));
	}
	
	/**
	 * 启用药品
	 */
	@RequiresPermissions("test:yp:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Yp yp) {
		yp.setStatus(Yp.STATUS_NORMAL);
		ypService.updateStatus(yp);
		return renderResult(Global.TRUE, text("启用药品成功"));
	}
	
	/**
	 * 删除药品
	 */
	@RequiresPermissions("test:yp:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Yp yp) {
		ypService.delete(yp);
		return renderResult(Global.TRUE, text("删除药品成功！"));
	}
	
}