package com.google.mlkit.logging.schema;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceObjectInferenceLogEvent {
    public static final int compareValues(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static final int computeShift$ar$ds(int i) {
        return Integer.numberOfLeadingZeros(i) + 1;
    }
}
