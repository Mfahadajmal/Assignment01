package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.R$styleable;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GapWorker;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.collection.SimpleArrayMap;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.PaintCompat;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat$Api26Impl;
import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.NestedScrollView;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import androidx.preference.Preference;
import androidx.viewpager.widget.ViewPager;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import j$.util.DesugarCollections;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements NestedScrollingChild {
    private static final Class[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    public static final /* synthetic */ int RecyclerView$ar$NoOp = 0;
    static final StretchEdgeEffectFactory sDefaultEdgeEffectFactory;
    static final Interpolator sQuinticInterpolator;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    public Adapter mAdapter;
    OverlayDisplay mAdapterHelper$ar$class_merging;
    boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    public ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    private final DifferentialMotionFlingTarget mDifferentialMotionFlingTarget;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private WindowCallbackWrapper.Api26Impl mEdgeEffectFactory$ar$class_merging;
    boolean mEnableFastScroller;
    public boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    public OnItemTouchListener mInterceptingOnItemTouchListener;
    public boolean mIsAttached;
    public ItemAnimator mItemAnimator;
    private FloatingActionButton.ShadowDelegateImpl mItemAnimatorListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private Runnable mItemAnimatorRunner;
    final ArrayList mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    private int mLastAutoMeasureNonExactMeasuredHeight;
    private int mLastAutoMeasureNonExactMeasuredWidth;
    private boolean mLastAutoMeasureSkippedDueToExact;
    private int mLastTouchX;
    private int mLastTouchY;
    public LayoutManager mLayout;
    private int mLayoutOrScrollCounter;
    public boolean mLayoutSuppressed;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    boolean mLowResRotaryEncoderFeature;
    private final int mMaxFlingVelocity;
    public final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    public OnFlingListener mOnFlingListener;
    public final ArrayList mOnItemTouchListeners;
    final List mPendingAccessibilityImportanceChange;
    SavedState mPendingSavedState;
    private final float mPhysicalCoef;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    final List mRecyclerListeners;
    final int[] mReusableIntPair;
    public EdgeEffect mRightGlow;
    public float mScaledHorizontalScrollFactor;
    public float mScaledVerticalScrollFactor;
    private List mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final FloatingActionButton.ShadowDelegateImpl mViewInfoProcessCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    final NodeBrailler mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    private static final float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    static final boolean ALLOW_THREAD_GAP_WORK = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Adapter {
        public final AdapterDataObservable mObservable = new AdapterDataObservable();
        public boolean mHasStableIds = false;
        public final int mStateRestorationPolicy$ar$edu = 1;

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1L;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public abstract void onBindViewHolder(ViewHolder viewHolder, int i);

        public abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i);

        public final void registerAdapterDataObserver$ar$class_merging(WindowCallbackWrapper.Api24Impl api24Impl) {
            this.mObservable.registerObserver(api24Impl);
        }

        public final void setHasStableIds(boolean z) {
            if (!this.mObservable.hasObservers()) {
                this.mHasStableIds = z;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public final void unregisterAdapterDataObserver$ar$class_merging(WindowCallbackWrapper.Api24Impl api24Impl) {
            this.mObservable.unregisterObserver(api24Impl);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AdapterDataObservable extends Observable {
        public final boolean hasObservers() {
            if (!this.mObservers.isEmpty()) {
                return true;
            }
            return false;
        }

        public final void notifyChanged() {
            int size = this.mObservers.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((WindowCallbackWrapper.Api24Impl) this.mObservers.get(size)).onChanged();
                } else {
                    return;
                }
            }
        }

        public final void notifyItemMoved(int i, int i2) {
            int size = this.mObservers.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((WindowCallbackWrapper.Api24Impl) this.mObservers.get(size)).onItemRangeMoved$ar$ds(i, i2);
                } else {
                    return;
                }
            }
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            int size = this.mObservers.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((WindowCallbackWrapper.Api24Impl) this.mObservers.get(size)).onItemRangeChanged(i, i2, obj);
                } else {
                    return;
                }
            }
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            int size = this.mObservers.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((WindowCallbackWrapper.Api24Impl) this.mObservers.get(size)).onItemRangeInserted(i, i2);
                } else {
                    return;
                }
            }
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            int size = this.mObservers.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((WindowCallbackWrapper.Api24Impl) this.mObservers.get(size)).onItemRangeRemoved(i, i2);
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api35Impl {
        public static void setFrameContentVelocity(View view, float f) {
            view.setFrameContentVelocity(f);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ItemAnimator {
        public FloatingActionButton.ShadowDelegateImpl mListener$ar$class_merging$4809de85_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        private final ArrayList mFinishedListeners = new ArrayList();
        public final long mAddDuration = 120;
        public final long mRemoveDuration = 120;
        public final long mMoveDuration = 250;
        public final long mChangeDuration = 250;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i = viewHolder.mFlags;
            int i2 = i & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i & 4) == 0) {
                int i3 = viewHolder.mOldPosition;
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                if (i3 != -1 && absoluteAdapterPosition != -1 && i3 != absoluteAdapterPosition) {
                    return i2 | 2048;
                }
            }
            return i2;
        }

        public static final NestedScrollingParentHelper recordPreLayoutInformation$ar$ds$ar$class_merging(ViewHolder viewHolder) {
            return new NestedScrollingParentHelper().setFrom$ar$class_merging(viewHolder);
        }

        public abstract boolean animateChange$ar$class_merging(ViewHolder viewHolder, ViewHolder viewHolder2, NestedScrollingParentHelper nestedScrollingParentHelper, NestedScrollingParentHelper nestedScrollingParentHelper2);

        /* JADX WARN: Removed duplicated region for block: B:16:0x006c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void dispatchAnimationFinished(android.support.v7.widget.RecyclerView.ViewHolder r9) {
            /*
                r8 = this;
                com.google.android.material.floatingactionbutton.FloatingActionButton$ShadowDelegateImpl r0 = r8.mListener$ar$class_merging$4809de85_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
                if (r0 == 0) goto L9d
                r1 = 1
                r9.setIsRecyclable(r1)
                android.support.v7.widget.RecyclerView$ViewHolder r2 = r9.mShadowedHolder
                r3 = 0
                if (r2 == 0) goto L13
                android.support.v7.widget.RecyclerView$ViewHolder r2 = r9.mShadowingHolder
                if (r2 != 0) goto L13
                r9.mShadowedHolder = r3
            L13:
                r9.mShadowingHolder = r3
                int r2 = r9.mFlags
                r2 = r2 & 16
                if (r2 == 0) goto L1d
                goto L9d
            L1d:
                java.lang.Object r2 = r0.FloatingActionButton$ShadowDelegateImpl$ar$this$0
                android.view.View r3 = r9.itemView
                android.support.v7.widget.RecyclerView r2 = (android.support.v7.widget.RecyclerView) r2
                r2.startInterceptRequestLayout()
                android.support.v7.widget.ChildHelper r4 = r2.mChildHelper
                int r5 = r4.mRemoveStatus
                r6 = 0
                if (r5 != r1) goto L3b
                android.view.View r1 = r4.mViewInRemoveView
                if (r1 != r3) goto L33
            L31:
                r1 = r6
                goto L6a
            L33:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "Cannot call removeViewIfHidden within removeView(At) for a different view"
                r9.<init>(r0)
                throw r9
            L3b:
                r7 = 2
                if (r5 == r7) goto L95
                r4.mRemoveStatus = r7     // Catch: java.lang.Throwable -> L91
                com.google.android.material.floatingactionbutton.FloatingActionButton$ShadowDelegateImpl r5 = r4.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> L91
                int r5 = r5.indexOfChild(r3)     // Catch: java.lang.Throwable -> L91
                r7 = -1
                if (r5 != r7) goto L4f
                r4.unhideViewInternal$ar$ds(r3)     // Catch: java.lang.Throwable -> L91
                r4.mRemoveStatus = r6
                goto L6a
            L4f:
                android.support.v7.widget.ChildHelper$Bucket r7 = r4.mBucket     // Catch: java.lang.Throwable -> L91
                boolean r7 = r7.get(r5)     // Catch: java.lang.Throwable -> L91
                if (r7 == 0) goto L67
                android.support.v7.widget.ChildHelper$Bucket r7 = r4.mBucket     // Catch: java.lang.Throwable -> L91
                r7.remove(r5)     // Catch: java.lang.Throwable -> L91
                r4.unhideViewInternal$ar$ds(r3)     // Catch: java.lang.Throwable -> L91
                com.google.android.material.floatingactionbutton.FloatingActionButton$ShadowDelegateImpl r7 = r4.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> L91
                r7.removeViewAt(r5)     // Catch: java.lang.Throwable -> L91
                r4.mRemoveStatus = r6
                goto L6a
            L67:
                r4.mRemoveStatus = r6
                goto L31
            L6a:
                if (r1 == 0) goto L7a
                android.support.v7.widget.RecyclerView$ViewHolder r3 = android.support.v7.widget.RecyclerView.getChildViewHolderInt(r3)
                android.support.v7.widget.RecyclerView$Recycler r4 = r2.mRecycler
                r4.unscrapView(r3)
                android.support.v7.widget.RecyclerView$Recycler r4 = r2.mRecycler
                r4.recycleViewHolderInternal(r3)
            L7a:
                r3 = r1 ^ 1
                r2.stopInterceptRequestLayout(r3)
                if (r1 != 0) goto L9d
                boolean r1 = r9.isTmpDetached()
                if (r1 == 0) goto L9d
                java.lang.Object r0 = r0.FloatingActionButton$ShadowDelegateImpl$ar$this$0
                android.view.View r9 = r9.itemView
                android.support.v7.widget.RecyclerView r0 = (android.support.v7.widget.RecyclerView) r0
                r0.removeDetachedView(r9, r6)
                return
            L91:
                r9 = move-exception
                r4.mRemoveStatus = r6
                throw r9
            L95:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "Cannot call removeViewIfHidden within removeViewIfHidden"
                r9.<init>(r0)
                throw r9
            L9d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.ItemAnimator.dispatchAnimationFinished(android.support.v7.widget.RecyclerView$ViewHolder):void");
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i = 0; i < size; i++) {
                ((ItemAnimatorFinishedListener) this.mFinishedListeners.get(i)).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewHolder);

        public abstract void endAnimations();

        public abstract boolean isRunning();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class LayoutManager {
        ChildHelper mChildHelper;
        public int mHeight;
        public int mHeightMode;
        final NodeBrailler mHorizontalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging;
        private final ViewBoundsCheck$Callback mHorizontalBoundCheckCallback;
        boolean mIsAttachedToWindow;
        public final boolean mItemPrefetchEnabled;
        public final boolean mMeasurementCacheEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        SmoothScroller mSmoothScroller;
        final NodeBrailler mVerticalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging;
        private final ViewBoundsCheck$Callback mVerticalBoundCheckCallback;
        public int mWidth;
        public int mWidthMode;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager$2 */
        /* loaded from: classes.dex */
        public final class AnonymousClass2 implements ViewBoundsCheck$Callback {
            private final /* synthetic */ int switching_field;
            final /* synthetic */ LayoutManager this$0;

            public AnonymousClass2(LayoutManager layoutManager, int i) {
                r2 = i;
                this.this$0 = layoutManager;
            }

            @Override // android.support.v7.widget.ViewBoundsCheck$Callback
            public final View getChildAt(int i) {
                if (r2 != 0) {
                    return this.this$0.getChildAt(i);
                }
                return this.this$0.getChildAt(i);
            }

            @Override // android.support.v7.widget.ViewBoundsCheck$Callback
            public final int getChildEnd(View view) {
                int decoratedBottom;
                int i;
                if (r2 != 0) {
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    decoratedBottom = this.this$0.getDecoratedRight(view);
                    i = layoutParams.rightMargin;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                    decoratedBottom = this.this$0.getDecoratedBottom(view);
                    i = layoutParams2.bottomMargin;
                }
                return decoratedBottom + i;
            }

            @Override // android.support.v7.widget.ViewBoundsCheck$Callback
            public final int getChildStart(View view) {
                int decoratedTop;
                int i;
                if (r2 != 0) {
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    decoratedTop = this.this$0.getDecoratedLeft(view);
                    i = layoutParams.leftMargin;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                    decoratedTop = this.this$0.getDecoratedTop(view);
                    i = layoutParams2.topMargin;
                }
                return decoratedTop - i;
            }

            @Override // android.support.v7.widget.ViewBoundsCheck$Callback
            public final int getParentEnd() {
                int i;
                int paddingBottom;
                if (r2 != 0) {
                    LayoutManager layoutManager = this.this$0;
                    i = layoutManager.mWidth;
                    paddingBottom = layoutManager.getPaddingRight();
                } else {
                    LayoutManager layoutManager2 = this.this$0;
                    i = layoutManager2.mHeight;
                    paddingBottom = layoutManager2.getPaddingBottom();
                }
                return i - paddingBottom;
            }

            @Override // android.support.v7.widget.ViewBoundsCheck$Callback
            public final int getParentStart() {
                if (r2 != 0) {
                    return this.this$0.getPaddingLeft();
                }
                return this.this$0.getPaddingTop();
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            AnonymousClass2 anonymousClass2 = new ViewBoundsCheck$Callback(this) { // from class: android.support.v7.widget.RecyclerView.LayoutManager.2
                private final /* synthetic */ int switching_field;
                final /* synthetic */ LayoutManager this$0;

                public AnonymousClass2(LayoutManager this, int i) {
                    r2 = i;
                    this.this$0 = this;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final View getChildAt(int i) {
                    if (r2 != 0) {
                        return this.this$0.getChildAt(i);
                    }
                    return this.this$0.getChildAt(i);
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getChildEnd(View view) {
                    int decoratedBottom;
                    int i;
                    if (r2 != 0) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        decoratedBottom = this.this$0.getDecoratedRight(view);
                        i = layoutParams.rightMargin;
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                        decoratedBottom = this.this$0.getDecoratedBottom(view);
                        i = layoutParams2.bottomMargin;
                    }
                    return decoratedBottom + i;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getChildStart(View view) {
                    int decoratedTop;
                    int i;
                    if (r2 != 0) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        decoratedTop = this.this$0.getDecoratedLeft(view);
                        i = layoutParams.leftMargin;
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                        decoratedTop = this.this$0.getDecoratedTop(view);
                        i = layoutParams2.topMargin;
                    }
                    return decoratedTop - i;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getParentEnd() {
                    int i;
                    int paddingBottom;
                    if (r2 != 0) {
                        LayoutManager layoutManager = this.this$0;
                        i = layoutManager.mWidth;
                        paddingBottom = layoutManager.getPaddingRight();
                    } else {
                        LayoutManager layoutManager2 = this.this$0;
                        i = layoutManager2.mHeight;
                        paddingBottom = layoutManager2.getPaddingBottom();
                    }
                    return i - paddingBottom;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getParentStart() {
                    if (r2 != 0) {
                        return this.this$0.getPaddingLeft();
                    }
                    return this.this$0.getPaddingTop();
                }
            };
            this.mHorizontalBoundCheckCallback = anonymousClass2;
            AnonymousClass2 anonymousClass22 = new ViewBoundsCheck$Callback(this) { // from class: android.support.v7.widget.RecyclerView.LayoutManager.2
                private final /* synthetic */ int switching_field;
                final /* synthetic */ LayoutManager this$0;

                public AnonymousClass2(LayoutManager this, int i) {
                    r2 = i;
                    this.this$0 = this;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final View getChildAt(int i) {
                    if (r2 != 0) {
                        return this.this$0.getChildAt(i);
                    }
                    return this.this$0.getChildAt(i);
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getChildEnd(View view) {
                    int decoratedBottom;
                    int i;
                    if (r2 != 0) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        decoratedBottom = this.this$0.getDecoratedRight(view);
                        i = layoutParams.rightMargin;
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                        decoratedBottom = this.this$0.getDecoratedBottom(view);
                        i = layoutParams2.bottomMargin;
                    }
                    return decoratedBottom + i;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getChildStart(View view) {
                    int decoratedTop;
                    int i;
                    if (r2 != 0) {
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        decoratedTop = this.this$0.getDecoratedLeft(view);
                        i = layoutParams.leftMargin;
                    } else {
                        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                        decoratedTop = this.this$0.getDecoratedTop(view);
                        i = layoutParams2.topMargin;
                    }
                    return decoratedTop - i;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getParentEnd() {
                    int i;
                    int paddingBottom;
                    if (r2 != 0) {
                        LayoutManager layoutManager = this.this$0;
                        i = layoutManager.mWidth;
                        paddingBottom = layoutManager.getPaddingRight();
                    } else {
                        LayoutManager layoutManager2 = this.this$0;
                        i = layoutManager2.mHeight;
                        paddingBottom = layoutManager2.getPaddingBottom();
                    }
                    return i - paddingBottom;
                }

                @Override // android.support.v7.widget.ViewBoundsCheck$Callback
                public final int getParentStart() {
                    if (r2 != 0) {
                        return this.this$0.getPaddingLeft();
                    }
                    return this.this$0.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = anonymousClass22;
            this.mHorizontalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging = new NodeBrailler(anonymousClass2);
            this.mVerticalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging = new NodeBrailler(anonymousClass22);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        private final void addViewInt(View view, int i, boolean z) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!z && !childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.removeFromDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (!childViewHolderInt.wasReturnedFromScrap() && !childViewHolderInt.isScrap()) {
                if (view.getParent() == this.mRecyclerView) {
                    int indexOfChild = this.mChildHelper.indexOfChild(view);
                    if (i == -1) {
                        i = this.mChildHelper.getChildCount();
                    }
                    if (indexOfChild != -1) {
                        if (indexOfChild != i) {
                            LayoutManager layoutManager = this.mRecyclerView.mLayout;
                            View childAt = layoutManager.getChildAt(indexOfChild);
                            if (childAt != null) {
                                layoutManager.detachViewAt(indexOfChild);
                                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                                ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(childAt);
                                if (childViewHolderInt2.isRemoved()) {
                                    layoutManager.mRecyclerView.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToDisappearedInLayout(childViewHolderInt2);
                                } else {
                                    layoutManager.mRecyclerView.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.removeFromDisappearedInLayout(childViewHolderInt2);
                                }
                                layoutManager.mChildHelper.attachViewToParent(childAt, i, layoutParams2, childViewHolderInt2.isRemoved());
                            } else {
                                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + indexOfChild + layoutManager.mRecyclerView.toString());
                            }
                        }
                    } else {
                        throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view) + this.mRecyclerView.exceptionLabel());
                    }
                } else {
                    this.mChildHelper.addView(view, i, false);
                    layoutParams.mInsetsDirty = true;
                    SmoothScroller smoothScroller = this.mSmoothScroller;
                    if (smoothScroller != null && smoothScroller.mRunning && smoothScroller.getChildPosition(view) == smoothScroller.mTargetPosition) {
                        smoothScroller.mTargetView = view;
                    }
                }
            } else {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode != Integer.MIN_VALUE) {
                if (mode != 1073741824) {
                    return Math.max(i2, i3);
                }
                return size;
            }
            return Math.min(size, Math.max(i2, i3));
        }

        public static final int getBottomDecorationHeight$ar$ds(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
        
            if (r4 == 1073741824) goto L39;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getChildMeasureSpec(int r3, int r4, int r5, int r6, boolean r7) {
            /*
                int r3 = r3 - r5
                r5 = 0
                int r3 = java.lang.Math.max(r5, r3)
                r0 = -1
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = 1073741824(0x40000000, float:2.0)
                if (r7 == 0) goto L19
                if (r6 < 0) goto L10
                goto L1b
            L10:
                if (r6 != r0) goto L2f
                if (r4 == r1) goto L1f
                if (r4 == 0) goto L2f
                if (r4 == r2) goto L1f
                goto L2f
            L19:
                if (r6 < 0) goto L1d
            L1b:
                r4 = r2
                goto L31
            L1d:
                if (r6 != r0) goto L21
            L1f:
                r6 = r3
                goto L31
            L21:
                r7 = -2
                if (r6 != r7) goto L2f
                if (r4 == r1) goto L2c
                if (r4 != r2) goto L29
                goto L2c
            L29:
                r6 = r3
                r4 = r5
                goto L31
            L2c:
                r6 = r3
                r4 = r1
                goto L31
            L2f:
                r4 = r5
                r6 = r4
            L31:
                int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r4)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, int, boolean):int");
        }

        public static final int getDecoratedMeasuredHeight$ar$ds(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public static final int getDecoratedMeasuredWidth$ar$ds(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public static final int getLeftDecorationWidth$ar$ds(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        public static final int getPosition$ar$ds(View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(0, 1);
            properties.spanCount = obtainStyledAttributes.getInt(10, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(9, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(11, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        public static final int getRightDecorationWidth$ar$ds(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public static final int getTopDecorationHeight$ar$ds(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public static final void layoutDecoratedWithMargins$ar$ds(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i + rect.left + layoutParams.leftMargin, i2 + rect.top + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
        }

        public final void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public final void addView(View view) {
            addView(view, -1);
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public final void calculateItemDecorationsForChild(View view, Rect rect) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public boolean canScrollHorizontally() {
            throw null;
        }

        public boolean canScrollVertically() {
            throw null;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            if (layoutParams != null) {
                return true;
            }
            return false;
        }

        public int computeHorizontalScrollExtent(State state) {
            throw null;
        }

        public int computeHorizontalScrollOffset(State state) {
            throw null;
        }

        public int computeHorizontalScrollRange(State state) {
            throw null;
        }

        public int computeVerticalScrollExtent(State state) {
            throw null;
        }

        public int computeVerticalScrollOffset(State state) {
            throw null;
        }

        public int computeVerticalScrollRange(State state) {
            throw null;
        }

        public final void detachAndScrapAttachedViews(Recycler recycler) {
            int childCount = getChildCount();
            while (true) {
                childCount--;
                if (childCount >= 0) {
                    View childAt = getChildAt(childCount);
                    ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                    if (!childViewHolderInt.shouldIgnore()) {
                        if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.mHasStableIds) {
                            removeViewAt(childCount);
                            recycler.recycleViewHolderInternal(childViewHolderInt);
                        } else {
                            detachViewAt(childCount);
                            recycler.scrapView(childAt);
                            this.mRecyclerView.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.removeFromDisappearedInLayout(childViewHolderInt);
                        }
                    }
                } else {
                    return;
                }
            }
        }

        public final void detachViewAt(int i) {
            getChildAt(i);
            this.mChildHelper.detachViewFromParent(i);
        }

        final void dispatchAttachedToWindow(RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerView);
        }

        final void dispatchDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow$ar$ds(recyclerView);
        }

        public final View findContainingItemView(View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.isHidden(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public final View getChildAt(int i) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildAt(i);
            }
            return null;
        }

        public final int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && recyclerView.mAdapter != null && canScrollHorizontally()) {
                return this.mRecyclerView.mAdapter.getItemCount();
            }
            return 1;
        }

        public final int getDecoratedBottom(View view) {
            return view.getBottom() + getBottomDecorationHeight$ar$ds(view);
        }

        public final int getDecoratedLeft(View view) {
            return view.getLeft() - getLeftDecorationWidth$ar$ds(view);
        }

        public final int getDecoratedRight(View view) {
            return view.getRight() + getRightDecorationWidth$ar$ds(view);
        }

        public final int getDecoratedTop(View view) {
            return view.getTop() - getTopDecorationHeight$ar$ds(view);
        }

        public final View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.isHidden(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public final int getItemCount() {
            Adapter adapter;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                adapter = recyclerView.mAdapter;
            } else {
                adapter = null;
            }
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public final int getLayoutDirection() {
            return this.mRecyclerView.getLayoutDirection();
        }

        public final int getMinimumHeight() {
            RecyclerView recyclerView = this.mRecyclerView;
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            return recyclerView.getMinimumHeight();
        }

        public final int getMinimumWidth() {
            RecyclerView recyclerView = this.mRecyclerView;
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            return recyclerView.getMinimumWidth();
        }

        public final int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public final int getPaddingEnd() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                return recyclerView.getPaddingEnd();
            }
            return 0;
        }

        public final int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public final int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public final int getPaddingStart() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                return recyclerView.getPaddingStart();
            }
            return 0;
        }

        public final int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && recyclerView.mAdapter != null && canScrollVertically()) {
                return this.mRecyclerView.mAdapter.getItemCount();
            }
            return 1;
        }

        public final void getTransformedBoundingBox$ar$ds(View view, Rect rect) {
            Matrix matrix;
            Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            if (this.mRecyclerView != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean isAutoMeasureEnabled() {
            throw null;
        }

        public boolean isLayoutReversed() {
            return false;
        }

        public final boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            if (smoothScroller != null && smoothScroller.mRunning) {
                return true;
            }
            return false;
        }

        public void offsetChildrenHorizontal(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetLeftAndRight(i);
                }
            }
        }

        public void offsetChildrenVertical(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetTopAndBottom(i);
                }
            }
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            Recycler recycler = recyclerView.mRecycler;
            State state = recyclerView.mState;
            if (recyclerView != null && accessibilityEvent != null) {
                boolean z = true;
                if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                Adapter adapter = this.mRecyclerView.mAdapter;
                if (adapter != null) {
                    accessibilityEvent.setItemCount(adapter.getItemCount());
                }
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
                accessibilityNodeInfoCompat.setGranularScrollingSupported$ar$ds();
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
                accessibilityNodeInfoCompat.setGranularScrollingSupported$ar$ds();
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$ar$class_merging(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), 0));
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(canScrollVertically() ? getPosition$ar$ds(view) : 0, 1, canScrollHorizontally() ? getPosition$ar$ds(view) : 0, 1, false));
        }

        public void onItemsUpdated$ar$ds(RecyclerView recyclerView, int i, int i2) {
            onItemsUpdated$ar$ds$5eb43a0c_0(i);
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            throw null;
        }

        public final void onMeasure$ar$ds(int i, int i2) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
            throw null;
        }

        public Parcelable onSaveInstanceState() {
            throw null;
        }

        public boolean performAccessibilityAction(int i, Bundle bundle) {
            int i2;
            int paddingLeft;
            float f;
            RecyclerView recyclerView = this.mRecyclerView;
            Recycler recycler = recyclerView.mRecycler;
            State state = recyclerView.mState;
            if (recyclerView == null) {
                return false;
            }
            int i3 = this.mHeight;
            int i4 = this.mWidth;
            Rect rect = new Rect();
            if (this.mRecyclerView.getMatrix().isIdentity() && this.mRecyclerView.getGlobalVisibleRect(rect)) {
                i3 = rect.height();
                i4 = rect.width();
            }
            if (i != 4096) {
                if (i != 8192) {
                    i2 = 0;
                    paddingLeft = 0;
                } else {
                    if (this.mRecyclerView.canScrollVertically(-1)) {
                        i2 = -((i3 - getPaddingTop()) - getPaddingBottom());
                    } else {
                        i2 = 0;
                    }
                    if (this.mRecyclerView.canScrollHorizontally(-1)) {
                        paddingLeft = -((i4 - getPaddingLeft()) - getPaddingRight());
                    }
                    paddingLeft = 0;
                }
            } else {
                if (this.mRecyclerView.canScrollVertically(1)) {
                    i2 = (i3 - getPaddingTop()) - getPaddingBottom();
                } else {
                    i2 = 0;
                }
                if (this.mRecyclerView.canScrollHorizontally(1)) {
                    paddingLeft = (i4 - getPaddingLeft()) - getPaddingRight();
                }
                paddingLeft = 0;
            }
            if (i2 == 0) {
                if (paddingLeft == 0) {
                    return false;
                }
                i2 = 0;
            }
            if (bundle != null) {
                f = bundle.getFloat("androidx.core.view.accessibility.action.ARGUMENT_SCROLL_AMOUNT_FLOAT", 1.0f);
                if (f < 0.0f) {
                    return false;
                }
            } else {
                f = 1.0f;
            }
            if (Float.compare(f, Float.POSITIVE_INFINITY) == 0) {
                RecyclerView recyclerView2 = this.mRecyclerView;
                if (recyclerView2.mAdapter == null) {
                    return false;
                }
                if (i != 4096) {
                    if (i == 8192) {
                        recyclerView2.smoothScrollToPosition(0);
                        return true;
                    }
                } else {
                    recyclerView2.smoothScrollToPosition(r0.getItemCount() - 1);
                    return true;
                }
            } else {
                if (Float.compare(1.0f, f) != 0 && Float.compare(0.0f, f) != 0) {
                    i2 = (int) (i2 * f);
                    paddingLeft = (int) (paddingLeft * f);
                }
                this.mRecyclerView.smoothScrollBy$ar$ds$a5e2b4f9_0(paddingLeft, i2, true);
            }
            return true;
        }

        public final void removeAllViews() {
            int childCount = getChildCount();
            while (true) {
                childCount--;
                if (childCount >= 0) {
                    this.mChildHelper.removeViewAt(childCount);
                } else {
                    return;
                }
            }
        }

        public final void removeAndRecycleAllViews(Recycler recycler) {
            int childCount = getChildCount();
            while (true) {
                childCount--;
                if (childCount >= 0) {
                    if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                        removeAndRecycleViewAt(childCount, recycler);
                    }
                } else {
                    return;
                }
            }
        }

        final void removeAndRecycleScrapInt(Recycler recycler) {
            int size = recycler.mAttachedScrap.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = ((ViewHolder) recycler.mAttachedScrap.get(i)).itemView;
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(view, false);
                    }
                    ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(view);
                }
            }
            recycler.mAttachedScrap.clear();
            ArrayList arrayList = recycler.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (size > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public final void removeAndRecycleView(View view, Recycler recycler) {
            ChildHelper childHelper = this.mChildHelper;
            int i = childHelper.mRemoveStatus;
            if (i != 1) {
                if (i != 2) {
                    try {
                        childHelper.mRemoveStatus = 1;
                        childHelper.mViewInRemoveView = view;
                        int indexOfChild = childHelper.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.indexOfChild(view);
                        if (indexOfChild >= 0) {
                            if (childHelper.mBucket.remove(indexOfChild)) {
                                childHelper.unhideViewInternal$ar$ds(view);
                            }
                            childHelper.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.removeViewAt(indexOfChild);
                        }
                        childHelper.mRemoveStatus = 0;
                        childHelper.mViewInRemoveView = null;
                        recycler.recycleView(view);
                        return;
                    } catch (Throwable th) {
                        childHelper.mRemoveStatus = 0;
                        childHelper.mViewInRemoveView = null;
                        throw th;
                    }
                }
                throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
            }
            throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
        }

        public final void removeAndRecycleViewAt(int i, Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public final void removeCallbacks$ar$ds(Runnable runnable) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.removeCallbacks(runnable);
            }
        }

        public final void removeViewAt(int i) {
            if (getChildAt(i) != null) {
                this.mChildHelper.removeViewAt(i);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x00aa, code lost:
        
            if ((r5.bottom - r10) > r2) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x00ae, code lost:
        
            if (r10 != 0) goto L64;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean requestChildRectangleOnScreen(android.support.v7.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
            /*
                r8 = this;
                int r0 = r8.getPaddingLeft()
                int r1 = r8.getPaddingTop()
                int r2 = r8.mWidth
                int r3 = r8.getPaddingRight()
                int r2 = r2 - r3
                int r3 = r8.mHeight
                int r4 = r8.getPaddingBottom()
                int r3 = r3 - r4
                int r4 = r10.getLeft()
                int r5 = r11.left
                int r4 = r4 + r5
                int r5 = r10.getScrollX()
                int r4 = r4 - r5
                int r5 = r10.getTop()
                int r6 = r11.top
                int r5 = r5 + r6
                int r10 = r10.getScrollY()
                int r5 = r5 - r10
                int r10 = r11.width()
                int r10 = r10 + r4
                int r11 = r11.height()
                int r11 = r11 + r5
                int r4 = r4 - r0
                int r5 = r5 - r1
                int r11 = r11 - r3
                r0 = 0
                int r1 = java.lang.Math.min(r0, r4)
                int r10 = r10 - r2
                int r2 = java.lang.Math.min(r0, r5)
                int r3 = java.lang.Math.max(r0, r10)
                int r11 = java.lang.Math.max(r0, r11)
                int r6 = r8.getLayoutDirection()
                r7 = 1
                if (r6 != r7) goto L5d
                if (r3 == 0) goto L58
                r1 = r3
                goto L63
            L58:
                int r1 = java.lang.Math.max(r1, r10)
                goto L63
            L5d:
                if (r1 != 0) goto L63
                int r1 = java.lang.Math.min(r4, r3)
            L63:
                if (r2 == 0) goto L66
                goto L6a
            L66:
                int r2 = java.lang.Math.min(r5, r11)
            L6a:
                int[] r10 = new int[]{r1, r2}
                r11 = r10[r0]
                r10 = r10[r7]
                if (r13 == 0) goto Lac
                android.view.View r13 = r9.getFocusedChild()
                if (r13 != 0) goto L7b
                goto Lb1
            L7b:
                int r1 = r8.getPaddingLeft()
                int r2 = r8.getPaddingTop()
                int r3 = r8.mWidth
                int r4 = r8.getPaddingRight()
                int r3 = r3 - r4
                int r4 = r8.mHeight
                int r5 = r8.getPaddingBottom()
                int r4 = r4 - r5
                android.support.v7.widget.RecyclerView r5 = r8.mRecyclerView
                android.graphics.Rect r5 = r5.mTempRect
                android.support.v7.widget.RecyclerView.getDecoratedBoundsWithMarginsInt(r13, r5)
                int r13 = r5.left
                int r13 = r13 - r11
                if (r13 >= r3) goto Lb1
                int r13 = r5.right
                int r13 = r13 - r11
                if (r13 <= r1) goto Lb1
                int r13 = r5.top
                int r13 = r13 - r10
                if (r13 >= r4) goto Lb1
                int r13 = r5.bottom
                int r13 = r13 - r10
                if (r13 <= r2) goto Lb1
            Lac:
                if (r11 != 0) goto Lb2
                if (r10 == 0) goto Lb1
                goto Lb3
            Lb1:
                return r0
            Lb2:
                r0 = r11
            Lb3:
                if (r12 == 0) goto Lb9
                r9.scrollBy(r0, r10)
                goto Lbc
            Lb9:
                r9.smoothScrollBy(r0, r10)
            Lbc:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.requestChildRectangleOnScreen(android.support.v7.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public final void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public final void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            throw null;
        }

        public void scrollToPosition(int i) {
            throw null;
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            throw null;
        }

        final void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        final void setMeasureSpecs(int i, int i2) {
            this.mWidth = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mHeightMode = mode2;
            if (mode2 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        public final void setMeasuredDimension(int i, int i2) {
            this.mRecyclerView.setMeasuredDimension(i, i2);
        }

        final void setMeasuredDimensionFromChildren(int i, int i2) {
            int childCount = getChildCount();
            if (childCount != 0) {
                int i3 = Integer.MIN_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = Integer.MAX_VALUE;
                int i6 = Integer.MIN_VALUE;
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = getChildAt(i7);
                    Rect rect = this.mRecyclerView.mTempRect;
                    RecyclerView.getDecoratedBoundsWithMarginsInt(childAt, rect);
                    if (rect.left < i4) {
                        i4 = rect.left;
                    }
                    if (rect.right > i3) {
                        i3 = rect.right;
                    }
                    if (rect.top < i5) {
                        i5 = rect.top;
                    }
                    if (rect.bottom > i6) {
                        i6 = rect.bottom;
                    }
                }
                this.mRecyclerView.mTempRect.set(i4, i5, i3, i6);
                setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
                return;
            }
            this.mRecyclerView.defaultOnMeasure(i, i2);
        }

        final void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public boolean shouldMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            if (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getWidth(), i, layoutParams.width) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getHeight(), i2, layoutParams.height)) {
                return false;
            }
            return true;
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public void smoothScrollToPosition$ar$ds(RecyclerView recyclerView, int i) {
            throw null;
        }

        public final void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.mSmoothScroller;
            if (smoothScroller2 != null && smoothScroller != smoothScroller2 && smoothScroller2.mRunning) {
                smoothScroller2.stop();
            }
            this.mSmoothScroller = smoothScroller;
            RecyclerView recyclerView = this.mRecyclerView;
            recyclerView.mViewFlinger.stop();
            if (smoothScroller.mStarted) {
                Log.w("RecyclerView", "An instance of " + smoothScroller.getClass().getSimpleName() + " was started more than once. Each instance of" + smoothScroller.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            smoothScroller.mRecyclerView = recyclerView;
            smoothScroller.mLayoutManager = this;
            int i = smoothScroller.mTargetPosition;
            if (i != -1) {
                RecyclerView recyclerView2 = smoothScroller.mRecyclerView;
                recyclerView2.mState.mTargetPosition = i;
                smoothScroller.mRunning = true;
                smoothScroller.mPendingInitialRun = true;
                smoothScroller.mTargetView = recyclerView2.mLayout.findViewByPosition(smoothScroller.mTargetPosition);
                smoothScroller.mRecyclerView.mViewFlinger.postOnAnimation();
                smoothScroller.mStarted = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public final void addDisappearingView(View view, int i) {
            addViewInt(view, i, true);
        }

        public final void addView(View view, int i) {
            addViewInt(view, i, false);
        }

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            setMeasuredDimension(chooseSize(i, rect.width() + getPaddingLeft() + getPaddingRight(), getMinimumWidth()), chooseSize(i2, rect.height() + getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
        }

        public final void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null || childViewHolderInt.isRemoved() || this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
        }

        public void onAdapterChanged$ar$ds() {
        }

        public void onItemsChanged$ar$ds() {
        }

        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        public void onDetachedFromWindow$ar$ds(RecyclerView recyclerView) {
        }

        public void onItemsUpdated$ar$ds$5eb43a0c_0(int i) {
        }

        public void onLayoutCompleted(State state) {
        }

        public void onScrollStateChanged(int i) {
        }

        public void collectInitialPrefetchPositions$ar$class_merging(int i, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        }

        public void onItemsAdded$ar$ds(int i, int i2) {
        }

        public void onItemsMoved$ar$ds(int i, int i2) {
        }

        public void onItemsRemoved$ar$ds(int i, int i2) {
        }

        public void collectAdjacentPrefetchPositions$ar$class_merging(int i, int i2, State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class OnFlingListener {
        public RecyclerView mRecyclerView;
        public final AppCompatSpinner.Api23Impl mScrollListener$ar$class_merging;

        public OnFlingListener() {
        }

        public abstract int[] calculateDistanceToFinalSnap(LayoutManager layoutManager, View view);

        protected SmoothScroller createScroller(LayoutManager layoutManager) {
            throw null;
        }

        public abstract View findSnapView(LayoutManager layoutManager);

        public abstract int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2);

        public final boolean onFling(int i, int i2) {
            SmoothScroller createScroller;
            int findTargetSnapPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            LayoutManager layoutManager = recyclerView.mLayout;
            if (layoutManager != null && recyclerView.mAdapter != null) {
                int i3 = recyclerView.mMinFlingVelocity;
                if ((Math.abs(i2) > i3 || Math.abs(i) > i3) && (layoutManager instanceof SmoothScroller.ScrollVectorProvider) && (createScroller = createScroller(layoutManager)) != null && (findTargetSnapPosition = findTargetSnapPosition(layoutManager, i, i2)) != -1) {
                    createScroller.mTargetPosition = findTargetSnapPosition;
                    layoutManager.startSmoothScroll(createScroller);
                    return true;
                }
                return false;
            }
            return false;
        }

        public final void snapToTargetExistingView() {
            LayoutManager layoutManager;
            View findSnapView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && (layoutManager = recyclerView.mLayout) != null && (findSnapView = findSnapView(layoutManager)) != null) {
                int[] calculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, findSnapView);
                int i = calculateDistanceToFinalSnap[0];
                if (i != 0 || calculateDistanceToFinalSnap[1] != 0) {
                    this.mRecyclerView.smoothScrollBy(i, calculateDistanceToFinalSnap[1]);
                }
            }
        }

        public OnFlingListener(byte[] bArr) {
            this();
            this.mScrollListener$ar$class_merging = new AppCompatSpinner.Api23Impl() { // from class: android.support.v7.widget.SnapHelper$1
                boolean mScrolled = false;

                @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
                public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    if (i == 0 && this.mScrolled) {
                        this.mScrolled = false;
                        RecyclerView.OnFlingListener.this.snapToTargetExistingView();
                    }
                }

                @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
                public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    if (i == 0 && i2 == 0) {
                        return;
                    }
                    this.mScrolled = true;
                }
            };
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent$ar$ds(MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent$ar$ds();

        void onTouchEvent$ar$ds(MotionEvent motionEvent);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Recycler {
        final ArrayList mAttachedScrap;
        public final ArrayList mCachedViews;
        ArrayList mChangedScrap;
        AsyncInterceptorsClientCallListener.PendingMessage mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging;
        private final int mRequestedCacheMax;
        public final List mUnmodifiableAttachedScrap;
        int mViewCacheMax;

        public Recycler() {
            ArrayList arrayList = new ArrayList();
            this.mAttachedScrap = arrayList;
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList();
            this.mUnmodifiableAttachedScrap = DesugarCollections.unmodifiableList(arrayList);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        public final void addViewHolderToRecycledViewPool(ViewHolder viewHolder, boolean z) {
            AccessibilityDelegateCompat accessibilityDelegateCompat;
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            RecyclerView recyclerView = RecyclerView.this;
            View view = viewHolder.itemView;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = recyclerView.mAccessibilityDelegate;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                if (itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    accessibilityDelegateCompat = (AccessibilityDelegateCompat) ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).mOriginalItemDelegates.remove(view);
                } else {
                    accessibilityDelegateCompat = null;
                }
                ViewCompat.setAccessibilityDelegate(view, accessibilityDelegateCompat);
            }
            if (z) {
                int size = RecyclerView.this.mRecyclerListeners.size();
                for (int i = 0; i < size; i++) {
                    ((RecyclerListener) RecyclerView.this.mRecyclerListeners.get(i)).onViewRecycled$ar$ds();
                }
                RecyclerView recyclerView2 = RecyclerView.this;
                if (recyclerView2.mState != null) {
                    recyclerView2.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.removeViewHolder(viewHolder);
                }
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            AsyncInterceptorsClientCallListener.PendingMessage recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging = getRecycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging();
            int i2 = viewHolder.mItemViewType;
            ArrayList arrayList = recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.getScrapDataForType(i2).mScrapHeap;
            int i3 = ((RecyclerView$RecycledViewPool$ScrapData) ((SparseArray) recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.message).get(i2)).mMaxScrap;
            if (arrayList.size() >= 5) {
                DrawableCompat$Api23Impl.callPoolingContainerOnRelease(viewHolder.itemView);
            } else {
                viewHolder.resetInternal();
                arrayList.add(viewHolder);
            }
        }

        public final void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public final int convertPreLayoutPositionToPostLayout(int i) {
            if (i >= 0 && i < RecyclerView.this.mState.getItemCount()) {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.mState.mInPreLayout) {
                    return i;
                }
                return recyclerView.mAdapterHelper$ar$class_merging.findPositionOffset(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
        }

        final AsyncInterceptorsClientCallListener.PendingMessage getRecycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging() {
            if (this.mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging == null) {
                this.mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging = new AsyncInterceptorsClientCallListener.PendingMessage(null);
                maybeSendPoolingContainerAttach();
            }
            return this.mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging;
        }

        public final View getViewForPosition(int i) {
            return tryGetViewHolderForPositionByDeadline$ar$ds(i, Long.MAX_VALUE).itemView;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Set, java.lang.Object] */
        public final void maybeSendPoolingContainerAttach() {
            RecyclerView recyclerView;
            Adapter adapter;
            AsyncInterceptorsClientCallListener.PendingMessage pendingMessage = this.mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging;
            if (pendingMessage != null && (adapter = (recyclerView = RecyclerView.this).mAdapter) != null && recyclerView.mIsAttached) {
                pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors.add(adapter);
            }
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Set, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v1, types: [java.util.Set, java.lang.Object] */
        public final void poolingContainerDetach(Adapter adapter, boolean z) {
            AsyncInterceptorsClientCallListener.PendingMessage pendingMessage = this.mRecyclerPool$ar$class_merging$ar$class_merging$ar$class_merging;
            if (pendingMessage != null) {
                pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors.remove(adapter);
                if (pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors.size() == 0 && !z) {
                    for (int i = 0; i < ((SparseArray) pendingMessage.message).size(); i++) {
                        SparseArray sparseArray = (SparseArray) pendingMessage.message;
                        ArrayList arrayList = ((RecyclerView$RecycledViewPool$ScrapData) sparseArray.get(sparseArray.keyAt(i))).mScrapHeap;
                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                            DrawableCompat$Api23Impl.callPoolingContainerOnRelease(((ViewHolder) arrayList.get(i2)).itemView);
                        }
                    }
                }
            }
        }

        final void quickRecycleScrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(childViewHolderInt);
        }

        final void recycleAndClearCachedViews() {
            int size = this.mCachedViews.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else {
                    recycleCachedViewAt(size);
                }
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        public final void recycleCachedViewAt(int i) {
            int i2 = RecyclerView.RecyclerView$ar$NoOp;
            addViewHolderToRecycledViewPool((ViewHolder) this.mCachedViews.get(i), true);
            this.mCachedViews.remove(i);
        }

        public final void recycleView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(childViewHolderInt);
            if (RecyclerView.this.mItemAnimator != null && !childViewHolderInt.isRecyclable()) {
                RecyclerView.this.mItemAnimator.endAnimation(childViewHolderInt);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
        
            if (android.support.v7.widget.RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(r7.mPosition) == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
        
            r3 = r3 - 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
        
            if (r3 < 0) goto L126;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
        
            if (android.support.v7.widget.RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(((android.support.v7.widget.RecyclerView.ViewHolder) r6.mCachedViews.get(r3)).mPosition) != false) goto L128;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007e, code lost:
        
            r3 = r3 + 1;
         */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0090  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final void recycleViewHolderInternal(android.support.v7.widget.RecyclerView.ViewHolder r7) {
            /*
                Method dump skipped, instructions count: 271
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.Recycler.recycleViewHolderInternal(android.support.v7.widget.RecyclerView$ViewHolder):void");
        }

        final void scrapView(View view) {
            ItemAnimator itemAnimator;
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && (itemAnimator = RecyclerView.this.mItemAnimator) != null && childViewHolderInt.getUnmodifiedPayloads().isEmpty() && ((SimpleItemAnimator) itemAnimator).mSupportsChangeAnimations && !childViewHolderInt.isInvalid()) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved()) {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.mAdapter.mHasStableIds) {
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.".concat(String.valueOf(recyclerView.exceptionLabel())));
                }
            }
            childViewHolderInt.setScrapContainer(this, false);
            this.mAttachedScrap.add(childViewHolderInt);
        }

        /* JADX WARN: Code restructure failed: missing block: B:248:0x0456, code lost:
        
            if ((r6 + r9) >= r20) goto L540;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x01b8, code lost:
        
            if (android.support.v7.widget.RecyclerView.this.mState.mInPreLayout == false) goto L437;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x01f2, code lost:
        
            if (r8.mItemId != r9.getItemId(r8.mPosition)) goto L437;
         */
        /* JADX WARN: Removed duplicated region for block: B:120:0x023c  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:217:0x03f8  */
        /* JADX WARN: Removed duplicated region for block: B:226:0x0532  */
        /* JADX WARN: Removed duplicated region for block: B:229:0x055a A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:233:0x053e  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:245:0x0445  */
        /* JADX WARN: Removed duplicated region for block: B:251:0x045e  */
        /* JADX WARN: Removed duplicated region for block: B:254:0x047a  */
        /* JADX WARN: Removed duplicated region for block: B:256:0x047f  */
        /* JADX WARN: Removed duplicated region for block: B:264:0x04b4  */
        /* JADX WARN: Removed duplicated region for block: B:269:0x04cb  */
        /* JADX WARN: Removed duplicated region for block: B:272:0x04ef  */
        /* JADX WARN: Removed duplicated region for block: B:286:0x0527  */
        /* JADX WARN: Removed duplicated region for block: B:288:0x051e  */
        /* JADX WARN: Removed duplicated region for block: B:289:0x047c  */
        /* JADX WARN: Removed duplicated region for block: B:290:0x0471  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final android.support.v7.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline$ar$ds(int r19, long r20) {
            /*
                Method dump skipped, instructions count: 1429
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline$ar$ds(int, long):android.support.v7.widget.RecyclerView$ViewHolder");
        }

        public final void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        public final void updateViewCacheSize() {
            int i;
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            if (layoutManager != null) {
                i = layoutManager.mPrefetchMaxCountObserved;
            } else {
                i = 0;
            }
            this.mViewCacheMax = i + 2;
            int size = this.mCachedViews.size();
            while (true) {
                size--;
                if (size >= 0 && this.mCachedViews.size() > this.mViewCacheMax) {
                    recycleCachedViewAt(size);
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface RecyclerListener {
        void onViewRecycled$ar$ds();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RecyclerViewDataObserver extends WindowCallbackWrapper.Api24Impl {
        public RecyclerViewDataObserver() {
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.mStructureChanged = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (!RecyclerView.this.mAdapterHelper$ar$class_merging.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            OverlayDisplay overlayDisplay = RecyclerView.this.mAdapterHelper$ar$class_merging;
            if (i2 > 0) {
                ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).add(overlayDisplay.obtainUpdateOp(4, i, i2, obj));
                overlayDisplay.numOfTextCell |= 4;
                if (((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).size() == 1) {
                    triggerUpdateProcessor();
                }
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeInserted(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            OverlayDisplay overlayDisplay = RecyclerView.this.mAdapterHelper$ar$class_merging;
            if (i2 > 0) {
                ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).add(overlayDisplay.obtainUpdateOp(1, i, i2, null));
                overlayDisplay.numOfTextCell |= 1;
                if (((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).size() == 1) {
                    triggerUpdateProcessor();
                }
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeMoved$ar$ds(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            OverlayDisplay overlayDisplay = RecyclerView.this.mAdapterHelper$ar$class_merging;
            if (i != i2) {
                ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).add(overlayDisplay.obtainUpdateOp(8, i, i2, null));
                overlayDisplay.numOfTextCell |= 8;
                if (((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).size() == 1) {
                    triggerUpdateProcessor();
                }
            }
        }

        @Override // android.support.v7.view.WindowCallbackWrapper.Api24Impl
        public final void onItemRangeRemoved(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            OverlayDisplay overlayDisplay = RecyclerView.this.mAdapterHelper$ar$class_merging;
            if (i2 > 0) {
                ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).add(overlayDisplay.obtainUpdateOp(2, i, i2, null));
                overlayDisplay.numOfTextCell |= 2;
                if (((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).size() == 1) {
                    triggerUpdateProcessor();
                }
            }
        }

        final void triggerUpdateProcessor() {
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                Runnable runnable = recyclerView.mUpdateChildViewsRunnable;
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                recyclerView.postOnAnimation(runnable);
            } else {
                recyclerView.mAdapterUpdateDuringMeasure = true;
                recyclerView.requestLayout();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SmoothScroller {
        protected final DecelerateInterpolator mDecelerateInterpolator;
        private final DisplayMetrics mDisplayMetrics;
        private boolean mHasCalculatedMillisPerPixel;
        protected int mInterimTargetDx;
        protected int mInterimTargetDy;
        public LayoutManager mLayoutManager;
        protected final LinearInterpolator mLinearInterpolator;
        private float mMillisPerPixel;
        public boolean mPendingInitialRun;
        public RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        public boolean mRunning;
        public boolean mStarted;
        public int mTargetPosition;
        protected PointF mTargetVector;
        public View mTargetView;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Action {
            public int mJumpToPosition = -1;
            private boolean mChanged = false;
            private int mConsecutiveUpdates = 0;
            private int mDx = 0;
            private int mDy = 0;
            private int mDuration = Integer.MIN_VALUE;
            private Interpolator mInterpolator = null;

            final void runIfNecessary(RecyclerView recyclerView) {
                int i = this.mJumpToPosition;
                if (i >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i);
                    this.mChanged = false;
                    return;
                }
                if (this.mChanged) {
                    Interpolator interpolator = this.mInterpolator;
                    if (interpolator != null && this.mDuration <= 0) {
                        throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                    }
                    int i2 = this.mDuration;
                    if (i2 > 0) {
                        recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2, interpolator);
                        int i3 = this.mConsecutiveUpdates + 1;
                        this.mConsecutiveUpdates = i3;
                        if (i3 > 10) {
                            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                        }
                        this.mChanged = false;
                        return;
                    }
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
                this.mConsecutiveUpdates = 0;
            }

            public final void update(int i, int i2, int i3, Interpolator interpolator) {
                this.mDx = i;
                this.mDy = i2;
                this.mDuration = i3;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int i);
        }

        public SmoothScroller() {
            this.mTargetPosition = -1;
            this.mRecyclingAction = new Action();
        }

        public static int calculateDtToFit$ar$ds(int i, int i2, int i3, int i4, int i5) {
            if (i5 != -1) {
                if (i5 != 0) {
                    return i4 - i2;
                }
                int i6 = i3 - i;
                if (i6 > 0) {
                    return i6;
                }
                int i7 = i4 - i2;
                if (i7 < 0) {
                    return i7;
                }
                return 0;
            }
            return i3 - i;
        }

        private static int clampApplyScroll$ar$ds(int i, int i2) {
            int i3 = i - i2;
            if (i * i3 <= 0) {
                return 0;
            }
            return i3;
        }

        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 25.0f / displayMetrics.densityDpi;
        }

        public final int calculateTimeForDeceleration(int i) {
            return (int) Math.ceil(calculateTimeForScrolling(i) / 0.3356d);
        }

        public int calculateTimeForScrolling(int i) {
            float abs = Math.abs(i);
            if (!this.mHasCalculatedMillisPerPixel) {
                this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
                this.mHasCalculatedMillisPerPixel = true;
            }
            return (int) Math.ceil(abs * this.mMillisPerPixel);
        }

        public final PointF computeScrollVectorForPosition(int i) {
            Object obj = this.mLayoutManager;
            if (obj instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) obj).computeScrollVectorForPosition(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement ".concat(String.valueOf(ScrollVectorProvider.class.getCanonicalName())));
            return null;
        }

        public final int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        final void onAnimation(int i, int i2) {
            PointF computeScrollVectorForPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            if (this.mTargetPosition == -1 || recyclerView == null) {
                stop();
            }
            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null && (computeScrollVectorForPosition.x != 0.0f || computeScrollVectorForPosition.y != 0.0f)) {
                recyclerView.scrollStep((int) Math.signum(computeScrollVectorForPosition.x), (int) Math.signum(computeScrollVectorForPosition.y), null);
            }
            this.mPendingInitialRun = false;
            View view = this.mTargetView;
            if (view != null) {
                if (getChildPosition(view) == this.mTargetPosition) {
                    View view2 = this.mTargetView;
                    State state = recyclerView.mState;
                    onTargetFound$ar$ds(view2, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    stop();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                State state2 = recyclerView.mState;
                onSeekTargetStep$ar$ds(i, i2, this.mRecyclingAction);
                Action action = this.mRecyclingAction;
                int i3 = action.mJumpToPosition;
                action.runIfNecessary(recyclerView);
                if (i3 >= 0 && this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView.mViewFlinger.postOnAnimation();
                }
            }
        }

        protected final void onSeekTargetStep$ar$ds(int i, int i2, Action action) {
            if (this.mRecyclerView.mLayout.getChildCount() == 0) {
                stop();
                return;
            }
            int clampApplyScroll$ar$ds = clampApplyScroll$ar$ds(this.mInterimTargetDx, i);
            this.mInterimTargetDx = clampApplyScroll$ar$ds;
            int clampApplyScroll$ar$ds2 = clampApplyScroll$ar$ds(this.mInterimTargetDy, i2);
            this.mInterimTargetDy = clampApplyScroll$ar$ds2;
            if (clampApplyScroll$ar$ds == 0 && clampApplyScroll$ar$ds2 == 0) {
                PointF computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition);
                if (computeScrollVectorForPosition != null && (computeScrollVectorForPosition.x != 0.0f || computeScrollVectorForPosition.y != 0.0f)) {
                    float sqrt = (float) Math.sqrt((computeScrollVectorForPosition.x * computeScrollVectorForPosition.x) + (computeScrollVectorForPosition.y * computeScrollVectorForPosition.y));
                    computeScrollVectorForPosition.x /= sqrt;
                    computeScrollVectorForPosition.y /= sqrt;
                    this.mTargetVector = computeScrollVectorForPosition;
                    this.mInterimTargetDx = (int) (computeScrollVectorForPosition.x * 10000.0f);
                    this.mInterimTargetDy = (int) (computeScrollVectorForPosition.y * 10000.0f);
                    int calculateTimeForScrolling = calculateTimeForScrolling(10000);
                    action.update((int) (this.mInterimTargetDx * 1.2f), (int) (this.mInterimTargetDy * 1.2f), (int) (calculateTimeForScrolling * 1.2f), this.mLinearInterpolator);
                    return;
                }
                action.mJumpToPosition = this.mTargetPosition;
                stop();
            }
        }

        protected final void onStop() {
            this.mInterimTargetDy = 0;
            this.mInterimTargetDx = 0;
            this.mTargetVector = null;
        }

        protected void onTargetFound$ar$ds(View view, Action action) {
            int i;
            int i2;
            PointF pointF = this.mTargetVector;
            int i3 = 1;
            int i4 = 0;
            if (pointF != null && pointF.x != 0.0f) {
                if (this.mTargetVector.x > 0.0f) {
                    i = 1;
                } else {
                    i = -1;
                }
            } else {
                i = 0;
            }
            LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager != null && layoutManager.canScrollHorizontally()) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                i2 = calculateDtToFit$ar$ds(layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.mWidth - layoutManager.getPaddingRight(), i);
            } else {
                i2 = 0;
            }
            PointF pointF2 = this.mTargetVector;
            if (pointF2 != null && pointF2.y != 0.0f) {
                if (this.mTargetVector.y <= 0.0f) {
                    i3 = -1;
                }
            } else {
                i3 = 0;
            }
            LayoutManager layoutManager2 = this.mLayoutManager;
            if (layoutManager2 != null && layoutManager2.canScrollVertically()) {
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                i4 = calculateDtToFit$ar$ds(layoutManager2.getDecoratedTop(view) - layoutParams2.topMargin, layoutManager2.getDecoratedBottom(view) + layoutParams2.bottomMargin, layoutManager2.getPaddingTop(), layoutManager2.mHeight - layoutManager2.getPaddingBottom(), i3);
            }
            int calculateTimeForDeceleration = calculateTimeForDeceleration((int) Math.sqrt((i2 * i2) + (i4 * i4)));
            if (calculateTimeForDeceleration > 0) {
                action.update(-i2, -i4, calculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }

        protected final void stop() {
            if (!this.mRunning) {
                return;
            }
            this.mRunning = false;
            onStop();
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager.mSmoothScroller == this) {
                layoutManager.mSmoothScroller = null;
            }
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }

        public SmoothScroller(Context context) {
            this();
            this.mLinearInterpolator = new LinearInterpolator();
            this.mDecelerateInterpolator = new DecelerateInterpolator();
            this.mHasCalculatedMillisPerPixel = false;
            this.mInterimTargetDx = 0;
            this.mInterimTargetDy = 0;
            this.mDisplayMetrics = context.getResources().getDisplayMetrics();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        int mTargetPosition = -1;
        int mPreviousLayoutItemCount = 0;
        public int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        int mLayoutStep = 1;
        int mItemCount = 0;
        public boolean mStructureChanged = false;
        public boolean mInPreLayout = false;
        boolean mTrackOldChangeHolders = false;
        boolean mIsMeasuring = false;
        boolean mRunSimpleAnimations = false;
        boolean mRunPredictiveAnimations = false;

        final void assertLayoutStep(int i) {
            if ((this.mLayoutStep & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
        }

        public final int getItemCount() {
            if (this.mInPreLayout) {
                return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
            }
            return this.mItemCount;
        }

        public final String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=null, mItemCount=" + this.mItemCount + ", mIsMeasuring=" + this.mIsMeasuring + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StretchEdgeEffectFactory extends WindowCallbackWrapper.Api26Impl {
        @Override // android.support.v7.view.WindowCallbackWrapper.Api26Impl
        public final EdgeEffect createEdgeEffect$ar$ds(RecyclerView recyclerView) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewFlinger implements Runnable {
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
        private boolean mEatRunOnAnimationRequest = false;
        private boolean mReSchedulePostAnimationCallback = false;

        public ViewFlinger() {
            this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }

        private final void internalPostOnAnimation() {
            RecyclerView.this.removeCallbacks(this);
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            RecyclerView.this.postOnAnimation(this);
        }

        public final void fling(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator interpolator = this.mInterpolator;
            Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
            if (interpolator != interpolator2) {
                this.mInterpolator = interpolator2;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
            }
            this.mOverScroller.fling(0, 0, i, i2, Integer.MIN_VALUE, Preference.DEFAULT_ORDER, Integer.MIN_VALUE, Preference.DEFAULT_ORDER);
            postOnAnimation();
        }

        final void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                internalPostOnAnimation();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x00fe  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0132  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01c9  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x01af  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 506
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.ViewFlinger.run():void");
        }

        public final void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            boolean z;
            int height;
            if (i3 == Integer.MIN_VALUE) {
                int abs = Math.abs(i);
                int abs2 = Math.abs(i2);
                if (abs > abs2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    height = RecyclerView.this.getWidth();
                } else {
                    height = RecyclerView.this.getHeight();
                }
                if (true != z) {
                    abs = abs2;
                }
                i3 = Math.min((int) (((abs / height) + 1.0f) * 300.0f), 2000);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, i, i2, i4);
            postOnAnimation();
        }

        public final void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ViewHolder {
        private static final List FULLUPDATE_PAYLOADS = Collections.emptyList();
        public final View itemView;
        Adapter mBindingAdapter;
        int mFlags;
        WeakReference mNestedRecyclerView;
        RecyclerView mOwnerRecyclerView;
        public int mPosition = -1;
        int mOldPosition = -1;
        long mItemId = -1;
        int mItemViewType = -1;
        int mPreLayoutPosition = -1;
        ViewHolder mShadowedHolder = null;
        ViewHolder mShadowingHolder = null;
        List mPayloads = null;
        List mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        Recycler mScrapContainer = null;
        boolean mInChangeScrap = false;
        public int mWasImportantForAccessibilityBeforeHidden = 0;
        int mPendingAccessibilityState = -1;

        public ViewHolder(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        public final void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
                return;
            }
            if ((1024 & this.mFlags) == 0) {
                if (this.mPayloads == null) {
                    ArrayList arrayList = new ArrayList();
                    this.mPayloads = arrayList;
                    this.mUnmodifiedPayloads = DesugarCollections.unmodifiableList(arrayList);
                }
                this.mPayloads.add(obj);
            }
        }

        public final void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        final void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        final void clearPayload() {
            List list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        final void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public final void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public final int getAbsoluteAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionInRecyclerView(this);
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            if (i == -1) {
                return this.mPosition;
            }
            return i;
        }

        final List getUnmodifiedPayloads() {
            List list;
            if ((this.mFlags & 1024) == 0 && (list = this.mPayloads) != null && list.size() != 0) {
                return this.mUnmodifiedPayloads;
            }
            return FULLUPDATE_PAYLOADS;
        }

        final boolean hasAnyOfTheFlags(int i) {
            if ((i & this.mFlags) != 0) {
                return true;
            }
            return false;
        }

        final boolean isAttachedToTransitionOverlay() {
            if (this.itemView.getParent() != null && this.itemView.getParent() != this.mOwnerRecyclerView) {
                return true;
            }
            return false;
        }

        public final boolean isBound() {
            if ((this.mFlags & 1) != 0) {
                return true;
            }
            return false;
        }

        public final boolean isInvalid() {
            if ((this.mFlags & 4) != 0) {
                return true;
            }
            return false;
        }

        public final boolean isRecyclable() {
            if ((this.mFlags & 16) == 0) {
                View view = this.itemView;
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                if (!view.hasTransientState()) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public final boolean isRemoved() {
            if ((this.mFlags & 8) != 0) {
                return true;
            }
            return false;
        }

        final boolean isScrap() {
            if (this.mScrapContainer != null) {
                return true;
            }
            return false;
        }

        public final boolean isTmpDetached() {
            if ((this.mFlags & 256) != 0) {
                return true;
            }
            return false;
        }

        final boolean isUpdated() {
            if ((this.mFlags & 2) != 0) {
                return true;
            }
            return false;
        }

        final boolean needsUpdate() {
            if ((this.mFlags & 2) != 0) {
                return true;
            }
            return false;
        }

        public final void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            int i2 = this.mPreLayoutPosition;
            if (i2 == -1) {
                i2 = this.mPosition;
                this.mPreLayoutPosition = i2;
            }
            if (z) {
                this.mPreLayoutPosition = i2 + i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        final void resetInternal() {
            int i = RecyclerView.RecyclerView$ar$NoOp;
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        final void setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (~i2));
        }

        public final void setIsRecyclable(boolean z) {
            int i;
            int i2;
            if (z) {
                i = this.mIsRecyclableCount - 1;
            } else {
                i = this.mIsRecyclableCount + 1;
            }
            this.mIsRecyclableCount = i;
            if (i < 0) {
                this.mIsRecyclableCount = 0;
                int i3 = RecyclerView.RecyclerView$ar$NoOp;
                toString();
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ".concat(toString()));
            } else {
                if (!z && i == 1) {
                    i2 = this.mFlags | 16;
                } else if (z && i == 0) {
                    i2 = this.mFlags & (-17);
                }
                this.mFlags = i2;
            }
            int i4 = RecyclerView.RecyclerView$ar$NoOp;
        }

        final void setScrapContainer(Recycler recycler, boolean z) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z;
        }

        public final boolean shouldIgnore() {
            if ((this.mFlags & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
                return true;
            }
            return false;
        }

        public final String toString() {
            String simpleName;
            String str;
            if (getClass().isAnonymousClass()) {
                simpleName = "ViewHolder";
            } else {
                simpleName = getClass().getSimpleName();
            }
            StringBuilder sb = new StringBuilder(simpleName + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                if (true != this.mInChangeScrap) {
                    str = "[attachedScrap]";
                } else {
                    str = "[changeScrap]";
                }
                sb.append(str);
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if ((this.mFlags & 512) != 0 || isInvalid()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        final void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        final boolean wasReturnedFromScrap() {
            if ((this.mFlags & 32) != 0) {
                return true;
            }
            return false;
        }
    }

    static {
        Class cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new ViewDragHelper.AnonymousClass1(1);
        sDefaultEdgeEffectFactory = new StretchEdgeEffectFactory();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private final void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder viewHolder) {
        WeakReference weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view != viewHolder.itemView) {
                    Object parent = view.getParent();
                    if (parent instanceof View) {
                        view = (View) parent;
                    } else {
                        view = null;
                    }
                } else {
                    return;
                }
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    public static final int consumeFlingInStretch$ar$ds(int i, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i2) {
        if (i > 0 && edgeEffect != null && PaintCompat.Api29Impl.getDistance(edgeEffect) != 0.0f) {
            int round = Math.round(((-i2) / 4.0f) * PaintCompat.Api29Impl.onPullDistance(edgeEffect, ((-i) * 4.0f) / i2, 0.5f));
            if (round != i) {
                edgeEffect.finish();
            }
            return i - round;
        }
        if (i < 0 && edgeEffect2 != null && PaintCompat.Api29Impl.getDistance(edgeEffect2) != 0.0f) {
            float f = i2;
            int round2 = Math.round((f / 4.0f) * PaintCompat.Api29Impl.onPullDistance(edgeEffect2, (i * 4.0f) / f, 0.5f));
            if (round2 != i) {
                edgeEffect2.finish();
            }
            return i - round2;
        }
        return i;
    }

    private final void createLayoutManager$ar$ds(Context context, String str, AttributeSet attributeSet, int i) {
        ClassLoader classLoader;
        Object[] objArr;
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                if (trim.charAt(0) == '.') {
                    trim = String.valueOf(context.getPackageName()).concat(String.valueOf(trim));
                } else if (!trim.contains(".")) {
                    trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                }
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(trim, false, classLoader).asSubclass(LayoutManager.class);
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), 0};
                    } catch (NoSuchMethodException e) {
                        objArr = null;
                        try {
                            constructor = asSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Error creating LayoutManager "), e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Class is not a LayoutManager "), e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Unable to find LayoutManager "), e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Cannot access non-public constructor "), e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Could not instantiate the LayoutManager: "), e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(trim, attributeSet, ": Could not instantiate the LayoutManager: "), e7);
                }
            }
        }
    }

    public static final void dispatchChildDetached$ar$ds(View view) {
        getChildViewHolderInt(view);
    }

    private final void dispatchLayoutStep1() {
        View view;
        long j;
        int absoluteAdapterPosition;
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord;
        View findContainingItemView;
        boolean z = true;
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        ViewHolder viewHolder = null;
        if (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) {
            view = getFocusedChild();
        } else {
            view = null;
        }
        if (view != null && (findContainingItemView = findContainingItemView(view)) != null) {
            viewHolder = getChildViewHolder(findContainingItemView);
        }
        if (viewHolder == null) {
            resetFocusInfo();
        } else {
            State state = this.mState;
            if (this.mAdapter.mHasStableIds) {
                j = viewHolder.mItemId;
            } else {
                j = -1;
            }
            state.mFocusedItemId = j;
            if (this.mDataSetHasChangedAfterLayout) {
                absoluteAdapterPosition = -1;
            } else if (viewHolder.isRemoved()) {
                absoluteAdapterPosition = viewHolder.mOldPosition;
            } else {
                absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
            }
            state.mFocusedItemPosition = absoluteAdapterPosition;
            State state2 = this.mState;
            View view2 = viewHolder.itemView;
            int id = view2.getId();
            while (!view2.isFocused() && (view2 instanceof ViewGroup) && view2.hasFocus()) {
                view2 = ((ViewGroup) view2).getFocusedChild();
                if (view2.getId() != -1) {
                    id = view2.getId();
                }
            }
            state2.mFocusedSubChildId = id;
        }
        State state3 = this.mState;
        if (!state3.mRunSimpleAnimations || !this.mItemsChanged) {
            z = false;
        }
        state3.mTrackOldChangeHolders = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        state3.mInPreLayout = state3.mRunPredictiveAnimations;
        state3.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.mHasStableIds)) {
                    ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt);
                    childViewHolderInt.getUnmodifiedPayloads();
                    this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToPreLayout$ar$class_merging(childViewHolderInt, ItemAnimator.recordPreLayoutInformation$ar$ds$ar$class_merging(childViewHolderInt));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToOldChangeHolders(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
                ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
                if (!childViewHolderInt2.shouldIgnore() && childViewHolderInt2.mOldPosition == -1) {
                    childViewHolderInt2.mOldPosition = childViewHolderInt2.mPosition;
                }
            }
            State state4 = this.mState;
            boolean z2 = state4.mStructureChanged;
            state4.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, state4);
            this.mState.mStructureChanged = z2;
            for (int i3 = 0; i3 < this.mChildHelper.getChildCount(); i3++) {
                ViewHolder childViewHolderInt3 = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
                if (!childViewHolderInt3.shouldIgnore() && ((viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.NodeBrailler$ar$rules).get(childViewHolderInt3)) == null || (viewInfoStore$InfoRecord.flags & 4) == 0)) {
                    ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt3);
                    boolean hasAnyOfTheFlags = childViewHolderInt3.hasAnyOfTheFlags(8192);
                    childViewHolderInt3.getUnmodifiedPayloads();
                    NestedScrollingParentHelper recordPreLayoutInformation$ar$ds$ar$class_merging = ItemAnimator.recordPreLayoutInformation$ar$ds$ar$class_merging(childViewHolderInt3);
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView$ar$class_merging(childViewHolderInt3, recordPreLayoutInformation$ar$ds$ar$class_merging);
                    } else {
                        NodeBrailler nodeBrailler = this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging;
                        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord2 = (ViewInfoStore$InfoRecord) ((SimpleArrayMap) nodeBrailler.NodeBrailler$ar$rules).get(childViewHolderInt3);
                        if (viewInfoStore$InfoRecord2 == null) {
                            Object obj = nodeBrailler.NodeBrailler$ar$rules;
                            viewInfoStore$InfoRecord2 = ViewInfoStore$InfoRecord.obtain();
                            ((SimpleArrayMap) obj).put(childViewHolderInt3, viewInfoStore$InfoRecord2);
                        }
                        viewInfoStore$InfoRecord2.flags |= 2;
                        viewInfoStore$InfoRecord2.preInfo$ar$class_merging = recordPreLayoutInformation$ar$ds$ar$class_merging;
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private final void dispatchLayoutStep2() {
        boolean z;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper$ar$class_merging.consumeUpdatesInOnePass();
        int itemCount = this.mAdapter.getItemCount();
        State state = this.mState;
        state.mItemCount = itemCount;
        state.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        if (this.mPendingSavedState != null) {
            int i = this.mAdapter.mStateRestorationPolicy$ar$edu;
            Parcelable parcelable = this.mPendingSavedState.mLayoutState;
            if (parcelable != null) {
                this.mLayout.onRestoreInstanceState(parcelable);
            }
            this.mPendingSavedState = null;
        }
        State state2 = this.mState;
        state2.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state2);
        State state3 = this.mState;
        state3.mStructureChanged = false;
        if (state3.mRunSimpleAnimations && this.mItemAnimator != null) {
            z = true;
        } else {
            z = false;
        }
        state3.mRunSimpleAnimations = z;
        state3.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private final boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        ArrayList arrayList = this.mOnItemTouchListeners;
        int action = motionEvent.getAction();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.mOnItemTouchListeners.get(i);
            if (onItemTouchListener.onInterceptTouchEvent$ar$ds(motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private final void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount != 0) {
            int i = Integer.MIN_VALUE;
            int i2 = Preference.DEFAULT_ORDER;
            for (int i3 = 0; i3 < childCount; i3++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
                if (!childViewHolderInt.shouldIgnore()) {
                    int layoutPosition = childViewHolderInt.getLayoutPosition();
                    if (layoutPosition < i2) {
                        i2 = layoutPosition;
                    }
                    if (layoutPosition > i) {
                        i = layoutPosition;
                    }
                }
            }
            iArr[0] = i2;
            iArr[1] = i;
            return;
        }
        iArr[0] = -1;
        iArr[1] = -1;
    }

    static RecyclerView findNestedRecyclerView(View view) {
        if (view instanceof ViewGroup) {
            if (view instanceof RecyclerView) {
                return (RecyclerView) view;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i));
                if (findNestedRecyclerView != null) {
                    return findNestedRecyclerView;
                }
            }
            return null;
        }
        return null;
    }

    public static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, view.getRight() + rect2.right + layoutParams.rightMargin, view.getBottom() + rect2.bottom + layoutParams.bottomMargin);
    }

    public static final long getNanoTime$ar$ds() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    private final NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private final void onPointerUp(MotionEvent motionEvent) {
        int i;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            if (actionIndex == 0) {
                i = 1;
            } else {
                i = 0;
            }
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    private final boolean predictiveItemAnimationsEnabled() {
        if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            return true;
        }
        return false;
    }

    private final void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper$ar$class_merging.reset();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged$ar$ds();
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper$ar$class_merging.preProcess();
        } else {
            this.mAdapterHelper$ar$class_merging.consumeUpdatesInOnePass();
        }
        boolean z4 = true;
        if (!this.mItemsAddedOrRemoved && !this.mItemsChanged) {
            z = false;
        } else {
            z = true;
        }
        State state = this.mState;
        if (this.mFirstLayoutComplete && this.mItemAnimator != null && (((z3 = this.mDataSetHasChangedAfterLayout) || z || this.mLayout.mRequestedSimpleAnimations) && (!z3 || this.mAdapter.mHasStableIds))) {
            z2 = true;
        } else {
            z2 = false;
        }
        state.mRunSimpleAnimations = z2;
        if (!z2 || !z || this.mDataSetHasChangedAfterLayout || !predictiveItemAnimationsEnabled()) {
            z4 = false;
        }
        state.mRunPredictiveAnimations = z4;
    }

    private final void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    private final int releaseHorizontalGlow(int i, float f) {
        float width = i / getWidth();
        float height = f / getHeight();
        EdgeEffect edgeEffect = this.mLeftGlow;
        float f2 = 0.0f;
        if (edgeEffect != null && PaintCompat.Api29Impl.getDistance(edgeEffect) != 0.0f) {
            if (canScrollHorizontally(-1)) {
                this.mLeftGlow.onRelease();
            } else {
                float f3 = -PaintCompat.Api29Impl.onPullDistance(this.mLeftGlow, -width, 1.0f - height);
                if (PaintCompat.Api29Impl.getDistance(this.mLeftGlow) == 0.0f) {
                    this.mLeftGlow.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        } else {
            EdgeEffect edgeEffect2 = this.mRightGlow;
            if (edgeEffect2 != null && PaintCompat.Api29Impl.getDistance(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.mRightGlow.onRelease();
                } else {
                    float onPullDistance = PaintCompat.Api29Impl.onPullDistance(this.mRightGlow, width, height);
                    if (PaintCompat.Api29Impl.getDistance(this.mRightGlow) == 0.0f) {
                        this.mRightGlow.onRelease();
                    }
                    f2 = onPullDistance;
                }
                invalidate();
            }
        }
        return Math.round(f2 * getWidth());
    }

    private final int releaseVerticalGlow(int i, float f) {
        float height = i / getHeight();
        float width = f / getWidth();
        EdgeEffect edgeEffect = this.mTopGlow;
        float f2 = 0.0f;
        if (edgeEffect != null && PaintCompat.Api29Impl.getDistance(edgeEffect) != 0.0f) {
            if (canScrollVertically(-1)) {
                this.mTopGlow.onRelease();
            } else {
                float f3 = -PaintCompat.Api29Impl.onPullDistance(this.mTopGlow, -height, width);
                if (PaintCompat.Api29Impl.getDistance(this.mTopGlow) == 0.0f) {
                    this.mTopGlow.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        } else {
            EdgeEffect edgeEffect2 = this.mBottomGlow;
            if (edgeEffect2 != null && PaintCompat.Api29Impl.getDistance(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.mBottomGlow.onRelease();
                } else {
                    float onPullDistance = PaintCompat.Api29Impl.onPullDistance(this.mBottomGlow, height, 1.0f - width);
                    if (PaintCompat.Api29Impl.getDistance(this.mBottomGlow) == 0.0f) {
                        this.mBottomGlow.onRelease();
                    }
                    f2 = onPullDistance;
                }
                invalidate();
            }
        }
        return Math.round(f2 * getHeight());
    }

    private final void requestChildOnScreen(View view, View view2) {
        View view3;
        boolean z;
        if (view2 != null) {
            view3 = view2;
        } else {
            view3 = view;
        }
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                this.mTempRect.left -= rect.left;
                this.mTempRect.right += rect.right;
                this.mTempRect.top -= rect.top;
                this.mTempRect.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        } else {
            view2 = null;
        }
        LayoutManager layoutManager = this.mLayout;
        Rect rect2 = this.mTempRect;
        boolean z2 = !this.mFirstLayoutComplete;
        if (view2 == null) {
            z = true;
        } else {
            z = false;
        }
        layoutManager.requestChildRectangleOnScreen(this, view, rect2, z2, z);
    }

    private final void resetFocusInfo() {
        State state = this.mState;
        state.mFocusedItemId = -1L;
        state.mFocusedItemPosition = -1;
        state.mFocusedSubChildId = -1;
    }

    private final void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private final boolean shouldAbsorb(EdgeEffect edgeEffect, int i, int i2) {
        if (i > 0) {
            return true;
        }
        float distance = PaintCompat.Api29Impl.getDistance(edgeEffect) * i2;
        double log = Math.log((Math.abs(-i) * 0.35f) / (this.mPhysicalCoef * 0.015f));
        double d = DECELERATION_RATE;
        if (((float) (this.mPhysicalCoef * 0.015f * Math.exp((d / ((-1.0d) + d)) * log))) < distance) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startNestedScrollForType(int i) {
        boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        int i2 = canScrollHorizontally;
        if (this.mLayout.canScrollVertically()) {
            i2 = (canScrollHorizontally ? 1 : 0) | 2;
        }
        startNestedScroll$ar$ds(i2, i);
    }

    private final void stopScrollersInternal() {
        SmoothScroller smoothScroller;
        this.mViewFlinger.stop();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && (smoothScroller = layoutManager.mSmoothScroller) != null) {
            smoothScroller.stop();
        }
    }

    public final void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        ViewParent parent = view.getParent();
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (parent != this) {
            this.mChildHelper.addView(view, -1, true);
            return;
        }
        ChildHelper childHelper = this.mChildHelper;
        int indexOfChild = childHelper.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.indexOfChild(view);
        if (indexOfChild >= 0) {
            childHelper.mBucket.set(indexOfChild);
            childHelper.hideViewInternal(view);
        } else {
            Objects.toString(view);
            throw new IllegalArgumentException("view is not a child, cannot hide ".concat(view.toString()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        super.addFocusables(arrayList, i, i2);
    }

    public final void addItemDecoration$ar$class_merging(AppCompatReceiveContentHelper$OnDropApi24Impl appCompatReceiveContentHelper$OnDropApi24Impl) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        this.mItemDecorations.add(appCompatReceiveContentHelper$OnDropApi24Impl);
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public final void addOnScrollListener$ar$class_merging(AppCompatSpinner.Api23Impl api23Impl) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(api23Impl);
    }

    final void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling".concat(String.valueOf(exceptionLabel())));
            }
            throw new IllegalStateException(str);
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("".concat(String.valueOf(exceptionLabel()))));
        }
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) layoutParams)) {
            return true;
        }
        return false;
    }

    final void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ViewHolder) recycler.mCachedViews.get(i2)).clearOldPosition();
        }
        int size2 = recycler.mAttachedScrap.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ((ViewHolder) recycler.mAttachedScrap.get(i3)).clearOldPosition();
        }
        ArrayList arrayList = recycler.mChangedScrap;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ((ViewHolder) recycler.mChangedScrap.get(i4)).clearOldPosition();
            }
        }
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    final void considerReleasingGlowsOnScroll(int i, int i2) {
        EdgeEffect edgeEffect = this.mLeftGlow;
        boolean z = false;
        if (edgeEffect != null && !edgeEffect.isFinished() && i > 0) {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public final void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
            if (this.mAdapterHelper$ar$class_merging.hasPendingUpdates()) {
                if (this.mAdapterHelper$ar$class_merging.hasAnyUpdateTypes(4) && !this.mAdapterHelper$ar$class_merging.hasAnyUpdateTypes(11)) {
                    Trace.beginSection("RV PartialInvalidate");
                    startInterceptRequestLayout();
                    onEnterLayoutOrScroll();
                    this.mAdapterHelper$ar$class_merging.preProcess();
                    if (!this.mLayoutWasDefered) {
                        int childCount = this.mChildHelper.getChildCount();
                        int i = 0;
                        while (true) {
                            if (i < childCount) {
                                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                                    dispatchLayout();
                                    break;
                                }
                                i++;
                            } else {
                                this.mAdapterHelper$ar$class_merging.consumePostponedUpdates();
                                break;
                            }
                        }
                    }
                    stopInterceptRequestLayout(true);
                    onExitLayoutOrScroll();
                    Trace.endSection();
                    return;
                }
                if (this.mAdapterHelper$ar$class_merging.hasPendingUpdates()) {
                    Trace.beginSection("RV FullInvalidate");
                    dispatchLayout();
                    Trace.endSection();
                    return;
                }
                return;
            }
            return;
        }
        Trace.beginSection("RV FullInvalidate");
        dispatchLayout();
        Trace.endSection();
    }

    final void defaultOnMeasure(int i, int i2) {
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
        setMeasuredDimension(LayoutManager.chooseSize(i, paddingLeft, getMinimumWidth()), LayoutManager.chooseSize(i2, getPaddingTop() + getPaddingBottom(), getMinimumHeight()));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        int i = 0;
        if (layoutManager == null) {
            return false;
        }
        if (layoutManager.canScrollVertically()) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 92 && keyCode != 93) {
                if (keyCode == 122 || keyCode == 123) {
                    boolean isLayoutReversed = layoutManager.isLayoutReversed();
                    if (keyCode == 122) {
                        if (isLayoutReversed) {
                            i = this.mAdapter.getItemCount();
                        }
                    } else if (!isLayoutReversed) {
                        i = this.mAdapter.getItemCount();
                    }
                    smoothScrollToPosition(i);
                    return true;
                }
            } else {
                int measuredHeight = getMeasuredHeight();
                if (keyCode == 93) {
                    smoothScrollBy$ar$ds(0, measuredHeight);
                } else {
                    smoothScrollBy$ar$ds(0, -measuredHeight);
                }
                return true;
            }
        } else if (layoutManager.canScrollHorizontally()) {
            int keyCode2 = keyEvent.getKeyCode();
            if (keyCode2 != 92 && keyCode2 != 93) {
                if (keyCode2 == 122 || keyCode2 == 123) {
                    boolean isLayoutReversed2 = layoutManager.isLayoutReversed();
                    if (keyCode2 == 122) {
                        if (isLayoutReversed2) {
                            i = this.mAdapter.getItemCount();
                        }
                    } else if (!isLayoutReversed2) {
                        i = this.mAdapter.getItemCount();
                    }
                    smoothScrollToPosition(i);
                    return true;
                }
            } else {
                int measuredWidth = getMeasuredWidth();
                if (keyCode2 == 93) {
                    smoothScrollBy$ar$ds(measuredWidth, 0);
                } else {
                    smoothScrollBy$ar$ds(-measuredWidth, 0);
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:149:0x0314, code lost:
    
        if (r16.mChildHelper.isHidden(getFocusedChild()) != false) goto L383;
     */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x037c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void dispatchLayout() {
        /*
            Method dump skipped, instructions count: 989
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.dispatchLayout():void");
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    final void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        List list = this.mScrollListeners;
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else {
                    ((AppCompatSpinner.Api23Impl) this.mScrollListeners.get(size)).onScrolled(this, i, i2);
                }
            }
        }
        this.mDispatchScrollCounter--;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        int i2;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z4 = false;
        for (int i3 = 0; i3 < size; i3++) {
            ((AppCompatReceiveContentHelper$OnDropApi24Impl) this.mItemDecorations.get(i3)).onDrawOver$ar$ds(canvas, this);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null && !edgeEffect.isFinished()) {
            int save = canvas.save();
            if (this.mClipToPadding) {
                i2 = getPaddingBottom();
            } else {
                i2 = 0;
            }
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + i2, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            if (edgeEffect2 != null && edgeEffect2.draw(canvas)) {
                z = true;
            } else {
                z = false;
            }
            canvas.restoreToCount(save);
        } else {
            z = false;
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            if (edgeEffect4 != null && edgeEffect4.draw(canvas)) {
                z3 = true;
            } else {
                z3 = false;
            }
            z |= z3;
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            if (this.mClipToPadding) {
                i = getPaddingTop();
            } else {
                i = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate(i, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            if (edgeEffect6 != null && edgeEffect6.draw(canvas)) {
                z2 = true;
            } else {
                z2 = false;
            }
            z |= z2;
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z4 = true;
            }
            z |= z4;
            canvas.restoreToCount(save4);
        }
        if (!z && (this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning())) {
            return;
        }
        postInvalidateOnAnimation();
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    final void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        EdgeEffect createEdgeEffect$ar$ds = this.mEdgeEffectFactory$ar$class_merging.createEdgeEffect$ar$ds(this);
        this.mBottomGlow = createEdgeEffect$ar$ds;
        if (this.mClipToPadding) {
            createEdgeEffect$ar$ds.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            createEdgeEffect$ar$ds.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    final void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        EdgeEffect createEdgeEffect$ar$ds = this.mEdgeEffectFactory$ar$class_merging.createEdgeEffect$ar$ds(this);
        this.mLeftGlow = createEdgeEffect$ar$ds;
        if (this.mClipToPadding) {
            createEdgeEffect$ar$ds.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            createEdgeEffect$ar$ds.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    final void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        EdgeEffect createEdgeEffect$ar$ds = this.mEdgeEffectFactory$ar$class_merging.createEdgeEffect$ar$ds(this);
        this.mRightGlow = createEdgeEffect$ar$ds;
        if (this.mClipToPadding) {
            createEdgeEffect$ar$ds.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            createEdgeEffect$ar$ds.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    final void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        EdgeEffect createEdgeEffect$ar$ds = this.mEdgeEffectFactory$ar$class_merging.createEdgeEffect$ar$ds(this);
        this.mTopGlow = createEdgeEffect$ar$ds;
        if (this.mClipToPadding) {
            createEdgeEffect$ar$ds.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            createEdgeEffect$ar$ds.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    final void fillRemainingScrollValues(State state) {
        if (this.mScrollState == 2) {
            OverScroller overScroller = this.mViewFlinger.mOverScroller;
            state.mRemainingScrollHorizontal = overScroller.getFinalX() - overScroller.getCurrX();
            state.mRemainingScrollVertical = overScroller.getFinalY() - overScroller.getCurrY();
        } else {
            state.mRemainingScrollHorizontal = 0;
            state.mRemainingScrollVertical = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View findContainingItemView(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            return r3
        L17:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    public final ViewHolder findViewHolderForAdapterPosition(int i) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i) {
                if (this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    viewHolder = childViewHolderInt;
                } else {
                    return childViewHolderInt;
                }
            }
        }
        return viewHolder;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean fling(int r7, int r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.fling(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x0061, code lost:
    
        if (r3.findNextFocus(r13, r14, r0) == null) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
    
        if (r3.findNextFocus(r13, r14, r0) != null) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
    
        consumePendingUpdateOperations();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        if (findContainingItemView(r14) != null) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006c, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006d, code lost:
    
        startInterceptRequestLayout();
        r13.mLayout.onFocusSearchFailed(r14, r15, r13.mRecycler, r13.mState);
        stopInterceptRequestLayout(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0116, code lost:
    
        if (r13.mTempRect.right <= r13.mTempRect2.left) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0136, code lost:
    
        if (r13.mTempRect.left >= r13.mTempRect2.right) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0157, code lost:
    
        if (r13.mTempRect.bottom <= r13.mTempRect2.top) goto L226;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0177, code lost:
    
        if (r13.mTempRect.top >= r13.mTempRect2.bottom) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0190, code lost:
    
        if (r2 > 0) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ae, code lost:
    
        if (r10 > 0) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01b1, code lost:
    
        if (r2 < 0) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b4, code lost:
    
        if (r10 < 0) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01bc, code lost:
    
        if ((r10 * r3) <= 0) goto L262;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01c4, code lost:
    
        if ((r10 * r3) >= 0) goto L262;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0103  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View focusSearch(android.view.View r14, int r15) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager".concat(String.valueOf(exceptionLabel())));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager".concat(String.valueOf(exceptionLabel())));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        return "android.support.v7.widget.RecyclerView";
    }

    final int getAdapterPositionInRecyclerView(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        OverlayDisplay overlayDisplay = this.mAdapterHelper$ar$class_merging;
        int i = viewHolder.mPosition;
        int size = ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).size();
        for (int i2 = 0; i2 < size; i2++) {
            AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((ArrayList) overlayDisplay.OverlayDisplay$ar$mainThreadHandler).get(i2);
            int i3 = adapterHelper$UpdateOp.cmd;
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 8) {
                        int i4 = adapterHelper$UpdateOp.positionStart;
                        if (i4 == i) {
                            i = adapterHelper$UpdateOp.itemCount;
                        } else {
                            if (i4 < i) {
                                i--;
                            }
                            if (adapterHelper$UpdateOp.itemCount <= i) {
                                i++;
                            }
                        }
                    }
                } else {
                    int i5 = adapterHelper$UpdateOp.positionStart;
                    if (i5 <= i) {
                        int i6 = adapterHelper$UpdateOp.itemCount;
                        if (i5 + i6 > i) {
                            return -1;
                        }
                        i -= i6;
                    } else {
                        continue;
                    }
                }
            } else if (adapterHelper$UpdateOp.positionStart <= i) {
                i += adapterHelper$UpdateOp.itemCount;
            }
        }
        return i;
    }

    @Override // android.view.View
    public final int getBaseline() {
        if (this.mLayout != null) {
            return -1;
        }
        return super.getBaseline();
    }

    final long getChangedHolderKey(ViewHolder viewHolder) {
        if (this.mAdapter.mHasStableIds) {
            return viewHolder.mItemId;
        }
        return viewHolder.mPosition;
    }

    public final int getChildLayoutPosition(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    public final ViewHolder getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, view, "View ", " is not a direct child of "));
        }
        return getChildViewHolderInt(view);
    }

    @Override // android.view.ViewGroup
    public final boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public final Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.mInPreLayout && (layoutParams.isItemChanged() || layoutParams.mViewHolder.isInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            ((AppCompatReceiveContentHelper$OnDropApi24Impl) this.mItemDecorations.get(i)).getItemOffsets$ar$ds(this.mTempRect, view, this);
            rect.left += this.mTempRect.left;
            rect.top += this.mTempRect.top;
            rect.right += this.mTempRect.right;
            rect.bottom += this.mTempRect.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public final boolean hasPendingAdapterUpdates() {
        if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout && !this.mAdapterHelper$ar$class_merging.hasPendingUpdates()) {
            return false;
        }
        return true;
    }

    final void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public final void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    final boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager != null && accessibilityManager.isEnabled()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public final boolean isComputingLayout() {
        if (this.mLayoutOrScrollCounter > 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().mIsNestedScrollingEnabled;
    }

    final void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout == null) {
            return;
        }
        setScrollState(2);
        this.mLayout.scrollToPosition(i);
        awakenScrollBars();
    }

    public final void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            LayoutParams layoutParams = (LayoutParams) ((ViewHolder) recycler.mCachedViews.get(i2)).itemView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.mInsetsDirty = true;
            }
        }
    }

    public final void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i4 = 0;
        while (true) {
            i3 = i + i2;
            if (i4 >= unfilteredChildCount) {
                break;
            }
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i5 = childViewHolderInt.mPosition;
                if (i5 >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                    this.mState.mStructureChanged = true;
                } else if (i5 >= i) {
                    childViewHolderInt.addFlags(8);
                    childViewHolderInt.offsetPosition(-i2, z);
                    childViewHolderInt.mPosition = i - 1;
                    this.mState.mStructureChanged = true;
                }
            }
            i4++;
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(size);
                if (viewHolder != null) {
                    int i6 = viewHolder.mPosition;
                    if (i6 >= i3) {
                        viewHolder.offsetPosition(-i2, z);
                    } else if (i6 >= i) {
                        viewHolder.addFlags(8);
                        recycler.recycleCachedViewAt(size);
                    }
                }
            } else {
                requestLayout();
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean z = true;
        this.mIsAttached = true;
        if (!this.mFirstLayoutComplete || isLayoutRequested()) {
            z = false;
        }
        this.mFirstLayoutComplete = z;
        this.mRecycler.maybeSendPoolingContainerAttach();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            GapWorker gapWorker = (GapWorker) GapWorker.sGapWorker.get();
            this.mGapWorker = gapWorker;
            if (gapWorker == null) {
                this.mGapWorker = new GapWorker();
                int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                Display display = getDisplay();
                float f = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                }
                GapWorker gapWorker2 = this.mGapWorker;
                gapWorker2.mFrameIntervalNs = 1.0E9f / f;
                GapWorker.sGapWorker.set(gapWorker2);
            }
            this.mGapWorker.mRecyclerViews.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        do {
        } while (ViewInfoStore$InfoRecord.sPool$ar$class_merging.acquire() != null);
        Recycler recycler = this.mRecycler;
        for (int i = 0; i < recycler.mCachedViews.size(); i++) {
            DrawableCompat$Api23Impl.callPoolingContainerOnRelease(((ViewHolder) recycler.mCachedViews.get(i)).itemView);
        }
        recycler.poolingContainerDetach(RecyclerView.this.mAdapter, false);
        Iterator it = new ViewGroupKt$special$$inlined$Sequence$1(this, 1).iterator();
        while (it.hasNext()) {
            DrawableCompat$Api23Impl.getPoolingContainerListenerHolder$ar$class_merging$ar$class_merging((View) it.next()).onRelease();
        }
        if (ALLOW_THREAD_GAP_WORK && (gapWorker = this.mGapWorker) != null) {
            gapWorker.mRecyclerViews.remove(this);
            this.mGapWorker = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            ((AppCompatReceiveContentHelper$OnDropApi24Impl) this.mItemDecorations.get(i)).onDraw$ar$ds(canvas, this);
        }
    }

    public final void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    final void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00cb, code lost:
    
        if (r0 != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0191, code lost:
    
        if (r9.mScrollState != 2) goto L173;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.onMeasure$ar$ds(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (!z && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(i, i2);
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                }
                this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
                this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
                return;
            }
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.onMeasure$ar$ds(i, i2);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll();
            State state = this.mState;
            if (state.mRunPredictiveAnimations) {
                state.mInPreLayout = true;
            } else {
                this.mAdapterHelper$ar$class_merging.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.mRunPredictiveAnimations) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            this.mState.mItemCount = adapter.getItemCount();
        } else {
            this.mState.mItemCount = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.onMeasure$ar$ds(i, i2);
        stopInterceptRequestLayout(false);
        this.mState.mInPreLayout = false;
    }

    @Override // android.view.ViewGroup
    protected final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.mSuperState);
        requestLayout();
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        Parcelable parcelable;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.mLayoutState = savedState2.mLayoutState;
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                parcelable = layoutManager.onSaveInstanceState();
            } else {
                parcelable = null;
            }
            savedState.mLayoutState = parcelable;
        }
        return savedState;
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        invalidateGlows();
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x0193, code lost:
    
        if (r0 != 0) goto L207;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0108  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 537
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            Runnable runnable = this.mItemAnimatorRunner;
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            postOnAnimation(runnable);
            this.mPostedAnimatorRunner = true;
        }
    }

    final void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(i2);
            if (viewHolder != null) {
                viewHolder.addFlags(6);
                viewHolder.addChangePayload(null);
            }
        }
        Adapter adapter = RecyclerView.this.mAdapter;
        if (adapter != null && adapter.mHasStableIds) {
            return;
        }
        recycler.recycleAndClearCachedViews();
    }

    final void recordAnimationInfoIfBouncedHiddenView$ar$class_merging(ViewHolder viewHolder, NestedScrollingParentHelper nestedScrollingParentHelper) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToOldChangeHolders(getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging.addToPreLayout$ar$class_merging(viewHolder, nestedScrollingParentHelper);
    }

    public final void removeAndRecycleViews() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    @Override // android.view.ViewGroup
    protected final void removeDetachedView(View view, boolean z) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        getChildViewHolderInt(view);
        super.removeDetachedView(view, z);
    }

    public final void removeOnScrollListener$ar$class_merging(AppCompatSpinner.Api23Impl api23Impl) {
        List list = this.mScrollListeners;
        if (list != null) {
            list.remove(api23Impl);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (!this.mLayout.isSmoothScrolling() && !isComputingLayout() && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            ((OnItemTouchListener) this.mOnItemTouchListeners.get(i)).onRequestDisallowInterceptTouchEvent$ar$ds();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutSuppressed) {
            super.requestLayout();
        } else {
            this.mLayoutWasDefered = true;
        }
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (!this.mLayoutSuppressed) {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (!canScrollHorizontally) {
                if (canScrollVertically) {
                    canScrollVertically = true;
                } else {
                    return;
                }
            }
            if (true != canScrollHorizontally) {
                i = 0;
            }
            if (true != canScrollVertically) {
                i2 = 0;
            }
            scrollByInternal(i, i2, null, 0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0113, code lost:
    
        if (r3 == 0.0f) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean scrollByInternal(int r19, int r20, android.view.MotionEvent r21, int r22) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent, int):boolean");
    }

    final void scrollStep(int i, int i2, int[] iArr) {
        int i3;
        int i4;
        ViewHolder viewHolder;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        Trace.beginSection("RV Scroll");
        fillRemainingScrollValues(this.mState);
        if (i != 0) {
            i3 = this.mLayout.scrollHorizontallyBy(i, this.mRecycler, this.mState);
        } else {
            i3 = 0;
        }
        if (i2 != 0) {
            i4 = this.mLayout.scrollVerticallyBy(i2, this.mRecycler, this.mState);
        } else {
            i4 = 0;
        }
        Trace.endSection();
        int childCount = this.mChildHelper.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = this.mChildHelper.getChildAt(i5);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (childViewHolder != null && (viewHolder = childViewHolder.mShadowingHolder) != null) {
                int left = childAt.getLeft();
                int top = childAt.getTop();
                View view = viewHolder.itemView;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = i3;
            iArr[1] = i4;
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public final void scrollToPosition(int i) {
        if (this.mLayoutSuppressed) {
            return;
        }
        stopScroll();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        int i;
        if (isComputingLayout()) {
            int i2 = 0;
            if (accessibilityEvent != null) {
                i = accessibilityEvent.getContentChangeTypes();
            } else {
                i = 0;
            }
            if (i != 0) {
                i2 = i;
            }
            this.mEatenAccessibilityChangeFlags |= i2;
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public final void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public final void setAdapter(Adapter adapter) {
        suppressLayout(false);
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver$ar$class_merging(this.mObserver);
        }
        removeAndRecycleViews();
        this.mAdapterHelper$ar$class_merging.reset();
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver$ar$class_merging(this.mObserver);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged$ar$ds();
        }
        Recycler recycler = this.mRecycler;
        Adapter adapter4 = this.mAdapter;
        recycler.clear();
        recycler.poolingContainerDetach(adapter3, true);
        AsyncInterceptorsClientCallListener.PendingMessage recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging = recycler.getRecycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging();
        if (adapter3 != null) {
            recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.currentStage--;
        }
        if (recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.currentStage == 0) {
            for (int i = 0; i < ((SparseArray) recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.message).size(); i++) {
                RecyclerView$RecycledViewPool$ScrapData recyclerView$RecycledViewPool$ScrapData = (RecyclerView$RecycledViewPool$ScrapData) ((SparseArray) recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.message).valueAt(i);
                ArrayList arrayList = recyclerView$RecycledViewPool$ScrapData.mScrapHeap;
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    DrawableCompat$Api23Impl.callPoolingContainerOnRelease(((ViewHolder) arrayList.get(i2)).itemView);
                }
                recyclerView$RecycledViewPool$ScrapData.mScrapHeap.clear();
            }
        }
        if (adapter4 != null) {
            recycledViewPool$ar$class_merging$ar$class_merging$ar$class_merging.currentStage++;
        }
        recycler.maybeSendPoolingContainerAttach();
        this.mState.mStructureChanged = true;
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public final void setChildImportantForAccessibilityInternal$ar$ds(ViewHolder viewHolder, int i) {
        if (isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = i;
            this.mPendingAccessibilityImportanceChange.add(viewHolder);
        } else {
            viewHolder.itemView.setImportantForAccessibility(i);
        }
    }

    @Override // android.view.ViewGroup
    public final void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public final void setHasFixedSize$ar$ds() {
        this.mHasFixedSize = true;
    }

    public final void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager == this.mLayout) {
            return;
        }
        stopScroll();
        if (this.mLayout != null) {
            ItemAnimator itemAnimator = this.mItemAnimator;
            if (itemAnimator != null) {
                itemAnimator.endAnimations();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.clear();
            if (this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        } else {
            this.mRecycler.clear();
        }
        ChildHelper childHelper = this.mChildHelper;
        childHelper.mBucket.reset();
        int size = childHelper.mHiddenViews.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            childHelper.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onLeftHiddenState((View) childHelper.mHiddenViews.get(size));
            childHelper.mHiddenViews.remove(size);
        }
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = childHelper.mCallback$ar$class_merging$5a35e444_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        int childCount = shadowDelegateImpl.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = shadowDelegateImpl.getChildAt(i);
            getChildViewHolderInt(childAt);
            childAt.clearAnimation();
        }
        ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).removeAllViews();
        this.mLayout = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.mRecyclerView == null) {
                this.mLayout.setRecyclerView(this);
                if (this.mIsAttached) {
                    this.mLayout.dispatchAttachedToWindow(this);
                }
            } else {
                throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.mRecyclerView.exceptionLabel());
            }
        }
        this.mRecycler.updateViewCacheSize();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public final void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    @Override // android.view.View
    public final void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    final void setScrollState(int i) {
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                stopScrollersInternal();
            }
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                layoutManager.onScrollStateChanged(i);
            }
            List list = this.mScrollListeners;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        ((AppCompatSpinner.Api23Impl) this.mScrollListeners.get(size)).onScrollStateChanged(this, i);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final void smoothScrollBy(int i, int i2) {
        smoothScrollBy$ar$ds(i, i2);
    }

    public final void smoothScrollBy$ar$ds(int i, int i2) {
        smoothScrollBy$ar$ds$a5e2b4f9_0(i, i2, false);
    }

    final void smoothScrollBy$ar$ds$a5e2b4f9_0(int i, int i2, boolean z) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (!this.mLayoutSuppressed) {
            int i3 = 0;
            if (true != layoutManager.canScrollHorizontally()) {
                i = 0;
            }
            if (true != this.mLayout.canScrollVertically()) {
                i2 = 0;
            }
            if (i == 0) {
                if (i2 != 0) {
                    i = 0;
                } else {
                    return;
                }
            }
            if (z) {
                if (i != 0) {
                    i3 = 1;
                }
                if (i2 != 0) {
                    i3 |= 2;
                }
                startNestedScroll$ar$ds(i3, 1);
            }
            this.mViewFlinger.smoothScrollBy(i, i2, Integer.MIN_VALUE, null);
        }
    }

    public final void smoothScrollToPosition(int i) {
        if (this.mLayoutSuppressed) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.smoothScrollToPosition$ar$ds(this, i);
        }
    }

    final void startInterceptRequestLayout() {
        int i = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i;
        if (i == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i);
    }

    public final void startNestedScroll$ar$ds(int i, int i2) {
        getScrollingChildHelper().startNestedScroll(i, i2);
    }

    final void stopInterceptRequestLayout(boolean z) {
        int i = this.mInterceptRequestLayoutDepth;
        if (i <= 0) {
            this.mInterceptRequestLayoutDepth = 1;
            i = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (i == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public final void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.mLayoutSuppressed = false;
                if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        final Rect mDecorInsets;
        public boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public final int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public final boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public final boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.marvin.talkback.R.attr.recyclerViewStyle);
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().dispatchNestedScrollInternal(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public final void onExitLayoutOrScroll(boolean z) {
        int i;
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 <= 0) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                int i3 = this.mEatenAccessibilityChangeFlags;
                this.mEatenAccessibilityChangeFlags = 0;
                if (i3 != 0 && isAccessibilityEnabled()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    obtain.setEventType(2048);
                    obtain.setContentChangeTypes(i3);
                    sendAccessibilityEventUnchecked(obtain);
                }
                for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
                    ViewHolder viewHolder = (ViewHolder) this.mPendingAccessibilityImportanceChange.get(size);
                    if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i = viewHolder.mPendingAccessibilityState) != -1) {
                        viewHolder.itemView.setImportantForAccessibility(i);
                        viewHolder.mPendingAccessibilityState = -1;
                    }
                }
                this.mPendingAccessibilityImportanceChange.clear();
            }
        }
    }

    public final void stopNestedScroll(int i) {
        getScrollingChildHelper().stopNestedScroll(i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1(0);
        Parcelable mLayoutState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.mLayoutState, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        /* compiled from: PG */
        /* renamed from: android.support.v7.widget.RecyclerView$SavedState$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                this.switching_field = i;
            }

            public static final AbsSavedState createFromParcel$ar$ds(Parcel parcel, ClassLoader classLoader) {
                if (parcel.readParcelable(classLoader) == null) {
                    return AbsSavedState.EMPTY_STATE;
                }
                throw new IllegalStateException("superState must be null");
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                int i = this.switching_field;
                if (i == 0) {
                    return new SavedState(parcel, null);
                }
                if (i == 1) {
                    return new Fragment.SavedState(parcel, null);
                }
                if (i == 2) {
                    return new Toolbar.SavedState(parcel, null);
                }
                if (i != 3) {
                    return i != 4 ? new ViewPager.SavedState(parcel, null) : createFromParcel$ar$ds(parcel, null);
                }
                return new CoordinatorLayout.SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object[] newArray(int i) {
                int i2 = this.switching_field;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    return new ViewPager.SavedState[i];
                                }
                                return new AbsSavedState[i];
                            }
                            return new CoordinatorLayout.SavedState[i];
                        }
                        return new Toolbar.SavedState[i];
                    }
                    return new Fragment.SavedState[i];
                }
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                int i = this.switching_field;
                if (i == 0) {
                    return new SavedState(parcel, classLoader);
                }
                if (i == 1) {
                    return new Fragment.SavedState(parcel, classLoader);
                }
                if (i == 2) {
                    return new Toolbar.SavedState(parcel, classLoader);
                }
                if (i != 3) {
                    return i != 4 ? new ViewPager.SavedState(parcel, classLoader) : createFromParcel$ar$ds(parcel, classLoader);
                }
                return new CoordinatorLayout.SavedState(parcel, classLoader);
            }
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore$ar$class_merging$ar$class_merging$ar$class_merging = new NodeBrailler((byte[]) null);
        this.mUpdateChildViewsRunnable = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 20, null);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory$ar$class_merging = sDefaultEdgeEffectFactory;
        this.mItemAnimator = new SimpleItemAnimator(null);
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new SearchView$SearchAutoComplete.AnonymousClass1(this, 1);
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        NestedScrollView.DifferentialMotionFlingTargetImpl differentialMotionFlingTargetImpl = new NestedScrollView.DifferentialMotionFlingTargetImpl(this, 1);
        this.mDifferentialMotionFlingTarget = differentialMotionFlingTargetImpl;
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(getContext(), differentialMotionFlingTargetImpl);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat$Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat$Api26Impl.getScaledVerticalScrollFactor(viewConfiguration);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mPhysicalCoef = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.mListener$ar$class_merging$4809de85_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.mItemAnimatorListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.mAdapterHelper$ar$class_merging = new OverlayDisplay(new FloatingActionButton.ShadowDelegateImpl(this));
        this.mChildHelper = new ChildHelper(new FloatingActionButton.ShadowDelegateImpl(this));
        if (ViewCompat.Api26Impl.getImportantForAutofill(this) == 0) {
            ViewCompat.Api26Impl.setImportantForAutofill(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.RecyclerView, attributeSet, obtainStyledAttributes, i, 0);
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(1, true);
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        this.mEnableFastScroller = z;
        if (z) {
            StateListDrawable stateListDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(6);
            Drawable drawable = obtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) obtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(5);
            if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
                Resources resources = getContext().getResources();
                new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.google.android.marvin.talkback.R.dimen.fastscroll_margin));
            } else {
                throw new IllegalArgumentException("Trying to set fast scroller without both required drawables.".concat(String.valueOf(exceptionLabel())));
            }
        }
        obtainStyledAttributes.recycle();
        this.mLowResRotaryEncoderFeature = context.getPackageManager().hasSystemFeature("android.hardware.rotaryencoder.lowres");
        createLayoutManager$ar$ds(context, string, attributeSet, i);
        int[] iArr = NESTED_SCROLLING_ATTRS;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes2, i, 0);
        boolean z2 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
        setTag(com.google.android.marvin.talkback.R.id.is_pooling_container_tag, true);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager".concat(String.valueOf(exceptionLabel())));
    }
}
