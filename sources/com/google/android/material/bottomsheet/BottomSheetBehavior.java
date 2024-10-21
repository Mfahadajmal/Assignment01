package com.google.android.material.bottomsheet;

import _COROUTINE._BOUNDARY;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.marvin.talkback.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.stateful.ExtendableSavedState;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    int activePointerId;
    private ColorStateList backgroundTint;
    private final ArrayList callbacks;
    private int childHeight;
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback;
    public boolean draggable;
    private boolean draggableOnNestedScroll;
    private boolean draggableOnNestedScrollLastDragIgnored;
    float elevation;
    final SparseIntArray expandHalfwayActionIds;
    private boolean expandedCornersRemoved;
    int expandedOffset;
    public boolean fitToContents;
    int fitToContentsOffset;
    public int gestureInsetBottom;
    private boolean gestureInsetBottomIgnored;
    int halfExpandedOffset;
    float halfExpandedRatio;
    private final float hideFriction;
    boolean hideable;
    private boolean ignoreEvents;
    private Map importantForAccessibilityMap;
    private int initialY;
    public int insetBottom;
    public int insetTop;
    private ValueAnimator interpolatorAnimator;
    private int lastNestedScrollDy;
    public boolean marginLeftSystemWindowInsets;
    public boolean marginRightSystemWindowInsets;
    public boolean marginTopSystemWindowInsets;
    public MaterialShapeDrawable materialShapeDrawable;
    private int maxHeight;
    private int maxWidth;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference nestedScrollingChildRef;
    public boolean paddingBottomSystemWindowInsets;
    public boolean paddingLeftSystemWindowInsets;
    public boolean paddingRightSystemWindowInsets;
    private boolean paddingTopSystemWindowInsets;
    int parentHeight;
    int parentWidth;
    public int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightGestureInsetBuffer;
    private int peekHeightMin;
    private int saveFlags;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private boolean shouldRemoveExpandedCorners;
    public int significantVelocityThreshold;
    public boolean skipCollapsed;
    public int state;
    private final StateSettlingTracker stateSettlingTracker;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    WeakReference viewRef;

    /* compiled from: PG */
    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ Object BottomSheetBehavior$3$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass3(Object obj, int i) {
            this.switching_field = i;
            this.BottomSheetBehavior$3$ar$this$0 = obj;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    ((TextInputLayout) this.BottomSheetBehavior$3$ar$this$0).collapsingTextHelper.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
                                    return;
                                } else {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    Handler handler = BaseTransientBottomBar.handler;
                                    ((BaseTransientBottomBar) this.BottomSheetBehavior$3$ar$this$0).view.setTranslationY(intValue);
                                    return;
                                }
                            }
                            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            ((BaseTransientBottomBar) this.BottomSheetBehavior$3$ar$this$0).view.setScaleX(floatValue);
                            ((BaseTransientBottomBar) this.BottomSheetBehavior$3$ar$this$0).view.setScaleY(floatValue);
                            return;
                        }
                        ((BaseTransientBottomBar) this.BottomSheetBehavior$3$ar$this$0).view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        return;
                    }
                    float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ((TextView) this.BottomSheetBehavior$3$ar$this$0).setScaleX(floatValue2);
                    ((TextView) this.BottomSheetBehavior$3$ar$this$0).setScaleY(floatValue2);
                    return;
                }
                ((CollapsingToolbarLayout) this.BottomSheetBehavior$3$ar$this$0).setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                return;
            }
            float floatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            MaterialShapeDrawable materialShapeDrawable = ((BottomSheetBehavior) this.BottomSheetBehavior$3$ar$this$0).materialShapeDrawable;
            if (materialShapeDrawable != null) {
                materialShapeDrawable.setInterpolation(floatValue3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$6, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass6 implements AccessibilityViewCommand {
        final /* synthetic */ BottomSheetBehavior this$0;
        final /* synthetic */ int val$state;

        public AnonymousClass6(BottomSheetBehavior bottomSheetBehavior, int i) {
            this.val$state = i;
            this.this$0 = bottomSheetBehavior;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
            this.this$0.setState(this.val$state);
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class BottomSheetCallback {
        public abstract void onSlide$ar$ds();

        public abstract void onStateChanged$ar$ds$e8571560_0();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StateSettlingTracker {
        private final Runnable continueSettlingRunnable = new FlagStore$$ExternalSyntheticLambda3(this, 10, null);
        public boolean isContinueSettlingRunnablePosted;
        public int targetState;

        public StateSettlingTracker() {
        }

        public final void continueSettlingToState(int i) {
            WeakReference weakReference = BottomSheetBehavior.this.viewRef;
            if (weakReference != null && weakReference.get() != null) {
                this.targetState = i;
                if (!this.isContinueSettlingRunnablePosted) {
                    ((View) BottomSheetBehavior.this.viewRef.get()).postOnAnimation(this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker();
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.draggableOnNestedScroll = true;
        this.state = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical$ar$ds(View view, int i) {
                return NotificationCompatBuilder$Api26Impl.clamp(i, BottomSheetBehavior.this.getExpandedOffset(), getViewVerticalDragRange$ar$ds());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewVerticalDragRange$ar$ds() {
                if (BottomSheetBehavior.this.canBeHiddenByDragging()) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.draggable) {
                        bottomSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
            
                if (r7 > r5.this$0.halfExpandedOffset) goto L51;
             */
            /* JADX WARN: Code restructure failed: missing block: B:25:0x0078, code lost:
            
                if (java.lang.Math.abs(r6.getTop() - r5.this$0.getExpandedOffset()) < java.lang.Math.abs(r6.getTop() - r5.this$0.halfExpandedOffset)) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x00ab, code lost:
            
                if (java.lang.Math.abs(r7 - r5.this$0.halfExpandedOffset) < java.lang.Math.abs(r7 - r5.this$0.collapsedOffset)) goto L51;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00c9, code lost:
            
                if (java.lang.Math.abs(r7 - r8.fitToContentsOffset) < java.lang.Math.abs(r7 - r5.this$0.collapsedOffset)) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x00db, code lost:
            
                if (r7 < java.lang.Math.abs(r7 - r8.collapsedOffset)) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00ee, code lost:
            
                if (java.lang.Math.abs(r7 - r0) < java.lang.Math.abs(r7 - r5.this$0.collapsedOffset)) goto L51;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onViewReleased(android.view.View r6, float r7, float r8) {
                /*
                    Method dump skipped, instructions count: 247
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                View view2;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i2 = bottomSheetBehavior.state;
                if (i2 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i2 == 3 && bottomSheetBehavior.activePointerId == i) {
                    WeakReference weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    if (weakReference != null) {
                        view2 = (View) weakReference.get();
                    } else {
                        view2 = null;
                    }
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                System.currentTimeMillis();
                WeakReference weakReference2 = BottomSheetBehavior.this.viewRef;
                if (weakReference2 == null || weakReference2.get() != view) {
                    return false;
                }
                return true;
            }
        };
    }

    private final void calculateCollapsedOffset() {
        int calculatePeekHeight = calculatePeekHeight();
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - calculatePeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - calculatePeekHeight;
        }
    }

    private static final float calculateCornerInterpolation$ar$ds(float f, RoundedCorner roundedCorner) {
        int radius;
        if (roundedCorner != null) {
            radius = roundedCorner.getRadius();
            float f2 = radius;
            if (f2 > 0.0f && f > 0.0f) {
                return f2 / f;
            }
        }
        return 0.0f;
    }

    private final void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int) (this.parentHeight * (1.0f - this.halfExpandedRatio));
    }

    private final float calculateInterpolationWithCornersRemoved() {
        WeakReference weakReference;
        WindowInsets rootWindowInsets;
        RoundedCorner roundedCorner;
        RoundedCorner roundedCorner2;
        if (this.materialShapeDrawable != null && (weakReference = this.viewRef) != null && weakReference.get() != null && Build.VERSION.SDK_INT >= 31) {
            View view = (View) this.viewRef.get();
            if (isAtTopOfScreen() && (rootWindowInsets = view.getRootWindowInsets()) != null) {
                float topLeftCornerResolvedSize = this.materialShapeDrawable.getTopLeftCornerResolvedSize();
                roundedCorner = rootWindowInsets.getRoundedCorner(0);
                float calculateCornerInterpolation$ar$ds = calculateCornerInterpolation$ar$ds(topLeftCornerResolvedSize, roundedCorner);
                float topRightCornerResolvedSize = this.materialShapeDrawable.getTopRightCornerResolvedSize();
                roundedCorner2 = rootWindowInsets.getRoundedCorner(1);
                return Math.max(calculateCornerInterpolation$ar$ds, calculateCornerInterpolation$ar$ds(topRightCornerResolvedSize, roundedCorner2));
            }
            return 0.0f;
        }
        return 0.0f;
    }

    private final int calculatePeekHeight() {
        int i;
        if (this.peekHeightAuto) {
            return Math.min(Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16)), this.childHeight) + this.insetBottom;
        }
        if (!this.gestureInsetBottomIgnored && !this.paddingBottomSystemWindowInsets && (i = this.gestureInsetBottom) > 0) {
            return Math.max(this.peekHeight, i + this.peekHeightGestureInsetBuffer);
        }
        return this.peekHeight + this.insetBottom;
    }

    private static final int getChildMeasureSpec$ar$ds(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode != 1073741824) {
            if (size != 0) {
                i3 = Math.min(size, i3);
            }
            return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
    }

    private final int getTopOffsetForState(int i) {
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    return this.halfExpandedOffset;
                }
                return this.parentHeight;
            }
            return this.collapsedOffset;
        }
        return getExpandedOffset();
    }

    private final boolean isAtTopOfScreen() {
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && weakReference.get() != null) {
            int[] iArr = new int[2];
            ((View) this.viewRef.get()).getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                return true;
            }
        }
        return false;
    }

    private final void replaceAccessibilityActionForState(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i) {
        ViewCompat.addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, accessibilityActionCompat.mId, null, new AnonymousClass6(this, i), accessibilityActionCompat.mViewCommandArgumentClass));
    }

    private final void reset() {
        this.activePointerId = -1;
        this.initialY = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private final boolean shouldHandleDraggingWithHelper() {
        if (this.viewDragHelper == null) {
            return false;
        }
        if (!this.draggable && this.state != 1) {
            return false;
        }
        return true;
    }

    private final void updateAccessibilityActions() {
        View view;
        int i;
        boolean z;
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 524288);
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, 1048576);
            int i2 = this.expandHalfwayActionIds.get(0, -1);
            if (i2 != -1) {
                ViewCompat.removeAccessibilityAction(view, i2);
                this.expandHalfwayActionIds.delete(0);
            }
            int i3 = 6;
            if (!this.fitToContents && this.state != 6) {
                SparseIntArray sparseIntArray = this.expandHalfwayActionIds;
                String string = view.getResources().getString(R.string.bottomsheet_action_expand_halfway);
                AnonymousClass6 anonymousClass6 = new AnonymousClass6(this, 6);
                List actionList = ViewCompat.getActionList(view);
                int i4 = 0;
                while (true) {
                    if (i4 < actionList.size()) {
                        if (TextUtils.equals(string, ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i4)).getLabel())) {
                            i = ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i4)).getId();
                            break;
                        }
                        i4++;
                    } else {
                        int i5 = -1;
                        for (int i6 = 0; i6 < 32 && i5 == -1; i6++) {
                            i5 = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS[i6];
                            boolean z2 = true;
                            for (int i7 = 0; i7 < actionList.size(); i7++) {
                                if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i7)).getId() != i5) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                z2 &= z;
                            }
                            if (true != z2) {
                                i5 = -1;
                            }
                        }
                        i = i5;
                    }
                }
                if (i != -1) {
                    ViewCompat.addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, i, string, anonymousClass6, null));
                }
                sparseIntArray.put(0, i);
            }
            if (this.hideable && this.state != 5) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            int i8 = this.state;
            if (i8 != 3) {
                if (i8 != 4) {
                    if (i8 == 6) {
                        replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                        replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
                        return;
                    }
                    return;
                }
                if (true == this.fitToContents) {
                    i3 = 3;
                }
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, i3);
                return;
            }
            if (true == this.fitToContents) {
                i3 = 4;
            }
            replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, i3);
        }
    }

    private final void updateDrawableForTargetState(int i, boolean z) {
        boolean z2;
        ValueAnimator valueAnimator;
        if (i != 2) {
            if (this.state == 3 && (this.shouldRemoveExpandedCorners || isAtTopOfScreen())) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.expandedCornersRemoved != z2 && this.materialShapeDrawable != null) {
                this.expandedCornersRemoved = z2;
                float f = 1.0f;
                if (z && (valueAnimator = this.interpolatorAnimator) != null) {
                    if (valueAnimator.isRunning()) {
                        this.interpolatorAnimator.reverse();
                        return;
                    }
                    float f2 = this.materialShapeDrawable.drawableState.interpolation;
                    if (z2) {
                        f = calculateInterpolationWithCornersRemoved();
                    }
                    this.interpolatorAnimator.setFloatValues(f2, f);
                    this.interpolatorAnimator.start();
                    return;
                }
                ValueAnimator valueAnimator2 = this.interpolatorAnimator;
                if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                    this.interpolatorAnimator.cancel();
                }
                MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
                if (this.expandedCornersRemoved) {
                    f = calculateInterpolationWithCornersRemoved();
                }
                materialShapeDrawable.setInterpolation(f);
            }
        }
    }

    private final void updateImportantForAccessibility(boolean z) {
        WeakReference weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (childAt != this.viewRef.get() && z) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }

    public final boolean canBeHiddenByDragging() {
        if (this.hideable) {
            return true;
        }
        return false;
    }

    final void dispatchOnSlide(int i) {
        if (((View) this.viewRef.get()) != null && !this.callbacks.isEmpty()) {
            int i2 = this.collapsedOffset;
            if (i <= i2 && i2 != getExpandedOffset()) {
                getExpandedOffset();
            }
            for (int i3 = 0; i3 < this.callbacks.size(); i3++) {
                ((BottomSheetCallback) this.callbacks.get(i3)).onSlide$ar$ds();
            }
        }
    }

    final View findScrollingChild(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        if (ViewCompat.Api21Impl.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    public final int getExpandedOffset() {
        int i;
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        int i2 = this.expandedOffset;
        if (this.paddingTopSystemWindowInsets) {
            i = 0;
        } else {
            i = this.insetTop;
        }
        return Math.max(i2, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onDetachedFromLayoutParams() {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z;
        View view2;
        int i;
        ViewDragHelper viewDragHelper;
        if (view.isShown() && this.draggable) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                reset();
                actionMasked = 0;
            }
            if (this.velocityTracker == null) {
                this.velocityTracker = VelocityTracker.obtain();
            }
            this.velocityTracker.addMovement(motionEvent);
            View view3 = null;
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    this.touchingScrollingChild = false;
                    this.activePointerId = -1;
                    if (this.ignoreEvents) {
                        this.ignoreEvents = false;
                        return false;
                    }
                }
            } else {
                int x = (int) motionEvent.getX();
                this.initialY = (int) motionEvent.getY();
                if (this.state != 2) {
                    WeakReference weakReference = this.nestedScrollingChildRef;
                    if (weakReference != null) {
                        view2 = (View) weakReference.get();
                    } else {
                        view2 = null;
                    }
                    if (view2 != null && coordinatorLayout.isPointInChildBounds(view2, x, this.initialY)) {
                        this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.touchingScrollingChild = true;
                    }
                }
                if (this.activePointerId == -1 && !coordinatorLayout.isPointInChildBounds(view, x, this.initialY)) {
                    z = true;
                } else {
                    z = false;
                }
                this.ignoreEvents = z;
            }
            if (!this.ignoreEvents && (viewDragHelper = this.viewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                return true;
            }
            WeakReference weakReference2 = this.nestedScrollingChildRef;
            if (weakReference2 != null) {
                view3 = (View) weakReference2.get();
            }
            if (actionMasked == 2 && view3 != null && !this.ignoreEvents && this.state != 1 && !coordinatorLayout.isPointInChildBounds(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) && this.viewDragHelper != null && (i = this.initialY) != -1) {
                if (Math.abs(i - motionEvent.getY()) > this.viewDragHelper.mTouchSlop) {
                    return true;
                }
            }
            return false;
        }
        this.ignoreEvents = true;
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x006a  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onLayoutChild(androidx.coordinatorlayout.widget.CoordinatorLayout r6, android.view.View r7, int r8) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onLayoutChild(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int):boolean");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec$ar$ds(i, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.maxWidth, marginLayoutParams.width), getChildMeasureSpec$ar$ds(i3, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.maxHeight, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onNestedPreFling$ar$ds(View view) {
        WeakReference weakReference = this.nestedScrollingChildRef;
        if (weakReference == null || view != weakReference.get() || this.state == 3 || this.draggableOnNestedScrollLastDragIgnored) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0064, code lost:
    
        if (r1 == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006a, code lost:
    
        if (r1 == false) goto L36;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onNestedPreScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r4, android.view.View r5, android.view.View r6, int r7, int r8, int[] r9, int r10) {
        /*
            r3 = this;
            r4 = 1
            if (r10 != r4) goto L5
            goto La4
        L5:
            java.lang.ref.WeakReference r7 = r3.nestedScrollingChildRef
            if (r7 == 0) goto L10
            java.lang.Object r7 = r7.get()
            android.view.View r7 = (android.view.View) r7
            goto L11
        L10:
            r7 = 0
        L11:
            if (r6 != r7) goto La4
            int r10 = r5.getTop()
            int r0 = r10 - r8
            if (r8 <= 0) goto L53
            boolean r1 = r3.nestedScrolled
            if (r1 != 0) goto L2b
            boolean r1 = r3.draggableOnNestedScroll
            if (r1 != 0) goto L2b
            if (r6 != r7) goto L2b
            boolean r6 = r6.canScrollVertically(r4)
            if (r6 != 0) goto L67
        L2b:
            int r6 = r3.getExpandedOffset()
            if (r0 >= r6) goto L43
            int r6 = r3.getExpandedOffset()
            int r10 = r10 - r6
            r9[r4] = r10
            int[] r6 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            int r6 = -r10
            r5.offsetTopAndBottom(r6)
            r6 = 3
            r3.setStateInternal(r6)
            goto L96
        L43:
            boolean r6 = r3.draggable
            if (r6 == 0) goto La4
            r9[r4] = r8
            int r6 = -r8
            int[] r7 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            r5.offsetTopAndBottom(r6)
            r3.setStateInternal(r4)
            goto L96
        L53:
            if (r8 >= 0) goto L96
            r1 = -1
            boolean r1 = r6.canScrollVertically(r1)
            boolean r2 = r3.nestedScrolled
            if (r2 != 0) goto L6a
            boolean r2 = r3.draggableOnNestedScroll
            if (r2 != 0) goto L6a
            if (r6 != r7) goto L6a
            if (r1 != 0) goto L67
            goto L6c
        L67:
            r3.draggableOnNestedScrollLastDragIgnored = r4
            return
        L6a:
            if (r1 != 0) goto L96
        L6c:
            int r6 = r3.collapsedOffset
            if (r0 <= r6) goto L87
            boolean r6 = r3.canBeHiddenByDragging()
            if (r6 == 0) goto L77
            goto L87
        L77:
            int r6 = r3.collapsedOffset
            int r10 = r10 - r6
            r9[r4] = r10
            int[] r6 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            int r6 = -r10
            r5.offsetTopAndBottom(r6)
            r6 = 4
            r3.setStateInternal(r6)
            goto L96
        L87:
            boolean r6 = r3.draggable
            if (r6 == 0) goto La4
            r9[r4] = r8
            int r6 = -r8
            int[] r7 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            r5.offsetTopAndBottom(r6)
            r3.setStateInternal(r4)
        L96:
            int r5 = r5.getTop()
            r3.dispatchOnSlide(r5)
            r3.lastNestedScrollDy = r8
            r3.nestedScrolled = r4
            r4 = 0
            r3.draggableOnNestedScrollLastDragIgnored = r4
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onNestedPreScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int, int, int[], int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.saveFlags;
        int i2 = 4;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.peekHeight = savedState.peekHeight;
            }
            if (i == -1 || (i & 2) == 2) {
                this.fitToContents = savedState.fitToContents;
            }
            if (i == -1 || (i & 4) == 4) {
                this.hideable = savedState.hideable;
            }
            if (i == -1 || (i & 8) == 8) {
                this.skipCollapsed = savedState.skipCollapsed;
            }
        }
        int i3 = savedState.state;
        if (i3 != 1 && i3 != 2) {
            i2 = i3;
        }
        this.state = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((i & 2) == 0) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        if (r4.getTop() <= r2.halfExpandedOffset) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0072, code lost:
    
        if (java.lang.Math.abs(r3 - r2.fitToContentsOffset) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0081, code lost:
    
        if (r3 < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0091, code lost:
    
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ad, code lost:
    
        if (java.lang.Math.abs(r3 - r2.halfExpandedOffset) < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L20;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.setStateInternal(r0)
            return
        Lf:
            java.lang.ref.WeakReference r3 = r2.nestedScrollingChildRef
            if (r3 == 0) goto Lb6
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb6
            boolean r3 = r2.nestedScrolled
            if (r3 != 0) goto L1f
            goto Lb6
        L1f:
            int r3 = r2.lastNestedScrollDy
            r5 = 6
            if (r3 <= 0) goto L35
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L2a
            goto Lb0
        L2a:
            int r3 = r4.getTop()
            int r6 = r2.halfExpandedOffset
            if (r3 <= r6) goto Lb0
        L32:
            r0 = r5
            goto Lb0
        L35:
            boolean r3 = r2.hideable
            if (r3 == 0) goto L56
            android.view.VelocityTracker r3 = r2.velocityTracker
            if (r3 != 0) goto L3f
            r3 = 0
            goto L4e
        L3f:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.maximumVelocity
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.velocityTracker
            int r6 = r2.activePointerId
            float r3 = r3.getYVelocity(r6)
        L4e:
            boolean r3 = r2.shouldHide(r4, r3)
            if (r3 == 0) goto L56
            r0 = 5
            goto Lb0
        L56:
            int r3 = r2.lastNestedScrollDy
            r6 = 4
            if (r3 != 0) goto L94
            int r3 = r4.getTop()
            boolean r1 = r2.fitToContents
            if (r1 == 0) goto L75
            int r5 = r2.fitToContentsOffset
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L98
            goto Lb0
        L75:
            int r1 = r2.halfExpandedOffset
            if (r3 >= r1) goto L84
            int r6 = r2.collapsedOffset
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto L32
            goto Lb0
        L84:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L98
            goto L32
        L94:
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L9a
        L98:
            r0 = r6
            goto Lb0
        L9a:
            int r3 = r4.getTop()
            int r0 = r2.halfExpandedOffset
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L98
            goto L32
        Lb0:
            r3 = 0
            r2.startSettling(r4, r0, r3)
            r2.nestedScrolled = r3
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents) {
            float abs = Math.abs(this.initialY - motionEvent.getY());
            ViewDragHelper viewDragHelper = this.viewDragHelper;
            if (abs > viewDragHelper.mTouchSlop) {
                viewDragHelper.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        if (this.ignoreEvents) {
            return false;
        }
        return true;
    }

    public final void setExpandedOffset(int i) {
        if (i >= 0) {
            this.expandedOffset = i;
            updateDrawableForTargetState(this.state, true);
            return;
        }
        throw new IllegalArgumentException("offset must be greater than or equal to 0");
    }

    public final void setPeekHeight(int i) {
        if (i == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
            } else {
                return;
            }
        } else {
            if (!this.peekHeightAuto && this.peekHeight == i) {
                return;
            }
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i);
        }
        updatePeekHeight$ar$ds();
    }

    public final void setState(int i) {
        int i2;
        if (!this.hideable && i == 5) {
            Log.w("BottomSheetBehavior", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(5, "Cannot set state: "));
            return;
        }
        if (i == 6 && this.fitToContents && getTopOffsetForState(6) <= this.fitToContentsOffset) {
            i2 = 3;
        } else {
            i2 = i;
        }
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && weakReference.get() != null) {
            View view = (View) this.viewRef.get();
            ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0 componentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0 = new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0((BottomSheetBehavior) this, view, i2, 7);
            ViewParent parent = view.getParent();
            if (parent != null && parent.isLayoutRequested() && view.isAttachedToWindow()) {
                view.post(componentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0);
                return;
            } else {
                componentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0.run();
                return;
            }
        }
        setStateInternal(i);
    }

    public final void setStateInternal(int i) {
        if (this.state != i) {
            this.state = i;
            if (i != 4 && i != 3 && i != 6 && this.hideable && i == 5) {
                i = 5;
            }
            WeakReference weakReference = this.viewRef;
            if (weakReference != null && ((View) weakReference.get()) != null) {
                if (i == 3) {
                    updateImportantForAccessibility(true);
                } else if (i == 6 || i == 5 || i == 4) {
                    updateImportantForAccessibility(false);
                }
                updateDrawableForTargetState(i, true);
                for (int i2 = 0; i2 < this.callbacks.size(); i2++) {
                    ((BottomSheetCallback) this.callbacks.get(i2)).onStateChanged$ar$ds$e8571560_0();
                }
                updateAccessibilityActions();
            }
        }
    }

    final boolean shouldHide(View view, float f) {
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.collapsedOffset) {
            return false;
        }
        if (Math.abs((view.getTop() + (f * this.hideFriction)) - this.collapsedOffset) / calculatePeekHeight() > 0.5f) {
            return true;
        }
        return false;
    }

    public final void startSettling(View view, int i, boolean z) {
        int topOffsetForState = getTopOffsetForState(i);
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null && (!z ? viewDragHelper.smoothSlideViewTo(view, view.getLeft(), topOffsetForState) : viewDragHelper.settleCapturedViewAt(view.getLeft(), topOffsetForState))) {
            setStateInternal(2);
            updateDrawableForTargetState(i, true);
            this.stateSettlingTracker.continueSettlingToState(i);
            return;
        }
        setStateInternal(i);
    }

    public final void updatePeekHeight$ar$ds() {
        View view;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
            if (this.state == 4 && (view = (View) this.viewRef.get()) != null) {
                view.requestLayout();
            }
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.saveFlags = 0;
        this.fitToContents = true;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker();
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.draggableOnNestedScroll = true;
        this.state = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical$ar$ds(View view, int i) {
                return NotificationCompatBuilder$Api26Impl.clamp(i, BottomSheetBehavior.this.getExpandedOffset(), getViewVerticalDragRange$ar$ds());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewVerticalDragRange$ar$ds() {
                if (BottomSheetBehavior.this.canBeHiddenByDragging()) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.draggable) {
                        bottomSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewReleased(View view, float f, float f2) {
                /*  JADX ERROR: Method code generation error
                    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
                    	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    */
                /*
                    Method dump skipped, instructions count: 247
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                View view2;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i2 = bottomSheetBehavior.state;
                if (i2 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i2 == 3 && bottomSheetBehavior.activePointerId == i) {
                    WeakReference weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    if (weakReference != null) {
                        view2 = (View) weakReference.get();
                    } else {
                        view2 = null;
                    }
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                System.currentTimeMillis();
                WeakReference weakReference2 = BottomSheetBehavior.this.viewRef;
                if (weakReference2 == null || weakReference2.get() != view) {
                    return false;
                }
                return true;
            }
        };
        this.peekHeightGestureInsetBuffer = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(3)) {
            this.backgroundTint = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(22)) {
            this.shapeAppearanceModelDefault = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, R.style.Widget_Design_BottomSheet_Modal));
        }
        if (this.shapeAppearanceModelDefault != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
            this.materialShapeDrawable = materialShapeDrawable;
            materialShapeDrawable.initializeElevationOverlay(context);
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                this.materialShapeDrawable.setFillColor(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
                this.materialShapeDrawable.setTint(typedValue.data);
            }
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(calculateInterpolationWithCornersRemoved(), 1.0f);
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new AnonymousClass3(this, 0));
        this.elevation = obtainStyledAttributes.getDimension(2, -1.0f);
        if (obtainStyledAttributes.hasValue(0)) {
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(10);
        if (peekValue != null && peekValue.data == -1) {
            setPeekHeight(peekValue.data);
        } else {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(10, -1));
        }
        boolean z = obtainStyledAttributes.getBoolean(9, false);
        if (this.hideable != z) {
            this.hideable = z;
            if (!z && this.state == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
        this.gestureInsetBottomIgnored = obtainStyledAttributes.getBoolean(14, false);
        boolean z2 = obtainStyledAttributes.getBoolean(7, true);
        if (this.fitToContents != z2) {
            this.fitToContents = z2;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((this.fitToContents && this.state == 6) ? 3 : this.state);
            updateDrawableForTargetState(this.state, true);
            updateAccessibilityActions();
        }
        this.skipCollapsed = obtainStyledAttributes.getBoolean(13, false);
        this.draggable = obtainStyledAttributes.getBoolean(4, true);
        this.draggableOnNestedScroll = obtainStyledAttributes.getBoolean(5, true);
        this.saveFlags = obtainStyledAttributes.getInt(11, 0);
        float f = obtainStyledAttributes.getFloat(8, 0.5f);
        if (f > 0.0f && f < 1.0f) {
            this.halfExpandedRatio = f;
            if (this.viewRef != null) {
                calculateHalfExpandedOffset();
            }
            TypedValue peekValue2 = obtainStyledAttributes.peekValue(6);
            if (peekValue2 != null && peekValue2.type == 16) {
                setExpandedOffset(peekValue2.data);
            } else {
                setExpandedOffset(obtainStyledAttributes.getDimensionPixelOffset(6, 0));
            }
            this.significantVelocityThreshold = obtainStyledAttributes.getInt(12, 500);
            this.paddingBottomSystemWindowInsets = obtainStyledAttributes.getBoolean(18, false);
            this.paddingLeftSystemWindowInsets = obtainStyledAttributes.getBoolean(19, false);
            this.paddingRightSystemWindowInsets = obtainStyledAttributes.getBoolean(20, false);
            this.paddingTopSystemWindowInsets = obtainStyledAttributes.getBoolean(21, true);
            this.marginLeftSystemWindowInsets = obtainStyledAttributes.getBoolean(15, false);
            this.marginRightSystemWindowInsets = obtainStyledAttributes.getBoolean(16, false);
            this.marginTopSystemWindowInsets = obtainStyledAttributes.getBoolean(17, false);
            this.shouldRemoveExpandedCorners = obtainStyledAttributes.getBoolean(24, true);
            obtainStyledAttributes.recycle();
            this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1(0);
        final boolean fitToContents;
        final boolean hideable;
        final int peekHeight;
        final boolean skipCollapsed;
        final int state;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
            this.peekHeight = parcel.readInt();
            this.fitToContents = parcel.readInt() == 1;
            this.hideable = parcel.readInt() == 1;
            this.skipCollapsed = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
            parcel.writeInt(this.peekHeight);
            parcel.writeInt(this.fitToContents ? 1 : 0);
            parcel.writeInt(this.hideable ? 1 : 0);
            parcel.writeInt(this.skipCollapsed ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
            super(parcelable);
            this.state = bottomSheetBehavior.state;
            this.peekHeight = bottomSheetBehavior.peekHeight;
            this.fitToContents = bottomSheetBehavior.fitToContents;
            this.hideable = bottomSheetBehavior.hideable;
            this.skipCollapsed = bottomSheetBehavior.skipCollapsed;
        }

        /* compiled from: PG */
        /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$SavedState$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                this.switching_field = i;
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                switch (this.switching_field) {
                    case 0:
                        return new SavedState(parcel, (ClassLoader) null);
                    case 1:
                        return new AppBarLayout.BaseBehavior.SavedState(parcel, null);
                    case 2:
                        return new MaterialButton.SavedState(parcel, null);
                    case 3:
                        return new CheckableImageButton.SavedState(parcel, null);
                    case 4:
                        return new ParcelableSparseArray(parcel, null);
                    case 5:
                        return new NavigationBarView.SavedState(parcel, null);
                    case 6:
                        return new SideSheetBehavior.SavedState(parcel, (ClassLoader) null);
                    case 7:
                        return new ExtendableSavedState(parcel, null);
                    default:
                        return new TextInputLayout.SavedState(parcel, null);
                }
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object[] newArray(int i) {
                switch (this.switching_field) {
                    case 0:
                        return new SavedState[i];
                    case 1:
                        return new AppBarLayout.BaseBehavior.SavedState[i];
                    case 2:
                        return new MaterialButton.SavedState[i];
                    case 3:
                        return new CheckableImageButton.SavedState[i];
                    case 4:
                        return new ParcelableSparseArray[i];
                    case 5:
                        return new NavigationBarView.SavedState[i];
                    case 6:
                        return new SideSheetBehavior.SavedState[i];
                    case 7:
                        return new ExtendableSavedState[i];
                    default:
                        return new TextInputLayout.SavedState[i];
                }
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                switch (this.switching_field) {
                    case 0:
                        return new SavedState(parcel, classLoader);
                    case 1:
                        return new AppBarLayout.BaseBehavior.SavedState(parcel, classLoader);
                    case 2:
                        return new MaterialButton.SavedState(parcel, classLoader);
                    case 3:
                        return new CheckableImageButton.SavedState(parcel, classLoader);
                    case 4:
                        return new ParcelableSparseArray(parcel, classLoader);
                    case 5:
                        return new NavigationBarView.SavedState(parcel, classLoader);
                    case 6:
                        return new SideSheetBehavior.SavedState(parcel, classLoader);
                    case 7:
                        return new ExtendableSavedState(parcel, classLoader);
                    default:
                        return new TextInputLayout.SavedState(parcel, classLoader);
                }
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }
}
