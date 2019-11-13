package com.datasource.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 2017年7月31日 下午4:04:22
 */
public class DateUtil {
    public static final long MILLIS_PER_MINUTE = 60 * 1000;
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyyMMddAPP = "yyyy/MM/dd";
    public static final String yyyyMMddHistory = "yyyy-MM-dd 00:00:00";
    public static final String yyyyMM = "yyyy-MM";
    public static final String yyyyMMAPP= "yyyy/MM";
    public static final String yyyyMMHistory = "yyyy-MM-01 00:00:00";

    /**
     * 获取给定时间date的前day天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateBeforeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        Date _date = calendar.getTime();
        return _date;
    }

    /**
     * 获取给定时间后day天
     *
     * @param date
     * @param day
     * @return Date
     * each engineer has a duty to keep the code elegant
     */
    public static Date getDateAfterDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date _date = calendar.getTime();
        return _date;
    }

    /**
     * 获取给定时间前hours
     *
     * @param date
     * @return Date
     * each engineer has a duty to keep the code elegant
     */
    public static Date getDateBeforeHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -hours);
        Date _date = calendar.getTime();
        return _date;
    }

    /**
     * 获取给定时间后hours
     *
     * @param date
     * @return Date
     * each engineer has a duty to keep the code elegant
     */
    public static Date getDateAfterHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        Date _date = calendar.getTime();
        return _date;
    }

    /**
     * 获取指定时间的日期
     * 2013-12-25 下午4:38:51
     *
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return Date
     * each engineer has a duty to keep the code elegant
     */
    public static Date getDateSetTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取指定格式时间字符串
     * 2013-12-18 下午3:30:06
     *
     * @param pattern 自定义格式 yyyy-MM-dd HH:mm:ss
     * @param date    Date类型日期
     * @return String 字符串格式日期
     * each engineer has a duty to keep the code elegant
     */
    public static String getStrDateFormat(String pattern, Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.applyPattern(pattern);
        String dateStr = fmt.format(date);
        return dateStr;
    }

    /**
     * 获取指定格式时间字符串
     * 2013-12-18 下午3:30:06
     *
     * @param pattern 自定义格式 yyyy-MM-dd HH:mm:ss
     * @param dateStr String 日期格式字符
     * @return Date类型日期
     * each engineer has a duty to keep the code elegant
     * @throws ParseException
     */
    public static Date getDateFormat(String pattern, String dateStr) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat();
        if (StringUtil.isEmptyTrim(pattern) && dateStr != null) {
            if (dateStr.matches("[0-9]{13}")) {
                return new Date(Long.parseLong(dateStr));
            } else {
                dateStr = dateStr.replace("/", "-");
                if (dateStr.length() == 19) {
                    fmt.applyPattern(yyyyMMddHHmmss);
                } else if (dateStr.length() == 16) {
                    fmt.applyPattern(yyyyMMddHHmm);
                } else if (dateStr.length() == 13) {
                    fmt.applyPattern(yyyyMMddHH);
                } else {
                    fmt.applyPattern(yyyyMMdd);
                }
            }
        } else {
            fmt.applyPattern(pattern);
        }
        Date date = fmt.parse(dateStr);
        return date;
    }

    /**
     * 根据字符串分析转换为日期类型
     * 2017年8月16日 下午2:28:24
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateAuto(String dateStr) throws ParseException {
        return getDateFormat(null, dateStr);
    }

    /**
     * 获取系统日期字符串
     * 2013-12-18 下午3:31:04
     *
     * @param pattern
     * @return String
     * each engineer has a duty to keep the code elegant
     */
    public static String getStrDateFormat(String pattern) {
        return getStrDateFormat(pattern, new Date());
    }

    /**
     * 获取param月前/后的日期
     * 2013-12-18 下午3:53:26
     *
     * @param date  基准日期
     * @param month 传正数/负数加减月数
     * @return Date
     * each engineer has a duty to keep the code elegant
     */
    public static Date getDateByMonth(Date date, int month) {
        Calendar caldate = Calendar.getInstance();
        caldate.add(Calendar.MONTH, month);
        return caldate.getTime();
    }

    /**
     * 将给定的时间 转换成Format的样式 输出
     *
     * @param input
     * @param fmtPattern
     * @return String
     * each engineer has a duty to keep the code elegant
     */
    public static String getString(Date input, String fmtPattern) {
        String strReturn;
        if (input == null) {
            strReturn = "3000-1-1";
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat(fmtPattern);
            strReturn = fmt.format(input.getTime());
        }
        return strReturn;
    }

    /**
     * 获取指定日期的0点0分0秒
     *
     * @param date
     * @return
     */
    public static Date getZeroHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期指定小时点0分0秒
     *
     * @param date
     * @return
     */
    public static Date getZeroMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @return
     * @author yexiuli
     * 2017年8月21日 上午11:46:39
     */
    public static Date getDateBeforeMinute(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minutes);
        Date _date = calendar.getTime();
        return _date;
    }
}
