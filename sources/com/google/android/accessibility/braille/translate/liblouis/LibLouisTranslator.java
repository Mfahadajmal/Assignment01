package com.google.android.accessibility.braille.translate.liblouis;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import androidx.core.content.ContextCompat$Api24Impl;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.android.accessibility.utils.braille.BrailleUnicode;
import com.google.android.marvin.talkback.R;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.stream.Collectors;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LibLouisTranslator implements BrailleTranslator {
    public final Map bypassMap;
    public final Map commutativityMap;
    private final String tableName;

    public LibLouisTranslator(Context context, String str) {
        this.tableName = str;
        Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
        File externalFilesDir = createDeviceProtectedStorageContext.getExternalFilesDir(null);
        File[] listFiles = new File(externalFilesDir, "/liblouis/tables").listFiles();
        if (listFiles == null || listFiles.length == 0) {
            externalFilesDir = createDeviceProtectedStorageContext.getDir("translator", 0);
            _BOUNDARY.extractTables(createDeviceProtectedStorageContext.getResources(), R.raw.translationtables, externalFilesDir);
        }
        LouisTranslation.setTablesDirNative(externalFilesDir.getPath());
        this.bypassMap = new LinkedHashMap();
        this.commutativityMap = new LinkedHashMap();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibLouisTranslator)) {
            return false;
        }
        return this.tableName.equals(((LibLouisTranslator) obj).tableName);
    }

    public final int hashCode() {
        return Objects.hashCode(this.tableName);
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final TranslationResult translate(CharSequence charSequence, int i) {
        TranslationResult translateNative = LouisTranslation.translateNative(charSequence, this.tableName, i);
        int i2 = 0;
        while (i2 < charSequence.length()) {
            int i3 = i2 + 1;
            if (BrailleUnicode.isBraille(charSequence.charAt(i2))) {
                translateNative = TranslationResult.correctTranslation(translateNative, new BrailleWord(new BrailleCharacter(BrailleUnicode.toDotNumbers(charSequence.charAt(i2)))), i2, i3);
            }
            i2 = i3;
        }
        return translateNative;
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final String translateToPrint(BrailleWord brailleWord) {
        String translateToPrintDirect$ar$ds;
        String str = (String) this.bypassMap.get(brailleWord);
        if (str == null) {
            String translateToPrintDirect$ar$ds2 = translateToPrintDirect$ar$ds(brailleWord);
            if (brailleWord.containsAny(this.commutativityMap.keySet())) {
                List<BrailleWord> list = brailleWord.tokenize(this.commutativityMap.keySet());
                if (translateToPrintDirect$ar$ds2.equals((String) Collection.EL.stream(list).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(this, 2)).collect(Collectors.joining()))) {
                    StringBuilder sb = new StringBuilder();
                    for (BrailleWord brailleWord2 : list) {
                        if (brailleWord2.size() == 1 && this.commutativityMap.containsKey(brailleWord2.get(0))) {
                            translateToPrintDirect$ar$ds = (String) this.commutativityMap.get(brailleWord2.get(0));
                        } else {
                            translateToPrintDirect$ar$ds = translateToPrintDirect$ar$ds(brailleWord2);
                        }
                        sb.append(translateToPrintDirect$ar$ds);
                    }
                    translateToPrintDirect$ar$ds2 = sb.toString();
                }
            }
            return transformPostTranslation(translateToPrintDirect$ar$ds2);
        }
        return str;
    }

    public final String translateToPrintDirect$ar$ds(BrailleWord brailleWord) {
        return LouisTranslation.backTranslateNative(brailleWord.toByteArray(), this.tableName, BrailleInputEvent.CMD_INCREASE_AUTO_SCROLL_DURATION);
    }

    public LibLouisTranslator(Context context) {
        this(context, "ar-ar-g1.utb");
    }

    public LibLouisTranslator(Context context, byte[] bArr) {
        this(context, "fr-bfu-comp6.utb");
        this.commutativityMap.put(new BrailleCharacter(3, 4, 5), "@");
        this.commutativityMap.put(new BrailleCharacter(2, 3, 5, 6), "\"");
    }

    public LibLouisTranslator(Context context, char[] cArr) {
        this(context, "pl.tbl");
    }

    protected String transformPostTranslation(String str) {
        return str;
    }
}
