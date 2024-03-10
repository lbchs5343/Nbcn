/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.ProgressBar
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

public class 进度条
        extends 可视化组件 {
    public 进度条(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new ProgressBar(this.取上下文(), null, 16842872);
    }

    public void 进度(int n) {
        this.getView().setProgress(n);
    }

    public int 进度() {
        return this.getView().getProgress();
    }

    public void 最大进度(int n) {
        this.getView().setMax(n);
    }

    public int 最大进度() {
        return this.getView().getMax();
    }

    public void 缓冲进度(int n) {
        this.getView().setSecondaryProgress(n);
    }

    public int 缓冲进度() {
        return this.getView().getSecondaryProgress();
    }

    public void 模糊进度(boolean bl) {
        this.getView().setIndeterminate(bl);
    }

    public boolean 模糊进度() {
        return this.getView().isIndeterminate();
    }

    public ProgressBar getView() {
        return (ProgressBar) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

