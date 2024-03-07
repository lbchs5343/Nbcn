/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Color
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.os.Build$VERSION
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.widget.AdapterView
 *  android.widget.FrameLayout
 *  android.widget.HorizontalScrollView
 *  android.widget.LinearLayout
 *  android.widget.ScrollView
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.IllegalAccessException
 *  java.lang.IllegalArgumentException
 *  java.lang.Integer
 *  java.lang.NoSuchFieldException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;

public class 悬浮窗
        extends 组件 {
    private FloatWindow mFloatWindow;
    private boolean canMove = true;
    private 被按下 $event_internal_被按下;
    private 被弹起 $event_internal_被弹起;
    private 被移动 $event_internal_被移动;

    public 悬浮窗(Context context) {
        super(context);
        this.mFloatWindow = new FloatWindow(context);
    }

    private void makeTree(可视化组件 可视化组件2) {
        View view = 可视化组件2.getView();
        if (view instanceof AdapterView || view instanceof ScrollView || view instanceof HorizontalScrollView) {
            return;
        }
        ProxyListener proxyListener = new ProxyListener(this, 可视化组件2);
        view.setOnTouchListener((View.OnTouchListener) proxyListener);
        view.setTag((Object) proxyListener);
        if (可视化组件2 instanceof 布局组件) {
            布局组件 布局组件2 = (布局组件) 可视化组件2;
            for (int i = 0; i < 布局组件2.子组件数目(); ++i) {
                try {
                    if (布局组件2.取子组件(i) == null) continue;
                    this.makeTree(布局组件2.取子组件(i));
                    continue;
                } catch (IllegalArgumentException | NullPointerException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    private void changeTree(View view) {
        if (view instanceof AdapterView) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i <= viewGroup.getChildCount(); ++i) {
                try {
                    if (viewGroup.getChildAt(i) == null) continue;
                    this.changeTree(viewGroup.getChildAt(i));
                    continue;
                } catch (IllegalArgumentException | NullPointerException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    public void 可否移动(boolean bl) {
        this.canMove = bl;
    }

    public void 背景图片(String string) {
        try {
            this.mFloatWindow.setBackground(Drawable.createFromStream((InputStream) this.窗口环境.getAssets().open(string), (String) string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 背景颜色(int n) {
        this.mFloatWindow.setBackground((Drawable) new ColorDrawable(n));
    }

    public void 背景颜色(String string) {
        this.背景颜色(Color.parseColor((String) string));
    }

    public void 组件(组件容器 组件容器2) {
        Object 通用型 = 组件容器2.取根组件();
        View view = ((可视化组件) 通用型).getView();
        this.makeTree((可视化组件) 通用型);
        this.changeTree(view);
        this.mFloatWindow.setContentView(view);
    }

    public void 组件(可视化组件 可视化组件2) {
        View view = 可视化组件2.getView();
        this.makeTree(可视化组件2);
        this.changeTree(view);
        this.mFloatWindow.setContentView(view);
    }

    public void 可获取焦点(boolean bl) {
        this.mFloatWindow.setFocus(bl);
    }

    @Override
    public void X坐标(int n) {
        this.mFloatWindow.setX(n);
    }

    public int X坐标() {
        return this.mFloatWindow.getX();
    }

    @Override
    public void Y坐标(int n) {
        this.mFloatWindow.setY(n);
    }

    public int Y坐标() {
        return this.mFloatWindow.getY();
    }

    @Override
    public void 高度(int n) {
        this.mFloatWindow.setHeight(n);
    }

    @Override
    public void 宽度(int n) {
        this.mFloatWindow.setHeight(n);
    }

    public void 重力属性(int n) {
        this.mFloatWindow.setGravity(n);
    }

    public void 标志(int n) {
        this.mFloatWindow.setFlags(n);
    }

    public void 类型(int n) {
        this.mFloatWindow.setType(n);
    }

    public void 格式(int n) {
        this.mFloatWindow.setFormat(n);
    }

    public void 添加组件(可视化组件 可视化组件2) {
        this.mFloatWindow.addContentView(可视化组件2.getView());
    }

    public void 请求焦点() {
        this.mFloatWindow.requestFocus();
    }

    public void 显示() {
        this.mFloatWindow.show();
    }

    public void 隐藏() {
        this.mFloatWindow.hide();
    }

    public void 关闭() {
        this.mFloatWindow.dismiss();
    }

    public void 置被按下(被按下 被按下2) {
        this.$event_internal_被按下 = 被按下2;
    }

    public void 置被弹起(被弹起 被弹起2) {
        this.$event_internal_被弹起 = 被弹起2;
    }

    public void 置被移动(被移动 被移动2) {
        this.$event_internal_被移动 = 被移动2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 被移动 {
        public void 被移动(int var1, int var2, int var3, int var4);
    }

    public static interface 被弹起 {
        public void 被弹起(int var1, int var2);
    }

    public static interface 被按下 {
        public void 被按下(int var1, int var2);
    }

    private class ProxyListener
            implements View.OnTouchListener {
        private float mDownRawX;
        private float mDownRawY;
        private int mMarkedX;
        private int mMarkedY;
        private int mMarkedRight;
        private int mMarkedBottom;
        private int mMarkedType;
        private int fX;
        private int fY;
        private 可视化组件 target;
        private 悬浮窗 fw;

        public ProxyListener(悬浮窗 悬浮窗3, 可视化组件 可视化组件2) {
            this.fw = 悬浮窗3;
            this.target = 可视化组件2;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0: {
                    this.fX = 悬浮窗.this.mFloatWindow.getX();
                    this.fY = 悬浮窗.this.mFloatWindow.getY();
                    this.mDownRawX = motionEvent.getRawX();
                    this.mDownRawY = motionEvent.getRawY();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    try {
                        this.mMarkedX = (Integer) layoutParams.getClass().getField("x").get((Object) layoutParams);
                        this.mMarkedY = (Integer) layoutParams.getClass().getField("y").get((Object) layoutParams);
                        this.mMarkedType = 0;
                    } catch (IllegalAccessException | NoSuchFieldException throwable) {
                        try {
                            this.mMarkedX = (Integer) layoutParams.getClass().getField("leftMargin").get((Object) layoutParams);
                            this.mMarkedY = (Integer) layoutParams.getClass().getField("topMargin").get((Object) layoutParams);
                            this.mMarkedRight = (Integer) layoutParams.getClass().getField("rightMargin").get((Object) layoutParams);
                            this.mMarkedBottom = (Integer) layoutParams.getClass().getField("bottomMargin").get((Object) layoutParams);
                            this.mMarkedType = 1;
                        } catch (IllegalAccessException | NoSuchFieldException throwable2) {
                            this.mMarkedType = 2;
                        }
                    }
                    if (悬浮窗.this.$event_internal_被按下 == null) break;
                    悬浮窗.this.$event_internal_被按下.被按下(this.fX, this.fY);
                    break;
                }
                case 2: {
                    int n = (int) (motionEvent.getRawX() - this.mDownRawX);
                    int n2 = (int) (motionEvent.getRawY() - this.mDownRawY);
                    int n3 = this.mMarkedX + n;
                    int n4 = this.mMarkedY + n2;
                    try {
                        if (悬浮窗.this.canMove) {
                            switch (this.mMarkedType) {
                                case 0: {
                                    int n5 = this.fX + n3 - this.mMarkedX;
                                    int n6 = this.fY + n4 - this.mMarkedY;
                                    悬浮窗.this.X坐标(n5);
                                    悬浮窗.this.Y坐标(n6);
                                    if (this.fw == null || 悬浮窗.this.$event_internal_被移动 == null)
                                        break;
                                    悬浮窗.this.$event_internal_被移动.被移动(this.fX, this.fY, n5, n6);
                                    break;
                                }
                                case 1: {
                                    int n7 = this.fX + n3 - this.mMarkedX;
                                    int n8 = this.fY + n4 - this.mMarkedY;
                                    悬浮窗.this.X坐标(n7);
                                    悬浮窗.this.Y坐标(n8);
                                    if (this.fw == null || 悬浮窗.this.$event_internal_被移动 == null)
                                        break;
                                    悬浮窗.this.$event_internal_被移动.被移动(this.fX, this.fY, n7, n8);
                                    break;
                                }
                            }
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                case 1: {
                    if (悬浮窗.this.$event_internal_被弹起 == null) break;
                    悬浮窗.this.$event_internal_被弹起.被弹起(悬浮窗.this.mFloatWindow.getX(), 悬浮窗.this.mFloatWindow.getY());
                }
            }
            return !this.target.hasClickListener();
        }
    }

    class FloatWindow {
        private Context mContext;
        private WindowManager mWindowManager;
        private WindowManager.LayoutParams mLayoutParams;
        private LinearLayout mLayout;
        private FrameLayout content;
        private int textColor;
        private DisplayMetrics dm;
        private int mWidth;
        private int mHeight;
        private int backgroundColor;
        private int flag1 = 327968;
        private int flag2 = 327944;

        public FloatWindow(Context context) {
            this.mContext = context;
            this.dm = context.getResources().getDisplayMetrics();
            this.mWidth = this.getWidth();
            this.mHeight = this.getHeight();
            this.init(context);
        }

        private void init(Context context) {
            this.mWindowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            this.mLayoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = this.flag2;
            this.mLayoutParams.type = Build.VERSION.SDK_INT >= 26 ? 2038 : (Build.VERSION.SDK_INT == 23 ? 2005 : 2003);
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
            this.mLayout = new LinearLayout(context);
            this.mLayout.setOrientation(1);
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{0x1010031, 16842806});
            this.backgroundColor = typedArray.getColor(0, 0xFF00FF);
            this.textColor = typedArray.getColor(1, 0xFF00FF);
            typedArray.recycle();
            this.mWindowManager.addView((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
            this.mLayout.setVisibility(8);
        }

        private int getWidth() {
            return this.mContext.getResources().getDisplayMetrics().widthPixels;
        }

        public void setWidth(int n) {
            this.mLayoutParams.width = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        private int getHeight() {
            return this.mContext.getResources().getDisplayMetrics().heightPixels;
        }

        public void setHeight(int n) {
            this.mLayoutParams.height = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public int getX() {
            return this.mLayoutParams.x;
        }

        public void setX(int n) {
            this.mLayoutParams.x = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public int getY() {
            return this.mLayoutParams.y;
        }

        public void setY(int n) {
            this.mLayoutParams.y = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public void requestFocus() {
            this.mLayout.requestFocus();
        }

        public void setGravity(int n) {
            this.mLayoutParams.gravity = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public void setFocus(boolean bl) {
            if (bl) {
                this.mLayoutParams.flags = this.flag1;
                this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
            } else {
                this.mLayoutParams.flags = this.flag2;
                this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
            }
        }

        private int dp(float f) {
            return (int) TypedValue.applyDimension((int) 1, (float) f, (DisplayMetrics) this.dm);
        }

        public Drawable getBackground() {
            return this.mLayout.getBackground();
        }

        public void setBackground(Drawable drawable) {
            this.mLayout.setBackgroundDrawable(drawable);
        }

        public void addContentView(View view) {
            this.content.removeAllViews();
            this.content.addView(view);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(this.backgroundColor);
            gradientDrawable.setCornerRadius(4.0f);
            gradientDrawable.setStroke(2, this.textColor);
            gradientDrawable.setAlpha(136);
            this.mLayout.setBackgroundDrawable((Drawable) gradientDrawable);
        }

        public void setContentView(View view) {
            this.mLayout.removeAllViews();
            this.mLayout.addView(view);
        }

        public void show() {
            this.mLayout.setVisibility(0);
        }

        public void hide() {
            this.mLayout.setVisibility(8);
        }

        public void dismiss() {
            this.mWindowManager.removeView((View) this.mLayout);
        }

        public void setFlags(int n) {
            this.mLayoutParams.flags = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public void setFormat(int n) {
            this.mLayoutParams.format = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }

        public void setType(int n) {
            this.mLayoutParams.type = n;
            this.mWindowManager.updateViewLayout((View) this.mLayout, (ViewGroup.LayoutParams) this.mLayoutParams);
        }
    }
}

