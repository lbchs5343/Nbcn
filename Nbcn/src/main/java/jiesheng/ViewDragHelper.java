/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.MotionEvent
 *  android.view.VelocityTracker
 *  android.view.View
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.animation.Interpolator
 *  android.widget.Scroller
 *  java.lang.IllegalArgumentException
 *  java.lang.IllegalStateException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.util.Arrays
 */
package jiesheng;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.util.Arrays;

public class ViewDragHelper {
    public static final int INVALID_POINTER = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_SETTLING = 2;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_ALL = 15;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int DIRECTION_ALL = 3;
    private static final String TAG = "ViewDragHelper";
    private static final int EDGE_SIZE = 20;
    private static final int BASE_SETTLE_DURATION = 256;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final Interpolator sInterpolator = new Interpolator() {

        public float getInterpolation(float f) {
            return (f -= 1.0f) * f * f * f * f + 1.0f;
        }
    };
    private final Callback mCallback;
    private final ViewGroup mParentView;
    private int mDragState;
    private int mTouchSlop;
    private int mActivePointerId = -1;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private int[] mInitialEdgesTouched;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mPointersDown;
    private VelocityTracker mVelocityTracker;
    private float mMaxVelocity;
    private float mMinVelocity;
    private int mEdgeSize;
    private int mTrackingEdges;
    private Scroller mScroller;
    private View mCapturedView;
    private boolean mReleaseInProgress;

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get((Context) context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mEdgeSize = (int) (20.0f * f + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new Scroller(context, sInterpolator);
    }

    public static ViewDragHelper create(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    public static ViewDragHelper create(ViewGroup viewGroup, float f, Callback callback) {
        ViewDragHelper viewDragHelper = ViewDragHelper.create(viewGroup, callback);
        viewDragHelper.mTouchSlop = (int) ((float) viewDragHelper.mTouchSlop * (1.0f / f));
        return viewDragHelper;
    }    private final Runnable mSetIdleRunnable = new Runnable() {

        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public void setMinVelocity(float f) {
        this.mMinVelocity = f;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public void setEdgeTrackingEnabled(int n) {
        this.mTrackingEdges = n;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public void captureChildView(View view, int n) {
        if (view.getParent() != this.mParentView) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
        }
        this.mCapturedView = view;
        this.mActivePointerId = n;
        this.mCallback.onViewCaptured(view, n);
        this.setDragState(1);
    }

    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        this.clearMotionHistory();
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        this.cancel();
        if (this.mDragState == 2) {
            int n = this.mScroller.getCurrX();
            int n2 = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int n3 = this.mScroller.getCurrX();
            int n4 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, n3, n4, n3 - n, n4 - n2);
        }
        this.setDragState(0);
    }

    public boolean smoothSlideViewTo(View view, int n, int n2) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean bl = this.forceSettleCapturedViewAt(n, n2, 0, 0);
        if (!bl && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return bl;
    }

    public boolean settleCapturedViewAt(int n, int n2) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return this.forceSettleCapturedViewAt(n, n2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    private boolean forceSettleCapturedViewAt(int n, int n2, int n3, int n4) {
        int n5 = this.mCapturedView.getLeft();
        int n6 = this.mCapturedView.getTop();
        int n7 = n - n5;
        int n8 = n2 - n6;
        if (n7 == 0 && n8 == 0) {
            this.mScroller.abortAnimation();
            this.setDragState(0);
            return false;
        }
        int n9 = this.computeSettleDuration(this.mCapturedView, n7, n8, n3, n4);
        this.mScroller.startScroll(n5, n6, n7, n8, n9);
        this.setDragState(2);
        return true;
    }

    private int computeSettleDuration(View view, int n, int n2, int n3, int n4) {
        n3 = this.clampMag(n3, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        n4 = this.clampMag(n4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int n5 = Math.abs((int) n);
        int n6 = Math.abs((int) n2);
        int n7 = Math.abs((int) n3);
        int n8 = Math.abs((int) n4);
        int n9 = n7 + n8;
        int n10 = n5 + n6;
        float f = n3 != 0 ? (float) n7 / (float) n9 : (float) n5 / (float) n10;
        float f2 = n4 != 0 ? (float) n8 / (float) n9 : (float) n6 / (float) n10;
        int n11 = this.computeAxisDuration(n, n3, this.mCallback.getViewHorizontalDragRange(view));
        int n12 = this.computeAxisDuration(n2, n4, this.mCallback.getViewVerticalDragRange(view));
        return (int) ((float) n11 * f + (float) n12 * f2);
    }

    private int computeAxisDuration(int n, int n2, int n3) {
        int n4;
        if (n == 0) {
            return 0;
        }
        int n5 = this.mParentView.getWidth();
        int n6 = n5 / 2;
        float f = Math.min((float) 1.0f, (float) ((float) Math.abs((int) n) / (float) n5));
        float f2 = (float) n6 + (float) n6 * this.distanceInfluenceForSnapDuration(f);
        if ((n2 = Math.abs((int) n2)) > 0) {
            n4 = 4 * Math.round((float) (1000.0f * Math.abs((float) (f2 / (float) n2))));
        } else {
            float f3 = (float) Math.abs((int) n) / (float) n3;
            n4 = (int) ((f3 + 1.0f) * 256.0f);
        }
        return Math.min((int) n4, (int) 600);
    }

    private int clampMag(int n, int n2, int n3) {
        int n4 = Math.abs((int) n);
        if (n4 < n2) {
            return 0;
        }
        if (n4 > n3) {
            return n > 0 ? n3 : -n3;
        }
        return n;
    }

    private float clampMag(float f, float f2, float f3) {
        float f4 = Math.abs((float) f);
        if (f4 < f2) {
            return 0.0f;
        }
        if (f4 > f3) {
            return f > 0.0f ? f3 : -f3;
        }
        return f;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        f -= 0.5f;
        f = (float) ((double) f * 0.4712389167638204);
        return (float) Math.sin((double) f);
    }

    public void flingCapturedView(int n, int n2, int n3, int n4) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), n, n3, n2, n4);
        this.setDragState(2);
    }

    public boolean continueSettling(boolean bl) {
        if (this.mDragState == 2) {
            boolean bl2 = this.mScroller.computeScrollOffset();
            int n = this.mScroller.getCurrX();
            int n2 = this.mScroller.getCurrY();
            int n3 = n - this.mCapturedView.getLeft();
            int n4 = n2 - this.mCapturedView.getTop();
            if (n3 != 0) {
                this.mCapturedView.offsetLeftAndRight(n3);
            }
            if (n4 != 0) {
                this.mCapturedView.offsetTopAndBottom(n4);
            }
            if (n3 != 0 || n4 != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, n, n2, n3, n4);
            }
            if (bl2 && n == this.mScroller.getFinalX() && n2 == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                bl2 = false;
            }
            if (!bl2) {
                if (bl) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    this.setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    private void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            this.setDragState(0);
        }
    }

    private void clearMotionHistory() {
        if (this.mInitialMotionX == null) {
            return;
        }
        Arrays.fill((float[]) this.mInitialMotionX, (float) 0.0f);
        Arrays.fill((float[]) this.mInitialMotionY, (float) 0.0f);
        Arrays.fill((float[]) this.mLastMotionX, (float) 0.0f);
        Arrays.fill((float[]) this.mLastMotionY, (float) 0.0f);
        Arrays.fill((int[]) this.mInitialEdgesTouched, (int) 0);
        Arrays.fill((int[]) this.mEdgeDragsInProgress, (int) 0);
        Arrays.fill((int[]) this.mEdgeDragsLocked, (int) 0);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int n) {
        if (this.mInitialMotionX == null) {
            return;
        }
        this.mInitialMotionX[n] = 0.0f;
        this.mInitialMotionY[n] = 0.0f;
        this.mLastMotionX[n] = 0.0f;
        this.mLastMotionY[n] = 0.0f;
        this.mInitialEdgesTouched[n] = 0;
        this.mEdgeDragsInProgress[n] = 0;
        this.mEdgeDragsLocked[n] = 0;
        this.mPointersDown &= ~(1 << n);
    }

    private void ensureMotionHistorySizeForId(int n) {
        if (this.mInitialMotionX == null || this.mInitialMotionX.length <= n) {
            float[] fArray = new float[n + 1];
            float[] fArray2 = new float[n + 1];
            float[] fArray3 = new float[n + 1];
            float[] fArray4 = new float[n + 1];
            int[] nArray = new int[n + 1];
            int[] nArray2 = new int[n + 1];
            int[] nArray3 = new int[n + 1];
            if (this.mInitialMotionX != null) {
                System.arraycopy((Object) this.mInitialMotionX, (int) 0, (Object) fArray, (int) 0, (int) this.mInitialMotionX.length);
                System.arraycopy((Object) this.mInitialMotionY, (int) 0, (Object) fArray2, (int) 0, (int) this.mInitialMotionY.length);
                System.arraycopy((Object) this.mLastMotionX, (int) 0, (Object) fArray3, (int) 0, (int) this.mLastMotionX.length);
                System.arraycopy((Object) this.mLastMotionY, (int) 0, (Object) fArray4, (int) 0, (int) this.mLastMotionY.length);
                System.arraycopy((Object) this.mInitialEdgesTouched, (int) 0, (Object) nArray, (int) 0, (int) this.mInitialEdgesTouched.length);
                System.arraycopy((Object) this.mEdgeDragsInProgress, (int) 0, (Object) nArray2, (int) 0, (int) this.mEdgeDragsInProgress.length);
                System.arraycopy((Object) this.mEdgeDragsLocked, (int) 0, (Object) nArray3, (int) 0, (int) this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = fArray;
            this.mInitialMotionY = fArray2;
            this.mLastMotionX = fArray3;
            this.mLastMotionY = fArray4;
            this.mInitialEdgesTouched = nArray;
            this.mEdgeDragsInProgress = nArray2;
            this.mEdgeDragsLocked = nArray3;
        }
    }

    private void saveInitialMotion(float f, float f2, int n) {
        this.ensureMotionHistorySizeForId(n);
        this.mInitialMotionX[n] = this.mLastMotionX[n] = f;
        this.mInitialMotionY[n] = this.mLastMotionY[n] = f2;
        this.mInitialEdgesTouched[n] = this.getEdgesTouched((int) f, (int) f2);
        this.mPointersDown |= 1 << n;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int n = motionEvent.getPointerCount();
        for (int i = 0; i < n; ++i) {
            int n2 = motionEvent.getPointerId(i);
            float f = motionEvent.getX(i);
            float f2 = motionEvent.getY(i);
            this.mLastMotionX[n2] = f;
            this.mLastMotionY[n2] = f2;
        }
    }

    public boolean isPointerDown(int n) {
        return (this.mPointersDown & 1 << n) != 0;
    }

    void setDragState(int n) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != n) {
            this.mDragState = n;
            this.mCallback.onViewDragStateChanged(n);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    boolean tryCaptureViewForDrag(View view, int n) {
        if (view == this.mCapturedView && this.mActivePointerId == n) {
            return true;
        }
        if (view != null && this.mCallback.tryCaptureView(view, n)) {
            this.mActivePointerId = n;
            this.captureChildView(view, n);
            return true;
        }
        return false;
    }

    protected boolean canScroll(View view, boolean bl, int n, int n2, int n3, int n4) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int n5 = view.getScrollX();
            int n6 = view.getScrollY();
            int n7 = viewGroup.getChildCount();
            for (int i = n7 - 1; i >= 0; --i) {
                View view2 = viewGroup.getChildAt(i);
                if (n3 + n5 < view2.getLeft() || n3 + n5 >= view2.getRight() || n4 + n6 < view2.getTop() || n4 + n6 >= view2.getBottom() || !this.canScroll(view2, true, n, n2, n3 + n5 - view2.getLeft(), n4 + n6 - view2.getTop()))
                    continue;
                return true;
            }
        }
        return bl && (view.canScrollHorizontally(-n) || view.canScrollVertically(-n2));
    }

    public boolean shouldInterceptTouchEvent(MotionEvent motionEvent) {
        int n = motionEvent.getActionMasked();
        int n2 = motionEvent.getActionIndex();
        if (n == 0) {
            this.cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (n) {
            case 0: {
                int n3;
                float f = motionEvent.getX();
                float f2 = motionEvent.getY();
                int n4 = motionEvent.getPointerId(0);
                this.saveInitialMotion(f, f2, n4);
                View view = this.findTopChildUnder((int) f, (int) f2);
                if (view == this.mCapturedView && this.mDragState == 2) {
                    this.tryCaptureViewForDrag(view, n4);
                }
                if (((n3 = this.mInitialEdgesTouched[n4]) & this.mTrackingEdges) == 0) break;
                this.mCallback.onEdgeTouched(n3 & this.mTrackingEdges, n4);
                break;
            }
            case 5: {
                View view;
                int n5 = motionEvent.getPointerId(n2);
                float f = motionEvent.getX(n2);
                float f3 = motionEvent.getY(n2);
                this.saveInitialMotion(f, f3, n5);
                if (this.mDragState == 0) {
                    int n6 = this.mInitialEdgesTouched[n5];
                    if ((n6 & this.mTrackingEdges) == 0) break;
                    this.mCallback.onEdgeTouched(n6 & this.mTrackingEdges, n5);
                    break;
                }
                if (this.mDragState != 2 || (view = this.findTopChildUnder((int) f, (int) f3)) != this.mCapturedView)
                    break;
                this.tryCaptureViewForDrag(view, n5);
                break;
            }
            case 2: {
                if (this.mInitialMotionX == null || this.mInitialMotionY == null) break;
                int n7 = motionEvent.getPointerCount();
                for (int i = 0; i < n7; ++i) {
                    boolean bl;
                    int n8 = motionEvent.getPointerId(i);
                    float f = motionEvent.getX(i);
                    float f4 = motionEvent.getY(i);
                    float f5 = f - this.mInitialMotionX[n8];
                    float f6 = f4 - this.mInitialMotionY[n8];
                    View view = this.findTopChildUnder((int) f, (int) f4);
                    boolean bl2 = bl = view != null && this.checkTouchSlop(view, f5, f6);
                    if (bl) {
                        int n9 = view.getLeft();
                        int n10 = n9 + (int) f5;
                        int n11 = this.mCallback.clampViewPositionHorizontal(view, n10, (int) f5);
                        int n12 = view.getTop();
                        int n13 = n12 + (int) f6;
                        int n14 = this.mCallback.clampViewPositionVertical(view, n13, (int) f6);
                        int n15 = this.mCallback.getViewHorizontalDragRange(view);
                        int n16 = this.mCallback.getViewVerticalDragRange(view);
                        if ((n15 == 0 || n15 > 0 && n11 == n9) && (n16 == 0 || n16 > 0 && n14 == n12))
                            break;
                    }
                    this.reportNewEdgeDrags(f5, f6, n8);
                    if (this.mDragState == 1 || bl && this.tryCaptureViewForDrag(view, n8)) break;
                }
                this.saveLastMotion(motionEvent);
                break;
            }
            case 6: {
                int n17 = motionEvent.getPointerId(n2);
                this.clearMotionHistory(n17);
                break;
            }
            case 1:
            case 3: {
                this.cancel();
            }
        }
        return this.mDragState == 1;
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int n = motionEvent.getActionMasked();
        int n2 = motionEvent.getActionIndex();
        if (n == 0) {
            this.cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (n) {
            case 0: {
                float f = motionEvent.getX();
                float f2 = motionEvent.getY();
                int n3 = motionEvent.getPointerId(0);
                View view = this.findTopChildUnder((int) f, (int) f2);
                this.saveInitialMotion(f, f2, n3);
                this.tryCaptureViewForDrag(view, n3);
                int n4 = this.mInitialEdgesTouched[n3];
                if ((n4 & this.mTrackingEdges) == 0) break;
                this.mCallback.onEdgeTouched(n4 & this.mTrackingEdges, n3);
                break;
            }
            case 5: {
                int n5 = motionEvent.getPointerId(n2);
                float f = motionEvent.getX(n2);
                float f3 = motionEvent.getY(n2);
                this.saveInitialMotion(f, f3, n5);
                if (this.mDragState == 0) {
                    View view = this.findTopChildUnder((int) f, (int) f3);
                    this.tryCaptureViewForDrag(view, n5);
                    int n6 = this.mInitialEdgesTouched[n5];
                    if ((n6 & this.mTrackingEdges) == 0) break;
                    this.mCallback.onEdgeTouched(n6 & this.mTrackingEdges, n5);
                    break;
                }
                if (!this.isCapturedViewUnder((int) f, (int) f3)) break;
                this.tryCaptureViewForDrag(this.mCapturedView, n5);
                break;
            }
            case 2: {
                if (this.mDragState == 1) {
                    int n7 = motionEvent.findPointerIndex(this.mActivePointerId);
                    float f = motionEvent.getX(n7);
                    float f4 = motionEvent.getY(n7);
                    int n8 = (int) (f - this.mLastMotionX[this.mActivePointerId]);
                    int n9 = (int) (f4 - this.mLastMotionY[this.mActivePointerId]);
                    this.dragTo(this.mCapturedView.getLeft() + n8, this.mCapturedView.getTop() + n9, n8, n9);
                    this.saveLastMotion(motionEvent);
                    break;
                }
                int n10 = motionEvent.getPointerCount();
                for (int i = 0; i < n10; ++i) {
                    View view;
                    int n11 = motionEvent.getPointerId(i);
                    float f = motionEvent.getX(i);
                    float f5 = motionEvent.getY(i);
                    float f6 = f - this.mInitialMotionX[n11];
                    float f7 = f5 - this.mInitialMotionY[n11];
                    this.reportNewEdgeDrags(f6, f7, n11);
                    if (this.mDragState == 1 || this.checkTouchSlop(view = this.findTopChildUnder((int) f, (int) f5), f6, f7) && this.tryCaptureViewForDrag(view, n11))
                        break;
                }
                this.saveLastMotion(motionEvent);
                break;
            }
            case 6: {
                int n12 = motionEvent.getPointerId(n2);
                if (this.mDragState == 1 && n12 == this.mActivePointerId) {
                    int n13 = -1;
                    int n14 = motionEvent.getPointerCount();
                    for (int i = 0; i < n14; ++i) {
                        float f;
                        float f8;
                        int n15 = motionEvent.getPointerId(i);
                        if (n15 == this.mActivePointerId || this.findTopChildUnder((int) (f8 = motionEvent.getX(i)), (int) (f = motionEvent.getY(i))) != this.mCapturedView || !this.tryCaptureViewForDrag(this.mCapturedView, n15))
                            continue;
                        n13 = this.mActivePointerId;
                        break;
                    }
                    if (n13 == -1) {
                        this.releaseViewForPointerUp();
                    }
                }
                this.clearMotionHistory(n12);
                break;
            }
            case 1: {
                if (this.mDragState == 1) {
                    this.releaseViewForPointerUp();
                }
                this.cancel();
                break;
            }
            case 3: {
                if (this.mDragState == 1) {
                    this.dispatchViewReleased(0.0f, 0.0f);
                }
                this.cancel();
            }
        }
    }

    private void reportNewEdgeDrags(float f, float f2, int n) {
        int n2 = 0;
        if (this.checkNewEdgeDrag(f, f2, n, 1)) {
            n2 |= 1;
        }
        if (this.checkNewEdgeDrag(f2, f, n, 4)) {
            n2 |= 4;
        }
        if (this.checkNewEdgeDrag(f, f2, n, 2)) {
            n2 |= 2;
        }
        if (this.checkNewEdgeDrag(f2, f, n, 8)) {
            n2 |= 8;
        }
        if (n2 != 0) {
            int n3 = n;
            this.mEdgeDragsInProgress[n3] = this.mEdgeDragsInProgress[n3] | n2;
            this.mCallback.onEdgeDragStarted(n2, n);
        }
    }

    private boolean checkNewEdgeDrag(float f, float f2, int n, int n2) {
        float f3 = Math.abs((float) f);
        float f4 = Math.abs((float) f2);
        if ((this.mInitialEdgesTouched[n] & n2) != n2 || (this.mTrackingEdges & n2) == 0 || (this.mEdgeDragsLocked[n] & n2) == n2 || (this.mEdgeDragsInProgress[n] & n2) == n2 || f3 <= (float) this.mTouchSlop && f4 <= (float) this.mTouchSlop) {
            return false;
        }
        if (f3 < f4 * 0.5f && this.mCallback.onEdgeLock(n2)) {
            int n3 = n;
            this.mEdgeDragsLocked[n3] = this.mEdgeDragsLocked[n3] | n2;
            return false;
        }
        return (this.mEdgeDragsInProgress[n] & n2) == 0 && f3 > (float) this.mTouchSlop;
    }

    private boolean checkTouchSlop(View view, float f, float f2) {
        boolean bl;
        if (view == null) {
            return false;
        }
        boolean bl2 = this.mCallback.getViewHorizontalDragRange(view) > 0;
        boolean bl3 = bl = this.mCallback.getViewVerticalDragRange(view) > 0;
        if (bl2 && bl) {
            return f * f + f2 * f2 > (float) (this.mTouchSlop * this.mTouchSlop);
        }
        if (bl2) {
            return Math.abs((float) f) > (float) this.mTouchSlop;
        }
        if (bl) {
            return Math.abs((float) f2) > (float) this.mTouchSlop;
        }
        return false;
    }

    public boolean checkTouchSlop(int n) {
        int n2 = this.mInitialMotionX.length;
        for (int i = 0; i < n2; ++i) {
            if (!this.checkTouchSlop(n, i)) continue;
            return true;
        }
        return false;
    }

    public boolean checkTouchSlop(int n, int n2) {
        if (!this.isPointerDown(n2)) {
            return false;
        }
        boolean bl = (n & 1) == 1;
        boolean bl2 = (n & 2) == 2;
        float f = this.mLastMotionX[n2] - this.mInitialMotionX[n2];
        float f2 = this.mLastMotionY[n2] - this.mInitialMotionY[n2];
        if (bl && bl2) {
            return f * f + f2 * f2 > (float) (this.mTouchSlop * this.mTouchSlop);
        }
        if (bl) {
            return Math.abs((float) f) > (float) this.mTouchSlop;
        }
        if (bl2) {
            return Math.abs((float) f2) > (float) this.mTouchSlop;
        }
        return false;
    }

    public boolean isEdgeTouched(int n) {
        int n2 = this.mInitialEdgesTouched.length;
        for (int i = 0; i < n2; ++i) {
            if (!this.isEdgeTouched(n, i)) continue;
            return true;
        }
        return false;
    }

    public boolean isEdgeTouched(int n, int n2) {
        return this.isPointerDown(n2) && (this.mInitialEdgesTouched[n2] & n) != 0;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        float f = this.clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity);
        float f2 = this.clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity);
        this.dispatchViewReleased(f, f2);
    }

    private void dragTo(int n, int n2, int n3, int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = this.mCapturedView.getLeft();
        int n8 = this.mCapturedView.getTop();
        if (n3 != 0) {
            n5 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, n, n3);
            this.mCapturedView.offsetLeftAndRight(n5 - n7);
        }
        if (n4 != 0) {
            n6 = this.mCallback.clampViewPositionVertical(this.mCapturedView, n2, n4);
            this.mCapturedView.offsetTopAndBottom(n6 - n8);
        }
        if (n3 != 0 || n4 != 0) {
            int n9 = n5 - n7;
            int n10 = n6 - n8;
            this.mCallback.onViewPositionChanged(this.mCapturedView, n5, n6, n9, n10);
        }
    }

    public boolean isCapturedViewUnder(int n, int n2) {
        return this.isViewUnder(this.mCapturedView, n, n2);
    }

    public boolean isViewUnder(View view, int n, int n2) {
        if (view == null) {
            return false;
        }
        return n >= view.getLeft() && n < view.getRight() && n2 >= view.getTop() && n2 < view.getBottom();
    }

    public View findTopChildUnder(int n, int n2) {
        int n3 = this.mParentView.getChildCount();
        for (int i = n3 - 1; i >= 0; --i) {
            View view = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(i));
            if (n < view.getLeft() || n >= view.getRight() || n2 < view.getTop() || n2 >= view.getBottom())
                continue;
            return view;
        }
        return null;
    }

