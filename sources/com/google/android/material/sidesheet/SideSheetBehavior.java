package com.google.android.material.sidesheet;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.android.marvin.talkback.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private ColorStateList backgroundTint;
    public final Set callbacks;
    public int childWidth;
    private int coplanarSiblingViewId;
    public WeakReference coplanarSiblingViewRef;
    private final ViewDragHelper.Callback dragCallback;
    public boolean draggable;
    private float elevation;
    public final float hideFriction;
    private boolean ignoreEvents;
    private int initialX;
    public int innerMargin;
    private MaterialShapeDrawable materialShapeDrawable;
    public int parentInnerEdge;
    public int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    public SheetDelegate sheetDelegate;
    public int state;
    private final StateSettlingTracker stateSettlingTracker;
    private VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    public WeakReference viewRef;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StateSettlingTracker {
        private final Runnable continueSettlingRunnable = new FlagStore$$ExternalSyntheticLambda3(this, 11);
        public boolean isContinueSettlingRunnablePosted;
        public int targetState;

        public StateSettlingTracker() {
        }

        public final void continueSettlingToState(int i) {
            WeakReference weakReference = SideSheetBehavior.this.viewRef;
            if (weakReference != null && weakReference.get() != null) {
                this.targetState = i;
                if (!this.isContinueSettlingRunnablePosted) {
                    ((View) SideSheetBehavior.this.viewRef.get()).postOnAnimation(this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    public SideSheetBehavior() {
        this.stateSettlingTracker = new StateSettlingTracker();
        this.draggable = true;
        this.state = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
                return NotificationCompatBuilder$Api26Impl.clamp(i, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical$ar$ds(View view, int i) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewHorizontalDragRange(View view) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return sideSheetBehavior.childWidth + sideSheetBehavior.innerMargin;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                    if (sideSheetBehavior.draggable) {
                        sideSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
                View view2;
                ViewGroup.MarginLayoutParams marginLayoutParams;
                WeakReference weakReference = SideSheetBehavior.this.coplanarSiblingViewRef;
                if (weakReference != null) {
                    view2 = (View) weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                if (!sideSheetBehavior.callbacks.isEmpty()) {
                    sideSheetBehavior.sheetDelegate.calculateSlideOffset(i);
                    Iterator it = sideSheetBehavior.callbacks.iterator();
                    while (it.hasNext()) {
                        ((SheetCallback) it.next()).onSlide$ar$ds$3f3711a9_0();
                    }
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
            
                if (java.lang.Math.abs(r6 - r0.getExpandedOffset()) < java.lang.Math.abs(r6 - r0.sheetDelegate.getHiddenOffset())) goto L18;
             */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
            
                if (r0.sheetDelegate.isReleasedCloseToInnerEdge(r5) == false) goto L18;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onViewReleased(android.view.View r5, float r6, float r7) {
                /*
                    r4 = this;
                    com.google.android.material.sidesheet.SideSheetBehavior r0 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r1 = r1.isExpandingOutwards(r6)
                    r2 = 3
                    if (r1 == 0) goto Lc
                    goto L4d
                Lc:
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r1 = r1.shouldHide(r5, r6)
                    r3 = 5
                    if (r1 == 0) goto L27
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r6 = r1.isSwipeSignificant(r6, r7)
                    if (r6 != 0) goto L25
                    com.google.android.material.sidesheet.SheetDelegate r6 = r0.sheetDelegate
                    boolean r6 = r6.isReleasedCloseToInnerEdge(r5)
                    if (r6 == 0) goto L4d
                L25:
                    r2 = r3
                    goto L4d
                L27:
                    r1 = 0
                    int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                    if (r1 == 0) goto L32
                    boolean r6 = com.google.android.material.shape.EdgeTreatment.isSwipeMostlyHorizontal(r6, r7)
                    if (r6 != 0) goto L25
                L32:
                    int r6 = r5.getLeft()
                    int r7 = r0.getExpandedOffset()
                    int r7 = r6 - r7
                    int r7 = java.lang.Math.abs(r7)
                    com.google.android.material.sidesheet.SheetDelegate r0 = r0.sheetDelegate
                    int r0 = r0.getHiddenOffset()
                    int r6 = r6 - r0
                    int r6 = java.lang.Math.abs(r6)
                    if (r7 >= r6) goto L25
                L4d:
                    com.google.android.material.sidesheet.SideSheetBehavior r6 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    r7 = 1
                    r6.startSettling(r5, r2, r7)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                WeakReference weakReference;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                if (sideSheetBehavior.state == 1 || (weakReference = sideSheetBehavior.viewRef) == null || weakReference.get() != view) {
                    return false;
                }
                return true;
            }
        };
    }

    private final CoordinatorLayout.LayoutParams getViewLayoutParams() {
        View view;
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null && (view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        }
        return null;
    }

    private final void replaceAccessibilityActionForState(View view, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, final int i) {
        ViewCompat.addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, accessibilityActionCompat.mId, null, new AccessibilityViewCommand() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$$ExternalSyntheticLambda1
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view2, AccessibilityViewCommand.CommandArguments commandArguments) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                int i2 = i;
                WeakReference weakReference = sideSheetBehavior.viewRef;
                if (weakReference != null && weakReference.get() != null) {
                    View view3 = (View) sideSheetBehavior.viewRef.get();
                    RatingView$$ExternalSyntheticLambda5 ratingView$$ExternalSyntheticLambda5 = new RatingView$$ExternalSyntheticLambda5(sideSheetBehavior, i2, 4);
                    ViewParent parent = view3.getParent();
                    if (parent != null && parent.isLayoutRequested() && view3.isAttachedToWindow()) {
                        view3.post(ratingView$$ExternalSyntheticLambda5);
                        return true;
                    }
                    ratingView$$ExternalSyntheticLambda5.run();
                    return true;
                }
                sideSheetBehavior.setStateInternal(i2);
                return true;
            }
        }, accessibilityActionCompat.mViewCommandArgumentClass));
    }

    private final void resetVelocity() {
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
        WeakReference weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, 1048576);
            if (this.state != 5) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            if (this.state != 3) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    private final void updateMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    private final void updateSheetVisibility(View view) {
        int i;
        if (this.state == 5) {
            i = 4;
        } else {
            i = 0;
        }
        if (view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }

    public final int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
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
        ViewDragHelper viewDragHelper;
        if ((view.isShown() || ViewCompat.getAccessibilityPaneTitle(view) != null) && this.draggable) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                resetVelocity();
                actionMasked = 0;
            }
            if (this.velocityTracker == null) {
                this.velocityTracker = VelocityTracker.obtain();
            }
            this.velocityTracker.addMovement(motionEvent);
            if (actionMasked != 0) {
                if ((actionMasked == 1 || actionMasked == 3) && this.ignoreEvents) {
                    this.ignoreEvents = false;
                    return false;
                }
            } else {
                this.initialX = (int) motionEvent.getX();
            }
            if (!this.ignoreEvents && (viewDragHelper = this.viewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                return true;
            }
            return false;
        }
        this.ignoreEvents = true;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        int i2;
        CoordinatorLayout.LayoutParams viewLayoutParams;
        CoordinatorLayout.LayoutParams viewLayoutParams2;
        int i3;
        int i4;
        View findViewById;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.viewRef = new WeakReference(view);
            new MaterialSideContainerBackHelper(view);
            MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
            if (materialShapeDrawable != null) {
                view.setBackground(materialShapeDrawable);
                MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
                float f = this.elevation;
                if (f == -1.0f) {
                    f = ViewCompat.Api21Impl.getElevation(view);
                }
                materialShapeDrawable2.setElevation(f);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    ViewCompat.Api21Impl.setBackgroundTintList(view, colorStateList);
                }
            }
            updateSheetVisibility(view);
            updateAccessibilityActions();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
            if (ViewCompat.getAccessibilityPaneTitle(view) == null) {
                ViewCompat.setAccessibilityPaneTitle(view, view.getResources().getString(R.string.side_sheet_accessibility_pane_title));
            }
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) view.getLayoutParams()).gravity, i);
        int i5 = 0;
        if (absoluteGravity != 3) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        SheetDelegate sheetDelegate = this.sheetDelegate;
        if (sheetDelegate == null || sheetDelegate.getSheetEdge() != i2) {
            if (absoluteGravity != 3) {
                this.sheetDelegate = new RightSheetDelegate(this);
                if (this.shapeAppearanceModel != null && ((viewLayoutParams2 = getViewLayoutParams()) == null || viewLayoutParams2.rightMargin <= 0)) {
                    ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(this.shapeAppearanceModel);
                    builder.setTopRightCornerSize$ar$ds(0.0f);
                    builder.setBottomRightCornerSize$ar$ds(0.0f);
                    updateMaterialShapeDrawable(new ShapeAppearanceModel(builder));
                }
            } else {
                this.sheetDelegate = new LeftSheetDelegate(this);
                if (this.shapeAppearanceModel != null && ((viewLayoutParams = getViewLayoutParams()) == null || viewLayoutParams.leftMargin <= 0)) {
                    ShapeAppearanceModel.Builder builder2 = new ShapeAppearanceModel.Builder(this.shapeAppearanceModel);
                    builder2.setTopLeftCornerSize$ar$ds(0.0f);
                    builder2.setBottomLeftCornerSize$ar$ds(0.0f);
                    updateMaterialShapeDrawable(new ShapeAppearanceModel(builder2));
                }
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        int outerEdge = this.sheetDelegate.getOuterEdge(view);
        coordinatorLayout.onLayoutChild(view, i);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout);
        this.childWidth = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams != null) {
            i3 = this.sheetDelegate.calculateInnerMargin(marginLayoutParams);
        } else {
            i3 = 0;
        }
        this.innerMargin = i3;
        int i6 = this.state;
        if (i6 != 1 && i6 != 2) {
            if (i6 != 3) {
                if (i6 == 5) {
                    i5 = this.sheetDelegate.getHiddenOffset();
                } else {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i6, "Unexpected value: "));
                }
            }
        } else {
            i5 = outerEdge - this.sheetDelegate.getOuterEdge(view);
        }
        int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
        view.offsetLeftAndRight(i5);
        if (this.coplanarSiblingViewRef == null && (i4 = this.coplanarSiblingViewId) != -1 && (findViewById = coordinatorLayout.findViewById(i4)) != null) {
            this.coplanarSiblingViewRef = new WeakReference(findViewById);
        }
        for (SheetCallback sheetCallback : this.callbacks) {
            if (sheetCallback instanceof SideSheetCallback) {
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        int i = ((SavedState) parcelable).state;
        if (i == 1 || i == 2) {
            i = 5;
        }
        this.state = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
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
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents && shouldHandleDraggingWithHelper()) {
            float abs = Math.abs(this.initialX - motionEvent.getX());
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

    public final void setStateInternal(int i) {
        View view;
        if (this.state != i) {
            this.state = i;
            WeakReference weakReference = this.viewRef;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                updateSheetVisibility(view);
                Iterator it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((SheetCallback) it.next()).onStateChanged$ar$ds$e8571560_1();
                }
                updateAccessibilityActions();
            }
        }
    }

    public final void startSettling(View view, int i, boolean z) {
        int expandedOffset;
        if (i != 3) {
            if (i == 5) {
                expandedOffset = this.sheetDelegate.getHiddenOffset();
            } else {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Invalid state to get outer edge offset: "));
            }
        } else {
            expandedOffset = getExpandedOffset();
        }
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null && (!z ? viewDragHelper.smoothSlideViewTo(view, expandedOffset, view.getTop()) : viewDragHelper.settleCapturedViewAt(expandedOffset, view.getTop()))) {
            setStateInternal(2);
            this.stateSettlingTracker.continueSettlingToState(i);
        } else {
            setStateInternal(i);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BottomSheetBehavior.SavedState.AnonymousClass1(6);
        final int state;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcelable parcelable, SideSheetBehavior sideSheetBehavior) {
            super(parcelable);
            this.state = sideSheetBehavior.state;
        }
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stateSettlingTracker = new StateSettlingTracker();
        this.draggable = true;
        this.state = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
                return NotificationCompatBuilder$Api26Impl.clamp(i, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical$ar$ds(View view, int i) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewHorizontalDragRange(View view) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return sideSheetBehavior.childWidth + sideSheetBehavior.innerMargin;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                    if (sideSheetBehavior.draggable) {
                        sideSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
                View view2;
                ViewGroup.MarginLayoutParams marginLayoutParams;
                WeakReference weakReference = SideSheetBehavior.this.coplanarSiblingViewRef;
                if (weakReference != null) {
                    view2 = (View) weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                if (!sideSheetBehavior.callbacks.isEmpty()) {
                    sideSheetBehavior.sheetDelegate.calculateSlideOffset(i);
                    Iterator it = sideSheetBehavior.callbacks.iterator();
                    while (it.hasNext()) {
                        ((SheetCallback) it.next()).onSlide$ar$ds$3f3711a9_0();
                    }
                }
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
                    this = this;
                    com.google.android.material.sidesheet.SideSheetBehavior r0 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r1 = r1.isExpandingOutwards(r6)
                    r2 = 3
                    if (r1 == 0) goto Lc
                    goto L4d
                Lc:
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r1 = r1.shouldHide(r5, r6)
                    r3 = 5
                    if (r1 == 0) goto L27
                    com.google.android.material.sidesheet.SheetDelegate r1 = r0.sheetDelegate
                    boolean r6 = r1.isSwipeSignificant(r6, r7)
                    if (r6 != 0) goto L25
                    com.google.android.material.sidesheet.SheetDelegate r6 = r0.sheetDelegate
                    boolean r6 = r6.isReleasedCloseToInnerEdge(r5)
                    if (r6 == 0) goto L4d
                L25:
                    r2 = r3
                    goto L4d
                L27:
                    r1 = 0
                    int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                    if (r1 == 0) goto L32
                    boolean r6 = com.google.android.material.shape.EdgeTreatment.isSwipeMostlyHorizontal(r6, r7)
                    if (r6 != 0) goto L25
                L32:
                    int r6 = r5.getLeft()
                    int r7 = r0.getExpandedOffset()
                    int r7 = r6 - r7
                    int r7 = java.lang.Math.abs(r7)
                    com.google.android.material.sidesheet.SheetDelegate r0 = r0.sheetDelegate
                    int r0 = r0.getHiddenOffset()
                    int r6 = r6 - r0
                    int r6 = java.lang.Math.abs(r6)
                    if (r7 >= r6) goto L25
                L4d:
                    com.google.android.material.sidesheet.SideSheetBehavior r6 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    r7 = 1
                    r6.startSettling(r5, r2, r7)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                WeakReference weakReference;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                if (sideSheetBehavior.state == 1 || (weakReference = sideSheetBehavior.viewRef) == null || weakReference.get() != view) {
                    return false;
                }
                return true;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SideSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(3)) {
            this.backgroundTint = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.shapeAppearanceModel = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, attributeSet, 0, R.style.Widget_Material3_SideSheet));
        }
        if (obtainStyledAttributes.hasValue(5)) {
            int resourceId = obtainStyledAttributes.getResourceId(5, -1);
            this.coplanarSiblingViewId = resourceId;
            WeakReference weakReference = this.coplanarSiblingViewRef;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.coplanarSiblingViewRef = null;
            WeakReference weakReference2 = this.viewRef;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1 && view.isLaidOut()) {
                    view.requestLayout();
                }
            }
        }
        if (this.shapeAppearanceModel != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
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
        this.elevation = obtainStyledAttributes.getDimension(2, -1.0f);
        this.draggable = obtainStyledAttributes.getBoolean(4, true);
        obtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
