package com.google.android.accessibility.braille.common;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TouchDots {
    AUTO_DETECT(R.string.auto_detect, R.string.auto_detect),
    SCREEN_AWAY(R.string.screen_away, R.string.screen_away_detail),
    TABLETOP(R.string.tabletop, R.string.tabletop_detail);

    public final int layoutDescriptionStringId;
    public final int layoutNameStringId;

    TouchDots(int i, int i2) {
        this.layoutNameStringId = i;
        this.layoutDescriptionStringId = i2;
    }
}
