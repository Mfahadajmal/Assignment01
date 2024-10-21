package com.google.android.accessibility.brailleime.analytics;

import j$.time.Duration;
import j$.time.Instant;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeAnalyticsHelper$SessionCache {
    public Instant sessionStart;
    public int totalBrailleCharCount;
    public Duration totalSessionDuration = Duration.ZERO;
    public final HashMap gestureActMap = new HashMap();

    public final boolean isSessionStartValid() {
        if (this.sessionStart != null) {
            return true;
        }
        return false;
    }
}
