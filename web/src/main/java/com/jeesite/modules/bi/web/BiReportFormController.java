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
            commona = "日期";
            commonb = "进店人数";
            commonc = "离岛人数";
            etitle = "三亚客流数据";
            mark = "注意：日期格式：年月（201904），日期不大于当前年月，进店离岛人数为正整数";
            //excel标题
            title = new String[]{commona, commonb, commonc,  mark};
        } else if ("dim_hk_passenger_flow".equals(id)) {
            commona = "日期年月";
            commonb = "机场总人数本月累计";
            commonc = "机场总人数本月预算";
            commond = "机场总人数去年同期月累计";
            commone = "机场总人数去年同期月预算";
            etitle = "香港客流数据";
            mark = "注意：日期格式：年月（2019-04），人数为正整数";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, mark};
        } else if ("eva_repor".equals(id)) {
            commona = "重点门店名称";
            commona = "门店编码";
            etitle = "EVA数据";
            mark = "注意：重点门店名称只能为中文，门店编码只能为数字";
        } else if ("external_wholesale_report".equals(id)) {
            commona = "日期";
            commonb = "渠道名称";
            commonc = "门店名称";
            commond = "品类名称";
            commone = "营业收入本年累计";
            commonf = "营业收入全年预算";
            commong = "营业收入上年同期";
            etitle = "对外批发数据";
            mark = "注意：日期格式：年月（201904），营业收入本年累计，全年预算，上年同期为数值";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, commong,  mark};
        } else if ("dim_hotel".equals(id)) {
            commona = "日期";
            commonb = "酒店名称";
            commonc = "酒店位置";
            commond = "酒店星级";
            commone = "酒店编码";
            commonf = "客房数";
            commong = "入住率";
            etitle = "三亚酒店基本信息";
            mark = "注意：日期、酒店名称、酒店位置、酒店星级、酒店编码都不为空，客房数和入住率为数值,入住率为百分制（例：81）,日期格式（年-月）";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, commong,  mark};
        } else if ("dim_map_brand_locate".equals(id)) {
            commona = "分类";
            commonb = "店铺号";
            commonc = "落位ID";
            commond = "店铺面积";
            commone = "柜组编码";
            commonf = "柜组名称";
            commong = "品牌编码";
            commonh = "品牌名称";
            etitle = "店铺号品牌映射";
            mark = "注意：分类为中文或/，店铺号为数字、大写英文和-，落位ID为数字，店铺面积为数值，柜组编码和品牌编码为六位数字，柜组名称和品牌名称非必填";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, commong, commonh, mark};
        } else if ("dim_sale_target_yyy".equals(id)) {
            commona = "日期";
            commonb = "门店编码";
            commond = "店面编码";
            commone = "柜组编码";
            commonf = "营业员号";
            commong = "营业员名称";
            commonh = "营业员销售目标";
            etitle = "三亚营业员销售目标";
            mark = "注意：日期为年月：2019-01，销售目标为数值，营业员号为数字，门店编码为4位数字，店面为6位数字，柜组为8位数字";
            //excel标题
            title = new String[]{commona, commonb, commond, commone, commonf, commong, commonh, mark};
        } else if ("dim_lxs_yyrs".equals(id)) {
            commona = "日期";
            commonb = "旅行社或会展编码";
            commonc = "旅行社或会展名称";
            commond = "预约人数";
            commone = "服务费";
            commonf = "旅行社或会展";
            etitle = "旅行社会展预约人数";
            mark = "注意：日期为年月：201901，旅行社或会展编码不为空，名称不为空，预约人数为数字，服务费为数值，类型为旅行社或会展";
            title = new String[]{commona, commonb, commonc, commond, commone, mark};
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
            commonb = "门店名称";
            commonc = "大类编码";
            commond = "折算率";
            commone = "毛利额折算率";
            etitle = "汇率折算信息";
            mark = "注意：日期格式为年，门店名称（香港机场|广州机场|柬中免|邮轮）大类编码为数字，折算率和毛利额折算率为有效数值";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, mark};
        } else if ("dim_gys_ppdz".equals(id)) {
            commona = "供应商编码";
            commonb = "供应商名称";
            commonc = "品牌编码";
            commond = "品牌名称";
            etitle = "供应商品牌对照信息";
            mark = "注意：编码编码为数字且6位";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, mark};
        } else if ("bi_ds_pvuv".equals(id)) {
            commona = "日期";
            commonb = "门店编码";
            commonc = "累计注册用户数";
            commond = "新增注册用户数";
            commone = "uv浏览量";
            commonf = "成交转换率";
            etitle = "电商渠道信息";
            mark = "注意：日期格式为：2019-01，门店编码固定为6874，其它均为数值";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, mark};
        } else if ("dim_area_gz_map".equals(id)) {
            commona = "区域";
            commonb = "店面";
            commonc = "柜组";
            etitle = "区域店面柜组映射信息";
            mark = "注意：店面、柜组正确格式，例：686804 A区一层香化；68680401 A区一层香化 雅诗兰黛团队(MS)";
            //excel标题
            title = new String[]{commona, commonb, commonc, mark};
        } else if ("dim_unit_channel_gcp".equals(id)) {
            commona = "日期";
            commonb = "渠道名称";
            commonc = "门店名称";
            commond = "九其门店名称";
            commone = "描述";
            commonf = "模块（月报、预算）";
            etitle = "战略部门店渠道映射";
            mark = "注意：日期、渠道名称、门店名称、九其门店名称、模块（月报、预算），并且日期格式为：201901";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, mark};
        } else if ("dim_xhgysbg_md".equals(id)) {
            commona = "门店名称";
            commonb = "门店简称";
            commonc = "门店编码";
            commond = "类型";
            etitle = "香化门店导出映射";
            mark = "注意：都不可为空，门店编码为整数，类型选填：汇总或明细";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, mark};
        } else if ("dim_pp_sell_report_exchangrate".equals(id)) {
            commona = "品牌编码";
            commonb = "品牌名称";
            commonc = "门店编码";
            commond = "门店英文名称";
            commone = "汇率";
            commonf = "开始时间";
            commong = "结束时间";
            etitle = "香化品牌汇率映射";
            mark = "注意：品牌编码（数字6位，必填），品牌名称（未校验，非必填），门店编码（数字4位，必填），门店英文名称（英文和空格，必填），汇率（数值，必填），开始时间和结束时间成对填写（格式2020-01-01，未知可填9999-12-31，非必填）";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, commonf, commong, mark};
        } else if ("bi_sy_ldtype_ldrs".equals(id)) {
            commona = "日期";
            commonb = "门店";
            commonc = "离岛类型";
            commond = "离岛人数";
            etitle = "三亚国际免税城离岛人数";
            mark = "注意：日期格式年-月，日期不大于当前年月，门店默认6868（4位数字），离岛类型选一种（01博鳌离岛 02火车离岛 03三亚离境 04三亚离岛 05海口离岛 06轮渡离岛 " +
                    "07海口离境），离岛人数有效数字";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, mark};
        } else if ("bi_gyl_gslx".equals(id)) {
            commona = "日期";
            commonb = "公司编码";
            commonc = "公司名称";
            commond = "公司类型";
            commone = "是否控股";
            etitle = "供应链公司类型";
            mark = "注意：日期格式年月，日期不大于当前年月，公司编码（数字和英文），公司类型（境内、境外），是否控股（是、否）";
            //excel标题
            title = new String[]{commona, commonb, commonc, commond, commone, mark};
        } else if ("bi_gyl_kczz".equals(id)) {
            commona = "日期";
            commonb = "库存组织编码";
            commonc = "库存组织名称";
            commond = "所属库存组织";
            etitle = "供应链库存组织";
            mark = "注意：日期格式年月，日期不大于当前年月,库存组织编码为数字";
            //excel标题
            title = new String[]{commona, commonb, commonc,commond, mark};
        } else if ("dim_rssh_ppmap_xh".equals(id)) {
            commona = "BI日上上海品牌名称";
            commonb = "中免品牌编码";
            commonc = "中免品牌名称";
            commond = "中免供应商编码";
            commone = "中免供应商名称";
            etitle = "日上中免品牌映射";
            mark = "注意：中免品牌编码为数字，中免供应商编码为英文和数字";
            //excel标题
            title = new String[]{commona, commonb, commonc,commond,commone, mark};
        } else if ("bi_gyl_WMS_dlmrkc".equals(id)) {
            commona = "日期";
            commonb = "仓库";
            commonc = "类型";
            commond = "部门";
            commone = "件数";
            commonf = "箱数";
            etitle = "WMS大类每日库存填报";
            mark = "注意：日期格式年-月-日，仓库为（上海库|大连库|深圳库|青岛库），部门为英文，件数和箱数为数字";
            //excel标题
            title = new String[]{commona, commonb, commonc,commond,commone,commonf, mark};
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
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
            }
        } else if ("dim_hk_passenger_flow".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
            }
        } else if ("eva_repor".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][1] = obj.getCommonA();
            }
        } else if ("external_wholesale_report".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
                content[i][6] = obj.getCommonG();
            }
        } else if ("dim_hotel".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
                content[i][6] = obj.getCommonG();
            }
        } else if ("dim_map_brand_locate".equals(id)) {
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
            }
        } else if ("dim_sale_target_yyy".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
                content[i][6] = obj.getCommonG();
            }
        } else if ("dim_lxs_yyrs".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
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
        } else if ("dim_gys_ppdz".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
            }
        }else if ("bi_ds_pvuv".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
            }
        }else if ("dim_area_gz_map".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
            }
        }else if ("dim_unit_channel_gcp".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
            }
        }else if ("dim_xhgysbg_md".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
            }
        } else if ("dim_pp_sell_report_exchangrate".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
                content[i][6] = obj.getCommonG();
            }
        }else if ("bi_sy_ldtype_ldrs".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
            }
        } else if ("bi_gyl_gslx".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
            }
        } else if ("bi_gyl_kczz".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
            }
        }  else if ("dim_rssh_ppmap_xh".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
            }
        }  else if ("bi_gyl_WMS_dlmrkc".equals(id)) {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonB();
                content[i][2] = obj.getCommonC();
                content[i][3] = obj.getCommonD();
                content[i][4] = obj.getCommonE();
                content[i][5] = obj.getCommonF();
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                BiCommonTables obj = list.get(i);
                content[i][0] = obj.getCommonA();
                content[i][1] = obj.getCommonA();
            }
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHssFWorkBook(sheetName, title, content, null);

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
        List<BiCommonTables> listcommons = null;
        int i = 0;
        try {
            list = er.readExcelContentByList(file.getInputStream());
            mark = er.list(list, tableName);
            if (mark != null || list.size()==0) {
                return renderResult(Global.FALSE, "上传失败！" + mark);
            }
            if("bi_gyl_gslx".equals(tableName) || "bi_gyl_kczz".equals(tableName)){
                BiCommonTables bct = new BiCommonTables();
                bct.setTableName(tableName);
                listcommons  = biCommonTablesService.findList(bct);
            }
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
            if("bi_gyl_gslx".equals(tableName) || "bi_gyl_kczz".equals(tableName)){
                for (BiCommonTables arr : listcommons) {
                    biCommonTablesService.delete(arr);
                }
            }
            return renderResult(Global.TRUE, "上传成功！");
        } catch (Exception ex) {
            int y = 0;
            if(list!=null && list.size()!=0){
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
            }
            return renderResult(Global.FALSE, "上传失败！" + mark + ex.getMessage());
        }
    }
}
