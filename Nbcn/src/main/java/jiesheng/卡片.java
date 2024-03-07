/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Color
 *  android.view.View
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class 卡片
        extends 布局组件 {
    public 卡片(Context context) {
        super(context);
    }

    public CardView getView() {
        return (CardView) super.getView();
    }

    @Override
    public View 创建视图() {
        return new CardView(this.取上下文());
    }

    public void 圆角(int n) {
        this.getView().setRadius(n);
    }

    @Override
    public void 阴影(int n) {
        this.getView().setCardElevation(n);
    }

    @Override
    public void 背景颜色(int n) {
        this.getView().setCardBackgroundColor(n);
    }

    @Override
    public void 背景颜色(String string) {
        this.背景颜色(Color.parseColor((String) string));
    }

    @Override
    public void 初始化事件() {
    }
}

