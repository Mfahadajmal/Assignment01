package android.support.v4.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;

/* compiled from: PG */
/* loaded from: classes.dex */
final class FragmentAnim$EndViewTransitionAnimation extends AnimationSet implements Runnable {
    private boolean mAnimating;
    private final View mChild;
    private boolean mEnded;
    private final ViewGroup mParent;
    private boolean mTransitionEnded;

    public FragmentAnim$EndViewTransitionAnimation(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.mAnimating = true;
        this.mParent = viewGroup;
        this.mChild = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation) {
        this.mAnimating = true;
        if (this.mEnded) {
            return !this.mTransitionEnded;
        }
        if (!super.getTransformation(j, transformation)) {
            this.mEnded = true;
            OneShotPreDrawListener.add$ar$ds$ef887652_0(this.mParent, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.mEnded && this.mAnimating) {
            this.mAnimating = false;
            this.mParent.post(this);
        } else {
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
        }
    }

    @Override // android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation, float f) {
        this.mAnimating = true;
        if (this.mEnded) {
            return !this.mTransitionEnded;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.mEnded = true;
            OneShotPreDrawListener.add$ar$ds$ef887652_0(this.mParent, this);
        }
        return true;
    }
}
