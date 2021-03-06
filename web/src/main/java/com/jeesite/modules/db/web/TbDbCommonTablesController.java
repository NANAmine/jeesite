/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.db.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.db.entity.TbDbCommonTables;
import com.jeesite.modules.db.service.TbDbCommonTablesService;
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
 * tb_db_common_tablesController
 * @author liangjiawei
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/db/tbDbCommonTables")
public class TbDbCommonTablesController extends BaseController {

    public static final String STRING = "1";
    public static final String STRING1 = "2";
    public static final String STRING2 = "3";
    @Autowired
	private TbDbCommonTablesService tbDbCommonTablesService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TbDbCommonTables get(String id, boolean isNewRecord) {
		return tbDbCommonTablesService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("db:tbDbCommonTables:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbDbCommonTables tbDbCommonTables, Model model, String id) {
        if(STRING.equals(id)){
            tbDbCommonTables.setTeamName("fashion");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesListFashion";
        }else if(STRING1.equals(id)){
            tbDbCommonTables.setTeamName("watch");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesListWatch";
        }else if(STRING2.equals(id)){
            tbDbCommonTables.setTeamName("jew");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesListJew";
        }
		model.addAttribute("tbDbCommonTables", tbDbCommonTables);
		return "modules/db/tbDbCommonTablesList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("db:tbDbCommonTables:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TbDbCommonTables> listData(TbDbCommonTables tbDbCommonTables, HttpServletRequest request, HttpServletResponse response) {
		tbDbCommonTables.setPage(new Page<>(request, response));
		Page<TbDbCommonTables> page = tbDbCommonTablesService.findPage(tbDbCommonTables);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("db:tbDbCommonTables:view")
	@RequestMapping(value = "form")
	public String form(TbDbCommonTables tbDbCommonTables, Model model, String id) {
        if(STRING.equals(id)){
            tbDbCommonTables.setTeamName("fashion");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesFormFashion";
        }else if(STRING1.equals(id)){
            tbDbCommonTables.setTeamName("watch");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesFormWatch";
        }else if(STRING2.equals(id)){
            tbDbCommonTables.setTeamName("jew");
            model.addAttribute("tbDbCommonTables", tbDbCommonTables);
            return "modules/db/tbDbCommonTablesFormJew";
        }
		model.addAttribute("tbDbCommonTables", tbDbCommonTables);
		return "modules/db/tbDbCommonTablesForm";
	}

	/**
	 * 保存tb_db_common_tables
	 */
	@RequiresPermissions("db:tbDbCommonTables:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TbDbCommonTables tbDbCommonTables) {
		tbDbCommonTablesService.save(tbDbCommonTables);
		return renderResult(Global.TRUE, text("保存tb_db_common_tables成功！"));
	}
	
	/**
	 * 删除tb_db_common_tables
	 */
	@RequiresPermissions("db:tbDbCommonTables:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TbDbCommonTables tbDbCommonTables) {
		tbDbCommonTablesService.delete(tbDbCommonTables);
		return renderResult(Global.TRUE, text("删除tb_db_common_tables成功！"));
	}
	
}