package androidx.transition;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChangeBounds extends Transition {
    private static final String[] sTransitionProperties = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property TOP_LEFT_PROPERTY = new Property(PointF.class) { // from class: androidx.transition.ChangeBounds.1
        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            return null;
        }

        @Override // android.util.Property
        public final /* synthetic */ void set(Object obj, Object obj2) {
            ViewBounds viewBounds = (ViewBounds) obj;
            PointF pointF = (PointF) obj2;
            viewBounds.mLeft = Math.round(pointF.x);
            viewBounds.mTop = Math.round(pointF.y);
            int i = viewBounds.mTopLeftCalls + 1;
            viewBounds.mTopLeftCalls = i;
            if (i == viewBounds.mBottomRightCalls) {
                viewBounds.setLeftTopRightBottom();
            }
        }
    };
    private static final Property BOTTOM_RIGHT_PROPERTY = new Property(PointF.class) { // from class: androidx.transition.ChangeBounds.2
        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            return null;
        }

        @Override // android.util.Property
        public final /* synthetic */ void set(Object obj, Object obj2) {
            ViewBounds viewBounds = (ViewBounds) obj;
            PointF pointF = (PointF) obj2;
            viewBounds.mRight = Math.round(pointF.x);
            viewBounds.mBottom = Math.round(pointF.y);
            int i = viewBounds.mBottomRightCalls + 1;
            viewBounds.mBottomRightCalls = i;
            if (viewBounds.mTopLeftCalls == i) {
                viewBounds.setLeftTopRightBottom();
            }
        }
    };
    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new Property(PointF.class) { // from class: androidx.transition.ChangeBounds.3
        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            return null;
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            View view = (View) obj;
            PointF pointF = (PointF) obj2;
            ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    };
    private static final Property TOP_LEFT_ONLY_PROPERTY = new Property(PointF.class) { // from class: androidx.transition.ChangeBounds.4
        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            return null;
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            View view = (View) obj;
            PointF pointF = (PointF) obj2;
            ViewUtils.setLeftTopRightBottom(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    };
    private static final Property POSITION_PROPERTY = new Property(PointF.class) { // from class: androidx.transition.ChangeBounds.5
        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            return null;
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            View view = (View) obj;
            PointF pointF = (PointF) obj2;
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            ViewUtils.setLeftTopRightBottom(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SuppressLayoutListener extends TransitionListenerAdapter {
        boolean mCanceled = false;
        final ViewGroup mParent;

        public SuppressLayoutListener(ViewGroup viewGroup) {
            this.mParent = viewGroup;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
            this.mCanceled = true;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
            if (!this.mCanceled) {
                ViewGroupUtils.suppressLayout(this.mParent, false);
            }
            transition.removeListener$ar$ds(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionPause$ar$ds() {
            ViewGroupUtils.suppressLayout(this.mParent, false);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionResume$ar$ds() {
            ViewGroupUtils.suppressLayout(this.mParent, true);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ViewBounds {
        public int mBottom;
        public int mBottomRightCalls;
        public int mLeft;
        public int mRight;
        public int mTop;
        public int mTopLeftCalls;
        private final View mView;

        public ViewBounds(View view) {
            this.mView = view;
        }

        public final void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }
    }

    private static final void captureValues$ar$ds(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (!view.isLaidOut() && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
    }

    @Override // androidx.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        captureValues$ar$ds(transitionValues);
    }

    @Override // androidx.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        captureValues$ar$ds(transitionValues);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0125 A[RETURN] */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator createAnimator(android.view.ViewGroup r20, androidx.transition.TransitionValues r21, androidx.transition.TransitionValues r22) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeBounds.createAnimator(android.view.ViewGroup, androidx.transition.TransitionValues, androidx.transition.TransitionValues):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.Transition
    public final boolean isSeekingSupported() {
        return true;
    }
}
