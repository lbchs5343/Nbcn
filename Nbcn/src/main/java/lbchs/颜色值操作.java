/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.Color
 *  java.lang.Object
 */
package lbchs;

import android.graphics.Color;

public class 颜色值操作 {
    public static int 到颜色值(int n, int n2, int n3, int n4) {
        return Color.argb(n, n2, n3, n4);
    }

    public static int 取红色值(int n) {
        return Color.red(n);
    }

    public static int 取绿色值(int n) {
        return Color.green(n);
    }

    public static int 取蓝色值(int n) {
        return Color.blue(n);
    }

    public static int 取透明度(int n) {
        return Color.alpha(n);
    }

    public void 初始化事件() {
    }
}

