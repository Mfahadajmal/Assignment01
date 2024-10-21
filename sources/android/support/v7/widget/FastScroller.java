package android.support.v7.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FastScroller extends AppCompatReceiveContentHelper$OnDropApi24Impl implements RecyclerView.OnItemTouchListener {
    public int mAnimationState;
    private final Runnable mHideRunnable;
    float mHorizontalDragX;
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private final AppCompatSpinner.Api23Impl mOnScrollListener$ar$class_merging;
    public RecyclerView mRecyclerView;
    public final int mScrollbarMinimumRange;
    public final ValueAnimator mShowHideAnimator;
    float mVerticalDragY;
    int mVerticalThumbCenterY;
    public final StateListDrawable mVerticalThumbDrawable;
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    public final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;
    private static final int[] PRESSED_STATE_SET = {R.attr.state_pressed};
    private static final int[] EMPTY_STATE_SET = new int[0];
    public int mRecyclerViewWidth = 0;
    public int mRecyclerViewHeight = 0;
    public boolean mNeedVerticalScrollbar = false;
    public boolean mNeedHorizontalScrollbar = false;
    public int mState = 0;
    private int mDragState = 0;
    private final int[] mVerticalRange = new int[2];
    private final int[] mHorizontalRange = new int[2];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;

        public AnimatorListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
                return;
            }
            if (((Float) FastScroller.this.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                FastScroller fastScroller = FastScroller.this;
                fastScroller.mAnimationState = 0;
                fastScroller.setState(0);
            } else {
                FastScroller fastScroller2 = FastScroller.this;
                fastScroller2.mAnimationState = 2;
                fastScroller2.requestRedraw();
            }
        }
    }

    public FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mShowHideAnimator = ofFloat;
        this.mAnimationState = 0;
        this.mHideRunnable = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(this, 15, null);
        AppCompatSpinner.Api23Impl api23Impl = new AppCompatSpinner.Api23Impl() { // from class: android.support.v7.widget.FastScroller.2
            @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
            public final void onScrolled(RecyclerView recyclerView2, int i4, int i5) {
                boolean z;
                boolean z2;
                int computeHorizontalScrollOffset = recyclerView2.computeHorizontalScrollOffset();
                int computeVerticalScrollOffset = recyclerView2.computeVerticalScrollOffset();
                FastScroller fastScroller = FastScroller.this;
                int computeVerticalScrollRange = fastScroller.mRecyclerView.computeVerticalScrollRange();
                int i6 = fastScroller.mRecyclerViewHeight;
                if (computeVerticalScrollRange - i6 > 0 && i6 >= fastScroller.mScrollbarMinimumRange) {
                    z = true;
                } else {
                    z = false;
                }
                fastScroller.mNeedVerticalScrollbar = z;
                int computeHorizontalScrollRange = fastScroller.mRecyclerView.computeHorizontalScrollRange();
                int i7 = fastScroller.mRecyclerViewWidth;
                if (computeHorizontalScrollRange - i7 > 0 && i7 >= fastScroller.mScrollbarMinimumRange) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                fastScroller.mNeedHorizontalScrollbar = z2;
                if (!fastScroller.mNeedVerticalScrollbar) {
                    if (!z2) {
                        if (fastScroller.mState != 0) {
                            fastScroller.setState(0);
                            return;
                        }
                        return;
                    }
                } else {
                    float f = i6;
                    fastScroller.mVerticalThumbCenterY = (int) ((f * (computeVerticalScrollOffset + (f / 2.0f))) / computeVerticalScrollRange);
                    fastScroller.mVerticalThumbHeight = Math.min(i6, (i6 * i6) / computeVerticalScrollRange);
                }
                if (fastScroller.mNeedHorizontalScrollbar) {
                    float f2 = computeHorizontalScrollOffset;
                    float f3 = i7;
                    fastScroller.mHorizontalThumbCenterX = (int) ((f3 * (f2 + (f3 / 2.0f))) / computeHorizontalScrollRange);
                    fastScroller.mHorizontalThumbWidth = Math.min(i7, (i7 * i7) / computeHorizontalScrollRange);
                }
                int i8 = fastScroller.mState;
                if (i8 != 0 && i8 != 1) {
                    return;
                }
                fastScroller.setState(1);
            }
        };
        this.mOnScrollListener$ar$class_merging = api23Impl;
        this.mVerticalThumbDrawable = stateListDrawable;
        this.mVerticalTrackDrawable = drawable;
        this.mHorizontalThumbDrawable = stateListDrawable2;
        this.mHorizontalTrackDrawable = drawable2;
        this.mVerticalThumbWidth = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(i, drawable.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(i, drawable2.getIntrinsicWidth());
        this.mScrollbarMinimumRange = i2;
        this.mMargin = i3;
        stateListDrawable.setAlpha(PrivateKeyType.INVALID);
        drawable.setAlpha(PrivateKeyType.INVALID);
        ofFloat.addListener(new AnimatorListener());
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 1, null));
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                RecyclerView.LayoutManager layoutManager = recyclerView2.mLayout;
                if (layoutManager != null) {
                    layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
                }
                recyclerView2.mItemDecorations.remove(this);
                if (recyclerView2.mItemDecorations.isEmpty()) {
                    recyclerView2.setWillNotDraw(recyclerView2.getOverScrollMode() == 2);
                }
                recyclerView2.markItemDecorInsetsDirty();
                recyclerView2.requestLayout();
                RecyclerView recyclerView3 = this.mRecyclerView;
                recyclerView3.mOnItemTouchListeners.remove(this);
                if (recyclerView3.mInterceptingOnItemTouchListener == this) {
                    recyclerView3.mInterceptingOnItemTouchListener = null;
                }
                this.mRecyclerView.removeOnScrollListener$ar$class_merging(api23Impl);
                cancelHide();
            }
            this.mRecyclerView = recyclerView;
            if (recyclerView != null) {
                recyclerView.addItemDecoration$ar$class_merging(this);
                this.mRecyclerView.mOnItemTouchListeners.add(this);
                this.mRecyclerView.addOnScrollListener$ar$class_merging(api23Impl);
            }
        }
    }

    private final void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private final boolean isLayoutRTL() {
        if (this.mRecyclerView.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    private final void resetHideDelay(int i) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, i);
    }

    private static final int scrollTo$ar$ds(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    final boolean isPointInsideHorizontalThumb(float f, float f2) {
        if (f2 >= this.mRecyclerViewHeight - this.mHorizontalThumbHeight) {
            int i = this.mHorizontalThumbCenterX;
            int i2 = this.mHorizontalThumbWidth / 2;
            if (f >= i - i2 && f <= i + i2) {
                return true;
            }
            return false;
        }
        return false;
    }

    final boolean isPointInsideVerticalThumb(float f, float f2) {
        if (isLayoutRTL()) {
            if (f > this.mVerticalThumbWidth) {
                return false;
            }
        } else if (f < this.mRecyclerViewWidth - this.mVerticalThumbWidth) {
            return false;
        }
        int i = this.mVerticalThumbCenterY;
        int i2 = this.mVerticalThumbHeight / 2;
        if (f2 >= i - i2 && f2 <= i + i2) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl
    public final void onDrawOver$ar$ds(Canvas canvas, RecyclerView recyclerView) {
        if (this.mRecyclerViewWidth == this.mRecyclerView.getWidth() && this.mRecyclerViewHeight == this.mRecyclerView.getHeight()) {
            if (this.mAnimationState != 0) {
                if (this.mNeedVerticalScrollbar) {
                    int i = this.mRecyclerViewWidth;
                    int i2 = this.mVerticalThumbWidth;
                    int i3 = i - i2;
                    int i4 = this.mVerticalThumbCenterY;
                    int i5 = this.mVerticalThumbHeight;
                    int i6 = i4 - (i5 / 2);
                    this.mVerticalThumbDrawable.setBounds(0, 0, i2, i5);
                    this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
                    float f = i6;
                    int i7 = -i6;
                    if (isLayoutRTL()) {
                        this.mVerticalTrackDrawable.draw(canvas);
                        canvas.translate(this.mVerticalThumbWidth, f);
                        canvas.scale(-1.0f, 1.0f);
                        this.mVerticalThumbDrawable.draw(canvas);
                        canvas.scale(-1.0f, 1.0f);
                        canvas.translate(-this.mVerticalThumbWidth, i7);
                    } else {
                        canvas.translate(i3, 0.0f);
                        this.mVerticalTrackDrawable.draw(canvas);
                        canvas.translate(0.0f, f);
                        this.mVerticalThumbDrawable.draw(canvas);
                        canvas.translate(-i3, i7);
                    }
                }
                if (this.mNeedHorizontalScrollbar) {
                    int i8 = this.mRecyclerViewHeight;
                    int i9 = this.mHorizontalThumbHeight;
                    int i10 = this.mHorizontalThumbCenterX;
                    int i11 = this.mHorizontalThumbWidth;
                    this.mHorizontalThumbDrawable.setBounds(0, 0, i11, i9);
                    this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
                    canvas.translate(0.0f, i8 - i9);
                    this.mHorizontalTrackDrawable.draw(canvas);
                    canvas.translate(i10 - (i11 / 2), 0.0f);
                    this.mHorizontalThumbDrawable.draw(canvas);
                    canvas.translate(-r3, -r8);
                    return;
                }
                return;
            }
            return;
        }
        this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
        this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
        setState(0);
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public final boolean onInterceptTouchEvent$ar$ds(MotionEvent motionEvent) {
        int i = this.mState;
        if (i == 1) {
            boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!isPointInsideVerticalThumb) {
                if (!isPointInsideHorizontalThumb) {
                    return false;
                }
            } else if (!isPointInsideHorizontalThumb) {
                this.mDragState = 2;
                this.mVerticalDragY = (int) motionEvent.getY();
                setState(2);
                return true;
            }
            this.mDragState = 1;
            this.mHorizontalDragX = (int) motionEvent.getX();
            setState(2);
            return true;
        }
        if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public final void onTouchEvent$ar$ds(MotionEvent motionEvent) {
        if (this.mState != 0) {
            if (motionEvent.getAction() == 0) {
                boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
                boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
                if (!isPointInsideVerticalThumb) {
                    if (!isPointInsideHorizontalThumb) {
                        return;
                    }
                } else if (!isPointInsideHorizontalThumb) {
                    this.mDragState = 2;
                    this.mVerticalDragY = (int) motionEvent.getY();
                    setState(2);
                    return;
                }
                this.mDragState = 1;
                this.mHorizontalDragX = (int) motionEvent.getX();
                setState(2);
                return;
            }
            if (motionEvent.getAction() == 1 && this.mState == 2) {
                this.mVerticalDragY = 0.0f;
                this.mHorizontalDragX = 0.0f;
                setState(1);
                this.mDragState = 0;
                return;
            }
            if (motionEvent.getAction() == 2 && this.mState == 2) {
                show();
                if (this.mDragState == 1) {
                    float x = motionEvent.getX();
                    int[] iArr = this.mHorizontalRange;
                    int i = this.mMargin;
                    iArr[0] = i;
                    int i2 = this.mRecyclerViewWidth - i;
                    iArr[1] = i2;
                    float max = Math.max(i, Math.min(i2, x));
                    if (Math.abs(this.mHorizontalThumbCenterX - max) >= 2.0f) {
                        int scrollTo$ar$ds = scrollTo$ar$ds(this.mHorizontalDragX, max, iArr, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
                        if (scrollTo$ar$ds != 0) {
                            this.mRecyclerView.scrollBy(scrollTo$ar$ds, 0);
                        }
                        this.mHorizontalDragX = max;
                    }
                }
                if (this.mDragState == 2) {
                    float y = motionEvent.getY();
                    int[] iArr2 = this.mVerticalRange;
                    int i3 = this.mMargin;
                    iArr2[0] = i3;
                    int i4 = this.mRecyclerViewHeight - i3;
                    iArr2[1] = i4;
                    float max2 = Math.max(i3, Math.min(i4, y));
                    if (Math.abs(this.mVerticalThumbCenterY - max2) >= 2.0f) {
                        int scrollTo$ar$ds2 = scrollTo$ar$ds(this.mVerticalDragY, max2, iArr2, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
                        if (scrollTo$ar$ds2 != 0) {
                            this.mRecyclerView.scrollBy(0, scrollTo$ar$ds2);
                        }
                        this.mVerticalDragY = max2;
                    }
                }
            }
        }
    }

    public final void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    final void setState(int i) {
        if (i == 2 && this.mState != 2) {
            this.mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (i == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && i != 2) {
            this.mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
            resetHideDelay(1200);
        } else if (i == 1) {
            resetHideDelay(1500);
        }
        this.mState = i;
    }

    public final void show() {
        int i = this.mAnimationState;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                this.mShowHideAnimator.cancel();
            }
        }
        this.mAnimationState = 1;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.mShowHideAnimator.setDuration(500L);
        this.mShowHideAnimator.setStartDelay(0L);
        this.mShowHideAnimator.start();
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public final void onRequestDisallowInterceptTouchEvent$ar$ds() {
    }
}
