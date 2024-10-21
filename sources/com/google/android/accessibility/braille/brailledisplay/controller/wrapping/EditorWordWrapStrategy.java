package com.google.android.accessibility.braille.brailledisplay.controller.wrapping;

import android.support.v7.widget.DrawableUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditorWordWrapStrategy extends WrapStrategy {
    public EditorWordWrapStrategy(int i) {
        super(i);
    }

    private final void addBreakPoint(int i) {
        this.breakPoints.append(i, 2);
        this.breakPoints.append(i, 1);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy
    public final void calculateBreakPoints() {
        int findWordWrapCutPoint;
        int i;
        int i2 = this.startIndexOfInput;
        int i3 = this.displayWidth;
        if (i2 < i3) {
            findWordWrapCutPoint = Math.min(this.endIndexOfInput, i3);
        } else {
            findWordWrapCutPoint = DrawableUtils.Api29Impl.findWordWrapCutPoint(this.translation.cells(), this.displayWidth, 0, this.startIndexOfInput);
        }
        DrawableUtils.Api29Impl.addWordWrapBreakPoints(this.breakPoints, this.translation.cells(), 0, this.startIndexOfInput - 1);
        int i4 = 0;
        while (true) {
            i = (int) (this.displayWidth * 0.25d);
            if (i4 >= this.startIndexOfInput) {
                break;
            }
            if (i4 >= findWordWrapCutPoint) {
                int findWordWrapCutPoint2 = DrawableUtils.Api29Impl.findWordWrapCutPoint(this.translation.cells(), Math.min(this.displayWidth + findWordWrapCutPoint, this.endIndexOfInput), Math.max(0, findWordWrapCutPoint), this.startIndexOfInput);
                int i5 = this.startIndexOfInput;
                if (findWordWrapCutPoint2 >= i5) {
                    int i6 = i5 - findWordWrapCutPoint;
                    if (i6 > 0 && i6 < i) {
                        findWordWrapCutPoint = DrawableUtils.Api29Impl.findWordWrapCutPoint(this.translation.cells(), findWordWrapCutPoint - (i - i6), Math.max(0, findWordWrapCutPoint - this.displayWidth), this.startIndexOfInput);
                        addBreakPoint(findWordWrapCutPoint);
                    } else {
                        findWordWrapCutPoint += this.displayWidth;
                    }
                } else {
                    findWordWrapCutPoint = findWordWrapCutPoint2;
                }
            }
            i4++;
        }
        int i7 = this.startIndexOfInput;
        while (true) {
            int i8 = this.endIndexOfInput;
            if (i7 > i8) {
                break;
            }
            if (i7 >= findWordWrapCutPoint) {
                int i9 = this.displayWidth;
                if (findWordWrapCutPoint < i9 && i8 < i9) {
                    addBreakPoint(findWordWrapCutPoint);
                    break;
                } else {
                    int findWordWrapCutPoint3 = DrawableUtils.Api29Impl.findWordWrapCutPoint(this.translation.cells(), Math.min(findWordWrapCutPoint - i, this.endIndexOfInput), Math.max(0, findWordWrapCutPoint - this.displayWidth), findWordWrapCutPoint);
                    addBreakPoint(findWordWrapCutPoint3);
                    findWordWrapCutPoint = findWordWrapCutPoint3 + this.displayWidth;
                }
            }
            i7++;
        }
        if (this.endIndexOfInput < this.translation.cells().size() && this.translation.cells().get(this.endIndexOfInput).isEmpty()) {
            addBreakPoint(this.endIndexOfInput + 1);
        }
        DrawableUtils.Api29Impl.addWordWrapBreakPoints(this.breakPoints, this.translation.cells(), this.endIndexOfInput + 1, this.translation.cells().size());
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy
    public final void recalculateLineBreaks(int i) {
        if (this.lineBreaks.indexOfKey(i) < 0) {
            this.lineBreaks.append(calculateWordWrapPivot(i), 1);
        }
    }
}
