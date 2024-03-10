/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.LinearGradient
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Path$FillType
 *  android.graphics.RadialGradient
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.Drawable
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  java.lang.IllegalArgumentException
 *  java.lang.Math
 *  java.lang.Object
 */
package lbchs;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

class RoundRectDrawableWithShadow
        extends Drawable {
    static final double COS_45 = Math.cos(Math.toRadians(45.0));
    static final float SHADOW_MULTIPLIER = 1.5f;
    static RoundRectHelper sRoundRectHelper;
    final int mInsetShadow;
    final RectF mCardBounds;
    private final int mShadowStartColor;
    private final int mShadowEndColor;
    Paint mPaint;
    Paint mCornerShadowPaint;
    Paint mEdgeShadowPaint;
    float mCornerRadius;
    Path mCornerShadowPath;
    float mMaxShadowSize;
    float mRawMaxShadowSize;
    float mShadowSize;
    float mRawShadowSize;
    private boolean mDirty = true;
    private boolean mAddPaddingForCorners = true;
    private boolean mPrintedShadowClipWarning = false;
    private final DisplayMetrics dm;

    RoundRectDrawableWithShadow(Resources resources, int n, float f, float f2, float f3) {
        this.mShadowStartColor = 0x37000000;
        this.mShadowEndColor = 0x3000000;
        this.dm = resources.getDisplayMetrics();
        this.mInsetShadow = (int) this.dp();
        this.mPaint = new Paint(5);
        this.mPaint.setColor(n);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
        this.mCornerRadius = (int) (f + 0.5f);
        this.mCardBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint.setAntiAlias(false);
        this.setShadowSize(f2, f3);
    }

    static float calculateVerticalPadding(float f, float f2, boolean bl) {
        if (bl) {
            return (float) ((double) (f * 1.5f) + (1.0 - COS_45) * (double) f2);
        }
        return f * 1.5f;
    }

    static float calculateHorizontalPadding(float f, float f2, boolean bl) {
        if (bl) {
            return (float) ((double) f + (1.0 - COS_45) * (double) f2);
        }
        return f;
    }

    private float dp() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 1.0, this.dm);
    }

    private int toEven(float f) {
        int n = (int) (f + 0.5f);
        if (n % 2 == 1) {
            return n - 1;
        }
        return n;
    }

    public void setAddPaddingForCorners(boolean bl) {
        this.mAddPaddingForCorners = bl;
        this.invalidateSelf();
    }

    public void setAlpha(int n) {
        this.mPaint.setAlpha(n);
        this.mCornerShadowPaint.setAlpha(n);
        this.mEdgeShadowPaint.setAlpha(n);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDirty = true;
    }

    void setShadowSize(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        if ((f = (float) this.toEven(f)) > (f2 = (float) this.toEven(f2))) {
            f = f2;
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
        }
        if (this.mRawShadowSize == f && this.mRawMaxShadowSize == f2) {
            return;
        }
        this.mRawShadowSize = f;
        this.mRawMaxShadowSize = f2;
        this.mShadowSize = (int) (f * 1.5f + (float) this.mInsetShadow + 0.5f);
        this.mMaxShadowSize = f2 + (float) this.mInsetShadow;
        this.mDirty = true;
        this.invalidateSelf();
    }

    public boolean getPadding(Rect rect) {
        int n = (int) Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int n2 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        rect.set(n2, n, n2, n);
        return true;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.mCornerShadowPaint.setColorFilter(colorFilter);
        this.mEdgeShadowPaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void draw(Canvas canvas) {
        if (this.mDirty) {
            this.buildComponents(this.getBounds());
            this.mDirty = false;
        }
        canvas.translate(0.0f, this.mRawShadowSize / 2.0f);
        this.drawShadow(canvas);
        canvas.translate(0.0f, -this.mRawShadowSize / 2.0f);
        sRoundRectHelper.drawRoundRect(canvas, this.mCardBounds, this.mCornerRadius, this.mPaint);
    }

    private void drawShadow(Canvas canvas) {
        float f = -this.mCornerRadius - this.mShadowSize;
        float f2 = this.mCornerRadius + (float) this.mInsetShadow + this.mRawShadowSize / 2.0f;
        boolean bl = this.mCardBounds.width() - 2.0f * f2 > 0.0f;
        boolean bl2 = this.mCardBounds.height() - 2.0f * f2 > 0.0f;
        int n = canvas.save();
        canvas.translate(this.mCardBounds.left + f2, this.mCardBounds.top + f2);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (bl) {
            canvas.drawRect(0.0f, f, this.mCardBounds.width() - 2.0f * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(n);
        n = canvas.save();
        canvas.translate(this.mCardBounds.right - f2, this.mCardBounds.bottom - f2);
        canvas.rotate(180.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (bl) {
            canvas.drawRect(0.0f, f, this.mCardBounds.width() - 2.0f * f2, -this.mCornerRadius + this.mShadowSize, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(n);
        n = canvas.save();
        canvas.translate(this.mCardBounds.left + f2, this.mCardBounds.bottom - f2);
        canvas.rotate(270.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (bl2) {
            canvas.drawRect(0.0f, f, this.mCardBounds.height() - 2.0f * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(n);
        n = canvas.save();
        canvas.translate(this.mCardBounds.right - f2, this.mCardBounds.top + f2);
        canvas.rotate(90.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (bl2) {
            canvas.drawRect(0.0f, f, this.mCardBounds.height() - 2.0f * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(n);
    }

    private void buildShadowCorners() {
        RectF rectF = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float f = this.mCornerRadius / (this.mCornerRadius + this.mShadowSize);
        this.mCornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mCornerRadius + this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, f, 1.0f}, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setShader(new LinearGradient(0.0f, -this.mCornerRadius + this.mShadowSize, 0.0f, -this.mCornerRadius - this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        float f = this.mRawMaxShadowSize * 1.5f;
        this.mCardBounds.set((float) rect.left + this.mRawMaxShadowSize, (float) rect.top + f, (float) rect.right - this.mRawMaxShadowSize, (float) rect.bottom - f);
        this.buildShadowCorners();
    }

    float getCornerRadius() {
        return this.mCornerRadius;
    }

    void setCornerRadius(float f) {
        if (this.mCornerRadius == (f = (float) ((int) (f + 0.5f)))) {
            return;
        }
        this.mCornerRadius = f;
        this.mDirty = true;
        this.invalidateSelf();
    }

    void getMaxShadowAndCornerPadding(Rect rect) {
        this.getPadding(rect);
    }

    float getShadowSize() {
        return this.mRawShadowSize;
    }

    void setShadowSize(float f) {
        this.setShadowSize(f, this.mRawMaxShadowSize);
    }

    float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }

    void setMaxShadowSize(float f) {
        this.setShadowSize(this.mRawShadowSize, f);
    }

    float getMinWidth() {
        float f = 2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + (float) this.mInsetShadow + this.mRawMaxShadowSize / 2.0f);
        return f + (this.mRawMaxShadowSize + (float) this.mInsetShadow) * 2.0f;
    }

    float getMinHeight() {
        float f = 2.0f * Math.max(this.mRawMaxShadowSize, this.mCornerRadius + (float) this.mInsetShadow + this.mRawMaxShadowSize * 1.5f / 2.0f);
        return f + (this.mRawMaxShadowSize * 1.5f + (float) this.mInsetShadow) * 2.0f;
    }

    public void setColor(int n) {
        this.mPaint.setColor(n);
        this.invalidateSelf();
    }

    interface RoundRectHelper {
        void drawRoundRect(Canvas var1, RectF var2, float var3, Paint var4);
    }
}

