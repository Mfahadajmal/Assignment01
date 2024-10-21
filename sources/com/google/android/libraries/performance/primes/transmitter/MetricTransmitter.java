package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MetricTransmitter {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Priority {
        static final Priority DEFAULT = new Priority(0);
        public final int priority;

        public Priority(int i) {
            this.priority = i;
        }
    }

    Priority getTransmitterPriority();

    ListenableFuture send(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric);
}
