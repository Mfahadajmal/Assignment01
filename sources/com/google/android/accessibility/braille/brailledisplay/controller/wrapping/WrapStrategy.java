package com.google.android.accessibility.braille.brailledisplay.controller.wrapping;

import android.support.v7.widget.DrawableUtils;
import android.util.SparseIntArray;
import com.google.android.accessibility.braille.translate.TranslationResult;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WrapStrategy {
    public int displayEnd;
    public int displayStart;
    public final int displayWidth;
    public int endIndexOfInput;
    public int startIndexOfInput;
    public TranslationResult translation;
    public boolean isValid = false;
    public final SparseIntArray splitPoints = new SparseIntArray();
    public final SparseIntArray breakPoints = new SparseIntArray();
    public final SparseIntArray lineBreaks = new SparseIntArray();

    public WrapStrategy(int i) {
        this.displayWidth = i;
    }

    private static final int findPointIndex$ar$ds(SparseIntArray sparseIntArray, int i) {
        int indexOfKey = sparseIntArray.indexOfKey(i);
        if (indexOfKey >= 0) {
            return indexOfKey;
        }
        int i2 = ~indexOfKey;
        if (i2 <= 0) {
            return -1;
        }
        return i2 - 1;
    }

    public abstract void calculateBreakPoints();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int calculateWordWrapPivot(int i) {
        int size = this.translation.cells().size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.lineBreaks.size() && this.lineBreaks.keyAt(i2) < i) {
            int i4 = i2 + 1;
            int keyAt = this.lineBreaks.keyAt(i2);
            if (i2 != this.lineBreaks.size() - 1) {
                size = this.lineBreaks.keyAt(i4);
            }
            i2 = i4;
            i3 = keyAt;
        }
        return DrawableUtils.Api29Impl.findWordWrapCutPoint(this.translation.cells(), i, Math.max(0, i3 - 1), size + 1);
    }

    public final int findLeftLimit(SparseIntArray sparseIntArray, int i) {
        int findPointIndex$ar$ds = findPointIndex$ar$ds(sparseIntArray, i);
        if (findPointIndex$ar$ds >= 0) {
            int keyAt = sparseIntArray.keyAt(findPointIndex$ar$ds);
            if (keyAt < i) {
                return keyAt;
            }
            if (findPointIndex$ar$ds > 0) {
                return sparseIntArray.keyAt(findPointIndex$ar$ds - 1);
            }
            return 0;
        }
        return 0;
    }

    public final int findRightLimit(SparseIntArray sparseIntArray, int i) {
        int findPointIndex$ar$ds = findPointIndex$ar$ds(sparseIntArray, i) + 1;
        if (findPointIndex$ar$ds >= sparseIntArray.size()) {
            return this.translation.cells().size();
        }
        return sparseIntArray.keyAt(findPointIndex$ar$ds);
    }

    public final int getDisplayEnd() {
        return Math.min(getDisplayStart() + this.displayWidth, this.displayEnd);
    }

    public final int getDisplayStart() {
        return Math.max(0, this.displayStart);
    }

    public final void panTo(int i, boolean z) {
        if (this.isValid) {
            if (i <= 0) {
                i = 0;
            } else if (i >= this.translation.cells().size() - 1) {
                i = this.translation.cells().size() - 1;
            }
            if (!z && this.lineBreaks.indexOfKey(i) < 0) {
                recalculateLineBreaks(i);
            }
            int findPointIndex$ar$ds = findPointIndex$ar$ds(this.lineBreaks, i);
            if (findPointIndex$ar$ds != -1 && findPointIndex$ar$ds < this.lineBreaks.size() - 1) {
                this.displayStart = this.lineBreaks.keyAt(findPointIndex$ar$ds);
                this.displayEnd = this.lineBreaks.keyAt(findPointIndex$ar$ds + 1);
            }
        }
    }

    public abstract void recalculateLineBreaks(int i);
}
