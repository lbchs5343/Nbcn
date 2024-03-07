/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 */
package jiesheng;

public class 位运算 {
    public static int 位与(int n, int n2) {
        return n & n2;
    }

    public static int 位或(int n, int n2) {
        return n | n2;
    }

    public static int 位异或(int n, int n2) {
        return n ^ n2;
    }

    public static int 位非(int n, int n2) {
        return ~n;
    }

    public static int 位左移(int n, int n2) {
        return n << n2;
    }

    public static int 位右移(int n, int n2) {
        return n >> n2;
    }

    public static int 无符号位右移(int n, int n2) {
        return n >>> n2;
    }

    public void 初始化事件() {
    }
}

