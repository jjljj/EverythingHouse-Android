package com.crossy.app.everythinghouse.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ljj on 2014/7/12.
 * 时间格式转换工具
 */
public class TimeConvertUtil {
    /*
    From:0000-00-00 00:00:00
    To:TimeInMillis
     */
    public static long getTimeInMillisFromString(String time) {
        Calendar calendar = getCalendarTime(time);
        return calendar.getTimeInMillis();
    }

    /*
    From:0000-00-00 00:00:00
    To:Calendar
     */
    public static Calendar getCalendarTime(String time){
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5, 7)) - 1;//不知道为什么要减一
        int day = Integer.parseInt(time.substring(8, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int minute = Integer.parseInt(time.substring(14, 16));
        //int second = Integer.parseInt(time.substring(17,19));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    /*
    From:int[5]
    To:0000-00-00 00:00:00
     */
    public static String getTimeInString(int times[]){
        Calendar calendar = Calendar.getInstance();
        calendar.set(times[0], times[1], times[2], times[3], times[4]);//calendar.set(year, month, day, hour, minute);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(calendar.getTime());
    }

    /*
    计算时间差
    From:0000-00-00 00:00:00 0000-00-00 00:00:00
    TO:xx年xx月xx天。。。
     */
    public static String countDateDistance(String fromTime, String toTime){
        long timeLong = getTimeDistance(fromTime,toTime);
        if(timeLong<=0){
            return "时间已过";
        }else if(timeLong<60*1000){
            return "不到一分钟";
        }else if(timeLong<60*60*1000){
            return timeLong/(60*1000) + "分钟";
        }else if(timeLong<24*60*60*1000){
            return timeLong/(60*60*1000)+"小时"+(timeLong%(60*60*1000))/(60*1000)+"分钟";
        }else {
            return timeLong/(24*60*60*1000)+"天"+(timeLong%(24*60*60*1000))/(60*60*1000)+"小时"+(timeLong%(60*60*1000))/(60*1000)+"分钟";
        }
    }

    /*
    计算时间差
    From:0000-00-00 00:00:00 0000-00-00 00:00:00
    TO:long
     */
    public static long getTimeDistance(String fromTime, String toTime){
        Calendar fromCalendar = getCalendarTime(fromTime);
        Calendar toCalendar = getCalendarTime(toTime);

        return toCalendar.getTime().getTime() - fromCalendar.getTime().getTime();
    }

    /*
    计算时间差
    From:0000-00-00 00:00:00 到现在
    TO:long
     */
    public static long getTimeDistance(String toTime){
        Date fromCalendar = new Date(System.currentTimeMillis());
        Calendar toCalendar = getCalendarTime(toTime);

        return toCalendar.getTime().getTime() - fromCalendar.getTime();
    }

}
