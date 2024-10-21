package com.google.android.accessibility.braille.translate;

import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.AutoValue_TranslationResult;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TranslationResult {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract TranslationResult build();

        public abstract Builder setBrailleToTextPositions(List<Integer> list);

        public abstract Builder setCells(BrailleWord brailleWord);

        public abstract Builder setCursorBytePosition(int i);

        public abstract Builder setText(CharSequence charSequence);

        public abstract Builder setTextToBraillePositions(List<Integer> list);
    }

    public static TranslationResult appendOneEmptyCell(TranslationResult translationResult) {
        ArrayList arrayList = new ArrayList(translationResult.textToBraillePositions());
        arrayList.add(Integer.valueOf(translationResult.cells().size()));
        ArrayList arrayList2 = new ArrayList(translationResult.brailleToTextPositions());
        arrayList2.add(Integer.valueOf(translationResult.text().length()));
        translationResult.cells().append(BrailleCharacter.EMPTY_CELL);
        Builder builder = builder();
        builder.setCells(translationResult.cells());
        builder.setText(String.valueOf(String.valueOf(translationResult.text())).concat(" "));
        builder.setTextToBraillePositions(arrayList);
        builder.setBrailleToTextPositions(arrayList2);
        builder.setCursorBytePosition(translationResult.cursorBytePosition().intValue());
        return builder.build();
    }

    public static Builder builder() {
        return new AutoValue_TranslationResult.Builder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static TranslationResult correctTranslation(TranslationResult translationResult, BrailleWord brailleWord, int i, int i2) {
        BrailleWord brailleWord2 = new BrailleWord();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (i == 0) {
            arrayList2.addAll(Collections.nCopies(brailleWord.size(), 0));
            arrayList.addAll(Collections.nCopies(i2, 0));
        } else {
            int intValue = translationResult.textToBraillePositions().get(i).intValue();
            brailleWord2.append(translationResult.cells().subword(0, intValue));
            arrayList.addAll(translationResult.textToBraillePositions().subList(0, i));
            arrayList2.addAll(translationResult.brailleToTextPositions().subList(0, intValue));
            arrayList.addAll(Collections.nCopies(i2 - i, Integer.valueOf(brailleWord2.size())));
            for (int i3 = 0; i3 < brailleWord.size(); i3++) {
                arrayList2.add(Integer.valueOf(translationResult.text().subSequence(0, i).length()));
            }
        }
        brailleWord2.append(brailleWord);
        if (i2 != translationResult.text().length()) {
            int intValue2 = translationResult.textToBraillePositions().get(i2).intValue();
            int intValue3 = translationResult.textToBraillePositions().get(i).intValue();
            brailleWord2.append(translationResult.cells().subword(intValue2, translationResult.cells().size()));
            ImmutableList subList = translationResult.textToBraillePositions().subList(i2, translationResult.text().length());
            int size = subList.size();
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.add(Integer.valueOf((((Integer) subList.get(i4)).intValue() - intValue2) + intValue3 + brailleWord.size()));
            }
            arrayList2.addAll(translationResult.brailleToTextPositions().subList(intValue2, translationResult.brailleToTextPositions().size()));
        }
        Builder builder = builder();
        builder.setText(translationResult.text());
        builder.setCells(brailleWord2);
        builder.setTextToBraillePositions(arrayList);
        builder.setBrailleToTextPositions(arrayList2);
        builder.setCursorBytePosition(translationResult.cursorBytePosition().intValue());
        return builder.build();
    }

    public static TranslationResult createUnknown(CharSequence charSequence, int i) {
        ArrayList arrayList = new ArrayList(charSequence.length());
        BrailleWord brailleWord = new BrailleWord();
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            arrayList.add(Integer.valueOf(i2));
            brailleWord.append(BrailleCharacter.FULL_CELL);
        }
        Builder builder = builder();
        builder.setText(charSequence);
        builder.setCells(brailleWord);
        builder.setTextToBraillePositions(arrayList);
        builder.setBrailleToTextPositions(arrayList);
        builder.setCursorBytePosition(i);
        return builder.build();
    }

    public abstract ImmutableList<Integer> brailleToTextPositions();

    public abstract BrailleWord cells();

    public abstract Integer cursorBytePosition();

    public abstract CharSequence text();

    public abstract ImmutableList<Integer> textToBraillePositions();
}
