/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Outline
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.drawable.Drawable
 *  android.os.Build$VERSION
 *  java.lang.Math
 *  java.lang.Object
 */
package lbchs;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;

class RoundRectDrawable
        extends Drawable {
    private final Paint mPaint;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private float mRadius;
    private float mPadding;
    private boolean mInsetForPadding = false;
    private boolean mInsetForRadius = true;

    public RoundRectDrawable(int n, float f) {
        this.mRadius = f;
        this.mPaint = new Paint(5);
        this.mPaint.setColor(n);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    void setPadding(float f, boolean bl, boolean bl2) {
        if (f == this.mPadding && this.mInsetForPadding == bl && this.mInsetForRadius == bl2) {
            return;
        }
        this.mPadding = f;
        this.mInsetForPadding = bl;
        this.mInsetForRadius = bl2;
        this.updateBounds(null);
        this.invalidateSelf();
    }

    float getPadding() {
        return this.mPadding;
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, this.mPaint);
    }

    private void updateBounds(Rect rect) {
        if (rect == null) {
            rect = this.getBounds();
        }
        this.mBoundsF.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.mBoundsI.set(rect);
        if (this.mInsetForPadding) {
            float f = RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            float f2 = RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            this.mBoundsI.inset((int) Math.ceil(f2), (int) Math.ceil(f));
            this.mBoundsF.set(this.mBoundsI);
        }
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.updateBounds(rect);
    }

    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT >= 21) {
            outline.setRoundRect(this.mBoundsI, this.mRadius);
        }
    }

    public void setAlpha(int n) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public float getRadius() {
        return this.mRadius;
    }

    void setRadius(float f) {
        if (f == this.mRadius) {
            return;
        }
        this.mRadius = f;
        this.updateBounds(null);
        this.invalidateSelf();
    }

    public void setColor(int n) {
        this.mPaint.setColor(n);
        this.invalidateSelf();
    }
}

