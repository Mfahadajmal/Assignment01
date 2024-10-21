package com.google.android.accessibility.brailleime.input;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultitouchResult {
    public List heldPoints;
    public List releasedPoints;
    public Swipe swipe;
    public int type;

    public final String toString() {
        return "MultitouchResult{type=" + this.type + ", releasePoints=" + String.valueOf(this.releasedPoints) + ", swipe=" + String.valueOf(this.swipe) + ", heldPoints=" + String.valueOf(this.heldPoints) + "}";
    }
}
