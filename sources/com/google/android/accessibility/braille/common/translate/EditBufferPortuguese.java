package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferPortuguese extends EditBufferUnContracted {
    public EditBufferPortuguese(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBufferUnContracted
    protected final BrailleCharacter getCapitalize() {
        return BrailleTranslateUtils.DOTS46;
    }
}
