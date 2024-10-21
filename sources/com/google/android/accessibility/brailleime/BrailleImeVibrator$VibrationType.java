package com.google.android.accessibility.brailleime;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeVibrator$VibrationType {
    BRAILLE_COMMISSION(25, BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE),
    SPACE_DELETE_OR_MOVE_CURSOR_OR_GRANULARITY(70, 150),
    NEWLINE_OR_DELETE_WORD(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE, 180),
    HOLD(25, 200),
    OTHER_GESTURES(190, 210);

    public final int amplitude;
    public final int duration;

    BrailleImeVibrator$VibrationType(int i, int i2) {
        this.duration = i;
        this.amplitude = i2;
    }
}
