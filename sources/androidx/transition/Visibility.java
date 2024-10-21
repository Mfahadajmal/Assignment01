package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Visibility extends Transition {
    private static final String[] sTransitionProperties = {"android:visibility:visibility", "android:visibility:parent"};
    public int mMode = 3;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final View mView;
        boolean mCanceled = false;
        private final boolean mSuppressLayout = true;

        public DisappearListener(View view, int i) {
            this.mView = view;
            this.mFinalVisibility = i;
            this.mParent = (ViewGroup) view.getParent();
            suppressLayout(true);
        }

        private final void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            suppressLayout(false);
        }

        private final void suppressLayout(boolean z) {
            ViewGroup viewGroup;
            if (this.mSuppressLayout && this.mLayoutSuppressed != z && (viewGroup = this.mParent) != null) {
                this.mLayoutSuppressed = z;
                ViewGroupUtils.suppressLayout(viewGroup, z);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            hideViewWhenNotCanceled();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final /* synthetic */ void onTransitionEnd(Transition transition, boolean z) {
            onTransitionEnd(transition);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionPause$ar$ds() {
            suppressLayout(false);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionResume$ar$ds() {
            suppressLayout(true);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                return;
            }
            hideViewWhenNotCanceled();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
            transition.removeListener$ar$ds(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final /* synthetic */ void onTransitionStart(Transition transition, boolean z) {
            onTransitionStart(transition);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OverlayListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mHasOverlay = true;
        private final ViewGroup mOverlayHost;
        private final View mOverlayView;
        private final View mStartView;

        public OverlayListener(ViewGroup viewGroup, View view, View view2) {
            this.mOverlayHost = viewGroup;
            this.mOverlayView = view;
            this.mStartView = view2;
        }

        private final void removeFromOverlay() {
            this.mStartView.setTag(R.id.save_overlay_view, null);
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
            this.mHasOverlay = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            removeFromOverlay();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationPause(Animator animator) {
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationResume(Animator animator) {
            if (this.mOverlayView.getParent() == null) {
                ViewGroup viewGroup = this.mOverlayHost;
                viewGroup.getOverlay().add(this.mOverlayView);
                return;
            }
            Visibility.this.cancel();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                this.mStartView.setTag(R.id.save_overlay_view, this.mOverlayView);
                ViewGroup viewGroup = this.mOverlayHost;
                viewGroup.getOverlay().add(this.mOverlayView);
                this.mHasOverlay = true;
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
            if (this.mHasOverlay) {
                removeFromOverlay();
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final /* synthetic */ void onTransitionEnd(Transition transition, boolean z) {
            onTransitionEnd(transition);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                return;
            }
            removeFromOverlay();
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
            transition.removeListener$ar$ds(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final /* synthetic */ void onTransitionStart(Transition transition, boolean z) {
            onTransitionStart(transition);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionPause$ar$ds() {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionResume$ar$ds() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;
    }

    public static final void captureValues$ar$ds$4906f17c_0(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0074, code lost:
    
        if (r8 == 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007e, code lost:
    
        if (r0.mStartParent == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0090, code lost:
    
        if (r0.mStartVisibility == 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final androidx.transition.Visibility.VisibilityInfo getVisibilityChangeInfo$ar$ds(androidx.transition.TransitionValues r7, androidx.transition.TransitionValues r8) {
        /*
            androidx.transition.Visibility$VisibilityInfo r0 = new androidx.transition.Visibility$VisibilityInfo
            r0.<init>()
            r1 = 0
            r0.mVisibilityChange = r1
            r0.mFadeIn = r1
            java.lang.String r2 = "android:visibility:parent"
            r3 = 0
            r4 = -1
            java.lang.String r5 = "android:visibility:visibility"
            if (r7 == 0) goto L33
            java.util.Map r6 = r7.values
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L33
            java.util.Map r6 = r7.values
            java.lang.Object r6 = r6.get(r5)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r0.mStartVisibility = r6
            java.util.Map r6 = r7.values
            java.lang.Object r6 = r6.get(r2)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.mStartParent = r6
            goto L37
        L33:
            r0.mStartVisibility = r4
            r0.mStartParent = r3
        L37:
            if (r8 == 0) goto L5a
            java.util.Map r6 = r8.values
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L5a
            java.util.Map r3 = r8.values
            java.lang.Object r3 = r3.get(r5)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r0.mEndVisibility = r3
            java.util.Map r3 = r8.values
            java.lang.Object r2 = r3.get(r2)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.mEndParent = r2
            goto L5e
        L5a:
            r0.mEndVisibility = r4
            r0.mEndParent = r3
        L5e:
            r2 = 1
            if (r7 == 0) goto L81
            if (r8 == 0) goto L81
            int r7 = r0.mStartVisibility
            int r8 = r0.mEndVisibility
            if (r7 != r8) goto L6f
            android.view.ViewGroup r3 = r0.mStartParent
            android.view.ViewGroup r4 = r0.mEndParent
            if (r3 == r4) goto L95
        L6f:
            if (r7 == r8) goto L77
            if (r7 != 0) goto L74
            goto L92
        L74:
            if (r8 != 0) goto L95
            goto L87
        L77:
            android.view.ViewGroup r7 = r0.mEndParent
            if (r7 != 0) goto L7c
            goto L92
        L7c:
            android.view.ViewGroup r7 = r0.mStartParent
            if (r7 != 0) goto L95
            goto L87
        L81:
            if (r7 != 0) goto L8c
            int r7 = r0.mEndVisibility
            if (r7 != 0) goto L8c
        L87:
            r0.mFadeIn = r2
        L89:
            r0.mVisibilityChange = r2
            goto L95
        L8c:
            if (r8 != 0) goto L95
            int r7 = r0.mStartVisibility
            if (r7 != 0) goto L95
        L92:
            r0.mFadeIn = r1
            goto L89
        L95:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.getVisibilityChangeInfo$ar$ds(androidx.transition.TransitionValues, androidx.transition.TransitionValues):androidx.transition.Visibility$VisibilityInfo");
    }

    @Override // androidx.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        captureValues$ar$ds$4906f17c_0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01bb  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator createAnimator(android.view.ViewGroup r21, androidx.transition.TransitionValues r22, androidx.transition.TransitionValues r23) {
        /*
            Method dump skipped, instructions count: 647
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.createAnimator(android.view.ViewGroup, androidx.transition.TransitionValues, androidx.transition.TransitionValues):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.Transition
    public final boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null) {
            if (transitionValues2.values.containsKey("android:visibility:visibility") != transitionValues.values.containsKey("android:visibility:visibility")) {
                return false;
            }
        }
        VisibilityInfo visibilityChangeInfo$ar$ds = getVisibilityChangeInfo$ar$ds(transitionValues, transitionValues2);
        if (!visibilityChangeInfo$ar$ds.mVisibilityChange) {
            return false;
        }
        if (visibilityChangeInfo$ar$ds.mStartVisibility != 0 && visibilityChangeInfo$ar$ds.mEndVisibility != 0) {
            return false;
        }
        return true;
    }

    public Animator onAppear$ar$ds(View view, TransitionValues transitionValues) {
        throw null;
    }

    public Animator onDisappear$ar$ds(View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        throw null;
    }
}
