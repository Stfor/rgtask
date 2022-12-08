package com.example.rgtask.utils;

import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String, Integer> aa = new HashMap<>();
        aa.put("1",1);
        aa.put("2",2);
        aa.replace("1",3);
        System.out.println(aa);
    }
}
