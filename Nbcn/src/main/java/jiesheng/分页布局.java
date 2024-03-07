/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.ArrayList
 *  java.util.List
 */
package jiesheng;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class 分页布局
        extends 布局组件 {
    private ArrayPageAdapter adapter;
    private List<OnPageChangeListener> listeners = new ArrayList<>();
    private 页面被改变 $event_internal_页面被改变;
    private 页面被选中 $event_internal_页面被选中;
    private 页面被滑动 $event_internal_页面被滑动;
    private 滑动状态改变 $event_internal_滑动状态改变;

    public 分页布局(Context context) {
        super(context);
        this.adapter = new ArrayPageAdapter();
        this.getView().setAdapter(this.adapter);
        this.getView().setOnPageChangeListener(new PageView.OnPageChangeListener() {

            @Override
            public void onPageChange(View view, int n) {
                for (OnPageChangeListener onPageChangeListener : 分页布局.this.listeners) {
                    onPageChangeListener.onPageChange(view, n);
                }
                if (分页布局.this.$event_internal_页面被改变 != null) {
                    分页布局.this.$event_internal_页面被改变.页面被改变(n);
                }
            }

            @Override
            public void onPageScrolled(int n, float f, int n2) {
                for (OnPageChangeListener onPageChangeListener : 分页布局.this.listeners) {
                    onPageChangeListener.onPageScrolled(n, f, n2);
                }
                if (分页布局.this.$event_internal_页面被滑动 != null) {
                    分页布局.this.$event_internal_页面被滑动.页面被滑动(n, f, n2);
                }
            }

            @Override
            public void onPageSelected(int n) {
                for (OnPageChangeListener onPageChangeListener : 分页布局.this.listeners) {
                    onPageChangeListener.onPageSelected(n);
                }
                if (分页布局.this.$event_internal_页面被选中 != null) {
                    分页布局.this.$event_internal_页面被选中.页面被选中(n);
                }
            }

            @Override
            public void onPageScrollStateChanged(int n) {
                for (OnPageChangeListener onPageChangeListener : 分页布局.this.listeners) {
                    onPageChangeListener.onPageScrollStateChanged(n);
                }
                if (分页布局.this.$event_internal_滑动状态改变 != null) {
                    分页布局.this.$event_internal_滑动状态改变.滑动状态改变(n);
                }
            }
        });
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (onPageChangeListener != null) {
            this.listeners.add(onPageChangeListener);
        }
    }

    @Override
    public PageView getView() {
        return (PageView) super.getView();
    }

    @Override
    public void 添加组件(组件 组件2) {
        if (组件2 instanceof 可视化组件) {
            可视化组件 可视化组件2 = (可视化组件) 组件2;
            this.adapter.add(可视化组件2.getView());
            this.adapter.notifyDataSetChanged();
            可视化组件2.实例化布局参数();
            this.addComponent(可视化组件2);
        }
    }

    @Override
    public View 创建视图() {
        return new PageView(this.取上下文());
    }

    public int 页面总数() {
        return this.adapter.getCount();
    }

    public void 页面边距(int n) {
        this.getView().setPageMargin(n);
    }

    public void 添加页面(组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        this.adapter.add(((可视化组件) 通用型).getView());
        this.adapter.notifyDataSetChanged();
        ((可视化组件) 通用型).实例化布局参数();
    }

    public void 插入页面(int n, 组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        this.adapter.insert(n, ((可视化组件) 通用型).getView());
        this.adapter.notifyDataSetChanged();
        ((可视化组件) 通用型).实例化布局参数();
    }

    public void 删除页面(组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        this.adapter.remove(((可视化组件) 通用型).getView());
        this.adapter.notifyDataSetChanged();
    }

    public void 删除页面(int n) {
        this.adapter.remove(n);
        this.adapter.notifyDataSetChanged();
    }

    public void 显示页面(int n) {
        this.getView().showPage(n);
    }

    public void 置页面被改变(页面被改变 页面被改变2) {
        this.$event_internal_页面被改变 = 页面被改变2;
    }

    public void 置页面被选中(页面被选中 页面被选中2) {
        this.$event_internal_页面被选中 = 页面被选中2;
    }

    public void 置页面被滑动(页面被滑动 页面被滑动2) {
        this.$event_internal_页面被滑动 = 页面被滑动2;
    }

    public void 置滑动状态改变(滑动状态改变 滑动状态改变2) {
        this.$event_internal_滑动状态改变 = 滑动状态改变2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 滑动状态改变 {
        public void 滑动状态改变(int var1);
    }

    public static interface 页面被滑动 {
        public void 页面被滑动(int var1, float var2, int var3);
    }

    public static interface 页面被选中 {
        public void 页面被选中(int var1);
    }

    public static interface 页面被改变 {
        public void 页面被改变(int var1);
    }

    public static interface OnPageChangeListener {
        public void onPageChange(View var1, int var2);

        public void onPageScrolled(int var1, float var2, int var3);

        public void onPageSelected(int var1);

        public void onPageScrollStateChanged(int var1);
    }
}

