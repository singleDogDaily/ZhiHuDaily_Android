package com.kcode.zhihudaily.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caik on 2016/11/2.
 */

public class DateUtils {

    private final static String DEFAULT_FORMAT = "yyyyMMdd";
    private final static String MMDD_FORMAT = "MM月dd日";

    public static String getToday(){
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(calendar.getTime());
    }

    public static String long2Str(long time){
        DateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(new Date(time));
    }

    public static String long2MMdd(long time){
        DateFormat format = new SimpleDateFormat(MMDD_FORMAT);
        return format.format(new Date(time));
    }

    public static long str2Long(String date){
        DateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        try {
            Date date1 = format.parse(date);
            return date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
