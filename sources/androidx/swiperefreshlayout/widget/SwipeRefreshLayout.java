package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ListView;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent3, NestedScrollingParent2, NestedScrollingChild {
    private static final int[] LAYOUT_ATTRS = {R.attr.enabled};
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleDiameter;
    CircleImageView mCircleView;
    private int mCircleViewIndex;
    int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    public OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final int[] mNestedScrollingV2ConsumedCompat;
    boolean mNotify;
    protected int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    CircularProgressDrawable mProgress;
    private Animation.AnimationListener mRefreshListener;
    boolean mRefreshing;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    int mSpinnerOffsetEnd;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    boolean mUsingCustomStart;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnRefreshListener {
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private final void ensureTarget() {
        if (this.mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.mCircleView)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    private final void finishSpinner(float f) {
        if (f > this.mTotalDragDistance) {
            setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.setStartEndTrim$ar$ds(0.0f);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5(this, 0);
        this.mFrom = this.mCurrentTargetOffsetTop;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        CircleImageView circleImageView = this.mCircleView;
        circleImageView.mListener = anonymousClass5;
        circleImageView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
        this.mProgress.setArrowEnabled(false);
    }

    private static final boolean isAnimationRunning$ar$ds(Animation animation) {
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            return true;
        }
        return false;
    }

    private final void moveSpinner(float f) {
        this.mProgress.setArrowEnabled(true);
        float min = Math.min(1.0f, Math.abs(f / this.mTotalDragDistance));
        float max = (float) Math.max(min - 0.4d, 0.0d);
        float abs = Math.abs(f) - this.mTotalDragDistance;
        float f2 = this.mSpinnerOffsetEnd;
        double max2 = Math.max(0.0f, Math.min(abs, f2 + f2) / f2) / 4.0f;
        float f3 = min * f2;
        float pow = (float) (max2 - Math.pow(max2, 2.0d));
        float f4 = pow + pow;
        float f5 = f2 * f4;
        int i = this.mOriginalOffsetTop + ((int) (f3 + f5 + f5));
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        this.mCircleView.setScaleX(1.0f);
        this.mCircleView.setScaleY(1.0f);
        if (f < this.mTotalDragDistance) {
            if (this.mProgress.getAlpha() > 76 && !isAnimationRunning$ar$ds(this.mAlphaStartAnimation)) {
                this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
            }
        } else if (this.mProgress.getAlpha() < 255 && !isAnimationRunning$ar$ds(this.mAlphaMaxAnimation)) {
            this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), PrivateKeyType.INVALID);
        }
        float f6 = (max * 5.0f) / 3.0f;
        this.mProgress.setStartEndTrim$ar$ds(Math.min(0.8f, f6 * 0.8f));
        this.mProgress.setArrowScale(Math.min(1.0f, f6));
        CircularProgressDrawable circularProgressDrawable = this.mProgress;
        circularProgressDrawable.mRing.mRotation = (((f6 * 0.4f) - 0.25f) + f4 + f4) * 0.5f;
        circularProgressDrawable.invalidateSelf();
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop);
    }

    private final void onSecondaryPointerUp(MotionEvent motionEvent) {
        int i;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            if (actionIndex == 0) {
                i = 1;
            } else {
                i = 0;
            }
            this.mActivePointerId = motionEvent.getPointerId(i);
        }
    }

    private final void setRefreshingWithoutSwipeGesture(boolean z, boolean z2) {
        if (z) {
            if (!this.mRefreshing) {
                this.mRefreshing = true;
                setTargetOffsetTopAndBottom((this.mSpinnerOffsetEnd + this.mOriginalOffsetTop) - this.mCurrentTargetOffsetTop);
                this.mNotify = z2;
                Animation.AnimationListener animationListener = this.mRefreshListener;
                this.mCircleView.setVisibility(0);
                this.mProgress.setAlpha(PrivateKeyType.INVALID);
                Animation animation = new Animation() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.2
                    @Override // android.view.animation.Animation
                    public final void applyTransformation(float f, Transformation transformation) {
                        SwipeRefreshLayout.this.setAnimationProgress(f);
                    }
                };
                this.mScaleAnimation = animation;
                animation.setDuration(this.mMediumAnimationDuration);
                if (animationListener != null) {
                    this.mCircleView.mListener = animationListener;
                }
                this.mCircleView.clearAnimation();
                this.mCircleView.startAnimation(this.mScaleAnimation);
                return;
            }
            z = true;
        }
        setRefreshing(z, false);
    }

    private final Animation startAlphaAnimation(final int i, final int i2) {
        Animation animation = new Animation() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.4
            @Override // android.view.animation.Animation
            public final void applyTransformation(float f, Transformation transformation) {
                int i3 = i2;
                SwipeRefreshLayout.this.mProgress.setAlpha((int) (i + ((i3 - r0) * f)));
            }
        };
        animation.setDuration(300L);
        CircleImageView circleImageView = this.mCircleView;
        circleImageView.mListener = null;
        circleImageView.clearAnimation();
        this.mCircleView.startAnimation(animation);
        return animation;
    }

    private final void startDragging(float f) {
        float f2 = this.mInitialDownY;
        float f3 = f - f2;
        float f4 = this.mTouchSlop;
        if (f3 > f4 && !this.mIsBeingDragged) {
            this.mInitialMotionY = f2 + f4;
            this.mIsBeingDragged = true;
            this.mProgress.setAlpha(76);
        }
    }

    public final boolean canChildScrollUp() {
        View view = this.mTarget;
        if (view instanceof ListView) {
            return ((ListView) view).canScrollList(-1);
        }
        return view.canScrollVertically(-1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getAction() == 1 && keyEvent.getKeyCode() == 285) {
            setRefreshingWithoutSwipeGesture(true, true);
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup
    protected final int getChildDrawingOrder(int i, int i2) {
        int i3 = this.mCircleViewIndex;
        if (i3 < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return i3;
        }
        if (i2 >= i3) {
            return i2 + 1;
        }
        return i2;
    }

    @Override // android.view.ViewGroup
    public final int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.mIsNestedScrollingEnabled;
    }

    final void moveToStart(float f) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) ((this.mOriginalOffsetTop - r0) * f))) - this.mCircleView.getTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = motionEvent.getActionMasked();
        if (isEnabled() && !canChildScrollUp() && !this.mRefreshing && !this.mNestedScrollInProgress) {
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked != 3) {
                            if (actionMasked == 6) {
                                onSecondaryPointerUp(motionEvent);
                            }
                        }
                    } else {
                        int i = this.mActivePointerId;
                        if (i == -1) {
                            Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                            return false;
                        }
                        int findPointerIndex = motionEvent.findPointerIndex(i);
                        if (findPointerIndex >= 0) {
                            startDragging(motionEvent.getY(findPointerIndex));
                        }
                    }
                    return this.mIsBeingDragged;
                }
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                return this.mIsBeingDragged;
            }
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
            int pointerId = motionEvent.getPointerId(0);
            this.mActivePointerId = pointerId;
            this.mIsBeingDragged = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(pointerId);
            if (findPointerIndex2 >= 0) {
                this.mInitialDownY = motionEvent.getY(findPointerIndex2);
                return this.mIsBeingDragged;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.mTarget == null) {
                ensureTarget();
            }
            View view = this.mTarget;
            if (view != null) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                int measuredWidth2 = this.mCircleView.getMeasuredWidth();
                int measuredHeight2 = this.mCircleView.getMeasuredHeight();
                CircleImageView circleImageView = this.mCircleView;
                int i5 = measuredWidth / 2;
                int i6 = measuredWidth2 / 2;
                int i7 = this.mCurrentTargetOffsetTop;
                int i8 = i5 + i6;
                circleImageView.layout(i5 - i6, i7, i8, measuredHeight2 + i7);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
            this.mCircleViewIndex = -1;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3) == this.mCircleView) {
                    this.mCircleViewIndex = i3;
                    return;
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f = this.mTotalUnconsumed;
            float f2 = 0.0f;
            if (f > 0.0f) {
                float f3 = i2;
                if (f3 > f) {
                    iArr[1] = (int) f;
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    f2 = f - f3;
                    this.mTotalUnconsumed = f2;
                    iArr[1] = i2;
                }
                moveSpinner(f2);
            }
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        onNestedScroll(view, i, i2, i3, i4, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.mRefreshing);
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mRefreshing);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.mRefreshing || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll$ar$ds(0);
        this.mNestedScrollInProgress = false;
        float f = this.mTotalUnconsumed;
        if (f > 0.0f) {
            finishSpinner(f);
            this.mTotalUnconsumed = 0.0f;
        } else {
            post(new SearchView$SearchAutoComplete.AnonymousClass1(this, 18, null));
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!isEnabled() || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked == 3) {
                        return false;
                    }
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            onSecondaryPointerUp(motionEvent);
                        }
                    } else {
                        int actionIndex = motionEvent.getActionIndex();
                        if (actionIndex < 0) {
                            Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                            return false;
                        }
                        this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                    }
                } else {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex < 0) {
                        Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    float y = motionEvent.getY(findPointerIndex);
                    startDragging(y);
                    if (this.mIsBeingDragged) {
                        float f = (y - this.mInitialMotionY) * 0.5f;
                        if (f <= 0.0f) {
                            return false;
                        }
                        getParent().requestDisallowInterceptTouchEvent(true);
                        moveSpinner(f);
                    }
                }
            } else {
                int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex2 < 0) {
                    Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.mIsBeingDragged) {
                    float y2 = (motionEvent.getY(findPointerIndex2) - this.mInitialMotionY) * 0.5f;
                    this.mIsBeingDragged = false;
                    finishSpinner(y2);
                }
                this.mActivePointerId = -1;
                return false;
            }
        } else {
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsBeingDragged = false;
        }
        return true;
    }

    public final void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        this.mCircleView.getBackground().setAlpha(PrivateKeyType.INVALID);
        this.mProgress.setAlpha(PrivateKeyType.INVALID);
        setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    final void setAnimationProgress(float f) {
        this.mCircleView.setScaleX(f);
        this.mCircleView.setScaleY(f);
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            reset();
        }
    }

    @Override // android.view.View
    public final void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
    }

    public final void setRefreshing(boolean z) {
        setRefreshingWithoutSwipeGesture(z, false);
    }

    final void setTargetOffsetTopAndBottom(int i) {
        this.mCircleView.bringToFront();
        CircleImageView circleImageView = this.mCircleView;
        int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
        circleImageView.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i);
    }

    final void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        Animation animation = new Animation() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.3
            @Override // android.view.animation.Animation
            public final void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation = animation;
        animation.setDuration(150L);
        CircleImageView circleImageView = this.mCircleView;
        circleImageView.mListener = animationListener;
        circleImageView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(1);
        final boolean mRefreshing;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mRefreshing = parcel.readByte() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.mRefreshing ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.mRefreshing = z;
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new AnonymousClass5(this, 1);
        this.mAnimateToCorrectPosition = new Animation() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.6
            @Override // android.view.animation.Animation
            public final void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
                boolean z = swipeRefreshLayout.mUsingCustomStart;
                int abs = swipeRefreshLayout.mSpinnerOffsetEnd - Math.abs(swipeRefreshLayout.mOriginalOffsetTop);
                SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.mFrom + ((int) ((abs - r1) * f))) - swipeRefreshLayout2.mCircleView.getTop());
                SwipeRefreshLayout.this.mProgress.setArrowScale(1.0f - f);
            }
        };
        this.mAnimateToStartPosition = new Animation() { // from class: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.7
            @Override // android.view.animation.Animation
            public final void applyTransformation(float f, Transformation transformation) {
                SwipeRefreshLayout.this.moveToStart(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
        this.mCircleView = new CircleImageView(getContext());
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        this.mProgress = circularProgressDrawable;
        CircularProgressDrawable.Ring ring = circularProgressDrawable.mRing;
        float f = circularProgressDrawable.mResources.getDisplayMetrics().density;
        ring.setStrokeWidth(2.5f * f);
        ring.mRingCenterRadius = 7.5f * f;
        ring.setColorIndex(0);
        ring.mArrowWidth = (int) (10.0f * f);
        ring.mArrowHeight = (int) (f * 5.0f);
        circularProgressDrawable.invalidateSelf();
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        addView(this.mCircleView);
        setChildrenDrawingOrderEnabled(true);
        int i = (int) (displayMetrics.density * 64.0f);
        this.mSpinnerOffsetEnd = i;
        this.mTotalDragDistance = i;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper();
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.mCircleDiameter;
        this.mCurrentTargetOffsetTop = i2;
        this.mOriginalOffsetTop = i2;
        moveToStart(1.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
    }

    private final void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (z) {
                int i = this.mCurrentTargetOffsetTop;
                Animation.AnimationListener animationListener = this.mRefreshListener;
                this.mFrom = i;
                this.mAnimateToCorrectPosition.reset();
                this.mAnimateToCorrectPosition.setDuration(200L);
                this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
                if (animationListener != null) {
                    this.mCircleView.mListener = animationListener;
                }
                this.mCircleView.clearAnimation();
                this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
                return;
            }
            startScaleDownAnimation(this.mRefreshListener);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i, i2, i3, i4, i5, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            return onStartNestedScroll(view, view2, i);
        }
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        if (i5 != 0) {
            return;
        }
        int i7 = iArr[1];
        this.mNestedScrollingChildHelper.dispatchNestedScrollInternal(i, i2, i3, i4, this.mParentOffsetInWindow, 0, iArr);
        int i8 = i4 - (iArr[1] - i7);
        if (i8 == 0) {
            i8 = this.mParentOffsetInWindow[1] + i4;
            i6 = 0;
        } else {
            i6 = i8;
        }
        if (i8 >= 0 || canChildScrollUp()) {
            return;
        }
        float abs = this.mTotalUnconsumed + Math.abs(i8);
        this.mTotalUnconsumed = abs;
        moveSpinner(abs);
        iArr[1] = iArr[1] + i6;
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

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$5, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass5 implements Animation.AnimationListener {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ SwipeRefreshLayout this$0;

        public AnonymousClass5(SwipeRefreshLayout swipeRefreshLayout, int i) {
            this.switching_field = i;
            this.this$0 = swipeRefreshLayout;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            OnRefreshListener onRefreshListener;
            if (this.switching_field != 0) {
                SwipeRefreshLayout swipeRefreshLayout = this.this$0;
                if (swipeRefreshLayout.mRefreshing) {
                    swipeRefreshLayout.mProgress.setAlpha(PrivateKeyType.INVALID);
                    this.this$0.mProgress.start();
                    SwipeRefreshLayout swipeRefreshLayout2 = this.this$0;
                    if (swipeRefreshLayout2.mNotify && (onRefreshListener = swipeRefreshLayout2.mListener) != null) {
                        ((MddDebugListFragmentUiProviderImpl) onRefreshListener).actionProvider.updateFileGroupList();
                    }
                    SwipeRefreshLayout swipeRefreshLayout3 = this.this$0;
                    swipeRefreshLayout3.mCurrentTargetOffsetTop = swipeRefreshLayout3.mCircleView.getTop();
                    return;
                }
                swipeRefreshLayout.reset();
                return;
            }
            this.this$0.startScaleDownAnimation(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }
}
