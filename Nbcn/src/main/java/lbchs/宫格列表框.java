/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemLongClickListener
 *  android.widget.ArrayAdapter
 *  android.widget.BaseAdapter
 *  android.widget.GridView
 *  android.widget.ListAdapter
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;


import java.util.ArrayList;
import java.util.List;

public class 宫格列表框
        extends 可视化组件 {
    private final List<String> items;
    private final ArrayAdapter<String> adp;
    private BaseAdapter adapter;
    private boolean isStop = false;
    private 项目被单击 $event_internal_项目被单击;
    private 项目被长按 $event_internal_项目被长按;
    private 滚动状态改变 $event_internal_滚动状态改变;
    private 正在上滚 $event_internal_正在上滚;
    private 正在下滚 $event_internal_正在下滚;
    private 滚动到顶部 $event_internal_滚动到顶部;
    private 滚动到底部 $event_internal_滚动到底部;

    public 宫格列表框(Context context) {
        super(context);
        final GridView gridView = this.getView();
        this.items = new ArrayList<>();
        this.adp = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, this.items);
        gridView.setAdapter(this.adp);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                if (宫格列表框.this.$event_internal_项目被单击 != null) {
                    宫格列表框.this.$event_internal_项目被单击.项目被单击(n);
                }
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int n, long l) {
                if (宫格列表框.this.$event_internal_项目被长按 != null) {
                    宫格列表框.this.$event_internal_项目被长按.项目被长按(n);
                }
                return true;
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                if (宫格列表框.this.isStop) {
                    View view;
                    if (n == 0) {
                        View view2 = gridView.getChildAt(0);
                        if (view2 != null && view2.getTop() == 0 && 宫格列表框.this.$event_internal_滚动到顶部 != null) {
                            宫格列表框.this.$event_internal_滚动到顶部.滚动到顶部();
                        }
                    } else if (n + n2 == n3 && (view = gridView.getChildAt(gridView.getChildCount() - 1)) != null && view.getBottom() == gridView.getHeight() && 宫格列表框.this.$event_internal_滚动到底部 != null) {
                        宫格列表框.this.$event_internal_滚动到底部.滚动到底部();
                    }
                    if (n < gridView.getLastVisiblePosition()) {
                        if (宫格列表框.this.$event_internal_正在上滚 != null) {
                            宫格列表框.this.$event_internal_正在上滚.正在上滚();
                        }
                    } else if (n > gridView.getLastVisiblePosition() && 宫格列表框.this.$event_internal_正在下滚 != null) {
                        宫格列表框.this.$event_internal_正在下滚.正在下滚();
                    }
                }
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                宫格列表框.this.isStop = n == 0;
                if (宫格列表框.this.$event_internal_滚动状态改变 != null) {
                    宫格列表框.this.$event_internal_滚动状态改变.滚动状态改变(n);
                }
            }
        });
        gridView.setDescendantFocusability(393216);
    }

    @Override
    public View 创建视图() {
        return new GridView(this.取上下文());
    }

    public void 显示滑块条(boolean bl) {
        this.getView().setVerticalScrollBarEnabled(bl);
    }

    public void 支持快速滚动(boolean bl) {
        this.getView().setFastScrollEnabled(bl);
    }

    public void 始终显示快速滚动条(boolean bl) {
        this.getView().setFastScrollAlwaysVisible(bl);
    }

    public void 列宽(int n) {
        this.getView().setColumnWidth(n);
    }

    public int 列宽() {
        return this.getView().getColumnWidth();
    }

    public void 列数(int n) {
        this.getView().setNumColumns(n);
    }

    public int 列数() {
        return this.getView().getNumColumns();
    }

    public void 置适配器(适配器 适配器2) {
        this.adapter = 适配器2;
        this.getView().setAdapter(适配器2);
    }

    public void 置适配器(高级适配器 高级适配器2) {
        this.adapter = 高级适配器2;
        this.getView().setAdapter(高级适配器2);
    }

    public void 置适配器(基础适配器 基础适配器2) {
        this.adapter = 基础适配器2;
        this.getView().setAdapter(基础适配器2);
    }

    public void 添加项目(String string) {
        if (this.adapter == null) {
            this.items.add(string);
            this.adp.notifyDataSetChanged();
        }
    }

    public void 滚动至(int n) {
        this.getView().smoothScrollToPosition(n);
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

    public void 置项目被单击(项目被单击 项目被单击2) {
        this.$event_internal_项目被单击 = 项目被单击2;
    }

    public void 置项目被长按(项目被长按 项目被长按2) {
        this.$event_internal_项目被长按 = 项目被长按2;
    }

    public void 置滚动状态改变(滚动状态改变 滚动状态改变2) {
        this.$event_internal_滚动状态改变 = 滚动状态改变2;
    }

    public void 置正在上滚(正在上滚 正在上滚2) {
        this.$event_internal_正在上滚 = 正在上滚2;
    }

    public void 置正在下滚(正在下滚 正在下滚2) {
        this.$event_internal_正在下滚 = 正在下滚2;
    }

    public void 置滚动到顶部(滚动到顶部 滚动到顶部2) {
        this.$event_internal_滚动到顶部 = 滚动到顶部2;
    }

    public void 置滚动到底部(滚动到底部 滚动到底部2) {
        this.$event_internal_滚动到底部 = 滚动到底部2;
    }

    public void 项目(String string) {
        if (this.adapter == null && string.contains("{") && string.contains("}")) {
            if ((string = string.replace(" ", "").replace("{", "").replace("}", "")).contains(",")) {
                String[] stringArray;
                for (String string2 : stringArray = string.split(",")) {
                    this.添加项目(string2);
                }
            } else {
                this.添加项目(string);
            }
        }
    }

    public GridView getView() {
        return (GridView) super.getView();
    }

    @Override
    public void 初始化事件() {
    }

    public interface 滚动到底部 {
        void 滚动到底部();
    }

    public interface 滚动到顶部 {
        void 滚动到顶部();
    }

    public interface 正在下滚 {
        void 正在下滚();
    }

    public interface 正在上滚 {
        void 正在上滚();
    }

    public interface 滚动状态改变 {
        void 滚动状态改变(int var1);
    }

    public interface 项目被长按 {
        void 项目被长按(int var1);
    }

    public interface 项目被单击 {
        void 项目被单击(int var1);
    }
}

