package bai.bai.bai.demo.timezone;


import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Linn
 * @date 2018/7/23
 */
public class DateUtilTZ extends DateUtil {

    //======================================================
    // 获取格式化的日期时间字符串
    //======================================================

    /**
     * 获取格式化的日期时间字符串
     *
     * @param timestamp 指定时间的时间戳，传入Null值时使用当前时间
     * @param dateStyle 日期格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeStyle 时间格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeZone  时区名，传入空字符串或Null时使用当前时区（JDK所在时区）
     */
    public static String getDateTimeString(Long timestamp, int dateStyle, int timeStyle, String timeZone) {
        Locale locale = TextUtils.isEmpty(timeZone) ? Locale.getDefault() : TimeZoneEnum.get(timeZone).getLocal();
        TimeZone tz = TextUtils.isEmpty(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        Date date = timestamp == null ? new Date() : new Date(timestamp);
        DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static String getDateTimeString(Long timestamp, int dateTimeStyle, String timeZone) {
        return getDateTimeString(timestamp, dateTimeStyle, dateTimeStyle, timeZone);
    }

    public static String getDateTimeString(Long timestamp, int dateTimeStyle) {
        return getDateTimeString(timestamp, DateFormat.DEFAULT, null);
    }

    public static String getDateTimeString(Long timestamp, String timeZone) {
        return getDateTimeString(timestamp, DateFormat.DEFAULT, timeZone);
    }

    public static String getDateTimeString(int dateTimeStyle, String timeZone) {
        return getDateTimeString(null, dateTimeStyle, timeZone);
    }

    public static String getDateTimeString(Long timestamp) {
        return getDateTimeString(timestamp, DateFormat.DEFAULT, null);
    }

    public static String getDateTimeString(int dateTimeStyle) {
        return getDateTimeString(null, dateTimeStyle, null);
    }

    public static String getDateTimeString(String timeZone) {
        return getDateTimeString(null, DateFormat.DEFAULT, timeZone);
    }

    public static String getDateTimeString() {
        return getDateTimeString(null, DateFormat.DEFAULT, null);
    }

    //======================================================
    // 获取格式化的日期字符串
    //======================================================

    /**
     * 获取格式化的日期字符串
     *
     * @param timestamp 指定时间的时间戳，传入Null值时使用当前时间
     * @param dateStyle 日期格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeZone  时区名，传入空字符串或Null时使用当前时区（JDK所在时区）
     */
    public static String getDateString(Long timestamp, int dateStyle, String timeZone) {
        Locale locale = TextUtils.isEmpty(timeZone) ? Locale.getDefault() : TimeZoneEnum.get(timeZone).getLocal();
        TimeZone tz = TextUtils.isEmpty(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        Date date = timestamp == null ? new Date() : new Date(timestamp);
        DateFormat df = DateFormat.getDateInstance(dateStyle, locale);
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static String getDateString(Long timestamp, int dateStyle) {
        return getDateString(timestamp, dateStyle, null);
    }

    public static String getDateString(Long timestamp, String timeZone) {
        return getDateString(timestamp, DateFormat.DEFAULT, timeZone);
    }

    public static String getDateString(int dateStyle, String timeZone) {
        return getDateString(null, dateStyle, timeZone);
    }

    public static String getDateString(Long timestamp) {
        return getDateString(timestamp, DateFormat.DEFAULT, null);
    }

    public static String getDateString(int dateStyle) {
        return getDateString(null, dateStyle, null);
    }

    public static String getDateString(String timeZone) {
        return getDateString(null, DateFormat.DEFAULT, timeZone);
    }

    public static String getDateString() {
        return getDateString(null, DateFormat.DEFAULT, null);
    }

    //======================================================
    // 获取格式化的时间字符串
    //======================================================

    /**
     * 获取格式化的时间字符串
     *
     * @param timestamp 指定时间的时间戳，传入Null值时使用当前时间
     * @param timeStyle 时间格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeZone  时区名，传入空字符串或Null时使用当前时区（JDK所在时区）
     */
    public static String getTimeString(Long timestamp, int timeStyle, String timeZone) {
        Locale locale = TextUtils.isEmpty(timeZone) ? Locale.getDefault() : TimeZoneEnum.get(timeZone).getLocal();
        TimeZone tz = TextUtils.isEmpty(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        Date date = timestamp == null ? new Date() : new Date(timestamp);
        DateFormat df = DateFormat.getTimeInstance(timeStyle, locale);
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static String getTimeString(Long timestamp, int timeStyle) {
        return getDateString(timestamp, timeStyle, null);
    }

    public static String getTimeString(Long timestamp, String timeZone) {
        return getDateString(timestamp, DateFormat.DEFAULT, timeZone);
    }

    public static String getTimeString(int timeStyle, String timeZone) {
        return getDateString(null, timeStyle, timeZone);
    }

    public static String getTimeString(Long timestamp) {
        return getDateString(timestamp, DateFormat.DEFAULT, null);
    }

    public static String getTimeString(int timeStyle) {
        return getDateString(null, timeStyle, null);
    }

    public static String getTimeString(String timeZone) {
        return getDateString(null, DateFormat.DEFAULT, timeZone);
    }

    public static String getTimeString() {
        return getDateString(null, DateFormat.DEFAULT, null);
    }

    //======================================================
    // 解析日期时间字符串为Date对象
    //======================================================

    /**
     * 解析日期时间字符串为Date对象
     *
     * @param dateTimeString 日期时间字符串
     * @param dateStyle      日期格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeStyle      时间格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeZone       时区名，传入空字符串或Null时使用当前时区（JDK所在时区）
     */
    public static Date parseDateTimeStringToDate(String dateTimeString, int dateStyle, int timeStyle, String timeZone) throws ParseException {
        if (TextUtils.isEmpty(dateTimeString)) {
            throw new IllegalArgumentException("Parameter dateTimeString cannot be empty");
        }
        Locale locale = TextUtils.isEmpty(timeZone) ? Locale.getDefault() : TimeZoneEnum.get(timeZone).getLocal();
        TimeZone tz = TextUtils.isEmpty(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
        df.setTimeZone(tz);
        return df.parse(dateTimeString);
    }

    public static Date parseDateTimeStringToDate(String dateTimeString, int dateTimeStyle, String timeZone) throws ParseException {
        return parseDateTimeStringToDate(dateTimeString, dateTimeStyle, dateTimeStyle, timeZone);
    }

    public static Date parseDateTimeStringToDate(String dateTimeString, int dateTimeStyle) throws ParseException {
        return parseDateTimeStringToDate(dateTimeString, dateTimeStyle, dateTimeStyle, null);
    }

    public static Date parseDateTimeStringToDate(String dateTimeString, String timeZone) throws ParseException {
        return parseDateTimeStringToDate(dateTimeString, DateFormat.DEFAULT, timeZone);
    }

    public static Date parseDateTimeStringToDate(String dateTimeString) throws ParseException {
        return parseDateTimeStringToDate(dateTimeString, DateFormat.DEFAULT, null);
    }

    //======================================================
    // 解析日期字符串为Date对象
    //======================================================

    /**
     * 解析日期字符串为Date对象
     *
     * @param dateString 日期字符串
     * @param dateStyle  日期格式，取值DateFormat.FULL、DateFormat.LONG、DateFormat.MEDIUM、DateFormat.SHORT、DateFormat.DEFAULT
     * @param timeZone   时区名，传入空字符串或Null时使用当前时区（JDK所在时区）
     */
    public static Date parseDateStringToDate(String dateString, int dateStyle, String timeZone) throws ParseException {
        if (TextUtils.isEmpty(dateString)) {
            throw new IllegalArgumentException("Parameter dateString cannot be empty");
        }
        Locale locale = TextUtils.isEmpty(timeZone) ? Locale.getDefault() : TimeZoneEnum.get(timeZone).getLocal();
        TimeZone tz = TextUtils.isEmpty(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        DateFormat df = DateFormat.getDateInstance(dateStyle, locale);
        df.setTimeZone(tz);
        return df.parse(dateString);
    }

    public static Date parseDateStringToDate(String dateString, int dateStyle) throws ParseException {
        return parseDateStringToDate(dateString, dateStyle, null);
    }

    public static Date parseDateStringToDate(String dateString, String timeZone) throws ParseException {
        return parseDateStringToDate(dateString, DateFormat.DEFAULT, timeZone);
    }

    public static Date parseDateStringToDate(String dateString) throws ParseException {
        return parseDateStringToDate(dateString, DateFormat.DEFAULT, null);
    }



}
