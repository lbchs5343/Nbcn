/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.Color
 *  android.graphics.Paint$Style
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.RippleDrawable
 *  android.graphics.drawable.ShapeDrawable
 *  android.graphics.drawable.StateListDrawable
 *  android.graphics.drawable.shapes.RoundRectShape
 *  android.graphics.drawable.shapes.Shape
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;

import lbchs.view.可视化组件;

public class 组件样式类 {
    public static void 置水波纹样式(可视化组件 可视化组件2, float f, int n, int n2) {
        int[][] nArrayArray = new int[][]{{16842919}, {16842908}, {16843518}, new int[0]};
        int[] nArray = new int[]{n2, n2, n2, n};
        ColorStateList colorStateList = new ColorStateList(nArrayArray, nArray);
        float[] fArray = new float[]{f, f, f, f, f, f, f, f};
        RoundRectShape roundRectShape = new RoundRectShape(fArray, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(n);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable();
        shapeDrawable2.setShape(roundRectShape);
        shapeDrawable2.getPaint().setColor(n);
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
        RippleDrawable rippleDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rippleDrawable = new RippleDrawable(colorStateList, shapeDrawable2, shapeDrawable);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            可视化组件2.getView().setBackground(rippleDrawable);
        }
    }

    public static void 置水波纹样式(可视化组件 可视化组件2, float f, String string, String string2) {
        组件样式类.置水波纹样式(可视化组件2, f, Color.parseColor(string), Color.parseColor(string2));
    }

    public static void 置水波纹样式2(可视化组件 可视化组件2, int n) {
        int[][] nArrayArray = new int[][]{{16842919}, {16842908}, {16843518}, new int[0]};
        int[] nArray = new int[]{n, n, n, n};
        ColorStateList colorStateList = new ColorStateList(nArrayArray, nArray);
        RippleDrawable rippleDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rippleDrawable = new RippleDrawable(colorStateList, null, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            可视化组件2.getView().setBackground(rippleDrawable);
        }
    }

    public static void 置水波纹样式2(可视化组件 可视化组件2, String string) {
        组件样式类.置水波纹样式2(可视化组件2, Color.parseColor(string));
    }

    public static void 置水波纹样式3(可视化组件 可视化组件2, float f, int n) {
        int[][] nArrayArray = new int[][]{{16842919}, {16842908}, {16843518}, new int[0]};
        int[] nArray = new int[]{n, n, n, n};
        ColorStateList colorStateList = new ColorStateList(nArrayArray, nArray);
        float[] fArray = new float[]{f, f, f, f, f, f, f, f};
        RoundRectShape roundRectShape = new RoundRectShape(fArray, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(n);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        RippleDrawable rippleDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rippleDrawable = new RippleDrawable(colorStateList, null, shapeDrawable);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            可视化组件2.getView().setBackground(rippleDrawable);
        }
    }

    public static void 置水波纹样式3(可视化组件 可视化组件2, float f, String string) {
        组件样式类.置水波纹样式3(可视化组件2, f, Color.parseColor(string));
    }

    public static void 置圆角背景(可视化组件 可视化组件2, int n, float f, float f2, float f3, float f4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{f, f, f2, f2, f3, f3, f4, f4});
        gradientDrawable.setColor(n);
        可视化组件2.getView().setBackground(gradientDrawable);
    }

    public static void 置圆角边框(可视化组件 可视化组件2, int n, int n2, float f, float f2, float f3, float f4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{f, f, f2, f2, f3, f3, f4, f4});
        gradientDrawable.setStroke(n, n2);
        可视化组件2.getView().setBackground(gradientDrawable);
    }

    public static void 置圆角背景边框(可视化组件 可视化组件2, int n, int n2, int n3, float f, float f2, float f3, float f4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{f, f, f2, f2, f3, f3, f4, f4});
        gradientDrawable.setStroke(n2, n3);
        gradientDrawable.setColor(n);
        可视化组件2.getView().setBackground(gradientDrawable);
    }

    public static void 置圆角(可视化组件 可视化组件2, float f, float f2, float f3, float f4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{f, f, f2, f2, f3, f3, f4, f4});
        可视化组件2.getView().setBackground(gradientDrawable);
    }

    public static void 置普通样式(可视化组件 可视化组件2, float f, int n, int n2) {
        float[] fArray = new float[]{f, f, f, f, f, f, f, f};
        RoundRectShape roundRectShape = new RoundRectShape(fArray, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setColor(n2);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable();
        shapeDrawable2.setShape(roundRectShape);
        shapeDrawable2.getPaint().setColor(n);
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, shapeDrawable);
        stateListDrawable.addState(new int[]{0}, shapeDrawable2);
        可视化组件2.getView().setBackground(stateListDrawable);
    }

    public static void 置普通样式(可视化组件 可视化组件2, float f, String string, String string2) {
        组件样式类.置普通样式(可视化组件2, f, Color.parseColor(string), Color.parseColor(string2));
    }

    public void 初始化事件() {
    }
}

