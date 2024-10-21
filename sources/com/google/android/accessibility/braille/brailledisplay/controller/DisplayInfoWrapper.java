package com.google.android.accessibility.braille.brailledisplay.controller;

import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisplayInfoWrapper {
    public final ContentHelper contentHelper;
    public DisplayInfo displayInfo;
    public boolean reachToBeginning;
    public boolean reachToEnd;

    public DisplayInfoWrapper(ContentHelper contentHelper) {
        this.contentHelper = contentHelper;
    }

    public final boolean hasDisplayInfo() {
        if (this.displayInfo != null) {
            return true;
        }
        return false;
    }

    public final boolean panDown() {
        int indexOfKey;
        this.reachToBeginning = false;
        if (hasDisplayInfo() && this.reachToEnd) {
            return false;
        }
        ContentHelper contentHelper = this.contentHelper;
        int i = this.displayInfo.source$ar$edu;
        WrapStrategy wrapStrategy = contentHelper.wrapStrategyRetriever.getWrapStrategy();
        DisplayInfo displayInfo = null;
        if (wrapStrategy.isValid && (indexOfKey = wrapStrategy.lineBreaks.indexOfKey(wrapStrategy.displayEnd)) >= 0 && indexOfKey < wrapStrategy.lineBreaks.size() - 1) {
            wrapStrategy.displayStart = wrapStrategy.displayEnd;
            wrapStrategy.displayEnd = wrapStrategy.lineBreaks.keyAt(indexOfKey + 1);
            displayInfo = contentHelper.getDisplayInfo$ar$edu(contentHelper.currentTranslationResult.text(), wrapStrategy.getDisplayStart(), wrapStrategy.getDisplayEnd(), contentHelper.currentTranslationResult.brailleToTextPositions(), i);
        }
        if (displayInfo == null) {
            this.reachToEnd = true;
            return false;
        }
        this.displayInfo = displayInfo;
        this.reachToEnd = false;
        return true;
    }

    public final void renewDisplayInfo$ar$ds(CharSequence charSequence, int i) {
        this.displayInfo = this.contentHelper.generateDisplayInfo(charSequence, i, false);
        reset();
    }

    public final void reset() {
        this.reachToBeginning = false;
        this.reachToEnd = false;
    }

    public final void retranslate() {
        DisplayInfo generateDisplayInfo;
        if (hasDisplayInfo() && this.displayInfo.source$ar$edu == 1) {
            ContentHelper contentHelper = this.contentHelper;
            CharSequence charSequence = contentHelper.originalText;
            if (charSequence == null) {
                generateDisplayInfo = null;
            } else {
                generateDisplayInfo = contentHelper.generateDisplayInfo(charSequence, 2, contentHelper.isSplitParagraphs);
            }
            this.displayInfo = generateDisplayInfo;
            reset();
        }
    }
}
