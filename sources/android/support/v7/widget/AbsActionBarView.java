package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbsActionBarView extends ViewGroup {
    protected ActionMenuPresenter mActionMenuPresenter;
    public int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final VisibilityAnimListener mVisAnimListener;
    protected AccessibilityNodeInfoCompat.CollectionItemInfoCompat mVisibilityAnim$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        private boolean mCanceled = false;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationCancel$ar$ds() {
            this.mCanceled = true;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationEnd$ar$ds() {
            if (this.mCanceled) {
                return;
            }
            AbsActionBarView absActionBarView = AbsActionBarView.this;
            absActionBarView.mVisibilityAnim$ar$class_merging$ar$class_merging = null;
            AbsActionBarView.super.setVisibility(this.mFinalVisibility);
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public final void onAnimationStart$ar$ds() {
            AbsActionBarView.super.setVisibility(0);
            this.mCanceled = false;
        }

        public final void withFinalVisibility$ar$ds$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, int i) {
            AbsActionBarView.this.mVisibilityAnim$ar$class_merging$ar$class_merging = collectionItemInfoCompat;
            this.mFinalVisibility = i;
        }
    }

    AbsActionBarView(Context context) {
        this(context, null);
    }

    public static final int measureChildView$ar$ds(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, i - view.getMeasuredWidth());
    }

    public static int next(int i, int i2, boolean z) {
        if (z) {
            return i - i2;
        }
        return i + i2;
    }

    public static final int positionChild$ar$ds(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        int i5 = measuredHeight + i4;
        if (z) {
            view.layout(i - measuredWidth, i4, i, i5);
            return -measuredWidth;
        }
        view.layout(i, i4, i + measuredWidth, i5);
        return measuredWidth;
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(13, 0));
        obtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.mMaxItems = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(actionMenuPresenter.mContext).getMaxActionButtons();
            MenuBuilder menuBuilder = actionMenuPresenter.mMenu;
            if (menuBuilder != null) {
                menuBuilder.onItemsChanged(true);
            }
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
            actionMasked = 9;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
            actionMasked = 0;
        }
        if (!this.mEatingTouch) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        throw null;
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        if (i != getVisibility()) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = this.mVisibilityAnim$ar$class_merging$ar$class_merging;
            if (collectionItemInfoCompat != null) {
                collectionItemInfoCompat.cancel();
            }
            super.setVisibility(i);
        }
    }

    public final AccessibilityNodeInfoCompat.CollectionItemInfoCompat setupAnimatorToVisibility$ar$class_merging$ar$class_merging(int i, long j) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = this.mVisibilityAnim$ar$class_merging$ar$class_merging;
        if (collectionItemInfoCompat != null) {
            collectionItemInfoCompat.cancel();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging = ViewCompat.animate$ar$class_merging$ar$class_merging(this);
            animate$ar$class_merging$ar$class_merging.alpha$ar$ds(1.0f);
            animate$ar$class_merging$ar$class_merging.setDuration$ar$ds$d0f32809_0(j);
            VisibilityAnimListener visibilityAnimListener = this.mVisAnimListener;
            visibilityAnimListener.withFinalVisibility$ar$ds$ar$class_merging$ar$class_merging(animate$ar$class_merging$ar$class_merging, 0);
            animate$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(visibilityAnimListener);
            return animate$ar$class_merging$ar$class_merging;
        }
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging2 = ViewCompat.animate$ar$class_merging$ar$class_merging(this);
        animate$ar$class_merging$ar$class_merging2.alpha$ar$ds(0.0f);
        animate$ar$class_merging$ar$class_merging2.setDuration$ar$ds$d0f32809_0(j);
        VisibilityAnimListener visibilityAnimListener2 = this.mVisAnimListener;
        visibilityAnimListener2.withFinalVisibility$ar$ds$ar$class_merging$ar$class_merging(animate$ar$class_merging$ar$class_merging2, i);
        animate$ar$class_merging$ar$class_merging2.setListener$ar$ds$34caea9b_0(visibilityAnimListener2);
        return animate$ar$class_merging$ar$class_merging2;
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVisAnimListener = new VisibilityAnimListener();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.mPopupContext = context;
        } else {
            this.mPopupContext = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
