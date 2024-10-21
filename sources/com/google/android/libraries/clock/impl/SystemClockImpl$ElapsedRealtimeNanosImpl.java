package com.google.android.libraries.clock.impl;

import android.os.SystemClock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemClockImpl$ElapsedRealtimeNanosImpl {
    public static final boolean ELAPSED_REALTIME_NANOS_EXISTS;

    static {
        boolean z;
        try {
            SystemClock.elapsedRealtimeNanos();
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        ELAPSED_REALTIME_NANOS_EXISTS = z;
    }
}
