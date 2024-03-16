/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 */
package lbchs.utils;

public class 字符操作 {
    public static boolean 是否为数字(char c) {
        return Character.isDigit(c);
    }

    public static boolean 是否为字母(char c) {
        return Character.isLetter(c);
    }

    public static boolean 是否为字母或数字(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static boolean 是否为空格(char c) {
        return Character.isWhitespace(c);
    }

    public static boolean 是否为大写字母(char c) {
        return Character.isUpperCase(c);
    }

    public static boolean 是否为小写字母(char c) {
        return Character.isLowerCase(c);
    }

    public static char 到大写字母(char c) {
        return Character.toUpperCase(c);
    }

    public static char 到小写字母(char c) {
        return Character.toLowerCase(c);
    }

    public void 初始化事件() {
    }
}

