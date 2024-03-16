/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.ViewGroup
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.ArrayList
 *  java.util.List
 */
package lbchs.layout;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lbchs.view.可视化组件;
import lbchs.view.组件;

public class 布局组件
        extends 可视化组件 {
    private final List<可视化组件> components = new ArrayList<>();

    public 布局组件(Context context) {
        super(context);
    }

    public void 屏蔽子组件焦点(boolean bl) {
        if (bl) {
            this.getView().setDescendantFocusability(393216);
        } else {
            this.getView().setDescendantFocusability(262144);
        }
    }

    public int 子组件数目() {
        return this.components.size();
    }

    public void 添加组件(组件 组件2) {
        if (组件2 instanceof 可视化组件) {
            可视化组件 可视化组件2 = (可视化组件) 组件2;
            this.getView().addView(可视化组件2.getView());
            可视化组件2.实例化布局参数();
            可视化组件2.setParent(this);
            this.components.add(可视化组件2);
        }
    }

    public void 移除组件(组件 组件2) {
        if (组件2 instanceof 可视化组件) {
            可视化组件 可视化组件2 = (可视化组件) 组件2;
            this.getView().removeView(可视化组件2.getView());
            this.components.remove(可视化组件2);
        }
    }

    public void 移除组件(int n) {
        this.getView().removeViewAt(n);
        this.components.remove(n);
    }

    public void 移除所有组件() {
        this.getView().removeAllViews();
        this.components.clear();
    }

    public 可视化组件 取子组件(int n) {
        return this.components.get(n);
    }

    public void addComponent(可视化组件 可视化组件2) {
        可视化组件2.setParent(this);
        this.components.add(可视化组件2);
    }

    public ViewGroup getView() {
        return (ViewGroup) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

