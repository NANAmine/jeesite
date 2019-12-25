package com.jeesite.modules.bi.entity;

import com.jeesite.modules.bi.service.BiCommonTablesService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
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
                    if (row.getCell(j) != null && !row.getCell(j).getStringCellValue().equals("")) {
                        row.getCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
                    }
                    if (row.getCell(j) == null || row.getCell(j).getStringCellValue() == null) {
                        break;
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
            if(map.size() != 0){
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
                if(!isChinese(arr.get(0))||!isEN(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:门店名称只能为中文，门店编码只能包含数字和英文。";
                    return mark;
                }
            }else if("dim_hk_passenger_flow".equals(tableName)){
                if(!isEnglish(arr.get(0))||!isNum(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:团队名称只能为英文，门店编码只能为数字";
                    return mark;
                }
            }else if("eva_repor".equals(tableName)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:重点门店名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("external_wholesale_report".equals(tableName)){
                if(!isChinese(arr.get(0))||!isEN(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:系列名称只能为中文，门店编码只能包含数字和英文";
                    return mark;
                }
            }else if("dim_hotel".equals(tableName)){
                if(arr.get(0).isEmpty()||!isDateym(arr.get(0))||arr.get(1).isEmpty()||arr.get(2).isEmpty()||arr.get(3).isEmpty()||arr.get(4).isEmpty()||!isNumVal(arr.get(5))||!isNumVal(arr.get(6))){
                    mark = "第"+i+"条数据错误,请校验:是否存在空值,客房数数字,入住率为百分制（例：81）,日期格式（年-月）";
                    return mark;
                }
            }else if("dim_map_brand_locate".equals(tableName)){
                if(!isNum(arr.get(0))){
                    mark = "第"+i+"条数据错误,请校验:品牌编码只能为数字";
                    return mark;
                }
            }else if("dim_sale_target_yyy".equals(tableName)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "第"+i+"条数据错误,请校验:门店分类名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("dim_lxs_yyrs".equals(tableName)){
                /*if(true){
                    mark = "第"+i+"条数据错误,请校验:";
                    return mark;
                }*/
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
                if(!isDateym(arr.get(0)) || !arr.get(1).equals("6874") || !isNumVal(arr.get(2)) || !isNumVal(arr.get(3)) || !isNumVal(arr.get(4)) || !isNumVal(arr.get(5))){
                    mark = "第"+i+"条数据错误,注意：日期格式为：2019-01，门店编码固定为6874，其它均为数值";
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
    /*这是英文验证*/
    public static boolean isEnglish(String charaString){

        return charaString.matches("^[a-zA-Z]*");

    }
    /*这是英文和数字验证*/
    public static boolean isEN(String charaString){

        return charaString.matches("^[A-Za-z0-9]*");

    }
    /*这是数字验证*/
    public static boolean isNum(String charaString){

        return charaString.matches("[0-9]*");

    }
    /*这是数值验证*/
    public static boolean isNumVal(String charaString){

        return charaString.matches("^([1-9][0-9]*)(\\.[0-9]*)?$|^(0\\.[0-9]*)");

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

}
