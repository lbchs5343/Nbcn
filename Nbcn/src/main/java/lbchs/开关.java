/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.Switch
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class 开关
        extends 按钮 {
    private 被选中 $event_internal_被选中;
    private 取消选中 $event_internal_取消选中;

    public 开关(Context context) {
        super(context);
        this.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                if (bl) {
                    if (开关.this.$event_internal_被选中 != null) {
                        开关.this.$event_internal_被选中.被选中();
                    }
                } else if (开关.this.$event_internal_取消选中 != null) {
                    开关.this.$event_internal_取消选中.取消选中();
                }
            }
        });
    }

    @Override
    public View 创建视图() {
        return new Switch(this.取上下文());
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

    public void 文本_打开(String string) {
        this.getView().setTextOn(string);
    }

    public String 文本_打开() {
        return this.getView().getTextOn().toString();
    }

    public void 文本_关闭(String string) {
        this.getView().setTextOff(string);
    }

    public String 文本_关闭() {
        return this.getView().getTextOff().toString();
    }

    public Switch getView() {
        return (Switch) super.getView();
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

