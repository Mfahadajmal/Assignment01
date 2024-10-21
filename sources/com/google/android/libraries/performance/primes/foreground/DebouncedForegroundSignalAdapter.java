package com.google.android.libraries.performance.primes.foreground;

import com.google.android.libraries.performance.primes.NoPiiString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DebouncedForegroundSignalAdapter extends AbstractForegroundSignalAdapter {
    private final ForegroundListener foregroundListener;

    public DebouncedForegroundSignalAdapter(ForegroundListener foregroundListener) {
        this.foregroundListener = foregroundListener;
    }

    @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
    public final void emitBackgroundSignal(NoPiiString noPiiString) {
        this.foregroundListener.onAppToBackground(noPiiString);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
    public final void emitForegroundSignal(NoPiiString noPiiString) {
        this.foregroundListener.onAppToForeground(noPiiString);
    }

    public final void onActivityPaused(NoPiiString noPiiString) {
        observeBackgroundSignal(noPiiString);
    }

    public final void onActivityResumed(NoPiiString noPiiString) {
        observeForegroundSignal(noPiiString);
    }
}
