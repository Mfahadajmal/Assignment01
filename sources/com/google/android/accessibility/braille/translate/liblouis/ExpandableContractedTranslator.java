package com.google.android.accessibility.braille.translate.liblouis;

import android.text.TextUtils;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import j$.util.Objects;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpandableContractedTranslator implements BrailleTranslator {
    private final BrailleTranslator g1Translator;
    private final BrailleTranslator g2Translator;

    public ExpandableContractedTranslator(BrailleTranslator brailleTranslator, BrailleTranslator brailleTranslator2) {
        this.g1Translator = brailleTranslator;
        this.g2Translator = brailleTranslator2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExpandableContractedTranslator)) {
            return false;
        }
        ExpandableContractedTranslator expandableContractedTranslator = (ExpandableContractedTranslator) obj;
        if (this.g1Translator.equals(expandableContractedTranslator.g1Translator) && this.g2Translator.equals(expandableContractedTranslator.g2Translator)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.g1Translator, this.g2Translator);
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final TranslationResult translate(CharSequence charSequence, int i) {
        ImmutableList<Integer> immutableList;
        ExpandableContractedTranslator expandableContractedTranslator = this;
        int i2 = -1;
        for (String str : Splitter.on(' ').split(charSequence)) {
            if (!TextUtils.isEmpty(str)) {
                int indexOf = charSequence.toString().indexOf(str, i2 + 1);
                int length = str.length() + indexOf;
                if (indexOf <= i && i <= length - 1) {
                    CharSequence subSequence = charSequence.subSequence(0, indexOf);
                    CharSequence subSequence2 = charSequence.subSequence(indexOf, length);
                    CharSequence subSequence3 = charSequence.subSequence(length, charSequence.length());
                    TranslationResult translate = expandableContractedTranslator.g2Translator.translate(subSequence, -1);
                    TranslationResult translate2 = expandableContractedTranslator.g1Translator.translate(subSequence2, -1);
                    TranslationResult translate3 = expandableContractedTranslator.g2Translator.translate(subSequence3, -1);
                    BrailleWord cells = translate.cells();
                    BrailleWord cells2 = translate2.cells();
                    BrailleWord cells3 = translate3.cells();
                    ImmutableList<Integer> textToBraillePositions = translate.textToBraillePositions();
                    ImmutableList<Integer> brailleToTextPositions = translate.brailleToTextPositions();
                    ImmutableList<Integer> textToBraillePositions2 = translate2.textToBraillePositions();
                    ImmutableList<Integer> brailleToTextPositions2 = translate2.brailleToTextPositions();
                    ImmutableList<Integer> textToBraillePositions3 = translate3.textToBraillePositions();
                    ImmutableList<Integer> brailleToTextPositions3 = translate3.brailleToTextPositions();
                    BrailleWord brailleWord = new BrailleWord();
                    brailleWord.append(cells);
                    brailleWord.append(cells2);
                    brailleWord.append(cells3);
                    ArrayList arrayList = new ArrayList(charSequence.length());
                    ArrayList arrayList2 = new ArrayList(brailleWord.size());
                    int i3 = 0;
                    while (i3 < charSequence.length()) {
                        if (i3 < subSequence.length()) {
                            arrayList.add(textToBraillePositions.get(i3));
                            immutableList = textToBraillePositions;
                        } else if (i3 < subSequence.length() + subSequence2.length()) {
                            immutableList = textToBraillePositions;
                            arrayList.add(Integer.valueOf(cells.size() + textToBraillePositions2.get(i3 - subSequence.length()).intValue()));
                        } else {
                            immutableList = textToBraillePositions;
                            if (i3 < charSequence.length()) {
                                arrayList.add(Integer.valueOf(cells.size() + cells2.size() + textToBraillePositions3.get((i3 - subSequence.length()) - subSequence2.length()).intValue()));
                            }
                        }
                        i3++;
                        textToBraillePositions = immutableList;
                    }
                    for (int i4 = 0; i4 < brailleWord.size(); i4++) {
                        if (i4 < cells.size()) {
                            arrayList2.add(brailleToTextPositions.get(i4));
                        } else if (i4 < cells.size() + cells2.size()) {
                            arrayList2.add(Integer.valueOf(subSequence.length() + brailleToTextPositions2.get(i4 - cells.size()).intValue()));
                        } else if (i4 < brailleWord.size()) {
                            arrayList2.add(Integer.valueOf(subSequence.length() + subSequence2.length() + brailleToTextPositions3.get((i4 - cells.size()) - cells2.size()).intValue()));
                        }
                    }
                    TranslationResult.Builder builder = TranslationResult.builder();
                    builder.setText(charSequence);
                    builder.setCells(brailleWord);
                    builder.setTextToBraillePositions(arrayList);
                    builder.setBrailleToTextPositions(arrayList2);
                    builder.setCursorBytePosition(i);
                    return builder.build();
                }
                expandableContractedTranslator = this;
                i2 = length;
            } else {
                expandableContractedTranslator = this;
            }
        }
        return expandableContractedTranslator.g2Translator.translate(charSequence, i);
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final String translateToPrint(BrailleWord brailleWord) {
        return this.g2Translator.translateToPrint(brailleWord);
    }
}
