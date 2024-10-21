package com.google.android.libraries.performance.primes.flogger.logargs;

import com.google.android.libraries.performance.primes.NoPiiString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NonSensitiveLogParameterInternal {
    private final String value;

    public NonSensitiveLogParameterInternal() {
    }

    public final String getSafeString() {
        return this.value;
    }

    public final String toString() {
        return getSafeString();
    }

    public NonSensitiveLogParameterInternal(NoPiiString noPiiString) {
        this();
        this.value = noPiiString.value;
    }
}
