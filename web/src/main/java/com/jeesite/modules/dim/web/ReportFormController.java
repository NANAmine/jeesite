package com.jeesite.modules.dim.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dim.entity.DimDictionary;
import com.jeesite.modules.dim.entity.ExcelUtil;
import com.jeesite.modules.dim.service.DimDictionaryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/test/ReportForm")
public class ReportFormController extends BaseController {
    @Autowired
    private DimDictionaryService dimDictionaryService;
    /**
     * 导出报表
     * @return
     */
    @RequiresPermissions("dim:dimDictionary:edit")
    @RequestMapping(value = "exportData")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response,String id,String status) {
        DimDictionary dimDictionary = new DimDictionary();
        String name = "" , value = "" , expend1 = "",etitle = "",mark = "";
        if("GDBBMD".equals(id)){
            name = "门店名称";
            value = "门店编码";
            etitle = "门店渠道映射信息";
            mark = "注意：门店名称只能为中文，门店编码只能包含数字和英文";
        }else if("JPPPCODE".equals(id)){
            name = "团队名称";
            value = "品牌编码";
            etitle = "品牌团队映射信息";
            mark = "注意：团队名称只能为英文，品牌编码只能为数字";
        }else if("ZDMD".equals(id)){
            name = "重点门店名称";
            value = "门店编码";
            etitle = "驾驶舱重点门店映射信息";
            mark = "注意：重点门店名称只能为中文，门店编码只能为数字";
        }else if("GCYJZDDP".equals(id)){
            name = "系列名称";
            value = "商品编码";
            etitle = "国产烟酒重点单品映射信息";
            mark = "注意：系列名称只能为中文，门店编码只能包含数字和英文";
        }else if("GCYJZDMD".equals(id)){
            name = "重点门店名称";
            value = "门店编码";
            etitle = "国产烟酒重点门店映射信息";
            mark = "注意：重点门店名称只能为中文，门店编码只能为数字";
        }else if("SPBHPP".equals(id)){
            value = "品牌编码";
            etitle = "食品品牌映射信息";
            mark = "注意：品牌编码只能为数字";
        }else if("MDZLBFL".equals(id)){
            name = "门店分类名称";
            value = "门店编码";
            etitle = "战略部门店分类映射信息";
            mark = "注意：门店分类名称只能为中文，门店编码只能为数字";
        }else if("JSCQD".equals(id)){
            name = "渠道名称";
            value = "门店编码";
            etitle = "驾驶舱门店渠道映射信息";
            mark = "注意：渠道名称只能为中文，门店编码只能为数字";
        }else if("ZLBQD".equals(id)){
            name = "渠道名称";
            value = "门店编码";
            etitle = "战略部门店渠道映射信息";
            mark = "注意：渠道名称只能为中文，门店编码只能为数字";
        }else if("ZLBZCGX".equals(id)){
            name = "资产关系名称";
            value = "门店编码";
            etitle = "战略部资产关系映射信息";
            mark = "注意：资产关系名称只能为中文，门店编码只能为数字";
        }else if("XHPPHL".equals(id)){
            name = "品牌编码";
            value = "汇率";
            etitle = "香化品牌汇率映射信息";
            mark = "注意：品牌编码只能为数字，汇率只能为数值";
        }else if("XHMD".equals(id)){
            name = "门店名称";
            value = "门店编码";
            etitle = "香化门店导出映射信息";
            mark = "注意：门店编码只能为数字";
        }else if("HOLIDAY".equals(id)){
            name = "节日名称";
            value = "日期";
            etitle = "节假日映射信息";
            mark = "注意：节日名称只能为中文，日期格式只能如:20190501";
        }else if("QXKZ".equals(id)){
            name = "用户名称";
            value = "品牌编码";
            expend1 = "部门名称";
            etitle = "用户权限映射信息";
            mark = "注意：品牌编码只能为数字，部门名称只能为中文";
        }else if("JPQXKZ".equals(id)){
            name = "用户名称";
            value = "团队名称";
            expend1 = "部门名称";
            etitle = "用户权限映射信息";
            mark = "注意：部门名称只能为中文";
        }
        //获取数据
        List<DimDictionary> list = null;
        if (!"1".equals(status)){
            dimDictionary.setDdicCode(id);
            list = dimDictionaryService.findList(dimDictionary);
        }else{
            dimDictionary.setDdicCode("false");
            list = dimDictionaryService.findList(dimDictionary);
        }

        //excel标题
        String[] title = {name,value,expend1,mark};

        //excel文件名
        String fileName = etitle+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = etitle+"信息";
        String [][] content = new String[list.size()][4];
        if ("SPBHPP".equals(id)){
            for (int i = 0; i < list.size(); i++) {
                DimDictionary obj = list.get(i);
                content[i][1] = obj.getDdicValue();
            }
        }else if("QXKZ".equals(id)||"JPQXKZ".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                DimDictionary obj = list.get(i);
                content[i][0] = obj.getDdicName();
                content[i][1] = obj.getDdicValue();
                content[i][2] = obj.getDdicExpand1();
            }
        }else{
            for (int i = 0; i < list.size(); i++) {
                DimDictionary obj = list.get(i);
                content[i][0] = obj.getDdicName();
                content[i][1] = obj.getDdicValue();
            }
        }
        //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
           try {
                this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresPermissions("dim:dimDictionary:del")
    //@PostMapping(value = "importData")
    @RequestMapping(value = "inputData",method = RequestMethod.POST)
    @ResponseBody
    public String inputData(MultipartFile file, String ddicCode) {
        ExcelUtil er = new ExcelUtil();
        String mark = null;
        List<Map<Integer, String>> list = null;
        int i = 0;
        try {
            list = er.readExcelContentByList(file.getInputStream());
            mark = er.list(list,ddicCode);
            if(mark!=null){
                return renderResult(Global.FALSE, "上传失败:fail!"+mark);
            }
            DimDictionary dim = new DimDictionary();
            dim.setDdicCode(ddicCode);
            dimDictionaryService.deleteAll(dim);
            for (Map<Integer,String> arr: list
                 ) {
                DimDictionary dimDictionary = new DimDictionary();
                dimDictionary.setIsNewRecord(true);
                dimDictionary.setDdicCode(ddicCode);
                dimDictionary.setDdicName(arr.get(0));
                dimDictionary.setDdicValue(arr.get(1));
                dimDictionary.setDdicExpand1(arr.get(2));
                dimDictionaryService.save(dimDictionary);
                i++;
            }
            return renderResult(Global.TRUE, "上传成功:success!");
        } catch (Exception ex) {
            int y = 0;
            for (Map<Integer, String> arr : list
            ) {
                if(y<i) {
                    DimDictionary dimDictionary = new DimDictionary();
                    dimDictionary.setDdicCode(ddicCode);
                    dimDictionary.setDdicName(arr.get(0));
                    dimDictionary.setDdicValue(arr.get(1));
                    dimDictionary.setDdicExpand1(arr.get(2));
                    List<DimDictionary> list1 = dimDictionaryService.findList(dimDictionary);
                    if(list1.size()>0){
                        dimDictionaryService.delete(list1.get(0));
                    }
                    y++;
                }
            }
            return renderResult(Global.FALSE, "上传失败:fail!"+mark+ex.getMessage());
        }
    }
}
