/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.Color
 *  java.io.UnsupportedEncodingException
 *  java.lang.CharSequence
 *  java.lang.Character
 *  java.lang.IndexOutOfBoundsException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package lbchs;

import android.annotation.SuppressLint;
import android.graphics.Color;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 转换操作 {
    public static String 转换时长(int n) {
        if (n / 1000 % 60 < 10) {
            return n / 1000 / 60 + ":0" + n / 1000 % 60;
        }
        return n / 1000 / 60 + ":" + n / 1000 % 60;
    }

    @SuppressLint("DefaultLocale")
    public static String 转换文件大小(int n) {
        String string = 全局方法类.到文本(n) + "B";
        if (n > 0x100000) {
            string = String.format("%.2f MB", Long.valueOf(n).doubleValue() / 1048576.0);
        }
        return string;
    }

    public static int 转换颜色(String string) {
        return Color.parseColor(string);
    }

    public static String 中文转unicode(String string) {
        char[] cArray = string.toCharArray();
        StringBuilder string2 = new StringBuilder();
        for (char c : cArray) {
            String string3 = Integer.toHexString(c);
            if (string3.length() <= 2) {
                string3 = "00" + string3;
            }
            string2.append("\\u").append(string3);
        }
        return string2.toString();
    }

    public static String unicode转中文(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            char c = (char) Integer.parseInt(Objects.requireNonNull(matcher.group(2)), 16);
            string = string.replace(Objects.requireNonNull(matcher.group(1)), String.valueOf(c));
        }
        return string;
    }

    public static int 字符转代码(String string) {
        try {
            return string.charAt(0);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new RuntimeException("字符转代码( 字符长度应该大于等于1");
        }
    }

    public static String 代码转字符(int n) {
        return Character.toString((char) n);
    }

    public static String 到十六进制(int n) {
        String string = Integer.toHexString(n);
        if (string.length() < 2) {
            string = "0" + string;
        }
        return string;
    }

    public static int 到十进制(String string) {
        if (!"".equals(string)) {
            return Integer.valueOf(string, 16);
        }
        return 0;
    }

    public static String 到二进制(int n) {
        return Integer.toBinaryString(n);
    }

    public static String 文本到二进制(String string) {
        char[] cArray = string.toCharArray();
        StringBuilder string2 = new StringBuilder();
        for (char c : cArray) {
            string2.append(Integer.toBinaryString(c)).append(" ");
        }
        return string2.toString();
    }

    public static String 字节到文本(byte[] byArray) {
        return new String(byArray);
    }

    public static String 字节到文本(byte[] byArray, String string) {
        try {
            return new String(byArray, string);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static byte[] 文本到字节(String string) {
        return string.getBytes();
    }

    public static byte[] 文本到字节(String string, String string2) {
        try {
            return string.getBytes(string2);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException("文本到字节( 解码错误");
        }
    }

    public static int 字节到整数(byte[] byArray) {
        return byArray[0] & 0xFF | byArray[1] << 8 & 0xFF00 | byArray[2] << 24 >>> 8 | byArray[3] << 24;
    }

    public static byte[] 整数到字节(int n) {
        return new byte[]{(byte) (n & 0xFF), (byte) (n >> 8 & 0xFF), (byte) (n >> 16 & 0xFF), (byte) (n >>> 24)};
    }

    public static long 字节到长整数(byte[] byArray) {
        return (long) (byArray[0] & 0xFF) << 56 | (long) (byArray[1] & 0xFF) << 48 | (long) (byArray[2] & 0xFF) << 40 | (long) (byArray[3] & 0xFF) << 32 | (long) (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | (byArray[7] & 0xFF);
    }

    public static byte[] 长整数到字节(long l) {
        return new byte[]{(byte) (l >> 56), (byte) (l >> 48), (byte) (l >> 40), (byte) (l >> 32), (byte) (l >> 24), (byte) (l >> 16), (byte) (l >> 8), (byte) l};
    }

    public static String 数值到金额(double d) {
        if (d > 1.0E18 || d < -1.0E18) {
            return "";
        }
        String[] stringArray = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        boolean bl = false;
        if (d < 0.0) {
            bl = true;
            d *= -1.0;
        }
        long l = Math.round(d * 100.0);
        int n = (int) (l % 10L);
        int n2 = (int) ((l /= 10L) % 10L);
        l /= 10L;
        int[] nArray = new int[20];
        int n3 = 0;
        int n4 = 0;
        while (l != 0L) {
            int n5;
            nArray[n4] = (int) (l % 10000L);
            ++n3;
            l /= 10000L;
            ++n4;
        }
        n4 = 1;
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < n3; ++i) {
            String string2 = 转换操作.partTranslate(nArray[i]);
            if (i % 2 == 0) {
                n4 = "".equals(string2) ? 1 : 0;
            }
            if (i != 0) {
                if (i % 2 == 0) {
                    string.insert(0, "亿");
                } else if ("".equals(string2) && n4 == 0) {
                    string.insert(0, "零");
                } else {
                    if (nArray[i - 1] < 1000 && nArray[i - 1] > 0) {
                        string.insert(0, "零");
                    }
                    string.insert(0, "万");
                }
            }
            string.insert(0, string2);
        }
        if ("".equals(string.toString())) {
            string = new StringBuilder(stringArray[0]);
        } else if (bl) {
            string.insert(0, "负");
        }
        string.append("元");
        string = new StringBuilder(n == 0 && n2 == 0 ? string + "整" : (n == 0 ? string + stringArray[n2] + "角" : (n2 == 0 ? string + "零" + stringArray[n] + "分" : string + stringArray[n2] + "角" + stringArray[n] + "分")));
        return string.toString();
    }

    public static String 字节集到十六进制(byte[] byArray) {
        byte[] byArray2 = "0123456789ABCDEF".getBytes();
        byte[] byArray3 = new byte[2 * byArray.length];
        for (int i = 0; i < byArray.length; ++i) {
            byArray3[2 * i] = byArray2[byArray[i] >> 4 & 0xF];
            byArray3[2 * i + 1] = byArray2[byArray[i] & 0xF];
        }
        return new String(byArray3);
    }

    public static byte[] 十六进制到字节集(String string) {
        byte[] byArray = new byte[string.length() / 2];
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            char c = string.charAt(n++);
            char c2 = string.charAt(n++);
            byArray[i] = (byte) (转换操作.parse(c) << 4 | 转换操作.parse(c2));
        }
        return byArray;
    }

    private static String partTranslate(int n) {
        if (n < 0 || n > 10000) {
            return "";
        }
        String[] stringArray = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] stringArray2 = new String[]{"", "拾", "佰", "仟"};
        int n2 = n;
        String string = Integer.valueOf(n).toString();
        int n3 = string.length();
        boolean bl = true;
        StringBuilder string2 = new StringBuilder();
        for (int i = 0; i < n3 && n2 != 0; n2 /= 10, ++i) {
            int n4 = n2 % 10;
            if (n4 == 0) {
                if (!bl) {
                    string2.insert(0, "零");
                }
                bl = true;
                continue;
            }
            string2.insert(0, stringArray[n4] + stringArray2[i]);
            bl = false;
        }
        return string2.toString();
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return c - 97 + 10 & 0xF;
        }
        if (c >= 'A') {
            return c - 65 + 10 & 0xF;
        }
        return c - 48 & 0xF;
    }

    public void 初始化事件() {
    }
}

