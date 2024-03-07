/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class 多选框
        extends 按钮 {
    private 被选中 $event_internal_被选中;
    private 取消选中 $event_internal_取消选中;

    public 多选框(Context context) {
        super(context);
        this.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    if (多选框.this.$event_internal_被选中 != null) {
                        多选框.this.$event_internal_被选中.被选中();
                    }
                } else if (多选框.this.$event_internal_取消选中 != null) {
                    多选框.this.$event_internal_取消选中.取消选中();
                }
            }
        });
    }

    @Override
    public View 创建视图() {
        return new CheckBox(this.取上下文());
    }

    public void 选中(boolean bl) {
        this.getView().setChecked(bl);
    }

    public boolean 选中() {
        return this.getView().isChecked();
    }

    public void 切换状态() {
        this.getView().toggle();
    }

    public void 置被选中(被选中 被选中2) {
        this.$event_internal_被选中 = 被选中2;
    }

    public void 置取消选中(取消选中 取消选中2) {
        this.$event_internal_取消选中 = 取消选中2;
    }

    public CheckBox getView() {
        return (CheckBox) super.getView();
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 取消选中 {
        public void 取消选中();
    }

    public static interface 被选中 {
        public void 被选中();
    }
}

