/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.math.BigDecimal
 */
package lbchs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class 大数运算 {
    public static String 大数相加(String string, String string2) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        BigDecimal bigDecimal3 = bigDecimal.add(bigDecimal2);
        return bigDecimal3.toPlainString();
    }

    public static String 大数相减(String string, String string2) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        BigDecimal bigDecimal3 = bigDecimal.subtract(bigDecimal2);
        return bigDecimal3.toPlainString();
    }

    public static String 大数相乘(String string, String string2) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        BigDecimal bigDecimal3 = bigDecimal.multiply(bigDecimal2);
        return bigDecimal3.toPlainString();
    }

    public static String 大数相除(String string, String string2, int n) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        BigDecimal bigDecimal3 = bigDecimal.divide(bigDecimal2, n, RoundingMode.HALF_UP);
        return bigDecimal3.toPlainString();
    }

    public static int 大数比较(String string, String string2) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        return bigDecimal.compareTo(bigDecimal2);
    }

    public static String 大数求余(String string, String string2) {
        BigDecimal bigDecimal = new BigDecimal(string);
        BigDecimal bigDecimal2 = new BigDecimal(string2);
        BigDecimal bigDecimal3 = bigDecimal.remainder(bigDecimal2);
        return bigDecimal3.toPlainString();
    }

    public static String 科学转普通计数(String string) {
        BigDecimal bigDecimal = new BigDecimal(string);
        return bigDecimal.toPlainString();
    }

    public void 初始化事件() {
    }
}

