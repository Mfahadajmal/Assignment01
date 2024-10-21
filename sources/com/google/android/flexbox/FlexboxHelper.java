package com.google.android.flexbox;

import _COROUTINE._BOUNDARY;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class FlexboxHelper {
    private boolean[] mChildrenFrozen;
    public final FlexContainer mFlexContainer;
    int[] mIndexToFlexLine;
    long[] mMeasureSpecCache;
    private long[] mMeasuredSizeCache;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Order implements Comparable {
        int index;
        int order;

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            Order order = (Order) obj;
            int i = this.order;
            int i2 = order.order;
            if (i != i2) {
                return i - i2;
            }
            return this.index - order.index;
        }

        public final String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + "}";
        }
    }

    public FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    private final void addFlexLine(List list, FlexLine flexLine, int i, int i2) {
        flexLine.mSumCrossSizeBefore = i2;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = i;
        list.add(flexLine);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void checkSizeConstraints(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.getMinWidth()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L26
            int r1 = r0.getMaxWidth()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L32
            int r2 = r0.getMinHeight()
            goto L3e
        L32:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L3d
            int r2 = r0.getMaxHeight()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.updateMeasureCache(r8, r1, r0, r7)
            com.google.android.flexbox.FlexContainer r0 = r6.mFlexContainer
            r0.updateViewCache(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.checkSizeConstraints(android.view.View, int):void");
    }

    private static final List constructFlexLinesForAlignContentCenter$ar$ds(List list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.mCrossSize = (i - i2) / 2;
        int size = list.size();
        int i3 = 0;
        while (i3 < size) {
            if (i3 == 0) {
                arrayList.add(flexLine);
                i3 = 0;
            }
            arrayList.add((FlexLine) list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(flexLine);
            }
            i3++;
        }
        return arrayList;
    }

    private final void expandFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        double d;
        double d2;
        float f = flexLine.mTotalFlexGrow;
        float f2 = 0.0f;
        if (f > 0.0f && i3 >= (i5 = flexLine.mMainSize)) {
            float f3 = (i3 - i5) / f;
            flexLine.mMainSize = i4 + flexLine.mDividerLengthInMainSize;
            int i8 = 0;
            if (!z) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            boolean z2 = false;
            int i9 = 0;
            float f4 = 0.0f;
            while (i8 < flexLine.mItemCount) {
                int i10 = flexLine.mFirstIndex + i8;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i10);
                if (reorderedFlexItemAt != null && reorderedFlexItemAt.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection != 0 && flexDirection != 1) {
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr = this.mMeasuredSizeCache;
                        if (jArr != null) {
                            measuredHeight = extractHigherInt(jArr[i10]);
                        }
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr2 = this.mMeasuredSizeCache;
                        if (jArr2 != null) {
                            measuredWidth = (int) jArr2[i10];
                        }
                        if (!this.mChildrenFrozen[i10] && flexItem.getFlexGrow() > f2) {
                            float flexGrow = measuredHeight + (flexItem.getFlexGrow() * f3);
                            if (i8 == flexLine.mItemCount - 1) {
                                flexGrow += f4;
                                f4 = f2;
                            }
                            int round = Math.round(flexGrow);
                            if (round > flexItem.getMaxHeight()) {
                                round = flexItem.getMaxHeight();
                                this.mChildrenFrozen[i10] = true;
                                flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                                i6 = i5;
                                z2 = true;
                            } else {
                                f4 += flexGrow - round;
                                i6 = i5;
                                double d3 = f4;
                                if (d3 > 1.0d) {
                                    round++;
                                    d2 = d3 - 1.0d;
                                } else if (d3 < -1.0d) {
                                    round--;
                                    d2 = d3 + 1.0d;
                                }
                                f4 = (float) d2;
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec);
                            measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i10, childWidthMeasureSpecInternal, makeMeasureSpec, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                            measuredHeight = measuredHeight2;
                        } else {
                            i6 = i5;
                        }
                        i7 = Math.max(i9, measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom();
                    } else {
                        i6 = i5;
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr3 = this.mMeasuredSizeCache;
                        if (jArr3 != null) {
                            measuredWidth2 = (int) jArr3[i10];
                        }
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr4 = this.mMeasuredSizeCache;
                        if (jArr4 != null) {
                            measuredHeight3 = extractHigherInt(jArr4[i10]);
                        }
                        if (!this.mChildrenFrozen[i10] && flexItem.getFlexGrow() > 0.0f) {
                            float flexGrow2 = measuredWidth2 + (flexItem.getFlexGrow() * f3);
                            if (i8 == flexLine.mItemCount - 1) {
                                flexGrow2 += f4;
                                f4 = 0.0f;
                            }
                            int round2 = Math.round(flexGrow2);
                            if (round2 > flexItem.getMaxWidth()) {
                                round2 = flexItem.getMaxWidth();
                                z2 = true;
                                this.mChildrenFrozen[i10] = true;
                                flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                            } else {
                                f4 += flexGrow2 - round2;
                                double d4 = f4;
                                if (d4 > 1.0d) {
                                    round2++;
                                    d = d4 - 1.0d;
                                } else if (d4 < -1.0d) {
                                    round2--;
                                    d = d4 + 1.0d;
                                }
                                f4 = (float) d;
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            reorderedFlexItemAt.measure(makeMeasureSpec2, childHeightMeasureSpecInternal);
                            int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i10, makeMeasureSpec2, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i10, reorderedFlexItemAt);
                            measuredWidth2 = measuredWidth3;
                            measuredHeight3 = measuredHeight4;
                        }
                        int max = Math.max(i9, measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                        i7 = max;
                    }
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i7);
                    i9 = i7;
                } else {
                    i6 = i5;
                }
                i8++;
                i5 = i6;
                f2 = 0.0f;
            }
            int i11 = i5;
            if (z2 && i11 != flexLine.mMainSize) {
                expandFlexItems(i, i2, flexLine, i3, i4, true);
            }
        }
    }

    private final int getChildHeightMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i, flexContainer.getPaddingTop() + flexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (size < flexItem.getMinHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    private final int getChildWidthMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i, flexContainer.getPaddingLeft() + flexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (size < flexItem.getMinWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    private static final int getFlexItemMarginEndCross$ar$ds(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    private static final int getFlexItemMarginEndMain$ar$ds(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    private static final int getFlexItemMarginStartCross$ar$ds(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    private static final int getFlexItemMarginStartMain$ar$ds(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    private static final int getFlexItemSizeCross$ar$ds(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private static final int getViewMeasuredSizeMain$ar$ds(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private static final boolean isLastFlexItem$ar$ds(int i, int i2, FlexLine flexLine) {
        if (i == i2 - 1 && flexLine.getItemCountNotGone() != 0) {
            return true;
        }
        return false;
    }

    static final long makeCombinedLong$ar$ds(int i, int i2) {
        return (i & 4294967295L) | (i2 << 32);
    }

    private final void shrinkFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        float f;
        float f2;
        int i5;
        FlexLine flexLine2 = flexLine;
        int i6 = flexLine2.mMainSize;
        float f3 = flexLine2.mTotalFlexShrink;
        float f4 = 0.0f;
        if (f3 > 0.0f && i3 <= i6) {
            float f5 = (i6 - i3) / f3;
            flexLine2.mMainSize = i4 + flexLine2.mDividerLengthInMainSize;
            int i7 = 0;
            if (!z) {
                flexLine2.mCrossSize = Integer.MIN_VALUE;
            }
            boolean z2 = false;
            int i8 = 0;
            float f6 = 0.0f;
            while (i7 < flexLine2.mItemCount) {
                int i9 = flexLine2.mFirstIndex + i7;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i9);
                if (reorderedFlexItemAt != null && reorderedFlexItemAt.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection != 0 && flexDirection != 1) {
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr = this.mMeasuredSizeCache;
                        if (jArr != null) {
                            measuredHeight = extractHigherInt(jArr[i9]);
                        }
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr2 = this.mMeasuredSizeCache;
                        if (jArr2 != null) {
                            measuredWidth = (int) jArr2[i9];
                        }
                        if (!this.mChildrenFrozen[i9] && flexItem.getFlexShrink() > f4) {
                            float flexShrink = measuredHeight - (flexItem.getFlexShrink() * f5);
                            if (i7 == flexLine2.mItemCount - 1) {
                                flexShrink += f6;
                                f6 = f4;
                            }
                            int round = Math.round(flexShrink);
                            if (round < flexItem.getMinHeight()) {
                                round = flexItem.getMinHeight();
                                this.mChildrenFrozen[i9] = true;
                                flexLine2.mTotalFlexShrink -= flexItem.getFlexShrink();
                                z2 = true;
                            } else {
                                f6 += flexShrink - round;
                                double d = f6;
                                if (d > 1.0d) {
                                    round++;
                                    f6 -= 1.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f6 += 1.0f;
                                }
                                flexLine2 = flexLine;
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine2.mSumCrossSizeBefore);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec);
                            int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i9, childWidthMeasureSpecInternal, makeMeasureSpec, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i9, reorderedFlexItemAt);
                            measuredWidth = measuredWidth2;
                            measuredHeight = measuredHeight2;
                        }
                        i5 = Math.max(i8, measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom();
                        f2 = f5;
                        f = 0.0f;
                    } else {
                        int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr3 = this.mMeasuredSizeCache;
                        if (jArr3 != null) {
                            measuredWidth3 = (int) jArr3[i9];
                        }
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr4 = this.mMeasuredSizeCache;
                        if (jArr4 != null) {
                            measuredHeight3 = extractHigherInt(jArr4[i9]);
                        }
                        if (!this.mChildrenFrozen[i9]) {
                            f = 0.0f;
                            if (flexItem.getFlexShrink() > 0.0f) {
                                float flexShrink2 = measuredWidth3 - (flexItem.getFlexShrink() * f5);
                                if (i7 == flexLine2.mItemCount - 1) {
                                    flexShrink2 += f6;
                                    f6 = 0.0f;
                                }
                                int round2 = Math.round(flexShrink2);
                                if (round2 < flexItem.getMinWidth()) {
                                    round2 = flexItem.getMinWidth();
                                    z2 = true;
                                    this.mChildrenFrozen[i9] = true;
                                    flexLine2.mTotalFlexShrink -= flexItem.getFlexShrink();
                                    f2 = f5;
                                } else {
                                    f6 += flexShrink2 - round2;
                                    f2 = f5;
                                    double d2 = f6;
                                    if (d2 > 1.0d) {
                                        round2++;
                                        f6 -= 1.0f;
                                    } else if (d2 < -1.0d) {
                                        round2--;
                                        f6 += 1.0f;
                                    }
                                }
                                int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine2.mSumCrossSizeBefore);
                                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                                reorderedFlexItemAt.measure(makeMeasureSpec2, childHeightMeasureSpecInternal);
                                int measuredWidth4 = reorderedFlexItemAt.getMeasuredWidth();
                                int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                                updateMeasureCache(i9, makeMeasureSpec2, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                                this.mFlexContainer.updateViewCache(i9, reorderedFlexItemAt);
                                measuredWidth3 = measuredWidth4;
                                measuredHeight3 = measuredHeight4;
                            } else {
                                f2 = f5;
                            }
                        } else {
                            f2 = f5;
                            f = 0.0f;
                        }
                        int max = Math.max(i8, measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += measuredWidth3 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                        i5 = max;
                    }
                    flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, i5);
                    i8 = i5;
                } else {
                    f = f4;
                    f2 = f5;
                }
                i7++;
                f5 = f2;
                f4 = f;
            }
            if (z2 && i6 != flexLine2.mMainSize) {
                shrinkFlexItems(i, i2, flexLine, i3, i4, true);
            }
        }
    }

    private final void stretchViewHorizontally(View view, int i, int i2) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            measuredHeight = extractHigherInt(jArr[i2]);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        updateMeasureCache(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    private final void stretchViewVertically(View view, int i, int i2) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            measuredWidth = (int) jArr[i2];
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        updateMeasureCache(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    private final void updateMeasureCache(int i, int i2, int i3, View view) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr != null) {
            jArr[i] = makeCombinedLong$ar$ds(i2, i3);
        }
        long[] jArr2 = this.mMeasuredSizeCache;
        if (jArr2 != null) {
            jArr2[i] = makeCombinedLong$ar$ds(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0226, code lost:
    
        if (r14 > (r27 + 1)) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0235, code lost:
    
        if (r7 < (r3 + r20)) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03b4 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void calculateFlexLines$ar$class_merging(com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations.Builder r31, int r32, int r33, int r34, int r35, int r36, java.util.List r37) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.calculateFlexLines$ar$class_merging(com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations$Builder, int, int, int, int, int, java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void calculateHorizontalFlexLines$ar$class_merging(ApplicationExitConfigurations.Builder builder, int i, int i2, int i3, int i4, List list) {
        calculateFlexLines$ar$class_merging(builder, i, i2, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void calculateVerticalFlexLines$ar$class_merging(ApplicationExitConfigurations.Builder builder, int i, int i2, int i3, int i4, List list) {
        calculateFlexLines$ar$class_merging(builder, i2, i, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearFlexLines(List list, int i) {
        int i2 = this.mIndexToFlexLine[i];
        if (i2 == -1) {
            i2 = 0;
        }
        if (list.size() > i2) {
            list.subList(i2, list.size()).clear();
        }
        int[] iArr = this.mIndexToFlexLine;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.mMeasureSpecCache;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    public final List createOrders(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            FlexItem flexItem = (FlexItem) this.mFlexContainer.getFlexItemAt(i2).getLayoutParams();
            Order order = new Order();
            order.order = flexItem.getOrder();
            order.index = i2;
            arrayList.add(order);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void determineCrossSize(int i, int i2, int i3) {
        int mode;
        int size;
        int round;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (flexDirection != 0 && flexDirection != 1) {
            if (flexDirection != 2 && flexDirection != 3) {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(flexDirection, "Invalid flex direction: "));
            }
            mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
        } else {
            mode = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
        }
        FlexContainer flexContainer = this.mFlexContainer;
        List<FlexLine> flexLinesInternal = flexContainer.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = flexContainer.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                ((FlexLine) flexLinesInternal.get(0)).mCrossSize = size - i3;
                return;
            }
            if (flexLinesInternal.size() >= 2) {
                FlexContainer flexContainer2 = this.mFlexContainer;
                int alignContent = flexContainer2.getAlignContent();
                if (alignContent != 1) {
                    if (alignContent != 2) {
                        if (alignContent != 3) {
                            if (alignContent != 4) {
                                if (alignContent == 5 && sumOfCrossSize < size) {
                                    float size2 = flexLinesInternal.size();
                                    int size3 = flexLinesInternal.size();
                                    float f = 0.0f;
                                    while (i4 < size3) {
                                        FlexLine flexLine = (FlexLine) flexLinesInternal.get(i4);
                                        float f2 = flexLine.mCrossSize + ((size - sumOfCrossSize) / size2);
                                        if (i4 == flexLinesInternal.size() - 1) {
                                            f2 += f;
                                            f = 0.0f;
                                        }
                                        int round2 = Math.round(f2);
                                        f += f2 - round2;
                                        if (f > 1.0f) {
                                            round2++;
                                            f -= 1.0f;
                                        } else if (f < -1.0f) {
                                            round2--;
                                            f += 1.0f;
                                        }
                                        flexLine.mCrossSize = round2;
                                        i4++;
                                    }
                                    return;
                                }
                                return;
                            }
                            if (sumOfCrossSize >= size) {
                                flexContainer2.setFlexLines(constructFlexLinesForAlignContentCenter$ar$ds(flexLinesInternal, size, sumOfCrossSize));
                                return;
                            }
                            int size4 = flexLinesInternal.size();
                            int i5 = (size - sumOfCrossSize) / (size4 + size4);
                            ArrayList arrayList = new ArrayList();
                            FlexLine flexLine2 = new FlexLine();
                            flexLine2.mCrossSize = i5;
                            for (FlexLine flexLine3 : flexLinesInternal) {
                                arrayList.add(flexLine2);
                                arrayList.add(flexLine3);
                                arrayList.add(flexLine2);
                            }
                            this.mFlexContainer.setFlexLines(arrayList);
                            return;
                        }
                        if (sumOfCrossSize < size) {
                            int size5 = flexLinesInternal.size() - 1;
                            ArrayList arrayList2 = new ArrayList();
                            int size6 = flexLinesInternal.size();
                            float f3 = 0.0f;
                            while (i4 < size6) {
                                arrayList2.add((FlexLine) flexLinesInternal.get(i4));
                                if (i4 != flexLinesInternal.size() - 1) {
                                    FlexLine flexLine4 = new FlexLine();
                                    float f4 = (size - sumOfCrossSize) / size5;
                                    if (i4 == flexLinesInternal.size() - 2) {
                                        int round3 = Math.round(f3 + f4);
                                        flexLine4.mCrossSize = round3;
                                        round = round3;
                                        f3 = 0.0f;
                                    } else {
                                        round = Math.round(f4);
                                        flexLine4.mCrossSize = round;
                                    }
                                    f3 += f4 - round;
                                    if (f3 > 1.0f) {
                                        flexLine4.mCrossSize = round + 1;
                                        f3 -= 1.0f;
                                    } else if (f3 < -1.0f) {
                                        flexLine4.mCrossSize = round - 1;
                                        f3 += 1.0f;
                                    }
                                    arrayList2.add(flexLine4);
                                }
                                i4++;
                            }
                            this.mFlexContainer.setFlexLines(arrayList2);
                            return;
                        }
                        return;
                    }
                    flexContainer2.setFlexLines(constructFlexLinesForAlignContentCenter$ar$ds(flexLinesInternal, size, sumOfCrossSize));
                    return;
                }
                int i6 = size - sumOfCrossSize;
                FlexLine flexLine5 = new FlexLine();
                flexLine5.mCrossSize = i6;
                flexLinesInternal.add(0, flexLine5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void determineMainSize(int i, int i2) {
        determineMainSize(i, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureIndexToFlexLine(int i) {
        int[] iArr = this.mIndexToFlexLine;
        if (iArr == null) {
            this.mIndexToFlexLine = new int[Math.max(i, 10)];
            return;
        }
        int length = iArr.length;
        if (length < i) {
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, Math.max(length + length, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureMeasureSpecCache(int i) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr == null) {
            this.mMeasureSpecCache = new long[Math.max(i, 10)];
            return;
        }
        int length = jArr.length;
        if (length < i) {
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, Math.max(length + length, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureMeasuredSizeCache(int i) {
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr == null) {
            this.mMeasuredSizeCache = new long[Math.max(i, 10)];
            return;
        }
        int length = jArr.length;
        if (length < i) {
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, Math.max(length + length, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int extractHigherInt(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0 && alignItems != 4) {
            if (alignItems == 3) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int max = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                } else {
                    int max2 = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                    view.layout(i, i2 - max2, i3, i4 - max2);
                    return;
                }
            }
            if (alignItems == 1) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i3, i6 - flexItem.getMarginBottom());
                    return;
                } else {
                    view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop());
                    return;
                }
            }
            if (alignItems == 2) {
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else {
                    int i8 = i2 - measuredHeight;
                    view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                    return;
                }
            }
            return;
        }
        if (this.mFlexContainer.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.getMarginTop(), i3, i4 + flexItem.getMarginTop());
        } else {
            view.layout(i, i2 - flexItem.getMarginBottom(), i3, i4 - flexItem.getMarginBottom());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0 && alignItems != 4 && alignItems != 3) {
            if (alignItems == 1) {
                if (!z) {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i4);
                    return;
                } else {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i4);
                    return;
                }
            }
            if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + marginLayoutParams.getMarginStart()) - marginLayoutParams.getMarginEnd()) / 2;
                if (!z) {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                } else {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                }
            }
            return;
        }
        if (!z) {
            view.layout(i + flexItem.getMarginLeft(), i2, i3 + flexItem.getMarginLeft(), i4);
        } else {
            view.layout(i - flexItem.getMarginRight(), i2, i3 - flexItem.getMarginRight(), i4);
        }
    }

    public final int[] sortOrdersIntoReorderedIndices(int i, List list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Order order = (Order) it.next();
            int i3 = order.index;
            iArr[i2] = i3;
            sparseIntArray.append(i3, order.order);
            i2++;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void stretchViews() {
        stretchViews(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void determineMainSize(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        int paddingRight;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null) {
            this.mChildrenFrozen = new boolean[Math.max(flexItemCount, 10)];
        } else {
            int length = zArr.length;
            if (length < flexItemCount) {
                this.mChildrenFrozen = new boolean[Math.max(length + length, flexItemCount)];
            } else {
                Arrays.fill(zArr, false);
            }
        }
        if (i3 >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        FlexContainer flexContainer = this.mFlexContainer;
        int flexDirection = flexContainer.getFlexDirection();
        int flexDirection2 = flexContainer.getFlexDirection();
        if (flexDirection2 != 0 && flexDirection2 != 1) {
            if (flexDirection2 != 2 && flexDirection2 != 3) {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(flexDirection, "Invalid flex direction: "));
            }
            int mode = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode != 1073741824) {
                size = this.mFlexContainer.getLargestMainSize();
            }
            FlexContainer flexContainer2 = this.mFlexContainer;
            paddingLeft = flexContainer2.getPaddingTop();
            paddingRight = flexContainer2.getPaddingBottom();
        } else {
            int mode2 = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            int largestMainSize = this.mFlexContainer.getLargestMainSize();
            if (mode2 != 1073741824) {
                size = Math.min(largestMainSize, size);
            }
            FlexContainer flexContainer3 = this.mFlexContainer;
            paddingLeft = flexContainer3.getPaddingLeft();
            paddingRight = flexContainer3.getPaddingRight();
        }
        int i4 = paddingLeft + paddingRight;
        int[] iArr = this.mIndexToFlexLine;
        List flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i5 = iArr != null ? iArr[i3] : 0; i5 < size2; i5++) {
            FlexLine flexLine = (FlexLine) flexLinesInternal.get(i5);
            if (flexLine.mMainSize < size) {
                expandFlexItems(i, i2, flexLine, size, i4, false);
            } else {
                shrinkFlexItems(i, i2, flexLine, size, i4, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void stretchViews(int i) {
        View reorderedFlexItemAt;
        if (i >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        FlexContainer flexContainer = this.mFlexContainer;
        int flexDirection = flexContainer.getFlexDirection();
        if (flexContainer.getAlignItems() == 4) {
            int[] iArr = this.mIndexToFlexLine;
            List flexLinesInternal = flexContainer.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
                FlexLine flexLine = (FlexLine) flexLinesInternal.get(i2);
                int i3 = flexLine.mItemCount;
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = flexLine.mFirstIndex + i4;
                    if (i4 < this.mFlexContainer.getFlexItemCount() && (reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i5)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                        FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                        if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                            if (flexDirection != 0 && flexDirection != 1) {
                                if (flexDirection != 2 && flexDirection != 3) {
                                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(flexDirection, "Invalid flex direction: "));
                                }
                                stretchViewHorizontally(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                            } else {
                                stretchViewVertically(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                            }
                        }
                    }
                }
            }
            return;
        }
        for (FlexLine flexLine2 : flexContainer.getFlexLinesInternal()) {
            for (Integer num : flexLine2.mIndicesAlignSelfStretch) {
                View reorderedFlexItemAt2 = this.mFlexContainer.getReorderedFlexItemAt(num.intValue());
                if (flexDirection != 0 && flexDirection != 1) {
                    if (flexDirection != 2 && flexDirection != 3) {
                        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(flexDirection, "Invalid flex direction: "));
                    }
                    stretchViewHorizontally(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                } else {
                    stretchViewVertically(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                }
            }
        }
    }
}
