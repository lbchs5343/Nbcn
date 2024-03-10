/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.io.PrintWriter
 *  java.io.StringWriter
 *  java.io.Writer
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Arrays
 */
package lbchs;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Arrays;

public class 全局方法类 {
    public static void 调试输出(Object object) {
        System.out.println(全局方法类.到文本(object));
    }

    public static void 调试输出2(String string, Object... objectArray) {
        System.out.printf(string, objectArray);
    }

    public static String 到文本(Object object) {
        if (object instanceof String) {
            return (String) object;
        }
        if (object instanceof Throwable) {
            Throwable throwable = (Throwable) object;
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        }
        if (object.getClass().isArray()) {
            return Arrays.deepToString((Object[]) object);
        }
        return String.valueOf(object);
    }

    public static String 到文本(char... cArray) {
        return new String(cArray);
    }

    public static String 格式化文本(String string, Object... objectArray) {
        return String.format(string, objectArray);
    }

    public static double 到数值(String string) {
        try {
            return Double.parseDouble(string);
        } catch (Exception exception) {
            throw new RuntimeException("到数值错误，无法将对应值转换为小数");
        }
    }

    public static int 到整数(Object object) {
        if (object instanceof String) {
            return Integer.parseInt((String) object);
        }
        if (object instanceof Double) {
            return (int) ((Double) object).doubleValue();
        }
        if (object instanceof Float) {
            return (int) ((Float) object).floatValue();
        }
        throw new RuntimeException("到整数错误，无法将对应值转换为整数");
    }

    public static long 到长整数(Object object) {
        if (object instanceof String) {
            return Long.parseLong((String) object);
        }
        if (object instanceof Double) {
            return (long) ((Double) object).doubleValue();
        }
        if (object instanceof Float) {
            return (long) ((Float) object).floatValue();
        }
        throw new RuntimeException("到长整数错误，无法将对应值转换为长整数");
    }

    public void 初始化事件() {
    }
}

