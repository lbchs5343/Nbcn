/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemSelectedListener
 *  android.widget.ArrayAdapter
 *  android.widget.BaseAdapter
 *  android.widget.Spinner
 *  android.widget.SpinnerAdapter
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 */
package jiesheng;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class 下拉列表框
        extends 可视化组件 {
    private List<String> items;
    private ArrayAdapter<String> adp;
    private BaseAdapter adapter;
    private 项目被选中 $event_internal_项目被选中;

    public 下拉列表框(Context 窗口环境) {
        super(窗口环境);
        this.getView().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int n, long l) {
                if (下拉列表框.this.$event_internal_项目被选中 != null) {
                    下拉列表框.this.$event_internal_项目被选中.项目被选中(n);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        items = new ArrayList<String>();
        adp = new ArrayAdapter<String>(窗口环境, android.R.layout.simple_list_item_1, items);
        this.getView().setAdapter(this.adp);
    }

    @Override
    public View 创建视图() {
        return new Spinner(this.取上下文());
    }

    public void 显示滑块条(boolean bl) {
        this.getView().setVerticalScrollBarEnabled(bl);
    }

    public void 置适配器(适配器 适配器2) {
        this.adapter = 适配器2;
        this.getView().setAdapter((SpinnerAdapter) 适配器2);
    }

    public void 置适配器(高级适配器 高级适配器2) {
        this.adapter = 高级适配器2;
        this.getView().setAdapter((SpinnerAdapter) 高级适配器2);
    }

    public void 添加项目(String string) {
        if (this.adapter == null) {
            this.items.add(string);
            this.adp.notifyDataSetChanged();
        }
    }

    public String 取项目(int n) {
        return this.items.get(n);
    }

    public int 取选中项索引() {
        return this.getView().getSelectedItemPosition();
    }

    public void 清除项目() {
        if (this.adapter == null) {
            this.items.clear();
            this.adp.notifyDataSetChanged();
        }
    }

    public void 移除项目(int n) {
        if (this.adapter == null) {
            this.items.remove(n);
            this.adp.notifyDataSetChanged();
        }
    }

    public int 取项目总数() {
        return this.items.size();
    }

    public void 置项目被选中(项目被选中 项目被选中2) {
        this.$event_internal_项目被选中 = 项目被选中2;
    }

    public void 项目(String string) {
        if (string.contains((CharSequence) "{") && string.contains((CharSequence) "}")) {
            if ((string = string.replace((CharSequence) " ", (CharSequence) "").replace((CharSequence) "{", (CharSequence) "").replace((CharSequence) "}", (CharSequence) "")).contains((CharSequence) ",")) {
                String[] stringArray;
                for (String string2 : stringArray = string.split(",")) {
                    this.添加项目(string2);
                }
            } else {
                this.添加项目(string);
            }
        }
    }

    public Spinner getView() {
        return (Spinner) super.getView();
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 项目被选中 {
        public void 项目被选中(int var1);
    }
}

