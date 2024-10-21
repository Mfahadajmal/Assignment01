package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    public Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    public int mDividerWidth;
    public int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(int i) {
            super(i, -2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    private static void setChildFrame$ar$ds(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    final void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    final void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2);
        }
        if (i == 1) {
            return new LayoutParams(-1);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public final int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.mBaselineAlignedChildIndex == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i3 = this.mBaselineChildTop;
            if (this.mOrientation == 1 && (i = this.mGravity & BrailleInputEvent.CMD_CONTROL_NEXT) != 48) {
                if (i != 16) {
                    if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                } else {
                    i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                }
            }
            return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            if ((this.mShowDividers & 1) == 0) {
                return false;
            }
            return true;
        }
        if (i == getChildCount()) {
            if ((this.mShowDividers & 4) == 0) {
                return false;
            }
            return true;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        do {
            i--;
            if (i < 0) {
                return false;
            }
        } while (getChildAt(i).getVisibility() == 8);
        return true;
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i;
        int left2;
        int bottom;
        if (this.mDivider != null) {
            int i2 = 0;
            if (this.mOrientation == 1) {
                int childCount = getChildCount();
                while (i2 < childCount) {
                    View childAt = getChildAt(i2);
                    if (childAt != null && childAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                        drawHorizontalDivider(canvas, (childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin) - this.mDividerHeight);
                    }
                    i2++;
                }
                if (hasDividerBeforeChildAt(childCount)) {
                    View childAt2 = getChildAt(childCount - 1);
                    if (childAt2 == null) {
                        bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
                    } else {
                        bottom = childAt2.getBottom() + ((LayoutParams) childAt2.getLayoutParams()).bottomMargin;
                    }
                    drawHorizontalDivider(canvas, bottom);
                    return;
                }
                return;
            }
            int childCount2 = getChildCount();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            while (i2 < childCount2) {
                View childAt3 = getChildAt(i2);
                if (childAt3 != null && childAt3.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                    LayoutParams layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    if (isLayoutRtl) {
                        left2 = childAt3.getRight() + layoutParams.rightMargin;
                    } else {
                        left2 = (childAt3.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                    }
                    drawVerticalDivider(canvas, left2);
                }
                i2++;
            }
            if (hasDividerBeforeChildAt(childCount2)) {
                View childAt4 = getChildAt(childCount2 - 1);
                if (childAt4 == null) {
                    if (isLayoutRtl) {
                        right = getPaddingLeft();
                    } else {
                        left = getWidth() - getPaddingRight();
                        i = this.mDividerWidth;
                        right = left - i;
                    }
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) childAt4.getLayoutParams();
                    if (isLayoutRtl) {
                        left = childAt4.getLeft() - layoutParams2.leftMargin;
                        i = this.mDividerWidth;
                        right = left - i;
                    } else {
                        right = childAt4.getRight() + layoutParams2.rightMargin;
                    }
                }
                drawVerticalDivider(canvas, right);
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x017e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x02ef, code lost:
    
        if (r10.width == (-1)) goto L143;
     */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0884  */
    /* JADX WARN: Removed duplicated region for block: B:340:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:344:0x06b6  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x07c0  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x07c3  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0638  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r41, int r42) {
        /*
            Method dump skipped, instructions count: 2244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.onMeasure(int, int):void");
    }

    public final void setBaselineAligned$ar$ds() {
        this.mBaselineAligned = false;
    }

    public final void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.LinearLayoutCompat, attributeSet, (TypedArray) obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable, i, 0);
        int i2 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(1, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(0, -1);
        if (i3 >= 0 && this.mGravity != i3) {
            i3 = (8388615 & i3) == 0 ? i3 | 8388611 : i3;
            this.mGravity = (i3 & BrailleInputEvent.CMD_CONTROL_NEXT) == 0 ? i3 | 48 : i3;
            requestLayout();
        }
        if (!obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getBoolean(2, true)) {
            setBaselineAligned$ar$ds();
        }
        this.mWeightSum = ((TypedArray) obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable).getFloat(4, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(3, -1);
        this.mUseLargestChild = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getBoolean(7, false);
        Drawable drawable = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(5);
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            setWillNotDraw(drawable == null);
            requestLayout();
        }
        this.mShowDividers = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(8, 0);
        this.mDividerPadding = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDimensionPixelSize(6, 0);
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }
}
