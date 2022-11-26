package com.example.rgtask.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalysisUtils {
    private static Map<String, List<String>> xAxis;

    static {
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
        constellation.add("天蝎座");
        constellation.add("射手座");
        constellation.add("摩羯座");
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
        //
    }
}
