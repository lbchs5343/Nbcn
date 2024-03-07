/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.Color
 *  java.lang.Object
 */
package jiesheng;

import android.graphics.Color;

public class 颜色值操作 {
    public static int 到颜色值(int n, int n2, int n3, int n4) {
        return Color.argb((int) n, (int) n2, (int) n3, (int) n4);
    }

    public static int 取红色值(int n) {
        return Color.red((int) n);
    }

    public static int 取绿色值(int n) {
        return Color.green((int) n);
    }

    public static int 取蓝色值(int n) {
        return Color.blue((int) n);
    }

    public static int 取透明度(int n) {
        return Color.alpha((int) n);
    }

    public void 初始化事件() {
    }
}

