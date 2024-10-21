package androidx.dynamicanimation.animation;

import androidx.core.graphics.drawable.IconCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.transition.Transition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DynamicAnimation implements AnimationHandler.AnimationFrameCallback {
    final IconCompat.Api30Impl mProperty$ar$class_merging;
    public float mVelocity = 0.0f;
    public float mValue = Float.MAX_VALUE;
    public boolean mStartValueIsSet = false;
    public boolean mRunning = false;
    public float mMaxValue = Float.MAX_VALUE;
    public float mMinValue = -3.4028235E38f;
    private long mLastFrameTime = 0;
    public final ArrayList mEndListeners = new ArrayList();
    public final ArrayList mUpdateListeners = new ArrayList();
    public float mMinVisibleChange = 1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$15, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass15 extends IconCompat.Api30Impl {
        final /* synthetic */ FloatValueHolder val$floatValueHolder;

        public AnonymousClass15(FloatValueHolder floatValueHolder) {
            this.val$floatValueHolder = floatValueHolder;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MassState {
        float mValue;
        float mVelocity;
    }

    public DynamicAnimation(FloatValueHolder floatValueHolder) {
        this.mProperty$ar$class_merging = new AnonymousClass15(floatValueHolder);
    }

    private static void removeNullEntries(ArrayList arrayList) {
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                if (arrayList.get(size) == null) {
                    arrayList.remove(size);
                }
            } else {
                return;
            }
        }
    }

    @Override // androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback
    public final void doAnimationFrame$ar$ds(long j) {
        long j2;
        long j3 = this.mLastFrameTime;
        if (j3 == 0) {
            this.mLastFrameTime = j;
            setPropertyValue(this.mValue);
            return;
        }
        long j4 = j - j3;
        this.mLastFrameTime = j;
        float f = AnimationHandler.getInstance().mDurationScale;
        if (f == 0.0f) {
            j2 = 2147483647L;
        } else {
            j2 = ((float) j4) / f;
        }
        boolean updateValueAndVelocity = updateValueAndVelocity(j2);
        float min = Math.min(this.mValue, this.mMaxValue);
        this.mValue = min;
        float max = Math.max(min, this.mMinValue);
        this.mValue = max;
        setPropertyValue(max);
        if (updateValueAndVelocity) {
            this.mRunning = false;
            AnimationHandler animationHandler = AnimationHandler.getInstance();
            animationHandler.mDelayedCallbackStartTime.remove(this);
            int indexOf = animationHandler.mAnimationCallbacks.indexOf(this);
            if (indexOf >= 0) {
                animationHandler.mAnimationCallbacks.set(indexOf, null);
                animationHandler.mListDirty = true;
            }
            this.mLastFrameTime = 0L;
            this.mStartValueIsSet = false;
            for (int i = 0; i < this.mEndListeners.size(); i++) {
                if (this.mEndListeners.get(i) != null) {
                    ((FloatingActionButton.ShadowDelegateImpl) this.mEndListeners.get(i)).onAnimationEnd$ar$ds$486c95f9_0(this, this.mValue, this.mVelocity);
                }
            }
            removeNullEntries(this.mEndListeners);
        }
    }

    final void setPropertyValue(float f) {
        ((AnonymousClass15) this.mProperty$ar$class_merging).val$floatValueHolder.mValue = f;
        for (int i = 0; i < this.mUpdateListeners.size(); i++) {
            if (this.mUpdateListeners.get(i) != null) {
                ((Transition.SeekController) this.mUpdateListeners.get(i)).onAnimationUpdate$ar$ds(this.mValue);
            }
        }
        removeNullEntries(this.mUpdateListeners);
    }

    public abstract boolean updateValueAndVelocity(long j);
}
