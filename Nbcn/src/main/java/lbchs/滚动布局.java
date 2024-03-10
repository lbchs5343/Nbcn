/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.ScrollView
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;

public class 滚动布局
        extends 布局组件 {
    public 滚动布局(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new ScrollView(this.取上下文());
    }

    public void 滚动至(int n, int n2) {
        this.getView().scrollTo(n, n2);
    }

    public void 显示滚动条(boolean bl) {
        this.getView().setVerticalScrollBarEnabled(bl);
    }

    public void 完全视图(boolean bl) {
        this.getView().setFillViewport(bl);
    }

    public ScrollView getView() {
        return (ScrollView) super.getView();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

