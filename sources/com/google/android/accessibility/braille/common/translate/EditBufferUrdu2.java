package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferUrdu2 extends EditBufferContracted {
    public EditBufferUrdu2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        map.put("1", "ا");
        map.put("345", "آ");
        map.put("12", "ب");
        map.put("1234", "پ");
        map.put("2345", "ت");
        map.put("246", "ٹ");
        map.put("1456", "ث");
        map.put("245", "ج");
        map.put("14", "چ");
        map.put("156", "ح ");
        map.put("1346", "خ");
        map.put("145", "د");
        map.put("346", "ڈ");
        map.put("2346", "ذ");
        map.put("1235", "ر");
        map.put("12456", "ڑ");
        map.put("1256", "ز");
        map.put("234", "س");
        map.put("146", "ش");
        map.put("12346", "ص");
        map.put("1246", "ض");
        map.put("23456", "ط");
        map.put("123456", "ظ");
        map.put("12356", "ع");
        map.put("126", "غ");
        map.put("124", "ف");
        map.put("12345", "ق");
        map.put("13", "ک");
        map.put("1245", "گ");
        map.put("123", "ل");
        map.put("134", "م");
        map.put("1345", "ن");
        map.put("56", "ں");
        map.put("2456", "و");
        map.put("125", "ه");
        map.put("236", "ھ");
        map.put("3", "ء");
        map.put("24", "ی");
        map.put("34", "ے");
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
        return null;
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
