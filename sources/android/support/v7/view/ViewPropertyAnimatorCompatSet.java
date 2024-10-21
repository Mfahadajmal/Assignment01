package android.support.v7.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewPropertyAnimatorCompatSet {
    private Interpolator mInterpolator;
    public boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private long mDuration = -1;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.view.ViewPropertyAnimatorCompatSet.1
        private boolean mProxyStarted = false;
        private int mProxyEndCount = 0;

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationEnd$ar$ds() {
            int i = this.mProxyEndCount + 1;
            this.mProxyEndCount = i;
            if (i == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.mListener;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationEnd$ar$ds();
                }
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                ViewPropertyAnimatorCompatSet.this.mIsStarted = false;
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationStart$ar$ds() {
            if (!this.mProxyStarted) {
                this.mProxyStarted = true;
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.mListener;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationStart$ar$ds();
                }
            }
        }
    };
    public final ArrayList mAnimators = new ArrayList();

    public final void cancel() {
        if (!this.mIsStarted) {
            return;
        }
        ArrayList arrayList = this.mAnimators;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((AccessibilityNodeInfoCompat.CollectionItemInfoCompat) arrayList.get(i)).cancel();
        }
        this.mIsStarted = false;
    }

    public final void play$ar$ds$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat) {
        if (!this.mIsStarted) {
            this.mAnimators.add(collectionItemInfoCompat);
        }
    }

    public final void setDuration$ar$ds() {
        if (!this.mIsStarted) {
            this.mDuration = 250L;
        }
    }

    public final void setInterpolator$ar$ds(Interpolator interpolator) {
        if (!this.mIsStarted) {
            this.mInterpolator = interpolator;
        }
    }

    public final void setListener$ar$ds(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.mIsStarted) {
            this.mListener = viewPropertyAnimatorListener;
        }
    }

    public final void start() {
        View view;
        if (this.mIsStarted) {
            return;
        }
        ArrayList arrayList = this.mAnimators;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = (AccessibilityNodeInfoCompat.CollectionItemInfoCompat) arrayList.get(i);
            long j = this.mDuration;
            if (j >= 0) {
                collectionItemInfoCompat.setDuration$ar$ds$d0f32809_0(j);
            }
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null && (view = (View) ((WeakReference) collectionItemInfoCompat.mInfo).get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.mListener != null) {
                collectionItemInfoCompat.setListener$ar$ds$34caea9b_0(this.mProxyListener);
            }
            View view2 = (View) ((WeakReference) collectionItemInfoCompat.mInfo).get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.mIsStarted = true;
    }
}
