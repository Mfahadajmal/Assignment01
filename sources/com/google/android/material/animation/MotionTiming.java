package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MotionTiming {
    private final long delay;
    private final long duration;
    private final TimeInterpolator interpolator;
    public int repeatCount = 0;
    public int repeatMode = 1;

    public MotionTiming(long j, long j2, TimeInterpolator timeInterpolator) {
        this.delay = j;
        this.duration = j2;
        this.interpolator = timeInterpolator;
    }

    public final void apply(Animator animator) {
        animator.setStartDelay(this.delay);
        animator.setDuration(this.duration);
        animator.setInterpolator(getInterpolator());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(this.repeatCount);
            valueAnimator.setRepeatMode(this.repeatMode);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionTiming)) {
            return false;
        }
        MotionTiming motionTiming = (MotionTiming) obj;
        if (this.delay != motionTiming.delay || this.duration != motionTiming.duration || this.repeatCount != motionTiming.repeatCount || this.repeatMode != motionTiming.repeatMode) {
            return false;
        }
        return getInterpolator().getClass().equals(motionTiming.getInterpolator().getClass());
    }

    public final TimeInterpolator getInterpolator() {
        TimeInterpolator timeInterpolator = this.interpolator;
        if (timeInterpolator != null) {
            return timeInterpolator;
        }
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public final int hashCode() {
        int hashCode = getInterpolator().getClass().hashCode();
        long j = this.duration;
        long j2 = this.delay;
        return (((((((((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + hashCode) * 31) + this.repeatCount) * 31) + this.repeatMode;
    }

    public final String toString() {
        return "\n" + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.delay + " duration: " + this.duration + " interpolator: " + getInterpolator().getClass() + " repeatCount: " + this.repeatCount + " repeatMode: " + this.repeatMode + "}\n";
    }
}
