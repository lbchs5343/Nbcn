/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.RelativeLayout
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

public class 相对布局
        extends 布局组件 {
    public 相对布局(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new RelativeLayout(this.取上下文());
    }

    public RelativeLayout getView() {
        return (RelativeLayout) super.getView();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

