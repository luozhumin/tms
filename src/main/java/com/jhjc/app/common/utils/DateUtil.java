package com.jhjc.app.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author: yuanhy
 * Time: 2017-6-28  9:20
 * Description:
 */
public class DateUtil {

    public static int compareDate(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
    }

    public static Date getUpdateTime() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(formatter.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseDateToString(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(data);
    }

    /**
     * 得到当前日期，格式为yyyymmdd.
     *
     * @return String
     */
    public static String getYyyyMMddHHmmss() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        return dateName;
    }

    public static Date getPayTime(Date date){
        Calendar   calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}