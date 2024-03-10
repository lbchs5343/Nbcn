/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.LinearLayout
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class 线性布局
        extends 布局组件 {
    public 线性布局(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new LinearLayout(this.取上下文());
    }

    public void 方向(int n) {
        this.getView().setOrientation(n);
    }

    public void 方向(String string) {
        int n = string.equals("垂直") || string.equals("1") ? 1 : 0;
        this.方向(n);
    }

    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

