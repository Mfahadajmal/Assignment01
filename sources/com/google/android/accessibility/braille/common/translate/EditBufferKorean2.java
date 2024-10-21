package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.common.collect.ImmutableMap;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferKorean2 extends EditBufferContracted {
    public EditBufferKorean2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0("126", "ㅏ");
        builder.put$ar$ds$de9b9d28_0("345", "ㅑ");
        builder.put$ar$ds$de9b9d28_0("234", "ㅓ");
        builder.put$ar$ds$de9b9d28_0("156", "ㅕ");
        builder.put$ar$ds$de9b9d28_0("136", "ㅗ");
        builder.put$ar$ds$de9b9d28_0("346", "ㅛ");
        builder.put$ar$ds$de9b9d28_0("134", "ㅜ");
        builder.put$ar$ds$de9b9d28_0("146", "ㅠ");
        builder.put$ar$ds$de9b9d28_0("246", "ㅡ");
        builder.put$ar$ds$de9b9d28_0("135", "ㅣ");
        builder.put$ar$ds$de9b9d28_0("1345", "ㅔ");
        builder.put$ar$ds$de9b9d28_0("1235", "ㅐ");
        builder.put$ar$ds$de9b9d28_0("34", "ㅖ");
        builder.put$ar$ds$de9b9d28_0("2456", "ㅢ");
        builder.put$ar$ds$de9b9d28_0("1236", "ㅘ");
        builder.put$ar$ds$de9b9d28_0("1234", "ㅝ");
        builder.put$ar$ds$de9b9d28_0("13456", "ㅚ");
        builder.put$ar$ds$de9b9d28_0("345-1235", "ㅒ");
        builder.put$ar$ds$de9b9d28_0("1236-1235", "ㅙ");
        builder.put$ar$ds$de9b9d28_0("1234-1235", "ㅞ");
        builder.put$ar$ds$de9b9d28_0("134-1235", "ㅟ");
        ImmutableMap buildOrThrow = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        builder2.put$ar$ds$de9b9d28_0("4", "ㄱ");
        builder2.put$ar$ds$de9b9d28_0("14", "ㄴ");
        builder2.put$ar$ds$de9b9d28_0("24", "ㄷ");
        builder2.put$ar$ds$de9b9d28_0("5", "ㄹ");
        builder2.put$ar$ds$de9b9d28_0("15", "ㅁ");
        builder2.put$ar$ds$de9b9d28_0("45", "ㅂ");
        builder2.put$ar$ds$de9b9d28_0("6", "ㅅ");
        builder2.put$ar$ds$de9b9d28_0("46", "ㅈ");
        builder2.put$ar$ds$de9b9d28_0("56", "ㅊ");
        builder2.put$ar$ds$de9b9d28_0("124", "ㅋ");
        builder2.put$ar$ds$de9b9d28_0("125", "ㅌ");
        builder2.put$ar$ds$de9b9d28_0("145", "ㅍ");
        builder2.put$ar$ds$de9b9d28_0("245", "ㅎ");
        builder2.put$ar$ds$de9b9d28_0("1", "ㄱ");
        builder2.put$ar$ds$de9b9d28_0("25", "ㄴ");
        builder2.put$ar$ds$de9b9d28_0("35", "ㄷ");
        builder2.put$ar$ds$de9b9d28_0("2", "ㄹ");
        builder2.put$ar$ds$de9b9d28_0("26", "ㅁ");
        builder2.put$ar$ds$de9b9d28_0("12", "ㅂ");
        builder2.put$ar$ds$de9b9d28_0("3", "ㅅ");
        builder2.put$ar$ds$de9b9d28_0("13", "ㅈ");
        builder2.put$ar$ds$de9b9d28_0("23", "ㅊ");
        builder2.put$ar$ds$de9b9d28_0("235", "ㅋ");
        builder2.put$ar$ds$de9b9d28_0("236", "ㅌ");
        builder2.put$ar$ds$de9b9d28_0("256", "ㅍ");
        builder2.put$ar$ds$de9b9d28_0("356", "ㅎ");
        builder2.put$ar$ds$de9b9d28_0("1256", "ㅇ");
        map.putAll(builder2.buildOrThrow());
        map.putAll(buildOrThrow);
        map2.putAll(map);
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
