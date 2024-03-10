/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 */
package lbchs;

public class 文本操作 {
    public static int 寻找文本(String string, String string2, int n) {
        if (n < 0 || n > string.length() || "".equals(string) || "".equals(string2)) {
            return -1;
        }
        return string.indexOf(string2, n);
    }

    public static int 倒找文本(String string, String string2, int n) {
        if (n < 0 || n > string.length() || "".equals(string) || "".equals(string2)) {
            return -1;
        }
        return string.lastIndexOf(string2, n);
    }

    public static String 到大写(String string) {
        return string.toUpperCase();
    }

    public static String 到小写(String string) {
        return string.toLowerCase();
    }

    public static char 取指定字符(String string, int n) {
        return string.charAt(n);
    }

    public static String 取文本左边(String string, int n) {
        if ("".equals(string) || n <= 0) {
            return "";
        }
        return n <= string.length() ? string.substring(0, n) : string;
    }

    public static String 取文本右边(String string, int n) {
        if ("".equals(string) || n <= 0) {
            return "";
        }
        return n <= string.length() ? string.substring(string.length() - n) : string;
    }

    public static String 取文本中间(String string, int n, int n2) {
        if ("".equals(string) || n < 0 || n2 <= 0 || n > string.length()) {
            return "";
        }
        int n3 = n + n2;
        if (n3 > string.length()) {
            n3 = string.length();
        }
        return string.substring(n, n3);
    }

    public static int length(String string) {
        return string.length();
    }

    public static int 取文本长度2(String string) {
        return string.getBytes().length;
    }

    public static String 删首尾空(String string) {
        return string.trim();
    }

    public static String 删首空(String string) {
        char[] cArray = string.toCharArray();
        int n = 0;
        int n2 = 0;
        while (n2 < cArray.length && cArray[n2] == ' ') {
            ++n2;
            ++n;
        }
        if (n > 0) {
            return new String(cArray, n, cArray.length - n);
        }
        return string;
    }

    public static String 删尾空(String string) {
        char[] cArray = string.toCharArray();
        int n = 0;
        int n2 = cArray.length - 1;
        while (n2 > 0 && cArray[n2] == ' ') {
            --n2;
            ++n;
        }
        if (n > 0) {
            return new String(cArray, 0, cArray.length - n);
        }
        return string;
    }

    public static String 子文本替换(String string, String string2, String string3) {
        if ("".equals(string2) || "".equals(string)) {
            return "";
        }
        return string.replaceAll("\\Q" + string2 + "\\E", string3);
    }

    public static String 子文本替换(String string, int n, int n2, String string2) {
        if ("".equals(string) || n < 0 || n > string.length() || n2 < n || n2 > string.length()) {
            return "";
        }
        return string.substring(0, n) + string2 + string.substring(n2 + 1);
    }

    public static int 文本比较(String string, String string2) {
        return string.compareTo(string2);
    }

    public static String 翻转文本(String string) {
        return new StringBuffer(string).reverse().toString();
    }

    public static String[] 分割文本(String string, String string2) {
        if ("".equals(string2) || "".equals(string)) {
            return new String[0];
        }
        if (string2.equals("\n")) {
            string = 文本操作.子文本替换(string, "\r", "");
        }
        if (文本操作.取文本右边(string, 文本操作.length(string2)).equals(string2)) {
            return 文本操作.取指定文本(string2 + string, string2, string2);
        }
        return 文本操作.取指定文本(string2 + string + string2, string2, string2);
    }

    public static String[] 取指定文本(String string, String string2, String string3) {
        if ("".equals(string) || "".equals(string2) || "".equals(string3)) {
            return new String[0];
        }
        return 正则表达式.正则匹配(string, "(?<=\\Q" + string2 + "\\E).*?(?=\\Q" + string3 + "\\E)");
    }

    public static String 截取文本(String string, String string2, String string3) {
        String[] stringArray = 文本操作.取指定文本(string, string2, string3);
        if (stringArray.length > 0) {
            return stringArray[0];
        }
        return "";
    }

    public void 初始化事件() {
    }
}

