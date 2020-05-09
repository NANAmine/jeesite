/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.web;

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
import com.jeesite.modules.db.entity.TbDbUpfile;
import com.jeesite.modules.db.service.TbDbUpfileService;

/**
 * tb_db_upfileController
 * @author liangjiawei
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/db/tbDbUpfile")
public class TbDbUpfileController extends BaseController {

	@Autowired
	private TbDbUpfileService tbDbUpfileService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TbDbUpfile get(String id, boolean isNewRecord) {
		return tbDbUpfileService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("db:tbDbUpfile:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbDbUpfile tbDbUpfile, Model model) {
		model.addAttribute("tbDbUpfile", tbDbUpfile);
		return "modules/db/tbDbUpfileList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("db:tbDbUpfile:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TbDbUpfile> listData(TbDbUpfile tbDbUpfile, HttpServletRequest request, HttpServletResponse response) {
		tbDbUpfile.setPage(new Page<>(request, response));
		Page<TbDbUpfile> page = tbDbUpfileService.findPage(tbDbUpfile);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("db:tbDbUpfile:view")
	@RequestMapping(value = "form")
	public String form(TbDbUpfile tbDbUpfile, Model model) {
		model.addAttribute("tbDbUpfile", tbDbUpfile);
		return "modules/db/tbDbUpfileForm";
	}

	/**
	 * 保存tb_db_upfile
	 */
	@RequiresPermissions("db:tbDbUpfile:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TbDbUpfile tbDbUpfile) {
		tbDbUpfileService.save(tbDbUpfile);
		return renderResult(Global.TRUE, text("保存tb_db_upfile成功！"));
	}
	
	/**
	 * 删除tb_db_upfile
	 */
	@RequiresPermissions("db:tbDbUpfile:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TbDbUpfile tbDbUpfile) {
		tbDbUpfileService.delete(tbDbUpfile);
		return renderResult(Global.TRUE, text("删除tb_db_upfile成功！"));
	}
	
}