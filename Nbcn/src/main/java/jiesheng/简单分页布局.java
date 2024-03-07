/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.VelocityTracker
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.HorizontalScrollView
 *  android.widget.LinearLayout
 *  android.widget.Scroller
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class 简单分页布局
        extends 布局组件 {
    private 页面被改变 $event_internal_页面被改变;

    public 简单分页布局(Context context) {
        super(context);
        this.getView().setOnPageChangeListener(new PageLayout.OnPageChangeListener() {

            @Override
            public void onPageChange(View view, int n) {
                if (简单分页布局.this.$event_internal_页面被改变 != null) {
                    简单分页布局.this.$event_internal_页面被改变.页面被改变(n);
                }
            }
        });
    }

    public PageLayout getView() {
        return (PageLayout) super.getView();
    }

    @Override
    public View 创建视图() {
        return new PageLayout(this.取上下文());
    }

    public void 显示页面(int n) {
        this.getView().showPage(n);
    }

    public void 置页面被改变(页面被改变 页面被改变2) {
        this.$event_internal_页面被改变 = 页面被改变2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 页面被改变 {
        public void 页面被改变(int var1);
    }

    public static class PageLayout
            extends HorizontalScrollView {
        private int mTouchScale = 0;
        private LinearLayout wrapper;
        private OnPageChangeListener mOnPageChangeListener;
        private int mIdx;
        private Scroller mScroller;
        private int mTouchSlop;
        private int mMinimumVelocity;
        private int mMaximumVelocity;
        private VelocityTracker mVelocityTracker;
        private int mCount;
        private int mWidth;

        public PageLayout(Context context) {
            super(context);
            this.init(context);
        }

        public PageLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.init(context);
        }

        public int getTouchScale() {
            return this.mTouchScale;
        }

        public void setTouchScale(int n) {
            this.mTouchScale = n;
        }

        private void init(Context context) {
            this.setHorizontalScrollBarEnabled(false);
            this.mWidth = context.getResources().getDisplayMetrics().widthPixels;
            this.mTouchScale = this.mWidth / 2;
            this.wrapper = new LinearLayout(context);
            super.addView((View) this.wrapper);
            this.mScroller = new Scroller(this.getContext());
            this.setFocusable(true);
            this.setWillNotDraw(false);
            ViewConfiguration viewConfiguration = ViewConfiguration.get((Context) context);
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        }

        protected void onMeasure(int n, int n2) {
            int n3 = this.getMeasuredWidth();
            int n4 = this.wrapper.getChildCount();
            if (this.mCount != n4 || this.mWidth != n3) {
                this.mCount = n4;
                this.mWidth = n3;
                for (int i = 0; i < n4; ++i) {
                    ViewGroup viewGroup = (ViewGroup) this.wrapper.getChildAt(i);
                    ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                    layoutParams.width = this.mWidth;
                    viewGroup.setLayoutParams(layoutParams);
                    viewGroup.requestLayout();
                }
                ViewGroup.LayoutParams layoutParams = this.wrapper.getLayoutParams();
                layoutParams.width = this.mWidth * n4;
                this.wrapper.setLayoutParams(layoutParams);
                this.wrapper.requestLayout();
                this.requestLayout();
            }
            super.onMeasure(n, n2);
            this.showPage(this.mIdx);
        }

        protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
            super.onLayout(bl, n, n2, n3, n4);
            if (bl) {
                this.showPage(this.mIdx);
            }
        }

        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            FrameLayout frameLayout = new FrameLayout(this.getContext());
            frameLayout.addView(view, layoutParams);
            this.wrapper.addView((View) frameLayout);
        }

        public void addView(View view) {
            FrameLayout frameLayout = new FrameLayout(this.getContext());
            frameLayout.addView(view);
            this.wrapper.addView((View) frameLayout);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getX() < (float) this.mTouchScale || motionEvent.getX() > (float) (this.mWidth - this.mTouchScale)) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            return false;
        }

        private void obtainVelocityTracker(MotionEvent motionEvent) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
        }

        private void releaseVelocityTracker() {
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int n = motionEvent.getAction();
            this.obtainVelocityTracker(motionEvent);
            switch (n) {
                case 1: {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                    int n2 = (int) velocityTracker.getYVelocity();
                    int n3 = (int) velocityTracker.getXVelocity();
                    this.releaseVelocityTracker();
                    int n4 = Math.abs((int) n3);
                    int n5 = Math.abs((int) n2);
                    if (n4 > this.mMinimumVelocity && n4 > n5) {
                        if (n3 > 0) {
                            this.showPage(Math.max((int) 0, (int) (this.mIdx - 1)));
                        } else {
                            this.showPage(Math.min((int) (this.wrapper.getChildCount() - 1), (int) (this.mIdx + 1)));
                        }
                        return true;
                    }
                    int n6 = this.getScrollX();
                    int n7 = n6 % this.mWidth;
                    if (n7 < this.mWidth / 2) {
                        this.showPage(n6 / this.mWidth);
                    } else {
                        this.showPage(n6 / this.mWidth + 1);
                    }
                    return true;
                }
            }
            return super.onTouchEvent(motionEvent);
        }

        public void showPage(int n) {
            this.smoothScrollTo(this.mWidth * n, 0);
            if (this.mOnPageChangeListener != null && this.mIdx != n) {
                this.mOnPageChangeListener.onPageChange((View) this, n);
            }
            this.mIdx = n;
        }

        public void showPage(View view) {
            int n = this.wrapper.getChildCount();
            for (int i = 0; i < n; ++i) {
                if (!this.wrapper.getChildAt(i).equals((Object) view)) continue;
                this.showPage(i);
            }
        }

        public View getPage(int n) {
            return this.wrapper.getChildAt(n);
        }

        public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
            this.mOnPageChangeListener = onPageChangeListener;
        }

        public static interface OnPageChangeListener {
            public void onPageChange(View var1, int var2);
        }
    }
}

