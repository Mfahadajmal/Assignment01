package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import android.support.v7.app.AppCompatDelegateImpl;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferUeb2 extends EditBufferContracted {
    private final Set forceNonInitialTranslationSet;

    public EditBufferUeb2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
        HashSet hashSet = new HashSet();
        this.forceNonInitialTranslationSet = hashSet;
        hashSet.add("2");
        hashSet.add("23");
        hashSet.add("25");
        hashSet.add("235");
        hashSet.add("256");
        hashSet.add("2356");
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        map.put("1", "a");
        map.put("2", ",");
        map.put("12", "b");
        map.put("3", "'");
        map.put("13", "k");
        map.put("23", "be");
        map.put("123", "l");
        map.put("14", "c");
        map.put("24", "i");
        map.put("124", "f");
        map.put("34", "st");
        map.put("134", "m");
        map.put("234", "s");
        map.put("1234", "p");
        map.put("15", "e");
        map.put("25", "con");
        map.put("125", "h");
        map.put("35", "in");
        map.put("135", "o");
        map.put("235", "!");
        map.put("1235", "r");
        map.put("145", "d");
        map.put("245", "j");
        map.put("1245", "g");
        map.put("345", "ar");
        map.put("1345", "n");
        map.put("2345", "t");
        map.put("12345", "q");
        map.put("16", "ch");
        map.put("26", "en");
        map.put("126", "gh");
        map.put("36", "-");
        map.put("136", "u");
        map.put("1236", "v");
        map.put("146", "sh");
        map.put("246", "ow");
        map.put("1246", "ed");
        map.put("1346", "x");
        map.put("2346", "the");
        map.put("12346", "and");
        map.put("156", "wh");
        map.put("256", "dis");
        map.put("1256", "ou");
        map.put("1356", "z");
        map.put("12356", "of");
        map.put("1456", "th");
        map.put("2456", "w");
        map.put("12456", "er");
        map.put("13456", "y");
        map.put("23456", "with");
        map.put("123456", "for");
        map2.put("346", "ing");
        map2.put("356", "\"");
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean forceInitialDefaultTranslation(String str) {
        return false;
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
        return AppCompatDelegateImpl.Api33Impl.m1BrailleLanguages$Code$1IA$ar$MethodMerging(c);
    }
}
