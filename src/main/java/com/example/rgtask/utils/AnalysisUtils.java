package com.example.rgtask.utils;

import com.example.rgtask.vo.AnalysisReturnVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisUtils {
    private static Map<String, List<String>> xAxis;

    static {
        xAxis = new HashMap<>();
        //性别
        List<String> sex = new ArrayList<>();
        sex.add("男");
        sex.add("女");
        xAxis.put("sex",sex);
        //星座
        List<String> constellation = new ArrayList<>();
        constellation.add("白羊座");
        constellation.add("金牛座");
        constellation.add("双子座");
        constellation.add("巨蟹座");
        constellation.add("狮子座");
        constellation.add("天秤座");
        constellation.add("处女座");
        constellation.add("天蝎座");
        constellation.add("射手座");
        constellation.add("魔羯座");
        constellation.add("水瓶座");
        constellation.add("双鱼座");
        xAxis.put("constellation",constellation);
        //年级
        List<String> grade = new ArrayList<>();
        grade.add("大一");
        grade.add("大二");
        grade.add("大三");
        grade.add("大四");
        xAxis.put("grade",grade);
        //省份
        List<String> province = new ArrayList<>();
        province.add("北京市");
        province.add("天津市");
        province.add("上海市");
        province.add("重庆市");
        province.add("河北省");
        province.add("山西省");
        province.add("辽宁省");
        province.add("吉林省");
        province.add("黑龙江省");
        province.add("江苏省");
        province.add("浙江省");
        province.add("安徽省");
        province.add("福建省");
        province.add("江西省");
        province.add("山东省");
        province.add("河南省");
        province.add("湖北省");
        province.add("湖南省");
        province.add("广东省");
        province.add("海南省");
        province.add("四川省");
        province.add("贵州省");
        province.add("云南省");
        province.add("陕西省");
        province.add("甘肃省");
        province.add("青海省");
        province.add("台湾省");
        province.add("内蒙古自治区");
        province.add("广西壮族自治区");
        province.add("西藏自治区");
        province.add("宁夏回族自治区");
        province.add("新疆维吾尔自治区");
        province.add("香港特别行政区");
        province.add("澳门特别行政区");
        xAxis.put("address",province);
    }

    public static Map<String, AnalysisReturnVO> initAnalysisArray(String condition){
        //初始化Map
        List<String> init = xAxis.get(condition);
        Map<String,AnalysisReturnVO> returnVOS = new HashMap<>();
        for (String str : init){
            //初始化选项Map
            Map<String,Integer> option = new HashMap<>();
            option.put("A",0);
            option.put("B",0);

            AnalysisReturnVO analysisReturnVO = new AnalysisReturnVO(str,option,0);
            returnVOS.put(str,analysisReturnVO);
        }
        return returnVOS;
    }
}
