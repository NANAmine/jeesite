package com.jeesite.modules.bi.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.bi.entity.BiCommonTables;
import com.jeesite.modules.bi.service.BiCommonTablesService;
import com.jeesite.modules.bi.entity.ExcelUtil;
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
@RequestMapping(value = "${adminPath}/test/BiReportForm")
public class BiReportFormController extends BaseController {
    @Autowired
    private BiCommonTablesService biCommonTablesService;

    /**
     * 导出报表
     *
     * @return
     */
    @RequiresPermissions("bi:biCommonTables:edit")
    @RequestMapping(value = "exportData")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response, String id, String status) {
        BiCommonTables biCommonTables = new BiCommonTables();
        String commona = "", commonb = "", commonc = "", commond = "", commone = "", commonf = "", commong = "", commonh = "", commoni = "", commonj = "", commonk = "", commonl = "", commonm = "", commonn = "", etitle = "", mark = "";
        String[] title = null;
        if ("bi_jsc_jdldrs".equals(id)) {
            commona = "门店名称";
            commonb = "门店编码";
            commona = "门店名称";
            etitle = "三亚客流数据";
            mark = "注意：门店名称只能为中文，门店编码只能包含数字和英文";
        } else if ("dim_hk_passenger_flow".equals(id)) {
            commona = "团队名称";
            commona = "品牌编码";
            etitle = "香港客流数据";
            mark = "注意：团队名称只能为英文，门店编码只能为数字";
        } else if ("eva_repor".equals(id)) {
            commona = "重点门店名称";
            commona = "门店编码";
            etitle = "EVA数据";
            mark = "注意：重点门店名称只能为中文，门店编码只能为数字";
        } else if ("external_wholesale_report".equals(id)) {
            commona = "系列名称";
            commona = "商品编码";
            etitle = "对外批发数据";
            mark = "注意：系列名称只能为中文，门店编码只能包含数字和英文";
        } else if ("dim_hotel".equals(id)) {
            commona = "重点门店名称";
            commona = "门店编码";
            etitle = "三亚酒店基本信息";
            mark = "注意：重点门店名称只能为中文，门店编码只能为数字";
        } else if ("dim_map_brand_locate".equals(id)) {
            commona = "品牌编码";
            etitle = "店铺号品牌映射";
            mark = "注意：品牌编码只能为数字";
        } else if ("dim_sale_target_yyy".equals(id)) {
            commona = "门店分类名称";
            commona = "门店编码";
            etitle = "三亚销售目标";
            mark = "注意：门店分类名称只能为中文，门店编码只能为数字";
        } else if ("dim_lxs_yyrs".equals(id)) {
            commona = "渠道名称";
            commona = "门店编码";
            etitle = "旅行社会站预约人数";
            mark = "注意：渠道名称只能为中文，门店编码只能为数字";
        } else if ("dim_instructions_detail".equals(id)) {
            commona = "商品编码";
            commonb = "厂商货号";
            commonc = "品牌编码";
            commond = "批件中文名称";
            commone = "批件英文名称";
            commonf = "批准日期";
            commong = "有效日期";
            commonh = "备案地点";
            commoni = "批件状态";
            commonj = "备注";
            commonk = "卫生许可证";
            commonl = "批件原产国";
            commonm = "批件实际产国";
            commonn = "规格";
            etitle = "批件明细数据维护";
            mark = "注意：商品编码和厂商货号：只能为数字和英文";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, commong, commonh, commoni, commonj, commonk, commonl, commonm, commonn, mark};
        } else if ("dim_texchange_rate".equals(id)) {
            commona = "日期";
            commonb = "门店编码";
            commonc = "大类编码";
            commond = "折算率";
            commone = "毛利额折算率";
            etitle = "汇率折算信息";
            mark = "注意：日期格式为年，门店编码为数字，大类编码为数字，折算率和毛利额折算率为有效数值";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, mark};
        }
        //获取数据
        List<BiCommonTables> list = null;
        if (!"1".equals(status)) {
            biCommonTables.setTableName(id);
            list = biCommonTablesService.findList(biCommonTables);
        } else {
            biCommonTables.setTableName("false");
            list = biCommonTablesService.findList(biCommonTables);
        }

        //excel文件名
        String fileName = etitle + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = etitle + "信息";
        String[][] content = new String[list.size()][20];
        if ("bi_jsc_jdldrs".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_hk_passenger_flow".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonA();
                content[i][2] = obj.getCommonA();
            }
        } else if ("eva_repor".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("external_wholesale_report".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_hotel".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_map_brand_locate".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_sale_target_yyy".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_lxs_yyrs".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("dim_instructions_detail".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
                content[i][6] = obj.getCommonG();
                content[i][7] = obj.getCommonH();
                content[i][8] = obj.getCommonI();
                content[i][9] = obj.getCommonJ();
                content[i][10] = obj.getCommonK();
                content[i][11] = obj.getCommonL();
                content[i][12] = obj.getCommonM();
                content[i][13] = obj.getCommonN();
            }
        } else if ("dim_texchange_rate".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonA();
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
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresPermissions("bi:biCommonTables:del")
    //@PostMapping(value = "importData")
    @RequestMapping(value = "inputData", method = RequestMethod.POST)
    @ResponseBody
    public String importData(MultipartFile file, String tableName) {
        ExcelUtil er = new ExcelUtil();
        String mark = null;
        List<Map<Integer, String>> list = null;
        int i = 0;
        try {
            list = er.readExcelContentByList(file.getInputStream());
            mark = er.list(list, tableName);
            if (mark != null) {
                return renderResult(Global.FALSE, "上传失败！" + mark);
            }
            /*BiCommonTables bct = new BiCommonTables();
            bct.setTableName(tableName);
            biCommonTablesService.deleteAll(bct);*/
            for (Map<Integer, String> arr : list
            ) {
                BiCommonTables biCommonTables = new BiCommonTables();
                biCommonTables.setIsNewRecord(true);
                biCommonTables.setTableName(tableName);
                biCommonTables.setCommonA(arr.get(0));
                biCommonTables.setCommonB(arr.get(1));
                biCommonTables.setCommonC(arr.get(2));
                biCommonTables.setCommonD(arr.get(3));
                biCommonTables.setCommonE(arr.get(4));
                biCommonTables.setCommonF(arr.get(5));
                biCommonTables.setCommonG(arr.get(6));
                biCommonTables.setCommonH(arr.get(7));
                biCommonTables.setCommonI(arr.get(8));
                biCommonTables.setCommonJ(arr.get(9));
                biCommonTables.setCommonK(arr.get(10));
                biCommonTables.setCommonL(arr.get(11));
                biCommonTables.setCommonM(arr.get(12));
                biCommonTables.setCommonN(arr.get(13));
                biCommonTables.setCommonO(arr.get(14));
                biCommonTables.setCommonP(arr.get(15));
                biCommonTablesService.save(biCommonTables);
                i++;
            }
            return renderResult(Global.TRUE, "上传成功！");
        } catch (Exception ex) {
            int y = 0;
            for (Map<Integer, String> arr : list
            ) {
                if(y<i) {
                    BiCommonTables biCommonTables = new BiCommonTables();
                    biCommonTables.setTableName(tableName);
                    biCommonTables.setCommonA(arr.get(0));
                    biCommonTables.setCommonB(arr.get(1));
                    biCommonTables.setCommonC(arr.get(2));
                    biCommonTables.setCommonD(arr.get(3));
                    biCommonTables.setCommonE(arr.get(4));
                    biCommonTables.setCommonF(arr.get(5));
                    biCommonTables.setCommonG(arr.get(6));
                    biCommonTables.setCommonH(arr.get(7));
                    biCommonTables.setCommonI(arr.get(8));
                    biCommonTables.setCommonJ(arr.get(9));
                    biCommonTables.setCommonK(arr.get(10));
                    biCommonTables.setCommonL(arr.get(11));
                    biCommonTables.setCommonM(arr.get(12));
                    biCommonTables.setCommonN(arr.get(13));
                    biCommonTables.setCommonO(arr.get(14));
                    biCommonTables.setCommonP(arr.get(15));
                    List<BiCommonTables> list1 = biCommonTablesService.findList(biCommonTables);
                    if(list1.size()>0){
                        biCommonTablesService.delete(list1.get(0));
                    }
                    y++;
                }
            }
            return renderResult(Global.FALSE, "上传失败！" + mark + ex.getMessage());
        }
    }
}