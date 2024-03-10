/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.AbsoluteLayout
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.AbsoluteLayout;

public class 自适应布局 extends 布局组件 {
    public 自适应布局(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new AbsoluteLayout(this.取上下文());
    }

    @Override
    public void 添加组件(组件 组件2) {
        if (组件2 instanceof 可视化组件) {
            可视化组件 可视化组件2 = (可视化组件) 组件2;
            this.getView().addView(可视化组件2.getView());
            可视化组件2.到顶层();
            可视化组件2.实例化布局参数();
            this.addComponent(可视化组件2);
        }
    }

    public AbsoluteLayout getView() {
        return (AbsoluteLayout) super.getView();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

