@file:JvmName("DateUtil")

package bai.bai.bai.demo.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by daigaokai on 2018/4/11.
 */

const val DATE_PATTERN_01 = "yyyyMMddHHmmssSSS"
const val DATE_PATTERN_02 = "yyyy-MM-dd HH:mm:ss:SSS"
const val DATE_PATTERN_03 = "HH:mm:ss"
const val DATE_PATTERN_04 = "yyyy-MM-dd HH:mm:ss"
const val DATE_PATTERN_05 = "yyyy-MM-dd"
const val DATE_PATTERN_DEFAULT = DATE_PATTERN_02

/**
 * 获取当前时间
 */
fun getCurrentDate(): String {
    return formatDate()
}

/**
 * 获取当前时间戳
 */
fun getCurrentTimeMillis(): Long {
    return System.currentTimeMillis()
}

/**
 * 格式化日期
 * @param time 时间戳
 * @param pattern 日期格式，默认为DATE_PATTERN_DEFAULT
 */
fun formatDate(time: Long = System.currentTimeMillis(), pattern: String = DATE_PATTERN_DEFAULT): String = SimpleDateFormat(pattern, Locale.getDefault()).format(time)

fun format(pattern: String = DATE_PATTERN_DEFAULT): DateFormat = SimpleDateFormat(pattern, Locale.getDefault())
/**
 * 判断日期是否为今天
 */
fun isToday(date: Long): Boolean {
    val today = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis())
    val temp = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(date)
    return today == temp
}

/**
 * UTC时间--->当地时间
 */
fun utc2Local(utcTime: String): String {
    val utcFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = utcFormat.parse(utcTime)
    SimpleDateFormat.getTimeInstance()
    val localFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    localFormat.timeZone = TimeZone.getDefault()
    return localFormat.format(date.time)
}

fun local2UTC(localTime: String): String {
    val localFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    val date = localFormat.parse(localTime)
    localFormat.timeZone = TimeZone.getTimeZone("UTC")
    return localFormat.format(date)
}

fun local2UTC(): String {
    val localFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    localFormat.timeZone = TimeZone.getTimeZone("UTC")
    return localFormat.format(Date())
}

fun startOfDay2UTC(date: Date? = Date()): String {
    val localFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    localFormat.timeZone = TimeZone.getTimeZone("UTC")
    return localFormat.format(calendar.time)
}

fun endOfDay2UTC(date: Date? = Date()): String {
    val localFormat = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.set(Calendar.HOUR, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    localFormat.timeZone = TimeZone.getTimeZone("UTC")
    return localFormat.format(calendar.time)
}

fun getPreviousDay(date: Date): Date {
    var previous: Date
    var calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    previous = calendar.time
    return previous
}

fun getNextDay(date: Date): Date {
    var next: Date
    var calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_MONTH, 1)
    next = calendar.time
    return next
}

/**
 * 获取零时区时间（2018-11-20 03:05:55）
 */
fun getGMTTime(): String {
    val sdf = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    return sdf.format(Date())
}

/**
 * 是否相差多少小时
 */
fun isPassHour(startDate: String, endDate: String, timespan: Int): Boolean {
    val sdf = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    val start = sdf.parse(startDate)
    val end = sdf.parse(endDate)
    val cha = end.time - start.time
    val result = cha * 1.0 / (1000 * 60 * 60)
    return result > timespan
}

/**
 * 某个时间转化成指定时区的时间
 */
fun dateA2dateB(timeDateA: String, dateBZone: String): String {
    val sdf = SimpleDateFormat(DATE_PATTERN_04, Locale.getDefault())
    val date = sdf.parse(timeDateA)
    val cal = Calendar.getInstance()
    cal.time = date
    val timestamp = cal.timeInMillis
    sdf.timeZone = TimeZone.getTimeZone(dateBZone)
    return sdf.format(Date(timestamp))
}

