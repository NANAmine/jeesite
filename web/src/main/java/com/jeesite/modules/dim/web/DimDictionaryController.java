/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimDictionary;
import com.jeesite.modules.dim.service.DimDictionaryService;
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
import java.util.List;

/**
 * 通用字典表Controller
 * @author ljw
 * @version 2019-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/dim/dimDictionary")
public class DimDictionaryController extends BaseController {

	@Autowired
	private DimDictionaryService dimDictionaryService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DimDictionary get(String ddicId, boolean isNewRecord) {
		return dimDictionaryService.get(ddicId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dim:dimDictionary:view")
	@RequestMapping(value = {"list", ""})
	public String list(DimDictionary dimDictionary, Model model,String id) {
		if("1".equals(id)){
			dimDictionary.setDdicCode("GDBBMD");
		}else if("2".equals(id)){
			dimDictionary.setDdicCode("JPPPCODE");
		}else if("3".equals(id)){
			dimDictionary.setDdicCode("ZDMD");
		}else if("4".equals(id)){
			dimDictionary.setDdicCode("GCYJZDDP");
		}else if("5".equals(id)){
			dimDictionary.setDdicCode("GCYJZDMD");
		}else if("6".equals(id)){
			dimDictionary.setDdicCode("SPBHPP");
		}else if("7".equals(id)){
			dimDictionary.setDdicCode("MDZLBFL");
		}else if("8".equals(id)){
			dimDictionary.setDdicCode("JSCQD");
		}else if("9".equals(id)){
			dimDictionary.setDdicCode("ZLBQD");
		}else if("10".equals(id)){
			dimDictionary.setDdicCode("ZLBZCGX");
		}else if("11".equals(id)){
			dimDictionary.setDdicCode("XHPPHL");
		}else if("12".equals(id)){
			dimDictionary.setDdicCode("XHMD");
		}else if("13".equals(id)){
			dimDictionary.setDdicCode("HOLIDAY");
		}else if("14".equals(id)){
			dimDictionary.setDdicCode("QXKZ");
		}else if("15".equals(id)){
			dimDictionary.setDdicCode("GZMJ");
		}
		model.addAttribute("dimDictionary", dimDictionary);
		return "modules/dim/dimDictionaryList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dim:dimDictionary:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DimDictionary> listData(DimDictionary dimDictionary, HttpServletRequest request, HttpServletResponse response) {
		dimDictionary.setPage(new Page<>(request, response));
		Page<DimDictionary> page = dimDictionaryService.findPage(dimDictionary);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dim:dimDictionary:view")
	@RequestMapping(value = "form")
	public String form(DimDictionary dimDictionary, Model model,String status) {
		model.addAttribute("dimDictionary", dimDictionary);
		if("1".equals(status)){
			status = "true";
		}
		model.addAttribute("status",status);
		return "modules/dim/dimDictionaryForm";
	}

	/**
	 * 保存通用字典表
	 */
	@RequiresPermissions("dim:dimDictionary:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DimDictionary dimDictionary) {
		String ddiccode = quchong(dimDictionary.getDdicCode(),dimDictionary);
		if("false".equals(ddiccode)){
			return renderResult(Global.TRUE, text("数据已经存在，不可重复添加！"));
		}else{
			return renderResult(Global.TRUE, text("保存通用字典表成功！"));
		}
	}
	
	/**
	 * 停用通用字典表
	 */
	@RequiresPermissions("dim:dimDictionary:del")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(DimDictionary dimDictionary) {
		dimDictionary.setStatus(DimDictionary.STATUS_DISABLE);
		dimDictionaryService.updateStatus(dimDictionary);
		return renderResult(Global.TRUE, text("停用通用字典表成功"));
	}
	
	/**
	 * 启用通用字典表
	 */
	@RequiresPermissions("dim:dimDictionary:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(DimDictionary dimDictionary) {
		dimDictionary.setStatus(DimDictionary.STATUS_NORMAL);
		dimDictionaryService.updateStatus(dimDictionary);
		return renderResult(Global.TRUE, text("启用通用字典表成功"));
	}
	
	/**
	 * 删除通用字典表
	 */
	@RequiresPermissions("dim:dimDictionary:del")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DimDictionary dimDictionary) {
		dimDictionaryService.delete(dimDictionary);
		dimDictionaryService.upDelete(dimDictionary);
		return renderResult(Global.TRUE, text("删除通用字典表成功！"));
	}

	/**
	 *去重方法
	 * */
	public String quchong(String ddiccode,DimDictionary dimDictionary){
		if ("HOLIDAY".equals(ddiccode)){                               //节假日
			dimDictionaryService.save(dimDictionary);
			return "true";
		}else if ("QXKZ".equals(ddiccode)){                            //权限控制
			if(dimDictionary.getIsNewRecord()){
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicName(dimDictionary.getDdicName());
				dim.setDdicValue(dimDictionary.getDdicValue());
				dim.setDdicExpand1(dimDictionary.getDdicExpand1());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!(dimDictionary.getDdicName().equals(list.get(i).getDdicName()) &&
						  dimDictionary.getDdicValue().equals(list.get(i).getDdicValue()) &&
							dimDictionary.getDdicExpand1().equals(list.get(i).getDdicExpand1())) ||
						  "0".equals(list.get(i).getDdicStatus())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}else{
					dimDictionaryService.save(dimDictionary);
					return "true";
				}
			}else{
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicName(dimDictionary.getDdicName());
				dim.setDdicValue(dimDictionary.getDdicValue());
				dim.setDdicExpand1(dimDictionary.getDdicExpand1());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!(dimDictionary.getDdicName().equals(list.get(i).getDdicName()) &&
							dimDictionary.getDdicValue().equals(list.get(i).getDdicValue()) &&
							dimDictionary.getDdicExpand1().equals(list.get(i).getDdicExpand1()))||
							"0".equals(list.get(i).getDdicStatus()) ||
							dimDictionary.getDdicId().equals(list.get(i).getDdicId())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}
				dimDictionaryService.save(dimDictionary);
				return "true";
			}
		}else if("XHPPHL".equals(ddiccode) || "GZMJ".equals(ddiccode)){    //香化品牌汇率、柜组面积
			if(dimDictionary.getIsNewRecord()){
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicName(dimDictionary.getDdicName());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!dimDictionary.getDdicName().equals(list.get(i).getDdicName()) || "0".equals(list.get(i).getDdicStatus())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}else{
					dimDictionaryService.save(dimDictionary);
					return "true";
				}
			}else{
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicName(dimDictionary.getDdicName());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!dimDictionary.getDdicName().equals(list.get(i).getDdicName()) ||
							"0".equals(list.get(i).getDdicStatus()) ||
							dimDictionary.getDdicId().equals(list.get(i).getDdicId())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}
				dimDictionaryService.save(dimDictionary);
				return "true";
			}
		}else {                                                                 //其它映射
			if(dimDictionary.getIsNewRecord()){
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicValue(dimDictionary.getDdicValue());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!dimDictionary.getDdicValue().equals(list.get(i).getDdicValue()) || "0".equals(list.get(i).getDdicStatus())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}else{
					dimDictionaryService.save(dimDictionary);
					return "true";
				}
			}else{
				DimDictionary dim = new DimDictionary();
				dim.setPage(new Page<>(1, 1));
				dim.setDdicCode(dimDictionary.getDdicCode());
				dim.setDdicValue(dimDictionary.getDdicValue());
				Page<DimDictionary> page = dimDictionaryService.findPage(dim);
				List<DimDictionary> list= page.getList();
				for (int i = 0;i < list.size();i ++){
					if (!dimDictionary.getDdicValue().equals(list.get(i).getDdicValue()) ||
							"0".equals(list.get(i).getDdicStatus()) ||
							dimDictionary.getDdicId().equals(list.get(i).getDdicId())){
						list.remove(i);
					}
				}
				if (list.size() != 0){
					for (int i = 0;i < list.size();i ++){
						if ("1".equals(list.get(i).getDdicStatus())){
							return "false";
						}
					}
				}
				dimDictionaryService.save(dimDictionary);
				return "true";
			}
		}
		return "true";
	}
}