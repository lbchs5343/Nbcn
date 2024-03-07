/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.LinearLayout
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

public class CardView
        extends LinearLayout
        implements CardViewDelegate {
    private static final CardViewImpl IMPL = Build.VERSION.SDK_INT >= 21 ? new CardViewApi21() : (Build.VERSION.SDK_INT >= 17 ? new CardViewJellybeanMr1() : new CardViewEclairMr1());

    static {
        IMPL.initStatic();
    }

    private final Rect mContentPadding = new Rect();
    private final Rect mShadowBounds = new Rect();
    private DisplayMetrics dm;
    private boolean mCompatPadding;
    private boolean mPreventCornerOverlap;

    public CardView(Context context) {
        super(context);
        this.initialize(context, null, 0);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initialize(context, attributeSet, 0);
    }

    public CardView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.initialize(context, attributeSet, n);
    }

    public void setPadding(int n, int n2, int n3, int n4) {
    }

    public void setPaddingRelative(int n, int n2, int n3, int n4) {
    }

    @Override
    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean bl) {
        if (this.mCompatPadding == bl) {
            return;
        }
        this.mCompatPadding = bl;
        IMPL.onCompatPaddingChanged(this);
    }

    public void setContentPadding(int n, int n2, int n3, int n4) {
        this.mContentPadding.set(n, n2, n3, n4);
        IMPL.updatePadding(this);
    }

    @SuppressLint(value = {"SwitchIntDef"})
    protected void onMeasure(int n, int n2) {
        if (!(IMPL instanceof CardViewApi21)) {
            int n3;
            int n4 = View.MeasureSpec.getMode((int) n);
            switch (n4) {
                case -2147483648:
                case 0x40000000: {
                    n3 = (int) Math.ceil((double) IMPL.getMinWidth(this));
                    n = View.MeasureSpec.makeMeasureSpec((int) Math.max((int) n3, (int) View.MeasureSpec.getSize((int) n)), (int) n4);
                }
            }
            n3 = View.MeasureSpec.getMode((int) n2);
            switch (n3) {
                case -2147483648:
                case 0x40000000: {
                    int n5 = (int) Math.ceil((double) IMPL.getMinHeight(this));
                    n2 = View.MeasureSpec.makeMeasureSpec((int) Math.max((int) n5, (int) View.MeasureSpec.getSize((int) n2)), (int) n3);
                }
            }
            super.onMeasure(n, n2);
        } else {
            super.onMeasure(n, n2);
        }
    }

    private void initialize(Context context, AttributeSet attributeSet, int n) {
        this.dm = context.getResources().getDisplayMetrics();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{0x1010031});
        int n2 = typedArray.getColor(0, -328966);
        typedArray.recycle();
        float f = this.dp(2.0f);
        float f2 = this.dp(2.0f);
        float f3 = this.dp(2.0f);
        this.mCompatPadding = false;
        this.mPreventCornerOverlap = true;
        boolean bl = false;
        this.mContentPadding.left = 0;
        this.mContentPadding.top = 0;
        this.mContentPadding.right = 0;
        this.mContentPadding.bottom = 0;
        if (f2 > f3) {
            f3 = f2;
        }
        IMPL.initialize(this, context, n2, f, f2, f3);
    }

    private float dp(float f) {
        return TypedValue.applyDimension((int) 1, (float) f, (DisplayMetrics) this.dm);
    }

    public void setCardBackgroundColor(int n) {
        IMPL.setBackgroundColor(this, n);
    }

    public void setBackgroundColor(int n) {
        IMPL.setBackgroundColor(this, n);
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    @Override
    public float getRadius() {
        return IMPL.getRadius(this);
    }

    public void setRadius(float f) {
        IMPL.setRadius(this, f);
    }

    @Override
    public void setShadowPadding(int n, int n2, int n3, int n4) {
        this.mShadowBounds.set(n, n2, n3, n4);
        super.setPadding(n + this.mContentPadding.left, n2 + this.mContentPadding.top, n3 + this.mContentPadding.right, n4 + this.mContentPadding.bottom);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this);
    }

    public void setCardElevation(float f) {
        IMPL.setElevation(this, f);
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this);
    }

    public void setMaxCardElevation(float f) {
        IMPL.setMaxElevation(this, f);
    }

    @Override
    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean bl) {
        if (bl == this.mPreventCornerOverlap) {
            return;
        }
        this.mPreventCornerOverlap = bl;
        IMPL.onPreventCornerOverlapChanged(this);
    }

    static class CardViewJellybeanMr1
            extends CardViewEclairMr1 {
        CardViewJellybeanMr1() {
        }

        @Override
        public void initStatic() {
            RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {

                @Override
                public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                    canvas.drawRoundRect(rectF, f, f, paint);
                }
            };
        }
    }

    static class CardViewEclairMr1
            implements CardViewImpl {
        final RectF sCornerRect = new RectF();

        CardViewEclairMr1() {
        }

        @Override
        public void initStatic() {
            RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {

                @Override
                public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                    float f2 = f * 2.0f;
                    float f3 = rectF.width() - f2 - 1.0f;
                    float f4 = rectF.height() - f2 - 1.0f;
                    if (f >= 1.0f) {
                        sCornerRect.set(-(f += 0.5f), -f, f, f);
                        int n = canvas.save();
                        canvas.translate(rectF.left + f, rectF.top + f);
                        canvas.drawArc(sCornerRect, 180.0f, 90.0f, true, paint);
                        canvas.translate(f3, 0.0f);
                        canvas.rotate(90.0f);
                        canvas.drawArc(sCornerRect, 180.0f, 90.0f, true, paint);
                        canvas.translate(f4, 0.0f);
                        canvas.rotate(90.0f);
                        canvas.drawArc(sCornerRect, 180.0f, 90.0f, true, paint);
                        canvas.translate(f3, 0.0f);
                        canvas.rotate(90.0f);
                        canvas.drawArc(sCornerRect, 180.0f, 90.0f, true, paint);
                        canvas.restoreToCount(n);
                        canvas.drawRect(rectF.left + f - 1.0f, rectF.top, rectF.right - f + 1.0f, rectF.top + f, paint);
                        canvas.drawRect(rectF.left + f - 1.0f, rectF.bottom - f + 1.0f, rectF.right - f + 1.0f, rectF.bottom, paint);
                    }
                    canvas.drawRect(rectF.left, rectF.top + Math.max((float) 0.0f, (float) (f - 1.0f)), rectF.right, rectF.bottom - f + 1.0f, paint);
                }
            };
        }

        @Override
        public void initialize(CardViewDelegate cardViewDelegate, Context context, int n, float f, float f2, float f3) {
            RoundRectDrawableWithShadow roundRectDrawableWithShadow = this.createBackground(context, n, f, f2, f3);
            roundRectDrawableWithShadow.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
            cardViewDelegate.setBackgroundDrawable(roundRectDrawableWithShadow);
            this.updatePadding(cardViewDelegate);
        }

        RoundRectDrawableWithShadow createBackground(Context context, int n, float f, float f2, float f3) {
            return new RoundRectDrawableWithShadow(context.getResources(), n, f, f2, f3);
        }

        @Override
        public void updatePadding(CardViewDelegate cardViewDelegate) {
            Rect rect = new Rect();
            this.getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
            ((View) cardViewDelegate).setMinimumHeight((int) Math.ceil((double) this.getMinHeight(cardViewDelegate)));
            ((View) cardViewDelegate).setMinimumWidth((int) Math.ceil((double) this.getMinWidth(cardViewDelegate)));
            cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
        }

        @Override
        public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
        }

        @Override
        public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
            this.getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
            this.updatePadding(cardViewDelegate);
        }

        @Override
        public void setBackgroundColor(CardViewDelegate cardViewDelegate, int n) {
            this.getShadowBackground(cardViewDelegate).setColor(n);
        }

        @Override
        public void setRadius(CardViewDelegate cardViewDelegate, float f) {
            this.getShadowBackground(cardViewDelegate).setCornerRadius(f);
            this.updatePadding(cardViewDelegate);
        }

        @Override
        public float getRadius(CardViewDelegate cardViewDelegate) {
            return this.getShadowBackground(cardViewDelegate).getCornerRadius();
        }

        @Override
        public void setElevation(CardViewDelegate cardViewDelegate, float f) {
            this.getShadowBackground(cardViewDelegate).setShadowSize(f);
        }

        @Override
        public float getElevation(CardViewDelegate cardViewDelegate) {
            return this.getShadowBackground(cardViewDelegate).getShadowSize();
        }

        @Override
        public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
            this.getShadowBackground(cardViewDelegate).setMaxShadowSize(f);
            this.updatePadding(cardViewDelegate);
        }

        @Override
        public float getMaxElevation(CardViewDelegate cardViewDelegate) {
            return this.getShadowBackground(cardViewDelegate).getMaxShadowSize();
        }

        @Override
        public float getMinWidth(CardViewDelegate cardViewDelegate) {
            return this.getShadowBackground(cardViewDelegate).getMinWidth();
        }

        @Override
        public float getMinHeight(CardViewDelegate cardViewDelegate) {
            return this.getShadowBackground(cardViewDelegate).getMinHeight();
        }

        private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardViewDelegate) {
            return (RoundRectDrawableWithShadow) cardViewDelegate.getBackground();
        }
    }

    @SuppressLint(value = {"NewApi"})
    static class CardViewApi21
            implements CardViewImpl {
        CardViewApi21() {
        }

        @Override
        public void initialize(CardViewDelegate cardViewDelegate, Context context, int n, float f, float f2, float f3) {
            RoundRectDrawable roundRectDrawable = new RoundRectDrawable(n, f);
            cardViewDelegate.setBackgroundDrawable(roundRectDrawable);
            View view = (View) cardViewDelegate;
            view.setClipToOutline(true);
            view.setElevation(f2);
            this.setMaxElevation(cardViewDelegate, f3);
        }

        @Override
        public void setRadius(CardViewDelegate cardViewDelegate, float f) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).setRadius(f);
        }

        @Override
        public void initStatic() {
        }

        @Override
        public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).setPadding(f, cardViewDelegate.getUseCompatPadding(), cardViewDelegate.getPreventCornerOverlap());
            this.updatePadding(cardViewDelegate);
        }

        @Override
        public float getMaxElevation(CardViewDelegate cardViewDelegate) {
            return ((RoundRectDrawable) cardViewDelegate.getBackground()).getPadding();
        }

        @Override
        public float getMinWidth(CardViewDelegate cardViewDelegate) {
            return this.getRadius(cardViewDelegate) * 2.0f;
        }

        @Override
        public float getMinHeight(CardViewDelegate cardViewDelegate) {
            return this.getRadius(cardViewDelegate) * 2.0f;
        }

        @Override
        public float getRadius(CardViewDelegate cardViewDelegate) {
            return ((RoundRectDrawable) cardViewDelegate.getBackground()).getRadius();
        }

        @Override
        public void setElevation(CardViewDelegate cardViewDelegate, float f) {
            ((View) cardViewDelegate).setElevation(f);
        }

        @Override
        public float getElevation(CardViewDelegate cardViewDelegate) {
            return ((View) cardViewDelegate).getElevation();
        }

        @Override
        public void updatePadding(CardViewDelegate cardViewDelegate) {
            if (!cardViewDelegate.getUseCompatPadding()) {
                cardViewDelegate.setShadowPadding(0, 0, 0, 0);
                return;
            }
            float f = this.getMaxElevation(cardViewDelegate);
            float f2 = this.getRadius(cardViewDelegate);
            int n = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f2, cardViewDelegate.getPreventCornerOverlap()));
            int n2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateVerticalPadding(f, f2, cardViewDelegate.getPreventCornerOverlap()));
            cardViewDelegate.setShadowPadding(n, n2, n, n2);
        }

        @Override
        public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
            this.setMaxElevation(cardViewDelegate, this.getMaxElevation(cardViewDelegate));
        }

        @Override
        public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
            this.setMaxElevation(cardViewDelegate, this.getMaxElevation(cardViewDelegate));
        }

        @Override
        public void setBackgroundColor(CardViewDelegate cardViewDelegate, int n) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).setColor(n);
        }
    }
}

