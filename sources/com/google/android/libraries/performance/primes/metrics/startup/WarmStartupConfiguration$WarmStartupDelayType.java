package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum WarmStartupConfiguration$WarmStartupDelayType implements Internal.EnumLite {
    DELAY_UNSPECIFIED(0),
    DELAY_10MS(1),
    DELAY_100MS(2),
    DELAY_250MS(3),
    DELAY_500MS(4);

    public final int value;

    WarmStartupConfiguration$WarmStartupDelayType(int i) {
        this.value = i;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.value);
    }
}
