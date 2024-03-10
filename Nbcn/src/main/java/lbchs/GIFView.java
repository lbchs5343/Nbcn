/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Movie
 *  android.os.Build$VERSION
 *  android.os.SystemClock
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  java.io.InputStream
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;

import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class GIFView
        extends View {
    private static final int DEFAULT_MOVIE_DURATION = 1000;
    private int mCurrentAnimationTime = 0;
    private float mLeft;
    private int mMeasuredMovieHeight;
    private int mMeasuredMovieWidth;
    private Movie mMovie;
    private long mMovieStart;
    private volatile boolean mPaused = false;
    private float mScale;
    private float mTop;
    private boolean mVisible = true;
    private int moviemode = 1;

    public GIFView(Context context) {
        this(context, null);
    }

    public GIFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setViewAttributes(context, attributeSet);
    }

    private void drawMovieFrame(Canvas canvas) {
        this.mMovie.setTime(this.mCurrentAnimationTime);
        if (this.moviemode == 1) {
            canvas.save();
            canvas.scale(this.mScale, this.mScale);
            this.mMovie.draw(canvas, this.mLeft / this.mScale, this.mTop / this.mScale);
            canvas.restore();
            return;
        }
        this.mMovie.draw(canvas, 0.0f, 0.0f);
    }

    private void invalidateView() {
        if (!this.mVisible) {
            return;
        }
        this.postInvalidateOnAnimation();
    }

    private void setViewAttributes(Context context, AttributeSet attributeSet) {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.mPaused = false;
    }

    private void updateAnimationTime() {
        int n;
        long l = SystemClock.uptimeMillis();
        if (this.mMovieStart == 0L) {
            this.mMovieStart = l;
        }
        if ((n = this.mMovie.duration()) == 0) {
            n = 1000;
        }
        this.mCurrentAnimationTime = (int) ((l - this.mMovieStart) % (long) n);
    }

    public Movie getMovie() {
        return this.mMovie;
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
        this.requestLayout();
    }

    public int getMovieMode() {
        return this.moviemode;
    }

    public void setMovieMode(int n) {
        this.moviemode = n;
        this.invalidateView();
    }

    public int getMovieTime() {
        return this.mCurrentAnimationTime;
    }

    public void setMovieTime(int n) {
        this.mCurrentAnimationTime = n;
        this.invalidate();
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public void setPaused(boolean bl) {
        this.mPaused = bl;
        if (!bl) {
            this.mMovieStart = SystemClock.uptimeMillis() - (long) this.mCurrentAnimationTime;
        }
        this.invalidate();
    }

    protected void onDraw(Canvas canvas) {
        if (this.mMovie == null) {
            return;
        }
        if (!this.mPaused) {
            this.updateAnimationTime();
            this.drawMovieFrame(canvas);
            this.invalidateView();
            return;
        }
        this.drawMovieFrame(canvas);
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        this.mLeft = (float) (this.getWidth() - this.mMeasuredMovieWidth) / 2.0f;
        this.mTop = (float) (this.getHeight() - this.mMeasuredMovieHeight) / 2.0f;
        this.mVisible = this.getVisibility() == View.VISIBLE;
    }

    protected void onMeasure(int n, int n2) {
        if (this.mMovie != null) {
            int n3 = this.mMovie.width();
            int n4 = this.mMovie.height();
            int n5 = View.MeasureSpec.getSize(n);
            this.mScale = 1.0f / ((float) n3 / (float) n5);
            this.mMeasuredMovieWidth = n5;
            this.mMeasuredMovieHeight = (int) ((float) n4 * this.mScale);
            if (MeasureSpec.EXACTLY == View.MeasureSpec.getMode(n) || MeasureSpec.EXACTLY == View.MeasureSpec.getMode(n2)) {
                this.moviemode = 1;
                this.setMeasuredDimension(this.mMeasuredMovieWidth, this.mMeasuredMovieHeight);
                return;
            }
            this.moviemode = 2;
            this.setMeasuredDimension(n3, n4);
            return;
        }
        this.setMeasuredDimension(this.getSuggestedMinimumWidth(), this.getSuggestedMinimumHeight());
    }

    public void onScreenStateChanged(int n) {
        boolean bl = true;
        super.onScreenStateChanged(n);
        if (n != 1) {
            bl = false;
        }
        this.mVisible = bl;
        this.invalidateView();
    }

    protected void onVisibilityChanged( View view, int n) {
        super.onVisibilityChanged(view, n);
        this.mVisible = n == 0;
        this.invalidateView();
    }

    protected void onWindowVisibilityChanged(int n) {
        super.onWindowVisibilityChanged(n);
        this.mVisible = n == 0;
        this.invalidateView();
    }

    public void setMovieResource(int n) {
        this.setMovieResource(this.getResources().openRawResource(n));
    }

    public void setMovieResource(InputStream inputStream) {
        this.mMovie = Movie.decodeStream(inputStream);
        this.requestLayout();
    }

    public void setMovieResource(String string) {
        this.mMovie = Movie.decodeFile(string);
        this.requestLayout();
    }

    public void setMovieResource(byte[] byArray) {
        this.mMovie = Movie.decodeByteArray(byArray, 0, byArray.length);
        this.requestLayout();
    }
}

