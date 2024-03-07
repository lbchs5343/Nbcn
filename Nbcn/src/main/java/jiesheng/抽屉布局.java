/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup$LayoutParams
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class 抽屉布局
        extends 布局组件 {
    private 侧滑关闭 $event_internal_侧滑关闭;
    private 侧滑打开 $event_internal_侧滑打开;

    public 抽屉布局(Context context) {
        super(context);
        this.getView().setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View view, float f) {
            }

            @Override
            public void onDrawerOpened(View view) {
                if (抽屉布局.this.$event_internal_侧滑打开 != null) {
                    抽屉布局.this.$event_internal_侧滑打开.侧滑打开();
                }
            }

            @Override
            public void onDrawerClosed(View view) {
                if (抽屉布局.this.$event_internal_侧滑关闭 != null) {
                    抽屉布局.this.$event_internal_侧滑关闭.侧滑关闭();
                }
            }

            @Override
            public void onDrawerStateChanged(int n) {
            }
        });
    }

    @Override
    public DrawerLayout getView() {
        return (DrawerLayout) super.getView();
    }

    @Override
    public View 创建视图() {
        return new DrawerLayout(this.取上下文());
    }

    public void 侧滑阴影(int n) {
        this.getView().setDrawerElevation(n);
    }

    public void 左侧布局(组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        this.添加组件((组件) 通用型);
        View view = ((可视化组件) 通用型).getView();
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = 3;
        view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
    }

    public void 右侧布局(组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        this.添加组件((组件) 通用型);
        View view = ((可视化组件) 通用型).getView();
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = 5;
        view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
    }

    public void 打开侧滑栏(int n) {
        this.getView().openDrawer(n);
    }

    public void 关闭侧滑栏(int n) {
        this.getView().closeDrawer(n);
    }

    public void 关闭侧滑栏() {
        this.getView().closeDrawers();
    }

    public void 置侧滑关闭(侧滑关闭 侧滑关闭2) {
        this.$event_internal_侧滑关闭 = 侧滑关闭2;
    }

    public void 置侧滑打开(侧滑打开 侧滑打开2) {
        this.$event_internal_侧滑打开 = 侧滑打开2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 侧滑打开 {
        public void 侧滑打开();
    }

    public static interface 侧滑关闭 {
        public void 侧滑关闭();
    }
}

