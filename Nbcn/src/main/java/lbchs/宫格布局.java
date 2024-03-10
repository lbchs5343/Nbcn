/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.GridLayout
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;

public class 宫格布局
        extends 布局组件 {
    public 宫格布局(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new GridLayout(this.取上下文());
    }

    public void 列数(int n) {
        this.getView().setColumnCount(n);
    }

    public int 列数() {
        return this.getView().getColumnCount();
    }

    public void 行数(int n) {
        this.getView().setRowCount(n);
    }

    public int 行数() {
        return this.getView().getRowCount();
    }

    public GridLayout getView() {
        return (GridLayout) super.getView();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

