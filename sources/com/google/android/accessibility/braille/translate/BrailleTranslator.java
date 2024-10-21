package com.google.android.accessibility.braille.translate;

import com.google.android.accessibility.braille.interfaces.BrailleWord;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BrailleTranslator {
    TranslationResult translate(CharSequence charSequence, int i);

    String translateToPrint(BrailleWord brailleWord);
}
