package com.google.android.accessibility.braille.translate;

import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_TranslationResult extends TranslationResult {
    private final ImmutableList brailleToTextPositions;
    private final BrailleWord cells;
    private final Integer cursorBytePosition;
    private final CharSequence text;
    private final ImmutableList textToBraillePositions;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends TranslationResult.Builder {
        private ImmutableList brailleToTextPositions;
        private BrailleWord cells;
        private Integer cursorBytePosition;
        private CharSequence text;
        private ImmutableList textToBraillePositions;

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult build() {
            BrailleWord brailleWord;
            ImmutableList immutableList;
            ImmutableList immutableList2;
            Integer num;
            CharSequence charSequence = this.text;
            if (charSequence != null && (brailleWord = this.cells) != null && (immutableList = this.textToBraillePositions) != null && (immutableList2 = this.brailleToTextPositions) != null && (num = this.cursorBytePosition) != null) {
                return new AutoValue_TranslationResult(charSequence, brailleWord, immutableList, immutableList2, num);
            }
            StringBuilder sb = new StringBuilder();
            if (this.text == null) {
                sb.append(" text");
            }
            if (this.cells == null) {
                sb.append(" cells");
            }
            if (this.textToBraillePositions == null) {
                sb.append(" textToBraillePositions");
            }
            if (this.brailleToTextPositions == null) {
                sb.append(" brailleToTextPositions");
            }
            if (this.cursorBytePosition == null) {
                sb.append(" cursorBytePosition");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult.Builder setBrailleToTextPositions(List<Integer> list) {
            this.brailleToTextPositions = ImmutableList.copyOf((Collection) list);
            return this;
        }

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult.Builder setCells(BrailleWord brailleWord) {
            if (brailleWord != null) {
                this.cells = brailleWord;
                return this;
            }
            throw new NullPointerException("Null cells");
        }

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult.Builder setCursorBytePosition(int i) {
            this.cursorBytePosition = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult.Builder setText(CharSequence charSequence) {
            if (charSequence != null) {
                this.text = charSequence;
                return this;
            }
            throw new NullPointerException("Null text");
        }

        @Override // com.google.android.accessibility.braille.translate.TranslationResult.Builder
        public final TranslationResult.Builder setTextToBraillePositions(List<Integer> list) {
            this.textToBraillePositions = ImmutableList.copyOf((Collection) list);
            return this;
        }
    }

    public AutoValue_TranslationResult(CharSequence charSequence, BrailleWord brailleWord, ImmutableList immutableList, ImmutableList immutableList2, Integer num) {
        this.text = charSequence;
        this.cells = brailleWord;
        this.textToBraillePositions = immutableList;
        this.brailleToTextPositions = immutableList2;
        this.cursorBytePosition = num;
    }

    @Override // com.google.android.accessibility.braille.translate.TranslationResult
    public final ImmutableList<Integer> brailleToTextPositions() {
        return this.brailleToTextPositions;
    }

    @Override // com.google.android.accessibility.braille.translate.TranslationResult
    public final BrailleWord cells() {
        return this.cells;
    }

    @Override // com.google.android.accessibility.braille.translate.TranslationResult
    public final Integer cursorBytePosition() {
        return this.cursorBytePosition;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TranslationResult) {
            TranslationResult translationResult = (TranslationResult) obj;
            if (this.text.equals(translationResult.text()) && this.cells.equals(translationResult.cells()) && ContextDataProvider.equalsImpl(this.textToBraillePositions, translationResult.textToBraillePositions()) && ContextDataProvider.equalsImpl(this.brailleToTextPositions, translationResult.brailleToTextPositions()) && this.cursorBytePosition.equals(translationResult.cursorBytePosition())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((this.text.hashCode() ^ 1000003) * 1000003) ^ this.cells.hashCode()) * 1000003) ^ this.textToBraillePositions.hashCode()) * 1000003) ^ this.brailleToTextPositions.hashCode()) * 1000003) ^ this.cursorBytePosition.hashCode();
    }

    @Override // com.google.android.accessibility.braille.translate.TranslationResult
    public final CharSequence text() {
        return this.text;
    }

    @Override // com.google.android.accessibility.braille.translate.TranslationResult
    public final ImmutableList<Integer> textToBraillePositions() {
        return this.textToBraillePositions;
    }

    public final String toString() {
        ImmutableList immutableList = this.brailleToTextPositions;
        ImmutableList immutableList2 = this.textToBraillePositions;
        BrailleWord brailleWord = this.cells;
        return "TranslationResult{text=" + String.valueOf(this.text) + ", cells=" + String.valueOf(brailleWord) + ", textToBraillePositions=" + String.valueOf(immutableList2) + ", brailleToTextPositions=" + String.valueOf(immutableList) + ", cursorBytePosition=" + this.cursorBytePosition + "}";
    }
}
