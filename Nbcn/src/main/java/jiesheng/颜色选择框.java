/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.LinearGradient
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.SweepGradient
 *  android.os.Bundle
 *  android.os.Looper
 *  android.view.Display
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  java.lang.CharSequence
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package jiesheng;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Looper;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class 颜色选择框
        extends 组件 {
    private static 对话框.DialogHandler handler;
    private static int colorResult;
    private ColorPickerDialog dialog;
    private int color = -1;
    private 颜色被改变 $event_internal_颜色被改变;
    private 按钮被单击 $event_internal_按钮被单击;

    public 颜色选择框(Context context) {
        super(context);
        this.dialog = new ColorPickerDialog(context, "选择颜色", "确定", this.color, new ColorPickerDialog.OnColorChangedListener() {

            @Override
            public void colorChanged(int n) {
                颜色选择框.this.color = n;
                if (颜色选择框.this.$event_internal_颜色被改变 != null) {
                    颜色选择框.this.$event_internal_颜色被改变.颜色被改变(n);
                }
            }
        });
        this.dialog.setClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                颜色选择框.this.dialog.dismiss();
                if (颜色选择框.this.$event_internal_按钮被单击 != null) {
                    颜色选择框.this.$event_internal_按钮被单击.按钮被单击();
                }
            }
        });
    }

    public 颜色选择框(Context context, String string) {
        this(context);
        this.标题(string);
    }

    public static int colorPickerBox(Context context, String string) {
        return 颜色选择框.colorPickerBox(context, string, -65536);
    }

    public static int colorPickerBox(Context context, String string, int n) {
        final ColorPickerDialog colorPickerDialog = new ColorPickerDialog(context, string, "确定", n, new ColorPickerDialog.OnColorChangedListener() {

            @Override
            public void colorChanged(int n) {
                colorResult = n;
                handler.sendMessage(handler.obtainMessage());
            }
        });
        colorPickerDialog.setClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                colorPickerDialog.dismiss();
                handler.sendMessage(handler.obtainMessage());
            }
        });
        colorPickerDialog.setCancelable(false);
        colorPickerDialog.show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
        return colorResult;
    }

    public void 标题(String string) {
        this.dialog.setTitle(string);
    }

    public void 按钮内容(String string) {
        this.dialog.setButtonTitle(string);
    }

    public void 可取消(boolean bl) {
        this.dialog.setCancelable(bl);
    }

    public int 颜色() {
        return this.color;
    }

    public void 显示() {
        this.dialog.show();
    }

    public void 隐藏() {
        this.dialog.hide();
    }

    public void 关闭() {
        this.dialog.dismiss();
    }

    public void 置颜色被改变(颜色被改变 颜色被改变2) {
        this.$event_internal_颜色被改变 = 颜色被改变2;
    }

    public void 置按钮被单击(按钮被单击 按钮被单击2) {
        this.$event_internal_按钮被单击 = 按钮被单击2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 按钮被单击 {
        public void 按钮被单击();
    }

    public static interface 颜色被改变 {
        public void 颜色被改变(int var1);
    }

    static class ColorPickerDialog
            extends Dialog {
        private String ButtonTitle;
        private Context mContext;
        private int mInitialColor;
        private OnColorChangedListener mListener;
        private String mTitle;
        private Button button;

        public ColorPickerDialog(Context context, String string, String string2, OnColorChangedListener onColorChangedListener) {
            this(context, string, string2, -7829368, onColorChangedListener);
        }

        public ColorPickerDialog(Context context, String string, String string2, int n, OnColorChangedListener onColorChangedListener) {
            super(context);
            this.mContext = context;
            this.mTitle = string;
            this.ButtonTitle = string2;
            this.mListener = onColorChangedListener;
            this.mInitialColor = n;
            this.button = new Button(this.mContext);
        }

        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.button.setText((CharSequence) this.ButtonTitle);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            if (this.是否为竖屏(this.mContext)) {
                PortraitColorPickerView portraitColorPickerView = new PortraitColorPickerView(this.mContext, this.mListener);
                linearLayout.addView((View) portraitColorPickerView);
                linearLayout.addView((View) this.button);
            } else {
                LandscapeColorPickerView landscapeColorPickerView = new LandscapeColorPickerView(this.mContext, this.mListener);
                linearLayout.addView((View) landscapeColorPickerView);
                linearLayout.addView((View) this.button);
            }
            this.setContentView((View) linearLayout);
            this.setTitle(this.mTitle);
        }

        public void setButtonTitle(String string) {
            this.button.setText((CharSequence) string);
        }

        public void setClickListener(View.OnClickListener onClickListener) {
            this.button.setOnClickListener(onClickListener);
        }

        private boolean 是否为竖屏(Context context) {
            return context.getResources().getConfiguration().orientation == 1;
        }

        public static interface OnColorChangedListener {
            public void colorChanged(int var1);
        }

        private class PortraitColorPickerView
                extends ColorPickerView {
            public PortraitColorPickerView(Context context, OnColorChangedListener onColorChangedListener) {
                super(context, onColorChangedListener);
                Display display = ColorPickerDialog.this.getWindow().getWindowManager().getDefaultDisplay();
                int n = (int) ((float) display.getHeight() * 0.5f) - 36;
                int n2 = (int) ((float) display.getWidth() * 0.7f);
                this.mHeight = n;
                this.mWidth = n2;
                this.setMinimumHeight(n);
                this.setMinimumWidth(n2);
                SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, this.mCircleColors, null);
                this.mCirclePaint = new Paint(1);
                this.mCirclePaint.setShader((Shader) sweepGradient);
                this.mCirclePaint.setStyle(Paint.Style.STROKE);
                this.mCirclePaint.setStrokeWidth(50.0f);
                this.mCircleRadius = (float) (n2 / 2) * 0.7f - this.mCirclePaint.getStrokeWidth() * 0.5f;
                this.mCenterPaint = new Paint(1);
                this.mCenterPaint.setColor(ColorPickerDialog.this.mInitialColor);
                this.mCenterPaint.setStrokeWidth(5.0f);
                this.mCenterRadius = (this.mCircleRadius - this.mCirclePaint.getStrokeWidth() / 2.0f) * 0.7f;
                this.mLinePaint = new Paint(1);
                this.mLinePaint.setColor(Color.parseColor((String) "#72A1D1"));
                this.mLinePaint.setStrokeWidth(4.0f);
                this.mRectPaint = new Paint(1);
                this.mRectPaint.setStrokeWidth(5.0f);
                this.mRectLeft = -this.mCircleRadius - this.mCirclePaint.getStrokeWidth() * 0.5f;
                this.mRectTop = this.mCircleRadius + this.mCirclePaint.getStrokeWidth() * 0.5f + this.mLinePaint.getStrokeMiter() * 0.5f + 15.0f;
                this.mRectRight = this.mCircleRadius + this.mCirclePaint.getStrokeWidth() * 0.5f;
                this.mRectBottom = this.mRectTop + 50.0f;
            }

            protected void onDraw(Canvas canvas) {
                canvas.translate((float) (this.mWidth / 2), (float) (this.mHeight / 2 - 50));
                canvas.drawCircle(0.0f, 0.0f, this.mCenterRadius, this.mCenterPaint);
                if (this.mHighlightCenter || this.mlittleLightCenter) {
                    int n = this.mCenterPaint.getColor();
                    this.mCenterPaint.setStyle(Paint.Style.STROKE);
                    if (this.mHighlightCenter) {
                        this.mCenterPaint.setAlpha(255);
                    } else if (this.mlittleLightCenter) {
                        this.mCenterPaint.setAlpha(144);
                    }
                    canvas.drawCircle(0.0f, 0.0f, this.mCenterRadius + this.mCenterPaint.getStrokeWidth(), this.mCenterPaint);
                    this.mCenterPaint.setStyle(Paint.Style.FILL);
                    this.mCenterPaint.setColor(n);
                }
                canvas.drawOval(new RectF(-this.mCircleRadius, -this.mCircleRadius, this.mCircleRadius, this.mCircleRadius), this.mCirclePaint);
                if (this.mDownInCircle) {
                    this.mRectColors[1] = this.mCenterPaint.getColor();
                }
                this.mRectShader = new LinearGradient(this.mRectLeft, 0.0f, this.mRectRight, 0.0f, this.mRectColors, null, Shader.TileMode.MIRROR);
                this.mRectPaint.setShader(this.mRectShader);
                canvas.drawRect(this.mRectLeft, this.mRectTop, this.mRectRight, this.mRectBottom, this.mRectPaint);
                float f = this.mLinePaint.getStrokeWidth() / 2.0f;
                Canvas canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f, this.mRectTop - f * 2.0f, this.mRectLeft - f, f * 2.0f + this.mRectBottom, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f * 2.0f, this.mRectTop - f, f * 2.0f + this.mRectRight, this.mRectTop - f, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectRight + f, this.mRectTop - f * 2.0f, this.mRectRight + f, f * 2.0f + this.mRectBottom, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f * 2.0f, this.mRectBottom + f, f * 2.0f + this.mRectRight, this.mRectBottom + f, this.mLinePaint);
                super.onDraw(canvas);
            }

            public boolean onTouchEvent(MotionEvent motionEvent) {
                float f = motionEvent.getX() - (float) (this.mWidth / 2);
                float f2 = motionEvent.getY() - (float) (this.mHeight / 2) + 50.0f;
                boolean bl = this.inColorCircle(f, f2, this.mCircleRadius + this.mCirclePaint.getStrokeWidth() / 2.0f, this.mCircleRadius - this.mCirclePaint.getStrokeWidth() / 2.0f);
                boolean bl2 = this.inCenter(f, f2, this.mCenterRadius);
                boolean bl3 = this.inRect(f, f2);
                switch (motionEvent.getAction()) {
                    case 0: {
                        this.onActionDown(bl, bl2, bl3);
                        break;
                    }
                    case 1: {
                        this.onActionUp(bl2);
                        break;
                    }
                }
                this.onActionMove(f, f2, bl, bl2, bl3);
                return true;
            }

            @Override
            public int interpRectColor(int[] nArray, float f, float f2) {
                float f3;
                int n;
                int n2;
                if (f < 0.0f) {
                    n2 = nArray[0];
                    n = nArray[1];
                    f3 = (this.mRectRight + f) / this.mRectRight;
                } else {
                    n2 = nArray[1];
                    n = nArray[2];
                    f3 = f / this.mRectRight;
                }
                return Color.argb((int) this.ave(Color.alpha((int) n2), Color.alpha((int) n), f3), (int) this.ave(Color.red((int) n2), Color.red((int) n), f3), (int) this.ave(Color.green((int) n2), Color.green((int) n), f3), (int) this.ave(Color.blue((int) n2), Color.blue((int) n), f3));
            }
        }

        private class LandscapeColorPickerView
                extends ColorPickerView {
            public LandscapeColorPickerView(Context context, OnColorChangedListener onColorChangedListener) {
                super(context, onColorChangedListener);
                Display display = ColorPickerDialog.this.getWindow().getWindowManager().getDefaultDisplay();
                int n = (int) ((float) display.getHeight() * 0.8f) - 36;
                int n2 = (int) ((float) display.getWidth() * 0.5f);
                this.mHeight = n;
                this.mWidth = n2;
                this.setMinimumHeight(n);
                this.setMinimumWidth(n2);
                SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, this.mCircleColors, null);
                this.mCirclePaint = new Paint(1);
                this.mCirclePaint.setShader((Shader) sweepGradient);
                this.mCirclePaint.setStyle(Paint.Style.STROKE);
                this.mCirclePaint.setStrokeWidth(50.0f);
                this.mCircleRadius = (float) (this.mHeight / 2) * 0.7f - this.mCirclePaint.getStrokeWidth() * 0.5f;
                this.mCenterPaint = new Paint(1);
                this.mCenterPaint.setColor(ColorPickerDialog.this.mInitialColor);
                this.mCenterPaint.setStrokeWidth(5.0f);
                this.mCenterRadius = (this.mCircleRadius - this.mCirclePaint.getStrokeWidth() / 2.0f) * 0.7f;
                this.mLinePaint = new Paint(1);
                this.mLinePaint.setColor(Color.parseColor((String) "#72A1D1"));
                this.mLinePaint.setStrokeWidth(4.0f);
                this.mRectPaint = new Paint(1);
                this.mRectPaint.setStrokeWidth(5.0f);
                this.mRectLeft = this.mCircleRadius + this.mCirclePaint.getStrokeWidth() * 0.5f + this.mLinePaint.getStrokeMiter() * 0.5f + 15.0f;
                this.mRectTop = -this.mCircleRadius - this.mCirclePaint.getStrokeWidth() * 0.5f;
                this.mRectRight = this.mRectLeft + 50.0f;
                this.mRectBottom = this.mCircleRadius + this.mCirclePaint.getStrokeWidth() * 0.5f;
            }

            protected void onDraw(Canvas canvas) {
                canvas.translate((float) (this.mWidth / 2 - 50), (float) (this.mHeight / 2));
                canvas.drawCircle(0.0f, 0.0f, this.mCenterRadius, this.mCenterPaint);
                if (this.mHighlightCenter || this.mlittleLightCenter) {
                    int n = this.mCenterPaint.getColor();
                    this.mCenterPaint.setStyle(Paint.Style.STROKE);
                    if (this.mHighlightCenter) {
                        this.mCenterPaint.setAlpha(255);
                    } else if (this.mlittleLightCenter) {
                        this.mCenterPaint.setAlpha(144);
                    }
                    canvas.drawCircle(0.0f, 0.0f, this.mCenterRadius + this.mCenterPaint.getStrokeWidth(), this.mCenterPaint);
                    this.mCenterPaint.setStyle(Paint.Style.FILL);
                    this.mCenterPaint.setColor(n);
                }
                canvas.drawOval(new RectF(-this.mCircleRadius, -this.mCircleRadius, this.mCircleRadius, this.mCircleRadius), this.mCirclePaint);
                if (this.mDownInCircle) {
                    this.mRectColors[1] = this.mCenterPaint.getColor();
                }
                this.mRectShader = new LinearGradient(0.0f, this.mRectTop, 0.0f, this.mRectBottom, this.mRectColors, null, Shader.TileMode.MIRROR);
                this.mRectPaint.setShader(this.mRectShader);
                canvas.drawRect(this.mRectLeft, this.mRectTop, this.mRectRight, this.mRectBottom, this.mRectPaint);
                float f = this.mLinePaint.getStrokeWidth() / 2.0f;
                Canvas canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f, this.mRectTop - f * 2.0f, this.mRectLeft - f, f * 2.0f + this.mRectBottom, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f * 2.0f, this.mRectTop - f, f * 2.0f + this.mRectRight, this.mRectTop - f, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectRight + f, this.mRectTop - f * 2.0f, this.mRectRight + f, f * 2.0f + this.mRectBottom, this.mLinePaint);
                canvas2 = canvas;
                canvas2.drawLine(this.mRectLeft - f * 2.0f, this.mRectBottom + f, f * 2.0f + this.mRectRight, this.mRectBottom + f, this.mLinePaint);
                super.onDraw(canvas);
            }

            public boolean onTouchEvent(MotionEvent motionEvent) {
                float f = motionEvent.getX() - (float) (this.mWidth / 2) + 50.0f;
                float f2 = motionEvent.getY() - (float) (this.mHeight / 2);
                boolean bl = this.inColorCircle(f, f2, this.mCircleRadius + this.mCirclePaint.getStrokeWidth() / 2.0f, this.mCircleRadius - this.mCirclePaint.getStrokeWidth() / 2.0f);
                boolean bl2 = this.inCenter(f, f2, this.mCenterRadius);
                boolean bl3 = this.inRect(f, f2);
                switch (motionEvent.getAction()) {
                    case 0: {
                        this.onActionDown(bl, bl2, bl3);
                        break;
                    }
                    case 1: {
                        this.onActionUp(bl2);
                        break;
                    }
                }
                this.onActionMove(f, f2, bl, bl2, bl3);
                return true;
            }

            @Override
            public int interpRectColor(int[] nArray, float f, float f2) {
                float f3;
                int n;
                int n2;
                float f4 = this.mRectBottom;
                if (f2 < 0.0f) {
                    n2 = nArray[0];
                    n = nArray[1];
                    f3 = (f2 + f4) / f4;
                } else {
                    n2 = nArray[1];
                    n = nArray[2];
                    f3 = f2 / f4;
                }
                return Color.argb((int) this.ave(Color.alpha((int) n2), Color.alpha((int) n), f3), (int) this.ave(Color.red((int) n2), Color.red((int) n), f3), (int) this.ave(Color.green((int) n2), Color.green((int) n), f3), (int) this.ave(Color.blue((int) n2), Color.blue((int) n), f3));
            }
        }

        protected abstract class ColorPickerView
                extends View {
            protected final int[] mCircleColors;
            protected final int[] mRectColors;
            protected Paint mCenterPaint;
            protected float mCenterRadius;
            protected Paint mCirclePaint;
            protected float mCircleRadius;
            protected boolean mDownInCircle;
            protected boolean mDownInRect;
            protected int mHeight;
            protected boolean mHighlightCenter;
            protected Paint mLinePaint;
            protected OnColorChangedListener mListener;
            protected float mRectBottom;
            protected float mRectLeft;
            protected Paint mRectPaint;
            protected float mRectRight;
            protected Shader mRectShader;
            protected float mRectTop;
            protected int mWidth;
            protected boolean mlittleLightCenter;

            public ColorPickerView(Context context, OnColorChangedListener onColorChangedListener) {
                super(context);
                this.mDownInCircle = true;
                this.mListener = onColorChangedListener;
                this.mCircleColors = new int[]{-65536, -65281, -16776961, -16711681, -16711936, -256, -65536};
                this.mRectColors = new int[]{-16777216, -65536, -1};
            }

            public abstract int interpRectColor(int[] var1, float var2, float var3);

            protected int ave(int n, int n2, float f) {
                return Math.round((float) ((float) (n2 - n) * f)) + n;
            }

            protected boolean inColorCircle(float f, float f2, float f3, float f4) {
                double d = Math.PI * (double) (f * f + f2 * f2);
                return d < Math.PI * (double) f3 * (double) f3 && d > Math.PI * (double) f4 * (double) f4;
            }

            protected boolean inCenter(float f, float f2, float f3) {
                return Math.PI * (double) (f * f + f2 * f2) < (double) f3 * Math.PI * (double) f3;
            }

            protected boolean inRect(float f, float f2) {
                return f <= this.mRectRight && f >= this.mRectLeft && f2 <= this.mRectBottom && f2 >= this.mRectTop;
            }

            public int interpCircleColor(int[] nArray, float f) {
                if (f <= 0.0f) {
                    return nArray[0];
                }
                if (f >= 1.0f) {
                    return nArray[nArray.length - 1];
                }
                float f2 = f * (float) (nArray.length - 1);
                int n = (int) f2;
                int n2 = nArray[n];
                int n3 = nArray[n + 1];
                return Color.argb((int) this.ave(Color.alpha((int) n2), Color.alpha((int) n3), f2 -= (float) n), (int) this.ave(Color.red((int) n2), Color.red((int) n3), f2), (int) this.ave(Color.green((int) n2), Color.green((int) n3), f2), (int) this.ave(Color.blue((int) n2), Color.blue((int) n3), f2));
            }

            public void onMeasure(int n, int n2) {
                super.onMeasure(this.mWidth, this.mHeight);
            }

            public void onActionDown(boolean bl, boolean bl2, boolean bl3) {
                this.mDownInCircle = bl;
                this.mDownInRect = bl3;
                this.mHighlightCenter = bl2;
            }

            public void onActionMove(float f, float f2, boolean bl, boolean bl2, boolean bl3) {
                if (this.mDownInCircle && bl) {
                    float f3 = (float) ((double) ((float) Math.atan2((double) f2, (double) f)) / (Math.PI * 2));
                    if (f3 < 0.0f) {
                        f3 += 1.0f;
                    }
                    this.mCenterPaint.setColor(this.interpCircleColor(this.mCircleColors, f3));
                } else if (this.mDownInRect && bl3) {
                    this.mCenterPaint.setColor(this.interpRectColor(this.mRectColors, f, f2));
                }
                if (this.mHighlightCenter && bl2 || this.mlittleLightCenter && bl2) {
                    this.mHighlightCenter = true;
                    this.mlittleLightCenter = false;
                } else if (this.mHighlightCenter || this.mlittleLightCenter) {
                    this.mHighlightCenter = false;
                    this.mlittleLightCenter = true;
                } else {
                    this.mHighlightCenter = false;
                    this.mlittleLightCenter = false;
                }
                this.invalidate();
            }

            protected void onActionUp(boolean bl) {
                if (this.mHighlightCenter && bl && this.mListener != null) {
                    this.mListener.colorChanged(this.mCenterPaint.getColor());
                    ColorPickerDialog.this.dismiss();
                }
                if (this.mDownInCircle) {
                    this.mDownInCircle = false;
                }
                if (this.mDownInRect) {
                    this.mDownInRect = false;
                }
                if (this.mHighlightCenter) {
                    this.mHighlightCenter = false;
                }
                if (this.mlittleLightCenter) {
                    this.mlittleLightCenter = false;
                }
                this.invalidate();
            }
        }
    }
}

