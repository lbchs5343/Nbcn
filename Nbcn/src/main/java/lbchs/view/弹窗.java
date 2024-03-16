/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Color
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.NinePatchDrawable
 *  android.widget.PopupWindow
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.widget.PopupWindow;

import java.io.IOException;

import lbchs.组件容器;

public class 弹窗
        extends 组件 {
    private final Context activity;
    private final PopupWindow mPopupWindow;
    private 组件容器 container;

    public 弹窗(Context context) {
        super(context);
        this.activity = context;
        this.mPopupWindow = new PopupWindow(context);
    }

    public 弹窗(Context context, int n, int n2) {
        super(context);
        this.activity = context;
        this.mPopupWindow = new PopupWindow(context);
        this.mPopupWindow.setWidth(n);
        this.mPopupWindow.setHeight(n2);
    }

    public void 组件(组件容器 组件容器2) {
        this.container = 组件容器2;
        this.mPopupWindow.setContentView(this.container.取根组件().getView());
    }

    public 组件容器 组件() {
        return this.container;
    }

    public void 动画风格(int n) {
        this.mPopupWindow.setAnimationStyle(n);
    }

    @Override
    public void 高度(int n) {
        this.mPopupWindow.setHeight(n);
    }

    @Override
    public void 宽度(int n) {
        this.mPopupWindow.setWidth(n);
    }

    public void 背景颜色(int n) {
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(n));
    }

    public void 背景颜色(String string) {
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor(string)));
    }

    public void 背景图片(int n) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.activity.getResources(), n);
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }

    public void 背景图片(String string) {
        try {
            this.mPopupWindow.setBackgroundDrawable(Drawable.createFromStream(this.activity.getAssets().open(string), string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 点九图(int n) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.activity.getResources(), n);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(bitmap, bitmap.getNinePatchChunk(), new Rect(), String.valueOf(n));
        this.mPopupWindow.setBackgroundDrawable(ninePatchDrawable);
    }

    public void 点九图(String string) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.activity.getAssets().open(string));
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(bitmap, bitmap.getNinePatchChunk(), new Rect(), string);
            this.mPopupWindow.setBackgroundDrawable(ninePatchDrawable);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 显示(可视化组件 可视化组件2) {
        this.mPopupWindow.showAsDropDown(可视化组件2.getView());
    }

    public void 显示(可视化组件 可视化组件2, int n, int n2, int n3) {
        this.mPopupWindow.showAtLocation(可视化组件2.getView(), n, n2, n3);
    }

    public void 关闭() {
        this.mPopupWindow.dismiss();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

