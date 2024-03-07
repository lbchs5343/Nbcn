/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapShader
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.util.AttributeSet
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  java.lang.Deprecated
 *  java.lang.Exception
 *  java.lang.IllegalArgumentException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView
        extends ImageView {
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_FILL_COLOR = 0;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();
    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mBorderPaint = new Paint();
    private final Paint mFillPaint = new Paint();
    private int mBorderColor = -16777216;
    private int mBorderWidth = 0;
    private int mFillColor = 0;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private float mDrawableRadius;
    private float mBorderRadius;
    private ColorFilter mColorFilter;
    private boolean mReady;
    private boolean mSetupPending;
    private boolean mBorderOverlay;
    private boolean mDisableCircularTransformation;
    private float mElevation;

    public CircleImageView(Context context) {
        super(context);
        this.init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.mBorderWidth = 0;
        this.mBorderColor = -16777216;
        this.mBorderOverlay = false;
        this.mFillColor = 0;
        this.init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending) {
            this.setup();
            this.mSetupPending = false;
        }
    }

    public void setElevation2(float f) {
        this.mElevation = f;
        this.mFillPaint.setShadowLayer(f, 0.0f, f / 2.0f, -16777216);
        this.invalidate();
    }

    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format((String) "ScaleType %s not supported.", (Object[]) new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean bl) {
        if (bl) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDisableCircularTransformation) {
            super.onDraw(canvas);
            return;
        }
        if (this.mBitmap == null) {
            return;
        }
        if (this.mFillColor != 0) {
            canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius - this.mElevation, this.mFillPaint);
        }
        canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius - this.mElevation, this.mBitmapPaint);
        if (this.mBorderWidth > 0) {
            canvas.drawCircle(this.mBorderRect.centerX(), this.mBorderRect.centerY(), this.mBorderRadius - this.mElevation, this.mBorderPaint);
        }
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.setup();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(int n) {
        if (n == this.mBorderColor) {
            return;
        }
        this.mBorderColor = n;
        this.mBorderPaint.setColor(this.mBorderColor);
        this.invalidate();
    }

    @Deprecated
    public void setBorderColorResource(int n) {
        this.setBorderColor(this.getContext().getResources().getColor(n));
    }

    @Deprecated
    public int getFillColor() {
        return this.mFillColor;
    }

    @Deprecated
    public void setFillColor(int n) {
        if (n == this.mFillColor) {
            return;
        }
        this.mFillColor = n;
        this.mFillPaint.setColor(n);
        this.invalidate();
    }

    public void setBackgroundColor(int n) {
        this.setFillColor(n);
    }

    @Deprecated
    public void setFillColorResource(int n) {
        this.setFillColor(this.getContext().getResources().getColor(n));
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int n) {
        if (n == this.mBorderWidth) {
            return;
        }
        this.mBorderWidth = n;
        this.setup();
    }

    public boolean isBorderOverlay() {
        return this.mBorderOverlay;
    }

    public void setBorderOverlay(boolean bl) {
        if (bl == this.mBorderOverlay) {
            return;
        }
        this.mBorderOverlay = bl;
        this.setup();
    }

    public boolean isDisableCircularTransformation() {
        return this.mDisableCircularTransformation;
    }

    public void setDisableCircularTransformation(boolean bl) {
        if (this.mDisableCircularTransformation == bl) {
            return;
        }
        this.mDisableCircularTransformation = bl;
        this.initializeBitmap();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.initializeBitmap();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.initializeBitmap();
    }

    public void setImageResource(int n) {
        super.setImageResource(n);
        this.initializeBitmap();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.initializeBitmap();
    }

    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter == this.mColorFilter) {
            return;
        }
        this.mColorFilter = colorFilter;
        this.applyColorFilter();
        this.invalidate();
    }

    private void applyColorFilter() {
        if (this.mBitmapPaint != null) {
            this.mBitmapPaint.setColorFilter(this.mColorFilter);
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap((int) 2, (int) 2, (Bitmap.Config) BITMAP_CONFIG) : Bitmap.createBitmap((int) (drawable.getIntrinsicWidth() - (int) (this.mElevation * 2.0f)), (int) (drawable.getIntrinsicHeight() - (int) (this.mElevation * 2.0f)), (Bitmap.Config) BITMAP_CONFIG);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void initializeBitmap() {
        this.mBitmap = this.mDisableCircularTransformation ? null : this.getBitmapFromDrawable(this.getDrawable());
        this.setup();
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
            return;
        }
        if (this.getWidth() == 0 && this.getHeight() == 0) {
            return;
        }
        if (this.mBitmap == null) {
            this.invalidate();
            return;
        }
        this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mBitmapPaint.setAntiAlias(true);
        this.mBitmapPaint.setShader((Shader) this.mBitmapShader);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
        this.mFillPaint.setStyle(Paint.Style.FILL);
        this.mFillPaint.setAntiAlias(true);
        this.mFillPaint.setColor(this.mFillColor);
        this.mBitmapHeight = this.mBitmap.getHeight();
        this.mBitmapWidth = this.mBitmap.getWidth();
        this.mBorderRect.set(this.calculateBounds());
        this.mBorderRadius = Math.min((float) ((this.mBorderRect.height() - (float) this.mBorderWidth) / 2.0f), (float) ((this.mBorderRect.width() - (float) this.mBorderWidth) / 2.0f));
        this.mDrawableRect.set(this.mBorderRect);
        if (!this.mBorderOverlay && this.mBorderWidth > 0) {
            this.mDrawableRect.inset((float) this.mBorderWidth - 1.0f, (float) this.mBorderWidth - 1.0f);
        }
        this.mDrawableRadius = Math.min((float) (this.mDrawableRect.height() / 2.0f), (float) (this.mDrawableRect.width() / 2.0f));
        this.applyColorFilter();
        this.updateShaderMatrix();
        this.invalidate();
    }

    private RectF calculateBounds() {
        int n = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        int n2 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int n3 = Math.min((int) n, (int) n2);
        float f = (float) this.getPaddingLeft() + (float) (n - n3) / 2.0f;
        float f2 = (float) this.getPaddingTop() + (float) (n2 - n3) / 2.0f;
        return new RectF(f, f2, f + (float) n3, f2 + (float) n3);
    }

    private void updateShaderMatrix() {
        float f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        this.mShaderMatrix.set(null);
        if ((float) this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * (float) this.mBitmapHeight) {
            f = this.mDrawableRect.height() / (float) this.mBitmapHeight;
            f2 = (this.mDrawableRect.width() - (float) this.mBitmapWidth * f) * 0.5f;
        } else {
            f = this.mDrawableRect.width() / (float) this.mBitmapWidth;
            f3 = (this.mDrawableRect.height() - (float) this.mBitmapHeight * f) * 0.5f;
        }
        this.mShaderMatrix.setScale(f, f);
        this.mShaderMatrix.postTranslate((float) ((int) (f2 + 0.5f)) + this.mDrawableRect.left, (float) ((int) (f3 + 0.5f)) + this.mDrawableRect.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }
}

