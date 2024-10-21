package com.google.common.android.base;

import android.os.SystemClock;
import com.google.common.base.Ticker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidTicker {
    public static final Ticker SYSTEM_TICKER;

    static {
        Ticker ticker;
        try {
            SystemClock.elapsedRealtimeNanos();
            ticker = new Ticker() { // from class: com.google.common.android.base.AndroidTicker.1
                @Override // com.google.common.base.Ticker
                public final long read() {
                    return SystemClock.elapsedRealtimeNanos();
                }
            };
        } catch (Throwable unused) {
            SystemClock.elapsedRealtime();
            ticker = new Ticker() { // from class: com.google.common.android.base.AndroidTicker.2
                @Override // com.google.common.base.Ticker
                public final long read() {
                    return SystemClock.elapsedRealtime() * 1000000;
                }
            };
        }
        SYSTEM_TICKER = ticker;
    }
}
