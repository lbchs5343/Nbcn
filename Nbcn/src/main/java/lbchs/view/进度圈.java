/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Color
 *  android.graphics.ColorFilter
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.graphics.drawable.ColorDrawable
 *  android.view.View
 *  android.widget.ProgressBar
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ProgressBar;

import lbchs.view.可视化组件;

public class 进度圈
        extends 可视化组件 {
    public 进度圈(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new ProgressBar(this.取上下文());
    }

    public void 着色(int n) {
        this.getView().getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(n, PorterDuff.Mode.SRC_ATOP));
    }

    public void 着色(String string) {
        this.着色(Color.parseColor(string));
    }

    public int 着色() {
        return ((ColorDrawable) this.getView().getIndeterminateDrawable().getCurrent()).getColor();
    }

    public ProgressBar getView() {
        return (ProgressBar) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

