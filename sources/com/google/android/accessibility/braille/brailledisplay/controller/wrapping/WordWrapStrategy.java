package com.google.android.accessibility.braille.brailledisplay.controller.wrapping;

import android.support.v7.widget.DrawableUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WordWrapStrategy extends WrapStrategy {
    public WordWrapStrategy(int i) {
        super(i);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy
    public final void calculateBreakPoints() {
        DrawableUtils.Api29Impl.addWordWrapBreakPoints(this.breakPoints, this.translation.cells(), 0, this.translation.cells().size());
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy
    public final void recalculateLineBreaks(int i) {
        if (this.lineBreaks.indexOfKey(i) < 0) {
            this.lineBreaks.append(calculateWordWrapPivot(i), 1);
        }
    }
}
