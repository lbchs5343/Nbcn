/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  java.lang.Object
 */
package jiesheng;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class 基础适配器
        extends BaseAdapter {
    public int getCount() {
        return this.取项目数();
    }

    public Object getItem(int n) {
        return this.取项目(n);
    }

    public long getItemId(int n) {
        return n;
    }

    public View getView(int n, View view, ViewGroup viewGroup) {
        组件容器 组件容器2 = this.加载组件(n, view == null ? null : (组件容器) view.getTag());
        if (组件容器2 != null) {
            view = ((可视化组件) 组件容器2.取根组件()).getView();
        }
        return view;
    }

    public int 取项目数() {
        return 0;
    }

    public Object 取项目(int n) {
        return null;
    }

    public 组件容器 加载组件(int n, 组件容器 组件容器2) {
        return null;
    }

    public void 初始化事件() {
    }
}

