/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.NinePatch
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Typeface
 *  android.graphics.Xfermode
 *  android.graphics.drawable.Drawable
 *  android.text.Layout$Alignment
 *  android.text.StaticLayout
 *  android.text.TextPaint
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class 画板
        extends 可视化组件
        implements View.OnClickListener,
        View.OnTouchListener {
    private Paint backgroundColor;
    private String backgroundImage = "";
    private int backgroundImage2 = -1;
    private int canvasheight;
    private int canvaswidth;
    private int currX;
    private int currY;
    private GestureDetector mGestureDetector;
    private Paint paintColor;
    private int paintsize;
    private int paintstyle;
    private float textsize;
    private Typeface typeface = null;
    private int 索引;
    private 被单击 $event_internal_被单击;
    private 被按下 $event_internal_被按下;
    private 被弹起 $event_internal_被弹起;
    private 被移动 $event_internal_被移动;
    private 触摸手势 $event_internal_触摸手势;

    public 画板(Context context) {
        super(context);
        this.canvasheight = context.getWallpaperDesiredMinimumWidth();
        this.canvaswidth = context.getWallpaperDesiredMinimumWidth();
    }

    public static int extractARGB(Paint paint) {
        return paint.getColor() | paint.getAlpha() << 24;
    }

    public static void changePaint(Paint paint, int n) {
        if ((0xFF000000 & n) == 0) {
            paint.setAlpha(0);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            return;
        }
        paint.setColor(0xFFFFFF & n);
        paint.setAlpha(n >> 24 & 0xFF);
        paint.setXfermode(null);
    }

    public void onClick(View view) {
        if (this.$event_internal_被单击 != null) {
            this.$event_internal_被单击.被单击();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int lastX = this.currX;
        int lastY = this.currY;
        this.currX = (int) motionEvent.getX();
        this.currY = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0: {
                if (this.$event_internal_被按下 == null) break;
                this.$event_internal_被按下.被按下(this.currX, this.currY);
                break;
            }
            case 1: {
                if (this.$event_internal_被弹起 == null) break;
                this.$event_internal_被弹起.被弹起(this.currX, this.currY);
                break;
            }
            case 2: {
                if (this.$event_internal_被移动 == null) break;
                this.$event_internal_被移动.被移动(lastX, lastY, this.currX, this.currY);
            }
        }
        this.mGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public View 创建视图() {
        CanvasView canvasView = new CanvasView(this.取上下文());
        canvasView.setOnClickListener(this);
        canvasView.setFocusable(true);
        this.backgroundColor = new Paint();
        this.paintColor = new Paint(1);
        this.paintColor.setStrokeWidth(1.0f);
        this.mGestureDetector = new GestureDetector(new MyGestureListener());
        canvasView.setOnTouchListener(this);
        return canvasView;
    }

    @Override
    public int 背景颜色() {
        return 画板.extractARGB(this.backgroundColor);
    }

    @Override
    public void 背景颜色(int n) {
        画板.changePaint(this.backgroundColor, n);
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawPaint(this.backgroundColor);
        canvasView.invalidate();
    }

    public String 背景图片() {
        return this.backgroundImage;
    }

    @Override
    public void 背景图片(String string) {
        this.backgroundImage = string;
        try {
            if (string.length() == 0) {
                return;
            }
            if (string.startsWith("/")) {
                CanvasView canvasView = (CanvasView) this.getView();
                画板.changePaint(this.backgroundColor, 0);
                canvasView.canvas.drawPaint(this.backgroundColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvasView.setBackgroundDrawable(Drawable.createFromStream(Files.newInputStream(Paths.get(string)), string));
                }
                canvasView.invalidate();
                return;
            }
            CanvasView canvasView = (CanvasView) this.getView();
            画板.changePaint(this.backgroundColor, 0);
            canvasView.canvas.drawPaint(this.backgroundColor);
            canvasView.setBackgroundDrawable(Drawable.createFromStream(canvasView.getContext().getAssets().open(string), string));
            canvasView.invalidate();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public int 背景图片2() {
        return this.backgroundImage2;
    }

    public void 背景图片2(int n) {
        this.backgroundImage2 = n;
        CanvasView canvasView = (CanvasView) this.getView();
        画板.changePaint(this.backgroundColor, 0);
        canvasView.canvas.drawPaint(this.backgroundColor);
        canvasView.setBackgroundResource(n);
        canvasView.invalidate();
    }

    public int 画笔颜色() {
        return 画板.extractARGB(this.paintColor);
    }

    public void 画笔颜色(int n) {
        画板.changePaint(this.paintColor, n);
    }

    public int 画笔粗细() {
        return this.paintsize;
    }

    public void 画笔粗细(int n) {
        if (n == 0) {
            this.paintColor.setStrokeWidth(1.0f);
            this.paintsize = 1;
            return;
        }
        this.paintColor.setStrokeWidth((float) n);
        this.paintsize = n;
    }

    public int 画笔类型() {
        return this.paintstyle;
    }

    public void 画笔类型(int n) {
        if (n == 1) {
            this.paintColor.setStyle(Paint.Style.FILL);
        } else {
            this.paintColor.setStyle(Paint.Style.STROKE);
        }
        this.paintstyle = n;
    }

    public int 画布宽度() {
        return this.canvaswidth;
    }

    public void 画布宽度(int n) {
        this.canvaswidth = n;
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.reset(this.canvaswidth, this.canvasheight);
        canvasView.invalidate();
    }

    public int 画布高度() {
        return this.canvasheight;
    }

    public void 画布高度(int n) {
        this.canvasheight = n;
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.reset(this.canvaswidth, this.canvasheight);
        canvasView.invalidate();
    }

    public float 字体大小() {
        return this.textsize;
    }

    public void 字体大小(int n) {
        this.paintColor.setTextSize((float) n);
        this.textsize = n;
    }

    public void 抗锯齿(boolean bl) {
        this.paintColor.setAntiAlias(bl);
    }

    public void 字体(String string) {
        this.typeface = string.startsWith("/") ? Typeface.createFromFile(string) : Typeface.createFromAsset(this.取上下文().getAssets(), string);
        this.paintColor.setTypeface(this.typeface);
    }

    public void 置画布尺寸(int n, int n2) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.reset(n, n2);
        canvasView.invalidate();
    }

    public void 清空() {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.bitmap.eraseColor(0);
        canvasView.invalidate();
    }

    public void 保存画面(String string, int n) {
        CanvasView canvasView = (CanvasView) this.getView();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(string);
            if (n == 1) {
                canvasView.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            } else {
                canvasView.bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
            fileOutputStream.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public int 取点颜色值(int n, int n2) {
        return ((CanvasView) this.getView()).bitmap.getPixel(n, n2);
    }

    public void 保存状态() {
        ((CanvasView) this.getView()).canvas.save();
    }

    public void 恢复状态() {
        ((CanvasView) this.getView()).canvas.restore();
    }

    public void 旋转画布(int n, int n2, int n3) {
        ((CanvasView) this.getView()).canvas.rotate((float) n3, (float) n, (float) n2);
    }

    public void 画点(int n, int n2) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawPoint((float) n, (float) n2, this.paintColor);
        canvasView.invalidate();
    }

    public void 画圆(int n, int n2, float f) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawCircle((float) n, (float) n2, f, this.paintColor);
        canvasView.invalidate();
    }

    public void 画直线(int n, int n2, int n3, int n4) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawLine((float) n, (float) n2, (float) n3, (float) n4, this.paintColor);
        canvasView.invalidate();
    }

    public void 画文字(int n, int n2, String string) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawText(string, (float) n, (float) n2, this.paintColor);
        canvasView.invalidate();
    }

    public void 画文字2(int n, int n2, String string, float f, int n3) {
        CanvasView canvasView = (CanvasView) this.getView();
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(this.textsize);
        textPaint.setColor(this.画笔颜色());
        textPaint.setStrokeWidth((float) this.paintsize);
        if (this.typeface != null) {
            textPaint.setTypeface(this.typeface);
        }
        if (this.paintstyle == 1) {
            textPaint.setStyle(Paint.Style.FILL);
        } else {
            textPaint.setStyle(Paint.Style.STROKE);
        }
        StaticLayout staticLayout = new StaticLayout(string, textPaint, n3, Layout.Alignment.ALIGN_NORMAL, f, 0.0f, true);
        canvasView.canvas.translate((float) n, (float) n2);
        staticLayout.draw(canvasView.canvas);
        canvasView.canvas.translate((float) (-n), (float) (-n2));
        canvasView.invalidate();
        staticLayout.getLineCount();
    }

    public void 画矩形(int n, int n2, int n3, int n4) {
        CanvasView canvasView = (CanvasView) this.getView();
        Rect rect = new Rect();
        rect.left = n;
        rect.top = n2;
        rect.right = n + n3;
        rect.bottom = n2 + n4;
        canvasView.canvas.drawRect(rect, this.paintColor);
        canvasView.invalidate();
    }

    public void 画圆角矩形(int n, int n2, int n3, int n4, float f) {
        CanvasView canvasView = (CanvasView) this.getView();
        RectF rectF = new RectF();
        rectF.left = n;
        rectF.top = n2;
        rectF.right = n + n3;
        rectF.bottom = n2 + n4;
        canvasView.canvas.drawRoundRect(rectF, f, f, this.paintColor);
        canvasView.invalidate();
    }

    public void 画贝塞尔曲线(int n, int n2, int n3, int n4, int n5, int n6) {
        Path path = new Path();
        path.moveTo((float) n, (float) n2);
        path.quadTo((float) n3, (float) n4, (float) n5, (float) n6);
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawPath(path, this.paintColor);
        canvasView.invalidate();
    }

    public void 画图片(String string, int n, int n2) {
        try {
            if (string.length() == 0) {
                return;
            }
            if (!string.startsWith("/")) {
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(BitmapFactory.decodeStream(this.取上下文().getResources().getAssets().open(string)), (float) n, (float) n2, this.paintColor);
                canvasView.invalidate();
            } else if (new File(string).exists()) {
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(BitmapFactory.decodeFile(string), (float) n, (float) n2, this.paintColor);
                canvasView.invalidate();
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 画字节图片(byte[] byArray, int n, int n2) {
        CanvasView canvasView = (CanvasView) this.getView();
        canvasView.canvas.drawBitmap(BitmapFactory.decodeByteArray(byArray, 0, byArray.length), (float) n, (float) n2, this.paintColor);
        canvasView.invalidate();
    }

    public void 画图片缩放(String string, int n, int n2, int n3, int n4) {
        try {
            if (string.length() == 0) {
                return;
            }
            if (!string.startsWith("/")) {
                Bitmap bitmap = BitmapFactory.decodeStream(this.取上下文().getResources().getAssets().open(string));
                Rect rect = new Rect();
                rect.left = n;
                rect.top = n2;
                rect.right = n + n3;
                rect.bottom = n2 + n4;
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(bitmap, null, rect, this.paintColor);
                canvasView.invalidate();
            } else if (new File(string).exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(string);
                Rect rect = new Rect();
                rect.left = n;
                rect.top = n2;
                rect.right = n + n3;
                rect.bottom = n2 + n4;
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(bitmap, null, rect, this.paintColor);
                canvasView.invalidate();
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 画图片缩放2(int n, int n2, int n3, int n4, int n5) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.取上下文().getResources(), n);
        new NinePatch(bitmap, bitmap.getNinePatchChunk(), null).draw(((CanvasView) this.getView()).canvas, new Rect(n2, n3, n4, n5));
    }

    public void 画图片缩放部分(String string, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        try {
            if (string.length() == 0) {
                return;
            }
            if (!string.startsWith("/")) {
                Bitmap bitmap = Bitmap.createBitmap(BitmapFactory.decodeStream(this.取上下文().getResources().getAssets().open(string)), n5, n6, n7, n8);
                Rect rect = new Rect();
                rect.left = n;
                rect.top = n2;
                rect.right = n + n3;
                rect.bottom = n2 + n4;
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(bitmap, null, rect, this.paintColor);
                canvasView.invalidate();
            } else if (new File(string).exists()) {
                Bitmap bitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(string), n5, n6, n7, n8);
                Rect rect = new Rect();
                rect.left = n;
                rect.top = n2;
                rect.right = n + n3;
                rect.bottom = n2 + n4;
                CanvasView canvasView = (CanvasView) this.getView();
                canvasView.canvas.drawBitmap(bitmap, null, rect, this.paintColor);
                canvasView.invalidate();
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 画弧形(int n, int n2, int n3, int n4, float f, float f2, boolean bl) {
        CanvasView canvasView = (CanvasView) this.getView();
        RectF rectF = new RectF();
        rectF.left = n;
        rectF.top = n2;
        rectF.right = n + n3;
        rectF.bottom = n2 + n4;
        canvasView.canvas.drawArc(rectF, f, f2, bl, this.paintColor);
        canvasView.invalidate();
    }

    public int 取文字宽度(String string) {
        Rect rect = new Rect();
        this.paintColor.getTextBounds(string, 0, string.length(), rect);
        return rect.width();
    }

    public int 取文字高度(String string) {
        Rect rect = new Rect();
        this.paintColor.getTextBounds(string, 0, string.length(), rect);
        return rect.height();
    }

    public void 置被单击(被单击 被单击2) {
        this.$event_internal_被单击 = 被单击2;
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

    @Override
    public void 初始化事件() {
    }

    public interface 触摸手势 {
        void 触摸手势(int var1);
    }

    public interface 被移动 {
        void 被移动(int var1, int var2, int var3, int var4);
    }

    public interface 被弹起 {
        void 被弹起(int var1, int var2);
    }

    public interface 被按下 {
        void 被按下(int var1, int var2);
    }

    public interface 被单击 {
        void 被单击();
    }

    public static class CanvasView
            extends View {
        protected Bitmap bitmap;
        protected Canvas canvas;

        public CanvasView(Context context) {
            super(context);
            this.bitmap = Bitmap.createBitmap(context.getWallpaperDesiredMinimumWidth(), context.getWallpaperDesiredMinimumWidth(), Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(this.bitmap);
        }

        protected void onDraw(Canvas canvas) {
            if (this.bitmap != null) {
                canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, null);
            }
        }

        public void reset(int n, int n2) {
            this.bitmap = Bitmap.createBitmap(n, n2, Bitmap.Config.ARGB_8888);
            this.canvas.setBitmap(this.bitmap);
        }
    }

    class MyGestureListener
            extends GestureDetector.SimpleOnGestureListener {

        private int n;

        MyGestureListener() {
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {

            int n2 = (int) (motionEvent.getRawX() - motionEvent2.getRawX());
            int n3 = (int) (motionEvent.getRawY() - motionEvent2.getRawY());
            int n4 = Math.abs(n2) > Math.abs(n3) ? (n2 > 0 ? 4 : 5) : (n = n3 > 0 ? 2 : 3);

            画板.this.$event_internal_触摸手势.触摸手势(n4);

            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {


            int n2 = Math.abs(f) > Math.abs(f2) ? (f > 0.0f ? 8 : 9) : (n = f2 > 0.0f ? 6 : 7);
            if (画板.this.$event_internal_触摸手势 != null) {
                画板.this.$event_internal_触摸手势.触摸手势(n2);
            }
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (画板.this.$event_internal_触摸手势 != null) {
                画板.this.$event_internal_触摸手势.触摸手势(0);
            }
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (画板.this.$event_internal_触摸手势 != null) {
                画板.this.$event_internal_触摸手势.触摸手势(1);
            }
            return true;
        }
    }
}

