package com.google.android.accessibility.braille.brailledisplay.controller;

import android.util.Range;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.interfaces.SelectionRange;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AssembledResult {
    public final Range actionClickableByteRange;
    public final Range holdingsClickableByteRange;
    public final TranslationResult overlayTranslationResult;
    public final SelectionRange textByteSelection;
    public final ImmutableList textFieldTextClickableByteRange;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder {
        public Range actionClickableByteRange;
        public boolean appendAction;
        public boolean appendHint;
        public Range holdingsClickableByteRange;
        public final int holdingsPosition;
        public final BrailleWord holdingsWord;
        public final BrailleDisplayForBrailleIme.ResultForDisplay resultForDisplay;
        private final boolean showPassword;
        public int textDisplacement;
        public final String textFieldText;
        public final ImmutableList textFieldTextBrailleToTextPositions;
        public final ImmutableList textFieldTextToBraillePositions;
        public final BrailleWord textFieldWord;
        public final Range textSelectionRange;
        private final BrailleTranslator translator;
        public final StringBuilder textOnOverlay = new StringBuilder();
        public final BrailleWord brailleWord = new BrailleWord();
        public final List textFieldTextClickableByteRange = new ArrayList();
        public final List textToBraillePositions = new ArrayList();
        public final List brailleToTextPositions = new ArrayList();

        public Builder(BrailleTranslator brailleTranslator, BrailleDisplayForBrailleIme.ResultForDisplay resultForDisplay) {
            this.translator = brailleTranslator;
            this.resultForDisplay = resultForDisplay;
            boolean z = resultForDisplay.showPassword;
            this.showPassword = z;
            BrailleWord brailleWord = new BrailleWord(resultForDisplay.holdingsInfo.holdings.array());
            int i = resultForDisplay.holdingsInfo.position;
            String valueOf = String.valueOf(resultForDisplay.onScreenText);
            SelectionRange selectionRange = resultForDisplay.textSelection;
            Integer valueOf2 = Integer.valueOf(Math.min(selectionRange.start, selectionRange.end));
            SelectionRange selectionRange2 = resultForDisplay.textSelection;
            Range range = new Range(valueOf2, Integer.valueOf(Math.max(selectionRange2.start, selectionRange2.end)));
            this.textSelectionRange = range;
            if (z) {
                BrailleWord cells = translateIfPossible("•", -1).cells();
                this.holdingsWord = new BrailleWord(cells, brailleWord.size());
                this.holdingsPosition = cells.size() * i;
                this.textFieldText = ContextDataProvider.repeat$ar$ds(valueOf.length());
            } else {
                this.holdingsWord = brailleWord;
                this.holdingsPosition = i;
                this.textFieldText = valueOf;
            }
            TranslationResult translateIfPossible = translateIfPossible(this.textFieldText, ((Integer) range.getLower()).intValue());
            this.textFieldWord = new BrailleWord(translateIfPossible.cells());
            this.textFieldTextToBraillePositions = translateIfPossible.textToBraillePositions();
            this.textFieldTextBrailleToTextPositions = translateIfPossible.brailleToTextPositions();
            this.holdingsClickableByteRange = new Range(-1, -1);
            this.actionClickableByteRange = new Range(-1, -1);
        }

        public static void appendPositionsToList(List list, int i, int i2, int i3, List list2) {
            while (i2 < i3) {
                list.add(Integer.valueOf(((Integer) list2.get(i2)).intValue() + i));
                i2++;
            }
        }

        public final void appendSpace() {
            this.textToBraillePositions.add(Integer.valueOf(this.brailleWord.size()));
            this.brailleToTextPositions.add(Integer.valueOf(this.textOnOverlay.length()));
            this.brailleWord.append(BrailleCharacter.EMPTY_CELL);
            this.textOnOverlay.append(" ");
        }

        public final boolean cursorInTheEnd() {
            if (((Integer) this.textSelectionRange.getLower()).intValue() == this.textFieldText.length()) {
                return true;
            }
            return false;
        }

        public final boolean noSelection() {
            if (((Integer) this.textSelectionRange.getLower()).intValue() == ((Integer) this.textSelectionRange.getUpper()).intValue()) {
                return true;
            }
            return false;
        }

        public final TranslationResult translateIfPossible(CharSequence charSequence, int i) {
            TranslationResult translate = this.translator.translate(charSequence, i);
            if (translate == null) {
                return TranslationResult.createUnknown(charSequence, i);
            }
            return translate;
        }
    }

    public AssembledResult() {
    }

    public final Range actionClickableByteRange() {
        return this.actionClickableByteRange;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssembledResult) {
            AssembledResult assembledResult = (AssembledResult) obj;
            if (this.textByteSelection.equals(assembledResult.textByteSelection()) && ContextDataProvider.equalsImpl(this.textFieldTextClickableByteRange, assembledResult.textFieldTextClickableByteRange()) && this.holdingsClickableByteRange.equals(assembledResult.holdingsClickableByteRange()) && this.actionClickableByteRange.equals(assembledResult.actionClickableByteRange()) && this.overlayTranslationResult.equals(assembledResult.overlayTranslationResult())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((this.textByteSelection.hashCode() ^ 1000003) * 1000003) ^ this.textFieldTextClickableByteRange.hashCode()) * 1000003) ^ this.holdingsClickableByteRange.hashCode()) * 1000003) ^ this.actionClickableByteRange.hashCode()) * 1000003) ^ this.overlayTranslationResult.hashCode();
    }

    public final Range holdingsClickableByteRange() {
        return this.holdingsClickableByteRange;
    }

    public final TranslationResult overlayTranslationResult() {
        return this.overlayTranslationResult;
    }

    public final SelectionRange textByteSelection() {
        return this.textByteSelection;
    }

    public final ImmutableList textFieldTextClickableByteRange() {
        return this.textFieldTextClickableByteRange;
    }

    public final String toString() {
        TranslationResult translationResult = this.overlayTranslationResult;
        Range range = this.actionClickableByteRange;
        Range range2 = this.holdingsClickableByteRange;
        ImmutableList immutableList = this.textFieldTextClickableByteRange;
        return "AssembledResult{textByteSelection=" + this.textByteSelection.toString() + ", textFieldTextClickableByteRange=" + immutableList.toString() + ", holdingsClickableByteRange=" + range2.toString() + ", actionClickableByteRange=" + range.toString() + ", overlayTranslationResult=" + translationResult.toString() + "}";
    }

    public AssembledResult(SelectionRange selectionRange, ImmutableList immutableList, Range range, Range range2, TranslationResult translationResult) {
        this();
        this.textByteSelection = selectionRange;
        if (immutableList == null) {
            throw new NullPointerException("Null textFieldTextClickableByteRange");
        }
        this.textFieldTextClickableByteRange = immutableList;
        if (range != null) {
            this.holdingsClickableByteRange = range;
            if (range2 != null) {
                this.actionClickableByteRange = range2;
                this.overlayTranslationResult = translationResult;
                return;
            }
            throw new NullPointerException("Null actionClickableByteRange");
        }
        throw new NullPointerException("Null holdingsClickableByteRange");
    }
}
