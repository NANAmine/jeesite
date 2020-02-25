/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bi.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.bi.entity.BiCommonTables;
import com.jeesite.modules.bi.service.BiCommonTablesService;
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
 * 通用字典表Controller
 * @author ljw
 * @version 2019-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/bi/biCommonTables")
public class BiCommonTablesController extends BaseController {

	@Autowired
	private BiCommonTablesService biCommonTablesService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BiCommonTables get(String id, boolean isNewRecord) {
		return biCommonTablesService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@SuppressWarnings("AlibabaUndefineMagicConstant")
    @RequiresPermissions("bi:biCommonTables:view")
	@RequestMapping(value = {"list", ""})
	public String list(BiCommonTables biCommonTables, Model model, String id) {
		if("1".equals(id)){
			biCommonTables.setTableName("bi_jsc_jdldrs");
		}else if("2".equals(id)){
			biCommonTables.setTableName("dim_hk_passenger_flow");
			biCommonTables.setCommonF("6915");
		}else if("3".equals(id)){
			biCommonTables.setTableName("eva_repor");
		}else if("4".equals(id)){
			biCommonTables.setTableName("external_wholesale_report");
		}else if("5".equals(id)){
			biCommonTables.setTableName("dim_hotel");
		}else if("6".equals(id)){
			biCommonTables.setTableName("dim_map_brand_locate");
		}else if("7".equals(id)){
			biCommonTables.setTableName("dim_sale_target_yyy");
		}else if("8".equals(id)){
			biCommonTables.setTableName("dim_lxs_yyrs");
		}else if("9".equals(id)){
			biCommonTables.setTableName("dim_instructions_detail");
		}else if("10".equals(id)){
			biCommonTables.setTableName("dim_texchange_rate");
		}else if("11".equals(id)){
            biCommonTables.setTableName("dim_gys_ppdz");
        }else if("12".equals(id)){
            biCommonTables.setTableName("bi_ds_pvuv");
        }else if("13".equals(id)){
            biCommonTables.setTableName("dim_area_gz_map");
        }else if("14".equals(id)){
            biCommonTables.setTableName("dim_unit_channel_gcp");
        }else if("15".equals(id)){
            biCommonTables.setTableName("dim_xhgysbg_md");
        }else if("16".equals(id)){
            biCommonTables.setTableName("dim_pp_sell_report_exchangrate");
        }else if("17".equals(id)){
            biCommonTables.setTableName("bi_sy_ldtype_ldrs");
        }
		model.addAttribute("biCommonTables", biCommonTables);
		return "modules/bi/biCommonTablesList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("bi:biCommonTables:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BiCommonTables> listData(BiCommonTables biCommonTables, HttpServletRequest request, HttpServletResponse response) {
		biCommonTables.setPage(new Page<>(request, response));
		Page<BiCommonTables> page = biCommonTablesService.findPage(biCommonTables);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@SuppressWarnings("AlibabaUndefineMagicConstant")
    @RequiresPermissions("bi:biCommonTables:view")
	@RequestMapping(value = "form")
	public String form(BiCommonTables biCommonTables, Model model, String status) {
		if("1".equals(status)){
			status = "true";
		}
		/*String ewr = "false";
		if ("合计".equals(biCommonTables.getCommonD())){
			ewr = "true";
		}
		model.addAttribute("ewr",ewr);*/
		if("bi_ds_pvuv".equals(biCommonTables.getTableName())){
		    biCommonTables.setCommonB("6874");
        }else if("bi_sy_ldtype_ldrs".equals(biCommonTables.getTableName()) && biCommonTables.getCommonB()==null){
            biCommonTables.setCommonB("6868");
        }
		model.addAttribute("status",status);
		model.addAttribute("biCommonTables", biCommonTables);
		return "modules/bi/biCommonTablesForm";
	}

	/**
	 * 保存通用字典表
	 */
	@RequiresPermissions("bi:biCommonTables:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BiCommonTables biCommonTables) {
	    if("bi_sy_ldtype_ldrs".equals(biCommonTables.getTableName())){
	        String commonC=biCommonTables.getCommonC();
            String[] words2 = commonC.split("\\,");
            biCommonTables.setCommonC(words2[words2.length-1]);
        }else {
            String commonC=biCommonTables.getCommonC();
            String[] words2 = commonC.split("\\,");
            biCommonTables.setCommonC(words2[0]);
        }
		biCommonTablesService.save(biCommonTables);
		return renderResult(Global.TRUE, text("保存通用字典表成功！"));
	}
	
	/**
	 * 删除通用字典表
	 */
	@RequiresPermissions("bi:biCommonTables:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BiCommonTables biCommonTables) {
		biCommonTablesService.delete(biCommonTables);
		return renderResult(Global.TRUE, text("删除通用字典表成功！"));
	}
	
}