/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Exception
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.System
 *  java.text.DateFormat
 *  java.text.ParseException
 *  java.text.SimpleDateFormat
 *  java.util.Calendar
 *  java.util.Date
 *  java.util.GregorianCalendar
 */
package lbchs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

public class 时间操作 {
    public static final int DATE_APRIL = 3;
    public static final int DATE_AUGUST = 7;
    public static final int DATE_DAY = 5;
    public static final int DATE_DECEMBER = 11;
    public static final int DATE_FEBRUARY = 1;
    public static final int DATE_FRIDAY = 6;
    public static final int DATE_HOUR = 11;
    public static final int DATE_JANUARY = 0;
    public static final int DATE_JULY = 6;
    public static final int DATE_JUNE = 5;
    public static final int DATE_MARCH = 2;
    public static final int DATE_MAY = 4;
    public static final int DATE_MINUTE = 12;
    public static final int DATE_MONDAY = 2;
    public static final int DATE_MONTH = 2;
    public static final int DATE_NOVEMBER = 10;
    public static final int DATE_OCTOBER = 9;
    public static final int DATE_SATURDAY = 7;
    public static final int DATE_SECOND = 13;
    public static final int DATE_SEPTEMBER = 8;
    public static final int DATE_SUNDAY = 1;
    public static final int DATE_THURSDAY = 5;
    public static final int DATE_TUESDAY = 3;
    public static final int DATE_WEDNESDAY = 4;
    public static final int DATE_WEEK = 3;
    public static final int DATE_YEAR = 1;

    public static String 取格式时间(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat(string, Locale.getDefault());
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String 时间戳到文本(long l, String string) {
        SimpleDateFormat sdf = new SimpleDateFormat(string, Locale.getDefault());
        return sdf.format(l);
    }

    public static long 取时间戳() {
        return System.currentTimeMillis();
    }

    public static String 取时间戳文本() {
        return new SimpleDateFormat(null,Locale.getDefault()).format(System.currentTimeMillis());
    }

    public static long 时间文本到时间戳(String string, String string2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string,Locale.getDefault());
        try {
            return Objects.requireNonNull(simpleDateFormat.parse(string2)).getTime();
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0L;
        }
    }

    public static Object 到时间(String string) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(Objects.requireNonNull(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(string)));
        } catch (ParseException parseException) {
            try {
                gregorianCalendar.setTime(Objects.requireNonNull(new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(string)));
            } catch (ParseException parseException2) {
                throw new RuntimeException("到时间( 参数错误");
            }
        }
        return gregorianCalendar;
    }

    public static Object 增减时间(Object object, int n, int n2) {
        int n3 = 0;
        switch (n) {
            case 1: {
                n3 = 1;
                break;
            }
            case 2: {
                n3 = 2;
                break;
            }
            case 3: {
                n3 = 5;
                break;
            }
            case 4: {
                n3 = 3;
                break;
            }
            case 5: {
                n3 = 11;
                break;
            }
            case 6: {
                n3 = 12;
                break;
            }
            case 7: {
                n3 = 13;
            }
        }
        ((Calendar) object).add(n3, n2);
        return object;
    }

    public static int 取日(Object object) {
        return ((Calendar) object).get(5);
    }

    public static String 时间到文本(Object object) {
        return DateFormat.getDateTimeInstance(1, 1).format(((Calendar) object).getTime());
    }

    public static int 取小时(Object object) {
        return ((Calendar) object).get(11);
    }

    public static int 取分钟(Object object) {
        return ((Calendar) object).get(12);
    }

    public static int 取月份(Object object) {
        return ((Calendar) object).get(2) + 1;
    }

    public static String 取月份名称(Object object) {
        return String.format("%1$tB", (Calendar)object);
    }

    public static Object 取现行时间对象() {
        return new GregorianCalendar();
    }

    public static int 取秒(Object object) {
        return ((Calendar) object).get(13);
    }

    public static long 取启动时间() {
        return System.currentTimeMillis();
    }

    public static int 取星期几(Object object) {
        return ((Calendar) object).get(7);
    }

    public static String 取星期几名称(Object object) {
        return String.format("%1$tA", (Calendar)object);
    }

    public static int 取年份(Object object) {
        return ((Calendar) object).get(1);
    }

    public static String 取现行时间文本(String string) {
        return new SimpleDateFormat("HH" + string + "mm" + string + "ss",Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }

    public static String 取现行日期文本(String string) {
        return new SimpleDateFormat("yyyy" + string + "MM" + string + "dd",Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }

    public static long 取时间间隔(Object object, Object object2) {
        return ((Calendar) object).getTimeInMillis() - ((Calendar) object2).getTimeInMillis();
    }

    public static String 取指定时间戳(Object object, int n) {
        String string = String.valueOf(((Calendar) object).getTimeInMillis());
        if (n == 2) {
            return string.substring(0, 10);
        }
        return string;
    }

    public static String 时间戳到时间文本(String 时间戳) {
        if (时间戳 == null || 时间戳.equals("")) {
            return "";
        }
        long stamp;
        if (时间戳.length() == 10) {
            stamp = Long.parseLong(时间戳) * 1000;
        } else if (时间戳.length() != 13) {
            return "";
        } else {
            stamp = Long.parseLong(时间戳);
        }
        String strs = "";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(new Date(stamp));
        } catch (Exception e) {
            e.printStackTrace();
            return strs;
        }
    }

    public void 初始化事件() {
    }
}

