package com.google.android.accessibility.braille.translate.liblouis;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleWord;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LibLouisTranslatorUeb1 extends LibLouisTranslator {
    public LibLouisTranslatorUeb1(Context context) {
        super(context, "en-ueb-g1.ctb");
        this.bypassMap.put(BrailleWord.create("46-134"), "μ");
        this.bypassMap.put(BrailleWord.create("6-46-134"), "Μ");
        this.bypassMap.put(BrailleWord.create("6-46-234"), "Σ");
    }

    @Override // com.google.android.accessibility.braille.translate.liblouis.LibLouisTranslator
    protected final String transformPostTranslation(String str) {
        return str.replace((char) 8217, '\'');
    }
}
