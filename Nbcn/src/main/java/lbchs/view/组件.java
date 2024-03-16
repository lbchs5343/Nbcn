/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.view.View;

import lbchs.utils.系统操作;

public class 组件 {
    public Context 窗口环境;

    public 组件() {
    }

    public 组件(Context context) {
        this.窗口环境 = context;
    }

    public int dip2px(int n) {
        float f = this.窗口环境.getResources().getDisplayMetrics().density;
        return (int) ((float) n * f + 0.5f);
    }

    public int px2dip(int n) {
        float f = this.窗口环境.getResources().getDisplayMetrics().density;
        return (int) ((float) n / f + 0.5f);
    }

    public Context 取上下文() {
        return this.窗口环境;
    }

    public View getView() {
        if (窗口环境 != null) {
            View view = new View(窗口环境);
            view.setVisibility(View.GONE);
            return view;
        }
        return null;
    }

    public int getRealHeight(double d) {
        return (int) (d * (double) (系统操作.取屏幕高度_不含导航栏(this.窗口环境) - 系统操作.取状态栏高度(this.窗口环境)));
    }

    public int getRealWidth(double d) {
        return (int) (d * (double) 系统操作.取屏幕宽度(this.窗口环境));
    }

    public void 上外边距(int n) {
    }

    public void 左外边距(int n) {
    }

    public void 下外边距(int n) {
    }

    public void 右外边距(int n) {
    }

    public void 高度(int n) {
    }

    public void 宽度(int n) {
    }

    public void 高度(String string) {
    }

    public void 宽度(String string) {
    }

    public void X坐标(int n) {
    }

    public void Y坐标(int n) {
    }

    public void X坐标(String string) {
    }

    public void Y坐标(String string) {
    }

    public void Z坐标(int n) {
    }

    public void 横坐标(int n) {
    }

    public void 纵坐标(int n) {
    }

    public void 竖坐标(int n) {
    }

    public void 初始化事件() {
    }
}

