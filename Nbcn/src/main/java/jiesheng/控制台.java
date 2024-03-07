/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Scanner
 */
package jiesheng;

import java.util.Scanner;

public class 控制台 {
    private static Scanner scanner = new Scanner(System.in);

    public static void 输出行(Object object) {
        System.out.println(object);
    }

    public static void 输出(Object object) {
        System.out.print(object);
    }

    public static void 输出格式文本(String string, Object... objectArray) {
        System.out.printf(string, objectArray);
    }

    public static String 取输入文本() {
        return scanner.next();
    }

    public static int 取输入整数() {
        return scanner.nextInt();
    }

    public static double 取输入双精度数() {
        return scanner.nextDouble();
    }

    public static float 取输入浮点数() {
        return scanner.nextFloat();
    }

    public static long 取输入长整数() {
        return scanner.nextLong();
    }

    public static boolean 取输入逻辑值() {
        return scanner.nextBoolean();
    }

    public static boolean 是否有下一行() {
        return scanner.hasNext();
    }

    public void 初始化事件() {
    }
}

