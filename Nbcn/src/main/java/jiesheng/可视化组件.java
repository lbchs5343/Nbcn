/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.BitmapFactory
 *  android.graphics.Color
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.StateListDrawable
 *  android.util.TypedValue
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnLongClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.WindowManager
 *  android.widget.AbsoluteLayout$LayoutParams
 *  android.widget.Button
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.GridLayout
 *  android.widget.GridLayout$LayoutParams
 *  android.widget.GridLayout$Spec
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.Spinner
 *  android.widget.TextView
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.lang.reflect.Field
 */
package jiesheng;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.reflect.Field;

public class 可视化组件
        extends 组件 {
    public StateListDrawable sld;
    private GestureDetector detector;
    private Context 窗口环境;
    private View view;
    private ViewGroup.LayoutParams 基础布局参数;
    private GradientDrawable drawable = new GradientDrawable();
    private AssetManager am;
    private WindowManager wm;
    private int strokeWidth = 1;
    private int strokeColor = -16777216;
    private 布局组件 parent;
    private Object tag;
    private boolean hasClickListener = false;
    private 被单击 $event_internal_被单击;
    private 被长按 $event_internal_被长按;
    private 被按下 $event_internal_被按下;
    private 被弹起 $event_internal_被弹起;
    private 被移动 $event_internal_被移动;
    private 触摸手势 $event_internal_触摸手势;
    private 获得焦点 $event_internal_获得焦点;
    private 失去焦点 $event_internal_失去焦点;

    public 可视化组件(View view) {
        super(view.getContext());
        this.窗口环境 = this.取上下文();
        this.view = view;
        this.初始化();
    }

    public 可视化组件(Context context) {
        super(context);
        this.窗口环境 = context;
        View view = this.创建视图();
        可视化组件 可视化组件2 = this.创建组件();
        if (view != null) {
            this.view = view;
        }
        if (可视化组件2 != null) {
            this.view = 可视化组件2.getView();
        }
        this.初始化();
    }

    public 可视化组件(Context context, int n) {
        super(context);
        this.窗口环境 = context;
        View view = this.创建视图(n);
        可视化组件 可视化组件2 = this.创建组件(n);
        if (view != null) {
            this.view = view;
        }
        if (可视化组件2 != null) {
            this.view = 可视化组件2.getView();
        }
        this.初始化();
    }

    public boolean hasClickListener() {
        return this.hasClickListener;
    }

    public void setParent(布局组件 布局组件2) {
        this.parent = 布局组件2;
    }

    public View 创建视图() {
        return null;
    }

    public View 创建视图(int n) {
        return null;
    }

    public 可视化组件 创建组件() {
        return null;
    }

    public 可视化组件 创建组件(int n) {
        return null;
    }

    @Override
    public void 横坐标(int n) {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            ((AbsoluteLayout.LayoutParams) this.基础布局参数).x = n;
            this.view.setLayoutParams(this.基础布局参数);
        }
    }

    public void 横坐标(String string) {
        this.横坐标(this.getRealValue(string));
    }

    public int 横坐标() {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            return ((AbsoluteLayout.LayoutParams) this.基础布局参数).x;
        }
        return (int) this.view.getX();
    }

    @Override
    public void 纵坐标(int n) {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            ((AbsoluteLayout.LayoutParams) this.基础布局参数).y = n;
            this.view.setLayoutParams(this.基础布局参数);
        }
    }

    public void 纵坐标(String string) {
        this.纵坐标(this.getRealValue(string));
    }

    public int 纵坐标() {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            return ((AbsoluteLayout.LayoutParams) this.基础布局参数).y;
        }
        return (int) this.view.getY();
    }

    @Override
    public void 竖坐标(int n) {
        this.view.setZ((float) this.dip2px(n));
    }

    public int 竖坐标() {
        return (int) this.view.getZ();
    }

    @Override
    public void X坐标(int n) {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            ((AbsoluteLayout.LayoutParams) this.基础布局参数).x = n;
            this.view.setLayoutParams(this.基础布局参数);
        }
    }

    @Override
    public void X坐标(String string) {
        this.X坐标(this.getRealValue(string));
    }

    public int X坐标() {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            return ((AbsoluteLayout.LayoutParams) this.基础布局参数).x;
        }
        return (int) this.view.getX();
    }

    @Override
    public void Y坐标(int n) {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            ((AbsoluteLayout.LayoutParams) this.基础布局参数).y = n;
            this.view.setLayoutParams(this.基础布局参数);
        }
    }

    @Override
    public void Y坐标(String string) {
        this.Y坐标(this.getRealValue(string));
    }

    public int Y坐标() {
        if (this.基础布局参数 instanceof AbsoluteLayout.LayoutParams) {
            return ((AbsoluteLayout.LayoutParams) this.基础布局参数).y;
        }
        return (int) this.view.getY();
    }

    @Override
    public void Z坐标(int n) {
        this.view.setZ((float) this.dip2px(n));
    }

    public int Z坐标() {
        return (int) this.view.getZ();
    }

    public void X偏移(int n) {
        this.view.setTranslationX((float) n);
    }

    public int X偏移() {
        return (int) this.view.getTranslationX();
    }

    public void Y偏移(int n) {
        this.view.setTranslationY((float) n);
    }

    public int Y偏移() {
        return (int) this.view.getTranslationY();
    }

    public void Z偏移(int n) {
        this.view.setTranslationZ((float) n);
    }

    public int Z偏移() {
        return (int) this.view.getTranslationZ();
    }

    public void 位于父布局中间(boolean bl) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            if (bl) {
                layoutParams.addRule(13);
            } else {
                layoutParams.removeRule(13);
            }
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于父布局顶部(boolean bl) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            if (bl) {
                layoutParams.addRule(10);
            } else {
                layoutParams.removeRule(10);
            }
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于父布局底部(boolean bl) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            if (bl) {
                layoutParams.addRule(12);
            } else {
                layoutParams.removeRule(12);
            }
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于父布局左边(boolean bl) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            if (bl) {
                layoutParams.addRule(9);
            } else {
                layoutParams.removeRule(9);
            }
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于父布局右边(boolean bl) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            if (bl) {
                layoutParams.addRule(11);
            } else {
                layoutParams.removeRule(11);
            }
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于某组件左边(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(0, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于某组件右边(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(1, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于某组件之上(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(2, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 位于某组件之下(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(3, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 与某组件左边平齐(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(5, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 与某组件右边平齐(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(7, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 与某组件顶部平齐(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(6, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 与某组件底部平齐(int n) {
        if (this.基础布局参数 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
            layoutParams.addRule(8, n);
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    @Override
    public void 宽度(int n) {
        if (n == -1) {
            if (this.基础布局参数 == null) {
                this.基础布局参数 = new ViewGroup.LayoutParams(-1, -2);
            } else {
                this.基础布局参数.width = -1;
            }
        } else if (n == -2) {
            if (this.基础布局参数 == null) {
                this.基础布局参数 = new ViewGroup.LayoutParams(-2, -2);
            } else {
                this.基础布局参数.width = -2;
            }
        } else if (this.基础布局参数 == null) {
            this.基础布局参数 = new ViewGroup.LayoutParams(this.dip2px(n), -2);
        } else {
            this.基础布局参数.width = this.基础布局参数 instanceof AbsoluteLayout.LayoutParams ? n : this.dip2px(n);
        }
        this.view.setLayoutParams(this.基础布局参数);
    }

    @Override
    public void 宽度(String string) {
        this.宽度(this.getRealValue(string));
    }

    public int 宽度() {
        int n = this.基础布局参数.width;
        if (!(this.基础布局参数 instanceof AbsoluteLayout.LayoutParams)) {
            n = this.px2dip(n);
        }
        return n;
    }

    @Override
    public void 高度(int n) {
        if (n == -1) {
            if (this.基础布局参数 == null) {
                this.基础布局参数 = new ViewGroup.LayoutParams(-2, -1);
            } else {
                this.基础布局参数.height = -1;
            }
        } else if (n == -2) {
            if (this.基础布局参数 == null) {
                this.基础布局参数 = new ViewGroup.LayoutParams(-2, -2);
            } else {
                this.基础布局参数.height = -2;
            }
        } else if (this.基础布局参数 == null) {
            this.基础布局参数 = new ViewGroup.LayoutParams(-2, this.dip2px(n));
        } else {
            this.基础布局参数.height = this.基础布局参数 instanceof AbsoluteLayout.LayoutParams ? n : this.dip2px(n);
        }
        this.view.setLayoutParams(this.基础布局参数);
    }

    @Override
    public void 高度(String string) {
        this.高度(this.getRealValue(string));
    }

    public int 高度() {
        int n = this.基础布局参数.height;
        if (!(this.基础布局参数 instanceof AbsoluteLayout.LayoutParams)) {
            n = this.px2dip(n);
        }
        return n;
    }

    public void 内边距(int n) {
        this.view.setPadding(this.dip2px(n), this.dip2px(n), this.dip2px(n), this.dip2px(n));
    }

    public void 左内边距(int n) {
        this.view.setPadding(this.dip2px(n), this.view.getPaddingTop(), this.view.getPaddingRight(), this.view.getPaddingBottom());
    }

    public int 左内边距() {
        return this.px2dip(this.view.getPaddingLeft());
    }

    public void 上内边距(int n) {
        this.view.setPadding(this.view.getPaddingLeft(), this.dip2px(n), this.view.getPaddingRight(), this.view.getPaddingBottom());
    }

    public int 上内边距() {
        return this.px2dip(this.view.getPaddingTop());
    }

    public void 右内边距(int n) {
        this.view.setPadding(this.view.getPaddingLeft(), this.view.getPaddingTop(), this.dip2px(n), this.view.getPaddingBottom());
    }

    public int 右内边距() {
        return this.px2dip(this.view.getPaddingRight());
    }

    public void 下内边距(int n) {
        this.view.setPadding(this.view.getPaddingLeft(), this.view.getPaddingTop(), this.view.getPaddingRight(), this.dip2px(n));
    }

    public int 下内边距() {
        return this.px2dip(this.view.getPaddingBottom());
    }

    public void 外边距(int n) {
        if (this.基础布局参数 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.基础布局参数;
            marginLayoutParams.setMargins(this.dip2px(n), this.dip2px(n), this.dip2px(n), this.dip2px(n));
            this.view.setLayoutParams((ViewGroup.LayoutParams) marginLayoutParams);
        }
    }

    @Override
    public void 左外边距(int n) {
        if (this.基础布局参数 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.基础布局参数;
            marginLayoutParams.setMargins(this.dip2px(n), marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            this.view.setLayoutParams((ViewGroup.LayoutParams) marginLayoutParams);
        }
    }

    @Override
    public void 上外边距(int n) {
        if (this.基础布局参数 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.基础布局参数;
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, this.dip2px(n), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            this.view.setLayoutParams((ViewGroup.LayoutParams) marginLayoutParams);
        }
    }

    @Override
    public void 右外边距(int n) {
        if (this.基础布局参数 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.基础布局参数;
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, this.dip2px(n), marginLayoutParams.bottomMargin);
            this.view.setLayoutParams((ViewGroup.LayoutParams) marginLayoutParams);
        }
    }

    @Override
    public void 下外边距(int n) {
        if (this.基础布局参数 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.基础布局参数;
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, this.dip2px(n));
            this.view.setLayoutParams((ViewGroup.LayoutParams) marginLayoutParams);
        }
    }

    public void 水波纹效果(boolean bl) {
        if (bl) {
            Resources.Theme theme = this.窗口环境.getTheme();
            TypedValue typedValue = new TypedValue();
            theme.resolveAttribute(16843534, typedValue, true);
            int[] nArray = new int[]{16843534};
            TypedArray typedArray = theme.obtainStyledAttributes(typedValue.resourceId, nArray);
            this.view.setForeground(typedArray.getDrawable(0));
        } else {
            this.view.setForeground(null);
        }
    }

    public void 阴影(int n) {
        this.view.setElevation((float) this.dip2px(n));
    }

    public int 阴影() {
        return (int) this.view.getElevation();
    }

    public void 透明度(int n) {
        this.view.setAlpha((float) n);
    }

    public int 透明度() {
        return (int) this.view.getAlpha();
    }

    public void 组件索引(int n) {
        this.view.setId(n);
    }

    public int 组件索引() {
        return this.view.getId();
    }

    public void 可用(boolean bl) {
        this.view.setEnabled(bl);
    }

    public boolean 可用() {
        return this.view.isEnabled();
    }

    public void 可视(boolean bl) {
        if (bl) {
            this.view.setVisibility(0);
        } else {
            this.view.setVisibility(8);
        }
    }

    public boolean 可视() {
        return this.view.getVisibility() == 0;
    }

    public void 填充系统界面(boolean bl) {
        this.view.setFitsSystemWindows(bl);
    }

    public boolean 填充系统界面() {
        return this.view.getFitsSystemWindows();
    }

    public void 可获取焦点(boolean bl) {
        this.view.setFocusable(bl);
        if (bl) {
            this.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View view, boolean bl) {
                    if (bl) {
                        if (可视化组件.this.$event_internal_获得焦点 != null) {
                            可视化组件.this.$event_internal_获得焦点.获得焦点();
                        }
                    } else if (可视化组件.this.$event_internal_失去焦点 != null) {
                        可视化组件.this.$event_internal_失去焦点.失去焦点();
                    }
                }
            });
        } else {
            this.view.setOnFocusChangeListener(null);
        }
    }

    public boolean 可获取焦点() {
        return this.view.isFocusable();
    }

    public void 背景图片(String string) {
        if (string.startsWith("/")) {
            this.view.setBackgroundDrawable((Drawable) new BitmapDrawable(BitmapFactory.decodeFile((String) string)));
        } else {
            try {
                InputStream inputStream = this.窗口环境.getAssets().open(string);
                Drawable drawable = Drawable.createFromStream((InputStream) inputStream, (String) string);
                this.view.setBackgroundDrawable(drawable);
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void 背景图片(int n) {
        this.view.setBackgroundResource(n);
    }

    public void 背景颜色(String string) {
        this.view.setBackgroundColor(Color.parseColor((String) string));
    }

    public void 背景颜色(int n) {
        this.view.setBackgroundColor(n);
    }

    public int 背景颜色() {
        return ((ColorDrawable) this.view.getBackground()).getColor();
    }

    public void 填充颜色(String string) {
        this.drawable.setColor(Color.parseColor((String) string));
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 填充颜色(int n) {
        this.drawable.setColor(n);
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 边框颜色(String string) {
        this.strokeColor = Color.parseColor((String) string);
        this.drawable.setStroke(this.strokeWidth, this.strokeColor);
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 边框颜色(int n) {
        this.strokeColor = n;
        this.drawable.setStroke(this.strokeWidth, this.strokeColor);
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 边框宽度(int n) {
        this.strokeWidth = n;
        this.drawable.setShape(0);
        this.drawable.setStroke(this.strokeWidth, this.strokeColor);
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 边框圆角(int n) {
        this.drawable.setCornerRadius((float) n);
        this.view.setBackgroundDrawable((Drawable) this.drawable);
    }

    public void 权重(int n) {
        if (this.基础布局参数 instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) this.基础布局参数).weight = n;
            this.view.setLayoutParams(this.基础布局参数);
        }
    }

    public int 权重() {
        if (this.基础布局参数 instanceof LinearLayout.LayoutParams) {
            return (int) ((LinearLayout.LayoutParams) this.基础布局参数).weight;
        }
        return -1;
    }

    public void 行权重(int n) {
        if (this.基础布局参数 instanceof GridLayout.LayoutParams) {
            GridLayout gridLayout = (GridLayout) ((ViewGroup) this.view.getParent());
            GridLayout.Spec spec = GridLayout.spec((int) (gridLayout.indexOfChild(this.view) / gridLayout.getColumnCount()), (float) n);
            GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) this.基础布局参数;
            layoutParams.rowSpec = spec;
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 列权重(int n) {
        if (this.基础布局参数 instanceof GridLayout.LayoutParams) {
            GridLayout gridLayout = (GridLayout) ((ViewGroup) this.view.getParent());
            GridLayout.Spec spec = GridLayout.spec((int) (gridLayout.indexOfChild(this.view) % gridLayout.getColumnCount()), (float) n);
            GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) this.基础布局参数;
            layoutParams.columnSpec = spec;
            this.view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        }
    }

    public void 布局重力(int n) {
        if (this.基础布局参数 instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) this.基础布局参数).gravity = n;
        } else if (this.基础布局参数 instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) this.基础布局参数).gravity = n;
        } else if (this.基础布局参数 instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) this.基础布局参数).gravity = n;
        } else if (this.基础布局参数 instanceof GridLayout.LayoutParams) {
            ((GridLayout.LayoutParams) this.基础布局参数).setGravity(n);
        } else if (this.基础布局参数 instanceof DrawerLayout.LayoutParams) {
            ((DrawerLayout.LayoutParams) this.基础布局参数).gravity = n;
        } else {
            try {
                String string = this.基础布局参数.getClass().getName();
                Class clazz = Class.forName((String) string);
                Field field = clazz.getDeclaredField("gravity");
                field.setAccessible(true);
                field.set((Object) this.基础布局参数, (Object) n);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.view.setLayoutParams(this.基础布局参数);
    }

    public void 重力属性(int n) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setGravity(n);
        } else if (this.view instanceof LinearLayout) {
            ((LinearLayout) this.view).setGravity(n);
        } else if (this.view instanceof RelativeLayout) {
            ((RelativeLayout) this.view).setGravity(n);
        } else if (this.view instanceof Spinner) {
            ((Spinner) this.view).setGravity(n);
        }
    }

    public Object 标记() {
        return this.tag;
    }

    public void 标记(Object object) {
        this.tag = object;
    }

    public boolean 支持单击() {
        return this.view.isClickable();
    }

    public boolean 支持长按() {
        return this.view.isLongClickable();
    }

    public void 支持单击(boolean bl) {
        this.view.setClickable(bl);
        if (bl) {
            this.hasClickListener = true;
            this.view.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (可视化组件.this.$event_internal_被单击 != null) {
                        可视化组件.this.$event_internal_被单击.被单击();
                    }
                }
            });
        } else {
            this.view.setOnClickListener(null);
        }
    }

    public void 支持长按(boolean bl) {
        this.view.setLongClickable(bl);
        if (bl) {
            this.view.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View view) {
                    if (可视化组件.this.$event_internal_被长按 != null) {
                        可视化组件.this.$event_internal_被长按.被长按();
                    }
                    return true;
                }
            });
        } else {
            this.view.setOnLongClickListener(null);
        }
    }

    public void 支持触摸(boolean bl) {
        if (bl) {
            this.view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int n = 0;
                    int n2 = 0;
                    int n3 = n;
                    int n4 = n2;
                    n = (int) motionEvent.getRawX();
                    n2 = (int) motionEvent.getRawY();
                    switch (motionEvent.getAction()) {
                        case 0: {
                            n3 = n;
                            n4 = n2;
                            if (可视化组件.this.$event_internal_被按下 == null) break;
                            可视化组件.this.$event_internal_被按下.被按下(n, n2);
                            break;
                        }
                        case 1: {
                            n3 = n;
                            n4 = n2;
                            if (可视化组件.this.$event_internal_被弹起 == null) break;
                            可视化组件.this.$event_internal_被弹起.被弹起(n, n2);
                            break;
                        }
                        case 2: {
                            if (可视化组件.this.$event_internal_被移动 == null) break;
                            可视化组件.this.$event_internal_被移动.被移动(n3, n4, n, n2);
                        }
                    }
                    可视化组件.this.detector.onTouchEvent(motionEvent);
                    return !可视化组件.this.hasClickListener;
                }
            });
        } else {
            this.view.setOnTouchListener(null);
        }
    }

    public void 支持焦点改变监听(boolean bl) {
        if (bl) {
            this.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View view, boolean bl) {
                    if (bl) {
                        if (可视化组件.this.$event_internal_获得焦点 != null) {
                            可视化组件.this.$event_internal_获得焦点.获得焦点();
                        }
                    } else if (可视化组件.this.$event_internal_失去焦点 != null) {
                        可视化组件.this.$event_internal_失去焦点.失去焦点();
                    }
                }
            });
        } else {
            this.view.setOnFocusChangeListener(null);
        }
    }

    public boolean 硬件加速() {
        return this.view.isHardwareAccelerated();
    }

    public void 硬件加速(boolean bl) {
        this.view.setLayerType(bl ? 2 : 1, null);
    }

    public void 到顶层() {
        this.view.bringToFront();
        if (this.view.getVisibility() == 0) {
            this.view.setVisibility(4);
            this.view.setVisibility(0);
            return;
        }
        this.view.setVisibility(0);
        this.view.setVisibility(4);
    }

    public void 刷新() {
        this.view.invalidate();
    }

    public void 模拟单击() {
        this.view.performClick();
    }

    public void 模拟长按() {
        this.view.performLongClick();
    }

    public void 请求焦点() {
        this.view.requestFocus();
    }

    public void 取消焦点() {
        this.view.clearFocus();
    }

    public 布局组件 取父组件() {
        return this.parent;
    }

    public Activity 取窗口() {
        return this.窗口环境 instanceof Activity ? (Activity) this.窗口环境 : null;
    }

    public void 初始化() {
        this.view.setTag((Object) this);
        this.sld = new StateListDrawable();
        this.am = this.窗口环境.getResources().getAssets();
        this.wm = (WindowManager) this.窗口环境.getSystemService("window");
        this.detector = new GestureDetector((GestureDetector.OnGestureListener) new GestureDetector.SimpleOnGestureListener() {

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (可视化组件.this.$event_internal_触摸手势 != null) {
                    可视化组件.this.$event_internal_触摸手势.触摸手势(0);
                }
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (可视化组件.this.$event_internal_触摸手势 != null) {
                    可视化组件.this.$event_internal_触摸手势.触摸手势(1);
                }
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int n;
                if (Math.abs((float) f) > Math.abs((float) f2)) {
                    n = f > 0.0f ? 8 : 9;
                } else {
                    int n2 = n = f2 > 0.0f ? 6 : 7;
                }
                if (可视化组件.this.$event_internal_触摸手势 != null) {
                    可视化组件.this.$event_internal_触摸手势.触摸手势(n);
                }
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int n;
                int n2 = (int) (motionEvent.getRawX() - motionEvent2.getRawX());
                int n3 = (int) (motionEvent.getRawY() - motionEvent2.getRawY());
                if (Math.abs((int) n2) > Math.abs((int) n3)) {
                    n = n2 > 0 ? 4 : 5;
                } else {
                    int n4 = n = n3 > 0 ? 2 : 3;
                }
                if (可视化组件.this.$event_internal_触摸手势 != null) {
                    可视化组件.this.$event_internal_触摸手势.触摸手势(n);
                }
                return true;
            }
        });
        if (this.view instanceof Button) {
            this.初始化基本事件();
            this.hasClickListener = true;
        }
    }

    public void 实例化布局参数() {
        this.基础布局参数 = this.view.getLayoutParams();
    }

    public void 初始化基本事件() {
        this.view.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (可视化组件.this.$event_internal_被单击 != null) {
                    可视化组件.this.$event_internal_被单击.被单击();
                }
            }
        });
        this.view.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View view) {
                if (可视化组件.this.$event_internal_被长按 != null) {
                    可视化组件.this.$event_internal_被长按.被长按();
                }
                return true;
            }
        });
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int n = 0;
                int n2 = 0;
                int n3 = n;
                int n4 = n2;
                n = (int) motionEvent.getRawX();
                n2 = (int) motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case 0: {
                        n3 = n;
                        n4 = n2;
                        if (可视化组件.this.$event_internal_被按下 == null) break;
                        可视化组件.this.$event_internal_被按下.被按下(n, n2);
                        break;
                    }
                    case 1: {
                        n3 = n;
                        n4 = n2;
                        if (可视化组件.this.$event_internal_被弹起 == null) break;
                        可视化组件.this.$event_internal_被弹起.被弹起(n, n2);
                        break;
                    }
                    case 2: {
                        if (可视化组件.this.$event_internal_被移动 == null) break;
                        可视化组件.this.$event_internal_被移动.被移动(n3, n4, n, n2);
                    }
                }
                可视化组件.this.detector.onTouchEvent(motionEvent);
                return false;
            }
        });
        this.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean bl) {
                if (bl) {
                    if (可视化组件.this.$event_internal_获得焦点 != null) {
                        可视化组件.this.$event_internal_获得焦点.获得焦点();
                    }
                } else if (可视化组件.this.$event_internal_失去焦点 != null) {
                    可视化组件.this.$event_internal_失去焦点.失去焦点();
                }
            }
        });
    }

    private int getRealValue(String string) {
        double d = string.endsWith("宽") ? Double.parseDouble((String) string.replace((CharSequence) "宽", (CharSequence) "").trim()) * (double) 系统操作.取屏幕宽度(this.窗口环境) : (string.endsWith("高") ? Double.parseDouble((String) string.replace((CharSequence) "高", (CharSequence) "").trim()) * (double) (系统操作.取屏幕高度_不含导航栏(this.窗口环境) - 系统操作.取状态栏高度(this.窗口环境)) : (string.endsWith("dp") ? (double) this.dip2px(Integer.parseInt((String) string.replace((CharSequence) "dp", (CharSequence) ""))) : (double) Integer.parseInt((String) string)));
        return (int) d;
    }

    @Override
    public View getView() {
        return this.view;
    }

    public void 置被单击(被单击 被单击2) {
        this.$event_internal_被单击 = 被单击2;
    }

    public void 置被长按(被长按 被长按2) {
        this.$event_internal_被长按 = 被长按2;
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

    public void 置触摸手势(触摸手势 触摸手势2) {
        this.$event_internal_触摸手势 = 触摸手势2;
    }

    public void 置获得焦点(获得焦点 获得焦点2) {
        this.$event_internal_获得焦点 = 获得焦点2;
    }

    public void 置失去焦点(失去焦点 失去焦点2) {
        this.$event_internal_失去焦点 = 失去焦点2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 失去焦点 {
        public void 失去焦点();
    }

    public static interface 获得焦点 {
        public void 获得焦点();
    }

    public static interface 触摸手势 {
        public void 触摸手势(int var1);
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

    public static interface 被长按 {
        public void 被长按();
    }

    public static interface 被单击 {
        public void 被单击();
    }
}

