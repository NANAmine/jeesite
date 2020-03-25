package com.jeesite.modules.bi.entity;

import com.jeesite.modules.bi.service.BiCommonTablesService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {

    @Autowired
    private BiCommonTablesService biCommonTablesService;
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setBottomBorderColor((short) 000000);
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 读取Excel数据内容
     * @param  //inputStream is
     * @return List<Map<String, String>>  Map的key是列Id(0代表第一列)，值是具体内容
     */
    public List<Map<Integer, String>> readExcelContentByList(InputStream is) {

        List<Map<Integer, String>> list = new ArrayList<Map<Integer,String>>();
        HSSFWorkbook wb = null;
        try {
            //fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(is);
            //wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFSheet sheet = wb.getSheetAt(0);

        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        HSSFRow row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            if(row == null){
                break;
            }
            int j = 0;
            Map<Integer,String> map = new HashMap<Integer, String>();

            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                //if ( row.getCell(j).getCellType())
                //System.out.println(row.getCell(j).getCellType());
                //CellType cellType = cell.getCellTypeEnum();
                //row.getCell(j).setCellValue();
              /*  if(row.getCell(j).getCellType() == 0){
                    Date date = row.getCell(j).getDateCellValue();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    //Date date = new Date(System.currentTimeMillis());
                    String day = simpleDateFormat.format(date);
                    map.put(j, day);
                }*/
                if(row.getCell(j) != null && row.getCell(j).getCellType() == 0){
                    String[] strArr = row.getCell(j).toString().split("-");
                    if(strArr.length == 3){
                        Date date = row.getCell(j).getDateCellValue();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        //Date date = new Date(System.currentTimeMillis());
                        String day = simpleDateFormat.format(date);
                        map.put(j, day);
                    }else {
                        row.getCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
                        String data =  row.getCell(j).getStringCellValue();
                        map.put(j, data);
                    }
                }else {
                    if (row.getCell(j) != null && !"".equals(row.getCell(j).getStringCellValue())) {
                        row.getCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
                    }
                    if (row.getCell(j) == null || row.getCell(j).getStringCellValue() == null) {
                        if(j==(colNum-1)){
                            break;
                        }else {
                            map.put(j, "");
                        }
                    } else {
                        if (row.getCell(j).getCellType() == 1) {
                            map.put(j, row.getCell(j).getStringCellValue());
                        }/*else if(row.getCell(j).getCellType() == 0){
                        Date date = row.getCell(j).getDateCellValue();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        //Date date = new Date(System.currentTimeMillis());
                        String day = simpleDateFormat.format(date);
                        map.put(j, day);
                    }*/
                    }//str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                }
                j++;
            }
            if(map.size() == (colNum-1) ){
                list.add(map);
            }

        }
        return list;
    }
    /*
     *判断Excel单元格格式验证输入是否准确
     *  */
    public String list(List<Map<Integer,String>> list,String tableName){
        String mark = null;
        int i = 0;
        for (Map<Integer,String> arr: list
        ) {
            i++;
            BiCommonTables biCommonTables = new BiCommonTables();
            biCommonTables.setIsNewRecord(true);
            biCommonTables.setTableName(tableName);
            if("bi_jsc_jdldrs".equals(tableName)){
                if(!isDateYM(arr.get(0)) || !ltedateym(arr.get(0))||!isNum(arr.get(1))||!isNum(arr.get(2))){
                    mark = "第"+i+"条数据错误,请校验:日期格式：年月（201904），日期不大于当前年月，进店离岛人数为正整数";
                    return mark;
                }
            }else if("dim_hk_passenger_flow".equals(tableName)){
                if(!isDateym(arr.get(0))||!isNum(arr.get(1))||!isNum(arr.get(2))||!isNum(arr.get(3))||!isNum(arr.get(4))){
                    mark = "第"+i+"条数据错误,请校验：日期格式：年月（2019-04），人数为正整数";
                    return mark;
                }
            }else if("eva_repor".equals(tableName)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:重点门店名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("external_wholesale_report".equals(tableName)){
                if(!isDateYM(arr.get(0))||!isNumVal(arr.get(4))||!isNumVal(arr.get(5))||!isNumVal(arr.get(6))){
                    mark = "第"+i+"条数据错误,请校验:日期格式：年月（201904），营业收入本年累计，全年预算，上年同期为数值";
                    return mark;
                }
            }else if("dim_hotel".equals(tableName)){
                if(arr.get(0).isEmpty()||!isDateym(arr.get(0))||arr.get(1).isEmpty()||arr.get(2).isEmpty()||arr.get(3).isEmpty()||arr.get(4).isEmpty()||!isNumVal(arr.get(5))||!isNumVal(arr.get(6))||!isCas(arr.get(1))||!isCas(arr.get(2))){
                    mark = "第"+i+"条数据错误,请校验:是否存在空值,客房数数字,入住率为百分制（例：81）,日期格式（年-月），酒店名称和位置为中文和数字";
                    return mark;
                }
            }else if("dim_map_brand_locate".equals(tableName)){
                if(!isCaxg(arr.get(0))||!isEahg(arr.get(1))||!isNum(arr.get(2))||!isNumVal(arr.get(3))||!isNum(arr.get(4))||!isNum(arr.get(6))||arr.get(4).length()!=6||arr.get(6).length()!=6){
                    mark = "第"+i+"条数据错误,请校验:分类为中文或/，店铺号为数字、大写英文和-，落位ID为数字，店铺面积为数值，柜组编码和品牌编码为六位数字";
                    return mark;
                }
            }else if("dim_sale_target_yyy".equals(tableName)){
                if(!isDateym(arr.get(0))||!isNum(arr.get(4))||!isNum(arr.get(1))||!isNum(arr.get(4))||!isNum(arr.get(2))||!isNum(arr.get(3))||!isNumVal(arr.get(6))||arr.get(3).length()!=8||arr.get(2).length()!=6||arr.get(1).length()!=4){
                    mark = "第"+i+"条数据错误,请校验:日期为年月：2019-01，销售目标为数值，营业员号为数字，门店编码为4位数字，店面为6位数字，柜组为8位数字";
                    return mark;
                }
            }else if("dim_lxs_yyrs".equals(tableName)){
                if(!isDateYM(arr.get(0))||!isNumVal(arr.get(4))||!isNum(arr.get(3))||!isLah(arr.get(5))||arr.get(1).isEmpty()||arr.get(2).isEmpty()){
                    mark = "第"+i+"条数据错误,请校验:日期为年月：201901，旅行社或会展编码不为空，名称不为空，预约人数为数字，服务费为数值，类型为旅行社或会展";
                    return mark;
                }
            }else if("dim_instructions_detail".equals(tableName)){
                System.out.println(arr.get(0));
                if(!isEN(arr.get(0)) || arr.get(0).isEmpty()) {
                    if (!isEN(arr.get(0)) || arr.get(0).isEmpty()) {
                        mark = "第" + i + "条数据错误,请校验:商品编码只能为数字和英文。您实际输入的内容为：" + arr.get(0);
                        return mark;
                    } else {
                        //mark = "第" + i + "条数据错误,请校验:厂商货号只能为数字和英文。您实际输入的内容为：" + arr.get(1);
                        mark = "第" + i + "条数据错误,请校验";
                        return mark;
                    }
                }else if(arr.get(3).length() > 10000 || arr.get(4).length() > 10000 || arr.get(10).length() > 10000){
                    mark = "第" + i + "条数据个别字段太长,长度超过10000";
                    return mark;
                }
            }else if("dim_texchange_rate".equals(tableName)){
                if(!dateYear(arr.get(0)) || !isNum(arr.get(1)) || !isNum(arr.get(2)) || !isNumVal(arr.get(3)) || !isNumVal(arr.get(4))){
                    mark = "第"+i+"条数据错误,请校验:日期格式为年，门店编码为数字，大类编码为数字，折算率和毛利额折算率为有效数值";
                    return mark;
                }
            }else if("dim_gys_ppdz".equals(tableName)){
                if(!isNum(arr.get(2))){
                    mark = "第"+i+"条数据错误,请校验:品牌编码为数字，长度为6位";
                    return mark;
                }
            }else if("bi_ds_pvuv".equals(tableName)){
                if(!isDateym(arr.get(0)) || !"6874".equals(arr.get(1)) || !isNumVal(arr.get(2)) || !isNumVal(arr.get(3)) || !isNumVal(arr.get(4)) || !isNumVal(arr.get(5))){
                    mark = "第"+i+"条数据错误,注意：日期格式为：2019-01，门店编码固定为6874，其它均为数值";
                    return mark;
                }
            }else if("dim_area_gz_map".equals(tableName)){
                /*if(arr.get(1).length()!= 6 || arr.get(2).length()!= 8 || !isNumVal(arr.get(1)) || !isNumVal(arr.get(2)) ){
                    mark = "第"+i+"条数据错误,注意：店面6位数字，柜组位8位数字";
                    return mark;
                }*/
            }else if("dim_unit_channel_gcp".equals(tableName)){
                if(arr.get(0).isEmpty() || arr.get(1).isEmpty() || arr.get(2).isEmpty() || arr.get(3).isEmpty() || arr.get(5).isEmpty() || !isDateym(arr.get(0)) || !isChinese(arr.get(1)) || !isChinese(arr.get(3)) || !isCak(arr.get(2)) || !isYay(arr.get(5)) ){
                    mark = "第"+i+"条数据错误,注意：日期、渠道名称、门店名称、九其门店名称、模块（月报、预算）必填，并且日期格式为：201901";
                    return mark;
                }
            }else if("dim_xhgysbg_md".equals(tableName)){
                if(arr.get(0).isEmpty() || arr.get(1).isEmpty() || !isNum(arr.get(2)) || !isMah(arr.get(3))){
                    mark = "第"+i+"条数据错误,注意：内容均必填，并且门店编码为整数，类型选填：汇总或明细";
                    return mark;
                }
            }else if("dim_pp_sell_report_exchangrate".equals(tableName)){
                if(!isNum(arr.get(0)) || arr.get(0).length()!=6 || !isNum(arr.get(2)) || arr.get(2).length()!=4 || !isEnglishKg(arr.get(3)) || !isNumVal(arr.get(4)) || !((isDateymd(arr.get(5))&&isDateymd(arr.get(6)))||(arr.get(5).isEmpty()&&arr.get(6).isEmpty()))){
                    mark = "第"+i+"条数据错误,注意：品牌编码（数字6位，必填），品牌名称（未校验，非必填），门店编码（数字4位，必填），门店英文名称（英文和空格，必填），汇率（数值，必填），开始时间和结束时间成对填写（格式2020-01-01，未知可填9999-12-31，非必填）";
                    return mark;
                }
            }else if("bi_sy_ldtype_ldrs".equals(tableName)){
                if(!isDateym(arr.get(0)) || !ltedate(arr.get(0))|| !isNum(arr.get(1)) || arr.get(1).length()!=4 || !isLdlx(arr.get(2)) || !isNum(arr.get(3))){
                    mark = "第"+i+"条数据错误,注意：日期格式年-月，日期不大于当前年月，门店默认6868(4位数字)，离岛类型选一种（01博鳌离岛 02火车离岛 03三亚离境 04三亚离岛 05海口离岛 06轮渡离岛 07海口离境），离岛人数有效数字";
                    return mark;
                }
            }else if("bi_gyl_gslx".equals(tableName)){
                if(!isDateYM(arr.get(0)) || !ltedateym(arr.get(0))|| !isEN(arr.get(1)) || !isJaj(arr.get(3)) || !iskg(arr.get(4))){
                    mark = "第"+i+"条数据错误,注意：日期格式年月，日期不大于当前年月，公司编码（数字和英文），公司类型（境内、境外），是否控股（是、否）";
                    return mark;
                }
            }else if("bi_gyl_kczz".equals(tableName)){
                if(!isDateYM(arr.get(0)) || !ltedateym(arr.get(0)) || !isNum(arr.get(1))){
                    mark = "第"+i+"条数据错误,注意：日期格式年月，日期不大于当前年月,库存组织编码为数字";
                    return mark;
                }
            }else if("dim_rssh_ppmap_xh".equals(tableName)){
                /*if(!isNum(arr.get(1)) || !isEN(arr.get(3))){
                    mark = "第"+i+"条数据错误,注意：中免品牌编码为数字，中免供应商编码为英文和数字";
                    return mark;
                }*/
            }else if("bi_gyl_WMS_dlmrkc".equals(tableName)){
                if(!isDateymd(arr.get(0)) || !gylck(arr.get(1)) || !isEnglish(arr.get(3)) || !isNumVal(arr.get(4)) || !isNumVal(arr.get(5))){
                    mark = "第"+i+"条数据错误,注意：日期格式年-月-日，仓库为（上海库|大连库|深圳库|青岛库），部门为英文，件数和箱数为数字";
                    return mark;
                }
            }
        }
        return mark;
    }
    /*
     * 这是中文验证
     * */
    public static boolean isChinese(String str){
        //noinspection AlibabaAvoidPatternCompileInMethod
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for(int i=0;i<c.length;i++){
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if(!matcher.matches()){
                return false;
            }
        }
        return true;
    }
    /*
     * 这是中文验证
     * */
    public static boolean isCaxg(String str){
        //noinspection AlibabaAvoidPatternCompileInMethod
        Pattern pattern = Pattern.compile("/|[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for(int i=0;i<c.length;i++){
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if(!matcher.matches()){
                return false;
            }
        }
        return true;
    }
    /*这是英文验证*/
    public static boolean isEahg(String charaString){

        return charaString.matches("^(-|[A-Z]|[0-9])+");

    }
    /*这是英文验证*/
    public static boolean isEnglish(String charaString){

        return charaString.matches("^[a-zA-Z]+");

    }
    /*这是英文空格验证*/
    public static boolean isEnglishKg(String charaString){

        return charaString.matches("^(([a-zA-Z]\\s)|[a-zA-Z])+");

    }
    /*这是英文和数字验证*/
    public static boolean isEN(String charaString){

        return charaString.matches("^[A-Za-z0-9]+");

    }
    /*这是数字验证*/
    public static boolean isNum(String charaString){

        return charaString.matches("[0-9]+");

    }
    /*这是数值验证*/
    public static boolean isNumVal(String charaString){

        return charaString.matches("^([1-9][0-9]*)(\\.[0-9]*)?$|^(0\\.[0-9]*)+");

    }
    /*这是日期验证如：20190501*/
    public static boolean isDate(String charaString){

        return charaString.matches("^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)0229)");

    }
    /*这是日期验证如：2019/05/01*/
    public static boolean isymd(String charaString){

        return charaString.matches("^((?!0000)[0-9]{4}[/]((0[1-9]|1[0-2])[/](0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)0229)$");
        //return charaString.matches("^20\\d{2}-[01]\\d-[0123]\\d$");

    }

    /*日期年验证 如：2019*/
    public static boolean dateYear(String charaString){

        return charaString.matches("^\\d{4}$");

    }

    /*日期年验证 如：2019-05*/
    public static boolean isDateym(String charaString){

        return charaString.matches("^\\d{4}[-]((0([1-9]))|(1(0|1|2)))$");

    }
    /*日期年验证 如：2019-01-01*/
    public static boolean isDateymd(String charaString){

        return charaString.matches("^((((19|20|99)\\d{2})[-](0?[13-9]|1[012])[-](0?[1-9]|[12]\\d|30))|(((19|20|99)\\d{2})[-](0?[13578]|1[02])[-]31)|(((19|20|99)\\d{2})[-]0?2[-](0?[1-9]|1\\d|2[0-8]))|((((19|20|99)([13579][26]|[2468][048]|0[48]))|(2000))[-]0?2[-]29))$");

    }
    /*日期年验证 如：201905*/
    public static boolean isDateYM(String charaString){

        return charaString.matches("^\\d{4}((0([1-9]))|(1(0|1|2)))$");

    }
    /*中文和括号验证 如：广州（机场）*/
    public static boolean isCak(String charaString){

        return charaString.matches("^([\\u4e00-\\u9fa5]|\\（|\\）)+$");

    }
    /*月报或者预算 如：月报*/
    public static boolean isYay(String charaString){

        return charaString.matches("^(月报|预算)$");

    }
    /*月报或者预算 如：月报*/
    public static boolean isLah(String charaString){

        return charaString.matches("^(旅行社|会展)$");

    }
    /*中文或数字 如：海棠湾3号*/
    public static boolean isCas(String charaString){

        return charaString.matches("^(([0-9]*[\\u4e00-\\u9fa5][0-9]*)|[\\u4e00-\\u9fa5])+$");

    }
    /*明细或者汇总 如：明细*/
    public static boolean isMah(String charaString){

        return charaString.matches("^(明细|汇总)$");

    }
    /*境内或者境外 如：境内*/
    public static boolean isJaj(String charaString){

        return charaString.matches("^(境内|境外)$");

    }

    /*是或者否 如：是*/
    public static boolean iskg(String charaString){

        return charaString.matches("^(是|否)$");

    }
    /*离岛类型 如：02火车离岛*/
    public static boolean isLdlx(String charaString){

        return charaString.matches("^(01博鳌离岛|02火车离岛|03三亚离境|04三亚离岛|05海口离岛|06轮渡离岛|07海口离境)$");

    }

    /*是或者否 如：是*/
    public static boolean gylck(String charaString){

        return charaString.matches("^(上海库|大连库|深圳库|青岛库)$");

    }
    /*判断日期是否大于当前时间*/
    public static boolean ltedate(String charaString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");//注意月份是MM
        Date date = null;
        Date now = null;
        try {
            date = simpleDateFormat.parse(charaString);
            now = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.before(now);
    }
    /*判断日期是否大于当前时间*/
    public static boolean ltedateym(String charaString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");//注意月份是MM
        Date date = null;
        Date now = null;
        try {
            date = simpleDateFormat.parse(charaString);
            now = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.before(now);
    }

}
