package com.example.rgtask.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by maple on 2019-08-20.
 */
public class LocalDateUtils {
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    public static LocalDateTime parseDate(Object str, String pattern) {
        if (str == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(str.toString(), dateTimeFormatter);
    }

    public static LocalDateTime parseDate(Object str, int pattern) {
        if (str == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(parsePatterns[pattern]);
        return LocalDateTime.parse(str.toString(), dateTimeFormatter);
    }

}
