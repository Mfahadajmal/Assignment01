package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity$$ExternalSyntheticLambda0;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.mlkit.common.model.RemoteModelManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    private final float appBarElevation;
    private Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private final boolean hasLiftOnScrollColor;
    public boolean haveChildWithInterpolator;
    public WindowInsetsCompat lastInsets;
    public boolean liftOnScroll;
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    public final List liftOnScrollListeners;
    private WeakReference liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    public boolean lifted;
    public List listeners;
    public int pendingAction;
    public Drawable statusBarForeground;
    public Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private WeakReference lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        public int offsetDelta;
        public FileUtils onDragCallback$ar$class_merging$ar$class_merging$ar$class_merging;
        private SavedState savedState;

        public BaseBehavior() {
        }

        private final void addAccessibilityDelegateIfNeeded(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            if (ViewCompat.getAccessibilityDelegateInternal(coordinatorLayout) != null) {
                return;
            }
            ViewCompat.setAccessibilityDelegate(coordinatorLayout, new AccessibilityDelegateCompat(this) { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.2
                final /* synthetic */ BaseBehavior this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    View childWithScrollingBehavior$ar$ds;
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
                    if (appBarLayout.getTotalScrollRange() != 0 && (childWithScrollingBehavior$ar$ds = BaseBehavior.getChildWithScrollingBehavior$ar$ds(coordinatorLayout)) != null) {
                        AppBarLayout appBarLayout2 = appBarLayout;
                        int childCount = appBarLayout2.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            if (((LayoutParams) appBarLayout2.getChildAt(i).getLayoutParams()).scrollFlags != 0) {
                                if (this.this$0.getTopBottomOffsetForScrollingSibling() != (-appBarLayout.getTotalScrollRange())) {
                                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                                    accessibilityNodeInfoCompat.setScrollable(true);
                                }
                                if (this.this$0.getTopBottomOffsetForScrollingSibling() != 0) {
                                    if (childWithScrollingBehavior$ar$ds.canScrollVertically(-1)) {
                                        if ((-appBarLayout.getDownNestedPreScrollRange()) != 0) {
                                            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                                            accessibilityNodeInfoCompat.setScrollable(true);
                                            return;
                                        }
                                        return;
                                    }
                                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                                    accessibilityNodeInfoCompat.setScrollable(true);
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                    if (i == 4096) {
                        appBarLayout.setExpanded(false);
                        return true;
                    }
                    if (i == 8192) {
                        if (this.this$0.getTopBottomOffsetForScrollingSibling() != 0) {
                            View childWithScrollingBehavior$ar$ds = BaseBehavior.getChildWithScrollingBehavior$ar$ds(coordinatorLayout);
                            if (childWithScrollingBehavior$ar$ds.canScrollVertically(-1)) {
                                int i2 = -appBarLayout.getDownNestedPreScrollRange();
                                if (i2 != 0) {
                                    this.this$0.onNestedPreScroll$ar$ds(coordinatorLayout, appBarLayout, childWithScrollingBehavior$ar$ds, i2, new int[]{0, 0});
                                    return true;
                                }
                            } else {
                                appBarLayout.setExpanded(true);
                                return true;
                            }
                        }
                        return false;
                    }
                    return super.performAccessibilityAction(view, i, bundle);
                }
            });
        }

        private final void animateOffsetTo$ar$ds(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i) {
            int height;
            float abs = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
            float abs2 = Math.abs(0.0f);
            if (abs2 > 0.0f) {
                height = Math.round((abs / abs2) * 1000.0f) * 3;
            } else {
                height = (int) (((abs / appBarLayout.getHeight()) + 1.0f) * 150.0f);
            }
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.1
                    final /* synthetic */ BaseBehavior this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        this.this$0.setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, ((Integer) valueAnimator4.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration(Math.min(height, 600));
            this.offsetAnimator.setIntValues(topBottomOffsetForScrollingSibling, i);
            this.offsetAnimator.start();
        }

        private static boolean checkFlag(int i, int i2) {
            if ((i & i2) == i2) {
                return true;
            }
            return false;
        }

        private static final View findFirstScrollingChild$ar$ds(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof NestedScrollingChild) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public static final View getChildWithScrollingBehavior$ar$ds(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).mBehavior instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        private final void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int topInset = appBarLayout.getTopInset() + appBarLayout.getPaddingTop();
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling() - topInset;
            int childCount = appBarLayout.getChildCount();
            int i = 0;
            while (true) {
                if (i < childCount) {
                    View childAt = appBarLayout.getChildAt(i);
                    int top = childAt.getTop();
                    int bottom = childAt.getBottom();
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (checkFlag(layoutParams.scrollFlags, 32)) {
                        top -= layoutParams.topMargin;
                        bottom += layoutParams.bottomMargin;
                    }
                    int i2 = -topBottomOffsetForScrollingSibling;
                    if (top <= i2 && bottom >= i2) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    i = -1;
                    break;
                }
            }
            if (i >= 0) {
                View childAt2 = appBarLayout.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.scrollFlags;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int i5 = -childAt2.getBottom();
                    if (i == 0 && appBarLayout.getFitsSystemWindows() && childAt2.getFitsSystemWindows()) {
                        i4 -= appBarLayout.getTopInset();
                    }
                    if (checkFlag(i3, 2)) {
                        i5 += childAt2.getMinimumHeight();
                    } else if (checkFlag(i3, 5)) {
                        int minimumHeight = childAt2.getMinimumHeight() + i5;
                        if (topBottomOffsetForScrollingSibling < minimumHeight) {
                            i4 = minimumHeight;
                        } else {
                            i5 = minimumHeight;
                        }
                    }
                    if (checkFlag(i3, 32)) {
                        i4 += layoutParams2.topMargin;
                        i5 -= layoutParams2.bottomMargin;
                    }
                    if (topBottomOffsetForScrollingSibling < (i5 + i4) / 2) {
                        i4 = i5;
                    }
                    animateOffsetTo$ar$ds(coordinatorLayout, appBarLayout, NotificationCompatBuilder$Api26Impl.clamp(i4 + topInset, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        private final void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, boolean z) {
            List list;
            View view;
            boolean z2;
            int abs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            int i3 = 0;
            while (true) {
                list = null;
                if (i3 < childCount) {
                    view = appBarLayout.getChildAt(i3);
                    if (abs >= view.getTop() && abs <= view.getBottom()) {
                        break;
                    } else {
                        i3++;
                    }
                } else {
                    view = null;
                    break;
                }
            }
            if (view != null) {
                int i4 = ((LayoutParams) view.getLayoutParams()).scrollFlags;
                if ((i4 & 1) != 0) {
                    int minimumHeight = view.getMinimumHeight();
                    z2 = true;
                    if (i2 > 0) {
                    }
                }
            }
            z2 = false;
            if (appBarLayout.liftOnScroll) {
                z2 = appBarLayout.shouldLift(findFirstScrollingChild$ar$ds(coordinatorLayout));
            }
            boolean liftedState = appBarLayout.setLiftedState(z2);
            if (!z) {
                if (liftedState) {
                    ArrayList incomingEdgesInternal = coordinatorLayout.mChildDag$ar$class_merging$ar$class_merging$ar$class_merging.getIncomingEdgesInternal(appBarLayout);
                    if (incomingEdgesInternal != null) {
                        list = new ArrayList(incomingEdgesInternal);
                    }
                    if (list == null) {
                        list = Collections.emptyList();
                    }
                    int size = list.size();
                    for (int i5 = 0; i5 < size; i5++) {
                        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) ((View) list.get(i5)).getLayoutParams()).mBehavior;
                        if (behavior instanceof ScrollingViewBehavior) {
                            if (((ScrollingViewBehavior) behavior).overlayTop == 0) {
                                return;
                            }
                        }
                    }
                    return;
                }
                return;
            }
            if (appBarLayout.getBackground() != null) {
                appBarLayout.getBackground().jumpToCurrentState();
            }
            if (appBarLayout.getForeground() != null) {
                appBarLayout.getForeground().jumpToCurrentState();
            }
            if (appBarLayout.getStateListAnimator() != null) {
                appBarLayout.getStateListAnimator().jumpToCurrentState();
            }
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final /* bridge */ /* synthetic */ boolean canDragView(View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.onDragCallback$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                if (Build.VERSION.SDK_INT > 33 && appBarLayout.getResources().getConfiguration().orientation == 2) {
                    return true;
                }
            } else {
                WeakReference weakReference = this.lastNestedScrollingChildRef;
                if (weakReference == null) {
                    return true;
                }
                View view2 = (View) weakReference.get();
                if (view2 != null && view2.isShown() && !view2.canScrollVertically(-1)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final /* bridge */ /* synthetic */ int getMaxDragOffset(View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            return (-appBarLayout.getDownNestedScrollRange()) + appBarLayout.getTopInset();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final /* synthetic */ int getScrollRangeForDragFling(View view) {
            return ((AppBarLayout) view).getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final /* bridge */ /* synthetic */ void onFlingFinished(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            snapToChildIfNeeded(coordinatorLayout, appBarLayout);
            if (appBarLayout.liftOnScroll) {
                appBarLayout.setLiftedState(appBarLayout.shouldLift(findFirstScrollingChild$ar$ds(coordinatorLayout)));
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            onLayoutChild$ar$ds(coordinatorLayout, (AppBarLayout) view, i);
            return true;
        }

        public void onLayoutChild$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int round;
            super.onLayoutChild(coordinatorLayout, appBarLayout, i);
            int i2 = appBarLayout.pendingAction;
            SavedState savedState = this.savedState;
            if (savedState != null && (i2 & 8) == 0) {
                if (savedState.fullyScrolled) {
                    setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
                } else if (savedState.fullyExpanded) {
                    setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, 0);
                } else {
                    View childAt = appBarLayout.getChildAt(savedState.firstVisibleChildIndex);
                    int i3 = -childAt.getBottom();
                    if (this.savedState.firstVisibleChildAtMinimumHeight) {
                        round = childAt.getMinimumHeight() + appBarLayout.getTopInset();
                    } else {
                        round = Math.round(childAt.getHeight() * this.savedState.firstVisibleChildPercentageShown);
                    }
                    setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, i3 + round);
                }
            } else if (i2 != 0) {
                int i4 = i2 & 4;
                if ((i2 & 2) != 0) {
                    int i5 = -appBarLayout.getTotalScrollRange();
                    if (i4 != 0) {
                        animateOffsetTo$ar$ds(coordinatorLayout, appBarLayout, i5);
                    } else {
                        setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, i5);
                    }
                } else if ((i2 & 1) != 0) {
                    if (i4 != 0) {
                        animateOffsetTo$ar$ds(coordinatorLayout, appBarLayout, 0);
                    } else {
                        setHeaderTopBottomOffset$ar$ds(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            appBarLayout.pendingAction = 0;
            this.savedState = null;
            setTopAndBottomOffset(NotificationCompatBuilder$Api26Impl.clamp(getTopAndBottomOffset(), -appBarLayout.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, getTopAndBottomOffset(), 0, true);
            appBarLayout.onOffsetChanged(getTopAndBottomOffset());
            addAccessibilityDelegateIfNeeded(coordinatorLayout, appBarLayout);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: onMeasureChild$ar$ds$f07ff2f1_0, reason: merged with bridge method [inline-methods] */
        public boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3) {
            if (((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).height != -2) {
                return false;
            }
            coordinatorLayout.onMeasureChild$ar$ds$27eaff33_0(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            onNestedPreScroll$ar$ds(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        public final void onNestedPreScroll$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int[] iArr) {
            int i2;
            int i3;
            if (i != 0) {
                if (i < 0) {
                    i2 = -appBarLayout.getTotalScrollRange();
                    i3 = appBarLayout.getDownNestedPreScrollRange() + i2;
                } else {
                    i2 = -appBarLayout.getTotalScrollRange();
                    i3 = 0;
                }
                int i4 = i2;
                int i5 = i3;
                if (i4 != i5) {
                    iArr[1] = scroll(coordinatorLayout, appBarLayout, i, i4, i5);
                }
            }
            if (appBarLayout.liftOnScroll) {
                appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            onNestedScroll$ar$ds(coordinatorLayout, (AppBarLayout) view, i4, iArr);
        }

        public final void onNestedScroll$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int[] iArr) {
            if (i < 0) {
                iArr[1] = scroll(coordinatorLayout, appBarLayout, i, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i == 0) {
                addAccessibilityDelegateIfNeeded(coordinatorLayout, appBarLayout);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            onRestoreInstanceState$ar$ds(parcelable);
        }

        public final void onRestoreInstanceState$ar$ds(Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                restoreScrollState((SavedState) parcelable, true);
                Parcelable parcelable2 = this.savedState.mSuperState;
            } else {
                this.savedState = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
            return onSaveInstanceState$ar$ds((AppBarLayout) view);
        }

        public final Parcelable onSaveInstanceState$ar$ds(AppBarLayout appBarLayout) {
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState saveScrollState = saveScrollState(absSavedState, appBarLayout);
            if (saveScrollState == null) {
                return absSavedState;
            }
            return saveScrollState;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            return onStartNestedScroll$ar$ds(coordinatorLayout, (AppBarLayout) view, view2, i, i2);
        }

        public final boolean onStartNestedScroll$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2) {
            ValueAnimator valueAnimator;
            boolean z = false;
            if ((i & 2) != 0 && (appBarLayout.liftOnScroll || appBarLayout.lifted || (appBarLayout.getTotalScrollRange() != 0 && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight()))) {
                z = true;
            }
            if (z && (valueAnimator = this.offsetAnimator) != null) {
                valueAnimator.cancel();
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = i2;
            return z;
        }

        final void restoreScrollState(SavedState savedState, boolean z) {
            if (this.savedState != null && !z) {
                return;
            }
            this.savedState = savedState;
        }

        final SavedState saveScrollState(Parcelable parcelable, AppBarLayout appBarLayout) {
            boolean z;
            boolean z2;
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = appBarLayout.getChildCount();
            boolean z3 = false;
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = androidx.customview.view.AbsSavedState.EMPTY_STATE;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    if (topAndBottomOffset == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    savedState.fullyExpanded = z;
                    if (!z && (-topAndBottomOffset) >= appBarLayout.getTotalScrollRange()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    savedState.fullyScrolled = z2;
                    savedState.firstVisibleChildIndex = i;
                    if (bottom == childAt.getMinimumHeight() + appBarLayout.getTopInset()) {
                        z3 = true;
                    }
                    savedState.firstVisibleChildAtMinimumHeight = z3;
                    savedState.firstVisibleChildPercentageShown = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final /* bridge */ /* synthetic */ int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            int i4;
            int i5;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int i6 = 0;
            if (i2 != 0 && topBottomOffsetForScrollingSibling >= i2 && topBottomOffsetForScrollingSibling <= i3) {
                int clamp = NotificationCompatBuilder$Api26Impl.clamp(i, i2, i3);
                if (topBottomOffsetForScrollingSibling != clamp) {
                    if (appBarLayout.haveChildWithInterpolator) {
                        int abs = Math.abs(clamp);
                        int childCount = appBarLayout.getChildCount();
                        int i7 = 0;
                        while (true) {
                            if (i7 >= childCount) {
                                break;
                            }
                            View childAt = appBarLayout.getChildAt(i7);
                            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                            Interpolator interpolator = layoutParams.scrollInterpolator;
                            if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                                if (interpolator != null) {
                                    int i8 = layoutParams.scrollFlags;
                                    if ((i8 & 1) != 0) {
                                        i5 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                                        if ((i8 & 2) != 0) {
                                            i5 -= childAt.getMinimumHeight();
                                        }
                                    } else {
                                        i5 = 0;
                                    }
                                    if (childAt.getFitsSystemWindows()) {
                                        i5 -= appBarLayout.getTopInset();
                                    }
                                    if (i5 > 0) {
                                        float f = i5;
                                        i4 = Integer.signum(clamp) * (childAt.getTop() + Math.round(f * interpolator.getInterpolation((abs - childAt.getTop()) / f)));
                                    }
                                }
                            } else {
                                i7++;
                            }
                        }
                    }
                    i4 = clamp;
                    boolean topAndBottomOffset = setTopAndBottomOffset(i4);
                    int i9 = topBottomOffsetForScrollingSibling - clamp;
                    this.offsetDelta = clamp - i4;
                    int i10 = 1;
                    if (topAndBottomOffset) {
                        for (int i11 = 0; i11 < appBarLayout.getChildCount(); i11++) {
                            LayoutParams layoutParams2 = (LayoutParams) appBarLayout.getChildAt(i11).getLayoutParams();
                            RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = layoutParams2.scrollEffect$ar$class_merging$ar$class_merging$ar$class_merging;
                            if (remoteModelManagerRegistration != null && (layoutParams2.scrollFlags & 1) != 0) {
                                View childAt2 = appBarLayout.getChildAt(i11);
                                float topAndBottomOffset2 = getTopAndBottomOffset();
                                Rect rect = (Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                                childAt2.getDrawingRect(rect);
                                appBarLayout.offsetDescendantRectToMyCoords(childAt2, rect);
                                rect.offset(0, -appBarLayout.getTopInset());
                                float abs2 = ((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).top - Math.abs(topAndBottomOffset2);
                                if (abs2 <= 0.0f) {
                                    float clamp2 = 1.0f - NotificationCompatBuilder$Api26Impl.clamp(Math.abs(abs2 / ((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).height()), 0.0f, 1.0f);
                                    float height = (-abs2) - ((((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).height() * 0.3f) * (1.0f - (clamp2 * clamp2)));
                                    childAt2.setTranslationY(height);
                                    childAt2.getDrawingRect((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
                                    ((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider).offset(0, (int) (-height));
                                    if (height >= ((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider).height()) {
                                        childAt2.setAlpha(0.0f);
                                    } else {
                                        childAt2.setAlpha(1.0f);
                                    }
                                    childAt2.setClipBounds((Rect) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
                                } else {
                                    childAt2.setClipBounds(null);
                                    childAt2.setTranslationY(0.0f);
                                    childAt2.setAlpha(1.0f);
                                }
                            }
                        }
                    } else if (appBarLayout.haveChildWithInterpolator) {
                        coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                    }
                    appBarLayout.onOffsetChanged(getTopAndBottomOffset());
                    if (clamp < topBottomOffsetForScrollingSibling) {
                        i10 = -1;
                    }
                    updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, clamp, i10, false);
                    i6 = i9;
                }
            } else {
                this.offsetDelta = 0;
            }
            addAccessibilityDelegateIfNeeded(coordinatorLayout, appBarLayout);
            return i6;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            if (this.lastStartedType == 0 || i == 1) {
                snapToChildIfNeeded(coordinatorLayout, appBarLayout);
                if (appBarLayout.liftOnScroll) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference(view);
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SavedState extends androidx.customview.view.AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new BottomSheetBehavior.SavedState.AnonymousClass1(1);
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;
            boolean fullyExpanded;
            boolean fullyScrolled;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.fullyScrolled = parcel.readByte() != 0;
                this.fullyExpanded = parcel.readByte() != 0;
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeByte(this.fullyScrolled ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.fullyExpanded ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public final /* bridge */ /* synthetic */ void onLayoutChild$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            super.onLayoutChild$ar$ds(coordinatorLayout, appBarLayout, i);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* renamed from: onMeasureChild$ar$ds$f07ff2f1_0 */
        public final /* bridge */ /* synthetic */ boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3) {
            return super.onMeasureChild$ar$ds(coordinatorLayout, appBarLayout, i, i2, i3);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends LinearLayout.LayoutParams {
        public RemoteModelManager.RemoteModelManagerRegistration scrollEffect$ar$class_merging$ar$class_merging$ar$class_merging;
        int scrollFlags;
        Interpolator scrollInterpolator;

        public LayoutParams() {
            super(-1, -2);
            this.scrollFlags = 1;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.scrollFlags = 1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.scrollFlags = obtainStyledAttributes.getInt(1, 0);
            this.scrollEffect$ar$class_merging$ar$class_merging$ar$class_merging = obtainStyledAttributes.getInt(0, 0) == 1 ? new RemoteModelManager.RemoteModelManagerRegistration((byte[]) null, (byte[]) null) : null;
            if (obtainStyledAttributes.hasValue(2)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(2, 0));
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LiftOnScrollListener {
        void onUpdate$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        static final AppBarLayout findFirstDependency$ar$ds(List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final /* bridge */ /* synthetic */ View findFirstDependency(List list) {
            return findFirstDependency$ar$ds(list);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final float getOverlapRatioForOffset(View view) {
            int i;
            int i2;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).mBehavior;
                if (behavior instanceof BaseBehavior) {
                    i = ((BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling();
                } else {
                    i = 0;
                }
                if ((downNestedPreScrollRange == 0 || totalScrollRange + i > downNestedPreScrollRange) && (i2 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (i / i2) + 1.0f;
                }
                return 0.0f;
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final int getScrollRange(View view) {
            return ((AppBarLayout) view).getTotalScrollRange();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean layoutDependsOn$ar$ds(View view) {
            return view instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onDependentViewChanged$ar$ds(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).mBehavior;
            if (behavior instanceof BaseBehavior) {
                int bottom = (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).offsetDelta) + this.verticalLayoutGap) - getOverlapPixelsForOffset(view2);
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                view.offsetTopAndBottom(bottom);
            }
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.liftOnScroll) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
                }
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void onDependentViewRemoved$ar$ds(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout, null);
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            super.onLayoutChild(coordinatorLayout, view, i);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            int i4;
            WindowInsetsCompat windowInsetsCompat;
            int i5 = view.getLayoutParams().height;
            if (i5 != -1) {
                if (i5 != -2) {
                    return false;
                }
                i5 = -2;
            }
            View findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (findFirstDependency == null) {
                return false;
            }
            int size = View.MeasureSpec.getSize(i3);
            if (size > 0) {
                if (findFirstDependency.getFitsSystemWindows() && (windowInsetsCompat = coordinatorLayout.mLastInsets) != null) {
                    size += windowInsetsCompat.getSystemWindowInsetTop() + windowInsetsCompat.getSystemWindowInsetBottom();
                }
            } else {
                size = coordinatorLayout.getHeight();
            }
            int scrollRange = size + getScrollRange(findFirstDependency);
            int measuredHeight = findFirstDependency.getMeasuredHeight();
            if (shouldHeaderOverlapScrollingChild()) {
                view.setTranslationY(-measuredHeight);
            } else {
                view.setTranslationY(0.0f);
                scrollRange -= measuredHeight;
            }
            if (i5 == -1) {
                i4 = 1073741824;
            } else {
                i4 = Integer.MIN_VALUE;
            }
            coordinatorLayout.onMeasureChild$ar$ds$27eaff33_0(view, i, i2, View.MeasureSpec.makeMeasureSpec(scrollRange, i4));
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout findFirstDependency$ar$ds = findFirstDependency$ar$ds(coordinatorLayout.getDependencies(view));
            if (findFirstDependency$ar$ds != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                Rect rect3 = this.tempRect1;
                rect3.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect3.contains(rect2)) {
                    findFirstDependency$ar$ds.setExpanded(false, !z);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            this.overlayTop = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    protected static final LayoutParams generateLayoutParams$ar$ds$a9354527_0(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    private final void invalidateScrollRanges() {
        Behavior behavior = this.behavior;
        BaseBehavior.SavedState savedState = null;
        if (behavior != null && this.totalScrollRange != -1 && this.pendingAction == 0) {
            savedState = behavior.saveScrollState(androidx.customview.view.AbsSavedState.EMPTY_STATE, this);
        }
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (savedState != null) {
            this.behavior.restoreScrollState(savedState, false);
        }
    }

    private final boolean shouldDrawStatusBarForeground() {
        if (this.statusBarForeground != null && getTopInset() > 0) {
            return true;
        }
        return false;
    }

    private final boolean shouldOffsetFirstChild() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8 && !childAt.getFitsSystemWindows()) {
                return true;
            }
        }
        return false;
    }

    private final void startLiftOnScrollColorAnimation(float f, float f2) {
        ValueAnimator valueAnimator = this.liftOnScrollColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.liftOnScrollColorAnimator = ofFloat;
        ofFloat.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.liftOnScrollColorUpdateListener;
        if (animatorUpdateListener != null) {
            this.liftOnScrollColorAnimator.addUpdateListener(animatorUpdateListener);
        }
        this.liftOnScrollColorAnimator.start();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (shouldDrawStatusBarForeground()) {
            int save = canvas.save();
            canvas.translate(0.0f, -this.currentOffset);
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public final CoordinatorLayout.Behavior getBehavior() {
        Behavior behavior = new Behavior();
        this.behavior = behavior;
        return behavior;
    }

    final int getDownNestedPreScrollRange() {
        int i;
        int minimumHeight;
        int i2 = this.downPreScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i4 = layoutParams.scrollFlags;
                if ((i4 & 5) == 5) {
                    int i5 = layoutParams.topMargin + layoutParams.bottomMargin;
                    if ((i4 & 8) != 0) {
                        minimumHeight = childAt.getMinimumHeight();
                    } else if ((i4 & 2) != 0) {
                        minimumHeight = measuredHeight - childAt.getMinimumHeight();
                    } else {
                        i = i5 + measuredHeight;
                        if (childCount == 0 && childAt.getFitsSystemWindows()) {
                            i = Math.min(i, measuredHeight - getTopInset());
                        }
                        i3 += i;
                    }
                    i = i5 + minimumHeight;
                    if (childCount == 0) {
                        i = Math.min(i, measuredHeight - getTopInset());
                    }
                    i3 += i;
                } else if (i3 > 0) {
                    break;
                }
            }
        }
        int max = Math.max(0, i3);
        this.downPreScrollRange = max;
        return max;
    }

    final int getDownNestedScrollRange() {
        int i = this.downScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                int i4 = layoutParams.scrollFlags;
                if ((i4 & 1) == 0) {
                    break;
                }
                i3 += measuredHeight;
                if ((i4 & 2) != 0) {
                    i3 -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.downScrollRange = max;
        return max;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = getMinimumHeight();
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                minimumHeight = getChildAt(childCount - 1).getMinimumHeight();
            } else {
                minimumHeight = 0;
            }
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return minimumHeight + minimumHeight + topInset;
    }

    final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.totalScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i4 = layoutParams.scrollFlags;
                if ((i4 & 1) == 0) {
                    break;
                }
                i3 += measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                if (i2 == 0) {
                    if (childAt.getFitsSystemWindows()) {
                        i3 -= getTopInset();
                    }
                    i2 = 0;
                }
                if ((i4 & 2) != 0) {
                    i3 -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.totalScrollRange = max;
        return max;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        EdgeTreatment.setParentAbsoluteElevation(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final int[] onCreateDrawableState(int i) {
        int i2;
        int i3;
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int length = iArr.length;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 4);
        boolean z = this.liftable;
        if (true != z) {
            i2 = -2130969846;
        } else {
            i2 = R.attr.state_liftable;
        }
        iArr[0] = i2;
        int i4 = -2130969847;
        if (z && this.lifted) {
            i4 = R.attr.state_lifted;
        }
        iArr[1] = i4;
        if (z) {
            i3 = R.attr.state_collapsible;
        } else {
            i3 = -2130969842;
        }
        iArr[2] = i3;
        int i5 = -2130969841;
        if (z && this.lifted) {
            i5 = R.attr.state_collapsed;
        }
        iArr[3] = i5;
        return mergeDrawableStates(onCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        /*
            r1 = this;
            super.onLayout(r2, r3, r4, r5, r6)
            boolean r2 = r1.getFitsSystemWindows()
            if (r2 == 0) goto L25
            boolean r2 = r1.shouldOffsetFirstChild()
            if (r2 == 0) goto L25
            int r2 = r1.getTopInset()
            int r3 = r1.getChildCount()
        L17:
            int r3 = r3 + (-1)
            if (r3 < 0) goto L25
            android.view.View r4 = r1.getChildAt(r3)
            int[] r5 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
            r4.offsetTopAndBottom(r2)
            goto L17
        L25:
            r1.invalidateScrollRanges()
            r2 = 0
            r1.haveChildWithInterpolator = r2
            int r3 = r1.getChildCount()
            r4 = r2
        L30:
            r5 = 1
            if (r4 >= r3) goto L47
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r6 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r6
            android.view.animation.Interpolator r6 = r6.scrollInterpolator
            if (r6 == 0) goto L44
            r1.haveChildWithInterpolator = r5
            goto L47
        L44:
            int r4 = r4 + 1
            goto L30
        L47:
            android.graphics.drawable.Drawable r3 = r1.statusBarForeground
            if (r3 == 0) goto L56
            int r4 = r1.getWidth()
            int r6 = r1.getTopInset()
            r3.setBounds(r2, r2, r4, r6)
        L56:
            boolean r3 = r1.liftOnScroll
            if (r3 != 0) goto L79
            int r3 = r1.getChildCount()
            r4 = r2
        L5f:
            if (r4 >= r3) goto L7a
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r6 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r6
            int r6 = r6.scrollFlags
            r0 = r6 & 1
            if (r0 != r5) goto L76
            r6 = r6 & 10
            if (r6 == 0) goto L76
            goto L79
        L76:
            int r4 = r4 + 1
            goto L5f
        L79:
            r2 = r5
        L7a:
            boolean r3 = r1.liftable
            if (r3 == r2) goto L83
            r1.liftable = r2
            r1.refreshDrawableState()
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && getFitsSystemWindows() && shouldOffsetFirstChild()) {
            int measuredHeight = getMeasuredHeight();
            if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    measuredHeight += getTopInset();
                }
            } else {
                measuredHeight = NotificationCompatBuilder$Api26Impl.clamp(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i2));
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        invalidateScrollRanges();
    }

    final void onOffsetChanged(int i) {
        this.currentOffset = i;
        if (!willNotDraw()) {
            postInvalidateOnAnimation();
        }
        List list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) this.listeners.get(i2);
                if (shadowDelegateImpl != null) {
                    shadowDelegateImpl.onOffsetChanged$ar$ds(i);
                }
            }
        }
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        EdgeTreatment.setElevation(this, f);
    }

    public final void setExpanded(boolean z) {
        setExpanded(z, isLaidOut());
    }

    final boolean setLiftedState(boolean z) {
        float f;
        float f2;
        if (this.lifted != z) {
            this.lifted = z;
            refreshDrawableState();
            if (!(getBackground() instanceof MaterialShapeDrawable)) {
                return true;
            }
            float f3 = 0.0f;
            if (this.hasLiftOnScrollColor) {
                if (true != z) {
                    f2 = 1.0f;
                } else {
                    f2 = 0.0f;
                }
                if (true == z) {
                    f3 = 1.0f;
                }
                startLiftOnScrollColorAnimation(f2, f3);
                return true;
            }
            if (!this.liftOnScroll) {
                return true;
            }
            if (z) {
                f = 0.0f;
            } else {
                f = this.appBarElevation;
            }
            if (z) {
                f3 = this.appBarElevation;
            }
            startLiftOnScrollColorAnimation(f, f3);
            return true;
        }
        return false;
    }

    @Override // android.widget.LinearLayout
    public final void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(1);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            drawable.setVisible(z, false);
        }
    }

    final boolean shouldLift(View view) {
        int i;
        View view2;
        View view3 = null;
        if (this.liftOnScrollTargetView == null && (i = this.liftOnScrollTargetViewId) != -1) {
            if (view != null) {
                view2 = view.findViewById(i);
            } else {
                view2 = null;
            }
            if (view2 == null && (getParent() instanceof ViewGroup)) {
                view2 = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (view2 != null) {
                this.liftOnScrollTargetView = new WeakReference(view2);
            }
        }
        WeakReference weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            view3 = (View) weakReference.get();
        }
        if (view3 != null) {
            view = view3;
        }
        if (view == null) {
            return false;
        }
        if (!view.canScrollVertically(-1) && view.getScrollY() <= 0) {
            return false;
        }
        return true;
    }

    public final void updateWillNotDraw() {
        setWillNotDraw(!shouldDrawStatusBarForeground());
    }

    @Override // android.view.View
    protected final boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable) && drawable != this.statusBarForeground) {
            return false;
        }
        return true;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.appBarLayoutStyle);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected final /* synthetic */ LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public final void setExpanded(boolean z, boolean z2) {
        setExpanded(z, z2, true);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, R.style.Widget_Design_AppBarLayout), attributeSet, i);
        Integer valueOf;
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.pendingAction = 0;
        this.liftOnScrollListeners = new ArrayList();
        Context context2 = getContext();
        setOrientation(1);
        if (getOutlineProvider() == ViewOutlineProvider.BACKGROUND) {
            int[] iArr = ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS;
            setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
        int[] iArr2 = ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS;
        Context context3 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context3, attributeSet, ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS, i, R.style.Widget_Design_AppBarLayout, new int[0]);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, obtainStyledAttributes.getResourceId(0, 0)));
            }
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.AppBarLayout, i, R.style.Widget_Design_AppBarLayout, new int[0]);
            setBackground(obtainStyledAttributes2.getDrawable(0));
            final ColorStateList colorStateList = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes2, 6);
            this.hasLiftOnScrollColor = colorStateList != null;
            final ColorStateList colorStateListOrNull = DrawableUtils$OutlineCompatR.getColorStateListOrNull(getBackground());
            int i2 = 3;
            if (colorStateListOrNull != null) {
                final MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
                materialShapeDrawable.setFillColor(colorStateListOrNull);
                if (colorStateList != null) {
                    final Integer colorOrNull = FileUtils.getColorOrNull(getContext(), R.attr.colorSurface);
                    this.liftOnScrollColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayout$$ExternalSyntheticLambda0
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            Integer num;
                            int layer = FileUtils.layer(colorStateListOrNull.getDefaultColor(), colorStateList.getDefaultColor(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
                            ColorStateList valueOf2 = ColorStateList.valueOf(layer);
                            MaterialShapeDrawable materialShapeDrawable2 = materialShapeDrawable;
                            materialShapeDrawable2.setFillColor(valueOf2);
                            AppBarLayout appBarLayout = AppBarLayout.this;
                            if (appBarLayout.statusBarForeground != null && (num = appBarLayout.statusBarForegroundOriginalColor) != null && num.equals(colorOrNull)) {
                                DrawableCompat$Api21Impl.setTint(appBarLayout.statusBarForeground, layer);
                            }
                            if (!appBarLayout.liftOnScrollListeners.isEmpty()) {
                                for (AppBarLayout.LiftOnScrollListener liftOnScrollListener : appBarLayout.liftOnScrollListeners) {
                                    if (materialShapeDrawable2.getFillColor() != null) {
                                        liftOnScrollListener.onUpdate$ar$ds();
                                    }
                                }
                            }
                        }
                    };
                    setBackground(materialShapeDrawable);
                } else {
                    materialShapeDrawable.initializeElevationOverlay(context2);
                    this.liftOnScrollColorUpdateListener = new CircularProgressDrawable.AnonymousClass1(this, materialShapeDrawable, i2);
                    setBackground(materialShapeDrawable);
                }
            }
            this.liftOnScrollColorDuration = DrawableUtils$OutlineCompatR.resolveInteger(context2, R.attr.motionDurationMedium2, getResources().getInteger(R.integer.app_bar_elevation_anim_duration));
            this.liftOnScrollColorInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context2, R.attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
            if (obtainStyledAttributes2.hasValue(4)) {
                setExpanded(obtainStyledAttributes2.getBoolean(4, false), false, false);
            }
            if (obtainStyledAttributes2.hasValue(3)) {
                ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, obtainStyledAttributes2.getDimensionPixelSize(3, 0));
            }
            if (obtainStyledAttributes2.hasValue(2)) {
                setKeyboardNavigationCluster(obtainStyledAttributes2.getBoolean(2, false));
            }
            if (obtainStyledAttributes2.hasValue(1)) {
                setTouchscreenBlocksFocus(obtainStyledAttributes2.getBoolean(1, false));
            }
            this.appBarElevation = getResources().getDimension(R.dimen.design_appbar_elevation);
            this.liftOnScroll = obtainStyledAttributes2.getBoolean(5, false);
            this.liftOnScrollTargetViewId = obtainStyledAttributes2.getResourceId(7, -1);
            Drawable drawable = obtainStyledAttributes2.getDrawable(8);
            Drawable drawable2 = this.statusBarForeground;
            if (drawable2 != drawable) {
                if (drawable2 != null) {
                    drawable2.setCallback(null);
                }
                Drawable mutate = drawable != null ? drawable.mutate() : null;
                this.statusBarForeground = mutate;
                if (mutate instanceof MaterialShapeDrawable) {
                    valueOf = Integer.valueOf(((MaterialShapeDrawable) mutate).resolvedTintColor);
                } else {
                    ColorStateList colorStateListOrNull2 = DrawableUtils$OutlineCompatR.getColorStateListOrNull(mutate);
                    valueOf = colorStateListOrNull2 != null ? Integer.valueOf(colorStateListOrNull2.getDefaultColor()) : null;
                }
                this.statusBarForegroundOriginalColor = valueOf;
                Drawable drawable3 = this.statusBarForeground;
                if (drawable3 != null) {
                    if (drawable3.isStateful()) {
                        this.statusBarForeground.setState(getDrawableState());
                    }
                    DrawableCompat$Api23Impl.setLayoutDirection(this.statusBarForeground, getLayoutDirection());
                    this.statusBarForeground.setVisible(getVisibility() == 0, false);
                    this.statusBarForeground.setCallback(this);
                }
                updateWillNotDraw();
                postInvalidateOnAnimation();
            }
            obtainStyledAttributes2.recycle();
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, new SelectToSpeakPopupActivity$$ExternalSyntheticLambda0(this, 2, null));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private final void setExpanded(boolean z, boolean z2, boolean z3) {
        this.pendingAction = (true != z ? 2 : 1) | (true != z2 ? 0 : 4) | (true == z3 ? 8 : 0);
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateLayoutParams$ar$ds$a9354527_0(layoutParams);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateLayoutParams$ar$ds$a9354527_0(layoutParams);
    }
}
