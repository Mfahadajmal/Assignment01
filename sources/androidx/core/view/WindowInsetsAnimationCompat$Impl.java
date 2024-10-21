package androidx.core.view;

import android.view.animation.Interpolator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WindowInsetsAnimationCompat$Impl {
    private final long mDurationMillis;
    private float mFraction;
    private final Interpolator mInterpolator;
    private final int mTypeMask;

    public WindowInsetsAnimationCompat$Impl(int i, Interpolator interpolator, long j) {
        this.mTypeMask = i;
        this.mInterpolator = interpolator;
        this.mDurationMillis = j;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public float getInterpolatedFraction() {
        Interpolator interpolator = this.mInterpolator;
        if (interpolator != null) {
            return interpolator.getInterpolation(this.mFraction);
        }
        return this.mFraction;
    }

    public int getTypeMask() {
        return this.mTypeMask;
    }

    public void setFraction(float f) {
        this.mFraction = f;
    }
}