    private int getEdgesTouched(int n, int n2) {
        int n3 = 0;
        if (n < this.mParentView.getLeft() + this.mEdgeSize) {
            n3 |= 1;
        }
        if (n2 < this.mParentView.getTop() + this.mEdgeSize) {
            n3 |= 4;
        }
        if (n > this.mParentView.getRight() - this.mEdgeSize) {
            n3 |= 2;
        }
        if (n2 > this.mParentView.getBottom() - this.mEdgeSize) {
            n3 |= 8;
        }
        return n3;
    }

    public static abstract class Callback {
        public void onViewDragStateChanged(int n) {
        }

        public void onViewPositionChanged(View view, int n, int n2, int n3, int n4) {
        }

        public void onViewCaptured(View view, int n) {
        }

        public void onViewReleased(View view, float f, float f2) {
        }

        public void onEdgeTouched(int n, int n2) {
        }

        public boolean onEdgeLock(int n) {
            return false;
        }

        public void onEdgeDragStarted(int n, int n2) {
        }

        public int getOrderedChildIndex(int n) {
            return n;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public abstract boolean tryCaptureView(View var1, int var2);

        public int clampViewPositionHorizontal(View view, int n, int n2) {
            return 0;
        }

        public int clampViewPositionVertical(View view, int n, int n2) {
            return 0;
        }
    }




}

