package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferVietnamese2 extends EditBufferContracted {
    public EditBufferVietnamese2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        map.put("1", "a");
        map.put("12", "b");
        map.put("14", "c");
        map.put("145", "d");
        map.put("15", "e");
        map.put("1245", "g");
        map.put("125", "h");
        map.put("24", "i");
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
        map.put("345", "ă");
        map.put("16", "â");
        map.put("2346", "đ");
        map.put("126", "ê");
        map.put("1456", "ô");
        map.put("246", "ơ");
        map.put("1256", "ư");
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean forceInitialDefaultTranslation(String str) {
        return false;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final boolean forceNonInitialDefaultTranslation(String str) {
        return false;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final BrailleCharacter getCapitalize() {
        return BrailleTranslateUtils.DOTS46;
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
