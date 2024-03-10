/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  java.lang.Object
 */
package lbchs;

import android.content.Context;

import android.util.TypedValue;

public class 像素单位操作 {
    public static int dip2px(Context context, int n) {
        float f = context.getResources().getDisplayMetrics().density;
        return (int) ((float) n * f + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        float f2 = context.getResources().getDisplayMetrics().density;
        return (int) (f / f2 + 0.5f);
    }

    public static float px2sp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int sp2px(Context context, int n) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) n, context.getResources().getDisplayMetrics());
    }

    public void 初始化事件() {
    }
}

