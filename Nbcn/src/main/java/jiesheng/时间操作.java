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
package jiesheng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        return new SimpleDateFormat(string).format((Object) System.currentTimeMillis());
    }

    public static String 时间戳到文本(long l, String string) {
        return new SimpleDateFormat(string).format((Object) l);
    }

    public static long 取时间戳() {
        return System.currentTimeMillis();
    }

    public static String 取时间戳文本() {
        return new SimpleDateFormat().format((Object) System.currentTimeMillis());
    }

    public static long 时间文本到时间戳(String string, String string2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string);
        try {
            return simpleDateFormat.parse(string2).getTime();
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0L;
        }
    }

    public static Object 到时间(String string) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(string));
        } catch (ParseException parseException) {
            try {
                gregorianCalendar.setTime(new SimpleDateFormat("yyyy/MM/dd").parse(string));
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
        return DateFormat.getDateTimeInstance((int) 1, (int) 1).format(((Calendar) object).getTime());
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
        return String.format((String) "%1$tB", (Object[]) new Object[]{(Calendar) object});
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
        return String.format((String) "%1$tA", (Object[]) new Object[]{(Calendar) object});
    }

    public static int 取年份(Object object) {
        return ((Calendar) object).get(1);
    }

    public static String 取现行时间文本(String string) {
        return new SimpleDateFormat("HH" + string + "mm" + string + "ss").format(new Date(System.currentTimeMillis()));
    }

    public static String 取现行日期文本(String string) {
        return new SimpleDateFormat("yyyy" + string + "MM" + string + "dd").format(new Date(System.currentTimeMillis()));
    }

    public static long 取时间间隔(Object object, Object object2) {
        return ((Calendar) object).getTimeInMillis() - ((Calendar) object2).getTimeInMillis();
    }

    public static String 取指定时间戳(Object object, int n) {
        String string = String.valueOf((long) ((Calendar) object).getTimeInMillis());
        if (n == 2) {
            return string.substring(0, 10);
        }
        return string;
    }

    public static String 时间戳到时间文本(String string) {
        Long l;
        if (string == null || string.equals((Object) "")) {
            return "";
        }
        if (string.length() == 10) {
            l = Long.parseLong((String) string) * 1000L;
        } else {
            if (string.length() != 13) {
                return "";
            }
            l = Long.parseLong((String) string);
        }
        String string2 = "";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l.longValue()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return string2;
        }
    }

    public void 初始化事件() {
    }
}

