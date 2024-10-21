package androidx.customview.widget;

import android.content.Context;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewDragHelper {
    private static final Interpolator sInterpolator = new AnonymousClass1(0);
    private final Callback mCallback;
    private View mCapturedView;
    private final int mDefaultEdgeSize;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private final float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private final OverScroller mScroller;
    public int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private final Runnable mSetIdleRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 11);

    /* compiled from: PG */
    /* renamed from: androidx.customview.widget.ViewDragHelper$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Interpolator {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            if (this.switching_field != 0) {
            }
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (callback != null) {
            this.mParentView = viewGroup;
            this.mCallback = callback;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int i = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.mDefaultEdgeSize = i;
            this.mEdgeSize = i;
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mScroller = new OverScroller(context, sInterpolator);
            return;
        }
        throw new NullPointerException("Callback may not be null");
    }

    private final boolean checkNewEdgeDrag$ar$ds(float f, float f2, int i) {
        Math.abs(f);
        Math.abs(f2);
        int i2 = this.mInitialEdgesTouched[i];
        return false;
    }

    private final boolean checkTouchSlop(View view, float f, float f2) {
        boolean z;
        boolean z2;
        if (view == null) {
            return false;
        }
        if (this.mCallback.getViewHorizontalDragRange(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.mCallback.getViewVerticalDragRange$ar$ds() > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            int i = this.mTouchSlop;
            if ((f * f) + (f2 * f2) <= i * i) {
                return false;
            }
            return true;
        }
        if (z) {
            if (Math.abs(f) <= this.mTouchSlop) {
                return false;
            }
            return true;
        }
        if (!z2 || Math.abs(f2) <= this.mTouchSlop) {
            return false;
        }
        return true;
    }

    private static final float clampMag$ar$ds(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs > f3) {
            if (f > 0.0f) {
                return f3;
            }
            return -f3;
        }
        return f;
    }

    private static final int clampMag$ar$ds$e9f0509f_0(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs > i3) {
            if (i > 0) {
                return i3;
            }
            return -i3;
        }
        return i;
    }

    private final void clearMotionHistory(int i) {
        float[] fArr = this.mInitialMotionX;
        if (fArr != null && isPointerDown(i)) {
            fArr[i] = 0.0f;
            this.mInitialMotionY[i] = 0.0f;
            this.mLastMotionX[i] = 0.0f;
            this.mLastMotionY[i] = 0.0f;
            this.mInitialEdgesTouched[i] = 0;
            this.mEdgeDragsInProgress[i] = 0;
            this.mEdgeDragsLocked[i] = 0;
            this.mPointersDown = (~(1 << i)) & this.mPointersDown;
        }
    }

    private final int computeAxisDuration(int i, int i2, int i3) {
        int abs;
        if (i == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth() / 2;
        float sin = (float) Math.sin((Math.min(1.0f, Math.abs(i) / r0) - 0.5f) * 0.47123894f);
        int abs2 = Math.abs(i2);
        if (abs2 > 0) {
            float f = width;
            abs = Math.round(Math.abs((f + (sin * f)) / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(abs, 600);
    }

    public static ViewDragHelper create(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private final void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private final boolean forceSettleCapturedViewAt(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0) {
            i5 = 0;
            if (i6 == 0) {
                this.mScroller.abortAnimation();
                setDragState(0);
                return false;
            }
        }
        int i7 = i5;
        View view = this.mCapturedView;
        int clampMag$ar$ds$e9f0509f_0 = clampMag$ar$ds$e9f0509f_0(i3, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag$ar$ds$e9f0509f_02 = clampMag$ar$ds$e9f0509f_0(i4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i7);
        int abs2 = Math.abs(i6);
        int abs3 = Math.abs(clampMag$ar$ds$e9f0509f_0);
        int abs4 = Math.abs(clampMag$ar$ds$e9f0509f_02);
        int i8 = abs3 + abs4;
        int i9 = abs + abs2;
        if (clampMag$ar$ds$e9f0509f_0 != 0) {
            f = abs3 / i8;
        } else {
            f = abs / i9;
        }
        if (clampMag$ar$ds$e9f0509f_02 != 0) {
            f2 = i8;
            f3 = abs4;
        } else {
            f2 = i9;
            f3 = abs2;
        }
        this.mScroller.startScroll(left, top, i7, i6, (int) ((computeAxisDuration(i7, clampMag$ar$ds$e9f0509f_0, this.mCallback.getViewHorizontalDragRange(view)) * f) + (computeAxisDuration(i6, clampMag$ar$ds$e9f0509f_02, this.mCallback.getViewVerticalDragRange$ar$ds()) * (f3 / f2))));
        setDragState(2);
        return true;
    }

    private final boolean isValidPointerForActionMove(int i) {
        if (!isPointerDown(i)) {
            return false;
        }
        return true;
    }

    private final void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag$ar$ds(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag$ar$ds(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private final void reportNewEdgeDrags(float f, float f2, int i) {
        checkNewEdgeDrag$ar$ds(f, f2, i);
        checkNewEdgeDrag$ar$ds(f2, f, i);
        checkNewEdgeDrag$ar$ds(f, f2, i);
        checkNewEdgeDrag$ar$ds(f2, f, i);
    }

    private final void saveInitialMotion(float f, float f2, int i) {
        float[] fArr = this.mInitialMotionX;
        int i2 = 0;
        if (fArr == null || fArr.length <= i) {
            int i3 = i + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.mInitialEdgesTouched;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.mEdgeDragsInProgress;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.mEdgeDragsLocked;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
        float[] fArr9 = this.mInitialMotionX;
        this.mLastMotionX[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.mInitialMotionY;
        this.mLastMotionY[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.mInitialEdgesTouched;
        int i4 = (int) f;
        int i5 = (int) f2;
        if (i4 < this.mParentView.getLeft() + this.mEdgeSize) {
            i2 = 1;
        }
        if (i5 < this.mParentView.getTop() + this.mEdgeSize) {
            i2 |= 4;
        }
        if (i4 > this.mParentView.getRight() - this.mEdgeSize) {
            i2 |= 2;
        }
        if (i5 > this.mParentView.getBottom() - this.mEdgeSize) {
            i2 |= 8;
        }
        iArr7[i] = i2;
        this.mPointersDown |= 1 << i;
    }

    private final void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public final void cancel() {
        this.mActivePointerId = -1;
        float[] fArr = this.mInitialMotionX;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public final void captureChildView(View view, int i) {
        if (view.getParent() == this.mParentView) {
            this.mCapturedView = view;
            this.mActivePointerId = i;
            this.mCallback.onViewCaptured(view, i);
            setDragState(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
    }

    public final boolean continueSettling$ar$ds() {
        if (this.mDragState == 2) {
            OverScroller overScroller = this.mScroller;
            boolean computeScrollOffset = overScroller.computeScrollOffset();
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                View view = this.mCapturedView;
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                view.offsetLeftAndRight(left);
            }
            if (top != 0) {
                View view2 = this.mCapturedView;
                int[] iArr2 = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                view2.offsetTopAndBottom(top);
            }
            if (left != 0 || top != 0) {
                this.mCallback.onViewPositionChanged$ar$ds(this.mCapturedView, currX, currY);
            }
            if (computeScrollOffset) {
                if (currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                    this.mScroller.abortAnimation();
                }
            }
            this.mParentView.post(this.mSetIdleRunnable);
        }
        if (this.mDragState == 2) {
            return true;
        }
        return false;
    }

    public final View findTopChildUnder(int i, int i2) {
        int childCount = this.mParentView.getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                View childAt = this.mParentView.getChildAt(childCount);
                if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                    return childAt;
                }
            } else {
                return null;
            }
        }
    }

    public final boolean isPointerDown(int i) {
        if (((1 << i) & this.mPointersDown) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        if (r9.mActivePointerId == (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006d, code lost:
    
        releaseViewForPointerUp();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void processTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.processTouchEvent(android.view.MotionEvent):void");
    }

    public final void setDragState(int i) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i) {
            this.mDragState = i;
            this.mCallback.onViewDragStateChanged(i);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public final boolean settleCapturedViewAt(int i, int i2) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i, i2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c9, code lost:
    
        if (r12 != r11) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean shouldInterceptTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean smoothSlideViewTo(View view, int i, int i2) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(i, i2, 0, 0);
        if (!forceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
            return false;
        }
        return forceSettleCapturedViewAt;
    }

    final boolean tryCaptureViewForDrag(View view, int i) {
        if (view == this.mCapturedView && this.mActivePointerId == i) {
            return true;
        }
        if (view != null && this.mCallback.tryCaptureView(view, i)) {
            this.mActivePointerId = i;
            captureChildView(view, i);
            return true;
        }
        return false;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Callback {
        public int clampViewPositionHorizontal$ar$ds(View view, int i) {
            throw null;
        }

        public int clampViewPositionVertical$ar$ds(View view, int i) {
            throw null;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange$ar$ds() {
            return 0;
        }

        public void onViewDragStateChanged(int i) {
            throw null;
        }

        public void onViewPositionChanged$ar$ds(View view, int i, int i2) {
            throw null;
        }

        public void onViewReleased(View view, float f, float f2) {
            throw null;
        }

        public abstract boolean tryCaptureView(View view, int i);

        public void onViewCaptured(View view, int i) {
        }
    }
}
