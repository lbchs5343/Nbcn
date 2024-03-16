/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.RatingBar
 *  android.widget.RatingBar$OnRatingBarChangeListener
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs.view;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

public class 评分条
        extends 可视化组件 {
    private 评分被改变 $event_internal_评分被改变;

    public 评分条(Context context) {
        super(context);
        this.getView().setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float f, boolean bl) {
                if (评分条.this.$event_internal_评分被改变 != null) {
                    评分条.this.$event_internal_评分被改变.评分被改变(f);
                }
            }
        });
    }

    @Override
    public View 创建视图() {
        return new RatingBar(this.取上下文());
    }

    public void 总评分(int n) {
        this.getView().setNumStars(n);
    }

    public int 总评分() {
        return this.getView().getNumStars();
    }

    public void 评分(int n) {
        this.getView().setRating((float) n);
    }

    public int 评分() {
        return (int) this.getView().getRating();
    }

    public void 最小评分单位(float f) {
        this.getView().setStepSize(f);
    }

    public float 最小评分单位() {
        return this.getView().getStepSize();
    }

    public void 置评分被改变(评分被改变 评分被改变2) {
        this.$event_internal_评分被改变 = 评分被改变2;
    }

    public RatingBar getView() {
        return (RatingBar) super.getView();
    }

    @Override
    public void 初始化事件() {
    }

    public interface 评分被改变 {
        void 评分被改变(float var1);
    }
}

