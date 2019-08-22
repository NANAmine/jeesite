package com.jeesite.modules.dim.entity;

import com.jeesite.modules.dim.service.DimDictionaryService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {

    @Autowired
    private DimDictionaryService dimDictionaryService;
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
                if(row.getCell(j) != null){
                    row.getCell(j).setCellType(HSSFCell.CELL_TYPE_STRING);
                }
                if(row.getCell(j) == null||row.getCell(j).getStringCellValue() == null){
                    break;
                }else {
                    if(row.getCell(j).getStringCellValue() == null){
                        break;
                    }else {
                        map.put(j, row.getCell(j).getStringCellValue());
                    }
                }//str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                j++;
            }
            list.add(map);
        }
        return list;
    }
    /*
    *判断Excel单元格格式验证输入是否准确
     *  */
    public String list(List<Map<Integer,String>> list,String ddicCode){
        String mark = null;
        for (Map<Integer,String> arr: list
        ) {
            DimDictionary dimDictionary = new DimDictionary();
            dimDictionary.setIsNewRecord(true);
            dimDictionary.setDdicCode(ddicCode);
            if("GDBBMD".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isEN(arr.get(1))){
                    mark = "门店名称只能为中文，门店编码只能包含数字和英文。";
                    return mark;
                }
            }else if("JPPPCODE".equals(ddicCode)){
                if(!isEnglish(arr.get(0))||!isNum(arr.get(1))){
                    mark = "团队名称只能为英文，门店编码只能为数字";
                    return mark;
                }
            }else if("ZDMD".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "重点门店名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("GCYJZDDP".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isEN(arr.get(1))){
                    mark = "系列名称只能为中文，门店编码只能包含数字和英文";
                    return mark;
                }
            }else if("GCYJZDMD".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "重点门店名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("SPBHPP".equals(ddicCode)){
                if(!isNum(arr.get(0))){
                    mark = "品牌编码只能为数字";
                    return mark;
                }
            }else if("MDZLBFL".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "门店分类名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("JSCQD".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "渠道名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("ZLBQD".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isChinese(arr.get(2))){
                    mark = "渠道名称只能为中文，门店编码只能为数字";
                    return mark;
                }
            }else if("ZLBZCGX".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isNum(arr.get(1))){
                    mark = "品牌编码只能为数字，部门名称只能为中文";
                    return mark;
                }
            }else if("XHPPHL".equals(ddicCode)){
                if(!isNum(arr.get(0))||!isNumVal(arr.get(1))){
                    mark = "品牌编码只能为数字，汇率只能为数值";
                    return mark;
                }
            }else if("XHMD".equals(ddicCode)){
                if(!isNum(arr.get(1))){
                    mark = "门店编码只能为数字";
                    return mark;
                }
            }else if("HOLIDAY".equals(ddicCode)){
                if(!isChinese(arr.get(0))||!isDate(arr.get(1))){
                    mark = "节日名称只能为中文，日期格式只能如:20190501";
                    return mark;
                }
            }else if("QXKZ".equals(ddicCode)){
                if(!isNum(arr.get(1))||!isChinese(arr.get(2))){
                    mark = "品牌编码只能为数字，部门名称只能为中文";
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

}
