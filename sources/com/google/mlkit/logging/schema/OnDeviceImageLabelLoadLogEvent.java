package com.google.mlkit.logging.schema;

import io.grpc.internal.AtomicLongCounter;
import io.grpc.internal.LongCounter;
import io.grpc.internal.ReflectionLongAdderCounter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceImageLabelLoadLogEvent {
    public static LongCounter create() {
        if (ReflectionLongAdderCounter.initializationException == null) {
            return new ReflectionLongAdderCounter();
        }
        return new AtomicLongCounter();
    }
}
