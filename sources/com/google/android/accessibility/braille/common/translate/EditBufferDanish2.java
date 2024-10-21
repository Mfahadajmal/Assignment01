package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.talkback.TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0;
import com.google.common.collect.ImmutableMap;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.Map;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferDanish2 extends EditBufferContracted {
    private static final ImmutableMap INITIAL_MAP;

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0("1", "a");
        builder.put$ar$ds$de9b9d28_0("12", "b");
        builder.put$ar$ds$de9b9d28_0("14", "c");
        builder.put$ar$ds$de9b9d28_0("145", "d");
        builder.put$ar$ds$de9b9d28_0("15", "e");
        builder.put$ar$ds$de9b9d28_0("124", "f");
        builder.put$ar$ds$de9b9d28_0("1245", "g");
        builder.put$ar$ds$de9b9d28_0("125", "h");
        builder.put$ar$ds$de9b9d28_0("24", "i");
        builder.put$ar$ds$de9b9d28_0("245", "j");
        builder.put$ar$ds$de9b9d28_0("13", "k");
        builder.put$ar$ds$de9b9d28_0("123", "l");
        builder.put$ar$ds$de9b9d28_0("134", "m");
        builder.put$ar$ds$de9b9d28_0("1345", "n");
        builder.put$ar$ds$de9b9d28_0("135", "o");
        builder.put$ar$ds$de9b9d28_0("1234", "p");
        builder.put$ar$ds$de9b9d28_0("12345", "q");
        builder.put$ar$ds$de9b9d28_0("1235", "r");
        builder.put$ar$ds$de9b9d28_0("234", "s");
        builder.put$ar$ds$de9b9d28_0("2345", "t");
        builder.put$ar$ds$de9b9d28_0("136", "u");
        builder.put$ar$ds$de9b9d28_0("1236", "v");
        builder.put$ar$ds$de9b9d28_0("2456", "w");
        builder.put$ar$ds$de9b9d28_0("1346", "x");
        builder.put$ar$ds$de9b9d28_0("13456", "y");
        builder.put$ar$ds$de9b9d28_0("1356", "z");
        builder.put$ar$ds$de9b9d28_0("345", "æ");
        builder.put$ar$ds$de9b9d28_0("246", "ø");
        builder.put$ar$ds$de9b9d28_0("16", "å");
        INITIAL_MAP = builder.buildOrThrow();
    }

    public EditBufferDanish2(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferContracted
    protected final void fillTranslatorMaps(Map map, Map map2) {
        Map.EL.forEach(INITIAL_MAP, new TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0(map, 1));
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
