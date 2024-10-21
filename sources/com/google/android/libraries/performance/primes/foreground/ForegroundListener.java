package com.google.android.libraries.performance.primes.foreground;

import com.google.android.libraries.performance.primes.NoPiiString;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ForegroundListener {
    void onAppToBackground(NoPiiString noPiiString);

    void onAppToForeground(NoPiiString noPiiString);
}
