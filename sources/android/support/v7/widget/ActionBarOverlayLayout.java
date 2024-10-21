package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import androidx.core.graphics.Insets;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.Preference;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent2, NestedScrollingParent3 {
    static final int[] ATTRS = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    private static final WindowInsetsCompat NON_EMPTY_SYSTEM_WINDOW_INSETS;
    private static final Rect ZERO_INSETS;
    private int mActionBarHeight;
    public ActionBarContainer mActionBarTop;
    public ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private WindowInsetsCompat mBaseInnerInsets;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    public ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private ToolbarWidgetWrapper mDecorToolbar$ar$class_merging;
    private OverScroller mFlingEstimator;
    public boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private WindowInsetsCompat mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private WindowInsetsCompat mLastBaseInnerInsets;
    private WindowInsetsCompat mLastInnerInsets;
    public int mLastSystemUiVisibility;
    private final NoSystemUiLayoutFlagView mNoSystemUiLayoutFlagView;
    public boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final Rect mTmpRect;
    public final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    public int mWindowVisibility;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ActionBarVisibilityCallback {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NoSystemUiLayoutFlagView extends View {
        public NoSystemUiLayoutFlagView(Context context) {
            super(context);
            setWillNotDraw(true);
        }

        @Override // android.view.View
        public final int getWindowSystemUiVisibility() {
            return 0;
        }
    }

    static {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat();
        collectionItemInfoCompat.setSystemWindowInsets$ar$ds(Insets.of(0, 1, 0, 1));
        NON_EMPTY_SYSTEM_WINDOW_INSETS = collectionItemInfoCompat.m31build();
        ZERO_INSETS = new Rect();
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    private static final boolean applyInsets$ar$ds(View view, Rect rect, boolean z) {
        boolean z2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            z2 = true;
        } else {
            z2 = false;
        }
        if (layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z2 = true;
        }
        if (layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z2 = true;
        }
        if (z && layoutParams.bottomMargin != rect.bottom) {
            layoutParams.bottomMargin = rect.bottom;
            return true;
        }
        return z2;
    }

    private final void init(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(ATTRS);
        boolean z = false;
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.mWindowContentOverlay = drawable;
        if (drawable == null) {
            z = true;
        }
        setWillNotDraw(z);
        obtainStyledAttributes.recycle();
        this.mFlingEstimator = new OverScroller(context);
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar$ar$class_merging.canShowOverflowMenu();
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void dismissPopups() {
        pullChildren();
        this.mDecorToolbar$ar$class_merging.dismissPopupMenus();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.mWindowContentOverlay != null) {
            if (this.mActionBarTop.getVisibility() == 0) {
                i = (int) (this.mActionBarTop.getBottom() + this.mActionBarTop.getTranslationY() + 0.5f);
            } else {
                i = 0;
            }
            this.mWindowContentOverlay.setBounds(0, i, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + i);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public final void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        ViewPropertyAnimator viewPropertyAnimator = this.mCurrentActionBarTopAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar$ar$class_merging.hideOverflowMenu();
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void initFeature(int i) {
        pullChildren();
        if (i != 109) {
            return;
        }
        this.mOverlayMode = true;
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar$ar$class_merging.isOverflowMenuShowPending();
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar$ar$class_merging.isOverflowMenuShowing();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
    
        if (r0 != false) goto L9;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.WindowInsets onApplyWindowInsets(android.view.WindowInsets r6) {
        /*
            r5 = this;
            r5.pullChildren()
            androidx.core.view.WindowInsetsCompat r6 = androidx.core.view.WindowInsetsCompat.toWindowInsetsCompat(r6, r5)
            android.graphics.Rect r0 = new android.graphics.Rect
            int r1 = r6.getSystemWindowInsetLeft()
            int r2 = r6.getSystemWindowInsetTop()
            int r3 = r6.getSystemWindowInsetRight()
            int r4 = r6.getSystemWindowInsetBottom()
            r0.<init>(r1, r2, r3, r4)
            android.support.v7.widget.ActionBarContainer r1 = r5.mActionBarTop
            r2 = 0
            boolean r0 = applyInsets$ar$ds(r1, r0, r2)
            android.graphics.Rect r1 = r5.mBaseContentInsets
            androidx.core.view.ViewCompat.Api21Impl.computeSystemWindowInsets(r5, r6, r1)
            android.graphics.Rect r1 = r5.mBaseContentInsets
            int r1 = r1.left
            android.graphics.Rect r2 = r5.mBaseContentInsets
            int r2 = r2.top
            android.graphics.Rect r3 = r5.mBaseContentInsets
            int r3 = r3.right
            android.graphics.Rect r4 = r5.mBaseContentInsets
            int r4 = r4.bottom
            androidx.core.view.WindowInsetsCompat r1 = r6.inset(r1, r2, r3, r4)
            r5.mBaseInnerInsets = r1
            androidx.core.view.WindowInsetsCompat r2 = r5.mLastBaseInnerInsets
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L4b
            androidx.core.view.WindowInsetsCompat r0 = r5.mBaseInnerInsets
            r5.mLastBaseInnerInsets = r0
            r0 = 1
        L4b:
            android.graphics.Rect r1 = r5.mLastBaseContentInsets
            android.graphics.Rect r2 = r5.mBaseContentInsets
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L5d
            android.graphics.Rect r0 = r5.mLastBaseContentInsets
            android.graphics.Rect r1 = r5.mBaseContentInsets
            r0.set(r1)
            goto L5f
        L5d:
            if (r0 == 0) goto L62
        L5f:
            r5.requestLayout()
        L62:
            androidx.core.view.WindowInsetsCompat r6 = r6.consumeDisplayCutout()
            androidx.core.view.WindowInsetsCompat r6 = r6.consumeSystemWindowInsets()
            androidx.core.view.WindowInsetsCompat r6 = r6.consumeStableInsets()
            android.view.WindowInsets r6 = r6.toWindowInsets()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarOverlayLayout.onApplyWindowInsets(android.view.WindowInsets):android.view.WindowInsets");
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        init(getContext());
        ViewCompat.Api20Impl.requestApplyInsets(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = layoutParams.leftMargin + paddingLeft;
                int i7 = layoutParams.topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00dd  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onMeasure(int r12, int r13) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarOverlayLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (this.mHideOnContentScroll && z) {
            this.mFlingEstimator.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Preference.DEFAULT_ORDER);
            if (this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight()) {
                haltActionBarHideOffsetAnimations();
                this.mAddActionBarHideOffset.run();
            } else {
                haltActionBarHideOffsetAnimations();
                this.mRemoveActionBarHideOffset.run();
            }
            this.mAnimatingForFling = true;
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.mHideOnContentScrollReference + i2;
        this.mHideOnContentScrollReference = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        WindowDecorActionBar windowDecorActionBar;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        ActionBarContainer actionBarContainer = this.mActionBarTop;
        this.mHideOnContentScrollReference = actionBarContainer != null ? -((int) actionBarContainer.getTranslationY()) : 0;
        haltActionBarHideOffsetAnimations();
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback == null || (viewPropertyAnimatorCompatSet = (windowDecorActionBar = (WindowDecorActionBar) actionBarVisibilityCallback).mCurrentShowAnim) == null) {
            return;
        }
        viewPropertyAnimatorCompatSet.cancel();
        windowDecorActionBar.mCurrentShowAnim = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.mActionBarTop.getVisibility() != 0) {
            return false;
        }
        return this.mHideOnContentScroll;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.mHideOnContentScroll || this.mAnimatingForFling) {
            return;
        }
        if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
            haltActionBarHideOffsetAnimations();
            postDelayed(this.mRemoveActionBarHideOffset, 600L);
        } else {
            haltActionBarHideOffsetAnimations();
            postDelayed(this.mAddActionBarHideOffset, 600L);
        }
    }

    @Override // android.view.View
    @Deprecated
    public final void onWindowSystemUiVisibilityChanged(int i) {
        boolean z;
        super.onWindowSystemUiVisibilityChanged(i);
        pullChildren();
        int i2 = this.mLastSystemUiVisibility ^ i;
        this.mLastSystemUiVisibility = i;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            int i3 = i & 256;
            int i4 = i & 4;
            if (i3 == 0) {
                z = true;
            } else {
                z = false;
            }
            WindowDecorActionBar windowDecorActionBar = (WindowDecorActionBar) actionBarVisibilityCallback;
            windowDecorActionBar.mContentAnimations = z;
            if (i4 != 0 && i3 != 0) {
                if (!windowDecorActionBar.mHiddenBySystem) {
                    windowDecorActionBar.mHiddenBySystem = true;
                    windowDecorActionBar.updateVisibility(true);
                }
            } else if (windowDecorActionBar.mHiddenBySystem) {
                windowDecorActionBar.mHiddenBySystem = false;
                windowDecorActionBar.updateVisibility(true);
            }
        }
        if ((i2 & 256) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.Api20Impl.requestApplyInsets(this);
        }
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mWindowVisibility = i;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            ((WindowDecorActionBar) actionBarVisibilityCallback).mCurWindowVisibility = i;
        }
    }

    final void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer) findViewById(R.id.action_bar_container);
            View findViewById = findViewById(R.id.action_bar);
            if (findViewById instanceof Toolbar) {
                this.mDecorToolbar$ar$class_merging = ((Toolbar) findViewById).getWrapper$ar$class_merging();
                return;
            }
            throw new IllegalStateException("Can't make a decor toolbar out of ".concat(String.valueOf(findViewById.getClass().getSimpleName())));
        }
    }

    public final void setActionBarHideOffset(int i) {
        haltActionBarHideOffsetAnimations();
        this.mActionBarTop.setTranslationY(-Math.max(0, Math.min(i, this.mActionBarTop.getHeight())));
    }

    public final void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if (!z) {
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void setMenu(Menu menu, MenuPresenter.Callback callback) {
        pullChildren();
        this.mDecorToolbar$ar$class_merging.setMenu(menu, callback);
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar$ar$class_merging.setMenuPrepared();
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void setWindowCallback(Window.Callback callback) {
        pullChildren();
        this.mDecorToolbar$ar$class_merging.setWindowCallback(callback);
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final void setWindowTitle(CharSequence charSequence) {
        pullChildren();
        this.mDecorToolbar$ar$class_merging.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.support.v7.widget.DecorContentParent
    public final boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar$ar$class_merging.showOverflowMenu();
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mTmpRect = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        this.mBaseInnerInsets = WindowInsetsCompat.CONSUMED;
        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.CONSUMED;
        this.mLastBaseInnerInsets = windowInsetsCompat;
        this.mInnerInsets = windowInsetsCompat;
        this.mLastInnerInsets = windowInsetsCompat;
        this.mTopAnimatorListener = new AnimatorListenerAdapter() { // from class: android.support.v7.widget.ActionBarOverlayLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = null;
                actionBarOverlayLayout.mAnimatingForFling = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = null;
                actionBarOverlayLayout.mAnimatingForFling = false;
            }
        };
        this.mRemoveActionBarHideOffset = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 12, null);
        this.mAddActionBarHideOffset = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 13, null);
        init(context);
        this.mParentHelper = new NestedScrollingParentHelper();
        NoSystemUiLayoutFlagView noSystemUiLayoutFlagView = new NoSystemUiLayoutFlagView(context);
        this.mNoSystemUiLayoutFlagView = noSystemUiLayoutFlagView;
        addView(noSystemUiLayoutFlagView);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        onNestedScroll(view, i, i2, i3, i4, i5);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onStopNestedScroll(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }
}
