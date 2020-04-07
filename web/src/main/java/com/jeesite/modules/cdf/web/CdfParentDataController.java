/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.cdf.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.cdf.entity.CdfChildData;
import com.jeesite.modules.cdf.entity.CdfParentData;
import com.jeesite.modules.cdf.entity.GetStory;
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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
	public String form(CdfParentData cdfParentData, Model model, String status, HttpServletRequest request, HttpServletResponse response) {
        if("1".equals(status)){
            status = "true";
        }else {
            status = "false";
        }
        String flag = "true";
        String createduser =  request.getRemoteUser();
        if("".equals(GetStory.getstory(createduser))){
            flag = "false";
        }
        /*新增获取默认值，取最近一条记录的门店 时间(始终默认昨天) 渠道 */
        if(cdfParentData.getId()==null){
            CdfParentData cdf = new CdfParentData();
            cdf.setCreateBy(createduser);
            List<CdfParentData> list = null;
            list =cdfParentDataService.findList(cdf);
            Calendar calendar= Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,-24);
            cdfParentData.setTime(calendar.getTime());
            cdfParentData.setStore(GetStory.getstory(createduser));
            if(list!=null && list.size()!=0){
                cdf = list.get(list.size() - 1);
                cdf = cdfParentDataService.get(cdf.getId(), false);
                cdfParentData.setStore(cdf.getStore());
                List<CdfChildData> childs= cdf.getCdfChildDataList();
                List<CdfChildData> childDatalist = new LinkedList<>();
                for(CdfChildData childData : childs){
                    CdfChildData cdfChildData = new CdfChildData();
                    if(childData!=null){
                        cdfChildData.setChannel(childData.getChannel());
                        childDatalist.add(cdfChildData);
                    }else {
                        childDatalist.add(cdfChildData);
                    }
                }
                cdfParentData.setCdfChildDataList(childDatalist);
            }else{
                List<CdfChildData> childDatalist = new LinkedList<>();
                CdfChildData cdfChildData1 = new CdfChildData();
                CdfChildData cdfChildData2 = new CdfChildData();
                CdfChildData cdfChildData3 = new CdfChildData();
                cdfChildData1.setChannel("自建");
                childDatalist.add(cdfChildData1);
                childDatalist.add(cdfChildData2);
                childDatalist.add(cdfChildData3);
                cdfParentData.setCdfChildDataList(childDatalist);
            }
        }
        model.addAttribute("flag",flag);
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