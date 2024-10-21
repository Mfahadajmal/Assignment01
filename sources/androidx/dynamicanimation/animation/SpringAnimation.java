package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$DurationScaleChangeListener;
import android.os.Build;
import android.os.Looper;
import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation {
    private float mPendingPosition;
    public SpringForce mSpring;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
    }

    /* JADX WARN: Type inference failed for: r0v27, types: [android.animation.ValueAnimator$DurationScaleChangeListener, java.lang.Object] */
    public final void animateToFinalPosition(float f) {
        float durationScale;
        if (this.mRunning) {
            this.mPendingPosition = f;
            return;
        }
        if (this.mSpring == null) {
            this.mSpring = new SpringForce(f);
        }
        this.mSpring.setFinalPosition$ar$ds(f);
        SpringForce springForce = this.mSpring;
        if (springForce != null) {
            double finalPosition = springForce.getFinalPosition();
            if (finalPosition <= this.mMaxValue) {
                if (finalPosition >= this.mMinValue) {
                    SpringForce springForce2 = this.mSpring;
                    double abs = Math.abs(this.mMinVisibleChange * 0.75f);
                    springForce2.mValueThreshold = abs;
                    springForce2.mVelocityThreshold = abs * 62.5d;
                    if (Thread.currentThread() == ((Looper) AnimationHandler.getInstance().mScheduler$ar$class_merging$ar$class_merging$ar$class_merging.NodeBrailler$ar$rules).getThread()) {
                        if (!this.mRunning) {
                            this.mRunning = true;
                            if (!this.mStartValueIsSet) {
                                this.mValue = ((DynamicAnimation.AnonymousClass15) this.mProperty$ar$class_merging).val$floatValueHolder.mValue;
                            }
                            float f2 = this.mValue;
                            if (f2 <= this.mMaxValue && f2 >= this.mMinValue) {
                                AnimationHandler animationHandler = AnimationHandler.getInstance();
                                if (animationHandler.mAnimationCallbacks.size() == 0) {
                                    animationHandler.mScheduler$ar$class_merging$ar$class_merging$ar$class_merging.postFrameCallback(animationHandler.mRunnable);
                                    if (Build.VERSION.SDK_INT >= 33) {
                                        durationScale = ValueAnimator.getDurationScale();
                                        animationHandler.mDurationScale = durationScale;
                                        if (animationHandler.mDurationScaleChangeListener$ar$class_merging == null) {
                                            animationHandler.mDurationScaleChangeListener$ar$class_merging = new AnimationHandler.DurationScaleChangeListener33(animationHandler);
                                        }
                                        final AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33 = animationHandler.mDurationScaleChangeListener$ar$class_merging;
                                        if (durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$mListener == null) {
                                            durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$mListener = new ValueAnimator$DurationScaleChangeListener() { // from class: androidx.dynamicanimation.animation.AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0
                                                public final void onChanged(float f3) {
                                                    ((AnimationHandler) AnimationHandler.DurationScaleChangeListener33.this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mDurationScale = f3;
                                                }
                                            };
                                            ValueAnimator.registerDurationScaleChangeListener(durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$mListener);
                                        }
                                    }
                                }
                                if (!animationHandler.mAnimationCallbacks.contains(this)) {
                                    animationHandler.mAnimationCallbacks.add(this);
                                    return;
                                }
                                return;
                            }
                            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
                        }
                        return;
                    }
                    throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
                }
                throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
            }
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public final boolean updateValueAndVelocity(long j) {
        float f;
        if (this.mPendingPosition != Float.MAX_VALUE) {
            long j2 = j / 2;
            DynamicAnimation.MassState updateValues = this.mSpring.updateValues(this.mValue, this.mVelocity, j2);
            this.mSpring.setFinalPosition$ar$ds(this.mPendingPosition);
            this.mPendingPosition = Float.MAX_VALUE;
            DynamicAnimation.MassState updateValues2 = this.mSpring.updateValues(updateValues.mValue, updateValues.mVelocity, j2);
            f = updateValues2.mValue;
            this.mValue = f;
            this.mVelocity = updateValues2.mVelocity;
        } else {
            DynamicAnimation.MassState updateValues3 = this.mSpring.updateValues(this.mValue, this.mVelocity, j);
            f = updateValues3.mValue;
            this.mValue = f;
            this.mVelocity = updateValues3.mVelocity;
        }
        float max = Math.max(f, this.mMinValue);
        this.mValue = max;
        this.mValue = Math.min(max, this.mMaxValue);
        float f2 = this.mVelocity;
        SpringForce springForce = this.mSpring;
        if (Math.abs(f2) < springForce.mVelocityThreshold && Math.abs(r1 - springForce.getFinalPosition()) < springForce.mValueThreshold) {
            this.mValue = this.mSpring.getFinalPosition();
            this.mVelocity = 0.0f;
            return true;
        }
        return false;
    }
}
