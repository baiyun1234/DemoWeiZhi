package bai.bai.bai.demo.timezone;


import android.text.TextUtils;

import java.util.Locale;

/**
 * 时区地区对照表（需验证与完善）
 *
 * @author Linn
 * @date 2018/7/18
 */
public enum TimeZoneEnum {

    //==================================================
    // 加拿大时区
    //==================================================
    AMERICA_DAWSON("America/Dawson", Locale.CANADA),
    AMERICA_VANCOUVER("America/Vancouver", Locale.CANADA),
    AMERICA_WHITEHORSE("America/Whitehorse", Locale.CANADA),
    AMERICA_CAMBRIDGE_BAY("America/Cambridge_Bay", Locale.CANADA),
    AMERICA_CRESTON("America/Creston", Locale.CANADA),
    AMERICA_DAWSON_CREEK("America/Dawson_Creek", Locale.CANADA),
    AMERICA_EDMONTON("America/Edmonton", Locale.CANADA),
    AMERICA_FORT_NELSON("America/Fort_Nelson", Locale.CANADA),
    AMERICA_INUVIK("America/Inuvik", Locale.CANADA),
    AMERICA_YELLOWKNIFE("America/Yellowknife", Locale.CANADA),
    AMERICA_RAINY_RIVER("America/Rainy_River", Locale.CANADA),
    AMERICA_RANKIN_INLET("America/Rankin_Inlet", Locale.CANADA),
    AMERICA_REGINA("America/Regina", Locale.CANADA),
    AMERICA_RESOLUTE("America/Resolute", Locale.CANADA),
    AMERICA_SWIFT_CURRENT("America/Swift_Current", Locale.CANADA),
    AMERICA_WINNIPEG("America/Winnipeg", Locale.CANADA),
    AMERICA_ATIKOKAN("America/Atikokan", Locale.CANADA),
    AMERICA_IQALUIT("America/Iqaluit", Locale.CANADA),
    AMERICA_NIPIGON("America/Nipigon", Locale.CANADA),
    AMERICA_PANGNIRTUNG("America/Pangnirtung", Locale.CANADA),
    AMERICA_THUNDER_BAY("America/Thunder_Bay", Locale.CANADA),
    AMERICA_TORONTO("America/Toronto", Locale.CANADA),
    AMERICA_BLANC_SABLON("America/Blanc-Sablon", Locale.CANADA),
    AMERICA_GLACE_BAY("America/Glace_Bay", Locale.CANADA),
    AMERICA_GOOSE_BAY("America/Goose_Bay", Locale.CANADA),
    AMERICA_HALIFAX("America/Halifax", Locale.CANADA),
    AMERICA_MONCTON("America/Moncton", Locale.CANADA),
    AMERICA_ST_JOHNS("America/St_Johns", Locale.CANADA),

    //==================================================
    // 中国时区
    //==================================================
    ASIA_SHANGHAI("Asia/Shanghai", Locale.CHINA),
    ASIA_CHONGQING("Asia/Chongqing", Locale.CHINA),
    ASIA_HONG_KONG("Asia/Hong_Kong", Locale.TRADITIONAL_CHINESE),
    ASIA_TAIPEI("Asia/Taipei", Locale.TRADITIONAL_CHINESE),

    //==================================================
    // 其他时区
    //==================================================
    UTC("UTC", Locale.getDefault());

    private String name;    //时区全名
    private Locale local;   //时区对应地区

    TimeZoneEnum(String name, Locale local) {
        this.name = name;
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public Locale getLocal() {
        return local;
    }

    public static TimeZoneEnum get(String timeZone) {
        if (TextUtils.isEmpty(timeZone)) {
            throw new IllegalArgumentException("Parameter timeZone cannot be empty");
        }
        for (TimeZoneEnum tze : values()) {
            if (timeZone.equals(tze.name)) {
                return tze;
            }
        }
        throw new RuntimeException("Not found time zone named " + timeZone);
    }

}
