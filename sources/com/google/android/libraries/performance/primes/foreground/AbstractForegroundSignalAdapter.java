package com.google.android.libraries.performance.primes.foreground;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.flogger.GoogleLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractForegroundSignalAdapter {
    public int foregroundState$ar$edu = 1;

    public abstract void emitBackgroundSignal(NoPiiString noPiiString);

    public abstract void emitForegroundSignal(NoPiiString noPiiString);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void observeBackgroundSignal(NoPiiString noPiiString) {
        if (this.foregroundState$ar$edu == 3) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/foreground/AbstractForegroundSignalAdapter", "observeBackgroundSignal", 42, "AbstractForegroundSignalAdapter.java")).log("Already in the foreground, not background");
        } else {
            this.foregroundState$ar$edu = 3;
            emitBackgroundSignal(noPiiString);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void observeForegroundSignal(NoPiiString noPiiString) {
        if (this.foregroundState$ar$edu == 2) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/foreground/AbstractForegroundSignalAdapter", "observeForegroundSignal", 31, "AbstractForegroundSignalAdapter.java")).log("Already in the foreground, not transitioning");
        } else {
            this.foregroundState$ar$edu = 2;
            emitForegroundSignal(noPiiString);
        }
    }
}
