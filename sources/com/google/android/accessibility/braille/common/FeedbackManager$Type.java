package com.google.android.accessibility.braille.common;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FeedbackManager$Type {
    NAVIGATE_OUT_OF_BOUNDS(R.raw.complete),
    NAVIGATE_INTO_HIERARCHY(R.raw.chime_down),
    NAVIGATE_OUT_OF_HIERARCHY(R.raw.chime_up),
    DISPLAY_CONNECTED(R.raw.display_connected),
    DISPLAY_DISCONNECTED(R.raw.display_disconnected),
    COMMAND_FAILED(R.raw.double_beep),
    UNKNOWN_COMMAND(R.raw.double_beep),
    AUTO_SCROLL_START(R.raw.turn_on),
    AUTO_SCROLL_STOP(R.raw.turn_off),
    CALIBRATION(R.raw.calibration_done),
    BEEP(R.raw.volume_beep);

    public final int resId;

    FeedbackManager$Type(int i) {
        this.resId = i;
    }
}
