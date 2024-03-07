/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 *  java.util.Arrays
 *  java.util.HashSet
 */
package jiesheng;

import java.util.Arrays;
import java.util.HashSet;

public class 数组操作 {
    public static String[] 过滤数组(String[] stringArray, String string, boolean bl) {
        HashSet hashSet = new HashSet();
        for (String string2 : stringArray) {
            if (string2.equals((Object) string) != bl) continue;
            hashSet.add((Object) string2);
        }
        return (String[]) hashSet.toArray((Object[]) new String[hashSet.size()]);
    }

    public static String 连接数组成员(String[] stringArray, String string) {
        if (string == null || stringArray == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String string2 = "";
        for (String string3 : stringArray) {
            stringBuilder.append(string2).append(string3);
            string2 = string;
        }
        return stringBuilder.toString();
    }

    public static int 取数组成员数(String[] stringArray) {
        return stringArray.length;
    }

    public static byte[] 合并字节数组(byte[] byArray, byte[] byArray2) {
        if (byArray == null && byArray2 != null) {
            return byArray2;
        }
        if (byArray != null && byArray2 == null) {
            return byArray;
        }
        if (byArray == null && byArray2 == null) {
            return null;
        }
        byte[] byArray3 = new byte[byArray.length + byArray2.length];
        System.arraycopy((Object) byArray, (int) 0, (Object) byArray3, (int) 0, (int) byArray.length);
        System.arraycopy((Object) byArray2, (int) 0, (Object) byArray3, (int) byArray.length, (int) byArray2.length);
        return byArray3;
    }

    public static String[] 合并文本数组(String[] stringArray, String[] stringArray2) {
        if (stringArray == null && stringArray2 != null) {
            return stringArray2;
        }
        if (stringArray != null && stringArray2 == null) {
            return stringArray;
        }
        if (stringArray == null && stringArray2 == null) {
            return null;
        }
        String[] stringArray3 = new String[stringArray.length + stringArray2.length];
        System.arraycopy((Object) stringArray, (int) 0, (Object) stringArray3, (int) 0, (int) stringArray.length);
        System.arraycopy((Object) stringArray2, (int) 0, (Object) stringArray3, (int) stringArray.length, (int) stringArray2.length);
        return stringArray3;
    }

    public static void 复制数组(Object[] objectArray, int n, Object[] objectArray2, int n2, int n3) {
        if (objectArray != null && objectArray2 != null) {
            System.arraycopy((Object) objectArray, (int) n, (Object) objectArray2, (int) n2, (int) n3);
        }
    }

    public static int[] 数组排序(int[] nArray) {
        if (nArray == null) {
            return new int[0];
        }
        Arrays.sort((int[]) nArray);
        return nArray;
    }

    public void 初始化事件() {
    }
}

