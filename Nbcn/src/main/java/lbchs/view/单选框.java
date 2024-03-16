/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.RadioButton
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs.view;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class 单选框
        extends 按钮 {
    private 被选中 $event_internal_被选中;
    private 取消选中 $event_internal_取消选中;

    public 单选框(Context context) {
        super(context);
        this.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    if (单选框.this.$event_internal_被选中 != null) {
                        单选框.this.$event_internal_被选中.被选中();
                    }
                } else if (单选框.this.$event_internal_取消选中 != null) {
                    单选框.this.$event_internal_取消选中.取消选中();
                }
            }
        });
    }

    @Override
    public View 创建视图() {
        return new RadioButton(this.取上下文());
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

    public RadioButton getView() {
        return (RadioButton) super.getView();
    }

    @Override
    public void 初始化事件() {
    }

    public interface 取消选中 {
        void 取消选中();
    }

    public interface 被选中 {
        void 被选中();
    }
}

