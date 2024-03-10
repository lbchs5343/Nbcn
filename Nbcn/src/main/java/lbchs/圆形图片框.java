/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.BitmapFactory
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.util.SparseArray
 *  android.view.View
 *  android.widget.ImageView$ScaleType
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView.ScaleType;

import java.io.InputStream;

public class 圆形图片框
        extends 可视化组件 {
    private static final SparseArray<ScaleType> intToType;

    static {
        intToType = new SparseArray<>();
        intToType.put(0, ScaleType.CENTER);
        intToType.put(1, ScaleType.CENTER_CROP);
        intToType.put(2, ScaleType.CENTER_INSIDE);
        intToType.put(3, ScaleType.FIT_CENTER);
        intToType.put(4, ScaleType.FIT_END);
        intToType.put(5, ScaleType.FIT_XY);
        intToType.put(6, ScaleType.MATRIX);
    }

    private final Context 窗口环境;

    public 圆形图片框(Context context) {
        super(context);
        this.窗口环境 = context;
    }

    public CircleImageView getView() {
        return (CircleImageView) super.getView();
    }

    @Override
    public View 创建视图() {
        return new CircleImageView(this.取上下文());
    }

    public void 图片(String string) {
        if (string.startsWith("http")) {
            EasyHttp.get(string, new EasyHttp.OnRequestListener() {

                @Override
                public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                    圆形图片框.this.载入字节图片(byArray);
                }

                @Override
                public void onFailed(String string, String string2, byte[] byArray) {
                }

                @Override
                public void onProgressChanged(int n) {
                }
            });
        } else if (string.startsWith("/")) {
            this.getView().setImageBitmap(BitmapFactory.decodeFile(string));
        } else {
            try {
                InputStream inputStream = this.窗口环境.getAssets().open(string);
                Drawable drawable = Drawable.createFromStream(inputStream, string);
                this.getView().setImageDrawable(drawable);
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void 图片(int n) {
        this.getView().setImageResource(n);
    }

    @Override
    public void 阴影(int n) {
        this.getView().setElevation2(n);
    }

    @Override
    public void 边框宽度(int n) {
        this.getView().setBorderWidth(n);
    }

    public int 边框宽度() {
        return this.getView().getBorderWidth();
    }

    @Override
    public void 边框颜色(int n) {
        this.getView().setBorderColor(n);
    }

    @Override
    public void 边框颜色(String string) {
        this.边框颜色(Color.parseColor(string));
    }

    public int 边框颜色() {
        return this.getView().getBorderColor();
    }

    public void 载入字节图片(byte[] byArray) {
        this.getView().setImageBitmap(BitmapFactory.decodeByteArray(byArray, 0, byArray.length));
    }

    @Override
    public void 初始化事件() {
    }
}

