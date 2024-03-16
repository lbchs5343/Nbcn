/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.util.SparseArray
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

import lbchs.utils.EasyHttp;

public class 图片框
        extends 可视化组件 {
    private static final SparseArray<ImageView.ScaleType> intToType = new SparseArray<>();

    static {
        intToType.put(0, ImageView.ScaleType.CENTER);
        intToType.put(1, ImageView.ScaleType.CENTER_CROP);
        intToType.put(2, ImageView.ScaleType.CENTER_INSIDE);
        intToType.put(3, ImageView.ScaleType.FIT_CENTER);
        intToType.put(4, ImageView.ScaleType.FIT_END);
        intToType.put(5, ImageView.ScaleType.FIT_XY);
        intToType.put(6, ImageView.ScaleType.MATRIX);
    }

    private final Context 窗口环境;

    public 图片框(Context context) {
        super(context);
        this.窗口环境 = context;
    }

    @Override
    public View 创建视图() {
        return new ImageView(this.取上下文());
    }

    public void 图片(String string) {
        if (string.startsWith("http")) {
            EasyHttp.get(string, new EasyHttp.OnRequestListener() {

                @Override
                public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                    图片框.this.载入字节图片(byArray);
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

    public void 着色(int n) {
        this.getView().setColorFilter(n);
    }

    public void 着色(String string) {
        this.着色(Color.parseColor(string));
    }

    public void 拉伸方式(int n) {
        this.getView().setScaleType((intToType.get(n)));
    }

    public int 拉伸方式() {
        return intToType.indexOfValue(this.getView().getScaleType());
    }

    @Override
    public void 透明度(int n) {
        this.getView().setImageAlpha(n);
    }

    @Override
    public int 透明度() {
        return this.getView().getImageAlpha();
    }

    public void 自适应大小(boolean bl) {
        this.getView().setAdjustViewBounds(bl);
    }

    public boolean 自适应大小() {
        return this.getView().getAdjustViewBounds();
    }

    public void 载入字节图片(byte[] byArray) {
        this.getView().setImageBitmap(BitmapFactory.decodeByteArray(byArray, 0, byArray.length));
    }

    public void 载入超大图片(String string) {
        this.载入超大图片(string, 0, 0);
    }

    public void 载入超大图片(String string, int n, int n2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (string.startsWith("/")) {
            Bitmap bitmap = BitmapFactory.decodeFile(string, options);
            options.inSampleSize = this.calculateInSampleSize(options, bitmap.getWidth(), bitmap.getHeight());
            options.inJustDecodeBounds = false;
            this.getView().setImageBitmap(BitmapFactory.decodeFile(string, options));
        } else {
            try {
                InputStream inputStream = this.窗口环境.getAssets().open(string);
                Drawable.createFromStream(inputStream, string);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                if (bitmap != null) {
                    options.inSampleSize = n == 0 && n2 == 0 ? this.calculateInSampleSize(options, bitmap.getWidth(), bitmap.getHeight()) : this.calculateInSampleSize(options, n, n2);
                }
                options.inJustDecodeBounds = false;
                this.getView().setImageBitmap(BitmapFactory.decodeStream(inputStream, null, options));
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void 清除着色() {
        this.getView().clearColorFilter();
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int n, int n2) {
        int n3;
        int n4 = options.outHeight;
        int n5 = options.outWidth;
        if (n4 <= n2 && n5 <= n) {
            return 1;
        }
        int n6 = Math.round(((float) n4 / (float) n2));
        if (n6 < (n3 = Math.round(((float) n5 / (float) n)))) {
            return n6;
        }
        return n3;
    }

    public ImageView getView() {
        return (ImageView) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

