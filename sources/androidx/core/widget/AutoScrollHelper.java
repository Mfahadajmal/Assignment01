package androidx.core.widget;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ListView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoScrollHelper implements View.OnTouchListener {
    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    public boolean mAnimating;
    private final Interpolator mEdgeInterpolator;
    private int mEdgeType;
    private boolean mEnabled;
    private float[] mMaximumEdges;
    private float[] mMaximumVelocity;
    private float[] mMinimumVelocity;
    public boolean mNeedsCancel;
    public boolean mNeedsReset;
    private float[] mRelativeEdges;
    private float[] mRelativeVelocity;
    private Runnable mRunnable;
    public final ClampedScroller mScroller;
    public final View mTarget;

    /* renamed from: mTarget, reason: collision with other field name */
    private final ListView f0mTarget;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClampedScroller {
        public int mEffectiveRampDown;
        public int mRampDownDuration;
        public int mRampUpDuration;
        public float mStopValue;
        public float mTargetVelocityX;
        public float mTargetVelocityY;
        public long mStartTime = Long.MIN_VALUE;
        public long mStopTime = -1;
        public long mDeltaTime = 0;
        public int mDeltaX = 0;
        public int mDeltaY = 0;

        public final float getValueAt(long j) {
            long j2 = this.mStartTime;
            if (j < j2) {
                return 0.0f;
            }
            long j3 = this.mStopTime;
            if (j3 >= 0 && j >= j3) {
                float f = this.mStopValue;
                return (1.0f - f) + (f * AutoScrollHelper.constrain(((float) (j - j3)) / this.mEffectiveRampDown, 0.0f, 1.0f));
            }
            return AutoScrollHelper.constrain(((float) (j - j2)) / this.mRampUpDuration, 0.0f, 1.0f) * 0.5f;
        }
    }

    public AutoScrollHelper(View view) {
        ClampedScroller clampedScroller = new ClampedScroller();
        this.mScroller = clampedScroller;
        this.mEdgeInterpolator = new AccelerateInterpolator();
        this.mRelativeEdges = new float[]{0.0f, 0.0f};
        this.mMaximumEdges = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.mRelativeVelocity = new float[]{0.0f, 0.0f};
        this.mMinimumVelocity = new float[]{0.0f, 0.0f};
        this.mMaximumVelocity = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.mTarget = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        float f = displayMetrics.density * 1575.0f;
        float f2 = displayMetrics.density * 315.0f;
        float f3 = ((int) (f + 0.5f)) / 1000.0f;
        float[] fArr = this.mMaximumVelocity;
        fArr[0] = f3;
        fArr[1] = f3;
        float f4 = ((int) (f2 + 0.5f)) / 1000.0f;
        float[] fArr2 = this.mMinimumVelocity;
        fArr2[0] = f4;
        fArr2[1] = f4;
        this.mEdgeType = 1;
        float[] fArr3 = this.mMaximumEdges;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.mRelativeEdges;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.mRelativeVelocity;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        this.mActivationDelay = DEFAULT_ACTIVATION_DELAY;
        clampedScroller.mRampUpDuration = 500;
        clampedScroller.mRampDownDuration = 500;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final float computeTargetVelocity(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.mRelativeEdges
            r0 = r0[r4]
            float[] r1 = r3.mMaximumEdges
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = constrain(r0, r2, r1)
            float r6 = r6 - r5
            float r5 = r3.constrainEdgeValue(r5, r0)
            float r6 = r3.constrainEdgeValue(r6, r0)
            float r6 = r6 - r5
            int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r5 >= 0) goto L25
            android.view.animation.Interpolator r5 = r3.mEdgeInterpolator
            float r6 = -r6
            float r5 = r5.getInterpolation(r6)
            float r5 = -r5
            goto L2f
        L25:
            int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r5 <= 0) goto L38
            android.view.animation.Interpolator r5 = r3.mEdgeInterpolator
            float r5 = r5.getInterpolation(r6)
        L2f:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = constrain(r5, r6, r0)
            goto L39
        L38:
            r5 = r2
        L39:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L3e
            return r2
        L3e:
            float[] r0 = r3.mRelativeVelocity
            r0 = r0[r4]
            float[] r1 = r3.mMinimumVelocity
            r1 = r1[r4]
            float[] r2 = r3.mMaximumVelocity
            r4 = r2[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L53
            float r5 = r5 * r0
            float r4 = constrain(r5, r1, r4)
            return r4
        L53:
            float r5 = -r5
            float r5 = r5 * r0
            float r4 = constrain(r5, r1, r4)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.computeTargetVelocity(int, float, float, float):float");
    }

    static float constrain(float f, float f2, float f3) {
        if (f > f3) {
            return f3;
        }
        if (f < f2) {
            return f2;
        }
        return f;
    }

    private final float constrainEdgeValue(float f, float f2) {
        if (f2 != 0.0f && f < f2) {
            if (f >= 0.0f) {
                return 1.0f - (f / f2);
            }
            if (this.mAnimating && this.mEdgeType == 1) {
                return 1.0f;
            }
        }
        return 0.0f;
    }

    private final void requestStop() {
        int i = 0;
        if (this.mNeedsReset) {
            this.mAnimating = false;
            return;
        }
        ClampedScroller clampedScroller = this.mScroller;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (currentAnimationTimeMillis - clampedScroller.mStartTime);
        int i3 = clampedScroller.mRampDownDuration;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        clampedScroller.mEffectiveRampDown = i;
        clampedScroller.mStopValue = clampedScroller.getValueAt(currentAnimationTimeMillis);
        clampedScroller.mStopTime = currentAnimationTimeMillis;
    }

    public final boolean canTargetScrollVertically(int i) {
        ListView listView = this.f0mTarget;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (i > 0) {
            if (i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
            return true;
        }
        if (i >= 0) {
            return false;
        }
        if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0014, code lost:
    
        if (r0 != 3) goto L29;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.mEnabled
            r1 = 0
            if (r0 != 0) goto L7
            goto L82
        L7:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1b
            if (r0 == r2) goto L17
            r3 = 2
            if (r0 == r3) goto L1f
            r6 = 3
            if (r0 == r6) goto L17
            goto L82
        L17:
            r5.requestStop()
            goto L82
        L1b:
            r5.mNeedsCancel = r2
            r5.mAlreadyDelayed = r1
        L1f:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.mTarget
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.computeTargetVelocity(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.mTarget
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.computeTargetVelocity(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$ClampedScroller r7 = r5.mScroller
            r7.mTargetVelocityX = r0
            r7.mTargetVelocityY = r6
            boolean r6 = r5.mAnimating
            if (r6 != 0) goto L82
            boolean r6 = r5.shouldAnimate()
            if (r6 == 0) goto L82
            java.lang.Runnable r6 = r5.mRunnable
            if (r6 != 0) goto L64
            android.support.v7.widget.SearchView$SearchAutoComplete$1 r6 = new android.support.v7.widget.SearchView$SearchAutoComplete$1
            r7 = 10
            r6.<init>(r5, r7)
            r5.mRunnable = r6
        L64:
            r5.mAnimating = r2
            r5.mNeedsReset = r2
            boolean r6 = r5.mAlreadyDelayed
            if (r6 != 0) goto L7b
            int r6 = r5.mActivationDelay
            if (r6 <= 0) goto L7b
            android.view.View r7 = r5.mTarget
            java.lang.Runnable r0 = r5.mRunnable
            int[] r3 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            long r3 = (long) r6
            r7.postOnAnimationDelayed(r0, r3)
            goto L80
        L7b:
            java.lang.Runnable r6 = r5.mRunnable
            r6.run()
        L80:
            r5.mAlreadyDelayed = r2
        L82:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void scrollTargetBy$ar$ds(int i) {
        this.f0mTarget.scrollListBy(i);
    }

    public final void setEnabled$ar$ds(boolean z) {
        if (this.mEnabled && !z) {
            requestStop();
        }
        this.mEnabled = z;
    }

    public final boolean shouldAnimate() {
        ClampedScroller clampedScroller = this.mScroller;
        float f = clampedScroller.mTargetVelocityY;
        float abs = f / Math.abs(f);
        float f2 = clampedScroller.mTargetVelocityX;
        float abs2 = f2 / Math.abs(f2);
        int i = (int) abs;
        if (i == 0 || !canTargetScrollVertically(i)) {
            return ((int) abs2) == 0 ? false : false;
        }
        return true;
    }

    public AutoScrollHelper(ListView listView) {
        this((View) listView);
        this.f0mTarget = listView;
    }
}
