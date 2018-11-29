package bai.bai.bai.demo.timezone;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	/**
	 * 获取DateFormat
	 * 
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 */
	public static DateFormat dateFormat(int dateFormat) {
		switch (dateFormat) {
		case 1:
			return new SimpleDateFormat("yyyyMMdd");
		case 2:
			return new SimpleDateFormat("yyyy-MM-dd");
		case 3:
			return new SimpleDateFormat("yyyy/MM/dd");
		case 4:
			return new SimpleDateFormat("yyyy年MM月dd日");
		default:
			return new SimpleDateFormat("yyyyMMdd");
		}
	}
	
	/**
	 * 获取DateFormat
	 * 
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 */
	public static DateFormat dateTimeFormat(int dateFormat) {
		switch (dateFormat) {
		case 1:
			return new SimpleDateFormat("yyyyMMddHHmmss");
		case 2:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		case 3:
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		case 4:
			return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		default:
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	}
	
	/**
	 * 获取当前日期字符串
	 * 
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 * @return
	 */
	public static String getCurrDateStr(int dateFormat) {
		return dateFormat(dateFormat).format(new Date());
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 * @return
	 */
	public static String date2string(Date date, int dateFormat) {
		return dateFormat(dateFormat).format(date);
	}

	/**
	 * 格式化日期字符串
	 * @param date
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 * @return
	 */
	public static Date string2date(String date, int dateFormat) throws ParseException {
		return dateFormat(dateFormat).parse(date);
	}
	
	/**
	 * 日期比较
	 * @param time1 	起始日期
	 * @param time2 	结束日期
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 * @return	     起始日期小于结束日期返回true，否则返回false
	 * @throws ParseException 
	 */
	public static boolean isBeforeDate(String time1, String time2, int dateFormat) throws ParseException {
		Date date1 = dateFormat(dateFormat).parse(time1);
		Date date2 = dateFormat(dateFormat).parse(time2);
		return date1.before(date2);
	}
	
	/**
	 * 获取指定日期之前或之后多少天的时间字符串
	 * 
	 * @param date		待加、减天数
	 * @param day		加、减天数
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMdd
	 *            dateFormat = 2 -- yyyy-MM-dd
	 *            dateFormat = 3 -- yyyy/MM/dd
	 *            dateFormat = 4 -- yyyy年MM月dd日
	 * @return
	 */
	public static String rollDay(Date date, int day, int dateFormat) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day);
		Date d = c.getTime();
		return dateFormat(dateFormat).format(d);
	}
	
	/**
	 * 获取当前时间字符串
	 * 
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String getCurrDateTimeStr(int dateFormat) {
		return dateTimeFormat(dateFormat).format(new Date());
	}
	
	/**
	 * 格式化时间字符串
	 * 
	 * @param dateTime
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static Date string2datetime(String dateTime, int dateFormat) throws ParseException {
		return dateTimeFormat(dateFormat).parse(dateTime);
	}

	/**
	 * 格式化时间
	 * 
	 * @param dateTime
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String datetime2string(Date dateTime, int dateFormat) {
		return dateTimeFormat(dateFormat).format(dateTime);
	}

	/**
	 * 时间比较
	 * @param time1 	起始时间
	 * @param time2 	结束时间
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return	     起始时间小于结束时间返回true，否则返回false
	 * @throws ParseException 
	 */
	public static boolean isBeforeDateTime(String time1, String time2, int dateFormat) throws ParseException {
		Date date1 = dateTimeFormat(dateFormat).parse(time1);
		Date date2 = dateTimeFormat(dateFormat).parse(time2);
		return date1.before(date2);
	}
	
	/**
	 * 获取指定时间之前或之后多少秒的时间字符串
	 * @param dateTime	待加、减时间
	 * @param second	加、减秒数
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String rollSecond(Date dateTime, int second, int dateFormat) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateTime);
		c.add(Calendar.SECOND, second);
		Date d = c.getTime();
		return dateTimeFormat(dateFormat).format(d);
	}
	
	/**
	 * 获取指定时间之前或之后多少分钟的时间字符串
	 * @param dateTime	待加、减时间
	 * @param min		加、减分钟数
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String rollMinute(Date dateTime, int min, int dateFormat) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateTime);
		c.add(Calendar.MINUTE, min);
		Date d = c.getTime();
		return dateTimeFormat(dateFormat).format(d);
	}
	
	/**
	 * 获取指定时间之前或之后多少天的时间字符串
	 * 
	 * @param dateTime	待加、减天数
	 * @param day		加、减天数
	 * @param dateFormat
	 *            dateFormat = 1 -- yyyyMMddHHmmss
	 *            dateFormat = 2 -- yyyy-MM-dd HH:mm:ss
	 *            dateFormat = 3 -- yyyy/MM/dd HH:mm:ss
	 *            dateFormat = 4 -- yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String rollDateTimeDay(Date dateTime, int day, int dateFormat) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateTime);
		c.add(Calendar.DAY_OF_MONTH, day);
		Date d = c.getTime();
		return dateTimeFormat(dateFormat).format(d);
	}
	
	/**
	 * 获得指定日期的格式
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String getAppointDate(Date date, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}
	
	/**
	 * 获取当前年
	 * @return
	 */
	public static String getYear() {
		DateFormat format = new SimpleDateFormat("yyyy");
		return format.format(new Date());
	}
	
	/**
	 * 获取当前月
	 * @return
	 */
	public static String getMonth() {
		DateFormat format = new SimpleDateFormat("MM");
		return format.format(new Date());
	}
	
	/**
	 * 获取今天几号
	 * @return
	 */
	public static String getToday() {
		DateFormat format = new SimpleDateFormat("dd");
		return format.format(new Date());
	}
	
	/**
	 * 获取当前时间，格式：HH:mm:ss
	 * @return
	 */
	public static String getTime() {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(new Date());
	}
	
	/**
	 * 获取今天是星期几
	 * @return
	 */
	public static String getDayOfWeek() {
		DateFormat format = new SimpleDateFormat("EEEE");
		return format.format(new Date());
	}

	/**
	 * 获得指定日期的数值
	 * 
	 * @param date
	 * @return
	 */
	public static GregorianCalendar dateToCal(Date date) {
		return new GregorianCalendar(
				Integer.parseInt(getAppointDate(date, "yyyy")), 
				Integer.parseInt(getAppointDate(date, "MM")) - 1, 
				Integer.parseInt(getAppointDate(date, "dd")), 
				Integer.parseInt(getAppointDate(date, "HH")), 
				Integer.parseInt(getAppointDate(date, "mm")), 
				Integer.parseInt(getAppointDate(date, "ss")));
	}
}