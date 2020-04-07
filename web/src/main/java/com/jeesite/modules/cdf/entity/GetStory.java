package com.jeesite.modules.cdf.entity;

/**
 * @Author jiawei.liang
 * @Date 2020/3/31 11:46
 * @Version 1.0
 */
public class GetStory {
    public static String getstory(String username){
        switch(username){
            case "cdjc_7ali":
                return "成都机场入境店";
            case "gzjc_u70u":
                return "广州机场";
            case "hkjc_untu":
                return "香港机场";
            case "hksn_lvw7":
                return "香港市内店";
            case "hm_ptm5":
                return "海免";
            case "hzjc_xctn":
                return "杭州机场";
            case "jpzsnd_dmoy":
                return "柬埔寨市内店";
            case "rsbj_q1ih":
                return "日上北京";
            case "rssh_rk70":
                return "日上上海";
            case "symsc_ty2i":
                return "三亚海棠湾免税城";
            case "xajc_lng1":
                return "西安机场";
            default:
                return "";
        }

    }
}
