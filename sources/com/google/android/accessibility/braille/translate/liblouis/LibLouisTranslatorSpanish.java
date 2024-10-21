package com.google.android.accessibility.braille.translate.liblouis;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LibLouisTranslatorSpanish extends LibLouisTranslator {
    private static final String[] INVERTIBLE_PUNCTUATION_SEQUENCES = {"?", "!", "?!", "!?"};
    private static final Map PUNCTUATION_INVERSE_MAP;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        PUNCTUATION_INVERSE_MAP = linkedHashMap;
        linkedHashMap.put('?', (char) 191);
        linkedHashMap.put('!', (char) 161);
    }

    public LibLouisTranslatorSpanish(Context context) {
        super(context, "es.tbl");
        this.commutativityMap.put(new BrailleCharacter(3), ".");
        this.commutativityMap.put(new BrailleCharacter(2, 3), ";");
        this.commutativityMap.put(new BrailleCharacter(2, 5), ":");
        this.commutativityMap.put(new BrailleCharacter(2, 6), "?");
    }

    @Override // com.google.android.accessibility.braille.translate.liblouis.LibLouisTranslator
    protected final String transformPostTranslation(String str) {
        if (str.length() > 0) {
            if (PUNCTUATION_INVERSE_MAP.containsKey(Character.valueOf(str.charAt(0)))) {
                String[] strArr = INVERTIBLE_PUNCTUATION_SEQUENCES;
                for (int i = 0; i < 4; i++) {
                    String str2 = strArr[i];
                    if (str.startsWith(str2)) {
                        str = String.valueOf(str2.replace('?', (char) 191).replace('!', (char) 161)).concat(String.valueOf(str.substring(str2.length())));
                    }
                }
            }
        }
        return str;
    }
}
