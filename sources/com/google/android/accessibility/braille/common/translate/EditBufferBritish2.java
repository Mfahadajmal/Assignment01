package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferBritish2 extends EditBufferContracted {
    private final Set forceInitialTranslationSet;
    private final Set forceNonInitialTranslationSet;

    public EditBufferBritish2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
        HashSet hashSet = new HashSet();
        this.forceInitialTranslationSet = hashSet;
        HashSet hashSet2 = new HashSet();
        this.forceNonInitialTranslationSet = hashSet2;
        hashSet.add("6");
        hashSet2.add("3456");
        hashSet2.add("45");
        hashSet2.add("456");
        hashSet2.add("5");
        hashSet2.add("56");
        hashSet2.add("2");
        hashSet2.add("23");
        hashSet2.add("25");
        hashSet2.add("256");
        hashSet2.add("36");
        hashSet2.add("235");
        hashSet2.add("2356");
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        map.put("1", "a");
        map.put("12", "b");
        map.put("14", "c");
        map.put("145", "d");
        map.put("15", "e");
        map.put("124", "f");
        map.put("1245", "g");
        map.put("125", "h");
        map.put("24", "i");
        map.put("245", "j");
        map.put("13", "k");
        map.put("123", "l");
        map.put("134", "m");
        map.put("1345", "n");
        map.put("135", "o");
        map.put("1234", "p");
        map.put("12345", "q");
        map.put("1235", "r");
        map.put("234", "s");
        map.put("2345", "t");
        map.put("136", "u");
        map.put("1236", "v");
        map.put("2456", "w");
        map.put("1346", "x");
        map.put("13456", "y");
        map.put("1356", "z");
        map.put("12346", "and");
        map.put("123456", "for");
        map.put("12356", "of");
        map.put("2346", "the");
        map.put("23456", "with");
        map.put("16", "ch");
        map.put("126", "gh");
        map.put("146", "sh");
        map.put("1456", "th");
        map.put("156", "wh");
        map.put("1246", "ed");
        map.put("12456", "er");
        map.put("1256", "ou");
        map.put("246", "ow");
        map.put("34", "st");
        map.put("345", "ar");
        map.put("26", "en");
        map.put("35", "in");
        map2.put("1", "a");
        map2.put("12", "b");
        map2.put("14", "c");
        map2.put("145", "d");
        map2.put("15", "e");
        map2.put("124", "f");
        map2.put("1245", "g");
        map2.put("125", "h");
        map2.put("24", "i");
        map2.put("245", "j");
        map2.put("13", "k");
        map2.put("123", "l");
        map2.put("134", "m");
        map2.put("1345", "n");
        map2.put("135", "o");
        map2.put("1234", "p");
        map2.put("12345", "q");
        map2.put("1235", "r");
        map2.put("234", "s");
        map2.put("2345", "t");
        map2.put("136", "u");
        map2.put("1236", "v");
        map2.put("2456", "w");
        map2.put("1346", "x");
        map2.put("13456", "y");
        map2.put("1356", "z");
        map2.put("12346", "and");
        map2.put("123456", "for");
        map2.put("12356", "of");
        map2.put("2346", "the");
        map2.put("23456", "with");
        map2.put("16", "ch");
        map2.put("126", "gh");
        map2.put("146", "sh");
        map2.put("1456", "th");
        map2.put("156", "wh");
        map2.put("1246", "ed");
        map2.put("12456", "er");
        map2.put("1256", "ou");
        map2.put("246", "ow");
        map2.put("34", "st");
        map2.put("345", "ar");
        map2.put("346", "ing");
        map2.put("26", "en");
        map2.put("35", "in");
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean forceInitialDefaultTranslation(String str) {
        return this.forceInitialTranslationSet.contains(str);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean forceNonInitialDefaultTranslation(String str) {
        return this.forceNonInitialTranslationSet.contains(str);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final BrailleCharacter getCapitalize() {
        return BrailleTranslateUtils.DOT6;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final BrailleCharacter getNumeric() {
        return BrailleTranslateUtils.DOTS3456;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean isLetter(char c) {
        return false;
    }
}
