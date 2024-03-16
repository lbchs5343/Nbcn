/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  java.lang.Object
 */
package lbchs.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import lbchs.组件容器;
import lbchs.集合;

public class 适配器
        extends BaseAdapter {
    private 集合<?> list;
    private Object[] strs;
    private 加载项目 $event_internal_加载项目;

    public 适配器(集合<?> 集合2) {
        this.list = 集合2;
        this.strs = 集合2.到数组(new Object[0]);
    }

    public 适配器(Object[] objectArray) {
        this.strs = objectArray;
    }

    public int getCount() {
        return this.strs.length == 0 ? 0 : this.strs.length;
    }

    public Object getItem(int n) {
        return this.strs.length == 0 ? null : this.strs[n];
    }

    public long getItemId(int n) {
        return n;
    }

    public View getDropDownView(int n, View view, ViewGroup viewGroup) {
        return this.getView(n, view, viewGroup);
    }

    public View getView(int n, View view, ViewGroup viewGroup) {
        组件容器 组件容器2 = this.$event_internal_加载项目 != null ? this.$event_internal_加载项目.加载项目(n) : null;
        if (组件容器2 != null) {
            return 组件容器2.取根组件().getView();
        }
        return null ;
    }

    public <T> T getItemTraversal(int n) {
        return (T) this.getItem(n);
    }

    public void notifyDataSetChanged() {
        if (this.list != null) {
            this.strs = this.list.到数组(new Object[0]);
        }
        super.notifyDataSetChanged();
    }

    public int 取项目总数() {
        return this.getCount();
    }

    public void 更新项目() {
        this.notifyDataSetChanged();
    }

    public <通用型> 通用型 取项目(int n) {
        return this.getItemTraversal(n);
    }

    public void 置加载项目(加载项目 加载项目2) {
        this.$event_internal_加载项目 = 加载项目2;
    }

    public void 初始化事件() {
    }

    public interface 加载项目 {
        组件容器 加载项目(int var1);
    }
}

